import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Client2 {

    public static void main(String[] args) throws FileNotFoundException {

        Doublets ladder = new Doublets(new FileInputStream("sample.txt"));
        System.out.println(ladder);

        List<String> ladder1 = new LinkedList<String>();
        ladder1.add("cat");
        ladder1.add("cut");
        ladder1.add("cup");
        ladder1.add("cop");
        ladder1.add("bop");

        System.out.println(ladder.isWordLadder(ladder1));
        
        
    }
}
