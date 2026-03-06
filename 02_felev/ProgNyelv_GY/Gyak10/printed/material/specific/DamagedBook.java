package printed.material.specific;

import printed.material.Book;

public class DamagedBook extends Book {
    public DamagedBook() {
        super();//meghívja Book üres konstruktorát
    }
    public DamagedBook(String author, String title, int pageCount) {
        super(author, title, pageCount);//meghívja Book másik konstruktorát
    }
}