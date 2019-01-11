package judgement;

public class CheckClear {
	//判断中间一行，可以消除返回true，无法消除返回false
	public static boolean judge(int[][] pattern){
		for(int j=0;j<=5;j++){
			if(pattern[1][j]==pattern[1][j+1])
				if(pattern[1][j]==pattern[1][j+3])
					return true;
		}
		for(int j=2;j<=7;j++){
			if(pattern[1][j]==pattern[1][j+1])
				if(pattern[1][j]==pattern[1][j-2])
					return true;
		}
		for(int l=1;l<8;l++){
			if(pattern[1][l-1]==pattern[1][l+1]&&(pattern[0][l]==pattern[1][l-1]||pattern[2][l]==pattern[1][l+1]))
				return true;
			if((pattern[0][l-1]==pattern[0][l+1]&&pattern[1][l]==pattern[0][l-1])||
					(pattern[2][l+1]==pattern[2][l-1]&&pattern[1][l]==pattern[2][l+1]))
				return true;
		}
		for(int i=0;i<9;i++){
			if(i<2){
				if(pattern[1][i+1]==pattern[1][i+2]&&(pattern[0][i]==pattern[1][i+1]||pattern[2][i]==pattern[1][i+1]))
					return true;
				if((pattern[0][i+1]==pattern[0][i+2]&&pattern[1][i]==pattern[0][i+1])||
						(pattern[2][i+1]==pattern[2][i+2]&&pattern[1][i]==pattern[2][i+1]))
					return true;
			}
			else if(i>6){
				if(pattern[1][i-1]==pattern[1][i-2]&&(pattern[0][i]==pattern[1][i-1]||pattern[2][i]==pattern[1][i-1]))
					return true;
				if((pattern[0][i-1]==pattern[0][i-2]&&pattern[1][i]==pattern[0][i-1])||
						(pattern[2][i-1]==pattern[2][i-2]&&pattern[1][i]==pattern[2][i-1]))
					return true;
			}
			else{
				if((pattern[0][i+1]==pattern[0][i+2]&&pattern[1][i]==pattern[0][i+1])||
						(pattern[2][i+1]==pattern[2][i+2]&&pattern[1][i]==pattern[2][i+1]))
					return true;
				if(pattern[1][i+1]==pattern[1][i+2]&&(pattern[0][i]==pattern[1][i+1]||pattern[2][i]==pattern[1][i+1]))
					return true;
				if((pattern[0][i-1]==pattern[0][i-2]&&pattern[1][i]==pattern[0][i-1])||
						(pattern[2][i-1]==pattern[2][i-2]&&pattern[1][i]==pattern[2][i-1]))
					return true;
				if(pattern[1][i-1]==pattern[1][i-2]&&(pattern[0][i]==pattern[1][i-1]||pattern[2][i]==pattern[1][i-1]))
					return true;
			}
		}
		return false;
	}
	
	//全图判断死局,可以消除返回true，死局返回false
	public static boolean allMap(int[][] pattern){
		int[][] part=new int[3][9];
		boolean result=false;
		for(int i=0;i<9;i++){
			for(int m=0;m<3;m++)
				for(int n=0;n<9;n++){
					if(i==0){
						if(m==0){
							if(n%2==0)
								part[m][n]=8;
							else
								part[m][n]=9;
						}
						else if(m==1){
							part[m][n]=pattern[i][n];
						}
						else if(m==2){
							part[m][n]=pattern[i+1][n];
						}
					}
					else if(i==8){
						if(m==0){
							part[m][n]=pattern[i-1][n];
						}
						else if(m==1){
							part[m][n]=pattern[i][n];
						}
						else if(m==2){
							if(n%2==0)
								part[m][n]=8;
							else
								part[m][n]=9;
						}
					}
					else{
						if(m==0){
							part[m][n]=pattern[i-1][n];
						}
						else if(m==1){
							part[m][n]=pattern[i][n];
						}
						else if(m==2){
							part[m][n]=pattern[i+1][n];
						}
					}
				}
			result=judge(part);
			if(result)
				return true;
		}
		for(int i=0;i<9;i++){
			for(int m=0;m<3;m++)
				for(int n=0;n<9;n++){
					if(i==0){
						if(m==0){
							if(n%2==0)
								part[m][n]=8;
							else
								part[m][n]=9;
						}
						else if(m==1){
							part[m][n]=pattern[n][i];
						}
						else if(m==2){
							part[m][n]=pattern[n][i+1];
						}
					}
					else if(i==8){
						if(m==0){
							part[m][n]=pattern[n][i-1];
						}
						else if(m==1){
							part[m][n]=pattern[n][i];
						}
						else if(m==2){
							if(n%2==0)
								part[m][n]=8;
							else
								part[m][n]=9;
						}
					}
					else{
						if(m==0){
							part[m][n]=pattern[n][i-1];
						}
						else if(m==1){
							part[m][n]=pattern[n][i];
						}
						else if(m==2){
							part[m][n]=pattern[n][i+1];
						}
					}
				}
			    result=judge(part);
				if(result)
					return true;
		}
		return false;
	}
	//判断某一行是否存在未消除元素，存在返回true，否则返回false
	public static boolean bug(int[] pattern){
		for(int i=0;i<=6;i++){
				if(pattern[i]==pattern[i+1]&&pattern[i]==pattern[i+2])
					return true;			
		}
		return false;
	}
    //判断全图是否存在未消除元素，存在返回true，否则返回false
    public static boolean allBug(int[][] pattern){
    	boolean check=false;
    	for(int i=0;i<9;i++){
    		check=bug(pattern[i]);
    		if(check)
    			return true;  
    		for(int j=0;j<=6;j++){
    		if(pattern[j][i]==pattern[j+1][i]&&pattern[j][i]==pattern[j+2][i])
    			return true;
    		}
    	}
    	return false;
    }
    //将需消除元素一律改为0
    public static int[][] changeZero(int[][] map){
    	int[][] temp=new int[9][9];
    	for(int m=0;m<9;m++)
    		for(int n=0;n<9;n++){
    			temp[m][n]=map[m][n];
    		}
    	for(int i=0;i<9;i++){
			for(int j=0;j<=6;j++){
				if(temp[i][j]==temp[i][j+1]&&temp[i][j]==temp[i][j+2]){
					map[i][j]=0;
					map[i][j+1]=0;
					map[i][j+2]=0;
				}
				if(temp[j][i]==temp[j+1][i]&&temp[j][i]==temp[j+2][i]){
					map[j][i]=0;
					map[j+1][i]=0;
					map[j+2][i]=0;
				}
			}			
    	}
    	return map;
    }
    //下落并用随机数填充
    public static int[][] fall(int[][] map){
    	int count;
    	int index;
    	for(int column=0;column<9;column++){
    		count=0;
    		index=8;
    	while(index!=count){
    		if(map[index][column]==0){
    			for(int i=index;i>count;i--){
    				map[i][column]=map[i-1][column];
    			}
    			count++;
    		}
    		else{
    			index--;
    		}
    	}
    	if(map[count][column]==0)
    		count++;
    	for(int j=0;j<count;j++){
    		map[j][column]=(int)(Math.random()*7)+1;
    	}
    	}
    	return map;
    }
}