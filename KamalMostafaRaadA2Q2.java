/**
 * KamalMostafaRaadA2Q2
 * <p>
 * COMP 2140 SECTION D01
 * ASSIGNMENT    Assignment 2, question 2
 *
 * @author Mostafa Raad Kamal.
 * @version 1/02/19
 * <p>
 * PURPOSE: Using Recursion to build the scrabble game.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

class Letter {

    private char letter;
    private int point;
    private Letter next;

    public Letter(char x, int y) {
        letter = x;
        point = y;

    }
    /**
     * Mutator method it sets the next one to the given one.
     *
     * PARAMETERS:
     *    firstParam  Letter next is the point that will be pointed by the current one
     *    secondParam N/A
     *
     * RETURNS:
     *    void
     */
    public void setNext(Letter next) {
        this.next = next;
    }
    /**
     * The next 3 are accessor methods that gives respectively the character , integer point and the next letter
     *
     * PARAMETERS:
     *    firstParam  N/A
     *    secondParam N/A
     *
     * RETURNS:
     *    return letter is for the tostring method,point is used to calculate the point of a word and next is point of the the next class.
     */

    public char getLetter() {
        return letter;
    }

    public int getPoint() {
        return point;
    }

    public Letter getNext() {
        return this.next;
    }
}
class LetterList {

    private Letter first;
    private int count;

    public LetterList() {
        first = null;
        count = 0;
    }

    /**
     * Inserting at the first of the linkedlist
     *
     * PARAMETERS:
     *    firstParam  char in that will be inserted
     *    secondParam int point is the score of the given character
     *
     * RETURNS:
     *    void
     */

    public void insert(char in, int point) {

        Letter letter = new Letter(in, point);
        letter.setNext(first);
        first = letter;
        count++;

    }
    /**
     * Traverse through the linkedlist and find the character then add the score of it
     *
     * PARAMETERS:
     *    firstParam  String words that will be separated into chars
     *    secondParam N/A
     *
     * RETURNS:
     *    It does return the integer sum of the whole characters points
     */

    public int getvalue(String words) {
        int store = 0;
        Letter curr;
        String uppercase = words.toUpperCase();
        for (int i = 0; i < words.length(); i++) {
            curr = first;
            char in = uppercase.charAt(i);
            while (curr != null && curr.getLetter() != in) {
                curr = curr.getNext();
                if (curr == null) {
                    break;
                }
            }
            store += curr.getPoint();
        }
        //  System.out.println(words+" "+store);
        return store;

    }
    /**
     * To ind out our list is empty or not
     *
     * PARAMETERS:
     *    firstParam  N/A
     *    secondParam N/A
     *
     * RETURNS:
     *    boolean value is true if first is null
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * Accessor method that provide the number of elements in out list
     *
     * PARAMETERS:
     *    firstParam  N/A
     *    secondParam N/A
     *
     * RETURNS:
     *    return the integer value
     */
    public int getSize() {
        return count;
    }
   /*
    public String toString() {
        Letter curr = first;
        String hold = "";
        while (curr != null) {
            hold += (curr.getLetter() + " " + curr.getPoint() + "\n");
            curr = curr.getNext();
        }
        return hold;

    }
    */
}


class Dictionary {

    private String[] storage;
    private int ele;

    public Dictionary(int size) {
        storage = new String[size];
        ele = 0;
    }

    /**
     * This accessor method provides the element number of the array.
     *
     * PARAMETERS:
     *    firstParam  N/A
     *    secondParam N/A
     *
     * RETURNS:
     *    The element number of the array that is necessary.
     */

    public int getSize() {
        return ele;
    }
    /**
     * Get a position and find the word in that position
     *
     * PARAMETERS:
     *    firstParam  integer x is the position of the word that we will get.
     *    secondParam N/A
     *
     * RETURNS:
     *    returns the string word from the array
     */
    public String getWord(int x) {
        return storage[x];
    }
    /**
     * Get the text name and read line by line and store them into the dictionary
     *
     * PARAMETERS:
     *    firstParam  String filename, The name of the file that will be read
     *    secondParam N/A
     *
     * RETURNS:
     *    Void
     */
    public void TextRead(String filename) {
        BufferedReader fileIn;
        String inputLine;
        try {
            fileIn = new BufferedReader(new FileReader(filename));
            inputLine = fileIn.readLine();

            while (inputLine != null) {
                insert(inputLine);
                inputLine = fileIn.readLine();
            }
            fileIn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Inserting the given word from less to more according to unicode value
     *
     * PARAMETERS:
     *    firstParam  String word is the word that will be inserted
     *    secondParam N/A
     *
     * RETURNS:
     *    Void
     */
    public void insert(String words) {
        int curr;
        if (ele == 0) {
            storage[0] = words;
            ele++;
        } else {
            curr = 0;
            while (curr < ele && storage[curr].compareToIgnoreCase(words) < 0) { //Comparing
                curr++; //Found
            }
            if (curr == ele) { //Last?
                storage[curr] = words;
                ele++;
            } else if (!storage[curr].equalsIgnoreCase(words)) {

                for (int i = ele - 1; i >= curr; i--) {
                    storage[i + 1] = storage[i];       //Shfting to the right
                }

                storage[curr] = words.strip(); //Correct position all other items after this have more Unicode value
                //System.out.println(storage[pos]+" "+ ele);
                ele++;
            }
        }
    }

    /**
     * A resursive binary search to search the given word in out dictionary
     *
     * PARAMETERS:
     *    firstParam  String word that we need to compare to a dictionary
     *    secondParam lower bound is the earlier position of the dictionary
     *    thirdParam upperbound the limit, the word is not after the limit
     *
     * RETURNS:
     *    Boolean value. If it finds the given word it gives true else false
     */

    public boolean Search(String word, int lowerBound, int upperBound) {
        int mid;


        mid = (lowerBound + upperBound) / 2;
        if (storage[mid].compareToIgnoreCase(word) == 0) {
            return true;
        } else if (lowerBound > upperBound) {
            return false;
        } else {
            if (storage[mid].compareToIgnoreCase(word) < 0) {
                return Search(word, mid + 1, upperBound);
            } else {
                return Search(word, lowerBound, mid - 1);
            }
        }


    }


   /* public String toString() {
        String hold = "";
        int count = 0;
        while (count != ele) {
            System.out.println(storage[count]);
            count++;
        }
        return hold;
    }*/
}

public class KamalMostafaRaadA2Q2 {

    private static int Anagramsize;
    private static char[] arrChar;
    private static Dictionary build;
    private static Dictionary store;
    private static LetterList create;


    public static void main(String[] args) {
        store = new Dictionary(1000);
        build = new Dictionary(84095);
        build.TextRead("engmix.txt");
        create = new LetterList();
        flll(create);
        arrChar = new char[8];
        read();
        Anagramsize = arrChar.length;
        doAnagram(Anagramsize);
        System.out.println(output());
        System.out.println("Program terminated normally");
    }

    /**
     * It displays string from the store dictionary and that has the 8th charcter
     *
     * PARAMETERS:
     *    firstParam  N/A
     *    secondParam N/A
     *
     * RETURNS:
     *    String text as output to tell us the best word that we can use
     */

    public static String output() {

        String show = "\n";
        int suPer = 0;
        String SuperWord = "";
        show += ("Valid words are :\n" + "Words" + "  " + "Points\n");

        if (store.getWord(0) == null) {
            show += ("\nNo words can be created\n");
        } else {
            for (int i = 0; i < store.getSize(); i++) {
                String sense = store.getWord(i);
                int points = create.getvalue(sense);
                show += sense + "\t" + points + "\n";
                if (points > suPer && sense.indexOf(arrChar[0])==0) {
                    suPer = points;
                    SuperWord = sense;
                }
            }

        }
        show += ("\nBest Word is :");
        show += (SuperWord + " " + suPer);

        return show;
    }

    /**
     * This method reads the input from the user
     *
     * PARAMETERS:
     *    firstParam  N/A
     *    secondParam N/A
     *
     * RETURNS:
     *    void method
     */
    public static void read() {

        String get;
        {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                for (int i = 1; i < 8; i++) { //Taking other characters
                    System.out.print("Please Enter Letter " + (i) + ":");
                    get = input.readLine();
                    arrChar[i] = get.charAt(0);
                }
                System.out.println("Please enter the letter you would like to use on the board :");
                get = input.readLine();
                arrChar[0] = get.charAt(0); //The letter that is onto the board
            } catch (Exception io) {
                io.printStackTrace();
                io.getMessage();
            }
        }


    }
    /**
     * doAnagram will recursively take the size of the word that will be less time to time and send the word to the rotate method
     * Implemented from Robert Lafore - Data Structures and Algorithms in Java-Sams (2002)
     * PARAMETERS:
     *    firstParam  int size is the size of the word
     *    secondParam N/A
     *
     * RETURNS:
     *    void method
     */
    private static void doAnagram(int size) {
        if (size == 1) { //base case
            return;
        }
        for (int j = 0; j < size; j++) {
            doAnagram(size - 1); //stacking
            if (size == 2)
                storing(build, store);
            rotate(size); //rotating
        }
    }
    /**
     * It rotates the word, it puts the first word to the last index after shifting the rest to the left
     * Implemented from Robert Lafore - Data Structures and Algorithms in Java-Sams (2002)
     *
     * PARAMETERS:
     *    firstParam  int Insize it takes the size of the word
     *    secondParam N/A
     *
     * RETURNS:
     *    Void method
     */
    public static void rotate(int Insize) {
        int j;
        int position = Anagramsize - Insize;
        char temp = arrChar[position];
        for (j = position + 1; j < Anagramsize; j++) {
            arrChar[j - 1] = arrChar[j];
        }
        arrChar[j - 1] = temp;
    }
    /**
     * Check if the word is meaningful according to the built dictionary and only then insert it into the some dictionary
     *
     * PARAMETERS:
     *    firstParam  built is the dictionary that has words from a real dictionary
     *    secondParam some is the dictionary where our desired meaningful words are going to be inserted.
     *
     * RETURNS:
     *    void method
     */
    public static void storing(Dictionary built, Dictionary some) {
        String convert;
        for (int j = 0; j < Anagramsize; j++) {
            convert = "";
            for (int i = 0; i <= j; i++)
                convert += arrChar[i];

            if (built.Search(convert, 0, build.getSize())) {
                some.insert(convert);
            }
        }
    }

    /**
     * Fill the created linkedlist with the A-Z characters and its associated points.
     *
     * PARAMETERS:
     *    firstParam  It takes the linkedlist where it will insert the character and points
     *    secondParam N/A
     *
     * RETURNS:
     *    void
     */

    private static void flll(LetterList in) {
        in.insert('A', 1);
        in.insert('B', 3);
        in.insert('C', 3);
        in.insert('D', 2);
        in.insert('E', 1);
        in.insert('F', 4);
        in.insert('G', 2);
        in.insert('H', 4);
        in.insert('I', 1);
        in.insert('J', 8);
        in.insert('K', 5);
        in.insert('L', 1);
        in.insert('M', 3);
        in.insert('N', 1);
        in.insert('O', 1);
        in.insert('P', 3);
        in.insert('Q', 10);
        in.insert('R', 1);
        in.insert('S', 1);
        in.insert('T', 1);
        in.insert('U', 1);
        in.insert('V', 4);
        in.insert('W', 4);
        in.insert('X', 8);
        in.insert('Y', 4);
        in.insert('Z', 10);
    }


}
