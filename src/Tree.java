import java.util.ArrayList;

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>(rootData);
        root.setChildren(new ArrayList<Tree<T>>());
    }
    
    public void addToRoot(Tree<T> in)
    {
    	in.getRoot().setParent(root);
    	ArrayList<Tree<T>> temp = root.getChildren();
    	temp.add(in);
    	root.setChildren(temp);
    }
    
    public Node<T> getRoot()
    {
    	return root;
    }
    
    public void addToChildren(Tree<T> in, T inPath)
    {
    	in.getRoot().setParent(root);
    	root.addToChildren(in,inPath);
    }
    
    public boolean hasChildren()
    {
    	return root.hasChildren();
    }
    
    public ArrayList<Tree<T>> getChildren()
    {
    	return root.getChildren();
    }
    
    public T getData()
    {
    	return root.getData();
    }
    
    public Node<T> getParent()
    {
    	return root.getParent();
    }
    
    
    
    
    
    public void print()
    {
    	int counter = 1;
    	
    	
    	System.out.println("Node # " + counter + ": " + root.getData());    		
    	try
    	{
    		System.out.println("Node # " + counter + "'s parent: " + root.getParent().getData());
    	}
    	catch(Exception e)
    	{
    		System.out.println("Node # " + counter + "'s parent: " + "No Parents");
    	}
    	
    	if(root.getChildren().size() > 1)
    	{
    		for(int i = 0; i < root.getChildren().size(); i++)
    		{
    			System.out.println( "Node # " + counter + " child #: " + i + " " + root.getChildren().get(i).getData());
    		}
    	}
    	else
    		System.out.println("Node # " + counter + "'s children: " + root.getChildren().get(0).getData());
    	System.out.println("Node # " + counter + "'s path: " + root.getPath());
    	//System.out.println("After first part");
    	System.out.println();
    	
    	counter++;
    	
    	printHelper(this,counter);
    	
    	/*
    	Stack<Node<T>> loader = new Stack<Node<T>>();
    	
    	loader.push(root);
    	
    	while(!loader.isEmpty())
    	{
    		Node x = loader.pop();
    		
    	}
    	*/
    }
    
    public int printHelper(Tree<T> in,int counter)
    {
    	if(in.hasChildren())
    	{
    		ArrayList<Tree<T>> children = in.getChildren();
    		
    		for(Tree<T> x : children)
    		{
    			if(x.hasChildren())
    			{
	    			System.out.println("Node # " + counter + ": " + x.getData());
	            	System.out.println("Node # " + counter + "'s parent: " + x.getParent().getData());
	            	if(x.getChildren().size() > 1)
	            	{
	            		for(int i = 0; i < x.getChildren().size(); i++)
	            		{
	            			System.out.println( "Node # " + counter + " child #: " + i + " " + x.getChildren().get(i).getData());
	            		}
	            	}
	            	else
	            		System.out.println("Node # " + counter + "'s children: " + x.getChildren().get(0).getData());
	            	System.out.println("Node # " + counter + "'s path: " + x.getRoot().getPath());
	            	System.out.println();
	            	counter++;
	            	counter = printHelper(x,counter);
    			}
    			else
    			{
    				System.out.println("Node # " + counter + ": " + x.getData());
    	        	System.out.println("Node # " + counter + "'s parent: " + x.getParent().getData());
    	        	System.out.println("Node # " + counter + " is a leaf");
    	        	System.out.println("Node # " + counter + "'s path: " + x.getRoot().getPath());
    	        	System.out.println();
    	        	counter++;
    			}
    		}
    		return counter;
    		
    	}
    	else //leaf
    	{
    		System.out.println("Node # " + counter + ": " + in.getData());
        	System.out.println("Node # " + counter + "'s parent: " + in.getParent().getData());
        	System.out.println("Node # " + counter + " is a leaf");
        	System.out.println();
        	counter++;
        	//System.out.println("done with this leaf \n");
    		return counter;
    	}
    	
    }
     
    
    /*
    public void print()
    {
    	String rtn = "";
    	
    	ArrayList<Tree<T>> children = root.getChildren();
    	
    	for(Tree<T> x : children)
    	{
    		Node<T> temp = x.getRoot();
    		rtn = rtn + temp.toString();
    	}
    	System.out.println(rtn);
    }
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    //toString
    public String toString()
    {
    	
    	System.out.println("in starter toString " + root.getData());
    	String rtn = "";
    	if(root.getChildren() != null)
    	{
    		ArrayList<Node<T>> rootChildren = root.getChildren();
    		rtn  = rtn + root.getData().toString() + "\n";
    		for (Node<T> x : rootChildren)
    		{
    			rtn = rtn + recursiveToString(x);
    		}
    	}
    	else
    	{
    		rtn = "root node only " + root.getData().toString();
    	}
    	
    	return rtn;
    	
    	
    }
    
    private String recursiveToString(Node<T> in)
    {
    	String rtn = "";
    	
    	return rtn;
    	
    	
    	
    	/*
    	System.out.println("In node toString");
    	System.out.println("Playing with this node " + in.getData());
    	String rtn = "";
    	if(in.getChildren() != null)
    	{
    		ArrayList<Node<T>> inChildren = root.getChildren();
    		for(Node<T> x : inChildren)
    		{
    			System.out.println("calling recursive");
    			String temp = recursiveToString(x);
    			System.out.println("done calling recursive");
    			//nicer formatting for the start
    			System.out.println("Printing out rtn " + rtn +"this is the end");
    			if(rtn.equals(""))
    				rtn = temp;
    			else
    				rtn = rtn + " " + temp;
    		}
    		rtn = rtn + "\n";
    	}
    	else
    	{
    		rtn = rtn + " " + in.getData().toString();
    		return rtn;
    	}
    	return rtn;
    	
	}
	*/
}
