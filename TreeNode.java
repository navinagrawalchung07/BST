public class TreeNode<E>
{
	private E value;
	private TreeNode<E> left;
	private TreeNode<E> right;
	
	public TreeNode(E v)
	{
		value = v;
		left = null;
		right = null;
	}
	
	public TreeNode(E v, TreeNode<E> l, TreeNode<E> r)
	{
		value = v;
		left = l;
		right = r;
	}
	
	public E getValue()
	{
		return value;
	}
	
	public TreeNode<E> getLeft()
	{
		return left;
	}
	
	public TreeNode<E> getRight()
	{
		return right;
	}
	
	public void setValue(E v)
	{
		value = v;
	}
	
	public void setLeft(TreeNode<E> leftVal)
	{
		left = leftVal;
	}
	
	public void setRight(TreeNode<E> rightVal)
	{
		right = rightVal;
	}
	
}
