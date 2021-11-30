package com.company;

public class HashMap{

    private OrderedMap[] buckets;
    private int nbBuckets;
    private int size;

    public HashMap() {
        nbBuckets = 13;
        buckets = new OrderedMap[13];
        initializeMaps();
    }

    public HashMap(int b) {
        nbBuckets = b;
        buckets = new OrderedMap[b];
        initializeMaps();
    }

    //sets the number of buckets and creates an empty OrderedMap in each one
    private void initializeMaps() {
        for(int i = 0; i < nbBuckets; i++){
            buckets[i] = new OrderedMap();
        }
    }

    //return the value of an entry in the hash map based on a key
    //returns null if not found
    public String getValue(String key){
        String temp = buckets[hash(Integer.parseInt(key))].get(key);
        return temp;
    }

    //return the value of an entry in the hash map based on a key
    //returns null if not found
    public Entry getEntry(String key){
        Entry temp = buckets[hash(Integer.parseInt(key))].getEntry(key);
        return temp;
    }

    //returns a sequence of all entries in the hash map
    public Sequence entrySet(){
        Sequence s = new Sequence();

        for(int i = 0; i < nbBuckets; i++){
            s.addAll(buckets[i].entrySet());
        }

        return s;
    }

    //finds the entry in the entire hash map with the biggest key smaller than the key sent into the function
    //returns null if not found
    public Entry floorEntry(String key){
        Entry temp = null;
        for(int i = 0; i < nbBuckets; i++){
            if (buckets[i].isEmpty())
                continue;
            if(temp == null)
                temp = buckets[i].floorEntry(key);
            else{
                Entry f = buckets[i].floorEntry(key);
                if(f == null)
                    continue;
                else
                    if(Integer.parseInt(f.getKey()) > Integer.parseInt(temp.getKey()))
                        temp = f;
            }
        }
        return temp;
    }

    //finds the entry in the entire hash map with the smallest key bigger than the key sent into the function
    //returns null if not found
    public Entry ceilingEntry(String key){
        Entry temp = null;
        for(int i = 0; i < nbBuckets; i++){
            if (buckets[i].isEmpty())
                continue;
            if(temp == null)
                temp = buckets[i].ceilingEntry(key);
            else{
                Entry c = buckets[i].ceilingEntry(key);
                if(c == null)
                    continue;
                else
                    if(Integer.parseInt(c.getKey()) < Integer.parseInt(temp.getKey()))
                        temp = c;
            }
        }
        return temp;
    }

    //put a new entry into the hash map by using the put method of the ordered map
    //if key already exists, replace the value of the existing entry
    //otherwise, make new entry, place it before the first key bigger than it and return the new key
    public String put(String key, String value){
        String temp = buckets[hash(Integer.parseInt(key))].put(key,value);
        if(temp == null)
            size++;
        return temp;
    }

    //the hashing function for the key using modulus of number of buckets
    //number of buckets adapts based on size of CleverSIDC size
    public int hash(int key) {
        return (key%nbBuckets);
    }

    //remove an entry from the hash map based on a key by using the remove method of the ordered map
    //return the key if the entry was successfully removed
    //otherwise return null
    public String remove(String key){
        String temp = buckets[hash(Integer.parseInt(key))].remove(key);
        if(temp != null)
            size--;
        return temp;
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
