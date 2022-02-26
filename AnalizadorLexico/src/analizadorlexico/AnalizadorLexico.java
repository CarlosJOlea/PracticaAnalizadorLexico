/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analizadorlexico;

import java.util.Scanner;

/**
 *
 * @author carlos
 */
public class AnalizadorLexico {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);

        String texto;

      
        System.out.println("Ingrese la operacion a realizar");
        texto = sc.nextLine();
        Estados estado = new Estados(texto);

        //   texto = texto.replace(" ", "");
        int stringLength = texto.length();
        System.out.println("el texto es " + texto + " son " + stringLength);
        Token t;
        do {
            t = estado.next();
            switch (t) {

                case entero:
                    System.out.println("<entero," + estado.getLexema() + ">");
                    break;
                case flotante:
                    System.out.println("<flotante," + estado.getLexema() + ">");
                    break;
                case suma:
                    System.out.println("<suma," + estado.getLexema() + ">");
                    break;
                case exponente:
                    System.out.println("<exponente," + estado.getLexema() + ">");
                    break;
                case incremento:
                    System.out.println("<incremento," + estado.getLexema() + ">");
                    break;
                case error:
                    System.out.println("<error," + estado.getLexema() + ">");
                    break;

            }
     
        } while ( t != Token.finallinea);

        return ;
    }

}

