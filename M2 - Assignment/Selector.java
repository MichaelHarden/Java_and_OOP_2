import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  James Michael Harden (jmh0212@auburn.edu)
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
    private Selector() { }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }
        

        T min = null;

        Iterator<T> iterator = coll.iterator();
        
        while (iterator.hasNext()) {
            T nxt = iterator.next();

            
            if (min == null) {
                min = nxt;
                continue;
            }
            
            int compare = comp.compare(min, nxt);
            if(compare > 0) {
                min = nxt;
            } 
        }
        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }

        T max = null;

        Iterator<T> iterator = coll.iterator();
        
        while (iterator.hasNext()) {
            T nxt = iterator.next();

            
            if (max == null) {
                max = nxt;
                continue;
            }
            
            int compare = comp.compare(max, nxt);
            if(compare < 0) {
                max = nxt;
            } 
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }

        List<T> copy = new ArrayList<>(coll);
        java.util.Collections.sort(copy, comp);

        int kth = 1;

        int i = 0, j = 1;

        while ((kth < k) && (j < copy.size())) {

            if (copy.get(i) != copy.get(j)) {
                i = j;
                j++;
                kth++;
            } else { j++; }
        }

        if ((kth == k) && (copy.size() != 0)) {
            return copy.get(i);
        }
        throw new NoSuchElementException();
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }

        List<T> copy = new ArrayList<>(coll);
        java.util.Collections.sort(copy, comp);

        int kth = 1;

        int i = copy.size() - 1, j = copy.size() -2;

        while ((kth < k) && (j > -1)) {
            if (copy.get(i) != copy.get(j)) {
                i = j;
                j--;
                kth++;
            } else { j--; }
        }

        if ((kth == k) && (copy.size() != 0)) {
            return copy.get(i);
        }
        throw new NoSuchElementException();
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }
        Iterator<T> iterator = coll.iterator();
        
        ArrayList<T> range = new ArrayList<T>();

        while (iterator.hasNext()) {
            T nxt = iterator.next();

            boolean isInRange = (comp.compare(nxt, low) >= 0) && (comp.compare(nxt, high) <= 0);

            if (isInRange) {
                range.add(nxt);
            }
        }
        if (range.size() == 0) {
            throw new NoSuchElementException();
        }
        return range;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }

        Iterator<T> iterator = coll.iterator();

        T ceiling = null;
        while (iterator.hasNext()) {
            T nxt = iterator.next();

            

            boolean isGreaterThanKey = comp.compare(nxt, key) >= 0;
            if (isGreaterThanKey) {
                if (ceiling == null) {
                    ceiling = nxt;
                    continue;
                } 

                boolean isLessThanFloor = comp.compare(nxt, ceiling) < 0;
                if (isLessThanFloor) {
                ceiling = nxt;
                }
            }   
        }
        if (ceiling == null) {
            throw new NoSuchElementException();
        }
        return ceiling;
        
    }
    


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw  new IllegalArgumentException();
        } else if (coll.size() == 0) {
            throw new NoSuchElementException();
        }

        Iterator<T> iterator = coll.iterator();

        T floor = null;
        while (iterator.hasNext()) {
            T nxt = iterator.next();

            boolean isLessThanKey = (comp.compare(nxt, key) <= 0) ? true: false;
            if (isLessThanKey) {
                if (floor == null) {
                    floor = nxt;
                    continue;
                } 

                boolean isGreaterThanFloor = (comp.compare(nxt, floor) > 0) ? true : false;
                if (isGreaterThanFloor) {
                floor = nxt;
                }
            }   
        }
        if (floor == null) {
            throw new NoSuchElementException();
        }
        return floor;
    }
}
