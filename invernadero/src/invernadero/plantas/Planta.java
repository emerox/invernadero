package invernadero.plantas;
import java.util.Random;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta
     */
public abstract class Planta extends Plantae{

        public Planta(CultivoDatos cult) {
            super(cult);
        }

        @Override
        public void grow() {
            if (!dead){
                ++this.numDias;
                if (!this.watered || this.numDias == this.diasToDead) {
                    Random ran = new Random();
                    if ( ran.nextBoolean() || this.numDias == this.diasToDead){
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