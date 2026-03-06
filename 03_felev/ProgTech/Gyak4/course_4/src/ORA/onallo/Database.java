package ORA.onallo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private final List<Animal> animals;

    public Database() {
        animals = new ArrayList<Animal>();
    }

    public void read(String filename) throws FileNotFoundException, AnimalControl {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int n = sc.nextInt();
        while (sc.hasNext())
        {
            String type = sc.next();
            String name = sc.next();
            int weight = sc.nextInt();
            Animal a;
            switch (type)
            {
                case "E":
                    a = new Emu(name, weight);
                    break;
                case "K":
                    a = new Goat(name, weight);
                    break;
                case "L":
                    a = new Horse(name, weight);
                    break;
                case "T":
                    a = new Cow(name, weight);
                    break;
                default:
                    throw new AnimalControl();
            }
            int meals = sc.nextInt();
            for (int i = 0; i < meals; ++i) {
                a.addMeal(sc.nextInt());
            }
            animals.add(a);
        }
    }

    public void report() {
        System.out.println("----- Report on the animals in the Database: -----");
        for (Animal a : animals) {

        }
    }

    public List<Animal> ateALot() {
        List<Animal> result = new ArrayList<Animal>();
        for (Animal a : animals) {
            if (a.moreThanKilo()) {result.add(a);}
        }
        return result;
    }
}
