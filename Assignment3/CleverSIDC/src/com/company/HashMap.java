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

    private void initializeMaps() {
        for(int i = 0; i < nbBuckets; i++){
            buckets[i] = new OrderedMap();
        }
    }

    public String getValue(String key){
        String temp = buckets[hash(Integer.parseInt(key))].get(key);
        return temp;
    }

    public Entry getEntry(String key){
        Entry temp = buckets[hash(Integer.parseInt(key))].getEntry(key);
        return temp;
    }

    public Sequence entrySet(){
        Sequence s = new Sequence();
        Entry temp = ceilingEntry("-1");
        for(int i = 0; i < size; i++){
            s.add(temp);
            temp = ceilingEntry(temp.getKey());
        }
        return s;
    }

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

    public String put(String key, String value){
        String temp = buckets[hash(Integer.parseInt(key))].put(key,value);
        if(temp == null)
            size++;
        return temp;
    }

    public int hash(int key) {
        return (key%nbBuckets);
    }

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
