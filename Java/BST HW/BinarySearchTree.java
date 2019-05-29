public class BinarySearchTree implements BSTInterface {

	private BSTNode root;
	
	BinarySearchTree(){
		root = null;
	}
	
	private class BSTNode {
		
		String item;
		BSTNode left;
		BSTNode right;
		
		BSTNode(String item) {
			this.item = item;
		}
		
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void makeEmpty() {
		root = null;
	}
	
	public void put(String s) {
		root = recursiveInsert(root, s);
	}
	public boolean contains(String s) {
		return recursiveSearch(root, s);
	}
	
	public void delete(String s) {
		root = recursiveRemove(root, s);
	}
	
	// TODO: Fill this in and call it from contains()
	protected boolean recursiveSearch(BSTNode node, String s) {
		if(node == null) {			
			return false;
		}
		if(node.item.equals(s)) {
			return true;
		}
		int comparison = s.compareTo(node.item);
		if(comparison < 0) {
			return recursiveSearch(node.left, s);
		}
		return recursiveSearch(node.right, s);
	}

	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s){
		if(node == null) {			
			return new BSTNode(s);
		}
		int comparison = s.compareTo(node.item);
		if(comparison < 0) {
			node.left = recursiveInsert(node.left, s);
		}
		else if(comparison > 0) {
			node.right = recursiveInsert(node.right, s);
		}
		return node;
	}

	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {
		if(node != null) {
			int comparison = s.compareTo(node.item);
			if(comparison < 0) {
				node.left = recursiveRemove(node.left, s);
			}
			if(comparison > 0) {
				node.right = recursiveRemove(node.right, s);
			}
			if(comparison == 0) {
				node = deleteNode(node);
			}
		}
		return node;
	}
	
	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
		if(node.left == null && node.right == null) {
			node = null;
		}
		else if(node.left != null && node.right == null) {
			node = node.left;
		}
		else if(node.right != null && node.left == null) {
			node = node.right;
		}
		else {
			node.item = getSmallest(node.right);
			node.right = recursiveRemove(node.right, node.item);
		}
		return node;
	}

	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
		String smallest = node.item;
		while(node.left != null) {
			smallest = node.left.item;
			node = node.left;
		}
		return smallest;
	}

	public MyQueue inOrder() {
		MyQueue queue = new MyQueue();
		inOrderRecursive(root, queue);
		return queue;
	}
	
	public MyQueue preOrder() {
		MyQueue queue = new MyQueue();
		preOrderRecursive(root, queue);
		return queue;
	}
	
	public MyQueue postOrder() {
		MyQueue queue = new MyQueue();
		postOrderRecursive(root, queue);
		return queue;
	}

	// TODO: Fill this in and call it from inOrder()
	protected void inOrderRecursive(BSTNode node, MyQueue queue) {
		if(node != null) {
			inOrderRecursive(node.left, queue);
			queue.enqueue(node.item);
			inOrderRecursive(node.right, queue);
		}
	}


	// TODO: Fill this in and call it from preOrder()
	protected void preOrderRecursive(BSTNode node, MyQueue queue) {
		if(node != null) {
			queue.enqueue(node.item);
			preOrderRecursive(node.left, queue);
			preOrderRecursive(node.right, queue);
		}
	}

	// TODO: Fill this in and call it from postOrder()
	protected void postOrderRecursive(BSTNode node, MyQueue queue) {
		if(node != null) {
			postOrderRecursive(node.left, queue);
			postOrderRecursive(node.right, queue);
			queue.enqueue(node.item);
		}
	}

	// Prints out the tree structure, using indenting to show the different levels of the tree
	public void printTreeStructure() { 
		printTree(0, root);
	}

	// Recursive helper for printTreeStructure()
	protected void printTree(int depth, BSTNode node) {
		indent(depth);
		if (node != null) {
	    	System.out.println(node.item);
	    	printTree(depth + 1, node.left);
	    	printTree(depth + 1, node.right);
	 	} 
	 	else {
	  		System.out.println("null");
	  	}
	}

	// Indents with with spaces 
	protected void indent(int depth) {
		for(int i = 0; i < depth; i++)
			System.out.print("  "); // Indents two spaces for each unit of depth
	}

	@Override
	public void balanceBST() {
	        int count = 0;
	        MyQueue queue = inOrder();
	        MyQueue temp = new MyQueue();
	        while(!queue.isEmpty()){
	        	count++;
	            temp.enqueue(queue.dequeue());
	        }
	        String[] arr = new String[count];
	        for(int i = 0; i < count; i++){
	            arr[i] = temp.dequeue().toString();
	        }
	        root = balanceRecursive(arr, 0, arr.length-1);
	    }
	protected BSTNode balanceRecursive(String[] array, int first, int last) {
	        BSTNode node = null;
	        if(first != last) {
	            int mid = (first + last) / 2;
	            node = new BSTNode(array[mid]);
	            node.left = balanceRecursive(array, first, mid);
	            node.right = balanceRecursive(array, mid + 1, last);
	        }
	        return node;

		
	}


	// Extra Credit 

	// TODO: If doing the extra credit, fill this in and call it from balanceBST()
	/* protected BSTNode balanceRecursive(String[] array, int first, int last) {

	} */
}