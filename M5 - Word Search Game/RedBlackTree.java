import java.util.ArrayList;

public class RedBlackTree {
    
    private Node root;
    private int size;

    public RedBlackTree() {}

    /**
     * The add method will locate the correct location to insert an element in to a tree.
     * If the tree already contains that element then nothing is added. 
     * 
     * Once the element has been added, rebalance is called to make any reballances to the
     * tree that need to be made.
     * 
     * @param element Element to add to the tree
     */
    public void add(String element) {

        Node node = new Node(element);

        if (root == null) {
            node.setBlack();
            root = node;
            return;
        }

        Node currentNode = this.root;
        Node possibleParent = currentNode;

        // Finds the insertion point and saves its parent in to possibleParent
        while (currentNode != null) {

            possibleParent = currentNode;
            int compare = element.compareTo(currentNode.value);

            if (compare > 0) {
                currentNode = currentNode.rightChild;

            } else {
                currentNode = currentNode.leftChild;
            }
        }

        // if the word to insert is already in the tree it will not be added. otherwise it is added to ether the left or right child
        node.parent = possibleParent;
        int compare2 = element.compareTo(possibleParent.value);
        if (compare2 > 0) {
            possibleParent.rightChild = node;
        } else if (compare2 < 0){
            possibleParent.leftChild = node;
        } else { return; }
        
        // the size of the tree is incremented and rebalanced.
        size++;
        rebalance(node);
    }

    // rebalances the tree based on Red Black principals
    private void rebalance(Node node) {
        
        while (node != this.root && node.parent.isRed()) {
            
            // checks for case 1, 2 or 3 as explained in class
            if (node.parent.parent.leftChild == node.parent) {
                Node uncle = node.parent.parent.rightChild;
                // case 1
                if (uncle != null && uncle.isRed()) {
                    node.parent.parent.setRed();
                    uncle.setBlack();
                    node.parent.setBlack();
                    node = node.parent.parent;
                } else { // case 2
                    if (node == node.parent.rightChild) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // case 2 and 3
                    node.parent.setBlack();
                    node.parent.parent.setRed();
                    rotateRight(node.parent.parent);
                }
            } else {
                // case 1, 4, and 5 as explained in class
                Node uncle = node.parent.parent.leftChild;
                if (uncle != null && uncle.isRed()) { // case 1
                    node.parent.setBlack();
                    uncle.setBlack();
                    node.parent.parent.setRed();
                    node = node.parent.parent;
                } else {
                    // case 4
                    if (node == node.parent.leftChild) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    // case 4 and 5
                    node.parent.setBlack();
                    node.parent.parent.setRed();
                    rotateLeft(node.parent.parent);
                }
            }
            this.root.setBlack();
        }
    } 

    private void rotateLeft(Node node) {

        Node sibling = node.rightChild;
        node.rightChild = sibling.leftChild;
        if (sibling.leftChild != null) {
            sibling.leftChild.parent = node;
        }
        sibling.parent = node.parent;
        if (node.parent == null) {
            this.root = sibling;
        } else {
            if (node == node.parent.leftChild) {
                node.parent.leftChild = sibling;
            } else {
                node.parent.rightChild = sibling;
            }
        }
        sibling.leftChild = node;
        node.parent = sibling;
    }

    private void rotateRight(Node node) {

        Node sibling = node.leftChild;
        node.leftChild = sibling.rightChild;
        if (sibling.rightChild != null) {
            sibling.rightChild.parent = node;
        }
        sibling.parent = node.parent;
        if (node.parent == null) {
            this.root = sibling;
        } else {
            if (node == node.parent.rightChild) {
                node.parent.rightChild = sibling;
            } else {
                node.parent.leftChild = sibling;
            }
        }
        sibling.rightChild = node;
        node.parent = sibling;
    }

    /**
     * The search method takes a given word and checks if the tree contains that word
     * 
     * @param element Word to check
     * @return True if the word is in the tree - False if not
     */
    public boolean search(String element) {
        Node currentNode = root;
        while (currentNode != null) {
            int compare = element.compareTo(currentNode.value);
            if (compare > 0) {
                currentNode = currentNode.rightChild;
            } else if (compare < 0) {
                currentNode = currentNode.leftChild;
            } else {
                return true;
            }
        }
        return false;
    }
    
    /**
     * The searchPrefix method takes a given prefix and checks if any words reside in the tree with 
     * that prefix. 
     * @param prefix prefix to check
     * @return True if there is a word(s) with the given prefix - False if not
     */
    public boolean searchPrefix(String prefix) {

        Node current = root;
        while (current != null) {
            if(current.value.startsWith(prefix)) {
                return true;
            }

            int comp = prefix.compareTo(current.value);
            if (comp > 0) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
                
            }
        }
        return false;
    }

    public int getSize() { return size; }

    public String toString() {
        return collectElements(root, new ArrayList<String>()).toString();
    }

    private ArrayList<String> collectElements(Node node, ArrayList<String> elements) {
        ArrayList<String> currentElements = elements;
        if (node.leftChild == null && node.rightChild == null) {
            elements.add(node.value);
            return elements;
        } 
        if (node.leftChild != null) {
            currentElements = collectElements(node.leftChild, currentElements);
        } 
        currentElements.add(node.value);
        if (node.rightChild != null) {
            currentElements = collectElements(node.rightChild, currentElements);
        }
        return currentElements;
    }

    private class Node {

        String value;
        Node leftChild, rightChild;
        Node parent;
        boolean red; // 0 is black, 1 is red


        public Node(String value) {
            this.value = value;
            leftChild = null;
            rightChild = null;
            parent = null;
            red = true;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed() {
            red = true;
        }
        
        public void setBlack() {
            red = false;
        }

        public String toString() {

            String s = "";
            s += "value : " + value;
            s += "\nleft child : " + leftChild.value;
            s += "\nright child : " + rightChild.value;
            return s;
        }
    }
}
