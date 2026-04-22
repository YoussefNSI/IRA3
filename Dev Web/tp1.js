function DAB(somme) {
    if (somme < 0 ) {
        console.error("Erreur : La somme doit être supérieure à zéro");
        return;
    }


    let resteEnCentimes = Math.round(somme * 100);

    const coupures = [
        {valeur: 50000, nom: "500€", type: "billet"},
        {valeur: 20000, nom: "200€", type: "billet"},
        {valeur: 10000, nom: "100€", type: "billet"},
        {valeur: 5000, nom: "50€", type: "billet"},
        {valeur: 2000, nom: "20€", type: "billet"},
        {valeur: 1000, nom: "10€", type: "billet"},
        {valeur: 500, nom: "5€", type: "billet"},
        {valeur: 200, nom: "2€", type: "pièce"},
        {valeur: 100, nom: "1€", type: "pièce"},
        {valeur: 50, nom: "0.50€", type: "pièce"},
        {valeur: 20, nom: "0.20€", type: "pièce"},
        {valeur: 10, nom: "0.10€", type: "pièce"},
        {valeur: 5, nom: "0.05€", type: "pièce"},
        {valeur: 2, nom: "0.02€", type: "pièce"},
        {valeur: 1, nom: "0.01€", type: "pièce"}
    ];

    console.log(`\n${somme}€ représente :`);

    for (const coupure of coupures) {
        const nombre = Math.floor(resteEnCentimes / coupure.valeur);
        if (nombre > 0) {
            console.log(`${nombre} ${coupure.type}(s) de ${coupure.nom}`);
            resteEnCentimes %= coupure.valeur;
        }
    }
}

DAB(617.17);
DAB(93.27);
DAB(-12.78);