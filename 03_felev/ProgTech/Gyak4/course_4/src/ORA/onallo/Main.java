package ORA.onallo;

import java.io.FileNotFoundException;

public class Main {
    public void main(String[] args) {
        Database database = new Database();
        try {
            database.read("data.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("Wrong filename!");
            System.exit(-1);
        }
        catch (AnimalControl e) {
            System.out.println("Wrong something!");
            System.exit(-1);
        }

    }
}
