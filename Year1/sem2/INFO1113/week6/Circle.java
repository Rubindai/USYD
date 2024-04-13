

public class Circle extends Shape{
    private double radius;
    public Circle(double radius){
        super("circle");
        this.radius=radius;

    }
    public double calculateArea(double radius){
        return 1*radius;
    }
    public double cle(){
        return 2.0;
    }
}
