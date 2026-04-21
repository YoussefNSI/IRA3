package entitees;

import project01.AdressePostale;
/**
 * Surcharger le constructeur de la classe Mère quand il y a des paramètres
 * et qu'il n'y a pas de constructeur par défaut : Personne()
 * 
 * Quand on implémente une interface on doit surcharger toutes les méthodes
 * non valorisées de l'interface (depuis la 1.8 -> 1.9 on peut donner des
 * comportement par défaut dans une méthode d'une interface Java
 * 
 * @author chris
 *
 */
public class Facteur extends Personne implements mesinterfaces.iFacteur 
{

	public Facteur(String nom, String prenom, AdressePostale adr) {
		super(nom, prenom, adr);
		// TODO Auto-generated constructor stub
	}

	

}
