package calendar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Controller
{
	//scanner is how user enters all data and chooses what to change
	//calendar is the list of tasks we are working with
	Scanner manage = new Scanner(System.in);
	//List<Task> calendar = new ArrayList<Task>();
	
	public void print(List<Task> tasks)
	{
		for (int i=0; i<tasks.size();i++)
		{
			Task x=tasks.get(i);
			System.out.println("Task index: "+i);
			System.out.println("Name: "+x.getName());
			System.out.println("Type: "+x.getType());
			System.out.println("Start date: "+x.getStart());
			System.out.println("End date: "+x.getEnd());
			System.out.println("Priority: "+x.getPriority());
			System.out.println("Completed: "+x.getCompleted());
			System.out.println();
		}
	}
	
	public void sortBy(List<Task> tasks)
	{
		String x="";
		System.out.println("Enter the character for the field you want to sort by: ");
		System.out.println("n -name\ne -end date\np -priority\n");
		System.out.println("enter x to finish");
		x = manage.nextLine();
		switch(x)
		{
		case "n":
			Task temp=new Task();
			for(int i=0;i<tasks.size();i++){
				int min=i;
				for(int j=i+1;j<tasks.size();j++){
					if(tasks.get(j).getName().compareTo(tasks.get(min).
							getName())<0){
						min=j;
					}
				}
				temp.copy(tasks.get(i));
				tasks.get(i).copy(tasks.get(min));
				tasks.get(min).copy(temp);
			}
			break;
		case "e":
			temp=new Task();
			try {
				for(int i=0;i<tasks.size();i++){
					int min=i;
					for(int j=i+1;j<tasks.size();j++){
						if(tasks.get(j).getEnd().compareTo(tasks.get(min).
								getEnd())<0){
							min=j;
						}
					}
					temp.copy(tasks.get(i));
					tasks.get(i).copy(tasks.get(min));
					tasks.get(min).copy(temp);
				}
			}
			catch(Exception e) {
				System.out.println("Exception occured.");
			}
			break;
		case "p":
			temp=new Task();
			for(int i=0;i<tasks.size();i++){
				int min=i;
				for(int j=i+1;j<tasks.size();j++){
					if(tasks.get(j).getPriority().compareTo(tasks.get(min).
							getPriority())<0){
						min=j;
					}
				}
				temp.copy(tasks.get(i));
				tasks.get(i).copy(tasks.get(min));
				tasks.get(min).copy(temp);
			}
			break;
		default:
			System.out.println("Invalid input.");
		}
	}
	
	public void createTask(List<Task> tasks)
	{
		System.out.println("Enter the data for this task in this order: ");
		Task x=new Task();
		System.out.println("name: ");
		x.setName(manage.nextLine());
		System.out.println("type: ");
		x.setType(manage.nextLine());
		try {
			System.out.println("start date (yyyy-MM-dd): ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date=manage.nextLine();
			Date parsed=format.parse(date);
			x.setStart(parsed);
		}
		catch(Exception e) {
			x.setStart(new Date());
			System.out.println("Exception occured.");
		}
		try {
			System.out.println("end date (yyyy-MM-dd): ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date=manage.nextLine();
			Date parsed=format.parse(date);
			x.setEnd(parsed);
		}
		catch(Exception e) {
			x.setEnd(new Date());
			System.out.println("Exception occured.");
		}
		System.out.println("priority(A/B/C): ");
		x.setPriority(manage.nextLine());
		System.out.println("completed(T/F): ");
		x.setCompleted(manage.nextLine());
		tasks.add(x);
	}
	
	public void editTask(int y, List<Task> tasks)
	{
		Task z=tasks.get(y);
		String x;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter the character for the field you want to edit: ");
			System.out.println("n -name: "+z.getName()+"\nt -type: "+z.getType()+
					"\ns -start date: "+z.getStart()+
					"\ne -end date: "+z.getEnd()+"\np -priority: "+z.getPriority()+
					"\nc -competed: "+z.getCompleted());
			System.out.println("enter x to exit");
			x = manage.nextLine();
			switch(x)
			{
				case "n":
					System.out.println("Enter the name.");
					//Scanner sc = new Scanner(System.in);				
					z.setName(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "t":
					System.out.println("Enter the type.");
					//Scanner sc2 = new Scanner(System.in);
					z.setType(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "s":
					try {
					//Scanner sc4 = new Scanner(System.in);
					System.out.println("Enter the start date (yyyy-MM-dd): ");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date=sc.nextLine();
					Date parsed=format.parse(date);
					z.setStart(parsed);
					//sc4.close();
					//calendar.set(y,z);
					}
					catch(Exception e) {
						z.setStart(new Date());
						System.out.println("Exception occured.");
					}
					break;
				case "e":
					try {
					//Scanner sc5 = new Scanner(System.in);
					System.out.println("Enter the end date (yyyy-MM-dd): ");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date=sc.nextLine();
					Date parsed=format.parse(date);
					z.setEnd(parsed);
					//sc5.close();
					//calendar.set(y,z);
					}
					catch(Exception e) {
						z.setEnd(new Date());
						System.out.println("Exception occured.");
					}
					break;
				case "p":
					System.out.println("Enter the priority.");
					//Scanner sc6 = new Scanner(System.in);
					z.setPriority(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "c":
					System.out.println("Enter if the task is completed.");
					//Scanner sc7 = new Scanner(System.in);
					z.setCompleted(sc.nextLine());
					//calendar.set(y,z);
					break;
			}
		}while(!x.equals("x"));
	}
	
	public void deleteTask(int x, List<Task> tasks)
	{
		tasks.remove(x);
	}
	
	public void removeAll(List<Task> tasks) {
		tasks.clear();
	}
	
	public void sync(List<Task> tasks) throws FileNotFoundException
	{
		File file = new File("learn.ics");
		Scanner sc = new Scanner(file);
	    //move through the file and look for string "BEGIN:VEVENT" indicating a task
		while (sc.hasNextLine())
		{
			if(sc.nextLine().equals("BEGIN:VEVENT"))
			{
				addTask(sc, tasks);
			}
		}
		sc.close();
	}
	
	public void addTask(Scanner sc, List<Task> tasks)
	{
		Task x=new Task();
		x.setType("Blackboard Notification");
		sc.nextLine();
		
		//start date
		try {
			String array1[]= sc.nextLine().split(":");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date=array1[1];
			Date parsed=format.parse(date);
			x.setStart(parsed);
		}
		catch(Exception e) {
			x.setStart(new Date());
			System.out.println("Exception occured.");
		}
		//end date
		try {
			String array1[]= sc.nextLine().split(":");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date=array1[1];
			Date parsed=format.parse(date);
			x.setEnd(parsed);
		}
		catch(Exception e) {
			x.setEnd(new Date());
			System.out.println("Exception occured.");
		}
		//name
		String array1[]= sc.nextLine().split(":");
		x.setName(array1[1]);
		//the code for the calendar file does not clearly say what class each task is for
		//each course may have a code number correlating to it, but the user would have to 
		//figure this out reading the file their self
		
		//checks if this task has already been added in previous syncs
		tasks.add(x);
	}
	
	
	public int day(int month, int day, int year) {
        int y = year - (14 - month) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = month + 12 * ((14 - month) / 12) - 2;
        int d = (day + x + (31*m)/12) % 7;
        return d;
    }


    // return true if the given year is a leap year
    public boolean isLeapYear(int year) {
        if  ((year % 4 == 0) && (year % 100 != 0)) return true;
        if  (year % 400 == 0) return true;
        return false;
    }


    public void calendar(int month, int year, List<Task> tasks) {
    	
    	List<Task> tasksOfMonth=getTasksOfMonth(month,year,tasks);
        // months[i] = name of month i
        String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        };


        // days[i] = number of days in month i
        int[] days = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };


        // check for leap year
        if (month == 2 && isLeapYear(year)) days[month] = 29;




        // print calendar header
        System.out.println("   " + months[month] + " " + year);
        System.out.println(" S      M     Tu      W     Th      F      S");


        // starting day
        int d = day(month, 1, year);


        // print the calendar
        for (int i = 0; i < d; i++)
                System.out.print("       ");
        for (int i = 1; i <= days[month]; i++) {
                System.out.printf("%2d", i);
                int tasksOfDay=getTasksOfDay(i,tasksOfMonth);
                if(tasksOfDay<100)
                	System.out.printf("(%2d) ",tasksOfDay);
                else
                	System.out.print("(99) ");
            if (((i + d) % 7 == 0) || (i == days[month])) System.out.println();
        }
        System.out.println();
    }
    
    public List<Task> getTasksOfMonth(int m,int y,List<Task> tasks){
    	List<Task> tl=new ArrayList<>();
    	for(int i=0;i<tasks.size();i++) {
    		Task t=tasks.get(i);
    		LocalDate localDate = t.getEnd().toInstant().
    				atZone(ZoneId.systemDefault()).toLocalDate();
    		int month=localDate.getMonthValue();
    		int year=localDate.getYear();
    		if(m==month&&y==year)
    			tl.add(t);
    	}
    	//print(tl);
    	return tl;
    }
    
    public int getTasksOfDay(int d,List<Task> tasks) {
    	int count=0;
    	for(int i=0;i<tasks.size();i++) {
    		Task t=tasks.get(i);
    		LocalDate localDate = t.getEnd().toInstant().
    				atZone(ZoneId.systemDefault()).toLocalDate();
    		int day=localDate.getDayOfMonth();
    		if(day==d&&t.getCompleted()==false)
    			count++;
    	}
    	return count;
    }
    
    public void calendarFunction(List<Task> tasks) {
    	Date today=new Date();
		LocalDate localDate = today.toInstant().
				atZone(ZoneId.systemDefault()).toLocalDate();
		int month=localDate.getMonthValue();
		int year=localDate.getYear();
    	System.out.println("**Number on each day means the number of tasks due that day.");
		calendar(4, 2018, tasks);
		String input;
		Scanner user=new Scanner(System.in);
		Scanner cal=new Scanner(System.in);
    	do {
    		System.out.println("See another month? y/n");
			input=user.next();
			if(input.equals("y")) {
				System.out.print("Type month: ");
				int m=Integer.parseInt(cal.nextLine());
				System.out.print("Type year: ");
				int y=Integer.parseInt(cal.nextLine());
				calendar(m, y, tasks);
			}
			else if(input.equals("n"));
			else
				System.out.println("Invalid input.");
		}while(!input.equals("n"));
    }
    
}