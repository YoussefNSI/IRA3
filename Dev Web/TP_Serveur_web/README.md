# Serveur Web Node.js - TP

Serveur HTTP créé avec Node.js natif (sans framework).

## Fonctionnalités

- ✅ Serveur HTTP avec module natif `http`
- ✅ Parsing d'URL avec le module `url`
- ✅ 4 pages disponibles (Accueil, À propos, Services, Contact)
- ✅ Gestion de la page 404
- ✅ Content-Type HTML correctement configuré
- ✅ Design responsive avec CSS intégré
- ✅ Navigation entre les pages

## Prérequis

- Node.js (version 12 ou supérieure)

## Installation

1. **Installer Node.js** (si pas encore fait) :

   ```powershell
   # Exécuter PowerShell en tant qu'administrateur
   winget install --id OpenJS.NodeJS.LTS
   ```

2. **Installer les dépendances** :

   ```bash
   npm install
   ```

3. **Installer nodemon pour le développement** :
   ```bash
   npm install --save-dev nodemon
   ```

## Utilisation

### Démarrer le serveur (mode normal)

```bash
npm start
```

### Démarrer le serveur (mode développement avec nodemon)

```bash
npm run dev
```

Le serveur sera accessible sur **http://localhost:3000**

## Pages disponibles

- 🏠 **Accueil** : http://localhost:3000/
- 📝 **À propos** : http://localhost:3000/about
- 💼 **Services** : http://localhost:3000/services
- 📧 **Contact** : http://localhost:3000/contact
- ❌ **Page 404** : Toute autre URL affichera une page d'erreur

## Structure du projet

```
TP_Serveur_web/
├── server.js          # Fichier principal du serveur
├── package.json       # Configuration du projet
└── README.md         # Ce fichier
```

## Détails techniques

### Module HTTP

Le serveur utilise le module natif `http` de Node.js pour créer un serveur web sans dépendances externes.

### Module URL

Le module `url` est utilisé pour parser les URLs des requêtes :

```javascript
const parsedUrl = url.parse(req.url, true);
const pathname = parsedUrl.pathname;
```

### Content-Type

Chaque réponse inclut le Content-Type approprié dans le header :

```javascript
res.writeHead(200, { "Content-Type": "text/html; charset=utf-8" });
```

### Gestion des routes

Le serveur utilise un `switch` statement pour gérer les différentes routes et retourner le contenu HTML approprié.

## Arrêter le serveur

Appuyez sur `Ctrl+C` dans le terminal pour arrêter le serveur.
