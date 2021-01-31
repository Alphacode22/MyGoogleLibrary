package GoogleBooks;

public class Book {
    int bookID;
    int score;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Book(int bookID, int score) {
        this.bookID = bookID;
        this.score = score;
    }
}
