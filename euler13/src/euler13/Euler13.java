package euler13;

import java.util.Scanner;

public class Euler13 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Suveskite arba ikopijuokite skaicius, kuriuos reikia susumuoti:");

		String[] pradinisMasyvas = new String [100];
		int n = 0;
		String line;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.isEmpty()) {
				break;
			}
			pradinisMasyvas[n] = line;
			n++;
		}

		String rezultatineEilute = "";
		int atminty = 0;

		for (int k = 50; k > 0; k--) {

			int akumuliacija = 0;

			for (int i = 0; i < 100; i++) {

				String ilgasSkaicius = pradinisMasyvas[i];
				int einamasisSkaitmuo = Character.getNumericValue(ilgasSkaicius.charAt(k-1)) ; /* paima reikalinga simboli */
				akumuliacija = akumuliacija + einamasisSkaitmuo;
			}

			int galutinisSkaitmuo = (akumuliacija + atminty) % 10;
			rezultatineEilute = String.valueOf(galutinisSkaitmuo) + rezultatineEilute;
			atminty = (akumuliacija + atminty) / 10;
		}
		
		rezultatineEilute = atminty + rezultatineEilute;
		System.out.println("Skaiciu sumos rezultatas:");
		System.out.println(rezultatineEilute);
		System.out.println("Pirmi 10 rezultato skaitmenu");
		String atsakymas = rezultatineEilute.substring(0, 10);
		System.out.println(atsakymas);
		
		scanner.close();
	}
}
