package projektinis;

public class Nuskaitymai {

    public static int nuskaitymas(int maxIvestis) {
        boolean ivestisTinkama = false;
        int pasirinkimas = 0;

        do {
            if (ProjektinisMain.scanner.hasNextInt()) {
                pasirinkimas = ProjektinisMain.scanner.nextInt();
                if (pasirinkimas >= 0 && pasirinkimas <= maxIvestis) {
                    ivestisTinkama = true;
                } else {
                    System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
                }
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }

            ProjektinisMain.scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static int teigIntSkaiciausNuskaitymas() {
        boolean ivestisTinkama = false;
        int pasirinkimas = 0;

        do {
            if (ProjektinisMain.scanner.hasNextInt()) {
                pasirinkimas = ProjektinisMain.scanner.nextInt();
                if (pasirinkimas > 0) {
                    ivestisTinkama = true;
                } else {
                    System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
                }
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            ProjektinisMain.scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static double teigDoubleSkaiciausNuskaitymas() {
        boolean ivestisTinkama = false;
        double pasirinkimas = 0;

        do {
            if (ProjektinisMain.scanner.hasNextDouble()) {
                pasirinkimas = ProjektinisMain.scanner.nextDouble();
                if (pasirinkimas > 0) {
                    ivestisTinkama = true;
                } else {
                    System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
                }
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            ProjektinisMain.scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static double doubleSkaiciausNuskaitymas() {
        boolean ivestisTinkama = false;
        double pasirinkimas = 0;

        do {
            if (ProjektinisMain.scanner.hasNextDouble()) {
                pasirinkimas = ProjektinisMain.scanner.nextDouble();
                ivestisTinkama = true;
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            ProjektinisMain.scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static int kiekioIntNuskaitymas() {
        boolean ivestisTinkama = false;
        int pasirinkimas = 0;

        do {
            if (ProjektinisMain.scanner.hasNextInt()) {
                pasirinkimas = ProjektinisMain.scanner.nextInt();
                ivestisTinkama = true;
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            ProjektinisMain.scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }
}
