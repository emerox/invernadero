package invernadero.Arboles.Tipo;

import invernadero.Arboles.Arbol;
import propiedades.CultivoDatos;
    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta un arbol con capacidad de regarse solo
     */
public abstract class Autorregable extends Arbol{

    public Autorregable(CultivoDatos cult) {
        super(cult);
    }
}
