public class Contains {
	
	public static boolean contains(int[] a, int element) {
		if (a==null){
            return false;
        }
        int c=0;
        for(int b:a){
            if (b!=element){
                c++;
            }
        }
        if(c!=a.length){
            return true;
        }
		return false;
	}
	
	public static void main(String[] args) {
		
		
		
	}
	
	
}