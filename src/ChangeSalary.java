import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeSalary {

	public static void main(String[] args) throws FileNotFoundException 
	{

		String csvFile = "/Users/billy/Data mining/Personal-Project/data/StackOverflow Columns deleted commas deleted jobSatisfaction changed Salary.csv";
		String line = "";
		String cvsSplitBy = ",";
		PrintWriter pw = new PrintWriter(new File("Salary Changed.csv"));
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

		    while ((line = br.readLine()) != null) {
		    	
		    	String[] row = line.split(cvsSplitBy);
		    	
		    	
		    	if(row[0].equals("Hobby"))
		    	{
		    		for(String x : row)
		    		{
		    			pw.write(x + ",");
		    		}
		    		pw.write("\n");
		    		continue;
		    	}
		    	
		    	if(row[17].equals("NA"))
		    	{
		    		for(String x : row)
			        {
			        	pw.write(x + ",");
			        }
			        pw.write('\n');
		    		continue;
		    	}
		    	//System.out.println(row[17]);
		    	int salary = Integer.parseInt(row[17]);
		    	
		    	
		    	if(salary >= 0 && salary <= 9999)
		    		row[17] = "0-9999";
		    	if(salary >= 10000 && salary <= 19999)
		    		row[17] = "10000-19999";
		    	if(salary >= 20000 && salary <= 29999)
		    		row[17] = "20000-29999";
		    	if(salary >= 30000 && salary <= 39999)
		    		row[17] = "30000-39999";
		    	if(salary >= 40000 && salary <= 49999)
		    		row[17] = "40000-49999";
		    	if(salary >= 50000 && salary <= 59999)
		    		row[17] = "50000-59999";
		    	if(salary >= 60000 && salary <= 69999)
		    		row[17] = "60000-69999";
		    	if(salary >= 70000 && salary <= 79999)
		    		row[17] = "70000-79999";
		    	if(salary >= 80000 && salary <= 89999)
		    		row[17] = "80000-89999";
		    	if(salary >= 90000 && salary <= 99999)
		    		row[17] = "90000-99999";
		    	if(salary >= 100000 && salary <= 109999)
		    		row[17] = "100000-109999";
		    	if(salary >= 110000 && salary <= 119999)
		    		row[17] = "110000-119999";
		    	if(salary >= 120000 && salary <= 129999)
		    		row[17] = "120000-129999";
		    	if(salary >= 130000 && salary <= 139999)
		    		row[17] = "130000-139999";
		    	if(salary >= 140000 && salary <= 149999)
		    		row[17] = "140000-149999";
		    	if(salary >= 150000 && salary <= 159999)
		    		row[17] = "150000-159999";
		    	if(salary >= 160000 && salary <= 169999)
		    		row[17] = "160000-169999";
		    	if(salary >= 170000 && salary <= 179999)
		    		row[17] = "170000-179999";
		    	if(salary >= 180000 && salary <= 189999)
		    		row[17] = "180000-189999";
		    	if(salary >= 190000 && salary <= 199999)
		    		row[17] = "190000-199999";
		    	if(salary >= 200000 && salary <= 209999)
		    		row[17] = "200000-209999";
		    	if(salary >= 210000 && salary <= 219999)
		    		row[17] = "210000-219999";
		    	if(salary >= 220000 && salary <= 229999)
		    		row[17] = "220000-229999";
		    	if(salary >= 230000 && salary <= 239999)
		    		row[17] = "230000-239999";
		    	if(salary >= 240000 && salary <= 249999)
		    		row[17] = "240000-249999";
		    	if(salary >= 250000 && salary <= 259999)
		    		row[17] = "250000-259999";
		    	if(salary >= 260000 && salary <= 269999)
		    		row[17] = "260000-269999";
		    	if(salary >= 270000 && salary <= 279999)
		    		row[17] = "270000-279999";
		    	if(salary >= 280000 && salary <= 289999)
		    		row[17] = "280000-289999";
		    	if(salary >= 290000 && salary <= 299999)
		    		row[17] = "290000-299999";
		    	if(salary >= 300000 && salary <= 309999)
		    		row[17] = "300000-309999";

		        for(String x : row)
		        {
		        	pw.write(x + ",");
		        }
		        pw.write('\n');
		    }
		} catch (IOException e) {
	    e.printStackTrace();
		}
		pw.close();
	    System.out.println("done!");
		
		
		
	}

}
