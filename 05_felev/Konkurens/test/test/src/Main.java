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
    }
}

