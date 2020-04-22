import java.util.Iterator;
import java.util.NoSuchElementException;

// This is a linked list based implementation
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int numItems;
    
    private class DeqIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { 
            return current != null; 
        }

        public void remove() { 
            throw new UnsupportedOperationException();  
        }
        
        public Item next()
        {
            Item item = current.data;
            if(item == null)
            {
                throw new NoSuchElementException();
            }
            current = current.next;
            return item;
        }
    }
    
    private class Node {
        Item data;
        Node next;
        Node prev;
    }
    
    public Deque() {                          // construct an empty deque
        first = null;
        last = null;
        numItems = 0;
    }
    
    public boolean isEmpty() {                // is the deque empty?
        if (first == null) {
            return true;
        }
        return false;
    }
    
    public int size() {                       // return the number of items on the deque
        return numItems;
    }
    
    public void addFirst(Item item) {          // add the item to the front
        
        if (item == null) {
            throw new NoSuchElementException();
        }
        
        Node object = new Node();
        object.data = item;
        object.prev = null;
        object.next = first;

        if (first != null) {
            first.prev = object;
        }

        first = object;

        if (last == null) {
            last = object;
        }
        numItems++;
    }
    
    public void addLast(Item item) {           // add the item to the end

        if (item == null) {
            throw new NoSuchElementException();
        }

        Node object = new Node();
        object.data = item;
        object.prev = last;
        object.next = null;
        if(last != null)
        {
            last.next = object;
        }

        last = object;
        
        if (first == null) {
            first = object;
        }
        numItems++;
    }
    
    public Item removeFirst() {                // remove and return the item from the front
        if (first == null) {
            throw new NoSuchElementException();
        }
        
        Item firstItem = first.data;
        first = first.next;
        if (first != null) 
            first.prev = null;
        else 
            last = null;
        numItems--;
        return firstItem;
    }
    
    public Item removeLast() {                 // remove and return the item from the end
        if(last == null) {
            throw new NoSuchElementException();
        }
        
        Item lastItem = last.data;
        last = last.prev;
        if(last != null)
            last.next = null;
        else 
            first = null;
        numItems--;
        return lastItem;
    }

    public Iterator<Item> iterator() { return new DeqIterator(); }

    public static void main(String[] args) {

        Deque<Integer> myDeq = new Deque<Integer>();
        if (true != myDeq.isEmpty()) {
            System.out.println("Failed Case 1");
        }
        
        myDeq.addFirst(10);
        myDeq.addFirst(20);
        myDeq.addFirst(30);
        myDeq.addFirst(40);
        myDeq.addLast(50);
        myDeq.addLast(60);
        myDeq.addLast(70);
        myDeq.addLast(80);
        
        int val = myDeq.size();
        if(val != 8) {
            System.out.println("Failed Case 1.1");
        }

        val = myDeq.removeFirst();
        if(val != 40) {
            System.out.println("Failed Case 2");
        }

        val = myDeq.removeLast();
        if(val != 80) {
            System.out.println("Failed Case 3");
        }
        
        val = myDeq.size();
        if(val != 6) {
            System.out.println("Failed Case 1.2");
        }
        
        val = myDeq.removeLast();
        System.out.println(val);
        val = myDeq.removeLast();
        System.out.println(val);
        val = myDeq.removeLast();
        System.out.println(val);
        val = myDeq.removeLast();
        System.out.println(val);
        val = myDeq.removeLast();
        System.out.println(val);
        val = myDeq.removeLast();
        System.out.println(val);

        Iterator<Integer> it = myDeq.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        
        val = myDeq.size();
        if(val != 0) {
            System.out.println("Failed Case 1.3");
        }
        
        
        myDeq.addFirst(10);
        val = myDeq.removeLast();
        System.out.println(val);

        val = myDeq.size();
        if(val != 0) {
            System.out.println("Failed Case 1.4");
        }

        // Add 1000 Elements in the end
        for(int i = 0; i < 1000; i++)
        {
            myDeq.addLast(i%7);
        }
        System.out.println(myDeq.size());

    }
}
