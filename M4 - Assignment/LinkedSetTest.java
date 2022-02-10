
public class LinkedSetTest {
    public static void main(String[] args) {
        LinkedSet<Integer> setA = new LinkedSet<Integer>();
        
        for(int i = 0; i < 100; i++) {
            System.out.println("adding " + i + "\t" + setA.add(i));
        }

        System.out.print("\n\n\n\n");
        for(int i = 99; i >= 0; i--) {
            System.out.println("removing " + i + "\t" + setA.remove(i));
        }
    
        for(int i = 0; i < 100; i++) {
            System.out.println("adding " + i + "\t" + setA.add(i));
        }

        System.out.print("\n\n\n\n");

        for(int i = 0; i < 100; i++) {
            System.out.println("adding " + i + "\t" + setA.add(i));
        }

        System.out.print("\n\n\n\n");

        
    }
}
