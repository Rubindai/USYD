import java.util.*;
public class CountDuplicates {
	
	
	public static int countDuplicates(int[] a) {
		if(a==null){
            return 0;
        }
        int cont=0;
        ArrayList<Integer> a1= new ArrayList<Integer>();
        for(int i=0;i<a.length;i++){
            cont=0;
            int init=a[i];
            for(int j=i+1;j<a.length;i++){
                if(init==a[j]){
                    for(int k=0;k<a1.size();k++){
                        if(init ==a1.get(k)){
                            cont++;
                        }
                        
                    }
                    if (cont==0){
                        a1.add(init);
                    }
                }
            }
        }
        int num=a1.size();
        return num;
        
		
	}
	
	public static void main(String[] args) {
		
		
	}
	
}