
public class Convert_StringsAndNumbers {
	public static void main(String[] args){
		args=new String[]{"3.1415926","2.71828"};
		float a1 = Float.valueOf(args[0]);
		float b1 = (Float.valueOf(args[1])).floatValue();
		//或者
		float a2 = Float.parseFloat(args[0]);
		float b2 = Float.parseFloat(args[1]);
		
		int i=0;
		double d=0;
		// Concatenate "i" with an empty string; conversion is handled for you.
		String s1 = "" + i;
		// The valueOf class method.
		String s2 = String.valueOf(i);
		//Each of the Number subclasses includes a class method, toString(), that will convert its primitive type to a string. For example:
		String s3 = Integer.toString(i);
		String s4 = Double.toString(d); 
		
		System.out.printf("%f %f %f %f %s %s %s %s",a1,a2,b1,b2,s1,s2,s3,s4);
	}

}
