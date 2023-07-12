package invernadero.plantas.finales;

import invernadero.plantas.tipo.Hidroponica;
import invernadero.plantas.tipo.Hoja;
import propiedades.AlmacenPropiedades;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta hoja e hidroponica
     */
public class Lechuga extends Hoja implements Hidroponica{

    public Lechuga() {
        super(AlmacenPropiedades.LECHUGA);
    }
    
}
