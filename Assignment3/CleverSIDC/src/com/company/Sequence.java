package com.company;

public class Sequence {

    //the number of entries in the sequence
    private int size;
    //pointer to first entry
    private Entry head;
    //pointer to last entry
    private Entry tail;

    public Sequence(){
        head = null;
        tail = null;
        size = 0;
    }

    //find and return an entry from an index
    //returns null if nothing found
    public Entry get(int i){
        return atIndex(i);
    }

    //find and return an entry based on a key
    //returns null if nothing found
    public Entry getEntryFromKey(String k){
        Entry temp = head;
        //check all the entries until the second to last one
        while(temp.hasNext()){
            //matching entry found
            if(temp.getKey().equals(k)){
                return temp;
            }
            temp = temp.getNext();
        }

        //check the last entry
        if(temp.getKey().equals(k)){
            return temp;
        }
        //no matching entry found
        return null;
    }

    //set the key and value of an entry at a certain index
    public void set(int i, Entry e){
        atIndex(i).setValue(e.getValue());
        atIndex(i).setKey(e.getKey());
    }

    //add a new entry to the end of the sequence
    //returns the entry
    public Entry add(Entry e){
        //if empty the first added entry is both the head and tail
        if(isEmpty()){;
            head = e;
            tail = e;
            size++;
            return e;
        }
        else{
            return addLast(e);
        }
    }

    //add all the entries from another sequence to this one
    public void addAll(Sequence s){
        if(s.first() == null){
            return;
        }

        Entry temp = s.first();
        //for every entry until the second to last one
        while(temp.hasNext()){
            //make a copy of the entry and add it to the sequence
            Entry newEntry = new Entry(temp.getKey(), temp.getValue());
            add(newEntry);
            temp = temp.getNext();
        }
        //make copy of last entry and add it
        Entry newEntry = new Entry(temp.getKey(), temp.getValue());
        add(newEntry);
    }

    //remove an entry at a certain index
    public void remove(int i){
        remove(atIndex(i));
    }

    //return the next entry linked to this entry
    public Entry prev(Entry p){
        return p.getPrev();
    }

    //return the next entry linked to this entry
    public Entry next(Entry p){
        return p.getNext();
    }

    //set the key and value of a specific entry pointer
    public void set(Entry p, Entry e){
        p.setKey(e.getKey());
        p.setValue(e.getValue());
    }

    //add an entry before another entry
    public Entry addBefore(Entry p, Entry e){
        //an index of -1 means not found, as long as index is not -1 we can add before
        if(indexOf(p) != -1){
            //adding before head
            if(p == head){
                addFirst(e);
            }
            //adding before any other entry
            else{
                Entry temp = p;
                e.setNext(temp);
                e.setPrev(temp.getPrev());
                temp.getPrev().setNext(e);
                temp.setPrev(e);
                size++;
            }
            return e;
        }
        return null;
    }

    //add an entry after another entry
    public Entry addAfter(Entry p, Entry e){
        //an index of -1 means not found, as long as index is not -1 we can add after
        if(indexOf(p) != -1){
            //add after the tail
            if(p == tail){
                addLast(e);
            }
            //add after any other entry
            else{
                Entry temp = p;
                e.setPrev(temp);
                e.setNext(temp.getNext());
                temp.getNext().setPrev(e);
                temp.setNext(e);
                size++;
            }
            return e;
        }
        return null;
    }

    //adds entry to the start of the sequence
    public Entry addFirst(Entry e){
        //if empty the first added entry is both the head and tail
        if(isEmpty()){;
            head = e;
            tail = e;
            size++;
            return e;
        }
        //add before the head
        Entry temp = head;
        e.setNext(temp);
        temp.setPrev(e);
        head = e;
        size++;
        return e;
    }

    //adds entry to the end of the sequence
    public Entry addLast(Entry e){
        //if empty the last added entry is both the head and tail
        if(isEmpty()){;
            head = e;
            tail = e;
            size++;
            return e;
        }
        //add after tail
        Entry temp = tail;
        temp.setNext(e);
        e.setPrev(temp);
        tail = e;
        size++;
        return e;
    }

    //remove an entry based on an entry pointer
    //returns null if nothing was removed
    //returns the entry if it was removed
    public Entry remove(Entry p){
        //check if entry is in sequence
        if(indexOf(p) != -1){
            //if only 1 entry
            if(size == 1){
                Entry temp = head;
                head = null;
                tail = null;
                size--;
                return temp;
            }
            //more than 1 entry
            else{
                //if removing head
                if(p == head){
                    Entry temp = head;
                    head = temp.getNext();
                    temp.getNext().setPrev(null);
                    temp.setNext(null);
                    size--;
                    return temp;
                }
                //if removing tail
                else if(p == tail){
                    Entry temp = tail;
                    tail = temp.getPrev();
                    temp.getPrev().setNext(null);
                    temp.setPrev(null);
                    size--;
                    return temp;
                }
                //if removing entry between head and tail
                else{
                    Entry temp = p;
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    temp.setPrev(null);
                    temp.setNext(null);
                    size--;
                    return temp;
                }
            }
        }
        //entry were trying to remove isnt in the sequence
        return null;
    }

    //returns first entry
    public Entry first(){
        return head;
    }

    //returns last entry
    public Entry last(){
        return tail;
    }

    //finds entry at a specific index
    //returns null if nothing found
    public Entry atIndex(int i){
        //if index given is out of bounds return null entry
        if(i > size-1 || i < 0){
            return null;
        }
        else{
            Entry temp = head;
            for(int c = 0; c < i; c++){
                temp = temp.getNext();
            }
            return temp;
        }
    }

    //returns the index of an entry from a pointer
    //returns null if nothing found
    public int indexOf(Entry e){
        Entry temp = head;
        int i;
        for(i = 0; i < size-1; i++){
            if(temp == e){
                return i;
            }
            temp = temp.getNext();
        }
        if(temp == e){
            return i;
        }
        //not found
        return -1;
    }

    //returns the size of the sequence
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
