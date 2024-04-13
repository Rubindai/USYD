public class Bottle{
    private String name;
    public Bottle(String name){
        this.name=name;
    }
    public void setNmae(String name){
        this.name=name;

    }
    public String getNames(){
        return name;
    }
    public String getName(){
        return "From Bottle class: "+getNames();
    }
}