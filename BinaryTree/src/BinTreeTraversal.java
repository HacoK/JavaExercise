import java.util.Scanner;

/*Preorder :ABDCEGFHI
 Inorder :  DBAEGCHFI
 Postorder :DBGEHIFCA*/

/*输入二叉树，输出该树的前序、中序、后序序列。
二叉树的输入使用二叉树的数组形式表示，在输入中如果节点为空，
则使用字符串“null”表示。如二叉树
          aa
      bb      cc
    dd      ee   ff  
使用如下输入表示：
aa bb cc dd null ee ff 

注意：输入在同一行，不会有第二行输入*/
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