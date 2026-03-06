import java.util.ArrayList;

public class Catalogue {
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
