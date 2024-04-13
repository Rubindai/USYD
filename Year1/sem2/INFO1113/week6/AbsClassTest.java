

public class AbsClassTest {
    public static void main(String[] args) {
        Circle c =new Circle(2.0);
        System.out.println(c.cle());        Shape[] a={c};
        for(Shape s :a){
            System.out.println(s.cle);
        }
    }
}
