public class SelectorTester {
    public static void main(String[] args) {
        int[] a = {3, 6, 8, 9, 10};
        int[] b = {4, 6, 2, 4, 8};
        int[] c = {1};
        int[] d = {1, 1, 1};
        int[] e = {};
        int[] f = null;

        //MIN
        // System.out.println("MIN");
        System.out.println("Expected 3 : " + Selector.min(a));
        // System.out.println("Expected 2 : " + Selector.min(b));
        // System.out.println("Expected 1 : " + Selector.min(c));
        // System.out.println("Expected 1 : " + Selector.min(d));
        // System.out.println("Expected Il : " + Selector.min(e));
        // System.out.println("Expected - : " + Selector.min(f));


        // //MAX
        // System.out.println("\nMAX");
        System.out.println("Expected 10 : " + Selector.max(a));
        // System.out.println("Expected 8 : " + Selector.max(b));
        // System.out.println("Expected 1 : " + Selector.max(c));
        // System.out.println("Expected 1 : " + Selector.max(d));
        // System.out.println("Expected - : " + Selector.max(f));

        //CEILING
        // System.out.println("\nCEILING");
        System.out.println("Expected 8 : " + Selector.ceiling(a, 7));
        // System.out.println("Expected 6 : " + Selector.ceiling(b, 6));
        // System.out.println("Expected MAX : " + Selector.ceiling(c, 2));
        // System.out.println("Expected 1 : " + Selector.ceiling(d, -1));
        // System.out.println("Expected MAX : " + Selector.ceiling(e, 2));
        // System.out.println("Expected MAX : " + Selector.ceiling(f, 2));

        // //FLOOR
        // System.out.println("\nFLOOR");
        System.out.println("Expected 3 : " + Selector.floor(a, 5));
        // System.out.println("Expected 6 : " + Selector.floor(b, 6));
        // System.out.println("Expected MIN : " + Selector.floor(c, 0));
        // System.out.println("Expected 1 : " + Selector.floor(d, 2));
        // System.out.println("Expected MIN : " + Selector.floor(e, 2));
        // System.out.println("Expected MIN : " + Selector.floor(f, 2));

        //RANGE
        int[] a1 = Selector.range(a, 2, 7);
        for (int i : a1) {
            System.out.println(i);
        }

        System.out.println("Expected 8 : " + Selector.kmax(a, 3));
        System.out.println("Expected 8 : " + Selector.kmax(a, 2));


        // System.out.println("Expected 2 : " + Selector.kmin(f, 1));
    }

        
}