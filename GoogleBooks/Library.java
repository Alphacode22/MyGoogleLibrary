package GoogleBooks;

import java.util.ArrayList;

class Library {
    int numOfBooks=0;
    int signUpDays=0;
    int bookPerDays=0;
    ArrayList<Integer> bookIDs = new ArrayList<Integer>();

    public Library() {

    }

    public Library(int numOfBooks, int signUpDays, int bookPerDays, ArrayList<Integer> bookIDs) {
        this.numOfBooks = numOfBooks;
        this.signUpDays = signUpDays;
        this.bookPerDays = bookPerDays;
        this.bookIDs = bookIDs;
    }

    public int getNumOfBooks() {
        return numOfBooks;
    }

    public void setNumOfBooks(int numOfBooks) {
        this.numOfBooks = numOfBooks;
    }

    public int getSignUpDays() {
        return signUpDays;
    }

    public void setSignUpDays(int signUpDays) {
        this.signUpDays = signUpDays;
    }

    public int getBookPerDays() {
        return bookPerDays;
    }

    public void setBookPerDays(int bookPerDays) {
        this.bookPerDays = bookPerDays;
    }

    public ArrayList<Integer> getBookIDs() {
        return bookIDs;
    }

    public void setBookIDs(ArrayList<Integer> bookIDs) {
        this.bookIDs = bookIDs;
    }
}