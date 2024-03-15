

import java.util.*;

public class BitManager {
    static int len_of_ary;//init len
    static boolean flag_input=false;
    
    
    public static boolean is_valid_index(int index){
        if(index<len_of_ary && index>=0){
            return true;
        }
        else{
            System.out.printf("Invalid index: Index out of bounds or not a number.\n\n");
            return false;
        }
    }
    
    public static ArrayList<Character> convert(String bit){
        ArrayList<Character> a1= new ArrayList<Character>();
        for(int i=0;i<bit.length();i++){
            a1.add(bit.charAt(i));
        }
        return a1;
    }

    public static boolean is_valid_string(int len_of_new_ary,String user_input){
        if(len_of_new_ary!=len_of_ary){
            System.out.printf("Invalid input: Please enter a string of 1s and 0s.\n\n");
            return false;
        }
        else{
            for(int i=0;i<user_input.length();i++){
                if(user_input.charAt(i)!='0' && user_input.charAt(i)!='1' ){
                    System.out.printf("Invalid input: Please enter a string of 1s and 0s.\n\n");
                    return false;
                }
            }
        }
        return true;
    }
    public static ArrayList<Character> init_input(Scanner input_obj){
        String r_input="";
        while(!flag_input){
        String input=input_obj.nextLine();
        len_of_ary=input.length();
        flag_input=is_valid_string(len_of_ary, input);
        r_input=input;
        }
         
        ArrayList<Character> ary=convert(r_input);
        return ary;
    }
    
    public static void main(String[] args) {
        String exit0="exit";
        String change0="change";
        String check="check";
        String naughty="naughty";
        String good="good";
        
        System.out.printf("Welcome to Bitlandia!\n");
        System.out.printf("Initial bit configuration: ") ;
        Scanner input_obj=new Scanner(System.in);
        
        
        ArrayList<Character> init_array=init_input(input_obj);


        
        System.out.printf("\n");
        System.out.printf("\n");

        System.out.printf("Please enter a command (check <string>, change <index> <state>, or exit):\n\n");
        while(true){
            while (true){
        String user_input=input_obj.nextLine();
        // System.out.println(user_input);
        String[] processed_user_input=user_input.split(" ");
        if(processed_user_input[0].equals(exit0)){
            System.out.printf("Exiting program. Goodbye!\n");
            System.exit(0);
        }
        if(processed_user_input[0].equals(change0)){
            if(!processed_user_input[2].equals(good) && !processed_user_input[2].equals(naughty)){
                System.err.print("Invalid argument: For change command, specify 'naughty' or 'good'.\n\n");
                break;
            }
            int index=Integer.parseInt(processed_user_input[1]);
            if(is_valid_index(index)){
                if(processed_user_input[2].equals(good)){
                    init_array.set(len_of_ary-1-index,'0');
                    System.out.printf("Bit at index %d marked as Good\n\n",index);
                }
                if(processed_user_input[2].equals(naughty)){
                    init_array.set(len_of_ary-1-index,'1');
                    System.out.printf("Bit at index %d marked as Naughty\n\n",index);
                }
            }
            else{
                break;
            }
        }
        boolean flag_naughty=false;
        if(processed_user_input[0].equals(check)){
            //missing checking

            String str=processed_user_input[1];
            boolean flag_val=is_valid_string(str.length(),str);
            if(!flag_val){
                break;
            }
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='1'){
                    char temp =init_array.get(i);
                    if(temp=='1'){
                        System.out.printf("Contains Naughty Bits\n\n");
                        flag_naughty=true;
                        break;
                    }
                }
               
            }
            if (!flag_naughty){
                System.out.printf("No Naughty Bits Found\n\n");
            }
        }
            }
//error
    }
}
}
