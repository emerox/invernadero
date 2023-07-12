package invernadero.estructuras;
import invernadero.Arboles.Arbol;
import invernadero.Arboles.Finales.*;
import invernadero.plantas.Plantae;
import invernadero.simulador.Simulador;
    /**
     * @author MauroCordal  
     * @version 1.0
     * Representa una estructura donde se almacenan arboles
     */
public class Arboleda extends Huerto{

    /**
     * si tiene la mejora de sistema de riego por goteo
     */
    private boolean goteo = false;

    public Arboleda(String name) {
        super(name);
        this.plantas = new Arbol[10];
    }

    /**
     * @return si tiene la mejora de riego por goteo 
     */
    public boolean isGoteo(){
        return goteo;
    }

    /**
     * añade la mejora de riego por goteo
     */
    public void gotear(){
        System.out.println("Añadido sistema de riego por goteo en " + super.getName());
        goteo = true;
    }

    public int getPrice(){
        int res = 0;
        for (Plantae arb : plantas) {
            if (arb != null) {
                res = res + arb.getPrice();                
            }
        }
        return res;
    }
    
    @Override
    public void subirNivel() {
        if (nivel == 10) {
            System.out.println("Ya está al nivel maximo!!!");
        }else{
            nivel++;
            System.out.println("La arboleda " + name + " sube a nivel " + nivel + "!!!");
        }
    }

    @Override
    public void plants(int tipo) {
        for (int i = 0; i < this.plantas.length; i++) {
            if (this.plantas[i] == null) {
                switch (tipo) {
                    case 1:
                        Kiwi ki = new Kiwi();
                        this.plantas[i] = ki;
                        break;
                    case 2:
                        Madronho mad = new Madronho();
                        this.plantas[i] = mad;
                        break;
                    case 3:
                        Manzano man = new Manzano();
                        this.plantas[i] = man;
                        break;
                    case 4:
                        Melocotonero mel = new Melocotonero();
                        this.plantas[i] = mel;
                        break;
                    case 5:
                        Naranjo nar = new Naranjo();
                        this.plantas[i] = nar;
                        break;
                }
                break;
            }
        }
    }

    @Override
    public void showCapacity() {
        System.out.println("Arboleda " + this.name + " " + Simulador.porcentaje(getNum(), getTiles()) + "% de capacidad " + this.getNum() + "/" + this.getTiles());
    }
}
