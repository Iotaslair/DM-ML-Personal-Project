import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Preprocessing {

	public static void main(String[] args) throws FileNotFoundException 
	{

		String csvFile = "/Users/billy/Data mining/Personal-Project/data/StackOverflow 10.csv";
		String line = "";
		String cvsSplitBy = ",";
		String careAbout = "DevType";
		ArrayList<String> first = new ArrayList<String>();
		int place = 0;
		ArrayList<String> list = new ArrayList<String>();
		PrintWriter pw = new PrintWriter(new File("Output.csv"));
        //pw.write("");
        //pw.write("\n"); 
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

		    while ((line = br.readLine()) != null) {
		    	//finds the place careAbout is in 
		    	String[] row = line.split(cvsSplitBy);
		    	
		    	/*
		    	for(String x : row)
		    	{
		    		System.out.print(x + ", ");
		    	}
		    	System.out.println();
		    	*/
		    	
		    	if(row[0].equals("Respondent"))
		    	{
		    		for(int i = 0; i < row.length; i++)
		    		{
		    			if(row[i].equals(careAbout))
		    				place = i;
		    		}
		    		continue;
		    	}
		    	//System.out.println("Place: " + place);
		    	/*
		    	System.out.println("Row we're working with");
		    	
		    	*/
		    	//int thing = Integer.parseInt(row[place]);
		    	//System.out.println("Row[place]");
		    	//System.out.println(row[place]);
		    	String[] each = row[place].split(";");
		    	//System.out.println("Each");
		    	//for(String x : each)
		    	//{
		    	//	System.out.print(x + ", ");
		    	//}
		    	
		    	
		    	for(String x : each)
		    	{
		    		if(!list.contains(x))
		    			list.add(x);
		    	}
		    	
		    	
		    	
		    	
		    	/*
		    	for(String x : row)
		        {
		        	System.out.print(x + " ");
		        }
		        System.out.println();
		    	
		        for(String x : row)
		        {
		        	pw.write(x);
		        	pw.write(",");
		        }

		        pw.write('\n');
		        */
		    }
		} catch (IOException e) {
	    e.printStackTrace();
		}
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

		    while ((line = br.readLine()) != null) {
		    	String[] row = line.split(cvsSplitBy);
		    	//if first row then write special rows
		    	if(row[0].equals("Respondent"))
		    	{
		    		for(String x : row)
		    		{
		    			if(x.equals(careAbout))
		    			{
		    				for(String last : list)
		    				{
		    					String temp = x + "." + last;
		    					pw.write(temp + ",");
		    					first.add(temp);
		    				}
		    			}
		    			pw.write(x + ",");
		    			first.add(x);
		    		}
		    		
		    		continue;
		    	}
		    	pw.write("\n");
		    	//loop through all of the lists
		    	
		    	for(String in : row)
		    	{
		    		//System.out.println("In " + in);
		    		if(special(in,list))
		    		{
				    	for(String x : list)
				    	{
				    		//if devType contains the element in the list
				    		if(row[place].contains(x))
				    		{
				    			//find the position of x in the list
				    			/*
				    			for(int i = 0; i < first.size(); i++)
					    		{
					    			if(first.get(i).contains(x))
					    				place = i;
					    		}
					    		*/
				    			pw.write("Yes,");
				    		}
				    		else
				    		{
				    			pw.write("No,");
				    		}
				    	}
		    		}
		    		else
		    			pw.write(in + ",");
		    	}
		    	
		    	
		    	

		    }
		    
		}catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		
		
		
		
		pw.close();
		
		
		
		
		System.out.println("List after everything");
		System.out.println(list);
	    System.out.println("done!");
		
		
		
	}
	
	public static boolean special(String in, ArrayList<String> list)
	{
		for(String x: list)
		{
			if(in.contains(x))
				return true;
		}

		return false;
	}

}
