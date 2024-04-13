import java.util.*;
public class ArrayUnion {

	
	public static int[] union(int[] a, int[] b) {
		if(a==null ||b==null){
            return null;
        }
		
        HashSet<Integer> c=new HashSet<Integer>();
        
        for(int i=0;i<a.length;i+=1){
            c.add(a[i]);
        }
        for(int i=0;i<b.length;i+=1){
            c.add(b[i]);
        }
        int[] to_return=new int[c.size()];
        int k=0;
        for(int d:c){
            to_return[k]=d;
            k+=1;
        }
        
        return to_return;
	}
	
	
	public static void main(String[] args) {

		
		
	}
	
}