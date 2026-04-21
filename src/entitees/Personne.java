package entitees;

import project01.AdressePostale;

public class Personne {
	public String nom;
	String prenom;
	AdressePostale adr;
	public Personne(String nom, String prenom, AdressePostale adr) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adr = adr;
	}
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", adr=" + adr + "]";
	}
	
	
}
