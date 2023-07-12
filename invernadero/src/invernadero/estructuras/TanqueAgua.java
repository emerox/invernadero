package invernadero.estructuras;
import invernadero.simulador.Simulador;
public class TanqueAgua {
     /**
     * cantidad disponible de agua
     */

     private int water = 10;

     /**
      * cantidad de agua maxima actual
      */
     private int maxWater = 20;
 
     /**
      * @return cantidad disponible de agua
      */
     public int getWater() {
         return water;
     }
 
     /**
      * @return cantidad de agua maxima actual
      */
     public int getMax() {
         return maxWater;
     }
 
     /**
      * muestra el estado del tanque
      */
     public void showStatus() {
        System.out.println(toString());
     }

     public String toString(){
        return "Tanque de agua al " + Simulador.porcentaje(this.water, this.maxWater) + "% de su capacidad " + this.water + "/" + this.maxWater;
     }
 
     /**
      * aumenta el agua disponible, si pasa del maximo el tanque se llena al maximo
      * @param water cantidad de agua disponible para añadir
      */
     public int addWater(int water) {
        if (this.water + water > maxWater) {
            int a = this.water;
            this.water = this.maxWater;
            return this.maxWater - a;
        }else{
            this.water = this.water + water;
            return water;
        }
     }
 
     /**
      * capacidad maxima de agua +5 
      */
     public void upgrade() {
         this.maxWater = this.maxWater + 5;
     }
 
     /**
      * resta agua
      * @param num cantidad de agua a restar
      */
     public void usar(int num){
         this.water = this.water - num;
     }
}
