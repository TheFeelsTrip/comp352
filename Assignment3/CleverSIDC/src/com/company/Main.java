package com.company;

public class Main {

    public static void main(String[] args) {
//        CleverSIDC peepee = new CleverSIDC();
//        peepee.add("69", "420");
//        peepee.add("17", "17");
//        peepee.add("20", "20");
//        //peepee.put(69, "69");
//        System.out.println(peepee.nextKey("69"));

        Entry temp1 = new Entry("69", "420");
        Entry temp2 = new Entry("17", "17");
        Entry temp3 = new Entry("20", "20");

        Sequence poopoo = new Sequence();
        poopoo.add(temp1);
        poopoo.add(temp2);
        poopoo.addAfter(temp1, temp3);
        poopoo.remove(temp1);
        System.out.println("cock");
    }
}
