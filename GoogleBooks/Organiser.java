package GoogleBooks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
TODO: USE CONSTRAINTS!!!!!!!!!!!!!
Pen and paper?
Write very specific requirements
Debug

 */
public class Organiser {
    //Global variables
    public static int totalNumOfBooks = 0;
    public static int totalNumOfLibs = 0;
    public static int totalNumOfDays = 0;
    public static ArrayList<Library> totalLibraries = new ArrayList<>();

    //Hash with booksID, Score
    public static HashMap<Integer, String> totalPossibleBooks = new HashMap<>();//Change to class next time

    //Temporary Variable
    private HashMap<Integer, String> tempBookObject = new HashMap<Integer, String>();

    public void parseInput(String fileName) throws FileNotFoundException {

        if(fileName == null){
            throw new FileNotFoundException("You are missing a file");
        }

        File file = new File(fileName);

        // Read in what file
        Scanner scanner = new Scanner(file);

        int index = 0;//This will hold the line number
        int mutex=0;//This is a lock

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
                            totalNumOfBooks = Integer.parseInt(numbers[i]);
                            break;
                        case 1:
                            totalNumOfLibs = Integer.parseInt(numbers[i]);
                            break;
                        case 2:
                            totalNumOfDays = Integer.parseInt(numbers[i]);
                            break;
                    }

                }
            }
            //Line two 1 2 3 6 5 4
            else if (index == 1) {
                for (int i = 0; i < numbers.length; i++) {
                    totalPossibleBooks.put(i+1, numbers[i]);
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
                    tempBookObject = new HashMap<Integer, String>();

                    //Line four 0 1 2 3 4
                    for (String number : numbers) {
                        tempBookObject.put(Integer.parseInt(number)+1, "");
                    }
                    library.setBookObjects(tempBookObject);
                    totalLibraries.add(library);
                    //Make a new library
                    library = new Library();
                    mutex = 0;
                }
            }
            index++;
            //System.out.println("");


        }

        //Fill in the books in each library
        for (Library l : totalLibraries) {
            for (Map.Entry<Integer, String> b : totalPossibleBooks.entrySet()) {
                for (Map.Entry<Integer, String> bi : l.getBookObjects().entrySet()) {
                    if (bi.getKey().equals(b.getKey())) {
                        l.getBookObjects().put(bi.getKey(), b.getValue());
                    }
                }
            }
        }

        //Sort the book in each of the libraries
        for (Library l : totalLibraries) {
            l.setBookObjects(sortBooksInEachLibrary(l.getBookObjects()));
        }

       // System.out.println("Hello");
    }//ENDparseInput


    //Use to sort all the books in the libraries from biggest to smallest
    private LinkedHashMap<Integer, String> sortBooksInEachLibrary(HashMap<Integer, String> libraryBooks){
        //This stores all the values
        ArrayList<String> tempList = new ArrayList<>();
        LinkedHashMap<Integer, String> sortedLibraryBooks = new LinkedHashMap<>();;

        //For each book in that library
        for (Map.Entry<Integer, String> entry : libraryBooks.entrySet()) {
            //Add the values of the hashmap to an arraylist
            tempList.add(entry.getValue());
        }

        //Sort the values in reverse order
        Collections.sort(tempList, Collections.reverseOrder());
        //For each of the values in the array list
        for (String str : tempList) {
            //For each of the books in that library
            for (Map.Entry<Integer, String> entry : libraryBooks.entrySet()) {
                //If we find the entry that corresponds to that sorted value in the arraylist
                if (entry.getValue().equals(str)) {
                    //We add that entry to the sortHashmap as it is already sorted
                    sortedLibraryBooks.put(entry.getKey(), str);
                }
            }
            //System.out.println(sortedLibraryBooks);
        }

        System.out.println("Didnt work");
        return sortedLibraryBooks;
    }//ENDsortBooksInEachLibrary

    //Sort
    public void sort(){
        for(int i=0; i< totalLibraries.size();i++){
            totalLibraries.get(i).setScannedScore(calculateTotalScore(totalLibraries.get(i)));
        }

        for (Library l : totalLibraries) {
            //l.setScannedScore(calculateTotalScore(l));
            System.out.println(l.getScannedScore());
        }
    }


    //Perform calculate total score for each library.
    private int calculateTotalScore(Library l){
        int runningScoring; // Use store running total score of books
        int dayLimit=0;
        int dayCounter =0;

        //For all the possible library
        runningScoring=0;//Set to zero
        dayLimit = totalNumOfDays - l.getSignUpDays();
        //For all the books in that library
        for (Iterator<Map.Entry<Integer, String>> iterator = l.getBookObjects().entrySet().iterator(); iterator.hasNext(); ) {
            for(int i=0; i< l.getBookPerDays(); i++){
                if(!iterator.hasNext()){
                    break;
                }

                Map.Entry<Integer, String> b = iterator.next();
                //Total up the book scores
                runningScoring += Integer.parseInt(b.getValue());
                //Add the book score to that library
                l.setTotalPossibleScannedScore(runningScoring);
                //Another day has passed

                //For each of the possible days we can send a book
                if (dayCounter == dayLimit) {
                    break;
                }
            }

            dayCounter++;
        }
        return runningScoring;

















//
//        LinkedHashMap<String, String> SortedLibraries = sortBooksInEachLibrary();
//
//        System.out.println(SortedLibraries);
//        System.out.println(SortedLibraries.size());
//        for(int i =0; i < SortedLibraries.size(); i++){
//            //HashMap<String, String> NewlyOrdered = SortedLibraries.get(i).bookObjects;
////            for(int j = 0; j < sortBooksInEachLibrary().size(); j++){
////
////            }
//        }


//        for(int i =0; i < totalOfLibs; i++){
//            for(int j=0; j < totalOfBooks; j++){
//                if(libraries.get(i).getBookIDs())
//            }
//        }



//        //For all the library we have
//        for(int h =0; h < libraries.size(); h++){
//            for(int i =0; i < index; i++){
//                Library library = libraries.get(i);
//                totalDaysPassed += library.signUpDays;
//
//                //For all the books in that library
//                for(int j=0; j < library.numOfBooks; j++){
//                    //For all the type of books available
//                    for(int k=0; k < totalOfBooks; k++){
//                        //If this library contains this book
//                        if(book.containsKey(library.getBookIDs().get(j))){
//                            //Since two days pass
//                            totalDaysPassed+=(float) 1/2;
//                            //If the totalDaysPassed is not 6
//                            if(!(totalDaysPassed < 6)){
//                                //We can continue to add scores
//                                totalPossibleScannedScore+= Integer.parseInt(book.get(i));
//                            }
//                        }
//                    }
//                }
//                index++;
//            }
//
//        }


    }//ENDsort

    //Create submission file
    public void createOutput(String fileName){

    }//ENDcreateOutput
}
