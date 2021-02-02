package GoogleBooks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
TODO: USE CONSTRAINTS!!!!!!!!!!!!!
Pen and paper?
Write very specific requirements

 */
public class Organiser {
    //Global variables
    public static int totalOfBooks = 0;
    public static int totalOfLibs = 0;
    public static int totalOfDays = 0;
    public static ArrayList<Library> libraries = new ArrayList<>();

    //Hash with booksID, Score
    public static HashMap<Integer, String> books = new HashMap<>();//Change to class next time

    //Temporary Variable
    private HashMap<Integer, String> booksThings = new HashMap<Integer, String>();

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
                    books.put(i+1, numbers[i]);
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
                    booksThings = new HashMap<Integer, String>();

                    //Line four 0 1 2 3 4
                    for (String number : numbers) {
                        booksThings.put(Integer.parseInt(number)+1, "");
                    }
                    library.setBookObjects(booksThings);
                    libraries.add(library);
                    //Make a new library
                    library = new Library();
                    mutex = 0;
                }
            }
            index++;
            //System.out.println("");


        }

        //Fill in the books in each library
        //High difficulty
        for (int i = 0; i < libraries.size(); i++) {
            Library l = libraries.get(i);
            for (Map.Entry<Integer, String> b : books.entrySet()) {
                for (Map.Entry<Integer, String> bi : l.getBookObjects().entrySet()){
                    if(bi.getKey().equals(b.getKey())){
                        l.getBookObjects().put(bi.getKey(),b.getValue());
                        if(i==1){
                            System.out.println(b);
                            System.out.println(bi);
                        }
                    }
                }
            }
        }


       // System.out.println("Hello");
    }

    public void sort(){

        float totalDaysPassed =0; //For the number of days
        int index=0;

        //        for(int i =0; i < totalOfLibs; i++){
//            for(int j=0; j < totalOfBooks; j++){
//                if(libraries.get(i).getBookIDs())
//            }
//        }

        //For each library in our libraries
        for(int i =0; i < libraries.size(); i++) {
            HashMap<Integer, String> libraryBooks = libraries.get(i).bookObjects;
            LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
            ArrayList<String> list = new ArrayList<>();

            for (Map.Entry<Integer, String> entry : libraryBooks.entrySet()) {
                list.add(entry.getValue());
            }
            //Here
            Collections.sort(list, Collections.reverseOrder());
            for (String str : list) {
                for (Map.Entry<Integer, String> entry : libraryBooks.entrySet()) {
                    if (entry.getValue().equals(str)) {
                        sortedMap.put(String.valueOf(entry.getKey()), str);
                    }
                }
            }
            System.out.println(sortedMap);
        }

//            Library library = libraries.get(i);
//            //For all the books in that library
//            for(int j=0; j < library.getNumOfBooks()-1; j++){






//                //Map.Entry<Integer, String> temp;
//                TreeMap<Integer, String> tm = new TreeMap<Integer, String>(library.getBookObjects());
//                Iterator itr=tm.keySet().iterator();
////                if(Integer.parseInt(library.getBookObjects().get(j)) < Integer.parseInt(library.getBookObjects().get(j+1))){
////                    library.getBookObjects().get(j);
////                }

        


//         //For each library in our libraries
//        for(int i =0; i < libraries.size(); i++){
//            Library library = libraries.get(i);
//            //For all the books in that library
//            for(int j=0; j < library.getNumOfBooks(); j++){
//            //For all the type of books available
//                for(int k=0; k < totalOfBooks; k++){
//                    //Use bubble sort
//                    //If this library contains this book
//
//                }
//            }
//        }



//        //For each library in our libraries
//        for(int i =0; i < libraries.size(); i++){
//            Library library = libraries.get(i);
//            //For all the books in that library
//            for(int j=0; j < library.numOfBooks; j++){
//            //For all the type of books available
//                for(int k=0; k < totalOfBooks; k++){
//                    //Use bubble sort
//                    //If this library contains this book
//                    if(book.containsKey(library.getBookIDs().get(j))){
//                       if()
//                    }
//                }
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

    }

    public void createOutput(String fileName){

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