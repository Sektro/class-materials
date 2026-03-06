
import java.util.ArrayList;

// Main class
public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Antal", "Balkán", "Contained", 2004);
        Book book2 = new Book("Béla", "Cristyne Margrouve", "Destroyed", 2001);
        Book book3 = new Book("Cecília", "Dragon Hunting", "Abolished", 2002);
        Book book4 = new Book("Döme", "Almafa", "Bent", 2003);

        Catalogue catalogue = new Catalogue();
        catalogue.addBook(book1);
        catalogue.addBook(book2);
        catalogue.addBook(book3);
        catalogue.addBook(book4);

        catalogue.printCatalogue();

        catalogue.sortCatalogue(3);

        catalogue.printCatalogue();

        catalogue.removeBook(book2);

        catalogue.printCatalogue();
    }
}

// Book class
class Book {

    private String author = "";
    private String title = "";
    private String publisher = "";
    private int year_of_publication = 0;

    // Getters
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getPublisher() {
        return publisher;
    }
    public int getYear_of_publication() {
        return year_of_publication;
    }

    // Constructor setting the 4 main variables to the specified values
    public Book(String author, String title, String publisher, int year_of_publication) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year_of_publication = year_of_publication;
    }

    // Used to print out the book's informations to the terminal
    public void printBook() {
        System.out.println(author + " | " + title + " | " + publisher + " | " + year_of_publication);
    }
}

// Catalogue class

class Catalogue {
    private ArrayList<Book> catalogue = new ArrayList<Book>();

    public Catalogue() {}

    // Adds a book to the catalogue
    public void addBook(Book newBook) {
        catalogue.add(newBook);
    }
    // Removes a book from the catalogue
    public void removeBook(Book removedBook) {
        catalogue.remove(removedBook);
    }

    /* Sorts the catalogue by the set parameter:
        0: sort by author
        1: sort by title
        2: sort by publisher
        3: sort by year of publication
     */
    public void sortCatalogue(int sortType) {
        switch (sortType) {
            case 0 -> {
                catalogue.sort((a, b) -> { return  a.getAuthor().compareTo(b.getAuthor()); });
                break;
            }
            case 1 -> {
                catalogue.sort((a, b) -> { return  a.getTitle().compareTo(b.getTitle()); });
                break;
            }
            case 2 -> {
                catalogue.sort((a, b) -> { return  a.getPublisher().compareTo(b.getPublisher()); });
                break;
            }
            case 3 -> {
                catalogue.sort((a, b) -> { return a.getYear_of_publication() - b.getYear_of_publication(); });
                break;
            }
        }
    }

    // Prints out the entire catalogue to the terminal
    public void printCatalogue() {
        System.out.println("||=========CATALOGUE========||");
        for (Book b : catalogue) {
            b.printBook();
        }
        System.out.println("||==========================||");
    }
}



