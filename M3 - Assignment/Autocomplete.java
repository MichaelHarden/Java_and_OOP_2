public class Autocomplete {

	private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
	public Autocomplete(Term[] terms) { 
		if (terms == null) {
			throw new NullPointerException();
		}
		

		this.terms = terms;
		java.util.Arrays.sort(this.terms);;

	}

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
	public Term[] allMatches(String prefix) {
		if (prefix == null) {
			throw new NullPointerException();
		}

		Term prefixTerm = new Term(prefix, 0);
		int leftIdx = BinarySearch.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
		int rightIdx = BinarySearch.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

		Term[] matches = new Term[rightIdx - leftIdx + 1];
		int i = leftIdx;
		int j = 0;
		while (i <= rightIdx) {
			matches[j] = terms[i];
			i++; 
			j++;
		}

		java.util.Arrays.sort(matches, Term.byDescendingWeightOrder());
		return matches;
	}
}
