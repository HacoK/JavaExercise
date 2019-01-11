/**
 * 模拟ALU进行整数和浮点数的四则运算
 * @author 161250098_彭俊杰 [请将此处修改为“学号_姓名”]
 *
 */

public class ALU {

	/**
	 * 生成十进制整数的二进制补码表示。<br/>
	 * 例：integerRepresentation("9", 8)
	 * @param number 十进制整数。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length 二进制补码表示的长度
	 * @return number的二进制补码表示，长度为length
	 */
	public String integerRepresentation (String number, int length) {
		// TODO YOUR CODE HERE.
		if(length==0)
			return "";
		if(number=="-2147483648")
			return "10000000000000000000000000000000";
		boolean minus=false;
		long value,i=0;
		StringBuilder bytes=new StringBuilder();
		if(number.charAt(0)=='-'){
			value=Long.parseLong(number.substring(1));
			minus=true;
		}
		else
			value=Long.parseLong(number);
		if(minus){
			while(value%2==0){
				bytes.append("0");
				value=value/2;
				i++;}
			value=value/2;
			bytes.append("1");
			i++;
			for(;i<length;i++){
				if(value%2==0)
					bytes.append("1");
				else
					bytes.append("0");
				value=value/2;
			}
		}
		else{
			for(;i<length;i++){
			if(value%2==0)
				bytes.append("0");
			else
				bytes.append("1");
			value=value/2;}
		}
		bytes.reverse();
		return bytes.toString();
	}
	
	/**
	 * 生成十进制浮点数的二进制表示。
	 * 需要考虑 0、反规格化、正负无穷（“+Inf”和“-Inf”）、 NaN等因素，具体借鉴 IEEE 754。
	 * 舍入策略为向0舍入。<br/>
	 * 例：floatRepresentation("11.375", 8, 11)
	 * @param number 十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return number的二进制表示，长度为 1+eLength+sLength。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String floatRepresentation (String number, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		if(number.equals("NaN")){
			StringBuilder NaN=new StringBuilder();
			for(int i=0;i<(1+eLength+sLength);i++){
				NaN.append('1');
			}return NaN.toString();
		}
		else if(number.equals("+Inf")||number.equals("-Inf")){
			StringBuilder Inf=new StringBuilder();
			if(number.charAt(0)=='+')
				Inf.append('0');
			else
				Inf.append('1');
			for(int i=0;i<eLength;i++){
				Inf.append('1');
				}
			for(int j=0;j<sLength;j++){
				Inf.append('0');
			}return Inf.toString();
		}
		else if((Double.parseDouble(number)==0&&number.charAt(0)!='-')||(Double.parseDouble(number)<Math.pow(2,(2-Math.pow(2,eLength-1)-sLength))&&Double.parseDouble(number)>0))
		{
			StringBuilder zero=new StringBuilder();
			for(int i=0;i<(1+eLength+sLength);i++){
				zero.append('0');
			}return zero.toString();
		}
		else if(Double.parseDouble(number)>-Math.pow(2,(2-Math.pow(2,eLength-1)-sLength))&&Double.parseDouble(number)<=0)
		{
			StringBuilder Neg_zero=new StringBuilder();
			Neg_zero.append('1');
			for(int i=0;i<(eLength+sLength);i++){
				Neg_zero.append('0');
			}return Neg_zero.toString();
		}
		else if(Double.parseDouble(number)>Math.pow(2,Math.pow(2,eLength-1))||Double.parseDouble(number)<-Math.pow(2,Math.pow(2,eLength-1)))
		{
			StringBuilder Inf=new StringBuilder();
			if(Double.parseDouble(number)>0)
				Inf.append('0');
			else
				Inf.append('1');
			for(int i=0;i<eLength;i++){
				Inf.append('1');
				}
			for(int j=0;j<sLength;j++){
				Inf.append('0');
			}return Inf.toString();
		}
		else if(Double.parseDouble(number)>0&&Double.parseDouble(number)<Math.pow(2,(2-Math.pow(2,eLength-1)))){
			StringBuilder Un=new StringBuilder();
			Un.append('0');
			for(int i=0;i<eLength;i++)
				Un.append('0');
			double s=Double.parseDouble(number)*Math.pow(2, (Math.pow(2,eLength-1)-2));
			for(int i=0;i<sLength;i++){
				s*=2;
				if(s<1)
					Un.append('0');
				else{
					Un.append('1');
					s-=1;
				}
			}return Un.toString();
		}
		else if(Double.parseDouble(number)<0&&Double.parseDouble(number)>-Math.pow(2,(2-Math.pow(2,eLength-1)))){
			StringBuilder UnN=new StringBuilder();
			UnN.append('1');
			for(int i=0;i<eLength;i++)
				UnN.append('0');
			double s=-Double.parseDouble(number)*Math.pow(2, (Math.pow(2,eLength-1)-2));
			for(int i=0;i<sLength;i++){
				s*=2;
				if(s<1)
					UnN.append('0');
				else{
					UnN.append('1');
					s-=1;
				}
			}return UnN.toString();
		}
		else if(Double.parseDouble(number)>=1||Double.parseDouble(number)<=-1)
		{boolean minus=false;
		int value,i=0;
		StringBuilder bytes1=new StringBuilder();
		StringBuilder bytes2=new StringBuilder();
		StringBuilder bytes3=new StringBuilder();
		StringBuilder bytes=new StringBuilder();
		if(number.charAt(0)=='-'){
			value=Integer.parseInt(number.substring(1,number.indexOf('.')));
			minus=true;
		}
		else
			value=Integer.parseInt(number.substring(0,number.indexOf('.')));
		while(value!=0){
			if(value%2==0)
				bytes1.append("0");
			else
				bytes1.append("1");
			value=value/2;}
		bytes1.reverse();
		int exponent=(bytes1.length()-1)+(int)Math.pow(2,eLength-1)-1;
		for(;i<eLength;i++){
			if(exponent%2==0)
				bytes2.append("0");
			else
				bytes2.append("1");
			exponent=exponent/2;}
		bytes2.reverse();
		double remain=Double.parseDouble("0"+number.substring(number.indexOf('.')));
		while(remain!=0)
			if(remain*2>=1)
			{
				bytes3.append("1");
				remain=remain*2-1;
			}
			else{
				bytes3.append("0");
				remain=remain*2;
			}
		if(minus)
			bytes.append("1"+bytes2.toString()+bytes1.substring(1)+bytes3.toString());
		else
			bytes.append("0"+bytes2.toString()+bytes1.substring(1)+bytes3.toString());
		if((i=bytes1.length()+bytes3.length()-1)>sLength)
			return bytes.substring(0,1+eLength+sLength);
		else{
			for(;i<sLength;i++)
			bytes.append("0");
	    }
		return bytes.toString();}
		else
			{boolean minus=false;
			int i=0;
			int count=0;
			double value=0;
			StringBuilder bytes2=new StringBuilder();
			StringBuilder bytes3=new StringBuilder();
			StringBuilder bytes=new StringBuilder();
			if(number.charAt(0)=='-'){
				minus=true;
				value=-Double.parseDouble(number);
			}
			else value=Double.parseDouble(number);
			while(value<1){
				value*=2;
				count++;
				}
			int exponent=(int)Math.pow(2,eLength-1)-1-count;
			for(;i<eLength;i++){
				if(exponent%2==0)
					bytes2.append("0");
				else
					bytes2.append("1");
				exponent=exponent/2;}
			bytes2.reverse();
			double remain=value-1;
			for(i=0;i<sLength;i++)
				if(remain*2>=1)
				{
					bytes3.append("1");
					remain=remain*2-1;
				}
				else{
					bytes3.append("0");
					remain=remain*2;
				}
			if(minus)
				bytes.append("1"+bytes2.toString()+bytes3.toString());
			else
				bytes.append("0"+bytes2.toString()+bytes3.toString());
			
			return bytes.toString();
		}
	}
	
	/**
	 * 生成十进制浮点数的IEEE 754表示，要求调用{@link #floatRepresentation(String, int, int) floatRepresentation}实现。<br/>
	 * 例：ieee754("11.375", 32)
	 * @param number 十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length 二进制表示的长度，为32或64
	 * @return number的IEEE 754表示，长度为length。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String ieee754 (String number, int length) {
		// TODO YOUR CODE HERE.
		if(length==32)
			return floatRepresentation(number,8,23);
		else
			return floatRepresentation(number,11,52);
		
	}
	
	/**
	 * 计算二进制补码表示的整数的真值。<br/>
	 * 例：integerTrueValue("00001001")
	 * @param operand 二进制补码表示的操作数
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 */
	public String integerTrueValue (String operand) {
		// TODO YOUR CODE HERE.
		long value=0;
		long index=1;
		if(operand.charAt(0)=='0'){
			for(int i=operand.length()-1;i>0;i--){
				if(operand.charAt(i)=='1'){
					value+=index;
				}
				index*=2;
			}return Long.toString(value);
		}
		else{
			StringBuilder match=new StringBuilder();
			int i=operand.length()-1;
			while(operand.charAt(i)!='1'){
				match.append('0');
				i--;
			}match.append('1');
			for(i=i-1;i>=0;i--){
				if(operand.charAt(i)=='1')
					match.append('0');
				else
					match.append('1');
			}
		    match.reverse();
		    for(i=match.length()-1;i>=0;i--){
				if(match.charAt(i)=='1'){
					value+=index;
				}
				index*=2;
			}return '-'+Long.toString(value);
		}
	}
	
	/**
	 * 计算二进制原码表示的浮点数的真值。<br/>
	 * 例：floatTrueValue("01000001001101100000", 8, 11)
	 * @param operand 二进制表示的操作数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位。正负无穷分别表示为“+Inf”和“-Inf”， NaN表示为“NaN”
	 */
	public String floatTrueValue (String operand, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		boolean minus=false;
		if(operand.charAt(0)=='1')
			minus=true;
		String e=operand.substring(1,1+eLength);
		String s=operand.substring(1+eLength);
		StringBuilder zeroPre=new StringBuilder();
		StringBuilder onePre=new StringBuilder();
		for(int i=0;i<eLength;i++){
			zeroPre.append('0');
			onePre.append('1');
		}String zero=zeroPre.toString();
		 String one=onePre.toString();
		 StringBuilder sZeroPre=new StringBuilder();
		for(int j=0;j<sLength;j++){
			sZeroPre.append('0');
		}String sZero=sZeroPre.toString();
		if(e.equals(zero)){
			if(s.equals(sZero)){
				if(minus)
					return "-0.0";
				else
					return "0.0";
			}
			else{
				double value=0;
				double index=1;
				for(int i=0;i<s.length();i++){
					index/=2;
					if(s.charAt(i)=='1')
						value+=index;
				}
				double result=value*Math.pow(2, (2-Math.pow(2,eLength-1)));
				if(minus)
				    return "-"+Double.toString(result);
				else
					return Double.toString(result);
			}			
		}
		else if(e.equals(one)){
			if(s.equals(sZero)){
				if(minus)
					return "-Inf";
				else
					return "+Inf";
			}
			else
				return "NaN";
		}
		else{
			int exponent=Integer.parseInt(e,2)-(int)Math.pow(2,eLength-1)+1;
			double value=1;
			double index=1;
			for(int i=0;i<s.length();i++){
				index/=2;
				if(s.charAt(i)=='1')
					value+=index;
			}
			double result=value*Math.pow(2,exponent);
			if(minus)
			    return "-"+Double.toString(result);
			else
				return Double.toString(result);
		}
	}
	
	/**
	 * 按位取反操作。<br/>
	 * 例：negation("00001001")
	 * @param operand 二进制表示的操作数
	 * @return operand按位取反的结果
	 */
	public String negation (String operand) {
		// TODO YOUR CODE HERE.
		if(operand==null||operand.length()==0)
			return "";
		StringBuilder negation=new StringBuilder();
		for(int i=0;i<operand.length();i++){
			if(operand.charAt(i)=='0')
				negation.append('1');
			else
				negation.append('0');
		}
		return negation.toString();
	}
	
	/**
	 * 左移操作。<br/>
	 * 例：leftShift("00001001", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 左移的位数
	 * @return operand左移n位的结果
	 */
	public String leftShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		if(operand==null||operand.length()==0)
			return "";
		if(n>=operand.length()){
			StringBuilder zero=new StringBuilder();
			for(int i=0;i<operand.length();i++){
				zero.append('0');
			}
			return zero.toString();
		}
		String s=operand.substring(n);
		StringBuilder result=new StringBuilder(s);
		for(int i=0;i<n;i++)
			result.append('0');
		return result.toString();
	}
	
	/**
	 * 逻辑右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 右移的位数
	 * @return operand逻辑右移n位的结果
	 */
	public String logRightShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		if(operand==null||operand.length()==0)
			return "";
		if(n>=operand.length()){
			StringBuilder zero=new StringBuilder();
			for(int i=0;i<operand.length();i++){
				zero.append('0');
			}
			return zero.toString();
		}
		StringBuilder result=new StringBuilder();
		for(int i=0;i<n;i++)
			result.append('0');
		String s=operand.substring(0,operand.length()-n);
		result.append(s);
		return result.toString();
		
	}
	
	/**
	 * 算术右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 右移的位数
	 * @return operand算术右移n位的结果
	 */
	public String ariRightShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		if(operand==null||operand.length()==0)
			return "";
		boolean minus=false;
		if(operand.charAt(0)=='1')
			minus=true;
		if(n>=operand.length()){
			StringBuilder zero=new StringBuilder();
			StringBuilder one=new StringBuilder();
			for(int i=0;i<operand.length();i++){
				zero.append('0');
				one.append('1');
			}
			if(minus)
				return one.toString();
			else
				return zero.toString();
		}
		StringBuilder result=new StringBuilder();
		for(int i=0;i<n;i++)
			result.append(operand.charAt(0));
		String s=operand.substring(0,operand.length()-n);
		result.append(s);
		return result.toString();
		
	}
	
	/**
	 * 全加器，对两位以及进位进行加法运算。<br/>
	 * 例：fullAdder('1', '1', '0')
	 * @param x 被加数的某一位，取0或1
	 * @param y 加数的某一位，取0或1
	 * @param c 低位对当前位的进位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String fullAdder (char x, char y, char c) {
		// TODO YOUR CODE HERE.
		int sum=(x-0x30)^(y-0x30)^(c-0x30);
		int carry=((x-0x30)&(y-0x30))|((y-0x30)&(c-0x30))|((x-0x30)&(c-0x30));
		String result=Integer.toString(carry)+Integer.toString(sum);
		return result;
	}
	
	/**
	 * 4位先行进位加法器。要求采用{@link #fullAdder(char, char, char) fullAdder}来实现<br/>
	 * 例：claAdder("1001", "0001", '1')
	 * @param operand1 4位二进制表示的被加数
	 * @param operand2 4位二进制表示的加数
	 * @param c 低位对当前位的进位，取0或1
	 * @return 长度为5的字符串表示的计算结果，其中第1位是最高位进位，后4位是相加结果，其中进位不可以由循环获得
	 */
	public String claAdder (String operand1, String operand2, char c) {
		// TODO YOUR CODE HERE.
		/*C1 = G1 + P1C0
          C2 = G2 + P2G1 + P2P1C0 
          C3 = G3 + P3G2 + P3P2G1 + P3P2P1C0 
          C4 = G4 + P4G3 + P4P3G2 + P4P3P2G1 + P4P3P2P1C0
          Pi = Xi + Yi    	Gi = XiYi
		  */
		char[] op1=operand1.toCharArray();
		char[] op2=operand2.toCharArray();
		char[] p=new char[4];
		char[] g=new char[4];
		for(int i=0;i<4;i++){
			p[i]=(char)(op1[i]|op2[i]);
			g[i]=(char)(op1[i]&op2[i]);
		}
		char[] carry=new char[5];
		carry[4]=c;
		carry[3]=(char)(g[3]|(p[3]&c));
		carry[2]=(char)(g[2]|(p[2]&g[3])|(p[2]&p[3]&c));
		carry[1]=(char)(g[1]|(p[1]&g[2])|(p[1]&p[2]&g[3])|(p[1]&p[2]&p[3]&c));
		carry[0]=(char)(g[0]|(p[0]&g[1])|(p[0]&p[1]&g[2])|(p[0]&p[1]&p[2]&g[3])|(p[0]&p[1]&p[2]&p[3]&c));
		StringBuilder result=new StringBuilder();
		result.append(carry[0]);
		for(int i=1;i<=4;i++)
			result.append(fullAdder(op1[i-1],op2[i-1],carry[i]).charAt(1));
		return result.toString();
	}
	
	/**
	 * 加一器，实现操作数加1的运算。
	 * 需要采用与门、或门、异或门等模拟，
	 * 不可以直接调用{@link #fullAdder(char, char, char) fullAdder}、
	 * {@link #claAdder(String, String, char) claAdder}、
	 * {@link #adder(String, String, char, int) adder}、
	 * {@link #integerAddition(String, String, int) integerAddition}方法。<br/>
	 * 例：oneAdder("00001001")
	 * @param operand 二进制补码表示的操作数
	 * @return operand加1的结果，长度为operand的长度加1，其中第1位指示是否溢出（溢出为1，否则为0），其余位为相加结果
	 */
	public String oneAdder (String operand) {
		// TODO YOUR CODE HERE.
		if(integerTrueValue(operand).equals("-1")){
			return integerRepresentation ("0",operand.length()+1);
		}
		else{char[] op=operand.toCharArray();
		int i=0;
		int index=op.length-1;
		int count=1;
		while(op[index-i]=='1'){
			i++;
			count++;
		}
		while(i>=0){
			if(op[index-i]=='0')
				op[index-i]='1';
			else
				op[index-i]='0';
			i--;
		}
		boolean over=false;
		if(count==operand.length())
			over=true;
		String s=String.valueOf(op);
		if(over)
			s='1'+s;
		else
			s='0'+s;
		return s;}
	}
	
	/**
	 * 加法器，要求调用{@link #claAdder(String, String, char)}方法实现。<br/>
	 * 例：adder("0100", "0011", ‘0’, 8)
	 * @param operand1 二进制补码表示的被加数
	 * @param operand2 二进制补码表示的加数
	 * @param c 最低位进位
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String adder (String operand1, String operand2, char c, int length) {
		// TODO YOUR CODE HERE.
		operand1=integerRepresentation(integerTrueValue(operand1),length);
		operand2=integerRepresentation(integerTrueValue(operand2),length);
		String s="";
		for(int i=0;i<length/4;i++){
			s=claAdder(operand1.substring(length-1-4*i-3,length-4*i),operand2.substring(length-1-4*i-3,length-4*i),c).substring(1)+s;
			c=claAdder(operand1.substring(length-1-4*i-3,length-4*i),operand2.substring(length-1-4*i-3,length-4*i),c).charAt(0);
		}
		if(operand1.charAt(0)==operand2.charAt(0)&&operand1.charAt(0)!=s.charAt(0))
			s='1'+s;
		else
			s='0'+s;
		return s;
	}
	
	/**
	 * 整数加法，要求调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerAddition("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被加数
	 * @param operand2 二进制补码表示的加数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String integerAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		return adder(operand1,operand2,'0',length);
	}
	
	/**
	 * 整数减法，可调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerSubtraction("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被减数
	 * @param operand2 二进制补码表示的减数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相减结果
	 */
	public String integerSubtraction (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		operand2=negation(operand2);
		return adder(operand1,operand2,'1',length);
	}
	
	/**
	 * 整数乘法，使用Booth算法实现，可调用{@link #adder(String, String, char, int) adder}等方法。<br/>
	 * 例：integerMultiplication("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被乘数
	 * @param operand2 二进制补码表示的乘数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的相乘结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相乘结果
	 */
	public String integerMultiplication (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
        //multiplicand multiplier
		int Length=Math.max((operand1.length()/4+1)*4,(operand2.length()/4+1)*4);
		while(operand1.length()!=Length)
			operand1=operand1.charAt(0)+operand1;
		while(operand2.length()!=Length)
			operand2=operand2.charAt(0)+operand2;
		String neg_multiplicand=oneAdder(negation(operand1)).substring(1);
		char[] storage=new char[operand2.length()+1];
		storage[0]='0';
		for(int i=1;i<storage.length;i++){
			storage[i]=operand2.charAt(operand2.length()-i);
		}
		char[] result=new char[operand1.length()*2];
		char[] part_sum=new  char[operand1.length()];
		char[] temp=new  char[operand1.length()];
		for(int i=0;i<part_sum.length;i++){
			part_sum[i]='0';
		}
		for(int i=0;i<operand1.length();i++)
		{
			if(storage[i]=='0'&&storage[i+1]=='0'){
				result[i]=part_sum[part_sum.length-1];
				for(int j=0;j<part_sum.length;j++){
					temp[j]=part_sum[j];
				}
				for(int j=1;j<part_sum.length;j++){
					part_sum[j]=temp[j-1];
				}
			}
            if(storage[i]=='1'&&storage[i+1]=='1'){
            	result[i]=part_sum[part_sum.length-1];
				for(int j=0;j<part_sum.length;j++){
					temp[j]=part_sum[j];
				}
				for(int j=1;j<part_sum.length;j++){
					part_sum[j]=temp[j-1];
				}
			}
            if(storage[i]=='1'&&storage[i+1]=='0'){
            	part_sum=(adder(String.valueOf(part_sum),operand1,'0',operand2.length()).substring(1)).toCharArray();
            	result[i]=part_sum[part_sum.length-1];
				for(int j=0;j<part_sum.length;j++){
					temp[j]=part_sum[j];
				}
				for(int j=1;j<part_sum.length;j++){
					part_sum[j]=temp[j-1];
				}
			}
            if(storage[i]=='0'&&storage[i+1]=='1'){
            	part_sum=(adder(String.valueOf(part_sum),neg_multiplicand,'0',operand2.length()).substring(1)).toCharArray();
            	result[i]=part_sum[part_sum.length-1];
				for(int j=0;j<part_sum.length;j++){
					temp[j]=part_sum[j];
				}
				for(int j=1;j<part_sum.length;j++){
					part_sum[j]=temp[j-1];
				}
			}
		}
		for(int i=0;i<operand1.length();i++)
			result[operand1.length()+i]=part_sum[operand1.length()-1-i];
		if(result.length<=length){
			StringBuilder stb=new StringBuilder(String.valueOf(result));
			stb.reverse();
			String product=stb.toString();
			for(int i=0;i<length-result.length;i++)
				product=product.charAt(0)+product;
			return "0"+product;
		}
		else
		{boolean over=true;
		if(integerTrueValue(String.valueOf(result).substring(length-1)).equals("-1")||integerTrueValue(String.valueOf(result).substring(length-1)).equals("0"))
			over=false;
		StringBuilder stb=new StringBuilder(String.valueOf(result).substring(0,length));
		stb.reverse();
		if(over)
			return "1"+stb.toString();
		else
			return "0"+stb.toString();}
	}
	
	/**
	 * 整数的不恢复余数除法，可调用{@link #adder(String, String, char, int) adder}等方法实现。<br/>
	 * 例：integerDivision("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被除数
	 * @param operand2 二进制补码表示的除数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，最后length位为余数
	 */
	public String integerDivision (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		if(integerTrueValue(operand2).equals("0")){
				return "NaN";
		}
		if(integerTrueValue(operand2).equals("-1")){
			if(operand1.length()==length&&oneAdder(negation(operand1)).charAt(0)=='1')
				return "1"+operand1+negation(operand2).substring(0,0+length);
		}
		char sign1=operand1.charAt(0);
		char sign2=operand2.charAt(0);
		boolean neg=false;
		if(sign1!=sign2)
			neg=true;
		if(sign1=='1')
			operand1=oneAdder(negation(operand1)).substring(1);
		if(sign2=='1')
			operand2=oneAdder(negation(operand2)).substring(1);
		String divisor=operand2;
		for(int i=0;i<length-1;i++)
			operand2+='0';
		String neg_operand2=oneAdder(negation(operand2)).substring(1);
		char sign='0';
		String part_quotient=operand1;
		StringBuilder pre_quotient=new StringBuilder();
		boolean zero=false;
		for(int i=0;i<length;i++){
			if(sign=='0'){
				part_quotient=adder(part_quotient,neg_operand2,'0',length*2).substring(1);
				sign=part_quotient.charAt(0);
			}
			else{
				part_quotient=adder(part_quotient,operand2,'0',length*2).substring(1);
				sign=part_quotient.charAt(0);
			}
			if(sign=='0')
				pre_quotient.append('1');
			else
				pre_quotient.append('0');
			if(integerTrueValue(part_quotient).equals("0")){
				zero=true;
				break;
			}
		    neg_operand2=neg_operand2.substring(0,neg_operand2.length()-1);
			operand2=operand2.substring(0,operand2.length()-1);
		}
		String quotient;
		if(zero){
			while(pre_quotient.length()<length)
				pre_quotient.append('0');
			if(neg)
				quotient=oneAdder(negation(pre_quotient.toString())).substring(1);			
			else
				quotient=pre_quotient.toString();
			part_quotient=part_quotient.substring(length);
			String result='0'+quotient+part_quotient;
			return result;
		}
		if(sign=='1')
			part_quotient=adder(part_quotient,divisor,'0',length*2).substring(1);
		if(neg)
			quotient=oneAdder(negation(pre_quotient.toString())).substring(1);			
		else
			quotient=pre_quotient.toString();
		if(sign1=='1')
			part_quotient=oneAdder(negation(part_quotient)).substring(1);
		part_quotient=part_quotient.substring(length);
		String result='0'+quotient+part_quotient;
		return result;
	}
	
	/**
	 * 带符号整数加法，可以调用{@link #adder(String, String, char, int) adder}等方法，
	 * 但不能直接将操作数转换为补码后使用{@link #integerAddition(String, String, int) integerAddition}、
	 * {@link #integerSubtraction(String, String, int) integerSubtraction}来实现。<br/>
	 * 例：signedAddition("1100", "1011", 8)
	 * @param operand1 二进制原码表示的被加数，其中第1位为符号位
	 * @param operand2 二进制原码表示的加数，其中第1位为符号位
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度（不包含符号），当某个操作数的长度小于length时，需要将其长度扩展到length
	 * @return 长度为length+2的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），第2位为符号位，后length位是相加结果
	 */
	public String signedAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		char sign1=operand1.charAt(0);
		char sign2=operand2.charAt(0);
		char sign;
		String sum;
		StringBuilder result=new StringBuilder();
		if(sign1==sign2){
			sign=sign1;
			sum=adder("0"+operand1.substring(1),"0"+operand2.substring(1),'0',length*2);
			if(sum.charAt(length)=='1')
				result.append('1');
			else
				result.append('0');
			result.append(sign);
			result.append(sum.substring(length+1));
		}
		else{
			sum=adder("0"+operand1.substring(1),oneAdder(negation("0"+operand2.substring(1))).substring(1),'0',length*2);
			if(sum.charAt(1)=='0')
				sign=sign1;
			else{
				sign=sign2;
		        sum=oneAdder(negation(sum.substring(1)));
		        }
		        result.append('0');
				result.append(sign);
				result.append(sum.substring(length+1));
			
		}
		return result.toString();
	}
	
	/**
	 * 浮点数加法，可调用{@link #signedAddition(String, String, int) signedAddition}等方法实现。<br/>
	 * 例：floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 二进制表示的被加数
	 * @param operand2 二进制表示的加数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @param gLength 保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相加结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatAddition (String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		String E1=operand1.substring(1,1+eLength);
		String E2=operand2.substring(1,1+eLength);
		String _E=adder("0"+E1,oneAdder(negation("0"+E2)).substring(1),'0',((eLength+1)/4+1)*4).substring(1);
		String E,M;
		String M1,M2;
		if(integerTrueValue(E1).equals("0"))
			M1=integerRepresentation ("0",sLength+1);
		else
			M1="1"+operand1.substring(1+eLength);
		if(integerTrueValue(E2).equals("0"))
			M2=integerRepresentation ("0",sLength+1);
		else
			M2="1"+operand2.substring(1+eLength);
		StringBuilder zero=new StringBuilder();
		for(int i=0;i<eLength+sLength;i++)
			zero.append("0");
		if(_E.charAt(0)=='1'){
			E=E2;
			_E=oneAdder(negation(_E)).substring(1);
			int i=0;
			for(;i<Integer.parseInt(integerTrueValue(_E));i++)
				M1="0"+M1;
			if(i>gLength)
				M1=M1.substring(0,1+sLength+gLength);
			while(M1.length()<1+sLength+gLength)
				M1+="0";
			while(M2.length()<1+sLength+gLength)
				M2+="0";
		}else{
			E=E1;
			int i=0;
			for(;i<Integer.parseInt(integerTrueValue(_E));i++)
				M2="0"+M2;
			if(i>gLength)
				M2=M2.substring(0,1+sLength+gLength);
			while(M1.length()<1+sLength+gLength)
				M1+="0";
			while(M2.length()<1+sLength+gLength)
				M2+="0";
		}
		char sign;
		if(operand1.charAt(0)==operand2.charAt(0)){
		    M=signedAddition("0"+M1,"0"+M2,((1+sLength+gLength+1)/4+1)*4);
		    M=M.substring(M.length()-(1+sLength+gLength+1));
		    sign=operand1.charAt(0);
		    }
		else{
			M=signedAddition("0"+M1,"1"+M2,((1+sLength+gLength+1)/4+1)*4).substring(1);
			if(M.charAt(0)=='0')
				sign=operand1.charAt(0);				
			else
				sign=operand2.charAt(0);
			M=M.substring(M.length()-(1+sLength+gLength+1));
			}
		if(integerTrueValue(M).equals("0")){
			return "00"+zero.toString();
		}
		boolean up_over=false;
		boolean down_over=false;
		if(M.charAt(0)=='1'){
			M=M.substring(0, M.length()-1);
			E=oneAdder(E).substring(1);
			if(integerTrueValue(E).equals("-1"))
				up_over=true;
		}
		else{
			int k=0;
			M=M.substring(1);
			while(M.charAt(k)=='0'){
				k++;
			}M=leftShift(M,k);
			while(k>0){
				E=signedAddition("0"+E,"1"+integerRepresentation("1",eLength),(eLength/4+1)*4).substring(2);
				E=E.substring(E.length()-eLength);
				k--;
				if(integerTrueValue(E).equals("0"))
					down_over=true;
			}
		}
		M=M.substring(1,1+sLength);
		StringBuilder result=new StringBuilder();
		if(down_over){
			if(sign=='0')
				return "00"+zero.toString();
			else
				return "01"+zero.toString();
		}
		if(up_over){
			result.append("1");
			result.append(sign);
			result.append(integerRepresentation("-1",eLength));
			result.append(M);
			return result.toString();
		}
		result.append("0");
		result.append(sign);
		result.append(E);
		result.append(M);
		return result.toString();
	}
	
	/**
	 * 浮点数减法，可调用{@link #floatAddition(String, String, int, int, int) floatAddition}方法实现。<br/>
	 * 例：floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 二进制表示的被减数
	 * @param operand2 二进制表示的减数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @param gLength 保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相减结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatSubtraction (String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		if(operand2.charAt(0)=='0')
			operand2="1"+operand2.substring(1);
		else
			operand2="0"+operand2.substring(1);
		return floatAddition(operand1,operand2,eLength,sLength,gLength);
	}
	
	/**
	 * 浮点数乘法，可调用{@link #integerMultiplication(String, String, int) integerMultiplication}等方法实现。<br/>
	 * 例：floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 二进制表示的被乘数
	 * @param operand2 二进制表示的乘数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatMultiplication (String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String sign=null;
		if(operand1.charAt(0)==operand2.charAt(0))
			sign="0";
		else
			sign="1";
		String E1=operand1.substring(1,1+eLength);
		String E2=operand2.substring(1,1+eLength);
		String E,M;
		String M1,M2;
		if(integerTrueValue(E1).equals("0")||integerTrueValue(E2).equals("0"))
			return "0"+sign+integerRepresentation("0",eLength)+integerRepresentation("0",sLength);
		else
		{M1="1"+operand1.substring(1+eLength);
		 M2="1"+operand2.substring(1+eLength);}
		E2=signedAddition("0"+E2,"1"+"0"+integerRepresentation("-1",eLength-1),(eLength/4+1)*4).substring(1);
		E1=signedAddition("0"+E1,integerRepresentation("0",eLength+1),(eLength/4+1)*4).substring(1);
		E=signedAddition(E1,E2,(eLength/4+1)*4);
		if(E.charAt(1)=='1')
			return "0"+sign+integerRepresentation("0",eLength)+integerRepresentation("0",sLength);
		boolean up_flow=false;
		if(!(integerTrueValue(E.substring(0, E.length()-eLength)).equals("0")))
			up_flow=true;
		E=E.substring(E.length()-eLength);
		M=integerMultiplication("0"+M1,"0"+M2,((sLength+2)*2/4+1)*4);
		M=M.substring(M.length()-(sLength+2)*2);
		boolean right_norm=false;
		if(M.charAt(2)=='1')
			right_norm=true;
		if(right_norm){
			E=oneAdder("0"+E);
			if(E.charAt(0)=='1')
				up_flow=true;
			E=E.substring(2);
			M=M.substring(3, 3+sLength);
		}
		else
			M=M.substring(4,4+sLength);
		if(integerTrueValue(E).equals("-1"))
			up_flow=true;
		if(up_flow)
			return "1"+sign+integerRepresentation("-1",eLength)+M;
		return "0"+sign+E+M;
	}
	
	/**
	 * 浮点数除法，可调用{@link #integerDivision(String, String, int) integerDivision}等方法实现。<br/>
	 * 例：floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 二进制表示的被除数
	 * @param operand2 二进制表示的除数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatDivision (String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String sign=null;
		if(operand1.charAt(0)==operand2.charAt(0))
			sign="0";
		else
			sign="1";
		String E1=operand1.substring(1,1+eLength);
		String E2=operand2.substring(1,1+eLength);
		String E,M;
		String M1,M2;
		if(integerTrueValue(E1).equals("0"))
			return "0"+sign+integerRepresentation("0",eLength)+integerRepresentation("0",sLength);
		if(integerTrueValue(E2).equals("0"))
			return "0"+sign+integerRepresentation("-1",eLength)+integerRepresentation("0",sLength);
		M1="1"+operand1.substring(1+eLength);
		M2="1"+operand2.substring(1+eLength);
		E1=signedAddition("0"+E1,"0"+"0"+integerRepresentation("-1",eLength-1),(eLength/4+1)*4).substring(1);
		E2=signedAddition("0"+E2,integerRepresentation("0",eLength+1),(eLength/4+1)*4).substring(1);
		E=signedAddition(E1,"1"+E2.substring(1),(eLength/4+1)*4);
		if(E.charAt(1)=='1')
			return "0"+sign+integerRepresentation("0",eLength)+integerRepresentation("0",sLength);
		boolean up_flow=false;
		if(!(integerTrueValue(E.substring(0, E.length()-eLength)).equals("0")))
			up_flow=true;
		E=E.substring(E.length()-eLength);
		for(int i=0;i<sLength;i++){
			M1=M1+"0";
			M2="0"+M2;
		}
		M=integerDivision("0"+M1,"0"+M2,((sLength+2)*2/4+1)*4);
		M=M.substring(1,M.length()-((sLength+2)*2/4+1)*4);
		M=M.substring(M.length()-(sLength+1));
		int k=0;
		while(M.charAt(k)=='0'){
			k++;
		}M=leftShift(M,k);
		while(k>0){
			E=signedAddition("0"+E,"1"+integerRepresentation("1",eLength),(eLength/4+1)*4).substring(2);
			E=E.substring(E.length()-eLength);
			k--;
			if(integerTrueValue(E).equals("0"))
				return "0"+sign+integerRepresentation("0",eLength)+integerRepresentation("0",sLength);
		}
		M=M.substring(1,1+sLength);
		if(integerTrueValue(E).equals("-1"))
			up_flow=true;
		if(up_flow)
			return "1"+sign+integerRepresentation("-1",eLength)+M;
		return "0"+sign+E+M;
	}
}
