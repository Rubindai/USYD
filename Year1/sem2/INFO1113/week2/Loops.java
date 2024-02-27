public class Loops {
    public static void main(String[] arg){
        int i =0;
        while (i<3){
            System.out.println(i);
            i++;
        }
        System.out.println("\ndo while next");
        int j =0;
        do{
            System.out.println(j);
            j+=1;
        }while(j<3);

        for(int x =0;x<10;x+=1){
            System.out.println(x);
        }
        String[] things={"I","love","math"};
        for (String a:things){
            System.out.println(a);
        }
    }
    
}
//++i increment then assign
//i++ assign then increment