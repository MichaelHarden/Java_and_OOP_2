public class Test {
    public static void main(String[] args) {
        autocompleteTest1();
        autocompleteTest2();
        autocompleteTest3();
        autocompleteTest4();
        autocompleteTest5();
        autocompleteTest6();
        // firstIndexOfTest1();
        // firstIndexOfTest2();
        // firstIndexOfTest3();
        // firstIndexOfTest4();
        // firstIndexOfTest5();
        // firstIndexOfTest6();
        // firstIndexOfTest7();
        autoTest();
        
        //autoTest();
        
    }

    private static void autoTest() {
        Term a1 = new Term("a", 4);
        Term a2 = new Term("ab", 8);
        Term a3 = new Term("abc", 10);
        Term a4 = new Term("abcd", 2);
        Term a5 = new Term("abcde", 6);
        Term[] as = {a3, a1, a2, a4, a5};

        Autocomplete auto = new Autocomplete(as);
        Term[] t = auto.allMatches("ab");
        for (Term term : t) {
            System.out.println(term);
        }
    }








    /*** FIRST-INDEX-OF TEST ***/


    private static void firstIndexOfTest1() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("a", 1);
        Term a3 = new Term("a", 1);
        Term a4 = new Term("a", 1);
        Term a5 = new Term("a", 1);
        Term a6 = new Term("a", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("a", 5), Term.byPrefixOrder(1));
        System.out.println("expected 0 -> answer " + idx + "\t");
    }
    private static void firstIndexOfTest2() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("a", 1);
        Term a3 = new Term("a", 1);
        Term a4 = new Term("a", 1);
        Term a5 = new Term("a", 1);
        Term a6 = new Term("a", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("b", 5), Term.byPrefixOrder(1));
        System.out.println("expected -1 -> answer " + idx + "\t");
    }

    private static void firstIndexOfTest3() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("b", 1);
        Term a3 = new Term("b", 1);
        Term a4 = new Term("b", 1);
        Term a5 = new Term("a", 1);
        Term a6 = new Term("a", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("b", 5), Term.byPrefixOrder(1));
        System.out.println("expected 1 -> answer " + idx + "\t");
    }

    private static void firstIndexOfTest4() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("a", 1);
        Term a3 = new Term("a", 1);
        Term a4 = new Term("b", 1);
        Term a5 = new Term("b", 1);
        Term a6 = new Term("b", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("b", 5), Term.byPrefixOrder(1));
        System.out.println("expected 3 -> answer " + idx + "\t");
    }

    private static void firstIndexOfTest5() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("b", 1);
        Term a3 = new Term("b", 1);
        Term a4 = new Term("c", 1);
        Term a5 = new Term("d", 1);
        Term a6 = new Term("d", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("c", 5), Term.byPrefixOrder(1));
        System.out.println("expected 3 -> answer " + idx + "\t");
    }
    private static void firstIndexOfTest6() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("b", 1);
        Term a3 = new Term("b", 1);
        Term a4 = new Term("c", 1);
        Term a5 = new Term("d", 1);
        Term a6 = new Term("e", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("e", 5), Term.byPrefixOrder(1));
        System.out.println("expected 5 -> answer " + idx + "\t");
    }
    private static void firstIndexOfTest7() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("b", 1);
        Term a3 = new Term("b", 1);
        Term a4 = new Term("c", 1);
        Term a5 = new Term("d", 1);
        Term a6 = new Term("d", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("a", 5), Term.byPrefixOrder(1));
        System.out.println("expected 0 -> answer " + idx + "\t");
    }

    private static void firstIndexOfTest8() {
        Term a1 = new Term("a", 1);
        Term a2 = new Term("a", 1);
        Term a3 = new Term("a", 1);
        Term a4 = new Term("a", 1);
        Term a5 = new Term("a", 1);
        Term a6 = new Term("a", 1);
        Term[] as = {a1, a2, a3, a4, a5, a6};

        int idx = BinarySearch.firstIndexOf(as, new Term("a", 5), Term.byPrefixOrder(1));
        System.out.println("expected 0 -> answer " + idx + "\t");
    }

    /*** AUTO-COMPLETE TEST ***/

    private static void autocompleteTest1() {
        Term cat = new Term("cat", 51);
        Term dog = new Term("dog", 102);
        Term bear = new Term("bear", 19);
        Term down = new Term("down", 62);
        Term[] stuff = { cat, dog, bear, down };

        Autocomplete auto = new Autocomplete(stuff);
    }

    private static void autocompleteTest2() {
        Term cat = new Term("", 51);
        Term dog = new Term("dog", 102);
        Term bear = new Term("bear", 19);
        Term down = new Term("down", 62);
        Term[] stuff = { cat, dog, bear, down };

        Autocomplete auto = new Autocomplete(stuff);
    }

    private static void autocompleteTest3() {
        Term cat = new Term("cat", 51);
        Term dog = new Term("dog", 0);
        Term bear = new Term("bear", 19);
        Term down = new Term("down", 62);
        Term[] stuff = { cat, dog, bear, down };

        Autocomplete auto = new Autocomplete(stuff);
    }

    private static void autocompleteTest4() {
        Term cat = new Term("cat", 51);
        Term dog = new Term("dog", 102);
        Term bear = new Term("bear", 19);
        Term down = new Term("down", 62);
        Term[] stuff = { cat, dog, bear, down };

        Autocomplete auto = new Autocomplete(stuff);
        Term[] a = auto.allMatches("d");

        for (Term n : a) {
            System.out.println(n);
        }
    }

    private static void autocompleteTest5() {
        Term sz = new Term("sz", 3);
        Term f = new Term("f", 5);
        Term b = new Term("b", 15);
        Term g = new Term("g", 8);
        Term z = new Term("z", 3);
        Term y = new Term("y", 9);
        Term sp = new Term("sp", 19);
        Term h = new Term("h", 52);
        Term l = new Term("l", 4);
        Term sm = new Term("sm", 1);
        Term p = new Term("p", 23);
        Term k = new Term("k", 71);
        Term[] terms = {sz, f, b, g, z, y, sp, h, l, sm, p, k};

        
        

        Autocomplete t = new Autocomplete(terms);
        Term[] ts = t.allMatches("s");

        for (Term erms : ts) {
            System.out.println(erms);
        }
    }

    private static void autocompleteTest6() {
        Term sz = new Term("sz", 3);
        Term f = new Term("", 5);
        Term b = new Term("b", 15);
        Term g = new Term("g", 8);
        Term z = new Term("", 0);
        Term y = new Term("y", 9);
        Term sp = new Term("sp", 19);
        Term h = new Term("h", 52);
        Term l = new Term("l", 0);
        Term sm = new Term("sm", 1);
        Term p = new Term("p", 23);
        Term k = new Term("k", 71);
        Term[] terms = {sz, f, b, g, z, y, sp, h, l, sm, p, k};

        
        

        Autocomplete t = new Autocomplete(terms);
        Term[] ts = t.allMatches("s");

        for (Term erms : ts) {
            System.out.println(erms);
        }
    }

    
}
