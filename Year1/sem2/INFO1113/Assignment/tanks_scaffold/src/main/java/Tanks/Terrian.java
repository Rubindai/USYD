package Tanks;

import java.util.*;

import processing.core.PImage;

public class Terrian {

    public ArrayList<float[]> moveAverage(ArrayList<float[]> list) {

        for (int i = 0; i < list.size() - 32; i += 1) {

            int pos = i;
            float x = 0;
            float sumy = 0;
            for (int j = 0; j < 32; j += 1) {
                x = list.get(i)[0];
                sumy += list.get(pos)[1];
                pos += 1;
            }
            float[] temp = { x, sumy / 32 };
            list.set(i, temp);
        }
        // System.out.println(1);
        return list;
    }

    public ArrayList<float[]> sortFromLeftToRight(ArrayList<float[]> list) {
        ArrayList<float[]> newArrayList = new ArrayList<float[]>();
        while (list.size() > 0) {
            float[] minArray = list.get(0);
            float minNum = minArray[0];

            for (int i = 1; i < list.size(); i++) {
                float[] tempArray = list.get(i);
                float temp_num = tempArray[0];
                if (temp_num < minNum) {
                    minArray = tempArray;
                    minNum = temp_num;
                }

            }
            newArrayList.add(minArray);
            list.remove(list.indexOf(minArray));

        }

        return newArrayList;
    }

    public void treeCheck(ArrayList<float[]> listOfPointsTerrian, ArrayList<float[]> listOfPointsTree) {

        int i = 0;
        for (float[] temp : listOfPointsTree) {
            float[] cord = new float[2];
            cord[0] = temp[0];
            float[] temp1 = listOfPointsTerrian.get((int) cord[0]);
            // System.out.println(temp1[1]);
            cord[1] = temp1[1] - 32;
            listOfPointsTree.set(i, cord);
            i += 1;

        }
    }
    public static void terrainDamageProj(ArrayList<float[]> listOfPointsTerrian,ArrayList<Tankpiece> listOfTankpieces,Projectile projectile,int radius){
        for (int i = -radius; i <= radius; i++) {

            float xTemp = (int) projectile.getCordX() + i;
            if (xTemp >= 0) {
                try{
                    float yTemp = listOfPointsTerrian.get((int) xTemp)[1];
                    float yDiff = (float) Math.sqrt(radius * radius - i * i);
                    if (yTemp + yDiff > 700) {
                        yTemp = 700;
                    } else {
                        yTemp = yTemp + yDiff;
                    }

                    float[] tempCord = { xTemp, yTemp };
                    
                    listOfPointsTerrian.set((int) xTemp, tempCord);
                    
                    for (Tankpiece tmp : listOfTankpieces) {
                        if (tmp.getCordX() == xTemp) {
                            if (projectile.getBelonging() != tmp) {
                                tmp.setProjectile(projectile);
                            }
                        }
                    }
            }catch(Exception e){System.out.println("line 90 from terrian");}
            }
        }

    }
    public static void terrainDamageTank(ArrayList<float[]> listOfPointsTerrian,Tankpiece tmp,int radius){
        for (int i = -radius; i <= radius; i++) {

            float xTemp = (int) tmp.getCordX() + i;
            if (xTemp >= 0) {
                try{
                float yTemp = listOfPointsTerrian.get((int) xTemp)[1];
                float yDiff = (float) Math.sqrt(radius * radius - i * i);
                if (yTemp + yDiff > 700) {
                    yTemp = 700;
                } else {
                    yTemp = yTemp + yDiff;
                }

                float[] tempCord = { xTemp, yTemp };
                
                listOfPointsTerrian.set((int) xTemp, tempCord);
                    
                    
                
            }catch(Exception e){System.out.println("line 115 from terrian");}
            }
        }

    }
    public void draw(App app, ArrayList<float[]> listOfPointsTerrian, ArrayList<float[]> listOfPointsTree,
            PImage tree, String color) {

        
        for (float[] tempCord : listOfPointsTerrian) {
            String[] forground = color.split(",");
            app.fill(Float.parseFloat(forground[0]), Float.parseFloat(forground[1]), Float.parseFloat(forground[2]));
            app.noStroke();
            app.rect(tempCord[0], tempCord[1], 1, 732);
        }

        treeCheck(listOfPointsTerrian, listOfPointsTree);
        for (float[] tempCord : listOfPointsTree) {

            app.image(tree, tempCord[0] - 16, tempCord[1], 32, 32);
        }
    }
}
