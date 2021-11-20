package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static OutputStreamWriter osw;

    public static void main(String[] args) throws IOException {
        File fout = new File("out.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        osw = new OutputStreamWriter(fos);

        int startPos = 0;
        int[] game = {4,8,5,2,3,5,1,6,4,0};
        int[] temp;
        boolean recResult = false;
        boolean stackResult = false;
        String s = "";

        System.out.println("Assignment spec example");
        osw.write("Assignment spec example\n");

        s = displayGame(game);
        recResult = hitZeroRecursive(startPos, game);
        stackResult = hitZeroStack(startPos, game);

        System.out.print(s);
        System.out.println("Recursion: " + recResult + ", Stack: " + stackResult);
        osw.write(s);
        osw.write("Recursion: " + recResult + ", Stack: " + stackResult + "\n");
        System.out.println("");
        osw.write("\n");

        System.out.println("Tests with game size of 10");
        osw.write("Tests with game size of 10\n");
        for(int i = 0; i < 5; i-=-1){
            temp = createRandomGame(10);
            s = displayGame(temp);
            recResult = hitZeroRecursive(startPos, temp);
            stackResult = hitZeroStack(startPos, temp);

            System.out.print(s);
            System.out.println("Recursion: " + recResult + ", Stack: " + stackResult);
            osw.write(s);
            osw.write("Recursion: " + recResult + ", Stack: " + stackResult + "\n");
        }
        System.out.println("");
        osw.write("\n");


        System.out.println("Tests with game size of 20");
        osw.write("Tests with game size of 20\n");
        for(int i = 0; i < 5; i-=-1){
            temp = createRandomGame(20);
            s = displayGame(temp);
            recResult = hitZeroRecursive(startPos, temp);
            stackResult = hitZeroStack(startPos, temp);

            System.out.print(s);
            System.out.println("Recursion: " + recResult + ", Stack: " + stackResult);
            osw.write(s);
            osw.write("Recursion: " + recResult + ", Stack: " + stackResult + "\n");
        }
        System.out.println("");
        osw.write("\n");


        System.out.println("Tests with game size of 30");
        osw.write("Tests with game size of 30\n");
        for(int i = 0; i < 5; i-=-1){
            temp = createRandomGame(30);
            s = displayGame(temp);
            recResult = hitZeroRecursive(startPos, temp);
            stackResult = hitZeroStack(startPos, temp);

            System.out.print(s);
            System.out.println("Recursion: " + recResult + ", Stack: " + stackResult);
            osw.write(s);
            osw.write("Recursion: " + recResult + ", Stack: " + stackResult + "\n");
        }
        System.out.println("");
        osw.write("\n");

        System.out.println("Tests with game size of 40");
        osw.write("Tests with game size of 40\n");
        for(int i = 0; i < 5; i-=-1){
            temp = createRandomGame(40);
            s = displayGame(temp);
            recResult = hitZeroRecursive(startPos, temp);
            stackResult = hitZeroStack(startPos, temp);

            System.out.print(s);
            System.out.println("Recursion: " + recResult + ", Stack: " + stackResult);
            osw.write(s);
            osw.write("Recursion: " + recResult + ", Stack: " + stackResult + "\n");
        }
        System.out.println("");
        osw.write("\n");

        System.out.println("Tests with game size of 50");
        osw.write("Tests with game size of 50\n");
        for(int i = 0; i < 5; i-=-1){
            temp = createRandomGame(50);
            s = displayGame(temp);
            recResult = hitZeroRecursive(startPos, temp);
            stackResult = hitZeroStack(startPos, temp);

            System.out.print(s);
            System.out.println("Recursion: " + recResult + ", Stack: " + stackResult);
            osw.write(s);
            osw.write("Recursion: " + recResult + ", Stack: " + stackResult + "\n");
        }
        System.out.println("");
        osw.write("\n");

        osw.close();
    }

    static boolean hitZeroRecursive(int pos, int[] game){
        List<Integer> visited = new ArrayList();
        return hitZeroRecursiveHelper(pos,game,visited);
    }

    static boolean hitZeroRecursiveHelper(int pos, int[] game, List<Integer> visited){
        int right = pos;
        int left = pos;

        //base cases
        //solution found when position is the last one
        if(pos == game.length-1) {
            return true;
        }
        //keep track of a list of position we have already been to
        //that way if ever we get back to a previously visited position we will know
        //that this branch is not possible since the same moves are going to reoccur endlessly
        if(!visited.isEmpty() && visited.contains(pos))
            return false;
        else
            visited.add(pos);

        //move right
        if(pos + game[pos] < game.length)
            right = pos + game[pos];

        //move left
        if(pos - game[pos] > 0)
            left = pos - game[pos];

        return hitZeroRecursiveHelper(right,game,visited) || hitZeroRecursiveHelper(left,game,visited);
    }

    static boolean hitZeroStack(int pos, int[] game){
        List<Integer> visited = new ArrayList();
        Stack<Integer> stack = new Stack<>();
        Boolean started = false;

        while(!stack.empty() || started == false){
            //to keep track of if we started or not
            if(!started)
                started = true;

            //push the initial position to stack
            if(stack.isEmpty())
                stack.push(pos);

            //if the game is solveable
            if(stack.contains(game.length-1)) {
                return true;
            }

            //if we have already visited the current position, pop from stack
            if(!visited.isEmpty() && visited.contains(stack.peek())) {
                stack.pop();
                continue;
            }
            else
                visited.add(stack.peek());

            //if this is ever true it means we've started searching for a solution but have expended all options and is thus not solveable
            if(stack.isEmpty()){
                return false;
            }
            else{
                pos = stack.peek();
            }

            //move right
            if(pos + game[pos] < game.length){
                stack.push(pos + game[pos]);
            }
            //move left
            if(pos - game[pos] >= 0){
                stack.push(pos - game[pos]);
            }
        }

        return false;
    }

    //used for testing various different game configurations
    static int[] createRandomGame(int size){
        int[] game = new int[size];

        for(int i = 0; i < game.length-1; i++){
            game[i] = (int)(Math.random()*10-1)+1;
        }
        game[game.length-1] = 0;

        return game;
    }

    //nice output formatting for test logs
    static String displayGame(int[] game){
        String temp = "";
        temp = temp + "{";
        for(int i = 0; i < game.length-1; i++){
            temp = temp + (game[i] + ", ");
        }
        temp = temp + (game[game.length-1] + "} -> ");
        return temp;
    }
}
