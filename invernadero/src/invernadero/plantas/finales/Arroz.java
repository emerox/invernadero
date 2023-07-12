package invernadero.plantas.finales;

import java.util.Random;

import invernadero.plantas.Planta;
import invernadero.plantas.tipo.Sumergida;
import invernadero.plantas.tipo.Triturable;
import propiedades.AlmacenPropiedades;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta sumergida y triturable
     */
public class Arroz extends Planta implements Sumergida, Triturable{

    public Arroz() {
        super(AlmacenPropiedades.ARROZ);
    }

    @Override
    public void grow() {
        if (!dead){
            ++this.numDias;
            if (!this.watered || this.numDias == this.diasToDead) {
                Random ran = new Random();
                if ( ran.nextInt(4) != 0|| this.numDias == this.diasToDead){
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
