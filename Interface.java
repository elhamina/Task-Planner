package calendar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Interface
{

	public static void main(String[] args) throws Exception
	{
		Scanner manage = new Scanner(System.in);
		DBmanager database = new DBmanager();
		Controller ctrl = new Controller();
		//creates an account data type and makes sure the account is within the dbmanager
		String x = "";
		boolean loggedIn = false;
		Account thisUser = new Account();
		List<Task> tasks = new ArrayList<Task>();
		while(loggedIn==false)
		{
			System.out.println("enter n for new account or l to log in");
			x=manage.next();
			//System.out.println(x);
			if(x.equals("n"))
			{
				thisUser = database.createAccount();
				loggedIn=true;
			}
			else if (x.equals("l"))
			{
				thisUser =  database.logIn();
				if(thisUser!=null)
					loggedIn=true;
			}
		}
		
		if(thisUser.getUsername().equals("admin")) {
			database.manageAccounts();
		}
		
		else {
			if(loggedIn) {
				tasks=database.getTasks();
			}
			
			//prints the caledar, if the user already has one
			/*System.out.println("\n<Task list>\n");
			ctrl.print(tasks);*/
			Date today=new Date();
			//System.out.println(today);
			LocalDate localDate = today.toInstant().
					atZone(ZoneId.systemDefault()).toLocalDate();
			int month=localDate.getMonthValue();
			int year=localDate.getYear();
			System.out.println("\n**Number on each day means the number of tasks due that day.");
			ctrl.calendar(month, year, tasks);
				
			do{
				System.out.println("Enter the character for the command: ");
				System.out.println("a -calendar display of tasks\nb -blackboard sync\n"
						+ "c -create task\ne -edit (index) task\n"
						+ "d -delete (index) task\nr -remove all tasks\n"
						+ "s -sort\np -print tasks");
				System.out.println("enter x to log off");
				System.out.println("----------------------------------------");
				System.out.println();
				x=manage.next();
				switch(x)
				{
					case "a":
						ctrl.calendarFunction(tasks);
						break;
					case "b":
						ctrl.sync(tasks);
						break;
					case "c":
						ctrl.createTask(tasks);
						break;
					case "e":
						ctrl.print(tasks);
						x="";
						System.out.println("Index of the task you want to edit: ");
						Scanner sc = new Scanner(System.in);
						ctrl.editTask(Integer.parseInt(sc.nextLine()), tasks);
						break;
					case "d":
						ctrl.print(tasks);
						System.out.println("Index of the task you want to remove: ");
						Scanner sc2 = new Scanner(System.in);
						ctrl.deleteTask(Integer.parseInt(sc2.nextLine()), tasks);
						break;
					case "r":
						ctrl.removeAll(tasks);
						break;
					case "s":
						ctrl.sortBy(tasks);
						break;
					case "p":
						//System.out.println(thisUser.myCalendar.size());
						ctrl.print(tasks);
						break;
					case "x":
						System.out.println("Log off. See you!");
						break;
					default:
						System.out.println("Invalid input.");
				}
				//copies the controller calendar to the account
				database.update(thisUser, tasks);
				//tasks = thisUser.ctrl.calendar;
			}while(!x.equals("x"));
			manage.close();
		}
	}
}