package invernadero.plantas.tipo;

import invernadero.plantas.Planta;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta con una pequeña probabilidad de no gastar agua al regar
     */
public abstract class Secano extends Planta{

    public Secano(CultivoDatos cult) {
        super(cult);
    }
    
}
