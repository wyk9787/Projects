import java.io.IOException;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataProcessingHunt {
	
	public static class NuclearPower {
		public String country;
		public int year;
		public int amount;
		
		public NuclearPower(String country, int year, int amount) {
			this.country = country;
			this.year = year;
			this.amount = amount;
		}
		
		public NuclearPower(String s) {
			country = s.substring(0, s.indexOf(','));
			if(country.charAt(0) == '"') {
				country = country.substring(1, country.length() - 1);
			}
			year = Integer.parseInt(s.substring(s.indexOf(',') + 1, s.lastIndexOf(',')));
			amount= Integer.parseInt(s.substring(s.lastIndexOf(',') + 1));
		}
	}
	
	@SuppressWarnings("resource")
	public static void nuclearPowerDataAnalysis() throws IOException{
		System.out.println("*****************************************************************");
		System.out.println("For NuclearPower data set:");
		//First Question
		Stream<String> nuclearData = Files.lines(Paths.get("data", "NuclearPower.csv"));
		int sumOfNuclearWeapons = nuclearData.skip(1)
		 						   		   	 .map(s -> new NuclearPower(s))
		 						   		   	 .filter(o -> o.year == 2014)
		 						   		   	 .mapToInt(o -> o.amount)
		 						   		   	 .sum();
		System.out.println("How many nuclear weapons are there in total in 2014?");
		System.out.println("> " + sumOfNuclearWeapons);
		System.out.println("(Sources: Federation of American Scientists)\n");	
		
		//Second Question
		nuclearData = Files.lines(Paths.get("data", "NuclearPower.csv"));
		int maxAmountNuclearWeapons = nuclearData.skip(1)
		 						   		   	 .map(s -> new NuclearPower(s))
		 						   		   	 .filter(o -> o.year == 2014)
		 						   		   	 .reduce(0, (acc, e) -> (acc < e.amount ? e.amount : acc), (accl, accr) -> accl + accr);
		System.out.println("How many nuclear weapons does one single country own the most in 2014?");
		System.out.println("> " + maxAmountNuclearWeapons);
		System.out.println("(Sources: Federation of American Scientists)\n");	
	
		//Third Question
		nuclearData = Files.lines(Paths.get("data", "NuclearPower.csv"));
		int sumOfAmericanNuclearWeapons = nuclearData.skip(1)
		 						   		   	 .map(s -> new NuclearPower(s))
		 						   		   	 .filter(o -> o.country.equals("United States"))
		 						   		   	 .mapToInt(o -> o.amount)
		 						   		   	 .sum();
		System.out.println("How many nuclear weapons does United States own in average from 1945 to 2014 each year?");
		System.out.println("> " + sumOfAmericanNuclearWeapons / (2014 - 1945 + 1));
		System.out.println("(Sources: Federation of American Scientists)\n");		
	}
	public static class WorkHours {
		public String country;
		public int year;
		public double workHours;
		
		public WorkHours(String country, int year, double workHours) {
			this.country = country;
			this.year = year;
			this.workHours = workHours;
		}
		
		public WorkHours(String s) {
			country = s.substring(0, s.indexOf(','));
			if(country.charAt(0) == '"') {
				country = country.substring(1, country.length() - 1);
			}
			year = Integer.parseInt(s.substring(s.indexOf(',') + 1, s.lastIndexOf(',')));
			workHours = Double.parseDouble(s.substring(s.lastIndexOf(',') + 1));
		}
	}
	

	
	@SuppressWarnings("resource")
	public static void workHoursDataAnalysis() throws IOException{
		System.out.println("*****************************************************************");
		System.out.println("For WorkHours data set:");
		
		//First Question
		Stream<String> workHoursData = Files.lines(Paths.get("data", "WorkHours.csv"));
		double sumOfWorkHours = workHoursData.skip(1)
												 .map(s -> new WorkHours(s))
												 .filter(o -> o.year == 1870)
												 .reduce(0.0, (acc, e) -> acc + e.workHours, (accl, accr) -> accl + accr);		
		workHoursData = Files.lines(Paths.get("data", "WorkHours.csv"));
		long numOfCountries = workHoursData.skip(1)
										   .map(s -> new WorkHours(s))
										   .filter(o -> o.year == 1870)
										   .count();
		System.out.println("All the questions below are only discussing about those countries:\n"
							+ "Australia, Belgium, Canada, Denmark, France, Germany, Ireland, Italy,\n Netherlands, Spain"
							+ "Sweden, Switzerland, United Kingdom, United States\n");
		System.out.println("What's the average work hours per week for those countries in 1870?");
		System.out.println("> " + sumOfWorkHours / numOfCountries);
		System.out.println("(Sources: Huberman & Minns (2007) – The times they are not changin’: Days and hours of work in Old and New Worlds, 1870–2000.)\n");
		
		//Second Question
		workHoursData = Files.lines(Paths.get("data", "WorkHours.csv"));
		sumOfWorkHours = workHoursData.skip(1)
				 					  .map(s -> new WorkHours(s))
				 					  .filter(o -> o.country.equals("United States"))
				 					  .reduce(0.0, (acc, e) -> acc + e.workHours, (accl, accr) -> accl + accr);
		workHoursData = Files.lines(Paths.get("data", "WorkHours.csv"));
		long numOfYears = workHoursData.skip(1)
										   .map(s -> new WorkHours(s))
										   .filter(o -> o.country.equals("United States"))
										   .count();
		System.out.println("What's the average work hours per week for United States between 1870 and 2000");
		System.out.println("> " + sumOfWorkHours / numOfYears);
		System.out.println("(Sources: Huberman & Minns (2007) – The times they are not changin’: Days and hours of work in Old and New Worlds, 1870–2000.)\n");
		
		//Third question
		workHoursData = Files.lines(Paths.get("data", "WorkHours.csv"));
		double result = workHoursData.skip(1)
										 .map(s -> new WorkHours(s))
										 .reduce(0.0, (acc, e) -> (acc < e.workHours ? e.workHours : acc), (accl, accr) -> accl + accr);
		System.out.println("What's the longest work hours per week for those countries between 1870 and 2000?");
		System.out.println("> " + result);
		System.out.println("(Sources: Huberman & Minns (2007) – The times they are not changin’: Days and hours of work in Old and New Worlds, 1870–2000.)\n");
	}

	@SuppressWarnings("resource")
	public static void passwordDataAnalysis() throws IOException{
		System.out.println("For Passwords data set:");
		//First Question
		Stream<String> passwordData = Files.lines(Paths.get("data", "Passwords.txt"));								   
		int count = passwordData.skip(16)
								.limit(20000)
								.filter(s -> s.length() < 8 || s.toLowerCase().equals(s) || s.toUpperCase().equals(s)) 
								.reduce(0, (acc, e) -> acc+1, (accl, accr) -> accl + accr);
		System.out.println("How many vulnerable passwords (fewer than 8 characters or all having the same cases) are there in this data set?");
		System.out.println("> " + count);
		System.out.println("(Sources: dazzlepond.com's password data set)\n");

		//Second Question
		passwordData = Files.lines(Paths.get("data", "passwords.txt"));								   
		count = passwordData.skip(16)
							.limit(20000)
							.map(s -> s.substring(0, 1) + s.substring(s.length() - 1, s.length()))
							.filter(s -> s.charAt(0) == s.charAt(1)) 
							.reduce(0, (acc, e) -> acc+1, (accl, accr) -> accl + accr);
		System.out.println("How many passwords start and end with the same character?");
		System.out.println("> " + count);
		System.out.println("(Sources: dazzlepond.com's password data set)\n");
		
		//Third Question
		passwordData = Files.lines(Paths.get("data", "passwords.txt"));								   
		count = passwordData.skip(16)
							.limit(20000)
							.filter(s -> s.matches(".*[Ff]uck.*") || s.matches(".*[Aa]ss.*") || s.matches(".*[Ss]hit.*"))  
							.reduce(0, (acc, e) -> acc+1, (accl, accr) -> accl + accr);
		System.out.println("How many passwords including naughty words, i.e. f*ck, a*s, sh*t");
		System.out.println("> " + count);
		System.out.println("(Sources: dazzlepond.com's password data set)\n");
	}


	public static void main(String[] args) throws IOException{
		passwordDataAnalysis();
		workHoursDataAnalysis();
		nuclearPowerDataAnalysis();
	}
}
