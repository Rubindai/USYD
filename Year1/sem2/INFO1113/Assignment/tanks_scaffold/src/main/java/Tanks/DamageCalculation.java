package Tanks;

import java.util.ArrayList;

public class DamageCalculation {
    /**
     * Calculating damages produced from projectiles
     * 
     * @param listOfTankpieces an arraylist consists of Tankpieces
     * @param projectile       projectile of type Projectile
     * @param radius           an integer radius, the radius of the explosion
     */
    public static void projectileDamage(ArrayList<Tankpiece> listOfTankpieces, Projectile projectile, int radius) {
        for (Tankpiece tmp : listOfTankpieces) {

            float tmp_radius = (float) Math.sqrt((tmp.getCordX() - projectile.getCordX())
                    * (tmp.getCordX() - projectile.getCordX())
                    + (tmp.getCordY() - projectile.getCordY())
                            * (tmp.getCordY() - projectile.getCordY()));
            if (tmp_radius <= radius) {
                int hp_damage = 0;
                if (radius == 30) {
                    hp_damage = (int) ((-2) * tmp_radius + 60);
                }
                if (radius == 60) {
                    hp_damage = (int) ((-1) * tmp_radius + 60);
                }

                if (projectile.getBelonging() != tmp) {
                    // System.out.println("this is called");
                    projectile.getBelonging().setPoint(hp_damage, tmp);
                    // System.out.println("this is called in APP");

                }
                tmp.setHp(-hp_damage);

            }
        }
    }

    /**
     * Calculating damages produced from projectiles
     * 
     * @param listOfTankpieces an arraylist consists of Tankpieces
     * @param tmp              tmp of type Tankpiece
     * @param radius           an integer radius, the radius of the explosion
     */
    public static void tankDamage(ArrayList<Tankpiece> listOfTankpieces, Tankpiece tmp, int radius) {
        for (Tankpiece tmp1 : listOfTankpieces) {

            float tmp_radius = (float) Math.sqrt((tmp1.getCordX() - tmp.getCordX())
                    * (tmp1.getCordX() - tmp.getCordX())
                    + (tmp1.getCordY() - tmp.getCordY())
                            * (tmp1.getCordY() - tmp.getCordY()));
            if (tmp_radius <= radius) {
                int hp_damage = 0;
                if (radius == 30) {
                    hp_damage = (int) ((-2) * tmp_radius + 60);
                }
                if (radius == 15) {
                    hp_damage = (int) ((-4) * tmp_radius + 60);
                }
                tmp1.setHp(-hp_damage);

            }
        }

    }
}