import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;

public class WordSearchGameClient implements WordSearchGame{

    private Board board = new Board(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H", "N", "B", "O", "Q", "T", "T", "Y"});
    
    // Where we will store our lexicon data set
    private RedBlackTree lexiconTree = new RedBlackTree();
    private boolean lexiconCalled = false;

    public void loadLexicon(String fileName)  {

        if (fileName == null) { throw new IllegalArgumentException(); }

        try {
            Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            
            lexiconCalled = true;
            while (fileScanner.hasNext()) {
                
                String word = fileScanner.next().toUpperCase();
                
                lexiconTree.add(word);
                if(lexiconTree.getSize() % 1000 == 0) {
                    System.out.println("status: " + lexiconTree.getSize());
                }

                fileScanner.nextLine();
            }   
            System.out.println("\n" + fileName + " was loaded successfully\n");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
        
    }

    public void setBoard(String[] letterArray) {

        if (letterArray == null || isNotSquare(letterArray)) {
            throw new IllegalArgumentException();
        }
    
        board = new Board(letterArray);
    }

    private boolean isNotSquare(String[] array) {
        return(((int)Math.sqrt(array.length)) * ((int)Math.sqrt(array.length))) != array.length;
    }

    public String getBoard() {
        return board.toString();
    }

    public SortedSet<String> getAllScorableWords(int minimumWordLength) {

        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        } else if (!lexiconCalled) {
            throw new IllegalStateException();
        }

        return board.getAllScorableWordsOnBoard(lexiconTree, minimumWordLength);
    }

    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        } else if (!lexiconCalled) {
            throw new IllegalStateException();
        }
        
        //all words on board
        SortedSet<String> sss = getAllScorableWords(minimumWordLength);
            
        int total = 0;
        for (String w : words) {
            if (w.length() >= minimumWordLength && sss.contains(w.toUpperCase())) {
                total += w.length() - minimumWordLength + 1;    
            }
        }
        return total;
    }

    public boolean isValidWord(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        } else if (!lexiconCalled) {
            throw new IllegalStateException();
        }

        return lexiconTree.search(wordToCheck.toUpperCase());

    }

    public boolean isValidPrefix(String prefixToCheck) {
        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        } else if (!lexiconCalled) {
            throw new IllegalStateException();
        }

        return lexiconTree.searchPrefix(prefixToCheck.toUpperCase());

    }

    public List<Integer> isOnBoard(String wordToCheck) {

        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        } else if (!lexiconCalled) {
            throw new IllegalStateException();
        }
        
        return board.isWordOnBoard(wordToCheck.toUpperCase());
        
    }

    
    
}
