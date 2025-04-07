package negocio.servicos;

import java.util.Random;
import java.util.Scanner;

public class Util {
    public final static Random r = new Random();
    public final static  Scanner entrada = new Scanner(System.in);
    
    public Scanner getEntrada() {
        return entrada;
    }

    public Random getR() {
        return r;
    }

}
