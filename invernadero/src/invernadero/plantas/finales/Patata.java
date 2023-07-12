package invernadero.plantas.finales;

import invernadero.plantas.tipo.Industrial;
import invernadero.plantas.tipo.Tuberculo;
import propiedades.AlmacenPropiedades;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta tipo tuberculo con usos industriales
     */
public class Patata extends Tuberculo implements Industrial{

    public Patata() {
        super(AlmacenPropiedades.PATATA);
    }
    
}
