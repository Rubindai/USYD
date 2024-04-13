public class BottleFactory {
    public static void main(String[] args) {
        GlassBottle gb=new GlassBottle("cola",true);
        System.out.println(gb.getName());
        Bottle b=new GlassBottle("wow", false);
        System.out.println(b.getName());
        System.out.println(b.isShatter);

    }
}
