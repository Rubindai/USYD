
public class GlassBottle extends Bottle {
    private boolean isShatter;
    public GlassBottle(String name, boolean isShatter){
        super(name);
        this.isShatter=isShatter;
    }
    public String getName(){
        return "From GlassBottle class: "+getNames();
    }
}
