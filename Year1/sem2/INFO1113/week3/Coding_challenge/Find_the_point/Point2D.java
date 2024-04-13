public class Point2D {
    private double x_cord;
    private double y_cord;
    public Point2D(double x,double y){
        this.x_cord=x;
        this.y_cord=y;

    }
    public double getX(){
        return x_cord;

    }
    public double getY(){
        return y_cord;
        
    }
    public double[] getsCoords(){
        double[] to_return={x_cord,y_cord};
        return to_return;
    }
}
