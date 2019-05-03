package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Mokinys> mokiniai = new ArrayList<>();

        Mokinys Tomas = new Mokinys("Tomas", 7, 6, 4);
        mokiniai.add(Tomas);
        Mokinys Romas = new Mokinys("Romas", 9, 5, 8);
        mokiniai.add(Romas);
        Mokinys Rimas = new Mokinys("Rimas", 6, 9, 8);
        mokiniai.add(Rimas);
        Mokinys Simas = new Mokinys("Simas", 10, 8, 5);
        mokiniai.add(Simas);
        Mokinys Tadas = new Mokinys("Tadas", 10, 9, 8);
        mokiniai.add(Tadas);

        System.out.format("%8s", " ");
        System.out.format("%14s", "Chemija");
        System.out.format("%14s", "Matematika");
        System.out.format("%14s", "Fizika");
        System.out.println();
        System.out.println("__________________________________________________");
        for (Mokinys m : mokiniai) {
            System.out.format("%8s", m.mokinioVardas);
            System.out.format("%14d", m.chemijosIvertis);
            System.out.format("%14d", m.matematikosIvertis);
            System.out.format("%14d", m.fizikosIvertis);
            System.out.println();
        }
        System.out.println("__________________________________________________");
        System.out.println();

        System.out.println("Ar norite itraukti dar ka nors i mokiniu sarasa?");
        String vartotojoPasirinkimas = scanner.nextLine();

        if (vartotojoPasirinkimas.toLowerCase().contains("taip")) {
            System.out.println("Iveskite mokinio varda:");
            String naujoMokinioVardas = scanner.nextLine();
            System.out.println("Iveskite chemijos pazymi:");
            int naujoMokinioChemijosIvertis = scanner.nextInt();
            System.out.println("Iveskite matematikos pazymi:");
            int naujoMokinioMatematikosIvertis = scanner.nextInt();
            System.out.println("Iveskite fizikos pazymi:");
            int naujoMokinioFizikosIvertis = scanner.nextInt();
            Mokinys naujasMokinys = new Mokinys(naujoMokinioVardas, naujoMokinioChemijosIvertis, naujoMokinioMatematikosIvertis, naujoMokinioFizikosIvertis);
            mokiniai.add(naujasMokinys);

            System.out.format("%8s", " ");
            System.out.format("%14s", "Chemija");
            System.out.format("%14s", "Matematika");
            System.out.format("%14s", "Fizika");
            System.out.println();
            System.out.println("__________________________________________________");
            for (Mokinys m : mokiniai) {
                System.out.format("%8s", m.mokinioVardas);
                System.out.format("%14d", m.chemijosIvertis);
                System.out.format("%14d", m.matematikosIvertis);
                System.out.format("%14d", m.fizikosIvertis);
                System.out.println();
            }
            System.out.println("__________________________________________________");
            System.out.println();

        } else if (vartotojoPasirinkimas.toLowerCase().contains("ne")) {
            System.out.println();
        } else {
            System.out.println("Nekorektiska ivestis!");
        }
        System.out.println();

        for (Mokinys m : mokiniai) {
            m.visuMokinioPazymiuVidurkis = m.grazinkMokinioPazymiuVidurki();
        }

        List chemijosLyderiuListas = new ArrayList();
        List matematikosLyderiuListas = new ArrayList();
        List fizikosLyderiuListas = new ArrayList();
        List bendrasLyderiuListas = new ArrayList();

        int chemijosMaxIvertis = 0;
        int matematikosMaxIvertis = 0;
        int fizikosMaxIvertis = 0;
        double bendrasMaxVidurkis = 0;

        for (Mokinys mokinys : mokiniai) {
            if (mokinys.chemijosIvertis > chemijosMaxIvertis) {
                chemijosLyderiuListas.clear();
                chemijosLyderiuListas.add(mokinys.mokinioVardas);
                chemijosMaxIvertis = mokinys.chemijosIvertis;
            } else if (mokinys.chemijosIvertis == chemijosMaxIvertis) {
                chemijosLyderiuListas.add(mokinys.mokinioVardas);
            }

            if (mokinys.matematikosIvertis > matematikosMaxIvertis) {
                matematikosLyderiuListas.clear();
                matematikosLyderiuListas.add(mokinys.mokinioVardas);
                matematikosMaxIvertis = mokinys.matematikosIvertis;
            } else if (mokinys.matematikosIvertis == matematikosMaxIvertis) {
                matematikosLyderiuListas.add(mokinys.mokinioVardas);
            }

            if (mokinys.fizikosIvertis > fizikosMaxIvertis) {
                fizikosLyderiuListas.clear();
                fizikosLyderiuListas.add(mokinys.mokinioVardas);
                fizikosMaxIvertis = mokinys.fizikosIvertis;
            } else if (mokinys.fizikosIvertis == fizikosMaxIvertis) {
                fizikosLyderiuListas.add(mokinys.mokinioVardas);
            }

            if (mokinys.visuMokinioPazymiuVidurkis > bendrasMaxVidurkis) {
                bendrasLyderiuListas.clear();
                bendrasLyderiuListas.add(mokinys.mokinioVardas);
                bendrasMaxVidurkis = mokinys.visuMokinioPazymiuVidurkis;
            } else if (mokinys.visuMokinioPazymiuVidurkis == bendrasMaxVidurkis) {
                bendrasLyderiuListas.add(mokinys.mokinioVardas);
            }
        }
        System.out.print("Auksciausia chemijos pazymi (" + chemijosMaxIvertis + ") turi: ");
        System.out.println(chemijosLyderiuListas);
        System.out.print("Auksciausia matematikos pazymi (" + matematikosMaxIvertis + ") turi: ");
        System.out.println(matematikosLyderiuListas);
        System.out.print("Auksciausia fizikos pazymi (" + fizikosMaxIvertis + ") turi: ");
        System.out.println(fizikosLyderiuListas);
        System.out.print("Auksciausia bendra vidurki (" + bendrasMaxVidurkis + ") turi: ");
        System.out.println(bendrasLyderiuListas);
    }
}
