import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Preprocessing {
	static ArrayList<String> careAboutList = new ArrayList<String>();
	static int number = 0;
	static String csvFile = "/Users/billy/eclipse-workspace/Personal Project/Output0.csv";
	static String line = "";
	static String cvsSplitBy = ",";
	static String careAboutStatic;
	static ArrayList<String> first = new ArrayList<String>();
	static int place = 0;
	static ArrayList<String> list = new ArrayList<String>();
	static PrintWriter pw;
	static PrintWriter weird;
	static Scanner reader = new Scanner(System.in);
	static ArrayList<Double> times = new ArrayList<Double>();
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		
		careAboutList.add("DevType");
		careAboutList.add("CommunicationTools");
		careAboutList.add("EducationTypes");
		careAboutList.add("SelfTaughtTypes");
		careAboutList.add("HackathonReasons");
		careAboutList.add("LanguageWorkedWith");
		careAboutList.add("LanguageDesireNextYear");
		careAboutList.add("DatabaseWorkedWith");
		careAboutList.add("DatabaseDesireNextYear");
		careAboutList.add("PlatformWorkedWith");
		careAboutList.add("PlatformDesireNextYear");
		careAboutList.add("FrameworkWorkedWith");
		careAboutList.add("FrameworkDesireNextYear");
		careAboutList.add("IDE");
		careAboutList.add("Methodology");
		careAboutList.add("VersionControl");
		careAboutList.add("AdBlockerReasons");
		careAboutList.add("ErgonomicDevices");
		careAboutList.add("Gender");
		careAboutList.add("SexualOrientation");
		careAboutList.add("RaceEthnicity");
		
		for(String careAbout : careAboutList)
		{
			System.out.println("Working on " + careAbout + " " + number);
			careAboutStatic = careAbout;

			String stupid = "Output" + (number + 1) + ".csv";
			pw = new PrintWriter(new File(stupid));
			weird = new PrintWriter(new File("Weird.txt"));
			
	        //pw.write("");
	        //pw.write("\n"); 
			
			long startTime = System.nanoTime();
			
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
			    	for(String x : each)
			    		weird.write(x + ", ");
			    	weird.write("\n");
			    	
			    	for(String x : each)
			    	{
				    	if(!list.contains(x))
				    		
				    		list.add(x);
			    	}
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
			    				continue;
			    			}
			    			pw.write(x + ",");
			    			first.add(x);
			    		}
			    		continue;
			    	}
			    	pw.write("\n");
			    	/*
			    	for(String x : row)
			    	{
			    		System.out.print(x + ", ");
			    	}
			    	System.out.println();
			    	*/
			    	
			    	boolean once = true;
			    	//Writing
			    	for(String in : row)
			    	{
			    		//System.out.println("In " + in);
			    		if(special(in,list) && once)
			    		{
					    	for(String x : list)
					    	{
					    		//if devType contains the element in the list
					    		if(row[place].contains(x))
					    			pw.write("Yes,");
					    		else
					    			pw.write("No,");
					    	}
					    	once = false;
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
		    System.out.println("Done!");
		    
		    long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			double seconds = (double)duration / 1000000000.0;
			times.add(seconds);
			System.out.println("\nIt took " + seconds + " seconds to complete " + careAbout);
			number = number + 1;
			csvFile = "/Users/billy/eclipse-workspace/Personal Project/Output" + number + ".csv";
			list.clear();
			first.clear();
			pw.flush();
			//String read;
			//System.out.println("Are you ready?");
			//read = reader.nextLine();
		}
		double time = 0;
		for(double x : times)
		{
			time = time + x;
		}
		System.out.println("It took " + time/60.0 + " minutes to finish it");
	}
	
	//takes in the row at a certain place, and the list of all elements in the column place
	public static boolean special(String in, ArrayList<String> list)
	{
		for(String x: list)
		{
			if(in.contains(x + ";"))
				return true;
			else if(in.contains(careAboutStatic + ".NA"))
				return true;
			else if (in.equals(x))
				return true;
		}

		return false;
	}

}
