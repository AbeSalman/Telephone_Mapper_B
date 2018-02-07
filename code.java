// Some Java's library privided by NetBeans to enable methods and statments below

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

//PartA: 

public class AS_Telephone_Mapper_B {

    @SuppressWarnings("empty-statement")

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        // prompt user for dictionary file
        
        System.out.print("Enter name of dictionary file: ");
        String fName = input.nextLine();

        //Creating files short3 and short4
        
        // instantiate PhoneMapper for 3-letter words from dictionary file
        
        PhoneMapper myMap3 = new PhoneMapper(fName, 3);
        String[] w3 = myMap3.getArray();

        // instantiate PhoneMapper for 4-letter words from dictionary file
        
        PhoneMapper myMap4 = new PhoneMapper(fName, 4);
        String[] w4 = myMap4.getArray();

        //Create and store in file_1
        
        FileWriter fileWriter1 = new FileWriter("short3");

        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
        for (int i = 0; i < myMap3.numWords; i++) {
            bufferedWriter1.newLine();
            bufferedWriter1.write(w3[i]);
        }

        bufferedWriter1.close();
        
        //Create and store in file_2

        FileWriter fileWriter2 = new FileWriter("short4");

        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
        for (int i = 0; i < myMap3.numWords; i++) {
            bufferedWriter2.newLine();
            bufferedWriter2.write(w4[i]);
        }

        bufferedWriter1.close();

        System.out.print("Enter a test word (3 letters): ");
        String s = input.nextLine();
        int testNum = myMap3.findTelNum(s);
        System.out.println("Test word maps to " + testNum);

        System.out.println("Enter telephone number ");
        System.out.println("(7 digits, no 0's or 1's, negative to quit): ");

        int telNum = input.nextInt();
       while (telNum >= 0)
       {
           // process each non-negative number
           
           // extract first 3 digits
           
           int firstPart = telNum / 10000;

           // get words for first 3 digits and display
           String[] results = myMap3.findWords(firstPart);

           System.out.println("Options for first 3 digits:");
           for (int i = 0; i < results.length; i++)
           {
               System.out.println(results[i]);
           }

           // extract last 4 digits
           
           int secondPart = telNum % 10000;

           // get words for last 4 digits and display
           
           String[] results4 = myMap4.findWords(secondPart);

           System.out.println("Options for last 4 digits:");
           for (int i = 0; i < results4.length; i++)
           {
               System.out.println(results4[i]);
           }

           // prompt user for phone number
           
           System.out.print("Enter telephone number ");
           System.out.print("(7 digits, no 0's or 1's, negative to quit): ");
           telNum = input.nextInt();
       } // end while (telNum >=0)

    }
}

//Part B 

class PhoneMapper {

    final int MAXWORDS = 20000; // max number of words from dictionary
    String[] wordList = new String[MAXWORDS]; // list of dictionary words
    int numWords = 0; // number of words of correct length
    // extracted from dictionary
    int cnt = 0;

    PhoneMapper(String fileName, int wordLength) throws Exception {

        // fileName: name of dictionary file
        
        FileReader fileReader
                = new FileReader(fileName);

        // Always wrap FileReader in BufferedReader
        
        BufferedReader bufferedReader
                = new BufferedReader(fileReader);

        String line;
        
        // read dictionary file
        
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            if (line.length() == wordLength) { // wordLength: length of words to extract
                wordList[cnt] = line; // add each word of length wordLength to wordList[]
                cnt++;
                numWords = cnt;
            }
        }
    }

    public String[] getArray() {
        return wordList;
    }

    // return array of words mapped to num
    
    String[] findWords(int num) {
        String[] nString = new String[0];
        String str = "" + num;
       int k = 0;

       for (int i = 0; i < numWords; i++)
       {
           String word = wordList[i];

           boolean flag = true;
           
           int j = 0;

           while (j < wordList[i].length() && flag)
           {
               if ((word.charAt(j)) != Character.getNumericValue(str.charAt(j)))
                   flag = findTelNum(word) == num;

               j++;
           }

           if (flag)
           {
               String[] temp = new String[nString.length + 1];

               for (int m = 0; m < nString.length; m++)
               {
                   temp[m] = nString[m];
               }

               nString = temp;

               nString[nString.length - 1] = word;

               k++;
           }
       }

        return nString;
    }

    // returns telephone number that inStr maps to
    
    int findTelNum(String inStr) {
        int x = 0;
        for (int i = 0; i < inStr.length(); i++) {
            if (inStr.charAt(i) == 'a' || inStr.charAt(i) == 'b' || inStr.charAt(i) == 'c') {
                x = x * 10;
                x = x + 2;

            } else if (inStr.charAt(i) == 'd' || inStr.charAt(i) == 'e' || inStr.charAt(i) == 'f') {
                x = x * 10;
                x = x + 3;

            } else if (inStr.charAt(i) == 'g' || inStr.charAt(i) == 'h' || inStr.charAt(i) == 'i') {
                x = x * 10;
                x = x + 4;

            } else if (inStr.charAt(i) == 'j' || inStr.charAt(i) == 'k' || inStr.charAt(i) == 'l') {
                x = x * 10;
                x = x + 5;

            } else if (inStr.charAt(i) == 'm' || inStr.charAt(i) == 'm' || inStr.charAt(i) == 'o') {

                x = x * 10;
                x = x + 6;

            } else if (inStr.charAt(i) == 'p' || inStr.charAt(i) == 'q' || inStr.charAt(i) == 'r' || inStr.charAt(i) == 's') {
                x = x * 10;
                x = x + 7;

            } else if (inStr.charAt(i) == 't' || inStr.charAt(i) == 'u' || inStr.charAt(i) == 'v') {

                x = x * 10;
                x = x + 8;

            } else if (inStr.charAt(i) == 'w' || inStr.charAt(i) == 'x' || inStr.charAt(i) == 'y' || inStr.charAt(i) == 'z'){
            
            

                x = x * 10;
                x = x + 9;

            }
        }
        return x;
    }
} 
