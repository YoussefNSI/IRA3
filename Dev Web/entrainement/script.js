const express = require('express');
const app = express();

const users = [
    { id: 1, nom: "Dupont" },
    { id: 2, nom: "Martin" },
    { id: 42, nom: "Smith" }
];

app.get('/api/users', (req, res) => {
    const nomRecherche = req.query.nom;
    if (nomRecherche) {
        const utilisateur = users.find(user => user.nom.toLowerCase() === nomRecherche.toLowerCase());
        if (utilisateur) {
            res.json(utilisateur);
        } else {
            res.status(404).json({ message: 'Utilisateur non trouvé' });
        }
    } else {
        res.json(users);
    }
});

app.get('/api/users/:id', (req, res) => {
    const idRecherche = parseInt(req.params.id, 10);
    const utilisateur = users.find(user => user.id === idRecherche);
    if (utilisateur) {
        res.json(utilisateur);
    } else {
        res.status(404).json({ message: 'Utilisateur non trouvé' });
    }
});

app.get('/', (req, res) => {
    res.send('Bienvenue sur Express!');
});

app.get('/api/user', (req, res) => {
    res.json({ id: 1, nom: 'Dupont' });
});

app.listen(3000, () => {
    console.log('Le serveur écoute sur le port 3000');
});