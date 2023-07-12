package invernadero.simulador;
/**
     * @author MauroCordal
     * @version 1.0
     * Clase que representa las monedas y contiene todos los metodos para operar con ellas
     */
public class Monedas {
    /**
     * monedas del jugador
     */
    private int monedas = 100;

    /**
     *  muestra las monedas del jugador
     */
    public void showMonedas() {
        System.out.println("Tienes " + this.monedas + " monedas!!!");
    }

    /**
     * suma una cantidad de monedas definida por param
     * @param gain monedas a sumar
     */
    public void addMonedas(int gain){
        this.monedas = this.monedas + gain;
    }

    public boolean gastable(int price) {
        if ((monedas - price) < 0) {
            System.out.println("Monedas insufiecientes!!!");
            return false;
        }
        return true;
    }

    /**
     * resta monedas 
     * @param price
     */
    public boolean gastar(int price){
        if (this.monedas - price < 0) {
            System.out.println("Monedas insufiecientes!!!");
            return false;
        }else{
            this.monedas = this.monedas - price;
            return true;
        }
    }
    
}
