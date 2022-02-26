/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico;

/**
 *
 * @author carlos
 */
enum Token {
    entero, flotante, suma, exponente, error, finallinea, incremento
}

public class Estados {
     static char[] aCaracteres;
    static int posicion;
    static int posicion2;
    static String lexema;

    public static String getLexema() {
        return lexema.substring(0, lexema.length() - 1);
    }

    public Estados(String texto) {
        posicion2=0;
        posicion = 0;
        aCaracteres = texto.toCharArray();
    }

    private static char leer() {
        if (finalL()) {
            return 32;
        }
        return aCaracteres[posicion++];
    }

    private static boolean finalL() {
        return posicion == aCaracteres.length;
    }

    public static Token next() {
        Token t;
        while (leer() == ' ' ){
            if(finalL()){
                return Token.finallinea;
            }
        }
        /*if (!finalL()) {
            posicion--;
        }*/
        posicion--;
        //posicion2=posicion;
        /*if (finalL()) {
            return Token.finallinea;
        }*/

        if ((t = numeros()) != Token.error) {
            return t;
        }
        return Token.error;
    }
//   4 3. 98 E5
    private static Token numeros() {
       
        int estadoActual = 0;
        int estadoAnterior = 0;
        String buffer = "";
        char ascii = '0';
        while (estadoActual != -1) {

            ascii = leer();

            estadoAnterior = estadoActual;
            buffer += (char) ascii;

            switch (estadoActual) {
                case 0:
                    if (ascii >= '1' && ascii <= '9') {
                        estadoActual = 1;
                    } else if (43 == ascii) {
                        estadoActual = 11;
                    } else {
                        estadoActual = -1;
                    }
                    break;
                case 1:
                    if (48 <= ascii && ascii <= 57) ; else if (46 == ascii) {
                        estadoActual = 2;
                    } else {
                        estadoActual = -1;
                    }
                    break;
                case 2:
                    if (48 <= ascii && ascii <= 57) {
                        estadoActual = 3;
                    } else {
                        estadoActual = -1;
                    }
                    break;
                case 3:
                    if (48 <= ascii && ascii <= 57) ; else if (69 == ascii || ascii == 101) {
                        estadoActual = 4;
                    } else {
                        estadoActual = -1;
                    }
                    break;
                case 4:
                    if (43 == ascii || ascii == 45) {
                        estadoActual = 7;
                    } else {
                        estadoActual = -1;
                    }
                    break;
                case 7:
                    if (48 <= ascii && ascii <= 57) {
                        estadoActual = 8;
                    } else {
                        estadoActual = -1;
                    }
                    break;
                case 8:
                    if (48 <= ascii && ascii <= 57) ; else {
                        estadoActual = -1;
                    }
                    break;
                case 10:
                    estadoActual = -1;
                    break;
                case 11:
                    if (43 == ascii){
                        estadoActual = 10;
                    }else {
                        estadoActual = -1;
                    }
                    break;
            }

        }

        if (estadoAnterior == 1 || estadoAnterior == 3 || estadoAnterior == 8 || estadoAnterior == 11 || estadoAnterior == 10) {
            if (!finalL()) {
                posicion--;
            }
            posicion2=posicion;
            lexema = buffer;
            switch (estadoAnterior) {

                case 1:
                    if (ascii == ' ' || (ascii >= '1' && ascii <= '9') || finalL()) {
                        return Token.entero;
                    }
                    break;
                case 3:
                    if (ascii == ' ' || (ascii >= '1' && ascii <= '9') || ascii == '.' || ascii == '+') {
                        return Token.flotante;
                    }
                case 8:
                    if (ascii == ' ' || (ascii >= '1' && ascii <= '9') || ascii == '.' || ascii == '+' || ascii == '-' || ascii == 'e' || ascii == 'E') {
                        return Token.exponente;
                    }
                    break;
                case 10:
                    //lexema = lexema.substring(0, lexema.length() - 1);
                    return Token.incremento;
                case 11:
                    //lexema = lexema.substring(0, lexema.length() - 1);
                    return Token.suma;

            }
        } else {
            lexema = buffer + " ";
        }


        return Token.error;
    }

}