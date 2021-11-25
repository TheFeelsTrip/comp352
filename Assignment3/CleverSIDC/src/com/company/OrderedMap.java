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

    public String get(int key){
        Entry temp = head;
        //check if key already exists
        while(temp.hasNext()){
            if(temp.getKey() == key){
                return temp.getValue();
            }
            temp = temp.getNext();
        }
        //for the last entry
        if(temp.getKey() == key){
            return temp.getValue();
        }

        return null;
    }

    public String put(int key, String value){
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
            while(temp.hasNext()){
                if(temp.getKey() == key){
                    temp.setValue(value);
                    return value;
                }
                temp = temp.getNext();
            }
            //for the last entry
            if(temp.getKey() == key){
                temp.setValue(value);
                return value;
            }

            //key does not exist, make new entry
            //compare to head
            Entry e =  new Entry(key, value);
            temp = head;
            if(temp.getKey() > key){
                e.setNext(temp);
                temp.setPrev(e);
                head = e;
                size++;
                return null;
            }
            //middle entries
            while(temp != tail){
                temp = temp.getNext();
                if(temp.getKey() > key){
                    e.setNext(temp);
                    e.setPrev(temp.getPrev());
                    temp.getPrev().setNext(e);
                    temp.setPrev(e);
                    size++;
                    return null;
                }
            }
            //compare to tail
            if(temp.getKey() > key){
                e.setNext(temp);
                e.setPrev(temp.getPrev());
                temp.getPrev().setNext(e);
                temp.setPrev(e);
                size++;
                return null;
            }
            else{
               temp.setNext(e);
               e.setPrev(temp);
               tail = e;
               size++;
               return null;
            }
        }
    }

    public String remove(int key){
        Entry temp = head;
        //if removing head
        if(size == 1){
            if(temp.getKey() == key){
                head = null;
                tail = null;
                size--;
                return temp.getValue();
            }
        }
        else{
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
