package invernadero.plantas.finales;

import invernadero.plantas.Planta;
import invernadero.plantas.tipo.Triturable;
import propiedades.AlmacenPropiedades;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta que puede producir harina con el molino
     */
public class Trigo extends Planta implements Triturable{

    public Trigo() {
        super(AlmacenPropiedades.TRIGO);
    }
    
}
