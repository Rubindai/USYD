package Tanks;
import java.util.*;

import processing.core.PImage;
public class Terrian {

    public ArrayList<float[]> moveavrage(ArrayList<float[]> list){
       
        for(int i=0;i<list.size()-32;i+=1){
            
            int pos=i;
            float x=0;
            float sumy=0;
            for(int j=0;j<32;j+=1){
                x=list.get(i)[0];
                sumy+=list.get(pos)[1];
                pos+=1;
            }
            float[] temp={x,sumy/32};
            list.set(i,temp);
        }
        // System.out.println(1);
        return list;
    }
    
    public ArrayList<float[]> sort_from_leftToRight(ArrayList<float[]> list){
        ArrayList<float[]> new_ArrayList=new ArrayList<float[]>();
        while(list.size()>0){
            float[] min_array=list.get(0);
            float min_num=min_array[0];

            for(int i=1;i<list.size();i++){
                float[] temp_array=list.get(i);
                float temp_num=temp_array[0];
                if(temp_num<min_num){
                    min_array=temp_array;
                    min_num=temp_num;
                }


            }
            new_ArrayList.add(min_array);
            list.remove(list.indexOf(min_array));

    }
    
    return new_ArrayList;
}


    public void tree_check(ArrayList<float[]> list_of_points_terrian,ArrayList<float[]> list_of_points_tree){
      
        int i=0;
        for(float[] temp:list_of_points_tree){
            float[] cord=new float[2];
            cord[0]=temp[0];
            float[] temp1=list_of_points_terrian.get((int)cord[0]);
            cord[1]=temp1[1]-32;
            list_of_points_tree.set(i, cord);
            i+=1;

        }    
    }

    public void draw(App app,ArrayList<float[]> list_of_points_terrian,ArrayList<float[]> list_of_points_tree,PImage tree,String color){
       
       
        
        // ArrayList<float[]> list_of_points_terrian=new ArrayList<float[]>(list_of_points_terrian_fromAPP);
        // ArrayList<float[]> list_of_points_tree=new ArrayList<float[]>(list_of_points_tree_fromAPP);
        // list_of_points_terrian=sort_from_leftToRight(list_of_points_terrian);
        // list_of_points_terrian=moveavrage(list_of_points_terrian);
        // list_of_points_terrian=moveavrage(list_of_points_terrian);
       
    //    ArrayList<float[]> a1=new ArrayList<float[]>();
    //    for(int t=0;t<150;t++){
    //     a1.add(list_of_points_terrian.get(t));
    //    }
        for(float[] temp_cord:list_of_points_terrian){
                String[] forground=color.split(",");
                app.fill(Float.parseFloat(forground[0]),Float.parseFloat(forground[1]),Float.parseFloat(forground[2]));
                app.noStroke();
                app.rect(temp_cord[0],temp_cord[1],1,630);
        }

        tree_check(list_of_points_terrian, list_of_points_tree);
        for(float[] temp_cord:list_of_points_tree){
            
            app.image(tree,temp_cord[0]-16,temp_cord[1],32, 32);
        }
    }
}
