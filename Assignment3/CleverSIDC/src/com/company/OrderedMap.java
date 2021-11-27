package com.company;

public class OrderedMap {

    private int size;
    private Sequence s;

    public OrderedMap(){
        s = new Sequence();
        size = 0;
    }

    /**
     *
     * @param key The key for which we want to get the value
     * @return The value associated to the key, null if key does not exist
     */
    public String get(String key){
        Entry temp = s.getEntryFromKey(key);
        if(temp == null){
            return null;
        }
        else{
            return temp.getValue();
        }
    }

    public Entry getEntry(String key){
        Entry temp = s.getEntryFromKey(key);
        if(temp == null){
            return null;
        }
        else{
            return temp;
        }
    }

    public String put(String key, String value){
        Entry e = new Entry(key, value);

        if(isEmpty()){
            s.add(e);
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
            else{
                Entry smallestBigger = ceilingEntry(key);
                if (smallestBigger == null){
                    s.addLast(e);
                }
                else{
                    s.addBefore(smallestBigger, e);
                }
                return null;
            }
        }
    }

    public String remove(String key){
        Entry temp = s.getEntryFromKey(key);
        if(temp == null){
            return null;
        }
        else{
            String retVal = temp.getValue();
            s.remove(temp);
            return retVal;
        }
    }

    public Sequence entrySet(){
        return s;
    }

    public Entry floorEntry(String key){
        Entry temp = s.first();

        if(isEmpty())
            return null;

        Entry retVal = null;
        //check if key already exists
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

    public Entry ceilingEntry(String key){
        Entry temp = s.first();

        if(isEmpty())
            return null;

        //check if key already exists
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
