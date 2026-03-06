public class Book {

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

