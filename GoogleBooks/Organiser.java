package GoogleBooks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
TODO: USE CONSTRAINTS!!!!!!!!!!!!!



 */
public class Organiser {
    //Global variables
    public static int totalOfBooks = 0;
    public static int totalOfLibs = 0;
    public static int totalOfDays = 0;
    public static ArrayList<Library> libraries = new ArrayList<>();

    public HashMap<Integer, String> book = new HashMap<>();
    //public static ArrayList<Integer> bookScores = new ArrayList<>();

    //Temporary Variable
    public ArrayList<Integer> booksNo = new ArrayList<>();



    public void parseInput(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        // Read in what file
        Scanner scanner = new Scanner(file);

        int index = 0;//This will hold the line number
        int mutex=0;

        //Need a new library to be created

        Library library = new Library();
        //While there is a next line in the file
        while (scanner.hasNextLine()) {
            //Hold each line
            String line = scanner.nextLine();

            //Number of elements in this line
            String[] numbers = line.split(" ");
            //Line one 6 2 7
            if(index == 0){
                for (int i = 0; i < numbers.length; i++) {
                    switch (i) {
                        case 0:
                            totalOfBooks = Integer.parseInt(numbers[i]);
                            break;
                        case 1:
                            totalOfLibs = Integer.parseInt(numbers[i]);
                            break;
                        case 2:
                            totalOfDays = Integer.parseInt(numbers[i]);
                            break;
                    }

                }
            }
            //Line two 1 2 3 6 5 4
            else if (index == 1) {
                for (int i = 0; i < numbers.length; i++) {
                    book.put(i, numbers[i]);
                }
            }
            else {
                if(mutex == 0){
                    //Line three 5 2 2
                    for (int i = 0; i < numbers.length; i++) {
                        switch (i) {
                            case 0:
                                library.setNumOfBooks(Integer.parseInt(numbers[i]));
                                break;
                            case 1:
                                library.setSignUpDays(Integer.parseInt(numbers[i]));
                                break;
                            case 2:
                                library.setBookPerDays(Integer.parseInt(numbers[i]));
                                break;
                        }
                    }
                    mutex = 1;
                } else {
                    //Empty bookArray
                    booksNo = new ArrayList<Integer>();

                    //Line four 0 1 2 3 4
                    for (String number : numbers) {
                        booksNo.add(Integer.parseInt(number));
                    }
                    library.setBookIDs(booksNo);
                    libraries.add(library);
                    //Make a new library
                    library = new Library();
                    mutex = 0;
                }
            }
            index++;
            //System.out.println("");
        }
    }
}

//    //Read in Data
//    public static void main(String[] args) throws Exception {
//        Library library0 = new Library();
//        Library library1 = new Library();
//
//
//        // Read dataset
//        File file = new File(in);
//        // Read in what file
//        Scanner scanner = new Scanner(file);
//
//        int index = 0;//This will hold the line number
//        //While there is a next line in the file
//        while (scanner.hasNextLine()) {
//            //Hold each line
//            String line = scanner.nextLine();
//
//            //Number of elements in this line
//            String[] numbers = line.split(" ");
//
//            //Empty bookArray
//            booksNumber = new ArrayList<Integer>();
//
////            //Line one 6 2 7
////            for (int i = 0; i < numbers.length; i++) {
////                if (i == 0) {
////                    numOfBooks = Integer.parseInt(numbers[i]);
////                } else if (i == 1) {
////                    numOfLibs = Integer.parseInt(numbers[i]);
////                } else if (i == 2) {
////                    numOfDays = Integer.parseInt(numbers[i]);
////                }
////                System.out.print(Integer.parseInt(numbers[i]) + " ");
////
////            }
////            index++;
////            System.out.println("");
//
//
//            //For the "words" in the line
//            for (int i = 0; i < numbers.length; i++) {
//                //Line one 6 2 7
//                if (index == 0) {
//                    if (i == 0) {
//                        numOfBooks = Integer.parseInt(numbers[i]);
//                    } else if (i == 1) {
//                        numOfLibs = Integer.parseInt(numbers[i]);
//                    } else if (i == 2) {
//                        numOfDays = Integer.parseInt(numbers[i]);
//                    }
//                //Line two 1 2 3 6 5 4
//                } else if (index == 1) {
//                    if (i == 0) {
//                        bookScores.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 1) {
//                        bookScores.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 2) {
//                        bookScores.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 3) {
//                        bookScores.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 4) {
//                        bookScores.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 5) {
//                        bookScores.add(Integer.parseInt(numbers[i]));
//                    }
//                //Line three 5 2 2
//                } else if (index == 2) {
//                    if (i == 0) {
//                        library0.setNumOfBooks(Integer.parseInt(numbers[i]));
//                    } else if (i == 1) {
//                        library0.setSignUpDays(Integer.parseInt(numbers[i]));
//                    } else if (i == 2) {
//                        library0.setBookPerDays(Integer.parseInt(numbers[i]));
//                    }
//                //Line four 0 1 2 3 4
//                } else if (index == 3) {
//                    if (i == 0) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 1) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 2) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 3) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 4) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    }
//                    library0.setBookIDs(booksNumber);
//                //Line five 4 3 1
//                } else if (index == 4) {
//                    if (i == 0) {
//                        library1.setNumOfBooks(Integer.parseInt(numbers[i]));
//                    } else if (i == 1) {
//                        library1.setSignUpDays(Integer.parseInt(numbers[i]));
//                    } else if (i == 2) {
//                        library1.setBookPerDays(Integer.parseInt(numbers[i]));
//                    }
//                // Line size 0 2 3 5
//                } else if(index == 5) {
//                    if (i == 0) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 1) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 2) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    } else if (i == 3) {
//                        booksNumber.add(Integer.parseInt(numbers[i]));
//                    }
//                    library1.setBookIDs(booksNumber);
//                    }
//                    //System.out.print(scanner.nextInt());
//                    System.out.print(Integer.parseInt(numbers[i]) + " ");
//                }
//                index++;
//                System.out.println("");
//            }
//    }//END OF CLASS
//}