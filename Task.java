package calendar;
import java.util.Date;

public class Task 
{
	private String name;
	private String type;
	private Date start;
	private Date end;
	private String priority;
	private boolean alarm = false;
	private Date alarmDate;
	
	public Task () {
		name="";
		type="";
		start=null;
		end=null;
		priority="";
		alarm=false;
		alarmDate=null;
	}
	
	public String getName()
	{
		return name;
	}
	public String getType()
	{
		return type;
	}
	public Date getStart()
	{
		return start;
	}
	public Date getEnd()
	{
		return end;
	}
	public String getPriority()
	{
		return priority;
	}
	public boolean getAlarm()
	{
		return alarm;
	}
	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setName(String x)
	{
		name=x;
	}
	public void setType(String x)
	{
		type=x;
	}
	public void setStart(Date x)
	{
		start=x;
	}
	public void setEnd(Date x)
	{
		end=x;
	}
	public void setPriority(String string)
	{
		priority=string;
	}
	public void setAlarm(String x)
	{
		if (x.equals("T"))
		{
			alarm=true;
		}
		else
		{
			alarm=false;
		}
	}
	public void setAlarmDate(Date d) {
		if(alarm==true) {
			alarmDate=d;
		}
	}
	
	public void copy(Task t) {
		name=t.getName();
		type=t.getType();
		start=t.getStart();
		end=t.getEnd();
		priority=t.getPriority();
		alarm=t.getAlarm();
		alarmDate=t.getAlarmDate();
	}
}
