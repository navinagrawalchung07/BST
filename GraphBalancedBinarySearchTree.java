/**
 * Creates and graphs Binary Search Trees.  The initial Binary Search Tree
 * is filled with random Integers from 1 to 500, and can have from 7 to 31
 * TreeNodes.  There is no attempt to balance the initial Binary Search Tree.
 * Then, this "unbalanced" Binary Search Tree is used to create a balanced
 * Binary Search Tree.  The user may toggle between views of the unbalanced
 * BST or the balanced BST by pressing the SPACE BAR.  If the user wants to
 * create a new BST (both the unbalanced and balanced versions) they may
 * press the ENTER key.
 *
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GraphBalancedBinarySearchTree
{
	/**  ints to control the height, width, and border of the GUI     */
	private int HEIGHT, WIDTH, BORDER;
	
	/**  You may need to create other fields          
	 */
	 
	private ArrayList<Integer> treeArray;
	
    /**
     * Constructs a GraphBalancedBinarySearchTree object.
     */
	public GraphBalancedBinarySearchTree ( )
	{
		WIDTH = 1200;
		HEIGHT = 700;
		BORDER = 20;
		treeArray = new ArrayList<Integer>();
	}
	
    /**
     * Creates an instance of the GraphBalancedBinarySearchTree, then sets up
	 * the canvas.  Constructs 2 BST's, initially empty.  Random Integer values
	 * are added to the first BST, with values from 1 to 500, and from 7 to 31
	 * TreeNodes.  This tree is unbalanced.  Next, the first (unbalanced) BST
	 * is used to create a second (balanced) BST.  The first BST is then
	 * displayed in the GUI, and a loop is run so that the user can toggle back
	 * and forth between the 2 BSTs.  The user may also choose to start over,
	 * with new BSTs created in this loop method.
	 * 
     * @param args    array of Strings (not used here)
     */
	public static void main(String[] args) 
	{
		GraphBalancedBinarySearchTree panel1 = new GraphBalancedBinarySearchTree();
		panel1.setUpCanvas();
		BinarySearchTree<Integer> treeOriginal = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> treeBalanced = new BinarySearchTree<Integer>();
		panel1.addRandomIntegers(treeOriginal);
		panel1.balanceBST(treeOriginal, treeBalanced);
		panel1.drawTree(treeOriginal, "UNbalanced");
		panel1.runLoop(treeOriginal, treeBalanced);
	}

    /**
     * Sets up the canvas for the GUI.
     * 
     */
	public void setUpCanvas ( )
	{
		StdDraw.setCanvasSize(WIDTH, HEIGHT + BORDER);
		StdDraw.setXscale(-BORDER, WIDTH + BORDER);
		StdDraw.setYscale(-BORDER, HEIGHT);

		StdDraw.enableDoubleBuffering();
	}

    /**
     * A continuous event loop for the GUI.  Waits for a key to be typed by the user.
	 * If the user types the SPACE BAR, the view toggles from a drawing for the 
	 * balanced to the unbalanced BST, or from the unbalanced to the balanced BST.
	 * If the user types the ENTER key, the BSTs are recreated, with a new set of 
	 * random values from 1 to 500, with from 7 to 31 TreeNodes.  The current BST
	 * is also redrawn, showing the new values for the BST.
     * 
     * @param original    the current, unbalanced BST
     * @param balanced    the current, balanced BST
     */
	public void runLoop(BinarySearchTree<Integer> original, BinarySearchTree<Integer> balanced)
	{
		boolean toggle = false;
		while(true)
		{
			StdDraw.pause(50);
			if(StdDraw.hasNextKeyTyped())
			{
				if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER))
				{
					addRandomIntegers(original);
					balanceBST(original, balanced);
					toggle = !toggle;
				}
				if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) || StdDraw.isKeyPressed(KeyEvent.VK_ENTER))
				{
					StdDraw.clear();
					if(toggle)
					{
						drawTree(original, "UNbalanced");
					}
					else
					{
						drawTree(balanced, "BALANCED");
					}
					toggle = !toggle;
				}
				StdDraw.nextKeyTyped();
			}
		}
	}

    /**
     * Fills the BST, passed in as an argument, with random Integer values from
	 * 1 to 500.  The resulting BST will contain from 7 to 31 TreeNodes.
     * 
     * @param tree    the BST to be filled with random values
     */
	public void addRandomIntegers(BinarySearchTree<Integer> tree)
	{
		tree.clear();
		for(int i = 0; i < 63 && tree.getHeight(tree.getRoot()) < 7; i++)
		{
			int value = (int)(Math.random() * 500 + 1);
			tree.add(value);
		}
	}

    /**
     * Draws the BST to the GUI.
     * 
     * @param tree    the BST to be drawn
     * @param label   the label to accompany the BST, "UNbalanced" or "BALANCED"
     */
	public void drawTree(BinarySearchTree<Integer> tree, String label)
	{
		Font font = new Font("Arial", Font.BOLD, 30);
		StdDraw.setFont(font);
		StdDraw.setPenColor(new Color(0,0,0));
		StdDraw.textLeft(10, 650, "Nodes: " + tree.countNodes());
		StdDraw.textLeft(10, 590, "Levels: " + tree.getHeight(tree.getRoot()));
		StdDraw.textLeft(1000, 650, label);
		font = new Font("Arial", Font.BOLD, 23 - tree.getHeight(tree.getRoot()) * 2);
		StdDraw.setFont(font);
		traverseLevels(tree, tree.getRoot());
		StdDraw.show();
		StdDraw.pause(50);
	}

    /**
     * Traverses the levels and nodes of the BST, drawing each element.
     * 
     * @param tree    the BST being drawn
     * @param node    the current node being drawn
     */
	public void traverseLevels(BinarySearchTree<Integer> tree, TreeNode<Integer> node)
	{
		int level = 1;

		while(drawLevel(tree, node, level, level, 0))
		{
			level++;
		}
	}

    /**
     * A recursive method, used to draw each TreeNode, and connect the TreeNodes with
	 * line segments, to show the BST in the GUI.
     * 
     * @param tree        the BST being drawn
     * @param node        the current node being drawn
	 * @param level       counts down using recursion, to get to the current TreeNode
	 * @param trueLevel   the vertical level of the TreeNode
	 * @param position    the horizontal position of the TreeNode
     */
	public boolean drawLevel(BinarySearchTree<Integer> tree, TreeNode<Integer> node, int level, int trueLevel, int position)
	{
		if(node == null)
		{
			return false;
		}

		if(level == 1)
		{
//			System.out.println((Integer)(node.getValue()) + " level: " + level + "  trueLevel: " + trueLevel + "  position: " + position);
			StdDraw.setPenColor(new Color(0,0,0));
			double xShiftPrevious = 1200 / (Math.pow(2, trueLevel - 1));
			double prevX = xShiftPrevious + xShiftPrevious * (position / 2) * 2;
			double prevY = 750 - (trueLevel - 1) * (198 - tree.getHeight(tree.getRoot()) * 14) - (22 - 2 * tree.getHeight(tree.getRoot()));
			double xShift = 1200 / (Math.pow(2,trueLevel));
			double x =  xShift + xShift * position * 2;
			double y = 750 - trueLevel * (198 - tree.getHeight(tree.getRoot()) * 14);
			if(node != tree.getRoot())
			{
				StdDraw.line(prevX, prevY, x, y);
			}
			StdDraw.filledCircle(x, y, 25 - 2 * tree.getHeight(tree.getRoot()));
			StdDraw.setPenColor(new Color(150,150,255));
			StdDraw.filledCircle(x, y, 23 - 2 * tree.getHeight(tree.getRoot()));
			StdDraw.setPenColor(new Color(0,0,0));
			StdDraw.text(x, y - (3 - tree.getHeight(tree.getRoot()) / 3), "" + (Integer)(node.getValue()));
		
			StdDraw.show();
			StdDraw.pause(50);
			
			return true;
		}

		boolean left = drawLevel(tree, node.getLeft(), level - 1, trueLevel, position);
		boolean right = drawLevel(tree, node.getRight(), level - 1, trueLevel, position + (int)(Math.pow(2,level - 2)));

		return (left || right);
	}
	
    /**
     * Creates a balanced BST (balanced) from the unbalanced BST (original).
     * 
     * @param original    the unbalanced BST
     * @param balanced    the balanced BST
     */
	public void balanceBST(BinarySearchTree<Integer> original, BinarySearchTree<Integer> balanced)
	{
		
        /**
         *
         *  Start coding here
         *
         */
        
        
		
	}
	
	/**  You may need to create other methods                          */
	
	
}
