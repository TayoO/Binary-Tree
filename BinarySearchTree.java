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
    
    //Implementing the count method  
    
    
    //contains method
    private boolean contains(E obj) {
    	boolean found = false;
    	Node<E> current = root;
    	while (!found && current != null) {
    		int test = obj.compareTo(current.value);
    		if (test == 0) {
    			found = true;
    		} else if (test < 0) {
    			current = current.left;
    		} else {
    			current = current.right;
    		}
    	}
    	return found;
    }
    
    //Traversing the tree
    private void visit(Node<E> current) {
    	System.out.print(" " + current.value);
    }
    
    //preOrder
    public void preOrder() {
    	preOrder(root);
    }
    private void preOrder(Node<E> current) {
    	if (current != null) {
    		visit(current);
    		preOrder(current.left);
    		preOrder(current.right);
    	}
    }
    
    //inOrder
    public void inOrder() {
    	inOrder(root);
    }
    private void inOrder(Node<E> current) {
    	if (current != null) {
    		inOrder(current.left);
    		visit(current);
    		inOrder(current.right);
    	}
    }
    
    //postOrder
    public void postOrder() {
    	postOrder(root);
    }
    private void postOrder( Node<E> current) {
    	if (current != null) {
    		postOrder(current.left);
    		postOrder(current.right);
    		visit(current);
    	}
    }
 
    //count method

   int count(E low, E high) {
	   int total = 0;
	   Node<E> current = root;
	   boolean tooLow = low.compareTo(current.value) >= 0;
	   boolean tooHigh = high.compareTo(current.value) <= 0;
	   
	   if (!tooLow) {
		   total = total + count(current.left, low, high, 0);
	   }
	   if (!tooHigh) {
		   total = total +(count(current.right, low, high, 0));
	   }
	   if (!tooHigh && !tooLow) {
		   return total + 1;
	   } else {
		   return total;
	   }
   }
   
   private int count(Node<E> current, E low, E high, int counter) {
	   boolean tooLow = low.compareTo(current.value) >= 0;
	   boolean tooHigh = high.compareTo(current.value) <= 0;
	   if (!tooHigh && !tooLow) {
		   counter++;
	   }
	   if (!tooLow) {
			   current = current.left;
			   counter++;
			   counter = count(current, low, high, counter);
	   }
	   if (!tooHigh) {
			   current = current.right;
			   counter++;
			   counter = count(current, low, high, counter);
	   }
	return counter;
	   
   } 
}