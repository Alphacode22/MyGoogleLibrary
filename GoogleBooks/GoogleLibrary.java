/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleBooks;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author AlexZ
 */
public class GoogleLibrary {

    /**
     * @param args the command line arguments
     */

    public static String in = "C:\\Users\\AlexZ\\IdeaProjects\\HashCode.txt";
    public static String out = "C:\\Users\\AlexZ\\IdeaProjects\\HashCodeOut.txt";

    //Variable
    public static int numOfBooks=0;
    public static int numOfLibs=0;
    public static int numOfDays=0;
    //

    public static ArrayList<Library> libraries = new ArrayList<>();
    public static ArrayList<Integer> bookScores= new ArrayList<>();
    public static ArrayList<Integer> booksNumber = new ArrayList<>();


    //Read in Data
    public static void main(String[] args) throws Exception {
        Library library0 = new Library();
        Library library1 = new Library();


        // Read dataset
        File file = new File(in);
        // Read in what file
        Scanner scanner = new Scanner(file);

        int index = 0;//This will hold the line number
        //While there is a next line in the file
        while (scanner.hasNextLine()) {
            //Hold each line
            String line = scanner.nextLine();

            //Number of elements in this line
            String[] numbers = line.split(" ");

            //Empty bookArray
            booksNumber = new ArrayList<Integer>();

            //For the "words" in the line
            for (int i = 0; i < numbers.length; i++) {
                //Line one 6 2 7
                if (index == 0) {
                    if (i == 0) {
                        numOfBooks = Integer.parseInt(numbers[i]);
                    } else if (i == 1) {
                        numOfLibs = Integer.parseInt(numbers[i]);
                    } else if (i == 2) {
                        numOfDays = Integer.parseInt(numbers[i]);
                    }
                //Line two 1 2 3 6 5 4
                } else if (index == 1) {
                    if (i == 0) {
                        bookScores.add(Integer.parseInt(numbers[i]));
                    } else if (i == 1) {
                        bookScores.add(Integer.parseInt(numbers[i]));
                    } else if (i == 2) {
                        bookScores.add(Integer.parseInt(numbers[i]));
                    } else if (i == 3) {
                        bookScores.add(Integer.parseInt(numbers[i]));
                    } else if (i == 4) {
                        bookScores.add(Integer.parseInt(numbers[i]));
                    } else if (i == 5) {
                        bookScores.add(Integer.parseInt(numbers[i]));
                    }
                //Line three 5 2 2
                } else if (index == 2) {
                    if (i == 0) {
                        library0.setNumOfBooks(Integer.parseInt(numbers[i]));
                    } else if (i == 1) {
                        library0.setSignUpDays(Integer.parseInt(numbers[i]));
                    } else if (i == 2) {
                        library0.setBookPerDays(Integer.parseInt(numbers[i]));
                    }
                //Line four 0 1 2 3 4
                } else if (index == 3) {
                    if (i == 0) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 1) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 2) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 3) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 4) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    }
                    library0.setBookIDs(booksNumber);
                //Line five 4 3 1
                } else if (index == 4) {
                    if (i == 0) {
                        library1.setNumOfBooks(Integer.parseInt(numbers[i]));
                    } else if (i == 1) {
                        library1.setSignUpDays(Integer.parseInt(numbers[i]));
                    } else if (i == 2) {
                        library1.setBookPerDays(Integer.parseInt(numbers[i]));
                    }
                // Line size 0 2 3 5
                } else if(index == 5) {
                    if (i == 0) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 1) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 2) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    } else if (i == 3) {
                        booksNumber.add(Integer.parseInt(numbers[i]));
                    }
                    library1.setBookIDs(booksNumber);
                    }
                    //System.out.print(scanner.nextInt());
                    System.out.print(Integer.parseInt(numbers[i]) + " ");
                }
                index++;
                System.out.println("");
            }
        }
}
