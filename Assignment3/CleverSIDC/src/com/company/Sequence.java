package com.company;

public class Sequence {

    private int size;
    private Entry head;
    private Entry tail;

    public Sequence(){
        head = null;
        tail = null;
        size = 0;
    }
    //*******************************************************//
    //Index-based methods
    //*******************************************************//
    public Entry get(int i){
        return atIndex(i);
    }

    public Entry getEntryFromKey(String k){
        Entry temp = head;
        while(temp.hasNext()){
            if(temp.getKey() == k){
                return temp;
            }
            temp = temp.getNext();
        }
        //check the last entry
        if(temp.getKey() == k){
            return temp;
        }
        return null;
    }

    public void set(int i, Entry e){
        atIndex(i).setValue(e.getValue());
        atIndex(i).setKey(e.getKey());
    }

    public Entry add(Entry e){
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

    public void remove(int i){
        remove(atIndex(i));
    }

    //*******************************************************//
    //Entry/Node-based methods
    //*******************************************************//
    public Entry prev(Entry p){
        return p.getPrev();
    }

    public Entry next(Entry p){
        return p.getNext();
    }

    public void set(Entry p, Entry e){
        p.setKey(e.getKey());
        p.setValue(e.getValue());
    }

    public Entry addBefore(Entry p, Entry e){
        if(indexOf(p) != -1){
            if(p == head){
                addFirst(e);
            }
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

    public Entry addAfter(Entry p, Entry e){
        if(indexOf(p) != -1){
            if(p == tail){
                addLast(e);
            }
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

    public Entry addFirst(Entry e){
        if(isEmpty()){;
            head = e;
            tail = e;
            size++;
            return e;
        }
        Entry temp = head;
        e.setNext(temp);
        temp.setPrev(e);
        head = e;
        size++;
        return e;
    }

    public Entry addLast(Entry e){
        if(isEmpty()){;
            head = e;
            tail = e;
            size++;
            return e;
        }
        Entry temp = tail;
        temp.setNext(e);
        e.setPrev(temp);
        tail = e;
        size++;
        return e;
    }

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

    public Entry first(){
        return head;
    }

    public Entry last(){
        return tail;
    }

    public Entry atIndex(int i){
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
