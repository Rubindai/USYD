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
			double a = Math.round((numa/numb)*1000)/1000.0;
			double b = Math.round((numb/numa)*1000)/1000.0;
			// System.out.println(numa+" "+numb);
			if (a ==ratio || b == ratio){
				System.out.println("\nGolden ratio!");
			}
			else{
				System.out.println("\nMaybe next time.");
			}
		
			}
		
		catch(Exception e){
			
			System.out.println("\nInvalid input.");
		}
	}
}