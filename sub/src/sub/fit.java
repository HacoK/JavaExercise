package sub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class fit {
	public static void main(String args[]){
			File f = new File("data");
			int minute=0;
			int second=0;
			boolean beyond=false;
			try{
				FileReader fr=new FileReader(f);
				BufferedReader br=new BufferedReader(fr);
				String str=null;
				String str1=null;
				String str2=null;
				while((str=br.readLine())!=null){
					str1=str.substring(12,19);
					str2=str.substring(23,30);
					second=Integer.parseInt(str1.substring(5));
					second+=16;
					if(second>60){
						second-=60;
						beyond=true;
					}
					minute=Integer.parseInt(str1.substring(2,4));
					minute-=2;
					if(beyond)
						{
						minute++;
						beyond=false;
						}
					System.out.println("Dialogue: 0,0:"+minute+":"+second+".");	
					second=Integer.parseInt(str2.substring(5));
					second+=16;
					if(second>60){
						second-=60;
						beyond=true;
					}
					minute=Integer.parseInt(str2.substring(2,4));
					minute-=2;
					if(beyond)
						{
						minute++;
						beyond=false;
						}
					System.out.println("0:"+minute+":"+second+".");	
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
}
