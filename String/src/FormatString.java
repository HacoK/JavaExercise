/*Using String's static format() method allows you to
create a formatted string that you can reuse, as
opposed to a one-time print statement. For
example, instead of */
public class FormatString {
	public static void main(String[] args){
	float floatVar=0;
	int intVar=0;
	String stringVar=null;
		System.out.printf("The value of the float " +
			              "variable is %f, while " +
			              "the value of the " +
			              "integer variable is %d, " +
			              "and the string is %s.\n",
			              floatVar, intVar, stringVar); 
        //you can write
		String fs;
		fs = String.format("The value of the float " +
		                   "variable is %f, while " +
		                   "the value of the " +
		                   "integer variable is %d, " +
		                   "and the string is %s.",
		                   floatVar, intVar, stringVar);
		System.out.println(fs);
	}
}
