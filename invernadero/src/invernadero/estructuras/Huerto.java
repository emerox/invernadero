package invernadero.estructuras;
import java.util.Random;

import estadisticas.Estadisticas;
import invernadero.Arboles.Tipo.Autorregable;
import invernadero.plantas.*;
import invernadero.plantas.tipo.Secano;
import invernadero.plantas.tipo.Sedienta;
import invernadero.plantas.tipo.SedientaCiclo;
import invernadero.plantas.tipo.Sumergida;
import invernadero.plantas.tipo.Triturable;
import invernadero.simulador.Simulador;

    /**
     * @author MauroCordal  
     * @version 1.0
     * Clase abstracta de las estructuras en las que se almacenan plantas o arboles
     */
public abstract class Huerto {

    /**
     * nivel de mejora. Por cada nivel aumenta 10 espacios. Empiexa en 1
     */
    protected int nivel = 1;

    /**
     *nombre del invernadero
     */
    protected String name;
    
    /**
     *las plantas que tiene el invernadero
     */
    protected Plantae[] plantas;

    /**
     * capacidad para 10 plantas por defecto
     * @param name nombre del invernadero.
     */
    protected Huerto(String name) {
        this.name = name;
        plantas = new Plantae[10];
    }

    public int getNivel(){
        return nivel;
    }

    /**
     * @return nombre del invernadero
     */
    public String getName() {
        return name;
    }

    /**
     * @return cantidad plantas plantadas
     */
    public int getNum(){
        int res = 0;
        for (Plantae planta : this.plantas) {
            if (planta != null) {
                res++;
            }
        }
        return res;
    }

    /**
     * @return cantidad espacio para plantas, plantado o vacio 
     */
    public int getTiles(){
        return plantas.length;
    }

    /**
     * @return cantidad cantidad plantas vivas
     */
    public int getAlive(){
        int res = 0;
        for (Plantae planta : plantas) {
            if (planta != null && !planta.isDead()) {
                res++;
            }
        }
        return res;
    }

    /**
     * @return cantidad plantas maduras
     */
    public int getMature() {
        int res = 0;
        for (Plantae planta : plantas) {
            if (planta != null && planta.isMature()) {
                res++;
            }
        }
        return res;
    }

    /**
     * @return cantidad plantas regadas
     */
    public int getWatered() {
        int res = 0;
        for (Plantae planta : plantas) {
            if (planta != null && planta.isWatered()) {
                res++;
            }
        }
        return res;
    }

    /**
     * si no está al maximo sube un nivel
     */
    public abstract void subirNivel();

    /** 
     * @return si la estructura esta llena
     */
    public boolean isLleno() {
        return plantas[plantas.length - 1] != null;
    }

    /**
     * muestra cantidad de plantas segun estado 
     */
    public void showStatus() {
        System.out.println("=============== " + this.name + " ===============");
        System.out.println("Nivel: " + this.nivel + " / 10");
        System.out.println("Ocupacion: " + this.getNum() + "/" + this.getTiles() + " " + Simulador.porcentaje(getNum(), getTiles()) + "%");

        if (getAlive() != 0) {
            System.out.println("Plantas vivas: " + this.getAlive() + "/" + this.getNum() +" "+ Simulador.porcentaje(getAlive(), getNum()) + "%");
            System.out.println("Plantas regadas: " + this.getWatered() + "/" + this.getAlive()+ " " + Simulador.porcentaje(getWatered(), getAlive()) + "%");
            System.out.println("Plantas maduras: " + this.getMature() + "/" + this.getAlive() + " " + Simulador.porcentaje(getMature(), getAlive()) + "%"); 
        }else{
            System.out.println("Plantas vivas: " + 0 + "/" + this.getNum() +" 0%" );
            System.out.println("Plantas regadas: " + 0 + "/" + 0 + " 0%");
            System.out.println("Plantas maduras: " + 0 + "/" + 0 + " 0%");
        }

    }



    /**
     * muestra estado de todas las plantas
     */
    public void showTileStatus() {
        boolean flag = true;
        for (Plantae plt : plantas) {
            if (plt != null) {
                flag = false;
                plt.showStatus();
            }
        }
        if (flag) {
            System.out.println();
            System.out.println("Esto esta vacio");
        }
    }

    /**
     *  muestra cuanto esta ocupado
     */
    public abstract void showCapacity();

    /**
     * riega todas las plantas sin regar llamando a water de cada una y diferenciando las de cada tipo
     * @return cantidad plantas regadas y dinero gastado
     */
    public int[]  waterCrops(int agua) {
        int cont = 0;
        int gastado = 0;
        boolean flag = false;
        Random ran = new Random();
        for (Plantae plt : plantas) {
            flag = false;
            if (plt != null) {
                if (plt.isDead()) {
                }else{              
                    if (plt instanceof Autorregable) {
                        flag = plt.water();
                        if(flag){
                            gastado++;
                            cont++;
                            agua = agua-1;
                        } 
                    }

                    if (plt instanceof Sumergida) {
                        if (agua > 4) {
                            flag = plt.water();
                            if(flag){
                                gastado = gastado + 5;
                                cont++;
                                agua = agua-5;
                            }                        
                        }
                    }

                    if (plt instanceof Secano) {
                        if ( ran.nextInt(4) == 0) {
                            flag = plt.water();
                            if (flag) {
                                cont++;
                            }
                        }else{
                            if (agua > 0) {
                                flag = plt.water();
                                if(flag){
                                    cont++;
                                    gastado++;
                                    agua = agua-1;
                                }
                            }
                        }
                    }

                    if (plt instanceof Sedienta || plt instanceof SedientaCiclo) {
                        if (agua > 1) {
                            flag = plt.water();
                            if(flag){
                                cont++;
                                gastado = gastado + 2;
                                agua = agua-2;
                            }                        
                        }

                    }

                    if (agua > 0 && !flag) {
                        flag = plt.water();
                        if(flag){
                            cont++;
                            gastado++;
                            agua = agua-1;
                        }
                    }else{
                        if (!flag) {
                            System.out.println("no hay agua para regar");
                        }
                    }
                }        
            }
        }
        int[] res = {cont,gastado};
        return res;
    }

    /**
     * crecen todas las plantas llamando a grow de cada una
     */
    public void growCrops() {
        for (Plantae plt : plantas) {
            if (plt != null) {
                plt.grow();
            }
        }
    }

    /**
     * aumenta capacidad del invernadero en 10
     */
    public void upgrade() {
        Plantae[] plantNuev = new Plantae[this.plantas.length + 10];
        System.arraycopy(this.plantas, 0, plantNuev, 0, this.plantas.length);
        this.subirNivel();
        this.plantas = plantNuev;
    }

    /**
     * busca un nulo en plantas y crea una nueva en esa posicion
     */
    public abstract void plants(int tipo);

    /**
     * llama al metodo harvest de cada planta y cuenta las plantas o arboles, los productos cosechados, los productos cosechados que son
     * triturables, las que mueren en el proceso y las monedas ganadas
     */
    public int[] harvest(Estadisticas estats) {
        int contReco = 0;
        int contMuer = 0;
        int contPlant = 0;
        int ganado = 0;
        int contTrit = 0;
        int prod = 0;
        int monedas = 0;
        for (Plantae planta : plantas) {
            if (planta != null) {
                prod = planta.harvest();
                if (planta.isDead()) {
                    contMuer++;
                }
                if(prod != 0){
                    estats.registrarCosecha(planta.getName(), prod);
                    contReco = contReco + prod;
                    contPlant++;
                }
                if (planta instanceof Triturable) {
                    monedas = prod * planta.getGain();
                    contTrit = contTrit + monedas;
                }else{
                    ganado = ganado + prod * planta.getGain();
                }
                
            }
        }
        int res [] = {contReco , contMuer , contPlant , ganado , contTrit};
        return res;
    }

    /**
     *  vuelve todas las plantas a null
     */
    public void unroot() {
        for (int i = 0; i < plantas.length; i++) {
            plantas[i] = null;
        }
    }

    /**
     *  vuelve las plantas muertas a null
     */
    public int plow() {
        int x= 0;
        for (int i = 0; i < plantas.length; i++) {
            if (plantas[i] != null) {
                if (plantas[i].isDead()) {
                    x = x + (plantas[i].getPrice()/2);
                    plantas[i] = null;
                }
            }
        }
        return x;
    }
}
