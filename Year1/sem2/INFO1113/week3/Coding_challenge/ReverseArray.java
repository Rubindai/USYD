import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class ReverseArray {
	
	public static void main(String[] args) {
		String input_f=args[0];
		String output_f=args[1];
		String f_input=null;
		try{
		File f_in=new File(input_f);
		Scanner scan = new Scanner(f_in);
		
		f_input=scan.nextLine();

		}catch(FileNotFoundException e){
			System.exit(0);
		}
		String[] process=null;
		process=f_input.split(" ");
		String to_write="";
		for(int i=process.length-1;i>=0;i-=1){
			to_write+=process[i];
			if(i!=0){
				to_write+=" ";
			}
		}
		try{
			PrintWriter writer=new PrintWriter(output_f);
			writer.println(to_write);
			writer.close();

		}catch(FileNotFoundException e){
			System.exit(0);
		}
		System.out.println();
		

		

	}
	
}