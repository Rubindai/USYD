public class ArrayMerge{
	public static int[][] mergeArrays(int[][] arrayA, int[][] arrayB){
		int[][] a=new int[arrayA.length][arrayA[0].length+arrayB[0].length];
        // System.out.println(arrayA[0].length+arrayB[0].length);
        for(int i=0;i<arrayA.length;i++){
            int k=0;
            for(int j=0;j<arrayA[0].length+arrayB[0].length;j++){
                if(j<arrayA[0].length){
                    a[i][j]=arrayA[i][j];

                }
                else{
                    
                    a[i][j]=arrayB[i][k];
                    k++;
                }
            }
        }
	return a;
    }
    public static void main(String[] args){
        int[][] a= new int [2][];
        a[0]=new int[]{0,1};
        a[1]=new int[]{2,3};
        int[][] b= new int [2][];
        b[0]=new int[]{4,5};
        b[1]=new int[]{6,7};
        int [][]c=mergeArrays(a,b);
    }
}