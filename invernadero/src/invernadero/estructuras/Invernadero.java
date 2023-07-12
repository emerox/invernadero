package invernadero.estructuras;
import invernadero.plantas.finales.*;
import invernadero.simulador.Simulador;

    /**
     * @author MauroCordal
     * @version 1.0
     * Representa una estructura donde se almacenan plantas
     */
public class Invernadero extends Huerto{

        public Invernadero(String name) {
            super(name);
        }

        @Override
        public void plants(int tipo) {
                for (int i = 0; i < this.plantas.length; i++) {
                    if (this.plantas[i] == null) {
                        switch (tipo) {
                            case 1:
                                Trigo tri = new Trigo();
                                this.plantas[i] = tri;
                                break;
                            case 2:
                                Arroz arr = new Arroz();
                                this.plantas[i] = arr;
                                break;
                            case 3:
                                Garbanzos gg = new Garbanzos();
                                this.plantas[i] = gg;
                                break;
                            case 4:
                                Lechuga lec = new Lechuga();
                                this.plantas[i] = lec;
                                break;
                            case 5:
                                Patata pat = new Patata();
                                this.plantas[i] = pat;
                                break;
                            case 6:
                                Pimientos pim = new Pimientos();
                                this.plantas[i] = pim;
                                break;
                        }
                        break;
                    }
                }
            }

        @Override
        public void subirNivel() {
            if (nivel == 10) {
                System.out.println("Ya está al nivel maximo!!!");
            }else{
                nivel++;
                System.out.println("El invernadero " + name + " sube a nivel " + nivel + "!!!");
            }
        }

        @Override
        public void showCapacity() {
            System.out.println("invernadero " + this.name + " " + Simulador.porcentaje(getNum(), getTiles()) + "% de capacidad " + this.getNum() + "/" + this.getTiles());
        }
        

        
}
