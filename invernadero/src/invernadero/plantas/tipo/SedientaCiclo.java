package invernadero.plantas.tipo;

import java.util.Random;

import invernadero.plantas.PlantaCiclo;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta que consume mas agua y tiene la capacidad de replantarse sola
     */
public abstract class SedientaCiclo extends PlantaCiclo{

    public SedientaCiclo(CultivoDatos cult) {
        super(cult);
    }

    @Override
    public void grow() {
        ++this.numDias;
        ++diasCiclo;
        if (!this.watered || this.diasCiclo == this.diasToDead) {
            Random ran = new Random();
            if (ran.nextInt(4) != 0 || this.numDias == this.diasToDead){
                kill();
                diasCiclo = 0;
                System.out.println(name + " ha muerto");
            }
        
            if (diasCiclo == this.diasToMature && !dead) {
                this.mature = true;
                System.out.println( name + " ha madurado");
            }
        }else{
            if (this.dead) {
                if (diasCiclo == ciclo ) {
                    replant();
                    diasCiclo = 0;
                    System.out.println(name + " se ha replantado");
                }else{
                    System.out.println(name + " está muerta pero lleva " + diasCiclo + "/" + ciclo + " para replantarse!!!");
                }
            }
        }
        this.watered = false;

    }
}