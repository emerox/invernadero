package invernadero.plantas.tipo;

import java.util.Random;
import invernadero.plantas.Planta;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta con capacidad de replantarse al cosechar
     */
public abstract class Tuberculo extends Planta{

    public Tuberculo(CultivoDatos cult) {
        super(cult);
    }

    @Override
    public int harvest() {
        int res = super.harvest();
        Random ran = new Random();
        if (ran.nextBoolean()) {
            replant();
            System.out.println(name + " se ha replantado sola");
        }
        return res;
    } 
}
