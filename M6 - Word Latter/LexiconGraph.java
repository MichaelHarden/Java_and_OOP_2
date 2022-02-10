import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;


public class LexiconGraph {

    private HashMap<String, TreeSet<String>> buckets = new HashMap<String, TreeSet<String>>();
    private HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();

    public void add(String word) {
        char[] letters = word.toCharArray();

        // declared as a for loop opposed to a for each loop to have acces to the index
        for (int i = 0; i < letters.length; i++) {

            String reconstructedWord = "";

            for (int c =0; c < letters.length; c++) {
                if (c == i) {
                     reconstructedWord += '_';
                } else {
                    reconstructedWord += letters[c];
                }
             }

            if (buckets.containsKey(reconstructedWord)) {
                buckets.get(reconstructedWord).add(word);
            } else {
                TreeSet<String> bucket = new TreeSet<String>();
                bucket.add(word);
                buckets.put(reconstructedWord, bucket);
            }
        }
        HashSet<String> neighbors = new HashSet<String>();
        graph.put(word, neighbors);
    }

    public HashMap<String, HashSet<String>> createGraph() {
        
        for (Map.Entry<String, HashSet<String>> entry : graph.entrySet()) {
            char[] keyLetters = entry.getKey().toCharArray();

            for(int i = 0; i < keyLetters.length; i++) {

                String reconstructedWord = "";

                for (int c =0; c < keyLetters.length; c++) {
                    if (c == i) {
                         reconstructedWord += '_';
                    } else {
                        reconstructedWord += keyLetters[c];
                    }
                 }
                TreeSet<String> bucketTree = buckets.get(reconstructedWord);

                Iterator<String> bucketItr = bucketTree.iterator();
                while(bucketItr.hasNext()) {
                    String nxt = bucketItr.next();
                    if (!(nxt.equals(entry.getKey()))){
                        entry.getValue().add(nxt);
                    }
                }
            }
        }
        return graph;
    }

    public int size() {
        return graph.size();
    }

    public List<String> breadthFirstSearch(String start, String end) {
        
        HashMap<String, String> orderVisited = findPath(start, end);

        return reconstructShortestPath(start, end, orderVisited);
        
    }

    private HashMap<String, String> findPath(String start, String end) {
        
        ConcurrentLinkedQueue<String> upNext = new ConcurrentLinkedQueue<String>();
        HashSet<String> isVisitied = new HashSet<String>();
        HashMap<String, String> orderVisited = new HashMap<String, String>();
        upNext.add(start);
        isVisitied.add(start);

        while(!orderVisited.containsKey(end) && !upNext.isEmpty()) {
            String currentNode = upNext.poll();

            HashSet<String> neighborSet = graph.get(currentNode);
            for (String neighbor : neighborSet) {
                if (!isVisitied.contains(neighbor)) {
                    isVisitied.add((neighbor));
                    upNext.add(neighbor);
                    orderVisited.put(neighbor, currentNode);
                }
            }

        }

        return orderVisited;
    }

    private List<String> reconstructShortestPath(String start, String end, HashMap<String, String> orderVisited) {
        
        LinkedList<String> path = new LinkedList<String>();

        if (orderVisited.containsKey(end)) {

            String nextNode = end;
    
            while (nextNode != start) {
                path.add(0, nextNode);
                nextNode = orderVisited.get(nextNode);
            }
            path.add(0, nextNode);
        }
        return path;
    }

    public int getHammingDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return -1;
        }
        char[] w1 = word1.toCharArray();
        char [] w2 = word2.toCharArray();

        int dis = 0;
        for(int i = 0; i < w1.length; i++) {
            if (w1[i] !=  w2[i]) {
                dis++;
            }
        }
        return dis;
    }

    public boolean containsWord(String word) {
        return graph.containsKey(word);
    }

    public List<String> getNeighbors(String word) {
        Iterator<String> neighborIterator = graph.get(word).iterator();
        List<String> nieghbors = new ArrayList<String>();
        
        while (neighborIterator.hasNext()) {
            nieghbors.add(neighborIterator.next());
        }

        return nieghbors;
    }

    public boolean isWordLadder(List<String> ladder) {
        
        for (int i = 0; i < ladder.size(); i++) {
            if (!(graph.containsKey(ladder.get(i).toUpperCase()))) {

                return false;
            } 
            if (i == ladder.size() - 1) {

                return true;
            }
            if (!(graph.get(ladder.get(i).toUpperCase()).contains(ladder.get(i+1).toUpperCase()))) {

                return false;           
            }
        }
        return false;
    }

   
    public String toString() {
        String str = "";
        int num = 0;
        for (Map.Entry<String, HashSet<String>> entry : graph.entrySet()) {
            
            str += num + ". " + entry.getKey() + "  :  [";
            num++;
            Iterator<String> setItr = entry.getValue().iterator();

            while (setItr.hasNext()) {

                str += setItr.next();
                
                if (setItr.hasNext()) {
                    str += ", ";
                }
            }

            str += "]\n";
        }
        return str;
    }
}

