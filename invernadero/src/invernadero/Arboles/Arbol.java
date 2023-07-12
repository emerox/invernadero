package invernadero.Arboles;

import java.util.Random;
import invernadero.plantas.Plantae;
import propiedades.CultivoDatos;

    /**
     * @author MauroCordal
     * @version 1.0
     * repesenta una planta
     */
public class Arbol extends Plantae{
    protected int ciclo;
    protected int diasCiclo;

    /**
     * se pone true cuando madura por primera vez
     */
    protected boolean growed = false;

    /**
     * nombre de lo que produce el arbol
     */
    protected String product;
    
    public Arbol(CultivoDatos cult) {
        super(cult);
        this.ciclo = cult.getCiclo();
        this.product = cult.getProducto();
    }
    
    /**
     * @return si maduro por primera vez
     */
    public boolean getGrowed(){
        return this.growed;
    }

    @Override
    public void showInfoNatura() {
        super.showInfoNatura();
        System.out.println("Ciclo: " + ciclo + " días");
    }



    @Override
    public String getProduct() {
        return this.product;
    } 

    @Override
    public void grow() {

        Random ran = new Random();
        if (watered) {
            if (!dead){
                ++this.numDias;
                if (this.numDias >= this.diasToDead && ran.nextInt(21) == 1){
                    kill();
                    System.out.println(name + " ha muerto");
                    return;
                }
                if (this.numDias == this.diasToMature) {
                    this.mature = true;
                    this.growed = true;
                    System.out.println( name + " ha madurado");
                }
                this.watered = false;
            }
        }else{
            if (!dead) {
                System.out.println("no puedes pasar el dia porque " + name + " está sin regar");
            }else{
                if (diasCiclo == ciclo) {
                    replant();
                    diasCiclo = 0;
                    System.out.println(name + " se ha replantado");
                }else{
                    System.out.println(name + " está muerta pero lleva " + diasCiclo + "/" + ciclo + " para replantarse!!!");
                    diasCiclo++;
                }
            }
            if (ran.nextInt(21) == 1){
                kill();
                System.out.println(name + " ha muerto");
            }
        }
    }
}
