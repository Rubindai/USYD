import java.util.Scanner;

public class NumberStatisticsCollector {
    public static void main(String[] args) {
        System.out.print("Enter numbers (type 'done' to finish):");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] numarray=input.split(" ");
        String done="done";
        double sum=0;
        double num;
        double small=0;
        double big=0;
        double n=0;
        boolean flag1=true;
        
        for(int i=0;i<numarray.length;i++){
            if (numarray[i].equals(done)){
                break;
            
            }
            else{
                try{
                num=Double.parseDouble(numarray[i]);
                if (flag1){
                    flag1=false;
                    small=num;
                    big=num;
                }
                sum+=num;
                n+=1;
                if(small>num){
                    small=num;
                }
                if(big<num){
                    big=num;
                }

                }
                catch(Exception e){
                    ;
                }
            }
        }
        double avg=sum/n;
        System.out.format("Sum: %.2f",sum);
        System.out.println();
        System.out.format("Average: %.2f",avg);
        System.out.println();
        System.out.format("Smallest: %.2f ",small);
        System.out.println();
        System.out.format("Biggest:%.2f ",big);
    }
}
