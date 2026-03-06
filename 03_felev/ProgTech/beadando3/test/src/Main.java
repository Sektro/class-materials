import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Coordinate> testing = new ArrayList<>();
        System.out.println(testing.size());
        testing.add(new Coordinate(2,1));
        System.out.println(testing.size());
        testing.remove(new Coordinate(2,1));
        System.out.println(testing.size());
        /*
        ArrayList<String> testing = new ArrayList<>();
        System.out.println(testing.size());
        testing.add("2");
        System.out.println(testing.size());
        testing.remove(new String("2"));
        System.out.println(testing.size());
        asd
         */
    }
}