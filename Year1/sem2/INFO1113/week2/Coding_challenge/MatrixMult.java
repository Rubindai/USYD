
import java.util.*;
public class MatrixMult {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int k=0;
        int width_of_A=0;
        int  height_of_A=0;
        int width_of_B=0;
        int height_of_B=0;
        int[][] array_A=null;
        int[][] array_B=null;
        int [][] array_A_B=null;

        while (scan.hasNextLine()) {
            String input=scan.nextLine();
            String[] a=input.split(" ");
            if(k==0){
                
                if(!a[0].equals(a[3])){
                    System.out.println("Invalid input.");
                    System.exit(0);

                }
                else{
                    width_of_A=Integer.parseInt(a[0]);
                    height_of_A=Integer.parseInt(a[1]);
                    width_of_B=Integer.parseInt(a[2]);
                    height_of_B=Integer.parseInt(a[3]);
                    array_A=new int[height_of_A][width_of_A];
                    array_B=new int[height_of_B][width_of_B];
                    array_A_B=new int[height_of_A][width_of_B];

                    

                }
                k+=1;
                if(width_of_A==0&&height_of_A==0&&width_of_B==0&&height_of_B==0){
                    System.out.println(0);
                    System.exit(0);
                }
            }
            else{
                  if(k<=height_of_A){
                    for(int i=0;i<width_of_A;i+=1){
                        
                        array_A[k-1][i]=Integer.parseInt(a[i]);
                       
                    }
                    k+=1;
                  }
                  else if(k-height_of_A<=height_of_B){
                    for(int i=0;i<width_of_B;i+=1){
                        array_B[k-height_of_A-1][i]=Integer.parseInt(a[i]);
                        
                    }
                    k+=1;
                  }
                  if(k-height_of_A>height_of_B){
                    
                    for(int i=0;i<height_of_A;i++){
                        
                        for(int j=0;j<width_of_B;j++){
                            int sum=0;
                          for(int m=0;m<width_of_A;m+=1){
                            sum+=array_A[i][m]*array_B[m][j];

                          }
                          array_A_B[i][j]=sum;
                        }
                  }
                  break;
                }

            }


            
        }
        for(int i=0;i<array_A_B.length;i+=1){
            for(int j=0;j<array_A_B[i].length;j+=1){
                System.out.print(array_A_B[i][j]);
                if(j<array_A_B[i].length-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        

    }
}
