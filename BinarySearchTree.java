package bst;

public class BinarySearchTree< E extends Comparable<E>> {

    private static class Node<F> {

        private final F value;
        private Node<F> left;
        private Node<F> right;

        private Node(F value) {
            this.value = value;
            left = null;
            right = null;
        }

    }

    private Node<E> root = null;

    /**
     * Inserts an object into this BinarySearchTree.
     *
     * @param obj item to be added
     * @return true if the object has been added and false otherwise
     */

    public boolean add(E obj) {

        // pre-condition:
        if (obj == null) {
            throw new NullPointerException("Illegal Argument");
        }

        // special case:
        if (root == null) {
            root = new Node<E>(obj);
            return true;
        }

        // general case:
        return add(obj, root);
    }

    private boolean add(E obj, Node<E> current) {

        boolean result;
        int test = obj.compareTo(current.value);

        if (test == 0) {
            result = false; // already exists, not added
        } else if (test < 0) {
            if (current.left == null) {
                current.left = new Node<E>(obj);
                result = true;
            } else {
                result = add(obj, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = new Node<E>(obj);
                result = true;
            } else {
                result = add(obj, current.right);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node<E> p) {
        if (p == null) {
            return "null";
        } else {
            return "(" + toString(p.left) + "," + p.value + "," + toString(p.right) + ")";
        }
    }
    
    private void visit( Node<E> current ) {
    	System.out.println( " " + current.value );
    }
    
    //Implementing the count method  
    
    //Search
    public boolean search(E toSearch){
       return search(root, toSearch);
    }
    private boolean search(Node<E> p, E toSearch){
       if (p == null) {
          return false;
       } else if (compareTo(toSearch, p.data) == 0) {
       	return true;
       } else if (compareTo(toSearch, p.data) < 0) {
          return search(p.left, toSearch);
       } else {
          return search(p.right, toSearch);
       }
    }
    
    
    //preOrderTransversal
    public void preOrderTraversal() {
       preOrderHelper(root);
    }
    private void preOrderHelper(Node r) {
       if (r != null) {
          System.out.print(r+" ");
          preOrderHelper(r.left);
          preOrderHelper(r.right);
       }
    }

    public void inOrderTraversal() {
       inOrderHelper(root);
    }
    private void inOrderHelper(Node r) {
       if (r != null) {
          inOrderHelper(r.left);
          System.out.print(r+" ");
          inOrderHelper(r.right);
       }
    }
    
    BinarySearchTree<Integer> bst;
 
    //count method
    public int count(E low, E high) {
    	return counter(root, E low, E high);
    }
    
    public int counter(E low,E high) {
    	int count = 0;
    	Node<E> current = root;
    	
    	
    	
    	//counting low values
    	int testl = low.compareTo(current.value);
    	
    	//counting high values
    	int testh = high.compareTo(current.value);
    	
    	if (testh != 0) {
    		if (testh > 0) {
    			while (current.value != root) {
    				if (current.right != null) {
    					count++;
    					visit(current.right);
    				}
    				bst.preOrderTraversal();
    			}
    			//System.out.println(root);
    		}
    	}    	
    	return count;
    }        
}