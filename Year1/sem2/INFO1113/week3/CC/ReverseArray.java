import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class ReverseArray {
	
	public static void main(String[] args) {
		String input_f=args[0];
		String output_f=args[1];
		try{
		File f_in=new File(input_f);
		Scanner scan = new Scanner(f_in);
		String array_in=scan.nextLine();
		}catch(FileNotFoundException e){
			System.exit(0);
		}
		

	}
	
}