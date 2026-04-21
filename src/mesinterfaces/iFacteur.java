package mesinterfaces;

public interface iFacteur {
	
	default public void distribuer() {
		System.out.println("Je distribue !");
	}
	
}
