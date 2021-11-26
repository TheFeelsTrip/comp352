package com.company;

public class OrderedMap {

    private int size;
    private Entry head;
    private Entry tail;

    public OrderedMap(){
        head = null;
        tail = null;
        size = 0;
    }

    public String getValue(String key){
        Entry temp = head;

        if(isEmpty())
            return null;

        //check if key already exists
        //loop until last entry
        while(temp.hasNext()){
            if(temp.getKey() == key){
                return temp.getValue();
            }
            temp = temp.getNext();
        }
        //check the last entry
        if(temp.getKey() == key){
            return temp.getValue();
        }

        return null;
    }

    public Entry getEntry(String key){
        Entry temp = head;

        if(isEmpty())
            return null;

        //check if key already exists
        //loop until last entry
        while(temp.hasNext()){
            if(temp.getKey() == key){
                return temp;
            }
            temp = temp.getNext();
        }
        //check the last entry
        if(temp.getKey() == key){
            return temp;
        }

        return null;
    }

    public String put(String key, String value){
        if(isEmpty()){
            Entry e =  new Entry(key, value);
            head = e;
            tail = e;
            size++;
            return null;
        }
        else {
            Entry temp = head;
            //check if key already exists
            //loop until last entry
            while(temp.hasNext()){
                if(temp.getKey() == key){
                    temp.setValue(value);
                    return value;
                }
                temp = temp.getNext();
            }
            //check the last entry
            if(temp.getKey() == key){
                temp.setValue(value);
                return value;
            }

            //key does not exist, make new entry
            //entry goes before head
            Entry e =  new Entry(key, value);
            temp = head;
            if(Integer.parseInt(temp.getKey()) > Integer.parseInt(key)){
                e.setNext(temp);
                temp.setPrev(e);
                head = e;
                size++;
                return null;
            }
            //new entry between head and tail
            while(temp != tail){
                temp = temp.getNext();
                if(Integer.parseInt(temp.getKey()) > Integer.parseInt(key)){
                    e.setNext(temp);
                    e.setPrev(temp.getPrev());
                    temp.getPrev().setNext(e);
                    temp.setPrev(e);
                    size++;
                    return null;
                }
            }
            //compare to tail
            //entry goes before tail
            if(Integer.parseInt(temp.getKey()) > Integer.parseInt(key)){
                e.setNext(temp);
                e.setPrev(temp.getPrev());
                temp.getPrev().setNext(e);
                temp.setPrev(e);
                size++;
                return null;
            }
            //entry goes after tail
            else{
               temp.setNext(e);
               e.setPrev(temp);
               tail = e;
               size++;
               return null;
            }
        }
    }

    public String remove(String key){
        Entry temp = head;

        if(isEmpty())
            return null;

        //if only 1 entry
        if(size == 1){
            if(temp.getKey() == key){
                head = null;
                tail = null;
                size--;
                return temp.getValue();
            }
        }
        //more than 1 entry
        else{
            //if removing head
            if(temp.getKey() == key){
                head = temp.getNext();
                temp.getNext().setPrev(null);
                temp.setNext(null);
                size--;
                return temp.getValue();
            }
            //if removing entry between head and tail
            temp = temp.getNext();
            while(temp.hasNext()){
                if(temp.getKey() == key){
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    size--;
                    return temp.getValue();
                }
                temp = temp.getNext();
            }
            //if removing tail
            if(temp.getKey() == key){
                tail = temp.getPrev();
                temp.getPrev().setNext(null);
                temp.setPrev(null);
                size--;
                return temp.getValue();
            }
        }
        return null;
    }

    public Entry floorEntry(String key){
        Entry temp = head;

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
        Entry temp = head;

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
