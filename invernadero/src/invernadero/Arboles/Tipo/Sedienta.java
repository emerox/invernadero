package invernadero.Arboles.Tipo;

import invernadero.Arboles.Arbol;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta un arbol que gasta mas agua al regarse
     */
public abstract class Sedienta extends Arbol{

    public Sedienta(CultivoDatos cult) {
        super(cult);
    }
    
}
