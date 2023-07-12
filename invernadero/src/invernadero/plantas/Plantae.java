package invernadero.plantas;
import java.util.Random;

import propiedades.CultivoDatos;

public abstract class Plantae {
    /**
     *  coste
     */
    protected int price;
    
    /**
     * ganancia por producto
     */
    protected int gain;

    /**
     * maximo de productos
     */
    protected int prodMax;

    /**
     * minimo de productos
     */
    protected int prodMin;

    /**
     * dias que tarda en madurar
     */
    protected int diasToMature;

    /**
     * dias que tarda en morir
     */
    protected int diasToDead;

     /**
     *nombre coloquial
     */
    protected String name;

    /**
     *nombre cientifico
     */
    protected String scientificName;

    /**
     *contador de dias que lleva viva la planta
     */
    protected int numDias = 0;

    /**
     *está regada o no
     */
    protected boolean watered = false;

    /**
     *está muerta o no
     */
    protected boolean dead = false; 

    /**
     *está madura o no
     */
    protected boolean mature = false;

    public Plantae(CultivoDatos cult){
        this.name  = cult.getNombre();
        this.scientificName = cult.getCientifico();
        this.price = cult.getCoste();
        this.gain = cult.getMonedas();
        this.diasToMature = cult.getMadura();
        this.diasToDead = cult.getMuerte();
        this.prodMin = cult.getProdMin();
        this.prodMax = cult.getProdMax();
    }

    /**
     * @param name nombre coloquial
     * @param scientificName nombre cientifico
     */
    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public boolean isWatered() {
        return watered;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isMature() {
        return mature;
    }

    public int getPrice() {
        return price;
    }

    public int getGain() {
        return gain;
    }

    public String getProduct() {
        return name;
    }

    public void showInfoNatura() {
        System.out.println("==========" + this.name + "==========");
        System.out.println("Precio: " + this.price);
        System.out.println("Producto: " + getProduct());
        System.out.println("Número de productos: " + this.prodMax + "/" + this.prodMin);
        System.out.println("Monedas por producto: " + this.gain + "/" + "producto");
        System.out.println("Maduración: " + this.diasToMature);
        System.out.println("Tiempo de vida: " + this.diasToDead);
    }

    public String toString() {
        return this.name + "-" + "Viva:" + (this.dead?"no":"si") + " | " + "Madura " + (this.mature?"si":"no") + " | " + "Regada: " + (this.watered?"si":"no");
    }


    /**
     * muestra por pantalla toda la informacion de la planta
     */
    public void showStatus() {
        System.out.println("---------------" + this.name +  "---------------");
        System.out.println("Edad: " + this.numDias + " dias");
        System.out.println("Viva: " + (this.dead?"no":"si"));
        System.out.println("Regada: " + (this.watered?"si":"no"));
        System.out.println("Madura " + (this.mature?"si":"no"));
    }

    /**
     * convierte watered a true
     * @return si se regó exitosamente
     */
    public boolean water() {
        if (this.dead) {
            return false;
        }
        if (!this.watered){
            this.watered=true;
            System.out.println(this.name + " está regada");
            return true;
        }
        return false;
    }

    /**
     * pasar un dia
     */
    public abstract void grow();

    /**
     *mata y vuelve mature y watered a false
     */
    protected void kill() {
        this.watered = false;
        this.dead = true; 
        this.mature = false;
    }

    /**
     * recolecta si es madura
     *  @return cantidad cosechada
     */
    public int harvest(){
        if (this.mature) {
            kill();
            Random ran = new Random();
            return ran.nextInt(this.prodMin,this.prodMax);
        } else {
            return 0;
        }
    }

    /**
     * reestablece los valores por defecto
     */
    public void replant() {
        this.numDias = 0;
        this.dead = false;
        this.watered = false;
        this.mature = false;
    }
}
