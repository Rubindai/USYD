import java.util.Scanner;

public class Occurrence {
	
	public static void main(String[] args) {
		//YOUR CODE HERE
	    char letter;
		String input = args[0];
		char zero='0';
		char one ='1';
		char two ='2';
		char three='3';
		char four ='4';
		char five='5';
		char six='6';
		char seven ='7';
		char eight='8';
		char nine='9';
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e =0;
		int f=0;
		int g=0;
		int h=0;
		int j=0;
		int k=0;
		for(int i=0;i<input.length();i++ ){
			letter=input.charAt(i);
			if (letter ==zero){
				a++;

			}
			else if(letter==one){
				b++;
			}
			else if(letter==two){
				c++;
			}
			else if(letter==three){
				d++;
			}
			else if(letter==four){
				e++;
			}
			else if(letter==five){
				f++;
			}
			else if (letter==six){
				g++;
			}
			else if (letter==seven){
				h++;
			}
			else if (letter==eight){
				j++;
			}
			else if(letter==nine){
				k++;
			}


		}
		System.out.printf("0: %d\n",a);
		System.out.printf("1: %d\n",b);
		System.out.printf("2: %d\n",c);
		System.out.printf("3: %d\n",d);
		System.out.printf("4: %d\n",e);
		System.out.printf("5: %d\n",f);
		System.out.printf("6: %d\n",g);
		System.out.printf("7: %d\n",h);
		System.out.printf("8: %d\n",j);
		System.out.printf("9: %d\n",k);
	}
	
}