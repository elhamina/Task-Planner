package calendar;

import java.util.ArrayList;
import java.util.List;

public class Account
{
	private String username;
	private String password;
	//in the interface, the calendar in controller is saved to the account's calendar
	Controller ctrl = new Controller();
	
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}

	public void setUsername(String x)
	{
		username=x;
	}
	public void setPassword(String x)
	{
// we add a comment
		password=x;
	}
}
