package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //****************************************
        //Test case 1: Size = 1000
        //****************************************
        //testFunction(1000);

        //****************************************
        //Test case 2: Size = 20000
        //****************************************
        //testFunction(20000);

        //****************************************
        //Test case 3: Size = 100000
        //****************************************
        //testFunction(100000);

        //****************************************
        //Test case 4: Size = 250000
        //****************************************
        //testFunction(250000);

        //****************************************
        //Test case 5: Size = 500000
        //****************************************
        //testFunction(500000);

        //****************************************
        //Test case 6: file = NASTA_test_file1.txt
        //****************************************
        //testFunctionFile(500000, "NASTA_test_file1.txt");

        //****************************************
        //Test case 7: file = NASTA_test_file2.txt
        //****************************************
        //testFunctionFile(500000, "NASTA_test_file2.txt");

        //****************************************
        //Test case 8: file = NASTA_test_file3.txt
        //****************************************
        testFunctionFile(500000, "NASTA_test_file3.txt");

    }

    static void testFunction(int size){
        String testKey = "";
        CleverSIDC testClever = new CleverSIDC(size);

        //this does the same thing as the previous constructor
        //testClever1.SetSIDCThreshold(size);

        System.out.println("Testing for size " + size + "\n");

        for(int i = 0; i < size; i++){
            String s = testClever.generate();
            if(i == 0) {
                testKey = s;
            }
            testClever.add(s, s);
        }

         Sequence sequence = new Sequence();
        sequence = testClever.allKeys();

        System.out.println("After sorting");
        System.out.println("First key: " + sequence.first().getKey());
        System.out.println("Last key: " + sequence.last().getKey());
        System.out.println();

        System.out.println("Test key for value, next and prev: " + testKey);
        String value = testClever.getValues(testKey);
        System.out.println("Value for key " + testKey + " : " + value);
        String testNext = testClever.nextKey(testKey);
        System.out.println("Next key for key " + testKey + " : " + testNext);
        String testPrev = testClever.prevKey(testKey);
        System.out.println("Previous key for key " + testKey + " : " + testPrev);

        int rangeCount = testClever.rangeKey("00000000", "55555555");
        System.out.println("Number of keys between 00000000 and 50000000: " + rangeCount);
        String removedKey = testClever.remove(testKey);
        if(removedKey != null) {
            System.out.println("Key " + testKey + " removed");
        }
    }

    static void testFunctionFile(int size, String fileName){
        String testKey = "";
        int nbLines = 0;
        CleverSIDC testClever = new CleverSIDC(size);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(testKey.equals("")){
                    testKey = line;
                }
                testClever.add(line, line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Testing for size " + size + "\n");

        Sequence sequence = new Sequence();
        sequence = testClever.allKeys();

        System.out.println("After sorting");
        System.out.println("First key: " + sequence.first().getKey());
        System.out.println("Last key: " + sequence.last().getKey());
        System.out.println();

        System.out.println("Test key for value, next and prev: " + testKey);
        String value = testClever.getValues(testKey);
        System.out.println("Value for key " + testKey + " : " + value);
        String testNext = testClever.nextKey(testKey);
        System.out.println("Next key for key " + testKey + " : " + testNext);
        String testPrev = testClever.prevKey(testKey);
        System.out.println("Previous key for key " + testKey + " : " + testPrev);

        int rangeCount = testClever.rangeKey("00000000", "55555555");
        System.out.println("Number of keys between 00000000 and 50000000: " + rangeCount);
        String removedKey = testClever.remove(testKey);
        if(removedKey != null) {
            System.out.println("Key " + testKey + " removed");
        }
    }
}
