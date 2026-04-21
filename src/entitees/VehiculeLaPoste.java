package entitees;

import mesinterfaces.iFacteur;

public class VehiculeLaPoste extends Vehicule implements iFacteur {

	@Override
	public void distribuer() {
		// TODO Auto-generated method stub
		System.out.println("La voiture distribue !");
	}

}
