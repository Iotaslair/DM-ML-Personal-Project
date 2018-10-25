

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.text.DecimalFormat;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Iterator;
	import java.util.Scanner;
	import java.util.Set;
public class Apriori 
{
	//set of all things
	public static HashSet<String> itemSet = new HashSet<String>();
	//times a particular combination occurred
	public static HashMap<HashSet<String>, Integer> database = new HashMap<HashSet<String>, Integer>();
	//times a particular String has occured
	public static HashMap<String,Integer> occured = new HashMap<String,Integer>();
	
	//Unsure about modeling
	//set of all things that ever passed though the filter
	public static HashSet<HashSet<String>> frequentSet = new HashSet<HashSet<String>>();
	//for use in powerSet() powerset of things that are viable currently
	public static HashSet<HashSet<String>> powerSet = new HashSet<HashSet<String>>();
	//Usable base units
	public static HashSet<String> filteredSet = new HashSet<String>();
	
	public static HashMap<HashSet<String>,HashSet<HashSet<String>>> rules = new HashMap<HashSet<String>,HashSet<HashSet<String>>>();
	
	public static double support = .11;
	public static double confidence = .9;
	public static double lift = 3.2;
	
	public static void main(String[] args) 
	{
		//Scanner reader = new Scanner(System.in);
		
		//String csvFile = "/Users/billy/Data mining/introdm/A1/data/Project_Data_Processed_Final_With_Days.csv";
		String csvFile = "/Users/billy/Data mining/Personal-Project/data/StackOverflow.csv";
		/*
		System.out.println("Put in the directory of the CSV including filename.csv");
		System.out.println("For me it is in the directory /Users/billy/Data mining/introdm/A1/data/data.csv");
		csvFile = reader.nextLine();
		reader.close();
		*/
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

		    while ((line = br.readLine()) != null) {

		        // use comma as separator
		        String[] row = line.split(cvsSplitBy);

		        if(!row[0].equals("Respondent"))
		        {
			        /*
					System.out.println("VendorID " + row[0] + " tpep_pickup_datetime " + row[1] +
				    " tpep_dropoff_datetime " + row[2] + " passenger_count " + row[3] +
				    " trip_distance " + row[4] + " RatecodeID " + row[5] +
				    " store_and_fwd_flag " + row[6] + " PULocationID " + row[7] +
				    " DOLocationID " + row[8] + " payment_type " + row[9] +
				    " fare_amount " + row[10] + " extra " + row[11] + 
				    " mta_tax " + row[12] + " tip_amount " + row[13] +
				    " tolls_amount " + row[14] + " improvement_surcharge " + row[15] + 
				    " total_amount " + row[16]);
					*/
		        	//creating the data base
					addDatabase(row);
					//makes the item set
					addItems(row);
					//counts the number of times a item is in the database
					addOccured(row);
		        }
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		//System.out.println(database);
		//System.out.println("Occured" + occured);
		//System.out.println(database);
		System.out.println("Database created successfully. It has " + database.size() + " unique rows.");
		System.out.println("There are " + itemSet.size() + " unique items.");
		
		//makes the start of the frequent set
		starterSet();
		
		//frequent set 
		int size = 1;
		while( !frequentSet.isEmpty())
		{
			//make a set from previous stuff
			powerSet(size);

			//adds the stuff from powerset into the frequent set
			//System.out.println(powerSet);
			for(HashSet<String> x : powerSet)
			{
				frequentSet.add(x);
			}
			
			//filter stuff
			filter();

			size++;
			//System.out.println("Rules" + rules);
			if(size > 4)
				break;
		}
		System.out.println("The rules for this dataset are as shown");
		DecimalFormat dfSupport = new DecimalFormat("#.###");
		DecimalFormat dfConfidence = new DecimalFormat("#.####");
		DecimalFormat dfLift = new DecimalFormat("#.####");
		Set<HashSet<String>> keys = rules.keySet();
		Iterator<HashSet<String>> iKeys = keys.iterator();
		while(iKeys.hasNext())
		{
			HashSet<String> title = iKeys.next();
			String str = title.toString() + " => ";
			Iterator<HashSet<String>> i = rules.get(title).iterator();
			while(i.hasNext())
			{
				HashSet<String> x = i.next();
				str = str + "   " + x;
				str = str + " Support: " + dfSupport.format(support(title));
				str = str + " Confidence: " + dfConfidence.format(confidence(title,x));
				str = str + " Lift: " + dfLift.format(lift(title,x));
			}
			System.out.println(str);
		}
		
		
		//System.out.println("Rules " + rules);
	}
	
	public static void addItems(String[] in)
	{
		for(String x : in)
		{
			itemSet.add(x);
		}
	}
	
	public static void addDatabase(String[] in)
	{
		HashSet<String> temp = new HashSet<String>();
		for(String x : in)
		{
			temp.add(x);
		}
		
		if(database.containsKey(temp))
		{
			database.put(temp, database.get(temp) + 1);
		}
		else
		{
			database.put(temp, 1);
		}
	}
	
	public static void addOccured(String[] in)
	{
		for(String x : in)
		{
			if(occured.containsKey(x))
			{
				occured.put(x, occured.get(x) + 1);
			}
			else
			{
				//System.out.println(x);
				occured.put(x, 1);
			}
		}
	}

	public static void starterSet()
	{
		for(String x: itemSet)
		{
			HashSet<String> temp = new HashSet<String>();
			temp.add(x);
			frequentSet.add(temp);
			filteredSet.add(x);
		}
	}

	public static void powerSet(int size)
	{
		//convert a set of strings into a array of strings		
		Iterator<String> it = filteredSet.iterator();
		String[] st = new String[filteredSet.size()];
		int c = 0;
		while(it.hasNext())
		{
			st[c] = (String) it.next();
			c++;
		}
		System.out.println("Getting into powerset code for level " + size);
		
		//String[] st = (String[]) filteredSet.toArray(); //{ "apple", "banana", "cherry", "dino" };
        HashSet<HashSet<String>> hashSet = new HashSet<HashSet<String>>();
        int len = st.length;
        int elements = (int) Math.pow(2, len);
        skip:
        for (int i = 0; i < elements; i++) {
            String str = Integer.toBinaryString(i);
            int value = str.length();
            int count = 0;
            for(int j = 0; j < value; j++)
            {
            	if(str.charAt(j) == '1')
            	{
            		count++;
            		if(count > size)
            			continue skip;
            	}
            }
            String pset = str;
            for (int k = value; k < len; k++) {
                pset = "0" + pset;
            }
            HashSet<String> set = new HashSet<String>();
            for (int j = 0; j < pset.length(); j++) {
                if (pset.charAt(j) == '1' && set.size() <= size)
                    set.add(st[j]);
            }
            hashSet.add(set);
        }
        System.out.println("Powerset completed");
        powerSet = hashSet;
	}
	
	public static void remove(HashSet<String> temp)
	{
		if(temp.size() == 1)
		{
			Iterator<String> t = temp.iterator();
			filteredSet.remove(t.next());
		}
	}
	
	public static void delete()
	{
		Iterator<String> items = filteredSet.iterator();
		Iterator<HashSet<String>> outer = powerSet.iterator();
		out:
		while(items.hasNext())
		{
			String careAbout = items.next();
			HashSet<String> innerSet = outer.next();
			Iterator<String> inner = innerSet.iterator();
			while(inner.hasNext())
			{
				String x = inner.next();
				if(careAbout.equals(x))
				{
					continue out;
				}
			}
		}
	}
	
	public static void filter()
	{
		System.out.println("Filtering");
		Iterator<HashSet<String>> pi = powerSet.iterator();
		ArrayList<HashSet<String>> removeLater = new ArrayList<HashSet<String>>();
		while(pi.hasNext())
		{
			HashSet<String> innerSet = pi.next();
			//System.out.println(innerSet);
			//makes sure it's not the null set
			if(innerSet.size() != 0)
			{
				//has one from each column
				if(hasEverything(innerSet))
				{
					Iterator<HashSet<String>> inner = powerSet.iterator();
					out:
					while(inner.hasNext())
					{
						HashSet<String> second = inner.next();
						if(second.size() == 0)
						{
							continue out;
						}
						//if the two sets are the same then go to next item in inner
						if(innerSet.equals(second))
						{
							continue out;
						}
						//passes all 3 tests
						if(support(innerSet) > support)
						{
							if(confidence(innerSet,second) > confidence)
							{
								if(lift(innerSet,second) > lift)
								{
									if(hasEverything(innerSet,second))
									{
										HashSet<HashSet<String>> t = rules.get(innerSet);
										//System.out.println("t" + t);
										//System.out.println("second" + second);
										if(t == null)
											t = new HashSet<HashSet<String>>();
										t.add(second);
										rules.put(innerSet, t);
										//System.out.println("added " + innerSet + " and " + t + " to rules.");
									}
								}
							}
							else
							{
								continue out;
							}
						}
						else
						{
							break out;
						}
					}
				}
				else
				{
					removeLater.add(innerSet);
					//powerSet.remove(innerSet);
					//System.out.println("Adding " + innerSet + " to be removed later");
				}
			}
		}
		delete();
		for(int i = 0; i < removeLater.size(); i++)
		{
			//System.out.println("Removing " + removeLater.get(i));
			powerSet.remove(removeLater.get(i));
		}
	}

	public static boolean hasEverything(HashSet<String> list)
	{
		//day of week
		Iterator<String> ex = list.iterator();
		int count = 0;
		boolean day = true;
		boolean time = true;
		boolean pass = true;
		boolean price = true;
		while(ex.hasNext())
		{
			String x = ex.next();
			if((x.equals("Monday") ||  x.equals("Tuesday") || x.equals("Wednesday") || x.equals("Thursday") || x.equals("Friday") ||
				x.equals("Saturday") || x.equals("Sunday")) && day)
				{
					count++;
					day = false;
				}
			else
			{
				if((x.equals("Evening") || x.equals("Morning") || x.equals("Midnight") || x.equals("Afternoon")) && time)
				{
					count++;
					time = false;
				}
				else
				{
					if((x.equals("1") || x.equals("2") || x.equals("3") || x.equals("4") || x.equals("5") || x.equals("6")) && pass)
					{
						count++;
						pass = false;
					}
					else
					{
						if((x.equals("Small") || x.equals("Medium") || x.equals("Big") ) && price)
						{
							count++;
							price = false;
						}
					}
				}
			}
			
		}
		return count == list.size();
	}
	
	public static boolean hasEverything(HashSet<String> first, HashSet<String> second)
	{
		HashSet<String> temp = new HashSet<String>();
		Iterator<String> itf = first.iterator();
		while(itf.hasNext())
		{
			temp.add(itf.next());
		}
		Iterator<String> its = second.iterator();
		while(its.hasNext())
		{
			temp.add(its.next());
		}
		return hasEverything(temp);
	}
	
	public static double support(HashSet<String> ex)
	{
		Set<HashSet<String>> keys =  database.keySet();
		
		HashSet<String> tester = ex;
		
		Iterator<HashSet<String>> dictLoop = keys.iterator();
		Iterator<String> rowLoop;
		Iterator<String> inLoop = tester.iterator();
		String rowx;
		String inx = inLoop.next();
		int count = 0;
		while(dictLoop.hasNext())
		{
			HashSet<String> rowSet = dictLoop.next();
			//System.out.println("On row " + rowSet);
			rowLoop = rowSet.iterator();
			while(rowLoop.hasNext())
			{
				rowx = rowLoop.next(); 
				if(inx.equals(rowx)) //increase inx only if it is inside rowx
				{
					//call function in here pass in the row and t3 
					if(good(rowx, rowLoop,tester)) //if good then add 1 else increase row
					{
						count++;
					}
				}
			}
		}		
		double top = (double) count;
		int bottom = database.size();
		return top/bottom;
	}
	
	public static boolean good(String rowx, Iterator<String> row, HashSet<String> tester)
	{
		Iterator<String> mine = tester.iterator();
		int count = 0;
		boolean once = true;
		int size = tester.size();
		//System.out.println("rowx " + rowx);
		while(mine.hasNext())
		{
			String myString = mine.next();
			if(rowx.equals(myString) && once)
			{
				count++;
				once = false;
			}
		}
		
		mine = tester.iterator();
		
		while(row.hasNext())
		{
			String rowString = row.next();
			while(mine.hasNext())
			{
				String myString = mine.next();
				
				if(myString.equals(rowString))
				{
					count++;
				}
			}
			mine = tester.iterator();
		}

		return size==count;
	}
	
	public static double confidence(HashSet<String> first, HashSet<String> second)
	{
		double top = 0.0;
		double bottom = 0.0;
		HashSet<String> topSet = new HashSet<String>();
		//make a set of the first two
		Iterator<String> itf = first.iterator();
		while(itf.hasNext())
		{
			topSet.add(itf.next());
		}
		Iterator<String> its = second.iterator();
		while(its.hasNext())
		{
			topSet.add(its.next());
		}
		//do support of previous
		top = support(topSet);
		//System.out.println("Top" + top);
		//do support of first
		bottom = support(first);
		//System.out.println("Bottom" + bottom);
		//Divide the numbers
		return (double)top/bottom;
	}
	
	public static double lift(HashSet<String> first, HashSet<String> second)
	{
		double top = 0.0;
		double bl = 0.0;
		double br = 0.0;
		HashSet<String> topSet = new HashSet<String>();
		//make a set of the first two
		Iterator<String> itf = first.iterator();
		while(itf.hasNext())
		{
			topSet.add(itf.next());
		}
		Iterator<String> its = second.iterator();
		while(its.hasNext())
		{
			topSet.add(its.next());
		}
		
		top = support(topSet);
		//System.out.println("Top: " + top);
		bl = support(first);
		//System.out.println("Bl: " + bl);
		br = support(second);
		//System.out.println("Br: " + br);
		
		return (top)/(bl*br);
	}
}