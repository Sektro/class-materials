package course_1.egyetem;

import java.util.ArrayList;

public class HallgatoMain
{

    private static ArrayList<Hallgato> hallgatok;

    public static void main(String[] args) {
        hallgatok = new ArrayList<Hallgato>();

        Hallgato h1 = new Hallgato("Janos", "magyar", 5);
        hallgatok.add(h1);
        Hallgato h2 = new Hallgato("Mohammed", "torok", 3);
        hallgatok.add(h2);
        Hallgato h3 = new Hallgato("Cintia", "amerikai", 1);
        hallgatok.add(h3);

        System.out.println("Legjobb atlagu diak: " + legjobbAtlag());
        System.out.println("Legrosszabb atlagu diak: " + legrosszabbAtlag());
        osztondij();
    }

    //feladat1
    public static String legjobbAtlag() {
        String legjobb = "";
        float currentAtlag = 0.0f;
        for (Hallgato h : hallgatok) {
            if (h.getAtlag() > currentAtlag) {legjobb = h.getNev(); currentAtlag = h.getAtlag();}
        }
        return legjobb;
    }
    public static String legrosszabbAtlag() {
        String legrosszabb = "";
        float currentAtlag = 6.0f;
        for (Hallgato h : hallgatok) {
            if (h.getAtlag() < currentAtlag) {legrosszabb = h.getNev(); currentAtlag = h.getAtlag();}
        }
        return legrosszabb;
    }

    //feladat2
    public static void osztondij() {
        System.out.println("Osztondijat kapo diakok: ");
        for (Hallgato h : hallgatok) {
            if (h.getAtlag() >= 4) {System.out.println(h.getNev());}
        }
    }
}
