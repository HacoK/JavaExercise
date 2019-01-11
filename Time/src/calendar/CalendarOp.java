package calendar;
import java.util.Calendar;
import static java.lang.System.out;

public class CalendarOp {
	public static void main(String[] args){
		Calendar c=Calendar.getInstance();
		c.set(2004,1,7,15,40);
		long day1=c.getTimeInMillis();
		day1+=1000*60*60;
		c.setTimeInMillis(day1);
		out.println("new hour:"+c.HOUR_OF_DAY);
		c.add(c.DATE,35);
		out.println("add 35 days:"+c.getTime());
		c.roll(c.DATE,35);
		out.println("roll 35 days:"+c.getTime());
		c.set(c.DATE,1);
		out.println("set to 1:"+c.getTime());
	}

}
