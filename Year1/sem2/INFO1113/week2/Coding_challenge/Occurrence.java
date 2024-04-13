import java.util.HashMap;
import java.util.Scanner;

public class Occurrence {
	
	public static void main(String[] args) {
		//YOUR CODE HERE
		Scanner scan =new Scanner(System.in);
		String input=scan.nextLine();
		char letter;
		HashMap<Character,Integer> a=new HashMap<Character,Integer>();
		
			a.put('0',0);
			a.put('1',0);
			a.put('2',0);
			a.put('3',0);
			a.put('4',0);
			a.put('5',0);
			a.put('6',0);
			a.put('7',0);
			a.put('8',0);
			a.put('9',0);
		
		for(int i=0;i<input.length();i+=1){
			letter=input.charAt(i);
			if(!a.containsKey(letter)){
				a.put(letter, 1);
			}
			else{
				a.put(letter,a.get(letter)+1);
			}
		}
		scan.close();

		// System.out.println(a);
		System.out.printf("0: %d\n",a.get('0'));
		System.out.printf("1: %d\n",a.get('1'));
		System.out.printf("2: %d\n",a.get('2'));
		System.out.printf("3: %d\n",a.get('3'));
		System.out.printf("4: %d\n",a.get('4'));
		System.out.printf("5: %d\n",a.get('5'));
		System.out.printf("6: %d\n",a.get('6'));
		System.out.printf("7: %d\n",a.get('7'));
		System.out.printf("8: %d\n",a.get('8'));
		System.out.printf("9: %d\n",a.get('9'));
	
	

	   
		
		
	}
	
}