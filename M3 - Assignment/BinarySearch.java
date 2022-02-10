    import java.util.Comparator;

public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();  
        }

        int left = 0;
        int right = a.length - 1;
        int leftMost = a.length;

        while(left <= right) {
            int mid = (left + right) / 2;
            int comparison = comparator.compare(a[mid], key);

            if (comparison == 0) {
                leftMost = mid < leftMost ? mid : leftMost;
                right = mid - 1;
            } else if (comparison < 0) {
                left = mid + 1;
            } else if (comparison > 0) {
                right = mid - 1;
            }
        }

        return (leftMost < a.length) ? leftMost : -1;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();  
        }

        int left = 0;
        int right = a.length-1;
        int rightMost = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = comparator.compare(a[mid], key);
            

            if (comparison == 0) {
                rightMost = mid > rightMost ? mid : rightMost;
                left = mid + 1;
            } else if (comparison < 0) {
                left = mid + 1;
            } else if (comparison > 0) {
                right = mid - 1;
            }
            
        }
        return rightMost;
    }
}
