package GoogleBooks;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class GoogleLibrary {

    static String in = "C:\\Users\\AlexZ\\IdeaProjects\\HashCode2.txt";
    static String out = "C:\\Users\\AlexZ\\IdeaProjects\\HashCodeOut.txt";

    public static void main(String[] args) throws IOException {
        Organiser organiser = new Organiser();
        organiser.parseInput(in);
        organiser.sort();
        //organiser.createOutput(out);
    }
}//END CLASS





