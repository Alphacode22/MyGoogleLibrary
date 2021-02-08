package GoogleBooks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
TODO: USE CONSTRAINTS!!!!!!!!!!!!!
Pen and paper?
Write very specific requirements
Debug

 */
public class Organiser {
    //Modification
    int addOne = 0; //This is to offset all the books by one

    //Global variables
     int totalNumOfBooks = 0;
     int totalNumOfLibs = 0;
     int totalNumOfDays = 0;
     ArrayList<Library> totalLibraries = new ArrayList<>();
     ArrayList<Library> rankedLibraries = new ArrayList<Library>();

    //Hash with booksID, Score
    HashMap<Integer, String> totalPossibleBooks = new HashMap<>();//Change to class next time

    //Temporary Variable
    HashMap<Integer, String> tempBookObject = new HashMap<Integer, String>();

    public void parseInput(String fileName) throws FileNotFoundException {

        //If we have not set a file name
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
                    totalPossibleBooks.put(i+addOne, numbers[i]);
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
                        tempBookObject.put(Integer.parseInt(number)+addOne, "");
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
       // System.out.println("Hello");
    }//ENDparseInput

    //Sort the Library Books
    public void sort(){
        //Fill in the books in each library
        for(Library l: totalLibraries){
            fillInAllTheBooks(l);
            //System.out.println(l.getBookObjects());
        }

        System.out.println();

        //Sort the book in each of the libraries
        for (Library l : totalLibraries) {
            l.setBookObjects(sortBooksInEachLibrary(l.getBookObjects()));
            //System.out.println(l.getBookObjects());
        }

        System.out.println();

        //Perform calculate total score for each library.
        for (Library l : totalLibraries) {
            l.setScannedScore(calculateTotalScore(l));
            System.out.println(l.getScannedScore());
        }

        System.out.println();

        //Rank each library by total score
        for(Library l : totalLibraries){
            rankEachLibrary(l);
        }
        rankedLibraries = totalLibraries;

        for (Library rl : rankedLibraries) {//zero
            //System.out.println("hio");
            System.out.println(rl.getScannedScore());
        }

//        for(int i=0; i< totalLibraries.size();i++){
//            totalLibraries.get(i).setScannedScore(calculateTotalScore(totalLibraries.get(i)));
//        }
//
//        for (Library l : totalLibraries) {
//            //l.setScannedScore(calculateTotalScore(l));
//            System.out.println(l.getScannedScore());
//        }
    }//ENDsort()

    //Fill in all the books
    private void fillInAllTheBooks(Library l){
        for (Map.Entry<Integer, String> b : totalPossibleBooks.entrySet()) {
            for (Map.Entry<Integer, String> bi : l.getBookObjects().entrySet()) {
                if (bi.getKey().equals(b.getKey())) {
                    l.getBookObjects().put(bi.getKey(), b.getValue());
                }
            }
        }
    }//ENDfillInAllTheBooks


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

        //System.out.println("Didnt work");
        return sortedLibraryBooks;
    }//ENDsortBooksInEachLibrary

    //Perform calculate total score for each library.
    private int calculateTotalScore(Library l){
        int runningScoring; // Use store running total score of books
        int dayLimit=0; //Use to store day limit
        int dayCounter =0; // Counts the days that pass

        //For all the possible library
        runningScoring=0;//Set to zero
        //Day limit remainder of the week after we finish the sign up days
        dayLimit = totalNumOfDays - l.getSignUpDays();

        boolean isFinished=false;
        //For all the books in that library
        for (Iterator<Map.Entry<Integer, String>> iterator = l.getBookObjects().entrySet().iterator(); iterator.hasNext(); ) {
            //For each book we can send in a day
            for(int i=0; i< l.getBookPerDays(); i++){
                //If there is no next node
                //if(!isFinished){
                    if(!iterator.hasNext()){
                        //isFinished = true;
                       // break;
                        return runningScoring;
                    }

                    //For each of the possible days we can send a book
                    if (dayCounter >= dayLimit) {
                        //isFinished = true;
                        //break;
                        return runningScoring;
                    }

                    //Get the next entry
                    Map.Entry<Integer, String> b = iterator.next();
                    //Total up the book scores
                    runningScoring += Integer.parseInt(b.getValue());
                    //Add the book score to that library
                    l.setTotalPossibleScannedScore(runningScoring);
                    //Another day has passed
                //}
            }
            //Next day
            ++dayCounter;
        }
        return runningScoring;
    }//ENDcalculateTotalScore

    //Rank Each Library
    private void rankEachLibrary(Library l) {
        Library tempLibrary;
        for (int j = 0; j  < totalLibraries.size()-1; j++) {
            Library tl = totalLibraries.get(j);
            if(l.getScannedScore() > tl.getScannedScore()){
                tempLibrary = totalLibraries.get(j) ;
                //totalLibraries.set(j , l);
                //totalLibraries.set(j , l);
                totalLibraries.set(j , totalLibraries.get(j+1));
                //totalLibraries.add(tempLibrary);
                totalLibraries.set(j+1 , tempLibrary);
            }
        }
    }//ENDrankEachLibrary

    //Create submission file
    public void createOutput(String fileName) throws IOException {
        if(fileName == null){
            throw new IOException("You are missing a file");
        }

        File file = new File(fileName);

        // Read in what file
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(totalLibraries +"\n");
        for(Library rl : rankedLibraries){

        }



    }//ENDcreateOutput
}//ENDCLASS
