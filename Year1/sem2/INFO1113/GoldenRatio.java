import java.util.Scanner;
public class GoldenRatio {

	public static void main(String[] args) {
	
		// TODO
		double ratio =1.618;
		System.out.print("Enter two numbers: ");
		Scanner scan = new Scanner(System.in);
		try{
			double numa = scan.nextDouble();
			double numb = scan.nextDouble();
			double a = Math.round((numa/numb)*100)/100;
			double b = Math.round((numb/numa)*100)/100;
			if (a==ratio || b == ratio){
				System.out.println("Golden ratio!");
			}
			else{
				System.out.println("Maybe next time.");
			}
		
			}
		
		catch(Exception e){
			System.out.println("Invalid input.");
		}
	}
}