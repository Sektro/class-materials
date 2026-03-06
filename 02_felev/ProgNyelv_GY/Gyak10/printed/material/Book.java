package printed.material;

public class Book /*extends szülő (itt extends Object)*/{
    public static final String defaultAuthor = "John Steinbeck";
    public static final String defaultTitle = "Of Mice and Men";
    public static final int defaultPageCount = 107;

    private String author;
    private String title;
    protected int pageCount;

    public Book() {
        initbook(defaultAuthor, defaultTitle, defaultPageCount);
    }

    public Book(String author, String title, int pageCount) {
        checkInitData(author, title, pageCount);
        initbook(author, title, pageCount);
    }

    protected void initbook(String author, String title, int pageCount) {
        this.author = author;
        this.title = title;
        this.pageCount = pageCount;
    }

    public String getAuthor()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }
    public int getPageCount()
    {
        return pageCount;
    }

    private void checkInitData(String author, String title, int pageCount) throws InvalidBookException{
        if (author.length() < 2 || title.length() < 4 || pageCount < 1)
        {
            throw new IllegalBookException();
        }
    }

    protected String getAuthorWithInitials() {
        String[] nameParts = author.Split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(nameParts[0].charAt(0) + ". ");

        for (int i = 1; i < nameParts.length - 1; i++) {
            sb.append(nameParts[i] + " ");
        }

        return sb.toString();
    }

    //author   ;   title   :   pagecount
    public static Book decode(String text) {
        String[] split1 = text.split(":");
        String[] split2 = split1[0].split(";");

        String author = split2[0].strip();
        String title = split2[1].strip();

        int pageCount = Integer.parseInt(split1[1].strip()); //strip leszedi a spaceket

        try {
        return new Book(author, title, pageCount);
        }
        catch (InvalidBookException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "%s; %s: %d".formatted(getAuthor(), getTitle(), getPageCount());
    }

    public String getShortInfo() {
        return "%s; %s: %d".formatted(getAuthorWithInitials(), getTitle().substring(0, 4), getPageCount());
    }

    public String createReference(String article, int from, int to) {
        return "%s [%d-%d] referenced in article: %s".formatted(getShortInfo(), from, to, article);
    }

    public int getPrice()
    {

    }
}