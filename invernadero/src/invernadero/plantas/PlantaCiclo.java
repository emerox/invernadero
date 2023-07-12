package invernadero.plantas;

import java.util.Random;

import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta plantas con la capacidad de replantarse solas despues de morir
     */
public abstract class PlantaCiclo extends Plantae{

    /**
     * contador de los dias que lleva en el ciclo para replantarse
     */
    protected int diasCiclo = 0;

    /**
     * numero de dias que tarda en replantarse despues de morir
     */
    protected int ciclo;

    public PlantaCiclo(CultivoDatos cult) {
        super(cult);
        this.ciclo = cult.getCiclo();
    }

    @Override
    public void showInfoNatura() {
        super.showInfoNatura();
        System.out.println("Ciclo: " + ciclo + " días");
    }

    @Override
    public int harvest() {
        int x = super.harvest();
        if (x == 0) {
            return 0;
        }
        dead = false;
        watered = true;
        diasCiclo = 0;
        return x;
        
    }

    @Override
    public void grow() {
        ++this.numDias;
        ++diasCiclo;
        if (!this.watered || this.numDias == this.diasToDead) {
            Random ran = new Random();
            if (ran.nextBoolean() || this.numDias == this.diasToDead){
                kill();
                diasCiclo = 0;
                System.out.println(name + " ha muerto");
            }
        
            if (diasCiclo == this.diasToMature && !dead) {
                this.mature = true;
                System.out.println( name + " ha madurado");
            }
            this.watered = false;
        }else{
            if (diasCiclo == ciclo) {
                replant();
                diasCiclo = 0;
                System.out.println(name + " se ha replantado");
            }else{
                System.out.println(name + " está muerta pero lleva " + diasCiclo + "/" + ciclo + " para replantarse!!!");
            }
        } 
    }
}
