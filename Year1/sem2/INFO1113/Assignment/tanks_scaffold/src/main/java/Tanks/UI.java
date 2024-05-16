package Tanks;

import java.util.ArrayList;
import java.util.Collection;

import processing.core.PImage;

public class UI {

    private ArrayList<Tankpiece> listOfTankpieces;
    private boolean ifIterate = false;
    private boolean ifUpdateTime = true;
    private float start;
    private float finish;
    private int i = 1;

    /**
     * Constructor for UI
     * 
     * @param listOfTankpieces an arraylist consists of Tankpieces
     */
    public UI(ArrayList<Tankpiece> listOfTankpieces) {

        this.listOfTankpieces = listOfTankpieces;

    }

    /**
     * Sorting tank using the point(in descending order)
     * 
     * @param list an arraylist consists of Tankpieces
     * @return an arraylist consists of Tankpieces in sorted order
     */
    public ArrayList<Tankpiece> sortTank(ArrayList<Tankpiece> list) {
        ArrayList<Tankpiece> new_ArrayList = new ArrayList<Tankpiece>();
        while (list.size() > 0) {
            Tankpiece maxArray = list.get(0);
            int maxNum = maxArray.getPoint();

            for (int i = 1; i < list.size(); i++) {
                Tankpiece tempArray = list.get(i);
                int tempNum = tempArray.getPoint();
                if (tempNum > maxNum) {
                    maxArray = tempArray;
                    maxNum = tempNum;
                }

            }
            new_ArrayList.add(maxArray);
            list.remove(list.indexOf(maxArray));

        }

        return new_ArrayList;
    }

    /**
     * Drawing scoreboard when the game ends
     * 
     * @param app app from App
     */
    public void endGameUI(App app) {

        app.fill(255, 102, 102, 200);
        app.stroke(0);
        app.strokeWeight(4);
        app.rect(250, 150, 400, listOfTankpieces.size() * 40 + 25);
        app.rect(250, 150, 400, 40f);

        app.fill(0);
        app.textSize(20);
        app.text("Final Scores", 260, 175);
        ArrayList<Tankpiece> listOfTankpiecesCopy = new ArrayList<Tankpiece>(listOfTankpieces);
        listOfTankpiecesCopy = sortTank(listOfTankpiecesCopy);

        app.fill(Float.parseFloat(listOfTankpiecesCopy.get(0).getColor()[0]),
                Float.parseFloat(listOfTankpiecesCopy.get(0).getColor()[1]),
                Float.parseFloat(listOfTankpiecesCopy.get(0).getColor()[2]));
        app.text("Player " + listOfTankpiecesCopy.get(0).getCallSign() + " wins!", 260, 130);

        int k = 0;
        finish = System.nanoTime();
        for (; k < i;) {
            app.fill(Float.parseFloat(listOfTankpiecesCopy.get(k).getColor()[0]),
                    Float.parseFloat(listOfTankpiecesCopy.get(k).getColor()[1]),
                    Float.parseFloat(listOfTankpiecesCopy.get(k).getColor()[2]));
            app.text("Player " + listOfTankpiecesCopy.get(k).getCallSign(), 260, 220 + k * 35);

            app.fill(0);
            app.text(listOfTankpiecesCopy.get(k).getPoint(), 550, 220 + k * 35);
            if (ifUpdateTime) {
                // System.out.println("Entered");
                start = System.nanoTime();
                ifUpdateTime = false;

            }

            k += 1;

        }

        if (finish - start > 0.7 * 1000000000 && i < listOfTankpiecesCopy.size()) {
            ifIterate = true;
        }
        if (ifIterate) {

            i += 1;
            ifIterate = false;
            ifUpdateTime = true;
            if (i >= listOfTankpiecesCopy.size()) {
                i = listOfTankpiecesCopy.size();
                ifIterate = false;
                ifUpdateTime = false;

            }
        }

    }

    /**
     * Draw UI for the game
     * 
     * @param app           app from App
     * @param fuel_img      fuel image
     * @param parachute_img parachute image
     * @param current       currentPlayer of the game
     * @param projDisplay   whether to display large projectile
     * @param gameEnd       whether the game end
     */
    public void draw(App app, PImage fuel_img, PImage parachute_img, Tankpiece current, boolean projDisplay,
            boolean gameEnd) {
        app.textSize(18);
        app.text("Player " + current.getCallSign() + "'s turn", 15, 25);

        fuel_img.resize(25, 25);
        app.image(fuel_img, 160, 5);
        app.text(current.getFuel(), 190, 25); // disp fuel

        parachute_img.resize(25, 25);
        app.image(parachute_img, 160, 35);
        app.text(current.getParachute(), 195, 55); // disp parachute

        app.noFill();
        app.stroke(0);
        app.strokeWeight(3);
        app.rect(435, 8, 102 * 1.5f, 25); // disp hp outline

        app.fill(Float.parseFloat(current.getColor()[0]), Float.parseFloat(current.getColor()[1]),
                Float.parseFloat(current.getColor()[2]));
        app.noStroke();
        app.rect(437, 10.25f, current.getHp() * 1.5f, 21.5f);// draw hp rect

        app.strokeWeight(5);
        app.stroke(169, 169, 169);
        app.noFill();
        app.rect(435, 9.5f, current.getPower() * 1.5f, 25);// disp power range on hp

        app.stroke(255, 0, 0);// draw power line
        app.strokeWeight(1);
        app.line(435 + current.getPower() * 1.5f, 4, 435 + current.getPower() * 1.5f, 40);

        app.fill(0);
        app.text("Health:", 365, 25);
        app.text(current.getHp(), 445 + 100 * 1.5f, 27.5f);
        app.text("Power:", 365, 52f);
        app.text(current.getPower(), 430, 52.5f);

        // scoreboard
        if (!gameEnd) {
            app.noFill();
            app.stroke(0);
            app.strokeWeight(4);
            app.rect(715, 52.25f, 140, listOfTankpieces.size() * 20 + 25);
            app.rect(715, 52.25f, 140, 20f);

            app.text("Scores", 720, 69.5f);
            int i = 0;
            for (Tankpiece t : listOfTankpieces) {

                app.fill(Float.parseFloat(t.getColor()[0]), Float.parseFloat(t.getColor()[1]),
                        Float.parseFloat(t.getColor()[2]));
                app.text("Player " + t.getCallSign(), 720, 90 + i * 20);
                i += 1;
                app.fill(0);
                app.text(t.getPoint(), 820, 70 + i * 20);
            }
            if (!projDisplay) {
                app.fill(Float.parseFloat(current.getColor()[0]), Float.parseFloat(current.getColor()[1]),
                        Float.parseFloat(current.getColor()[2]));
                app.text("Large projectile", 100, 100);
            }

        }

    }

}
