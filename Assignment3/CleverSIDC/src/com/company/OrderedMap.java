package com.company;

public class OrderedMap {

    private int size;
    private Sequence s;

    public OrderedMap(){
        s = new Sequence();
        size = 0;
    }

    //returns the value associated to a key
    //returns null if nothing found
    public String get(String key){
        return getEntry(key).getValue();
    }

    //returns a pointer to an entry based on a key
    //returns null if nothing found
    public Entry getEntry(String key){
        Entry temp = s.getEntryFromKey(key);
        if(temp == null){
            return null;
        }
        else{
            return temp;
        }
    }

    //put a new entry into the ordered map
    //if key already exists, replace the value of the existing entry
    //otherwise, make new entry, place it before the first key bigger than it and return the new key
    public String put(String key, String value){
        Entry e = new Entry(key, value);

        if(isEmpty()){
            s.add(e);
            size++;
            return null;
        }
        else {
            Entry temp = s.getEntryFromKey(key);
            //if key already exists
            if(temp != null){
                String retVal = temp.getValue();
                s.set(temp, e);
                return retVal;
            }
            //key does not already exist
            else{
                Entry smallestBigger = ceilingEntry(key);
                if (smallestBigger == null){
                    s.addLast(e);
                }
                else{
                    s.addBefore(smallestBigger, e);
                }
                size++;
                return null;
            }
        }
    }

    //remove an entry from the ordered map based on a key
    //return the key if the entry was successfully removed
    //otherwise return null
    public String remove(String key){
        Entry temp = s.getEntryFromKey(key);
        if(temp == null){
            return null;
        }
        else{
            String retVal = temp.getValue();
            s.remove(temp);
            size--;
            return retVal;
        }
    }

    //return the sequence of all the entries
    public Sequence entrySet(){
        return s;
    }

    //finds the entry with the biggest key smaller than the key sent into the function
    //returns null if not found
    public Entry floorEntry(String key){
        Entry temp = s.first();

        if(isEmpty())
            return null;

        Entry retVal = null;

        //loop until last entry
        while(temp.hasNext()){
            if(Integer.parseInt(temp.getKey()) < Integer.parseInt(key)){
                retVal = temp;
            }
            temp = temp.getNext();
        }
        //check the last entry
        if(Integer.parseInt(temp.getKey()) < Integer.parseInt(key)){
            retVal = temp;
        }

        return retVal;
    }

    //finds the entry with the smallest key bigger than the key sent into the function
    //returns null if not found
    public Entry ceilingEntry(String key){
        Entry temp = s.first();

        if(isEmpty())
            return null;

        //loop until last entry
        while(temp.hasNext()){
            if(Integer.parseInt(temp.getKey()) > Integer.parseInt(key)){
                return temp;
            }
            temp = temp.getNext();
        }
        //check the last entry
        if(Integer.parseInt(temp.getKey()) > Integer.parseInt(key)){
            return temp;
        }

        return null;
    }

    public Entry firstEntry(){
        return s.first();
    }

    public Entry lastEntry(){
        return s.last();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if (size == 0)
            return true;
        else
            return false;
    }


}
