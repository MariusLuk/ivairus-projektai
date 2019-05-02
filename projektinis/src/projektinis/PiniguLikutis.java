package projektinis;

public class PiniguLikutis {
	public String valiuta = "EUR";
	private static Double kiekis = 100.0;

	public static void keiskPiniguLikuti(double piniguPokytis) {
		kiekis += piniguPokytis;
//		spausdinkPiniguLikuti();
	}

	public static Double getKiekis() {
		return kiekis;
	}

	public static void setKiekis(Double kiekis) {
		PiniguLikutis.kiekis = kiekis;
	}

	public static void spausdinkPiniguLikuti() {
		System.out.println("Pinigu likutis parduotuveje siuo metu: " + PiniguLikutis.getKiekis() + " EUR");
		System.out.println();
	}
}
