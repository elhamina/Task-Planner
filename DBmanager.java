package calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DBmanager
{
	private List<Account> accounts = new ArrayList<Account>();
	private List<Task> tasks = new ArrayList<Task>();
	//List<Task> tasks=new ArrayList<>();
	
	public DBmanager() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
			String line;
			while((line=br.readLine())!=null) {
				String array[]=line.split("/");
				Account account=new Account();
				account.setUsername(array[0]);
				account.setPassword(array[1]);
				accounts.add(account);
			}
			br.close();
		}
		catch(Exception e) {
			File file = new File("accounts.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write("admin/1234\n");
            output.close();
		}
	}

	public List<Account> getAccounts(){
		return accounts;
	}
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public Account createAccount() throws IOException
	{
		String temp;
		Scanner input = new Scanner(System.in);
		Account created = new Account();
		boolean valid = true;
		System.out.println("Enter username: ");
		do
		{
			valid=true;
			temp = input.nextLine();
			for(int i=0;i<accounts.size();i++)
			{
				if (temp.equals(accounts.get(i).getUsername()))
				{
					valid = false;
				}
			}
			if (valid==false)
			{
				System.out.println("Invalid. Enter username: ");
			}
		}while(valid==false);
		created.setUsername(temp);
		valid=false;
		String password;
		do {
			System.out.println("Enter password: "); //Set the password length
			password=input.nextLine();
			if(password.length()>3)
				valid=true;
			else
				System.out.println("Password is too short. Try again.");
		}while(valid==false);
		created.setPassword(password);
		accounts.add(created);
		FileWriter fw = new FileWriter("accounts.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
	    out.println(created.getUsername()+"/"+created.getPassword());
	    out.close();
	    
	    String fileName=temp+".txt";
	    File file = new File(fileName);
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write("");
        output.close();
		//add the account info to the file.
		//collectTasks(created.getUsername());
		return created;
	}
	
	public Account logIn() throws Exception
	{
		String id;
		Scanner input = new Scanner(System.in);
		Account myAccount = new Account();
		boolean valid = false;
		System.out.println("Enter username: ");
		do
		{
			id = input.next();

			for(int i=0;i<accounts.size();i++)
			{
				if (id.equals(accounts.get(i).getUsername()))
				{
					valid = true;
					myAccount = accounts.get(i);
				}
			}
			if (valid==false)
			{
				System.out.println("Invalid. Enter username: ");
			}
		}while(valid==false);
		System.out.println("Enter password: ");
		String password=input.next();
		if(password.equals(myAccount.getPassword())) {
			try {
				String fileName=id+".txt";
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String line;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				while((line=br.readLine())!=null) {
					String array[]=line.split("/");
					Task task=new Task();
					task.setName(array[0]);
					task.setType(array[1]);
					Date parsed=format.parse(array[2]);
					task.setStart(parsed);
					parsed=format.parse(array[3]);
					task.setEnd(parsed);
					task.setPriority(array[4]);
					task.setCompleted(array[5]);
					tasks.add(task);
					/*String array[]=line.split("/");
					Account account=new Account();
					account.setUsername(array[0]);
					account.setPassword(array[1]);
					accounts.add(account);*/
				}
				br.close();
				return myAccount;
			}
			catch (Exception e) {
				return myAccount;
			}
		}
		else {
			System.out.println("Wrong password.");
			return null;
		}
		
//if someone does not have the password or no users exist and this is selected, it will go on infinitely. needs validation
	}
	
	public void update(Account account, List<Task> tasks) throws Exception {
		String fileName=account.getUsername()+".txt";
		FileWriter fw = new FileWriter(fileName,false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<tasks.size();i++) {
			Task t=tasks.get(i);
			String start=format.format(t.getStart());
			String end=format.format(t.getEnd());
			out.println(t.getName()+"/"+t.getType()+"/"+start+"/"+end+"/"+t.getPriority()+"/"
			+t.getCompleted());
		}
	    out.close();
	}
	
	public void printAccounts() {
		int count=0;
		System.out.println("Accounts list: ");
		for(int i=0;i<accounts.size();i++) {
			Account a=accounts.get(i);
			System.out.println(count+": "+a.getUsername());
			count++;
		}
		System.out.println();
	}
	
	public void manageAccounts() throws Exception {
		String input;
		do {
			System.out.println("e -edit password\nd -delete account"
					+ "\nx -log off");
			Scanner user=new Scanner(System.in);
			input=user.next();
			int index;
			switch(input) {
			case "e":
				printAccounts();
				System.out.print("Type the index of the account: ");
				try {
					Scanner sc=new Scanner(System.in);
					index=Integer.parseInt(sc.nextLine());
					Account a=accounts.get(index);
					String password="";
					boolean valid=false;
					do {
						System.out.println("Enter password: "); //Set the password length
						password=sc.nextLine();
						if(password.length()>3)
							valid=true;
						else
							System.out.print("Password is too short. Try again.");
					}while(valid==false);
					a.setPassword(password);
				}
				catch(Exception e) {
					System.out.println("Invalid input.");
				}
				break;
			case "d":
				printAccounts();
				System.out.print("Type the index of the account: ");
				try {
					Scanner sc2=new Scanner(System.in);
					index=Integer.parseInt(sc2.nextLine());
					String id=accounts.get(index).getUsername();
					accounts.remove(index);
					String fileName=id+".txt";
					File file = new File(fileName);
					BufferedWriter output = new BufferedWriter(new FileWriter(file));
		            output.write("");
		            output.close();
				}
				catch(Exception e) {
					System.out.println("Invalid input.");
				}
				break;
			}
			updateAccounts();
		}while(!input.equals("x"));
		System.out.println("Log off. See you!");
	}
	
	public void updateAccounts() throws Exception {
		FileWriter fw = new FileWriter("accounts.txt",false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		for(int i=0;i<accounts.size();i++) {
			Account a=accounts.get(i);
		    out.println(a.getUsername()+"/"+a.getPassword());
		}
		out.close();
	}
}