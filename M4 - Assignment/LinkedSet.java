import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author YOUR NAME (you@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if (element == null || this.contains(element)) {
            return false;
        }

        Node nodeToAdd = new Node(element);
        Node insertionPoint = findInsertionPoint(element);

        if (size == 0) {
            front = nodeToAdd;
            rear = nodeToAdd;

        } else if (insertionPoint == null) {
            nodeToAdd.next = front;
            front.prev = nodeToAdd;
            front = nodeToAdd;

        } else if (insertionPoint == rear) {
            insertionPoint.next = nodeToAdd;
            nodeToAdd.prev = rear;
            rear = nodeToAdd;

        } else {
            nodeToAdd.next = insertionPoint.next;
            insertionPoint.next.prev = nodeToAdd;
            nodeToAdd.prev = insertionPoint;
            insertionPoint.next = nodeToAdd;
        }

        size++;
        return true;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {
        if(element == null || !this.contains(element)) {
            return false;
        }
        Node nodeToRemove = locateElement(element);

        if (size == 1) {
            rear = null;
            front = null;

        } else if (nodeToRemove == front) {
            front = nodeToRemove.next;
            nodeToRemove.next.prev = null;

        } else if (nodeToRemove == rear) {
            rear = nodeToRemove.prev;
            rear.next = null;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;

        }
        size--;
        return true;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        return locateElement(element) != null;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        
        //checks to verify that all elements in first set are in second set
        Iterator<T> itr2 = s.iterator();
        while(itr2.hasNext()) {
            if(!this.contains(itr2.next())) {
                return false;
            }
        }

        // checks to veryfiy that all elements in sescond set are in first set
        Iterator<T> itr = this.iterator();
        while(itr.hasNext()) {
            if(!s.contains(itr.next())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        if (s.size != this.size) {
            return false;
        }
        
        Iterator<T> itr = this.iterator();

        while(itr.hasNext()) {
            if (!s.contains(itr.next())) {
                return false;
            }
        }

        return true;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
        Set<T> unionSet = new LinkedSet<T>();

        Iterator<T> itrA = this.iterator();
        while (itrA.hasNext()) {
            unionSet.add(itrA.next());
        }

        Iterator<T> itrB = s.iterator();
        while (itrB.hasNext()) {
            unionSet.add(itrB.next());
        }

        return unionSet;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        Set<T> unionSet = new LinkedSet<T>();
        
        Iterator<T> itrA = this.iterator();
        while (itrA.hasNext()) {
            unionSet.add(itrA.next());
        }

        Iterator<T> itrB = s.iterator();
        while (itrB.hasNext()) {
            unionSet.add(itrB.next());
        }

        return unionSet;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        Set<T> intersectionSet = new LinkedSet<T>();
        
        Iterator<T> itr = this.iterator();
        while(itr.hasNext()) {
            T next = itr.next();
            if (s.contains(next)) {
                intersectionSet.add(next);
            }
        }
        return intersectionSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        Set<T> intersectionSet = new LinkedSet<T>();
        
        Iterator<T> itr = this.iterator();
        while(itr.hasNext()) {
            T next = itr.next();
            if (s.contains(next)) {
                intersectionSet.add(next);
            }
        }
        return intersectionSet;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        Set<T> complementSet = new LinkedSet<T>();
        
        Iterator<T> itr = this.iterator();
        while (itr.hasNext()) {
            T next = itr.next();
            if (s.contains(next)) {
                continue;
            }
            complementSet.add(next);
        }
        return complementSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        Set<T> complementSet = new LinkedSet<T>();
        
        Iterator<T> itr = this.iterator();
        while (itr.hasNext()) {
            T next = itr.next();
            if (s.contains(next)) {
                continue;
            }
            complementSet.add(next);
        }
        return complementSet;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new LinkedSetIterator();
    }
    


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new DescendingSetIterator();
    }
    

    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        return new PowerIterator();
    }

    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.

    private Node locateElement(T element) {
        Node node = front;
        
        while (node != null) {
            int comparison = node.element.compareTo(element);
            if (comparison == 0) {
                return node;
            } else if (comparison > 0) {
                return null;
            }
            node = node.next;
        }
        return null;
    }

    private Node findInsertionPoint(T element) {
        Node node = front;
        Node insertionPoint = null;
        while (node != null) {
            int comparison = node.element.compareTo(element);
            if (comparison < 0) {
                insertionPoint = node;
                node = node.next;
            } else if (comparison > 0) {
                return insertionPoint;
            }

        }
        return insertionPoint;
        
    }


    ////////////////////
    // Nested classes //
    ////////////////////

    private class LinkedSetIterator implements Iterator<T> {
        
        private Node first = front;

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T next = first.element;
            first = first.next;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class DescendingSetIterator implements Iterator<T> {

        private Node last = rear;

        @Override
        public boolean hasNext() {
            return last != null;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            T next = last.element;
            last = last.prev;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class PowerIterator implements Iterator<Set<T>> {

        private int idx = 0;

        @Override
        public boolean hasNext() {
            int powerSetCardinality = (int) Math.pow(2, size);
            return idx < powerSetCardinality;
        }

        @Override
        public Set<T> next() {
            Set<T> nextSet = new LinkedSet<T>();

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            char[] bitString = Integer.toBinaryString(idx).toCharArray();
            Node isAdded = rear;
            for (int i = bitString.length - 1; i >= 0; i--) {

                if (bitString[i] == '1') {
                    nextSet.add(isAdded.element);
                }
                if (isAdded !=null) {
                    isAdded = isAdded.prev;
                }
            }
            idx++;
            return nextSet;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}
