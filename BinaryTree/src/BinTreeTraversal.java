import java.util.Scanner;

/*Preorder :ABDCEGFHI
 Inorder :  DBAEGCHFI
 Postorder :DBGEHIFCA*/

/*��������������������ǰ�����򡢺������С�
������������ʹ�ö�������������ʽ��ʾ��������������ڵ�Ϊ�գ�
��ʹ���ַ�����null����ʾ���������
          aa
      bb      cc
    dd      ee   ff  
ʹ�����������ʾ��
aa bb cc dd null ee ff 

ע�⣺������ͬһ�У������еڶ�������*/
public class BinTreeTraversal {
	public static void main(String[] args){
		String input;
		System.out.println("Input the node elements:");
		Scanner sc=new Scanner(System.in);
		input=sc.nextLine();
		sc.close();
		int count = 0;
		if (input.length() != 0){
			count++;
		}
		for (int i = 0; i < input.length();i++){
			if (input.charAt(i)==' '){
				count++;
				while (input.charAt(i+1)==' ')
					i++;
			}
		}
		String[] terms = new String[count+1];
		int index=1;
		int i = 0,length=0;
		while (index < count){
			while (input.charAt(i+length)!=' ')
				length++;
			terms[index] = input.substring(i, i+length);
			index++;
			i += (length + 1);
			while (input.charAt(i)==' ')
				i++;
			length = 0;
		}
		terms[index] = input.substring(i);
		System.out.print("PreOrder:");
		PreOrder(terms,count,1);
		System.out.println("");
		System.out.print("InOrder:");
		InOrder(terms,count,1);
		System.out.println("");
		System.out.print("PostOrder:");
		PostOrder(terms,count,1);
	}
	public static void PreOrder(String[] a, int num, int i){
		if (i > num || a[i] .equals("null"))
			return;
		else{
			System.out.print(a[i]+" ");
			PreOrder(a, num, 2 * i);
			PreOrder(a, num, 2 * i + 1);
		}
	}
	public static void InOrder(String[] a, int num, int i){
		if (i > num || a[i] .equals("null"))
			return;
		else{
			InOrder(a, num, 2 * i);
			System.out.print(a[i]+" ");
			InOrder(a, num, 2 * i + 1);
		}
	}
	public static void PostOrder(String[] a, int num, int i){
		if (i > num || a[i] .equals("null"))
			return;
		else{
			PostOrder(a, num, 2 * i);
			PostOrder(a, num, 2 * i + 1);
			System.out.print(a[i]+" ");
		}
	}
}