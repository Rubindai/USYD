public class Line {
    public static Point2D findPoint(Point2D a,Point2D b,double d){
        double x=a.getX()+(b.getX()-a.getX())*d;
        double y=a.getY()+(b.getY()-a.getY())*d;
        if(d>1||d< -1){
            return null;
        }
        Point2D p=new Point2D(x, y);
        return p;

    }
}
