package invernadero.plantas.tipo;

import invernadero.plantas.Planta;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta que muere instantaneamente si no se riega
     */
public abstract class Hoja extends Planta{

    public Hoja(CultivoDatos cult) {
        super(cult);
    }

    @Override
    public void grow() {
        if (!dead){
            ++this.numDias;
            if (!this.watered || this.numDias == this.diasToDead) {
                    kill();
                    System.out.println(name + " ha muerto");
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
