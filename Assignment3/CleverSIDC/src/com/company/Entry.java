package com.company;

public class Entry {

    private int key;
    private String value;
    private Entry next;
    private Entry prev;

    public Entry() {
        key = -1;
        value = null;
        next = null;
        prev = null;
    }
    public Entry(int k, String v) {
        key = k;
        value = v;
        next = null;
        prev = null;
    }
    public Entry(int k, String v, Entry n, Entry p) {
        key = k;
        value = v;
        next = n;
        prev = p;
    }

    public int getKey() {
        return key;
    }
    public void setKey(int k) {
        key = k;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String v) {
        value = v;
    }

    public Entry getNext() {
        return next;
    }
    public void setNext(Entry n) {
        next = n;
    }
    public boolean hasNext() {
        if (next == null)
            return false;
        else
            return true;
    }

    public Entry getPrev() {
        return prev;
    }
    public void setPrev(Entry p) {
        prev = p;
    }
    public boolean hasPrev() {
        if (prev == null)
            return false;
        else
            return true;
    }
}
