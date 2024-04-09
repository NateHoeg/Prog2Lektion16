package opgave01;

import opgave01.models.LinkedList;

import java.util.Iterator;

public class Opgave01 {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Hej");
        //linkedList.isEmpty();
        //linkedList.removeFirst();
        //linkedList.isEmpty();
        linkedList.add("Vi ses");
        linkedList.addFirst("Nej");
        //linkedList.removeFirst();
        //linkedList.remove("Hej");
        //linkedList.remove("Vi ses");
        //linkedList.contains("Hej");
        //linkedList.contains("Vi ses");
        //linkedList.size();
        //linkedList.get(2);
        //System.out.println(linkedList.get(1));
        //linkedList.get(0);
        //System.out.println(linkedList.get(5));
        linkedList.add(2, "Smut");
        //System.out.println(linkedList.remove(2));
        //System.out.println(linkedList.indexOf("Smut"));
        Iterator<String> iterator = linkedList.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }
}
