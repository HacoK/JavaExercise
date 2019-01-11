/*regionMatches（boolean ignoreCase，int toffset，String other，int ooffset，int len）；
regionMatches（int toffset，String other，int ooffset，int len）； 

上述两个方法用来比较两个字符串中指定区域的子串。入口参数中，用toffset和ooffset分别指出当前字符串中的子串起始位置和要与之比较的字符串中的子串起始地址；
len 指出比较长度。前一种方法可区分大写字母和小写字母，如果在 boolean ignoreCase处写 true，表示将不区分大小写，写false则表示将区分大小写。
而后一个方法认为大小写字母有区别。*/
public class RegionMatchesDemo {
	public static void main(String[] args) {
		String searchMe = "Green Eggs and Ham";
		String findMe = "Eggs";
		int searchMeLength = searchMe.length();
		int findMeLength = findMe.length();
		boolean foundIt = false;
		for (int i = 0;i <= (searchMeLength - findMeLength);i++) { 
			 if (searchMe.regionMatches(i, findMe,0,findMeLength)) {
					 foundIt = true;
					 System.out.println(searchMe.substring(i,i+findMeLength));
					 break;}
			 }
		if (!foundIt)
		System.out.println("No match found.");
	}
}


