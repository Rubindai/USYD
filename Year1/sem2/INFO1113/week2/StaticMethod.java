class Math1{
    public static int add(int a, int b){
        return a+b;}

    public  int mul(int a, int b){
        return a*b;
    }
}
public class StaticMethod {
    // public static int add(int a, int b){
    //     return a+b;
    // }
    public static void main(String[] arg){
        Math1 a = new Math1();
        System.out.println(Math1.add(10,20));
        System.out.println(a.mul(10,20));
    }
    
}
