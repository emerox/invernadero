package invernadero.simulador;
import java.util.Random;
import java.util.Scanner;

import estadisticas.Estadisticas;
import invernadero.plantas.finales.*;
import propiedades.AlmacenPropiedades;
import invernadero.Arboles.Finales.*;
import invernadero.estructuras.*;

/**
     * @author MauroCordal
     * @version 1.0
     * Clase donde está el main, donde se enlazan todas las partes del sistema y donde se hace casi toda la logica
     */
public class Simulador{
    
    /**
     *  nombre de las plantas y arboles disponibles
     */
    private String[] nombres = {AlmacenPropiedades.ARROZ.getNombre(),AlmacenPropiedades.GARBANZOS.getNombre(),AlmacenPropiedades.LECHUGA.getNombre(),
    AlmacenPropiedades.PATATA.getNombre(),AlmacenPropiedades.PIMIENTO.getNombre(),AlmacenPropiedades.TRIGO.getNombre(),
    AlmacenPropiedades.KIWI.getNombre(), AlmacenPropiedades.MADRONHO.getNombre(),AlmacenPropiedades.MANZANO.getNombre(),
    AlmacenPropiedades.MELOCOTONERO.getNombre(),AlmacenPropiedades.NARANJO.getNombre()};

    /**
     * si tiene la mejora aspersores
     */
    private boolean aspersores = false;

    /**
     * si tiene la mejora molino
     */
    private boolean molino = false;

    /**
     * instancia de la clase Estadisticas para registrar la cosecha
     */
    private estadisticas.Estadisticas stats;

    /**
     * instancia de la clase monedas
     */
    private Monedas monedas = new Monedas();

    /**
     * contador de los dias que pasaron desde que empezó el juego
     */
    public int numDias = 0;

    /**
     * los invernaderos que tiene comprados.
     * Empieza con uno
     */
    private Invernadero[] invernaderos;

    /**
     * las arboledas compradas
     */
    private Arboleda[] arboledas;

    /**
     * El tanque que tiene el agua para regar.
     * Solo hay uno por partida
     */
    private TanqueAgua tanqueDeAgua = new TanqueAgua();

    /**
     * El nombre de la partida / empresa del jugador
     */
    private String nombreEmpresa;

    /**
     * contador de los productos cosechados en todos los invervaderos
     */
    private int productosCosechados = 0;

    /**
     * metodo auxiliar para calcular porcentajes 
     * @param num1 divisor
     * @param num2 dividendo
     * @return porcentaje de los parametros
     */
    public static int porcentaje(int num1, int num2){
        float dec1 = num1;
        float dec2 = num2;
        return (int) ((dec1/dec2)*100);
    }


    /**
     * pide por teclado el nombre de la empresa y del primer invernadero
     */
    public void init() {
        this.stats = new Estadisticas(this.nombres);
        this.arboledas = new Arboleda[0];
        Scanner sc = new Scanner (System.in);
        System.out.println("Nombre de la empresa: ");
        try {
            this.nombreEmpresa = sc.nextLine();
        } catch (Exception e) {
        }
        this.invernaderos = new Invernadero[1];
        System.out.println("Nombre del invernadero:");
        try {
            this.invernaderos[0] = new Invernadero(sc.nextLine());
        } catch (Exception e) {
        }
    }
    
    /**
     * muestra el texto del menu
     */
    public void menu() {
        System.out.println();
        System.out.println("________________________");
        System.out.println("| 1. Estado general    |");
        System.out.println("| 2. Estado cultivos   |");
        System.out.println("| 3. Informes          |");
        System.out.println("| 4. Naturapedia       |");
        System.out.println("| 5. Pasar día         |");
        System.out.println("| 6. Recolectar agua   |");
        System.out.println("| 7. Regar             |");
        System.out.println("| 8. Plantar           |");
        System.out.println("| 9. Cosechar          |");
        System.out.println("| 10. Desbrozar        |");
        System.out.println("| 11. Arrancar         |");
        System.out.println("| 12. Mejorar          |");
        System.out.println("| 13. Pasar varios días|");
        System.out.println("| 14. Salir            |");
        System.out.println("________________________");
    }

    /**
     * muestra un pequeño menu que enseña los invernaderos y un resumen de sus atributos
     */
    public void menuInv() {
        System.out.println("Seleccione una opcion");
        System.out.println("--------------------------- Invernaderos ---------------------------");
        System.out.println("[Plantas vivas / Plantas plantadas / Terreno total]");
        int i = 1;
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null){
                System.out.println(i + " -" + invernadero.getName() + " " + invernadero.getAlive() + "/" +
                invernadero.getNum() + "/" + invernadero.getTiles());
                i++;
            }
        }
        System.out.println("0 salir");
    }

    /**
     * muestra un pequeño menu que enseña las arboledas y un resumen de sus atributos
     */
    public void menuArb() {
        System.out.println();
        System.out.println();
        System.out.println("Seleccione una opcion");
        System.out.println("--------------------------- Arboledas ---------------------------");
        System.out.println("[Arboles vivos / Arboles plantados / Terreno total]");
        int i = 1;
        for (Arboleda arboleda : arboledas) {
            if (arboleda != null){
                System.out.println(i + " -" + arboleda.getName() + " " + arboleda.getAlive() + "/" + arboleda.getNum() + "/" +
                arboleda.getTiles());
                i++;
            }
        }
        System.out.println("0 salir");
    }

    /**
     * pide un numero que referencia al invernadero a elegir, si no le das un numero o este no es valido, saldra un mensaje avisando y lo volvera a pedir 
     * @return la posicion del invernadero seleccionado en el array de invernaderos
     */
    public int selectInv() {
        this.menuInv();
        Scanner sc = new Scanner (System.in);
        boolean flag = true;
        while (flag){
            int selec = 0;
            try {
                selec = sc.nextInt();
                
            } catch (Exception e) {
                System.err.println("Pon un numero valido!!!");
            }
            if (selec != 0) {
                 if (selec -1 <= this.invernaderos.length) {
                    return selec - 1;
                }else{
                    System.out.println("Pon un numero valido!!!");
                }
            }
            if (selec == 0){
                flag = false;
            }
           
        }
        return -1;
    }

    /**
     * pide un numero que referencia a la arboleda a elegir, si no le das un numero o este no es valido, saldra un mensaje avisando y lo volvera a pedir 
     * @return la posicion de la arboleda seleccionado en el array de arboledas
     */
    public int selectArb() {
        this.menuArb();
        Scanner sc = new Scanner (System.in);
        boolean flag = true;
        while (flag){
            int selec = 0;
            try {
                selec = sc.nextInt();
                
            } catch (Exception e) {
                System.err.println("Pon un numero valido!!!");
            }
            if (selec != 0) {
                 if (selec -1 <= this.arboledas.length) {
                    return selec - 1;
                }else{
                    System.out.println("Pon un numero valido!!!");
                }
            }else{
                System.out.println();
                flag = false;
            }
           
        }
        return -1;
    }

    /**
     * muestra los atributos de cada invernadero, arboleda, del simulador, el tanque de agua y las monedas
     */
    public void showGeneralStatus() {
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null) {
                invernadero.showStatus();
            }
        }

        for (Arboleda arboleda : arboledas) {
            if (arboleda != null) {
                arboleda.showStatus();
            }
        }
        System.out.println();
        tanqueDeAgua.showStatus();
        System.out.println("Dia:" + numDias);

        if (molino) {
            System.out.println("El molino añade un 20% de ganancia a la hora de recolectar los cultivos triturables!!!");
        }else{
            System.out.println("Aun no tienes molino");
        }
        this.monedas.showMonedas();
    }

    /**
     * muestra informacion sobre el invernadero o arboleda, sus plantas o arboles y mejoras del que se seleccione por teclado
     */
    public void showSpecificStatus() {
        Scanner sc = new Scanner (System.in);
        Boolean flag = true;
        do{
            int indice = -1; 
            indice = -1;
            int cont = 1;
            System.out.println();
            System.out.println();
            System.out.println("De que quieres ver mas informacion???");

            for (Invernadero invernadero : invernaderos) {
                if (invernadero != null) {
                    System.out.println(cont +  " [I] " + invernadero.getName());
                    cont++;
                }
            }
            for (Arboleda arboleda : arboledas) {
                if (arboleda != null) {
                    System.out.println(cont + " [A] " + arboleda.getName());
                    cont++;
                }
            }
            System.out.println(cont + " Salir");
            try {
                indice = sc.nextInt();
            } catch (Exception e) {
            
            }
            if (indice == -1 || indice == cont) {
                flag = false;
            }else{
                if (indice >= 1 && indice < cont) {
                    if (indice > this.invernaderos.length) {
                        indice = indice - this.invernaderos.length;
                        if (arboledas[indice-1] != null) {
                            System.out.println(arboledas[indice-1].getName() + " es nivel " + arboledas[indice-1].getNivel());
                            if (arboledas[indice-1].isGoteo()) {
                                System.out.println(arboledas[indice-1].getName() + " tiene siestema de riego por goteo instalado");
                            }
                            System.out.println();                            
                            arboledas[indice-1].showTileStatus();
                        }
                    }else{
                        if (invernaderos[indice-1] != null) {
                            System.out.println(invernaderos[indice-1].getName() + " es nivel " + invernaderos[indice-1].getNivel());
                            invernaderos[indice-1].showTileStatus();
                        }
                    }
                }else{
                System.out.println("pon un numero valido");
                }                
            }
        }while(flag);
    }

    /**
     * llama al metodo growCrops de cada invernadero y arboleda
     * si tiene aspersores riega
     */
    public void nextDay() {
        System.out.println();
        System.out.println();
        if (aspersores) {
            this.waterCrops();
        }
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null) {

                invernadero.growCrops();
            }
        }
        for (Arboleda arboleda : arboledas) {
            if (arboleda != null) {
                arboleda.growCrops();
            }
        }

        this.numDias++;
    }

    /**
     * muestra un menu y pide por teclado un numero, si el numero es correcto y tiene dinero suficiente añade al agua al tanque
     */
    public void addWater() {
        Scanner sc = new Scanner (System.in);
        boolean flag = true;
        while(flag){
            System.out.println();
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("AÑADIR AGUA");
            System.out.println("elige la cantidad a añadir");
            System.out.println("1 / 5 / 10");
            System.out.println("33 para llenar");
            System.out.println("0 para salir");
            System.out.println("--------------------------------");
            int agua = 0;
            try {
                agua = sc.nextInt();
            } catch (Exception e) {
            }
            if (agua == 0 || agua == 1 || agua == 5 || agua == 10 || agua == 33) {
                if (agua == 33) {
                    if (monedas.gastar((tanqueDeAgua.getMax() - tanqueDeAgua.getWater()) - (tanqueDeAgua.getMax() - tanqueDeAgua.getWater())/10)) {
                        tanqueDeAgua.addWater(tanqueDeAgua.getMax() - tanqueDeAgua.getWater());
                        System.out.println("Tanque de agua lleno");
                    }
                }else{
                    if (agua > tanqueDeAgua.getMax() - tanqueDeAgua.getWater()) {
                        agua = tanqueDeAgua.getMax() - tanqueDeAgua.getWater();
                    }
                    if (monedas.gastar(agua)) {
                        tanqueDeAgua.addWater(agua);
                        System.out.println("añadidos " + agua + " de agua");
                        tanqueDeAgua.showStatus();
                        monedas.showMonedas();
                    }
                }
                flag = false;
            }else{
                System.out.println("introduce un numero valido!!!");
            }
        }
    }

    /**
     *  riega todas las plantas y arboles. Al final muestra un recuento de cuantas se pudieron regar
     */
    public void waterCrops() {
        int contRegadas = 0;
        int contVivas = 0;
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null) {
                int[] cont = invernadero.waterCrops(tanqueDeAgua.getWater());
                contRegadas = contRegadas + cont[0];
                contVivas = contVivas + invernadero.getAlive();
                this.tanqueDeAgua.usar(cont[1]);
            }
        }
        for (Arboleda arboleda : arboledas) {
            if (arboleda != null) {
                Random ran = new Random();
                int[] cont = arboleda.waterCrops(tanqueDeAgua.getWater());
                contRegadas = contRegadas + cont[0];
                contVivas = contVivas + arboleda.getAlive();
                if (arboleda.isGoteo()) {
                    if (ran.nextBoolean()) {
                        this.tanqueDeAgua.usar(cont[1]);
                    }
                }else{
                    this.tanqueDeAgua.usar(cont[1]);
                }
            }
        }
        System.out.println("se han regado " + contRegadas + "/" + contVivas + " plantas y arboles de los invernaderos y arboledas");
    }

    /**
     *  muestra el menu de seleccion de invernadero o arboleda y si el invernadero o la arboleda que le pasas no esta lleno crea
     *  una nueva planta o arbol seleccionada por teclado, muestra su capacidad ocupada y registra la compra
     */
    public void plant() {
        int prec = 0;
        Scanner sc = new Scanner (System.in);
        System.out.println();
        System.out.println();
        int selec = invOrArb(this.arboledas.length > 0);
        if (selec == 1) {
            boolean flag = true;
            while (flag) {
                Trigo tri = new Trigo();
                Arroz arr = new Arroz();
                Garbanzos gar = new Garbanzos();
                Lechuga lec = new Lechuga();
                Patata pat = new Patata();
                Pimientos pim = new Pimientos();
                System.out.println();
                System.out.println();
                System.out.println("1. Trigo       " + tri.getPrice() + " monedas");
                System.out.println("2. Arroz       " + arr.getPrice() + " monedas");
                System.out.println("3. Garbanzos   " + gar.getPrice() + " monedas");
                System.out.println("4. Lechuga     " + lec.getPrice() + " monedas");
                System.out.println("5. Patata      " + pat.getPrice() + " monedas");
                System.out.println("6. Pimientos   " + pim.getPrice() + " monedas" );
                System.out.println("7. Salir");
                int plt = 7;
                try {
                    plt = sc.nextInt();
                } catch (Exception e) {
                    System.err.println("Selecciona un numero valido!!!");
                }
                if (plt == 7) {
                    return;
                }
                switch (plt) {
                    case 1:
                        prec = tri.getPrice();
                        break;
                    case 2:
                        prec = arr.getPrice();
                        break;
                    case 3:
                        prec = gar.getPrice();
                        break;
                    case 4:
                        prec = lec.getPrice();
                        break;
                    case 5:
                        prec = pat.getPrice();
                        break;
                    case 6:
                        prec = pim.getPrice();
                        break;
                }
                if (plt >= 1 && plt <= 6) {
                    if (monedas.gastable(prec)) {
                    int indice = selectInv();
                        if (indice == -1) {
                            return;
                        }
                        if (indice >= 0 && indice < invernaderos.length) {
                            if (invernaderos[indice] != null) {
                                if (!invernaderos[indice].isLleno()) {
                                    invernaderos[indice].plants(plt);
                                    invernaderos[indice].showCapacity();
                                    monedas.gastar(prec);
                                    flag = false;
                                }else{
                                    System.out.println("Este invernadero esta lleno");
                                }
                            }        
                        }      
                    }
                }else{
                    System.out.println("Selecciona un numero valido!!!");
                }
                
            }            
        }

        if (selec == 2 && this.arboledas.length > 0) {
            boolean flag = true;
            while (flag) {
                Kiwi ki = new Kiwi();
                Madronho mad = new Madronho();
                Manzano man = new Manzano();
                Melocotonero mel = new Melocotonero();
                Naranjo nar = new Naranjo();
                System.out.println();
                System.out.println();
                System.out.println("1. Kiwi          " + ki.getPrice() + " monedas");
                System.out.println("2. Madroño       " + mad.getPrice() + " monedas");
                System.out.println("3. Manzano       " + man.getPrice() + " monedas");
                System.out.println("4. Melocotonero  " + mel.getPrice() + " monedas");
                System.out.println("5. Naranjo       " + nar.getPrice() + " monedas");
                System.out.println("6. Salir");
                int arb = 6;
                try {
                    arb = sc.nextInt();
                } catch (Exception e) {
                    System.err.println("Selecciona un numero valido!!!");
                }
                if (arb == 6) {
                    return;
                }
                switch (arb) {
                    case 1:
                        prec = ki.getPrice();
                        break;
                    case 2:
                        prec = mad.getPrice();
                        break;
                    case 3:
                        prec = man.getPrice();
                        break;
                    case 4:
                        prec = mel.getPrice();
                        break;
                    case 5:
                        prec = nar.getPrice();
                        break;
                }
                if (arb >= 1 && arb <= 5) {
                    if (monedas.gastable(prec)){
                        int indice = selectArb();
                        if (indice == -1) {
                            return;
                        }
                        if (indice >= 0 && indice < arboledas.length) {
                            if (arboledas[indice] != null) {
                                if (!arboledas[indice].isLleno()) {
                                    arboledas[indice].plants(arb);
                                    arboledas[indice].showCapacity();
                                    monedas.gastar(prec);
                                    flag = false;
                                }else{
                                    System.out.println("Esta arboleda esta llena");
                                }
                            }        
                        }
                    }

                }else{
                    System.out.println("Selecciona un numero valido!!!");
                }     
            }     
        }
        
    }

    /**
     *  llama al metodo harvest de cada estructura y muestra los contadores de cosechadas y recolectadas en total
     */
    public void harvest() {
        int ganadoTrit = 0;
        int recolectado = 0;
        int cont = 0;
        int ganado = 0;
        int muertas = 0;
        int opcion = invOrArb(this.arboledas.length > 0);
        if (opcion == 1) {
            for (Invernadero invernadero : invernaderos) {
                if (invernadero != null) {
                    int[] harvs = invernadero.harvest(stats);
                    recolectado = recolectado + harvs[0];
                    muertas = muertas + harvs[1];
                    cont = cont + harvs[2];
                    ganado = ganado + harvs[3];
                    ganadoTrit = ganadoTrit + harvs[4];
                    if (this.molino) {
                        ganadoTrit = (ganadoTrit * 120)/100;
                        stats.registrarHarina(ganadoTrit);
                    }
                }
            }  
        }
        if (opcion == 2 && this.arboledas.length > 0) {
            for (Arboleda arboleda : arboledas) {
                if (arboleda != null) {
                    int[] harvs = arboleda.harvest(stats);
                    recolectado = recolectado + harvs[0];
                    cont = cont + harvs[2];
                    ganado = ganado + harvs[3];
                }
            }
        }
        if (opcion == 3) {
            return;
        }
        monedas.addMonedas(ganado + ganadoTrit);
        System.out.println(cont + " plantas recolectadas han producido " + recolectado + " productos");
        System.out.println("Han muerto " + muertas + " plantas en el proceso");
        System.out.println("Has ganado " + (ganado + ganadoTrit) + " monedas");
    }

    /**
     *  llama al metodo plow de cada estructura
     */
    public void plow() {
        int opcion = invOrArb(this.arboledas.length > 0);
        if (opcion == 1) {
            for (Invernadero invernadero : invernaderos) {
                if (invernadero != null) {
                    invernadero.plow();
                }
            }            
        }
        if (opcion == 2 && this.arboledas.length > 0) {
            int ganado = 0;
            for (Arboleda arboleda : arboledas) {
                if (arboleda != null) {
                    ganado = ganado + arboleda.plow();
                }
            }
            monedas.addMonedas(ganado);
        }

    }

    /**
     *  muestra un menu para seleccionar invernadero o arboleda y usa el metodo unroot del elegido
     */
    public void unroot() {
        int opcion = invOrArb(this.invernaderos.length > 0);
        if (opcion == 1) {
            boolean flag = true;
            while (flag){
                int indice = selectInv();
                if (indice >= 0 && indice < invernaderos.length) {
                    invernaderos[indice].unroot();
                    flag = false;
                }
                if (opcion == 0) {
                    return;
                }
            }          
        }
        if (opcion == 2 && this.arboledas.length > 0) {
            boolean flag = true;
            while (flag) {
                int indice= selectArb();
                int coste = arboledas[indice].getPrice()/2;
                
                if (opcion == 0 || !monedas.gastable(coste)) {
                    return;
                }
                if (indice >= 0 && indice < this.arboledas.length) {
                    arboledas[indice].unroot();
                    monedas.gastar(coste);
                    flag = false;
                }

            }
        }

    }

    /**
     * muestra un menu para elegir si aumentar el tamaña del array de invernaderos o arboledas, mejorar la capacidad de cada estructura,
     * añadir sistema de riego por goteo a una arboleda, aumentar la capacidad del tanque o instalar aspersores
     */
    public void upgrade() {
        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println();
            System.out.println(" Elige que mejorar");
            System.out.println();
            System.out.println("1 Comprar edificio");
            System.out.println("2 Mejorar edificio");
            System.out.println("3 Mejorar tanque de agua");
            System.out.println("4 cancelar");
            Scanner sc = new Scanner (System.in);
            int opcion = 0;
            int precio;
            try {
                opcion = sc.nextInt(); 
            } catch (Exception e) {
            }
            switch (opcion) {
                case 1:
                    System.out.println();
                    System.out.println();
                    System.out.println("1. Invernadero");
                    System.out.println("2. Arboleda");
                    if (!this.molino) {
                        System.out.println("3. Molino");
                    }
                    try {
                        opcion = sc.nextInt(); 
                    } catch (Exception e) {
                    }
                    sc.nextLine();
                    int indice;
                    Boolean x;
                    String nombre = "";
                    switch (opcion) {
                        case 1:
                            x = monedas.gastar(this.invernaderos.length*500);
                            if (!x) {
                                break;
                            }
                            System.out.println("Nombre del invernadero nuevo: ");
                            try {
                                nombre = sc.nextLine();
                            } catch (Exception e) {
                            }
                            Invernadero[] inv = new Invernadero[this.invernaderos.length + 1];
                            System.arraycopy(this.invernaderos, 0, inv, 0, this.invernaderos.length);
                            inv[inv.length - 1] = new Invernadero(nombre);
                            this.invernaderos = inv;
                            break;
                        case 2:
                            x = monedas.gastar(this.arboledas.length*500);
                            if (!x) {
                                break;
                            }
                            System.out.println("Nombre de la arboleda nueva: ");
                            try {
                                nombre = sc.nextLine();
                            } catch (Exception e) {
                            }
                            Arboleda[] arb = new Arboleda[this.arboledas.length + 1];
                            System.arraycopy(this.arboledas, 0, arb, 0, this.arboledas.length);
                            arb[arb.length - 1] = new Arboleda(nombre);
                            this.arboledas = arb;
                            break;
                        case 3:
                            x = monedas.gastar(3000);
                            if (!this.molino && x) {
                                this.molino = true;
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println();
                    System.out.println("1. Invernadero");
                    if (this.arboledas.length > 0) {
                        System.out.println("2. Arboleda");
                    }
                    try {
                        opcion = sc.nextInt(); 
                    } catch (Exception e) {
                    }
                    switch (opcion) {
                        case 1:
                            System.out.println();
                            System.out.println();
                            indice = selectInv();
                                if (indice >= 0 && indice < invernaderos.length) {
                                    if (invernaderos[indice].getNivel() == 10) {
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("No hay mejoras disponibles");
                                    }else{
                                        System.out.println();
                                        System.out.println();
                                        System.out.println(invernaderos[indice].getName() + " es nivel " + invernaderos[indice].getNivel());
                                        precio = 150*invernaderos[indice].getNivel();
                                        System.out.println("La siguiente mejora cuesta " + precio + " monedas");
                                        System.out.println("1. Aumentar tamaño");
                                        try {
                                            opcion = sc.nextInt(); 
                                        } catch (Exception e) {
                                        }
                                        if (opcion == 1) {
                                            x = monedas.gastar(precio);
                                            if(x){
                                                System.out.println();
                                                System.out.println();
                                                invernaderos[indice].upgrade();
                                            }
                                        }
                                    }
                                }
                                break; 
                    case 2:
                        System.out.println();
                        System.out.println();
                        indice = selectArb();
                            if (indice >= 0 && indice < arboledas.length) {
                                if (arboledas[indice].getNivel() == 10 && !arboledas[indice].isGoteo()) {
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("No hay mejoras disponibles");
                                }else{
                                    System.out.println();
                                    System.out.println();
                                    System.out.println(arboledas[indice].getName() + " es nivel " + arboledas[indice].getNivel());
                                    precio = 150*arboledas[indice].getNivel();
                                    System.out.println("1. Aumentar tamaño                     "+ precio + " monedas");
                                    if (!arboledas[indice].isGoteo()) {
                                        System.out.println("2. Añadir sistema de riego por goteo   500 monedas");
                                    }
                                    try {
                                        opcion = sc.nextInt(); 
                                    } catch (Exception e) {
                                    }
                                    if (opcion == 1) {
                                        x = monedas.gastar(precio);
                                        if(x){
                                            System.out.println();
                                            System.out.println();
                                            arboledas[indice].upgrade();
                                        }
                                    }
                                    if (opcion == 2 && !arboledas[indice].isGoteo()) {
                                        if (indice >= 0 && indice < arboledas.length) {
                                            if (!this.arboledas[indice].isGoteo()) {
                                                x = monedas.gastar(500);
                                                if (x) {
                                                    System.out.println();
                                                    System.out.println();
                                                    this.arboledas[indice].gotear();    
                                                }
    
                                            }
                                        }
                                    }
                                }
                            }
                            break; 
                    }
                    break;
                case 3:
                    System.out.println();
                    System.out.println();
                    System.out.println("1. Aumentar tamaño     500 monedas");
                    if (!this.aspersores) {
                        System.out.println("2. Añadir aspersores  1000 monedas");
                    }
                    try {
                        opcion = sc.nextInt();
                    } catch (Exception e) {
                    }
                    if (opcion == 1) {
                        x = monedas.gastar(500);
                        if (x) {
                            tanqueDeAgua.upgrade();
                        }
                    }
                    if (opcion == 2 && !aspersores) {
                        x = monedas.gastar(1000);
                        if (x) {
                            this.aspersores = true;
                        }
                    }
                    break;
                case 4:
                    System.out.println("saliendo...");
                    flag = false;
                    break;
                default:
                    System.out.println("Pon un numero valido");
                    break;
            }
            opcion = 0;
        }
    }
    
    /**
     * llama a nextDay el numero de veces que le pases por teclado
     */
    public void forwardDays() {
        Scanner sc = new Scanner (System.in);
        System.out.println();
        System.out.println();
        System.out.println("Cuantos dias quieres pasar?");
        int x = 0;
        try {
            x = sc.nextInt();
        } catch (Exception e) {
        }
        for (int i = 0; i < x; i++) {
            this.nextDay();
        }
    }

    /**
     * @param arbo
     * @return 1 si elige invernadero, 2 arboleda o 3 para cancelar
     */
    private int invOrArb(boolean arbo){
        int elec = 1;
        boolean flag = true;
        while (flag){
            System.out.println("1. Invernadero");
            if (arbo) {
                System.out.println("2. Arboleda");
            }
            System.out.println("3. Salir");
            Scanner sc = new Scanner (System.in);
            try {
                elec = sc.nextInt();
            } catch (Exception e) {
            }
            if (elec != 1 && elec != 2 && elec != 3) {
                System.out.println("Elige un numero valido");
            }else{
                flag = false;
            }
        }
        return elec;
    }

    /**
     * muestra las estadisticas de la partida
     */
    public void showStats() {
        this.stats.mostrar();
    }

    /**
     * muestra la informacion de las plantas o arboles disponibles
     */
    public void ShowNatura() {
        Scanner sc = new Scanner (System.in);
        int selec = 0;
        int opcion = 0;
        while(selec != 3){
            System.out.println();
            System.out.println();
            selec = invOrArb(true);
            if (selec == 1) {
                System.out.println();
                System.out.println();
                System.out.println("1. Trigo");
                System.out.println("2. Arroz");
                System.out.println("3. Garbanzos");
                System.out.println("4. Lechuga");
                System.out.println("5. Patata");
                System.out.println("6. Pimientos");
                try {
                    opcion = sc.nextInt();
                } catch (Exception e) {}
                switch (opcion) {
                    case 1:
                        Trigo trig = new Trigo();
                        trig.showInfoNatura();
                        break;
                    case 2:
                        Arroz arr = new Arroz();
                        arr.showInfoNatura();
                        break;
                    case 3:
                        Garbanzos gar = new Garbanzos();
                        gar.showInfoNatura();
                        break;
                    case 4:
                        Lechuga lec = new Lechuga();
                        lec.showInfoNatura();
                        break;
                    case 5:
                        Patata pat = new Patata();
                        pat.showInfoNatura();
                        break;
                    case 6:
                        Pimientos pim = new Pimientos();
                        pim.showInfoNatura();
                        break;
                    default:
                        System.out.println("Escribe un numero valido!!!");
                        break;
                }
            }
            if(selec == 2){
                System.out.println();
                System.out.println();
                System.out.println("1. Kiwi");
                System.out.println("2. Madroño");
                System.out.println("3. Manzano");
                System.out.println("4. Melocotonero");
                System.out.println("5. Naranjo");
                try {
                    opcion = sc.nextInt();
                } catch (Exception e) {}
                switch (opcion) {
                    case 1:
                        Kiwi ki = new Kiwi();
                        ki.showInfoNatura();
                        break;
                    case 2:
                        Madronho mad = new Madronho();
                        mad.showInfoNatura();
                        break;
                    case 3:
                        Manzano man = new Manzano();
                        man.showInfoNatura();
                        break;
                    case 4:
                        Melocotonero mel = new Melocotonero();
                        mel.showInfoNatura();
                        break;
                    case 5:
                        Naranjo nar = new Naranjo();
                        nar.showInfoNatura();
                        break;
                    default:
                        System.out.println("Escribe un numero valido!!!");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Simulador sim = new Simulador();
        sim.init();
        boolean flag = true;
        while (flag) {
            Random ran = new Random();
            sim.menu();
            Scanner sc = new Scanner (System.in);
            int opcion = 0;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
            System.err.println("Escribe un numero valido!!!");
            }
            switch(opcion){
                case 1:
                    sim.showGeneralStatus();
                    break;
                case 2:
                    sim.showSpecificStatus();
                    break;
                case 3:
                    sim.showStats();
                    break;
                case 4:
                    sim.ShowNatura();
                    break;
                case 5:
                    sim.nextDay();
                    sim.showGeneralStatus();
                    break;
                case 6:
                    sim.addWater();
                    break;
                case 7:
                    sim.waterCrops();
                    break;
                case 8:
                    sim.plant();
                    break;
                case 9:
                    sim.harvest();
                    break;
                case 10:
                    sim.plow();
                    break;
                case 11:
                    sim.unroot();
                    break;
                case 12:
                    sim.upgrade();
                    break;
                case 13:
                    sim.forwardDays();
                    sim.showGeneralStatus();
                    break;
                case 14:
                    System.out.println("Saliendo...");
                    flag = false;
                    break;
                case 98:
                    int selec = sim.invOrArb(sim.arboledas.length > 0);
                    if (selec == 1) {
                        selec = sim.selectInv();
                        if (selec == -1) {
                            return;
                        }
                        for (int i = 0; i < 4; i++) {
                            if (sim.invernaderos[selec]!=null) {
                                if (!sim.invernaderos[selec].isLleno()) {
                                    int ind = ran.nextInt(5)+1;
                                    sim.invernaderos[selec].plants(ind);                                
                                }
                            }
                        }
                    }
                    if (selec == 2 && sim.arboledas.length > 0) {
                        selec = sim.selectArb();
                        if (selec == -1) {
                            return;
                        }
                        for (int i = 0; i < 5; i++) {
                            if (sim.arboledas[selec]!=null) {
                                if (!sim.arboledas[selec].isLleno()) {
                                    int ind = ran.nextInt(4)+1;
                                    sim.arboledas[selec].plants(ind);                                
                                }
                            }
                        }
                    }
                    break;
                case 99:
                    sim.monedas.addMonedas(1000);
                    break;
                default:
                    System.out.println("Escribe un numero valido!!!");
                    break;
            }
        }
    }
}
