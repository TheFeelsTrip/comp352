package com.company;

import java.util.Arrays;

public class CleverSIDC {
    //implement with hash maps with each bucked pointing to an ordered map
    private HashMap hashMapOfOrderedMaps;

    //a list of available prime numbers used to set the number of buckets for the hashmap
    //they were calculated finding the biggest prime number < (2^n)*0.75 where n>= 6 and n<= 19
    //the n values were selected because 2^6 is the biggest power of 2 less than 100 and 2^19 is the smallest power of 2 greater than 500000
    private int[] nbBucketOptions = {47, 89, 191, 383, 761, 1531, 3067, 6143, 12281, 24571, 49139, 98299, 196597, 393209};

    private int size;
    private int threshold;

    public CleverSIDC(){

    }

    public CleverSIDC(int s){
        SetSIDCThreshold(s);
    }

    //where 100 ≤ Size ≤ ~500,000 is an integer number that defines
    //the size of the list. This size is very important as it will determine what data types or data
    //structures will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.)
    public void SetSIDCThreshold(int size){
        threshold = size;
        int i;
        for(i = 0; i < nbBucketOptions.length - 1; i++){
            if(nbBucketOptions[i] > size){
                hashMapOfOrderedMaps = new HashMap(nbBucketOptions[i-1]);
                return;
            }
        }

        hashMapOfOrderedMaps = new HashMap(nbBucketOptions[i]);
    }

    //randomly generates new non-existing key of 8 digits
    public String generate(){
        String id = "";
        for(int i = 0; i < 8; i++){
            id += (int)Math.floor(Math.random()*10);
        }
        return id;
    }

    //return all keys in CleverSIDC as a sorted sequence
    public Sequence allKeys(){
        Sequence s = hashMapOfOrderedMaps.entrySet();
        Entry arr[] = new Entry[s.size()];
        Entry temp = s.first();

        int i = 0;
        while(temp.hasNext()){
            arr[i] = temp;
            i++;
            temp = temp.getNext();
        }
        arr[i] = temp;

        //sort all the entries
        Arrays.sort(arr);

        s = new Sequence();

        for(int j = 0; j < arr.length; j++){
            s.add(arr[j]);
        }

        return s;
    }

    //add an entry for the given key and value;
    public String add(String key, String value){
        if(size == threshold)
            return null;

        String temp = hashMapOfOrderedMaps.put(key,value);
        if(temp == null)
            size++;
        return temp;
    }

    //remove the entry for the given key;
    public String remove(String key){
        return hashMapOfOrderedMaps.remove(key);
    }

    //return the values of the given key;
    public String getValues(String key){
        return hashMapOfOrderedMaps.getValue(key);
    }

    //return the key for the successor of key
    public String nextKey(String key){
        Entry temp = hashMapOfOrderedMaps.ceilingEntry(key);
        if(temp == null)
            return null;
        else{
            return temp.getKey();
        }
    }

    //return the key for the predecessor of key
    public String prevKey(String key){
        Entry temp = hashMapOfOrderedMaps.floorEntry(key);
        if(temp == null)
            return null;
        else{
            return temp.getKey();
        }
    }

    //returns the number of keys that are within the specified range of
    //the two keys key1 and key2
    public int rangeKey(String key1, String key2){
        Sequence s = allKeys();
        Entry temp = s.first();
        int counter = 0;

        for(int i = 0; i < s.size(); i++){
            if(Integer.parseInt(temp.getKey()) >= Integer.parseInt(key1) && Integer.parseInt(temp.getKey()) <= Integer.parseInt(key2))
                counter++;

            if(Integer.parseInt(temp.getKey()) > Integer.parseInt(key2)){
                return counter;
            }

            if(temp.hasNext())
                temp = temp.getNext();
        }

        return counter;
    }

    public int size(){
        return this.size;
    }

}
