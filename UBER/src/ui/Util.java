package ui;

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

    public static void  esperar1800(){
        try {
            Thread.sleep(2050);
        } catch (InterruptedException e) {
        }
    }
    public static void esperar1200(){
        try {
            Thread.sleep(1250);
        } catch (InterruptedException e) {
        }
    }

    public static void transicaoCurta(){
        esperar1200();
        limparTela();
    } 
    
    public static void transicaoLonga(){
        esperar1800();
        limparTela();
    } 
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
