package GoogleBooks;

import java.io.FileNotFoundException;

public class GoogleLibrary {

    public static String in = "C:\\Users\\AlexZ\\IdeaProjects\\HashCode.txt";
    //public static String out = "C:\\Users\\AlexZ\\IdeaProjects\\HashCodeOut.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Organiser organiser = new Organiser();
        organiser.parseInput(in);
    }
}//END CLASS





