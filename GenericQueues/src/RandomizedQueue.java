import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] myQueue;
    private int tail = 0;
    private int capacity = 1;
    private int numItems = 0;
    
    private void resizeArray() {
        int newCapacity = 2 * capacity;
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < capacity; i++) {
            copy[i] = myQueue[i];
        }
        myQueue = copy;
        capacity = newCapacity;
    }
    
    private class RandomIterator implements Iterator<Item>
    {
        int current = 0;
        Item[] randomCopy;
        public RandomIterator() {
            randomCopy = (Item[]) new Object[capacity];
            for (int i = 0; i < numItems; i++) {
                randomCopy[i] = myQueue[i];
            }

            StdRandom.shuffle(randomCopy, 0, tail);

        }

        public boolean hasNext() { 
            return (current < tail && numItems != 0);
        }

        public void remove() { 
            throw new UnsupportedOperationException();  
        }
        
        public Item next()
        {
            if (current >= tail || numItems == 0) {
                throw new NoSuchElementException();
            }
            return randomCopy[current++];
        }
    }
    
    public RandomizedQueue() {                // construct an empty randomized queue
        myQueue = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {                // is the queue empty?
        return numItems == 0;
    }
    
    public int size() {                       // return the number of items on the queue
        return numItems;
    }
    
    public void enqueue(Item item) {           // add the item
        if(item == null) {
            throw new IllegalArgumentException();
        }
        
        if(numItems >= capacity) {
            resizeArray();
        }
        
        myQueue[tail++] = item;
        numItems++;
    }
    
    public Item dequeue() {                    // remove and return a random item
        if(numItems == 0) {
            throw new NoSuchElementException();
        }
        
        int index = StdRandom.uniform(0, tail);
        Item ret = myQueue[index];
        myQueue[index] = myQueue[tail - 1];
        tail--;
        numItems--;
        return ret;
    }
    
    public Item sample() {                     // return (but do not remove) a random item
        if(numItems == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, tail);
        return myQueue[index];
    }
    
    public Iterator<Item> iterator() { return new RandomIterator(); }

    public static void main(String[] args) {  // Unit tests
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        System.out.println("Running Unit tests...");
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60);
        q.enqueue(70);
        q.enqueue(80);
        q.enqueue(90);
        q.enqueue(100);
        
        if(q.size() != 10) {
            System.out.println("Test case failed 1");
        }

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();

        if(q.size() != 0) {
            System.out.println("Test case failed 2");
        }
        
        // Check for empty
        if(!q.isEmpty()) {
            System.out.println("Test case failed 3");
        }
        
        // Iterate over empty
        
        Iterator<Integer> it = q.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        // Add elements
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        
        // Iterator 1
        System.out.println("Iter 1");
        Iterator<Integer> it1 = q.iterator();
        while(it1.hasNext()) {
            System.out.println(it1.next());
        }
        
        
        // Iterator 2
        System.out.println("Iter 2");
        Iterator<Integer> it2 = q.iterator();
        while(it2.hasNext()) {
            System.out.println(it2.next());
        }
        
        System.out.println("Tests Passed !");
        
    }

}
