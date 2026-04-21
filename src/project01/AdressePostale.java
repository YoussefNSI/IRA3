package project01;

public class AdressePostale {
	private int numero;
	private String rue;
	private String codepostal;
	private String ville;
	public AdressePostale(int numero, String rue, String codepostal, String ville) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codepostal = codepostal;
		this.ville = ville;
	}
	@Override
	public String toString() {
		return "AdressePostale [numero=" + numero + ", rue=" + rue + ", codepostal=" + codepostal + ", ville=" + ville
				+ "]";
	}
	
	
	

}
