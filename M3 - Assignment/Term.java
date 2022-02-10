import java.util.Comparator;

public class Term implements Comparable<Term> {

    private String query = "";
    private long weight = 0;
    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) { 
        // invalid input checks
        if (query == null) {
            throw new NullPointerException();
        } else if (weight < 0) {
            throw new IllegalArgumentException();
        }
        
        //assignments
        this.query = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {
        return new ByDescendingWeightComparator();
    }
    private static class ByDescendingWeightComparator implements Comparator<Term> {
        @Override
        public int compare(Term o1, Term o2) {
            return Long.compare(o2.weight, o1.weight);
        }

    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        
        return new ByPrefixComparator(length);
    }
    private static class ByPrefixComparator implements Comparator<Term> {

        private int length = 1;

        public ByPrefixComparator(int length) {
            this.length = length;
        }

        @Override
        public int compare(Term o1, Term o2) {
            String s1 = o1.query.substring(0, Math.min(o1.query.length(), length));
            String s2 = o2.query.substring(0, Math.min(o2.query.length(), length));

            return s1.compareTo(s2);
        }
        
    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return this.query.compareTo(other.query);
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){
        return query + "\t" + weight;
    }

}