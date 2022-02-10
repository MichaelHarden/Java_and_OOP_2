/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   James Michael Harden (jmh0212@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  January 28th, 2021 
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
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a) {

        //check for valid inputs
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }

        //set the current minimum value
        int currentMin = Integer.MAX_VALUE;

        //for every nunmber in a[] compare it to currentMin. if num is less, set it as the new currentMax
        for (int num : a) {
            if (num < currentMin) {
                currentMin = num;
            }
        }

        //return the minimum value found
        return currentMin;
    }

    
    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a) {

        //chec for valid inputs
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        
        //set the current max value
        int currenMax = Integer.MIN_VALUE;
        
        //for every nunmber in a[] compare it to currentMax. if num is more, set it as the new currenMax
        for (int num : a) {
            if (num > currenMax) {
                currenMax = num;
            }
        }

        //return the maximum value found
        return currenMax;
    }

    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmin(int[] a, int k) {

        //check for valid inputs
        if (a == null){ throw new IllegalArgumentException(); }

        //create a copy of a[] to sort and sort it
        int[] array = a.clone();
        MergeSort.mergeSort(array);

        // initialize kth to keep track of the current kth size poition
        int kth = 1;

        //initialize two pointers to scan though the sorted array
        int i = 0, j = 1;

        //while the current k value is less than the targeted k value and while the pointer j is not out of bounds, keep going
        while ((kth < k) && (j < array.length)){

            //if array[i] != array[j] due to the nature of a sorted list j will be bigger so we found the kth + 1 positioned element
            if (array[i] != array[j]) {

                //set i to j, increment j, and add 1 to kth
                i = j; 
                j++;
                kth++;

            //if array[i] == array[j] then the element at array[j] is at the same kth position as array[i] so only move j forward
            } else { j++; }
        }

        //check for more valid arguments with new found datal
        if ((kth == k) && (array.length != 0)) {

            //return the desired kth element
            return array[i];
        }
        throw new IllegalArgumentException();
    }

    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmax(int[] a, int k) {

        //check for valid inputs
        if (a == null){ throw new IllegalArgumentException(); }

        //create a copy of a[] to sort and sort it
        int[] array = a.clone();
        MergeSort.mergeSort(array);

        // initialize kth to keep track of the current kth size poition
        int kth = 1;

        //initialize two pointers to scan though the sorted array
        int i = array.length - 1, j = array.length - 2;

        //while the current k value is less than the targeted k value and while the pointer j is not out of bounds, keep going
        while ((kth < k) && (j > -1)) {

            //if array[i] != array[j] due to the nature of a sorted list j will be smaller so we found the kth + 1 positioned element
            if (array[i] != array[j]) {

                //set i to j, increment j, and add 1 to kth
                i = j;
                j--;
                kth++;

            //if array[i] == array[j] then the element at array[j] is at the same kth position as array[i] so only move j forward    
            } else { j--; }
        }

        //check for more valid inputs with new found datal
        if ((kth == k) && (array.length != 0)) {

            //return the desired kth element
            return array[i];
        }

        throw new IllegalArgumentException();
    }

    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high) {

        //check for valid inputs
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        
        // initialize len which stores the cound of how many numbers are within the range
        int len = 0;
        for (int num : a) {
            if ((num >= low) && (num <= high)) {
                len++;
            }
        }

        //create an array of length len to store each number that falls within range
        int[] range = new int[len];

        //add numbers to new array that fall in the range
        int j = 0;
        for (int num : a) {
            if ((num >= low) && (num <= high)) {
                range[j] = num;
                j++;
            }
        }

        //return new array
        return range;
    }

    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key) {

        //check for valid inputs
        if (a == null || a.length == 0) { throw new IllegalArgumentException();}

        //initialize store of keys validity
        boolean isKeyValid = false;

        //set current ceiling for each number to be compared to
        int ceiling = Integer.MAX_VALUE;

        //for each num, compare it to key and ceiling. if num is less than ceiling and more than key, then set it as the new ceiling
        for (int num : a) {
            if ((num >= key) && (num < ceiling)) {
                isKeyValid = true;
                ceiling = num;
            }
        }
        
        //check for validity of key
        if(!isKeyValid) { throw new IllegalArgumentException();}

        //returns the ceiling found in the list
        return ceiling;
    }

    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key) {

        //check for valid inputs
        if (a == null || a.length == 0) { throw new IllegalArgumentException();}

        //initialize store of keys validity
        boolean isKeyValid = false;
         //set current floor for each number to be compared to
        int floor = Integer.MIN_VALUE;


        //for each num, compare it to key and floor. if num is more than floor and less than key, then set it as the new floor
        for (int num : a) {
            if ((num <= key) && (num > floor)) {
                isKeyValid = true;
                floor = num;
            }
        }
        
         //check for validity of key
        if(!isKeyValid) { throw new IllegalArgumentException();}

         //returns the floor found in the list
        return floor;
    }

    /**
     * MergeSort is a Inner Class to Selector and contains a helper method 
     * to sort an array and is used in the kmin and kmax methods.
     *
     */
    private static class MergeSort {

        /**
         * merge is a helper method to the mergeSort method which sorts 
         * an array and is used in the kmin and kmax methods.
         */
        private static void merge(int[] arr, int left, int mid, int right) {

            int subArr1 = mid - left + 1;
            int subArr2 = right - mid;

            int[] leftArray = new int[subArr1];
            int[] rightArray = new int[subArr2];

            for (int i = 0; i < subArr1; i++) {
                leftArray[i] = arr[i + left];
            }
            for (int j = 0; j < subArr2; j++) {
                rightArray[j] = arr[j + mid + 1];
            }

            int i = 0, j = 0;

            int k = left;
            while (i < subArr1 && j < subArr2) {
                if (leftArray[i] < rightArray[j]) {
                    arr[k] = leftArray[i];
                    i++;
                } else {
                    arr[k] = rightArray[j];
                    j++;
                }
                k++;
            }

            while (i < subArr1) {
                arr[k] = leftArray[i];
                i++;
                k++;
            }

            while (j < subArr2) {
                arr[k] = rightArray[j];
                j++;
                k++;
            }
        }
        
        /**
         * the sort method is a helper method to merge sort and it sorts the values of an array and works alongside merge 
         * and is a recursive algorithm.
         * 
         * @param a array to be sorted
         * @param l left bound of array to be sorted
         * @param r right bound of array to be sorted
         */
        private static void sort(int[] arr, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;

                sort(arr, left, mid);
                sort(arr, mid + 1, right);

                merge(arr, left, mid, right);
            }
        }
    
        /**
         * mergeSort is the methed called to sort an array
         * @param array the array to be sorted.
         */
        public static void mergeSort(int[] array) {
            sort(array, 0, array.length - 1);
        }
    }
}