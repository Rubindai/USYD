package Tanks;
public class Explosion  implements ExplosionInterface{
    private float expectedRadius;
    private float currRadius=0;
    private float cordX;
    private float cordY;
    public boolean status;
    public Explosion(float cordX,float cordY,float expectedRadius){
        this.cordX=cordX;
        this.cordY=cordY;
        this.expectedRadius=expectedRadius;
    }
    public void explode(App app){   
        if ( currRadius < expectedRadius ) {
            currRadius += expectedRadius/6;
            app.noStroke();

            if (currRadius < expectedRadius) {
                app.noStroke();
                app.fill(255, 0, 0);
                app.ellipse(cordX, cordY, currRadius * 10, currRadius * 10);

            } else {

                app.noStroke();
                app.fill(255, 0, 0);
                app.ellipse(cordX, cordY, 30, 30);

            }

            if (currRadius < expectedRadius*0.5) {
                app.fill(255, 165, 0);
                app.ellipse(cordX, cordY, currRadius * 10, currRadius * 10);

            } else {
                app.fill(255, 165, 0);
                app.ellipse(cordX, cordY, 15, 15);

            }

            if (currRadius < expectedRadius*0.2) {
                app.fill(255, 255, 0);
                app.ellipse(cordX, cordY, currRadius * 10, currRadius * 10);

            } else {
                app.fill(255, 255, 0);
                app.ellipse(cordX, cordY, 6, 6);

            }
        }
        else{
            status=true;
        }
        

    



     
}
}