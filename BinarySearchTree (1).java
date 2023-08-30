/**
 * BinarySearchTree.java
 * 
 * Binary Search Tree data structure that holds the root treenode.
 * Includes methods that count the number of nodes, levels, adds and removes nodes.
 *
 * @version 1.0
 * @since 5/5/2023
 */
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>>
{
	private TreeNode<E> root;
	private int count;
	private int actual;
	
	/**
	 * No args constructor that initializes root to null
	 */ 
	public BinarySearchTree ( )
	{
		root = null;
		count = 0;
		actual = 0;
	}
	
	/**
	 * Clears the tree by setting root to null
	 */ 
	public void clear ( )
	{
		root = null;
	}
	
	/**
	 * void method that calls countNodes
	 */ 
	public int countNodes ( )
	{
		return countNodes(root);
	}
	
	/**
	 * count nodes method that uses recursion to count nodes by adding 1 each time
	 */
	public int countNodes(TreeNode<E> node)
	{
		if(node != null)
			return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
		return 0;
	}
	
	/**
	 * calls the actual min method
	 */
	public E min()
	{
		return min(root);
	}

	/**
	 * Minimum method that returns the left most value of the tree
	 */
	private E min(TreeNode<E> node) 
	{
		if(node.getLeft() == null)
			return node.getValue();
		return min(node.getLeft());
	}

	/**
	 * calls the actual max method
	 */
	public E max()
	{
		return max(root);
	}

	/**
	 * Maximum method that returns the right most value of the tree
	 */
	private E max(TreeNode<E> node) 
	{
		if(node.getRight() == null)	
			return node.getValue();
		return max(node.getRight());
	}

	/**
	 * calls the actual find method
	 */
	public TreeNode<E> find(E value) 
	{
		return find(root, value);
	}
	
	/**
	 * Recursively goes through nodes of the subtree using compare to and returns the node with value
	 */
	public TreeNode<E> find(TreeNode<E> node, E value)
	{
		
		if(node == null)
			return null;
		int diff = value.compareTo(node.getValue());
		if(diff == 0)
			return node;
		else if(diff < 0)
			return find(node.getLeft(), value);
		else 
			return find(node.getRight(), value);
		
	}
	
	/**
	 * Calls the rank method and passes in root node
	 */
	public int rank(E value)
	{
		if (value != null)
		{
			return rank(value, root);
		}
		return -1;
	}
	
	/**
	 * Calls helped function findPosition and returns the index of value
	 */
	private int rank(E value, TreeNode<E> node)
	{
		findPosition(value, node);
		count = 0;
		return actual;
	
	}
	
	/**
	 * Helper function that does inOrder traversing and increases count each time until it reaches value
	 */
	private void findPosition(E value, TreeNode<E> node)
	{
		if(node == null)
			return;
		findPosition(value, node.getLeft());
		if(node.getValue().compareTo(value) == 0)
			actual = count;
		count++;
		findPosition(value, node.getRight());
	} 

	/**
	 * Calls contains method and passes in root
	 */
	public boolean contains(E value)
	{
		return contains(root,value);
	}
	
	/**
	 * Checks if the tree contains value by using recursion and compareTo
	 */
	private boolean contains(TreeNode<E> node, E value)
	{
		
        
        
        
        
		
	}
	
	/**
	 * Calls the add method and passes in root
	 */
	public boolean add(E value)
	{
		if(contains(value))
		{
			return false;
		}
		root = add(root, value);
		return true;
	}
	
	/**
	 * Addes value into the right position by traversing through tree and setting parent's child to value
	 */
	private TreeNode<E> add(TreeNode<E> node, E value)
	{
		if(node == null)
			node = new TreeNode<E>(value);
		else
		{
			int diff = value.compareTo(node.getValue());
			if(diff < 0)
				node.setLeft(add(node.getLeft(), value));
			else
				node.setRight(add(node.getRight(), value));
		}
		return node;
	}
	
	/**
	 * calls maxDepth with root passed in
	 */
	public int levelCount ( ) 
	{
		return(maxDepth(root));
	}
	
	/**
	 * Returns the max depth of the tree
	 */
	private int maxDepth(TreeNode<E> node) 
	{
		
        
        
        
        
	}
	
	/**
	 * Calls delete method with root passed in
	 */
	public void delete(E value) 
	{
		root = delete(root, value);
	}

	/**
	 * Delete method that goes through 3 cases, no children, one children and two children.
	 * Finds parent, replacement parent, and a replacement and links them
	 */
	private TreeNode<E> delete(TreeNode<E> node, E value) 
	{
		TreeNode<E> parent = null;
		
		while(node != null && node.getValue().compareTo(value) != 0)
		{
			parent = node;
			
			int diff = value.compareTo(node.getValue());
			if(diff < 0)
				node = node.getLeft();
			else
				node = node.getRight();
		}
		
		if(node == null)
		{
			System.out.println("Sorry, but " + value + " could not be found");
			return root;
		}

		else if(node.getLeft() == null && node.getRight() == null)
		{
			if(parent.getLeft() == node)
				parent.setLeft(null);
			else
				parent.setRight(null);
			
		}
		else if(node.getLeft() != null && node.getRight() != null)
		{
			TreeNode<E> replace = find(min(node.getRight()));
			TreeNode<E> replaceParent = null;
			TreeNode<E> temp = root;
		
			while(temp != null && temp.getValue().compareTo(replace.getValue()) != 0)
			{
				replaceParent = temp;
				
				int diff = replace.getValue().compareTo(temp.getValue());
				if(diff < 0)
					temp = temp.getLeft();
				else
					temp = temp.getRight();
			}
			
			TreeNode<E> prevLeft = node.getLeft();
			TreeNode<E> prevRight = node.getRight();
				
			if(replaceParent.getValue().compareTo(value) == 0)
			{
				if(node == root)
				{
					root = replace;
					root.setLeft(prevLeft);
				}
				else if(parent.getLeft() == replaceParent)
				{
					parent.setLeft(replace);
					replace.setLeft(prevLeft);
				}
				else
				{
					parent.setRight(replace);
					replace.setLeft(prevLeft);
				}
			}
			else
			{
				if(node == root)
				{	
					root = replace;
					root.setLeft(prevLeft);
					root.setRight(prevRight);
				}
				else if(parent.getLeft() == node)
				{
					parent.setLeft(replace);
					replace.setLeft(prevLeft);
					replace.setRight(prevRight);
				}
				else
				{
					parent.setRight(replace);
					replace.setLeft(prevLeft);
					replace.setRight(prevRight);
		
				}
				if(replaceParent.getLeft() == replace)
					replaceParent.setLeft(null);
				if(replaceParent.getRight() == replace)
					replaceParent.setRight(null);
			}
			
		}
		else
		{
			TreeNode<E> kid;
			if(node.getLeft()==null)
				kid = node.getRight();
			else 
				kid = node.getLeft();
			
			if(node!=root)
			{
				if(parent.getLeft() == node)
					parent.setLeft(kid);
				else 
					parent.setRight(kid);
			}
			else
				root = kid;
		}
		return root;
		
    }
    
	public TreeNode<E> getRoot ( )
	{
		return root;
	}
	
	public void setRoot(TreeNode<E> node)
	{
		root = node;
	}
	
	
	/*********  The methods below are complete.  Do not change them.    **********/
	
	
	
	public String toString ( )
	{
		return print(root);
	}
	
	public String print(TreeNode<E> node)
	{
		if (node != null) 
		{
			return print(node.getLeft()) + String.format("%4d", node.getValue()) + print(node.getRight());
		}
		return "";
	}

	public void printInFullForm ( )
	{
		int height = getHeight(root);
		int size = (int)Math.pow(2, height)-1;
		List<List<String>> result = new ArrayList<List<String>>();
		for(int i = 0; i < height; i++)
		{
			List<String> list = new ArrayList<String>();
			for(int j = 0; j < size; j++)
			{
				list.add("  ");
			}
			result.add(list);
		}
		helper(result, root, 0, 0, size - 1);
		System.out.println();
		for(List<String> line : result)
		{
			for(String numberOrSpaces : line)
			{
				System.out.print(numberOrSpaces);
			}
			System.out.println("\n");
		}
	}

	public void helper(List<List<String>> result, TreeNode<E> node, int level, int left, int right)
	{
		if(node == null)
		{
			return;
		}
		int index = (left + right) / 2;
		result.get(level).set(index, node.getValue()+"");
		helper(result, node.getLeft(), level+1, left, index-1);
		helper(result, node.getRight(), level+1, index+1, right);
	}

	public int getHeight(TreeNode<E> node)
	{
		if(node == null)
		{
			return 0;
		}
		return Math.max(1 + getHeight(node.getLeft()), 1 + getHeight(node.getRight()));
	}
}
