import java.util.ArrayList;


public class Node<T> {
	
	private T data;
	private T path;
    private Node<T> parent = null;
    private ArrayList<Tree<T>> children;
    
    public Node(T in)
    {
    	data = in;
    }
    
    public void setData(T temp)
    {
    	data = temp;
    }
    
    public T getData()
    {
    	return data;
    }
    
    public void setParent(Node<T> temp)
    {
    	parent = temp;
    }
    
    public Node<T> getParent()
    {
    	return parent;
    }
    
    public void setChildren(ArrayList<Tree<T>> temp)
    {
    	children = temp;
    }
    
    public ArrayList<Tree<T>> getChildren()
    {
    	return children;
    }
    
    public void setPath(T in)
    {
    	path = in;
    }
    
    public T getPath()
    {
    	return path;
    }
    
    public void addToChildren(Tree<T> in, T pathIn)
    {
    	if(children == null)
    		children = new ArrayList<Tree<T>>();
    	in.getRoot().setParent(this);
    	in.getRoot().setPath(pathIn);
    	children.add(in);
    }
    
    /*
    public String toString()
    {
    	String temp = "";
    	
    	temp = data.toString();
    	
    	return temp;
    }
    */
    public String toString()
    {
    	String str = "";
    	if(children.isEmpty())
    	{
    		str = "null";
    	}
    	else
    	{
    		for(Tree<T> x : children)
    		{
    			T temp = x.getRoot().getPath();
    			str = str + "\n" + temp + ": " + x.toString();
    		}
    	}
    	str = str + " " + data  + " "+ path;
    	return str;
    }
    
    public boolean hasChildren()
    {
    	if(children == null || children.isEmpty())
    		return false;
    	else
    		return true;
    }
}




















