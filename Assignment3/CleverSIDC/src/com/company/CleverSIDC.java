package com.company;

public class CleverSIDC {
    //implement with hash maps with each bucked pointing to an ordered map

    private int threshold;
    private HashMap hashMapOfOrderedMaps = new HashMap();

    //where 100 ≤ Size ≤ ~500,000 is an integer number that defines
    //the size of the list. This size is very important as it will determine what data types or data
    //structures will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.)
    public void SetSIDCThreshold(int size){

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
    public OrderedMap allKeys(){
        return null;
    }

    //add an entry for the given key and value;
    public String add(String key, String value){
        return hashMapOfOrderedMaps.put(key,value);
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
    public int rangeKey(int key1, int key2){
        return 0;
    }

}
