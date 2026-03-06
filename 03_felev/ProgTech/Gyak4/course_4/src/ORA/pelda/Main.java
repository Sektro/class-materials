package ORA.pelda;

import java.io.FileNotFoundException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Database database = new Database();
        try {
            database.read("data.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        database.report();
    }
}