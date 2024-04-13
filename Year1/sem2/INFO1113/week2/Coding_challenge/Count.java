public class Count {
	
	public static int count(int[] array, int element) {
		int i=0;
		for(int a:array){
            if(element==a){
                i++;
            }
        }
        return i;
	}
	
	public static void main(String[] args) {
		
		
	}
	
}