import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Board {


    private Position[] positions;
    
    private RedBlackTree wordTreeSet;
    private SortedSet<String> legalWords = new TreeSet<String>();
        
    private int minlength;

    /**
     * Constructs a new board and calculates all Position values for every item in the board.
     * @param board
     */
    public Board(String[] board) {

        positions = new Position[board.length];

        int index = 0;
        for (String letter : board) {
            positions[index] = new Position(index, letter.toUpperCase(), board.length);
            index++;
        }
    }

    /**
     * returns the Position for .this
     * @return
     */
    public Position[] getPositions() {
        return positions;
    }

    /**
     * Teturns all neighbors that a position will have.
     * @param index
     * @return
     */
    public int[] getNeighborsForPosition(int index) {
        if(!(index < positions.length)) {
            throw new IllegalArgumentException();
        }
        return positions[index].neighbors;
    }

    /**
     * Returns a value at the index of the board that is passed in
     * @param index
     * @return
     */
    public String getValueForPosition(int index){
        if(!(index < positions.length)) {
            throw new IllegalArgumentException();
        }
        return positions[index].value;
    }

    public SortedSet<String> getAllScorableWordsOnBoard(RedBlackTree wordTree, int minimumWordLength) {
        wordTreeSet = wordTree;
        minlength = minimumWordLength;
        for (Position position : positions) {
            ArrayList<Integer> array = new ArrayList<Integer>();
            array.add(position.index);

            recursive( array, position, "");
        }
        return legalWords;
    }

    private void recursive(ArrayList<Integer> walk, Position currentNode, String word) {
       
        word += currentNode.value;
        if (!wordTreeSet.searchPrefix(word)) {
            return;
        }
        for (int neighbor : currentNode.neighbors) {
            if(!walk.contains(neighbor)) {
                walk.add(neighbor);
                recursive(walk, positions[neighbor], word);
                walk.remove(walk.size()-1);
                // word = word.substring(0, word.length()-1);
            }
        }
        if (!(word.length() < minlength)  && wordTreeSet.search(word)) {
            legalWords.add(word);
        }
        return;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for(int i = 0; i < positions.length; i++) {
            result.append(positions[i].value);
            if (( (i+1) % (int)(Math.sqrt(positions.length) ) != 0) ) {
                result.append(", ");
                continue;
            }
            result.append("]\n[");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString(); //+ toStringConnections();
    }

    private String toStringConnections() {
        StringBuilder result = new StringBuilder();
        for (Position position : positions) {
            result.append( position.value + "  [");
            for (int neighbor : position.neighbors) {
                result.append(positions[neighbor].value + ", ");
            }
            result.deleteCharAt(result.length()-1);
            result.append("]\n");
        }
        return result.toString();
    }

    public List<Integer> isWordOnBoard(String word) {

        char[] letters = word.toCharArray();

        for (Position position : positions) {
            if (position.value.toCharArray()[0] != letters[0]) {
                continue;
            }
            List<Integer> path = recursiveFindWord(position.index, letters, 1, new ArrayList<Integer>());
            if (path == null) {
                continue;
            } else {
                return path;
            }
        }
        return new ArrayList<Integer>();
    }

    private List<Integer> recursiveFindWord(int position, char[] wordChars, int cIdx, List<Integer> path) {
        path.add(position);
        String wordFromPath = "";
        for(int l : path) {
            wordFromPath += positions[l].value;
        }
        String wordFromChar = "";
        for(char c : wordChars) {
            wordFromChar += c;
        }
        if (wordFromPath.equals(wordFromChar)) {
            return path;
        }

        for (int i : positions[position].neighbors) {
            if ( (positions[i].value.toCharArray()[0] == wordChars[cIdx]) && !(path.contains(i))) {
                return recursiveFindWord(i, wordChars, cIdx + 1, path);
            }
        }
        return null;
    }
    /**
     * Position class describes a position on a board by calculating each neighboring node and 
     * storing there index's, and storing the value of itself.
     */
    private class Position {

        private int index;
        int[] neighbors;
        String value;

        /**
         * Creates a new Position
         * 
         * @param index the index of the current node.
         * @param value the value the current node stores.
         * @param arrayLen the length of the array that stores the board.
         */
        public Position(int index, String value, int arrayLen) {
            this.index = index;
            this.value = value;
            neighbors = calculateNeighbors(arrayLen);
        }

        /**
         * Using the index, finds all indeces of the neighboring positions, and stores them in neighbors
         * @param arrayLen
         * @return
         */
        private int[] calculateNeighbors(int arrayLen) {
            int[] n = calculateNumOfNeighbors(arrayLen);
            int sideLen = (int)Math.sqrt(arrayLen);

            int row = index / sideLen;
            int column = index - (sideLen * row);
            int ni = 0;

            for (int i = -1; i < 2; i++) {
                if((row + i < 0) || (row + i >= sideLen)) {
                    continue;
                }
                for(int j = -1; j < 2; j++) {
                    if((column + j < 0) || (column + j >= sideLen)) {
                        continue;
                    }
                    if( !((row + i == row) && (column + j == column)) ) {
                        n[ni] = (column + j) + ((row + i) * sideLen);
                        ni++;
                    }
                }
            }            
            return n;
        }

        /**
         * Calculates how many neighbors a positionwill have.
         * @param arrayLen
         * @return
         */
        private int[] calculateNumOfNeighbors(int arrayLen) {
            int[] numOf;
            int side = (int)Math.sqrt(arrayLen);
            if ((index < side) || (index >= (arrayLen - side) && (index < arrayLen))) {
                if ((index % side == 0) || (index % side == side - 1)) {
                    numOf = new int[3];
                } else {
                    numOf = new int[5];
                }
            } else if ((index % side == 0) || (index % side == side - 1)) {
                numOf = new int[5];
            } else {
                numOf = new int[8];
            }
            return numOf;
        }
    }
}
