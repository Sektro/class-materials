package printed.material.specific;

import printed.material.Book;
import printed.material.CoverType;

public class printedBook extends Book {
    protected CoverType coverType;


    public PrintedBook(Book book)  throws InvalidBookException {
        this(book.getAuthor(), book.getTitle(), book.getPageCount(), CoverType.HARDCOVER);
    }
    public PrintedBook(String author, String title, int pageCount, CoverType coverType) throws InvalidBookException {
        super(author, title, pageCount + 2);
        this.coverType = coverType;
    }

    @Override
    public int getPrice() {
        int multiplier = coverType == CoverType.SOFTCOVER ? 2 : 3;
        return super.getPrice() * multiplier;
    }

    @Override
    public String toString() {
        return "%s (%s)".formatted(super.toString(), coverType);
    }

    // John Steinbeck: Of Mice and Men; 113 (HARDCOVER)
    public static PrintedBook decode(String text) throws InvalidBookException {
        String[] split1 = text.split("[(]");
        String[] split2 = split1[1].split("[)]");

        Book asBook = Book.decode(split1[0]);

        CoverType coverType = CoverType.valueOf(split2[0]);
        return new PrintedBook(asBook.getAuthor(), asBook.getTitle(), asBook.getPageCount()-2, coverType);
    }
}