package projektinis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Projektinis {

	static Krepselis krepselis = new Krepselis();

	static Scanner scanner = new Scanner(System.in);

	public static int nuskaitymas(int maxIvestis) {
		boolean ivestisTinkama = false;
		int pasirinkimas = 0;

		do {
			if (scanner.hasNextInt()) {
				pasirinkimas = scanner.nextInt();
				if (pasirinkimas >= 0 && pasirinkimas <= maxIvestis) {
					ivestisTinkama = true;
				} else {
					System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
				}
			} else {
				System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
			}

			scanner.nextLine();
		} while (!ivestisTinkama);
		System.out.println();
		return pasirinkimas;
	}

	public static int teigSkaiciausNuskaitymas() {
		boolean ivestisTinkama = false;
		int pasirinkimas = 0;

		do {
			if (scanner.hasNextInt()) {
				pasirinkimas = scanner.nextInt();
				if (pasirinkimas > 0) {
					ivestisTinkama = true;
				} else {
					System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
				}
			} else {
				System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
			}
			scanner.nextLine();
		} while (!ivestisTinkama);
		System.out.println();
		return pasirinkimas;
	}

	public static double teigRealSkaiciausNuskaitymas() {
		boolean ivestisTinkama = false;
		double pasirinkimas = 0;

		do {
			if (scanner.hasNextDouble()) {
				pasirinkimas = scanner.nextDouble();
				if (pasirinkimas > 0) {
					ivestisTinkama = true;
				} else {
					System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
				}
			} else {
				System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
			}
			scanner.nextLine();
		} while (!ivestisTinkama);
		System.out.println();
		return pasirinkimas;
	}

	public static double realSkaiciausNuskaitymas() {
		boolean ivestisTinkama = false;
		double pasirinkimas = 0;

		do {
			if (scanner.hasNextDouble()) {
				pasirinkimas = scanner.nextDouble();
				ivestisTinkama = true;
			} else {
				System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
			}
			scanner.nextLine();
		} while (!ivestisTinkama);
		System.out.println();
		return pasirinkimas;
	}

	public static int kiekioNuskaitymas() {
		boolean ivestisTinkama = false;
		int pasirinkimas = 0;

		do {
			if (scanner.hasNextInt()) {
				pasirinkimas = scanner.nextInt();
				ivestisTinkama = true;
			} else {
				System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
			}
			scanner.nextLine();
		} while (!ivestisTinkama);
		System.out.println();
		return pasirinkimas;
	}

	public static void pradzia() {

		System.out.println("Sveiki atvyke i e-parduotuve!");
		System.out.println();
		System.out.println("Jei esate pirkejas - spauskite 1");
		System.out.println("Jei esate administratorius - spauskite 2");

		switch (nuskaitymas(2)) {
		case 0:
			pradzia();
			break;
		case 1:
			pirkejoMeniu1();
			break;
		case 2:
			adminMeniu2();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			pradzia();
		}
	}

	public static void pirkejoMeniu1() {
		System.out.println("Naviguojate PIRKEJO MENIU. Pasirinkite norima veiksma:");
		System.out.println();
		System.out.println("0 - Grizti i pradzia");
		System.out.println("1 - Rinktis prekes is e-parduotuves");
		System.out.println("2 - Perziureti krepseli");

		switch (nuskaitymas(2)) {
		case 0:
			pradzia();
			break;
		case 1:
			pirkejoMeniu11();
			break;
		case 2:
			pirkejoMeniu12();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			pirkejoMeniu1();
		}

	}

	public static void pirkejoMeniu11() {
		Sandelis.sandelioSpausdinimasPirkejui();
		System.out.println("0 - Grizti atgal");
		System.out.println("1 - Perziureti krepseli");
		System.out.println("2 - Pasirinkti preke pagal ID");

		switch (nuskaitymas(2)) {
		case 0:
			pirkejoMeniu1();
			break;
		case 1:
			pirkejoMeniu12();
			break;
		case 2:
			pirkejoMeniu111();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			pirkejoMeniu11();
		}
	}

	public static void pirkejoMeniu111() {
		Sandelis.sandelioSpausdinimasPirkejui();
		System.out.println("Iveskite prekes ID");

		int prekesID = teigSkaiciausNuskaitymas();

		boolean arYraKrepselyje = false;
		for (Preke k : Krepselis.prekes) {
			if (k.ID == prekesID) {
				arYraKrepselyje = true;
			}
		}
		if (arYraKrepselyje) {
			System.out.println("Preke su tokiu ID jau yra Jusu krepselyje! Galite tiesiog keisti jos kieki.");
			pirkejoMeniu12211(prekesID);
		} else {
			boolean arYraSandely = false;
			for (Preke p : Sandelis.prekes) {
				if (p.ID == prekesID) {
					arYraSandely = true;
				}
			}
			if (arYraSandely) {
				pirkejoMeniu1111(prekesID);
			} else {
				System.out.println("Toks prekes ID nerastas!");
				pirkejoMeniu11();
			}
		}
	}

	public static void pirkejoMeniu1111(int prekesID) {
		Sandelis.sandelioPrekesSpausdinimasPirkejui(prekesID);
		System.out.println("Iveskite norima prekes kieki");
		int pirkejoNorimasKiekis = Projektinis.kiekioNuskaitymas();
		for (Preke prekeIsSandelio : Sandelis.prekes) {
			if (prekeIsSandelio.ID == prekesID) {
				if ((prekeIsSandelio.kiekis /*- krepselio prekiu masyve jau esancios tos pacios prekes kiekis(jei toks jau yra)*/) >= pirkejoNorimasKiekis) {
					Krepselis.PrekeToKrepselis(prekeIsSandelio, pirkejoNorimasKiekis);
				} else {
					System.out.println("Nepakankamas prekes likutis parduotuveje!");
					Projektinis.pirkejoMeniu11();
				}
			}
		}
		pirkejoMeniu12();
	}

	public static void pirkejoMeniu12() {
		Krepselis.krepselioSpausdinimasPirkejui();
		System.out.println("0 - Grizti atgal");
		System.out.println("1 - Patvirtinti uzsakyma ir apmoketi");
		System.out.println("2 - Modifikuoti krepselio turini");

		switch (nuskaitymas(2)) {
		case 0:
			pradzia();
			break;
		case 1:
			pirkejoMeniu121();
			break;
		case 2:
			pirkejoMeniu122();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			pirkejoMeniu12();
		}
	}

	public static void pirkejoMeniu121() {
		if (Krepselis.krepselioKaina <= 0) {
			System.out.println("Uzsakymas NEGALIMAS, nes Jusu krepselis kolkas TUSCIAS!");
			pirkejoMeniu122();
		} else {
			Krepselis.krepselioSpausdinimasPirkejui();
			System.out.println("0 - Grizti atgal");
			System.out.println("1 - Ivesti pirkejo duomenis, patvirtinti ir apmoketi uzsakyma");

			switch (nuskaitymas(1)) {
			case 0:
				pirkejoMeniu12();
				break;
			case 1:
				nuskaitykPirkejoRekvizitus();
				break;
			default:
				System.out.println("Nekorektiska ivestis!");
				pirkejoMeniu121();
			}
		}

	}

	public static void nuskaitykPirkejoRekvizitus() {
		System.out.println("Iveskite savo ID");
		int pirkejoID = teigSkaiciausNuskaitymas();
		System.out.println("Iveskite savo varda ir pavarde arba imones pavadinima:");
		String pirkejoPavadinimas = scanner.nextLine();
		System.out.println("Iveskite savo e-mail adresa:");
		String pirkejoEmail = scanner.nextLine();
		System.out.println("Iveskite pristatymo adresa:");
		String pirkejoAdresas = scanner.nextLine();

		// TODO: jei viskas ok, tai toliau:

		Sandelis.krepselioPrekiuIsemimasIsSandelio();

		uzsakymasToAtaskaita(pirkejoID, pirkejoPavadinimas, pirkejoEmail, pirkejoAdresas, Krepselis.krepselioKaina);

		PiniguLikutis.keiskPiniguLikuti(Krepselis.krepselioKaina);

		Krepselis.istustinkKrepseli();

		System.out.println("Uzsakymas patvirtintas ir artimiausiu metu bus issiustas Jums!");
		System.out.println("Dekojame, kad pirkote!");
		System.out.println();

		pirkejoMeniu1();
	}

	public static void uzsakymasToAtaskaita(int pirkejoID, String pirkejoPavadinimas, String pirkejoEmail,
			String pirkejoAdresas, double krepselioKaina) {
		String url = "jdbc:sqlite:C:\\git\\source\\projects-to-git\\projektinis\\uzsakymuAtaskaita.db";
		String sql = "INSERT INTO uzsakymai(pirkejoID,pirkejoPavadinimas,pirkejoEmail,pirkejoAdresas,krepselioKaina) VALUES(?,?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, pirkejoID);
			pstmt.setString(2, pirkejoPavadinimas);
			pstmt.setString(3, pirkejoEmail);
			pstmt.setString(4, pirkejoAdresas);
			pstmt.setDouble(5, krepselioKaina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void pirkejoMeniu122() {
		Krepselis.krepselioSpausdinimasPirkejui();
		System.out.println("0 - Grizti atgal");
		System.out.println("1 - Prideti nauja preke i krepseli");
		System.out.println("2 - Rinktis preke, kuria norima keisti");

		switch (nuskaitymas(2)) {
		case 0:
			pirkejoMeniu12();
			break;
		case 1:
			pirkejoMeniu11();
			break;
		case 2:
			pirkejoMeniu1221();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			pirkejoMeniu122();
		}
	}

	public static void pirkejoMeniu1221() {
		Krepselis.krepselioSpausdinimasPirkejui();
		System.out.println("Iveskite ID prekes, kuria norite keisti");
		int prekesID = teigSkaiciausNuskaitymas();

		boolean arYraKrepselyje = false;
		for (Preke p : Krepselis.prekes) {
			if (p.ID == prekesID) {
				arYraKrepselyje = true;
			}
		}
		if (arYraKrepselyje) {
			pirkejoMeniu12211(prekesID);
		} else {
			System.out.println("Prekes su tokiu ID Jusu krepselyje nera!");
			pirkejoMeniu122();
		}
	}

	public static void pirkejoMeniu12211(int prekesID) {
//		Rodomas prekes kiekis, jau esantis krepselyje:
		Krepselis.krepselioPrekesSpausdinimasPirkejui(prekesID);
//		Rodomas likes aktualios prekes kiekis sandelyje:
		Sandelis.sandelioPrekesSpausdinimasPirkejui(prekesID);

		Preke.keiskPrekesKiekiKrepselyje(prekesID);

		pirkejoMeniu12();
	}

	public static void adminMeniu2() {
//		System.out.println("Iveskite administratoriaus slaptazodi.");
		System.out.println("Naviguojate ADMINISTRATORIAUS MENIU. Pasirinkite norima veiksma:");
		System.out.println();
		System.out.println("0 - Grizti i pradzia");
		System.out.println("1 - Prekiu atsargos sandelyje ir ju keitimas");
		System.out.println("2 - Pinigu likutis ir jo keitimas");
		System.out.println("3 - Uzsakymu isklotines perziura");

		switch (nuskaitymas(3)) {
		case 0:
			pradzia();
			break;
		case 1:
			adminMeniu21();
			break;
		case 2:
			adminMeniu22();
			break;
		case 3:
			adminMeniu23();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			adminMeniu2();
		}
	}

	public static void adminMeniu21() {
		Sandelis.sandelioSpausdinimasAdminui();
		System.out.println("0 - Grizti atgal");
		System.out.println("1 - Keisti sandelyje turimos prekes atsargas");
		System.out.println("2 - Papildyti sandeli nauja preke");

		switch (nuskaitymas(2)) {
		case 0:
			adminMeniu2();
			break;
		case 1:
			adminMeniu211();
			break;
		case 2:
			adminMeniu212();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			adminMeniu21();
		}
	}

	public static void adminMeniu211() {
		Sandelis.sandelioSpausdinimasAdminui();
		System.out.println("Iveskite prekes ID");
		int prekesID = teigSkaiciausNuskaitymas();
		boolean arYraSandely = false;

		for (Preke p : Sandelis.prekes) {
			if (p.ID == prekesID) {
				arYraSandely = true;
			}
		}
		if (arYraSandely) {
			adminMeniu2111(prekesID);
		} else {
			System.out.println("Toks ID nerastas!");
			adminMeniu21();
		}
	}

	public static void adminMeniu2111(int prekesID) {
		Sandelis.sandelioPrekesSpausdinimasAdminui(prekesID);
		PiniguLikutis.spausdinkPiniguLikuti();
		Preke.keiskPrekesKiekiSandely(prekesID);
		adminMeniu21();
	}

	public static void adminMeniu212() {

		PiniguLikutis.spausdinkPiniguLikuti();
		Preke.nuskaitykNaujaPrekeToSandelis();
		adminMeniu21();
	}

	public static void adminMeniu22() {
		PiniguLikutis.spausdinkPiniguLikuti();
		System.out.println("0 - Grizti atgal");
		System.out.println("1 - Didinti/mazinti pinigu likuti");

		switch (nuskaitymas(1)) {
		case 0:
			adminMeniu2();
			break;
		case 1:
			adminMeniu221();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			adminMeniu22();
		}
	}

	public static void adminMeniu221() {
		PiniguLikutis.spausdinkPiniguLikuti();
		System.out.println("Iveskite pinigu pokyti (+/-) EUR");
		double ivestasPokytis = realSkaiciausNuskaitymas();
		if ((PiniguLikutis.getKiekis() + ivestasPokytis) >= 0) {
			PiniguLikutis.keiskPiniguLikuti(ivestasPokytis);
		} else {
			System.out.println("Veiksmas negalimas, nes ivesta suma virsija esama pinigu likuti!");
			adminMeniu221();
		}
		PiniguLikutis.spausdinkPiniguLikuti();
		adminMeniu2();
	}

	public static void adminMeniu23() {
		rodykUzsakymuAtaskaita();
		System.out.println("0 - Grizti atgal");
		switch (nuskaitymas(0)) {
		case 0:
			adminMeniu2();
			break;
		default:
			System.out.println("Nekorektiska ivestis!");
			adminMeniu23();
		}
	}

	public static void rodykUzsakymuAtaskaita() {
		String url = "jdbc:sqlite:C:\\git\\source\\projects-to-git\\projektinis\\uzsakymuAtaskaita.db";
		String sql = "SELECT pirkejoID, pirkejoPavadinimas, pirkejoEmail, pirkejoAdresas, krepselioKaina, uzsakymoDataLaikas FROM uzsakymai";

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			System.out.println(
					"Pirkejo ID --- Pavarde arba pavadinimas --- Pirkejo e-mail ---- Pirkejo adresas --------- Pirkinio suma --- Uzsakymo data/laikas");
			while (rs.next()) {
				System.out.println(rs.getInt("pirkejoID") + "\t       " + rs.getString("pirkejoPavadinimas")
						+ "\t           " + rs.getString("pirkejoEmail") + "\t" + rs.getString("pirkejoAdresas")
						+ "\t   " + rs.getDouble("krepselioKaina") + " EUR " + "\t    "
						+ rs.getString("uzsakymoDataLaikas"));
			}
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {

		pradzia();

	}
}
