import java.util.Scanner;

public class Binary {

	// Returns decimal representation of given binary number.
	public static int toDecimal(String b) {

		// TODO
        int sum=0;
        char num;        
        for(int i=b.length()-1;i>=0;i-=1){
            num=b.charAt(b.length()-1-i);
            if(num=='1'){
                sum+=Math.pow(2, i);
            }
            
            
        }
        return sum;
	}

	// Returns whether or not given string is a binary number.
	public static boolean isBinary(String b) {

		// TODO
        for(int i=0;i<b.length();i+=1){
            char to_check=b.charAt(i);
            if(to_check!='0' && to_check!='1'){
                return false;
            }
        }
        return true;

	}

	public static void main(String[] args) {

		// TODO
        System.out.print("Enter binary: ");
        
        Scanner scan =new Scanner(System.in);
        String input =scan.nextLine();
        System.out.println();
        boolean isB=isBinary(input);
        int num;
        if(isB){
            num=toDecimal(input);
            System.out.println(num+" in decimal");
        }else{
            System.out.println("Not binary!");
        }
	}
}