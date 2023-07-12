package invernadero.plantas.tipo;

import java.util.Random;

import invernadero.plantas.Planta;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta que consume mas agua
     */
public abstract class Sedienta extends Planta{

    public Sedienta(CultivoDatos cult) {
        super(cult);
    }

    @Override
    public void grow() {
        if (!dead){
            ++this.numDias;
            if (!this.watered || this.numDias == this.diasToDead) {
                Random ran = new Random();
                if (ran.nextInt(4) == 0 || this.numDias == this.diasToDead){
                    kill();
                    System.out.println(name + " ha muerto");
                }
            }
            if (this.numDias == this.diasToMature && !dead) {
                this.mature = true;
                System.out.println( name + " ha madurado");
            }
            this.watered = false;
        }else{
            System.out.println("no puedes hacer esto porque " + name + " está muerta");
        }
    }
    
}
