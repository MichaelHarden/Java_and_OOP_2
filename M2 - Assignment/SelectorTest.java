import java.util.ArrayList;

public class SelectorTest {
    public static void main(String[] args) {

        ArrayList<String> als = new ArrayList<String>();
        
        String a = "a";
        String aa = "A";
        String b = "b";
        String c = "h";
        String d = "y";
        String e = "q";
        String f = "e";
        String g = "a";
        String h = "z";

        als.add(a);
        als.add(b);
        als.add(c);
        als.add(d);
        als.add(e);
        als.add(f);
        als.add(g);
        als.add(h);
        // als.add(aa);

           
        System.out.println(Selector.ceiling(als, "f", new Stringer()) );

        ArrayList<Integer> ali = new ArrayList<Integer>();
        
        int a1 = 1;
        int b2 = 3;
        int c3 = -3;
        int d4 = 4;
        int d5 = 0;

        ali.add(a1);
        ali.add(b2);
        ali.add(c3);
        ali.add(d4);
        ali.add(d5);
           
        System.out.println(Selector.ceiling(ali, 2, new Inter()) );
    
    }
    
}

