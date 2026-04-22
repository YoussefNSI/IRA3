const http = require("http");
const url = require("url");
const fs = require("fs").promises;
const path = require("path");
const ejs = require("ejs");

const PORT = 3001;
const HOST = "127.0.0.1";

// Fonction pour parser les cookies
function parseCookies(cookieHeader) {
  const cookies = {};
  if (cookieHeader) {
    cookieHeader.split(";").forEach((cookie) => {
      const parts = cookie.trim().split("=");
      if (parts.length === 2) {
        cookies[parts[0]] = parts[1];
      }
    });
  }
  return cookies;
}

// Fonction pour rendre un template EJS
async function renderTemplate(templateName, data) {
  try {
    const templatePath = path.join(__dirname, "views", templateName + ".ejs");
    const template = await fs.readFile(templatePath, "utf-8");
    // Ajouter filename pour que les includes EJS fonctionnent
    return ejs.render(template, { ...data, filename: templatePath });
  } catch (error) {
    console.error(`Erreur lors du rendu du template ${templateName}:`, error);
    throw error;
  }
}

// Création du serveur
const server = http.createServer(async (req, res) => {
  console.log("=== Nouvelle requête ===");
  console.log("URL:", req.url);
  try {
    // Parser l'URL de la requête
    const parsedUrl = url.parse(req.url, true);
    const pathname = parsedUrl.pathname;
    const query = parsedUrl.query;
    
    console.log("Pathname:", pathname);
    console.log("Query:", query);

    // Parser les cookies
    const cookies = parseCookies(req.headers.cookie);
    let isAdmin = cookies.isAdmin === "true";

    // Gérer le paramètre isAdmin depuis l'URL
    if (query.isAdmin !== undefined) {
      isAdmin = query.isAdmin === "true";
      // Définir le cookie pour persister l'état
      res.setHeader(
        "Set-Cookie",
        `isAdmin=${isAdmin}; Path=/; Max-Age=86400; SameSite=Lax`
      );
    }

    // Configuration du Content-Type HTML
    res.writeHead(200, { "Content-Type": "text/html; charset=utf-8" });

    // Données communes pour tous les templates
    const commonData = {
      isAdmin: isAdmin,
    };

    // Gestion des différentes routes
    switch (pathname) {
      case "/":
        const homeHtml = await renderTemplate("index", {
          title: "Accueil - Mon Serveur Web",
          ...commonData,
        });
        res.end(homeHtml);
        break;

      case "/about":
        const aboutHtml = await renderTemplate("about", {
          title: "À propos",
          ...commonData,
        });
        res.end(aboutHtml);
        break;

      case "/services":
        const servicesHtml = await renderTemplate("services", {
          title: "Services",
          ...commonData,
        });
        res.end(servicesHtml);
        break;

      case "/contact":
        const contactHtml = await renderTemplate("contact", {
          title: "Contact",
          ...commonData,
        });
        res.end(contactHtml);
        break;

      default:
        // Page 404 pour les routes non trouvées
        res.writeHead(404, { "Content-Type": "text/html; charset=utf-8" });
        const errorHtml = await renderTemplate("404", {
          title: "Erreur 404",
          pathname: pathname,
          ...commonData,
        });
        res.end(errorHtml);
        break;
    }
  } catch (error) {
    console.error("❌ Erreur serveur:", error.message);
    console.error(error.stack);
    try {
      res.writeHead(500, { "Content-Type": "text/plain; charset=utf-8" });
      res.end("Erreur interne du serveur: " + error.message);
    } catch (e) {
      console.error("Impossible d'envoyer la réponse d'erreur");
    }
  }
});

// Démarrage du serveur
server.listen(PORT, HOST, () => {
  console.log(`✅ Serveur démarré sur http://${HOST}:${PORT}`);
  console.log("📄 Pages disponibles :");
  console.log(`   - http://${HOST}:${PORT}/`);
  console.log(`   - http://${HOST}:${PORT}/about`);
  console.log(`   - http://${HOST}:${PORT}/services`);
  console.log(`   - http://${HOST}:${PORT}/contact`);
  console.log("\n🛑 Pour arrêter le serveur, appuyez sur Ctrl+C");
});

server.on('error', (err) => {
  console.error("❌ Erreur du serveur:", err);
});
