/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import org.apache.commons.codec.binary.Base64;

import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

/**
 * In this java class, there are the text and number validation methods, also there are the encryption methods for passwords
 */
public class CodeDJA {

    private static final String staticKey = "HHolis";
    //1-->00000000001
    private static final String[][] bufferLetters = new String[][]{
        {"zyC", "pLd", "aFo", "Jre", "Klc", "oMy", "qnP", "kfI", "iSz", "Gcu"},
        {"ZLo", "VmQ", "oLP", "RDg", "ZdP", "DcV", "oEG", "IuK", "AsM", "LOw"},
        {"AZM", "SFR", "OGH", "MVX", "YFD", "KTC", "DSK", "URF", "ACD", "HWX"},
        {"fme", "xia", "rtp", "acf", "cid", "oeb", "pwe", "zuw", "nvs", "amb"}
    };

   /**  encryption method for passwords
    * @param texto This variable of type String, contains the text to encrypt
    * @param codigo This variable of type Int, contains the encryption code
    * @return The function returns a String, containing the encrypted text.
    */
    public static String cifradoCesar(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + codigo) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + codigo) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            }
        }
        return cifrado.toString();
    }

    /**  decryption method for passwords
    * @param texto This variable of type String, contains the encrypted text
    * @param codigo This variable of type Int, contains the encryption code
    * @return The function returns a String, containing the decrypted text.
    */
    public static String descifradoCesar(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            }
        }
        return cifrado.toString();
    }

    private static int letterToInteger(String information) {
        int num = 0;
        for (int i = 0; i < information.length(); i++) {
            num += (int) information.charAt(i);
        }
        return num + 23;
    }

    public static String encodeDJA(String texto) {
        if (isNumeric(texto)) {
            texto = transform(texto);
            texto = cifradoCesar(texto, letterToInteger(staticKey));
            texto = cifradoCesar(texto, 1204);
            byte[] bytesEncoded = Base64.encodeBase64(texto.getBytes());
            return new String(bytesEncoded);
        } else {
            return "";
        }
    }

    public static String decodeDJA(String texto) {
        byte[] valueDecoded = Base64.decodeBase64(texto);
        texto = descifradoCesar(new String(valueDecoded), 1204);
        texto = descifradoCesar(texto, letterToInteger(staticKey));
        texto = untransform(texto);
        return texto;
    }

    public static String untransform(String formato) {
        String texto = "";
        for (int i = 0; i < formato.length() / 3; i++) {
            Boolean Rowflag = false;
            String part = formato.substring((i * 3), (i * 3) + 3);
            for (int bffRow = 0; bffRow < bufferLetters.length; bffRow++) {
                for (int bffCol = 0; bffCol < bufferLetters[0].length; bffCol++) {
                    if (part.equals(bufferLetters[bffRow][bffCol])) {
                        texto += bffCol;
                        Rowflag = true;
                    }
                }

            }
            if (Rowflag == false) {
                texto += "ñ";
            }
        }
        return texto;
    }

    public static String transform(String formato) {
        String texto = "";
        for (int i = 0; i < formato.length(); i++) {
            // y = i%%4;
            int y = i % 4;
            String caracter = String.valueOf(formato.charAt(i));
            int num = Integer.parseInt(caracter);
            texto += bufferLetters[y][num];
        }
        return texto;
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
//    public static String getSuperID(String num) {
//        String zeros = repeat("0",10 - num.length());
//        return zeros+num;
//    }
//
//    private static String repeat(String str, int times) {
//        return Stream.generate(() -> str).limit(times).collect(joining());
//    }
}
