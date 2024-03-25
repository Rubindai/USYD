public class Toyota extends Car{
    public Toyota(String Brand,String model,String colour,String engine){
        super(Brand,model,colour);
    }
    public void ignition(){
        System.out.println("T starts");
    }
}