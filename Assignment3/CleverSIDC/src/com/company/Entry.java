package com.company;

public class Entry implements Comparable<Entry> {

    private String key;
    private String value;
    private Entry next;
    private Entry prev;

    public Entry() {
        key = null;
        value = null;
        next = null;
        prev = null;
    }
    public Entry(String k, String v) {
        key = k;
        value = v;
        next = null;
        prev = null;
    }
    public Entry(String k, String v, Entry n, Entry p) {
        key = k;
        value = v;
        next = n;
        prev = p;
    }

    //return the key for this entry
    public String getKey() {
        return key;
    }
    //set the key for this entry
    public void setKey(String k) {
        key = k;
    }

    //return the value for this entry
    public String getValue() {
        return value;
    }
    //set the value for this entry
    public void setValue(String v) {
        value = v;
    }

    //return the linked next entry to this entry
    public Entry getNext() {
        return next;
    }
    //set the linked next entry to this entry
    public void setNext(Entry n) {
        next = n;
    }
    //check if this entry has a next linked entry
    public boolean hasNext() {
        if (next == null)
            return false;
        else
            return true;
    }

    //return the linked previous entry to this entry
    public Entry getPrev() {
        return prev;
    }
    //set the linked previous entry to this entry
    public void setPrev(Entry p) {
        prev = p;
    }
    //check if this entry has a previous linked entry
    public boolean hasPrev() {
        if (prev == null)
            return false;
        else
            return true;
    }

    @Override
    //Used to sort entries amongst each other
    public int compareTo(Entry e) {
        return key.compareTo(e.getKey());
    }
}
