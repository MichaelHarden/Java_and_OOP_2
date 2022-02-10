import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////
    private HashSet<String> lexicon;


    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            //////////////////////////////////////
            // INSTANTIATE lexicon OBJECT HERE  //
            //////////////////////////////////////
            lexicon = new HashSet<String>();

            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));

            
            while (s.hasNext()) {

                
                String str = s.next();
                /////////////////////////////////////////////////////////////
                // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
                /////////////////////////////////////////////////////////////
                lexicon.add(str.toLowerCase());
                s.nextLine();
                
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    /**
     * Returns the number of words in the lexicon.
     */
    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    /**
     * Checks if parameter is contained in the lexicon.
     */
    @Override
    public boolean isWord(String str) {
        return lexicon.contains(str.toLowerCase());
    }

    /**
     * Calculates the hammindDistance between two words>
     */
    @Override
    public int getHammingDistance(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return -1;
        }
        
        char[] chars1 = str1.toLowerCase().toCharArray();
        char[] chars2 = str2.toLowerCase().toCharArray();
        
        int distance = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * Calculates all possible neigbors to the parameter word, and returns
     * all neighbors that are contained in the lexicon
     */
    @Override
    public List<String> getNeighbors(String word) {
        List<String> neighbors = new LinkedList<String>();
        for (int i = 0; i < word.length(); i++) {
            char[] letters = word.toLowerCase().toCharArray();
            for (int c = 'a'; c <= 'z'; c++) {
                if (word.toLowerCase().charAt(i) != (char) c) {
                    letters[i] = (char) c;
                    String neighbor = new String(letters);
                    if (isWord(neighbor)) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Checks if parameter word sequence is a valid word ladder.
     * 
     * A valid word ladder must have a hamming distance of 1 bewteen each
     * word, and all words must be constained in the lexicon.
     */
    @Override
    public boolean isWordLadder(List<String> sequence) {
        if (sequence.isEmpty() ) {
            return false;
        }
        Iterator<String> seqItr = sequence.iterator();
        String prev = null;
        while (seqItr.hasNext()) {
            if(prev == null) {
                prev = seqItr.next().toLowerCase();
                continue;
            }
            String nxt = seqItr.next().toLowerCase();
            if (getHammingDistance(prev, nxt) != 1 
                    || !isWord(nxt)) {
                return false;
            }
            prev = nxt;
        }
        return true;
    }

    /**
     * Returns the shortest path of words between the start and end word parameters.
     */
    @Override
    public List<String> getMinLadder(String start, String end) {
        if (start.length() != end.length()) {
            return new LinkedList<String>();
        }
        
        return bfs(start.toLowerCase(), end.toLowerCase());
    }

    // @Override
    // public String toString() {
    //     Iterator<String> lexItr = lexicon.iterator();
    //     String s = "";
    //     while (lexItr.hasNext()) {
    //         String nxt = lexItr.next();
    //         s += nxt + " : " + getNeighbors(nxt) + "\n";
    //     }
    //     return s;
    // }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////

    /**
     * Using breadth first search, finds the shorteset path between the start and end 
     * word parameters.
     */
    private List<String> bfs(String start, String end) {

        HashMap<String, String> visitOrder = findPath(start.toLowerCase(), end.toLowerCase());

        return reconstructPath(start.toLowerCase(), end.toLowerCase(), visitOrder);
    }

    /**
     * Helper method for bfs, finds the path to all words up to the end word 
     * in the lexicon.
     */
    private HashMap<String, String> findPath(String start, String end) {
        if (start.length() != end.length()) {
            return new HashMap<String, String>();
        }
        Queue<String> upNext = new ConcurrentLinkedQueue<String>();
        HashSet<String> isVisited = new HashSet<>();
        HashMap<String, String> order = new HashMap<String, String>();
        upNext.add(start);
        isVisited.add(start);
        order.put(start, start);

        while(!order.containsKey(end) & !upNext.isEmpty()) {
            String current = upNext.poll();
            List<String> currNeighbors = getNeighbors(current);
            for (String neighbor : currNeighbors) {
                if(!isVisited.contains((neighbor))) {
                    isVisited.add(neighbor);
                    upNext.add(neighbor);
                    order.put(neighbor, current);
                }
            }
        }
        return order;
    }

    /**
     * Helper method for bfs, Takes the return value of findPath() and constructs a path
     * from the start to the end.
     */
    private List<String> reconstructPath(String start, String end, HashMap<String, String> order) {
        List<String> path = new LinkedList<String>();
        if(order.containsKey(end)) {
            String current = end;
            while (current != start) {
                path.add(0, current);
                current = order.get(current);
            }
            path.add(0, start);
        }
        return path;
    }
}

