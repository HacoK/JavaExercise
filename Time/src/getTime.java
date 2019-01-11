import java.util.*;
import static java.lang.String.format;

public class getTime {
	public static void main(String[] args){
		args=new String[3];
		args[0]=format("%tc",new Date());
		args[1]=format("%tA,%<tB %<td",new Date());
		args[2]=format("%tr",new Date());
		for(String str:args)
			System.out.println(str);
	}
}
