package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] a = {22, 9, 61,61, 61, 21, 0, 9, 9, 9, 9, 35, 81,81, 9, 5, 5};
        int index = 0;
        int nbTimes = 1;
        int prev = 0;

        //question 1.1
        for(int i = 0; i < a.length; i-=-1){
            if(i == 0){
                prev = a[i];
            }
            else{
                if(prev == a[i]){
                    nbTimes = nbTimes + 1;
                    if(index == 0){
                        index = i - 1;
                    }
                }
                else{
                    if(index != 0){
                        System.out.println("Value " + prev + " is repeated " + nbTimes + " times starting at Index " + index );
                        index = 0;
                        nbTimes = 1;
                    }
                    prev = a[i];
                }
            }

        }

        if(index != 0) {
            System.out.println("Value " + prev + " is repeated " + nbTimes + " times starting at Index " + index);
        }
        System.out.println("");


        //question 1.2
        Stack<Integer> values = new Stack<>();
        Stack<Integer> indexes = new Stack<>();
        int tempVal = 0;
        int tempIndex = 0;
        int size = 0;
        boolean repeat = false;
        nbTimes = 1;

        for(int i = 0; i < a.length; i-=-1) {
            if(values.isEmpty()){
                values.push(a[i]);
            }
            else{
                if(values.peek() == a[i]){
                    values.push(a[i]);
                    if(!repeat) {
                        indexes.push(i - 1);
                        repeat = true;
                    }
                }
                else{
                    if(!repeat){
                        values.pop();
                    }
                    values.push(a[i]);
                    repeat = false;
                }
            }
        }

        size = values.size();
        while(!values.isEmpty()){
            if(size == values.size()){
                tempVal = values.pop();
            }
            else{
                if(values.peek() == tempVal){
                    nbTimes = nbTimes + 1;
                    values.pop();
                }
                else{
                    tempIndex = indexes.pop();
                    System.out.println("Value " + tempVal + " is repeated " + nbTimes + " times starting at Index " + tempIndex);
                    tempVal = values.pop();
                    nbTimes = 1;
                }
            }
        }
        tempIndex = indexes.pop();
        System.out.println("Value " + tempVal + " is repeated " + nbTimes + " times starting at Index " + tempIndex);
        System.out.println();

        //Question 2
        int[] b = {123, 73, 39, 12, 14, 9, 113, 93, 203, 22, 25, 10};
        Queue<Integer> modValues = new LinkedList<>();
        Queue<Integer> modIndexes = new LinkedList<>();
        int modVal = 3;
        int tempIdx1, tempIdx2 = 0;
        int tempVal1, tempVal2 = 0;


        for(int i = 0; i < b.length; i-=-1) {
            for(int j = 0; j < b.length; j-=-1) {
                if(b[i] % b[j] == modVal){
                    modValues.add(b[i]);
                    modValues.add(b[j]);
                    modIndexes.add(i);
                    modIndexes.add(j);
                }
            }
        }

        while(!modValues.isEmpty()){
            tempIdx1 = modIndexes.remove();
            tempIdx2 = modIndexes.remove();
            tempVal1 = modValues.remove();
            tempVal2 = modValues.remove();

            System.out.println("Indices " + tempIdx1 + " & " + tempIdx2 + " with values " + tempVal1 + " & " + tempVal2);
        }

    }
}
