/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

/**
 * * This java class contains the methods used within the back-end of the application.
 */
public final class Methods {

    public static String getDataToJwt(String jwt) {
        String response;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("digiclave")
                    .parseClaimsJws(jwt).getBody();
            response = claims.getSubject();
        } catch (Exception e) {
            System.out.println("error JWT: " + e.getMessage());
            response = "";
        }
        return response;
    }
    /** This method is for the security application.
     * @param request Processes HTTP type requests
     * @param param String type variable, contains the information obtained to the method.
     * @param defaulx String type variable, return variable
     * @return a String, for the security request. 
     */
    public static String securRequest(HttpServletRequest request, String param, String defaulx) {
        try {
            String res = request.getParameter(param);
            return res != null ? res : defaulx;
        } catch (Exception e) {
            return defaulx;
        }
    }
    /** This method is for the security application.
     * @param email String type variable, contains the email.
     * @param params RegEt Email validator.
     * @return a String, for the security request.
     */
    public static Boolean comprobeEmail(String email, String params) {
        Pattern pat = Pattern.compile(params);//".*@uteq.edu.ec"
        Matcher mat = pat.matcher(email);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }
    /** Convert from string to json.
     * @param json String type variable, contains the json to be converted.
     * @return a json.
     */
    public static JsonObject stringToJSON(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject Jso = parser.parse(json).getAsJsonObject();
            return Jso;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new JsonObject();
        }
    }
    /** Convert from string to json.
     * @param json String type variable, contains the json to be converted.
     * @return a json.
     */
    public static JsonElement stringToJSON2(String json) {
        try {
            JsonElement parser = new JsonPrimitive(json);
            System.out.println(parser.getAsString());
            //JsonObject Jso = new JsonObject();
            //Jso =  (JsonObject) parser.p(json);
            return parser;
        } catch (Exception e) {
            return new JsonObject();
        }
    }
    /** Get a part of the json.
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json parameter to be divided.
     * @return a json, divided.
     */
    public static JsonElement securGetJSON(JsonObject jso, String param) {
        try {
            JsonElement res = jso.get(param);//request.getParameter(param);
            return res;
        } catch (Exception e) {
            return null;
        }
    }
    /** A sub json of a json.
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json parameter to be divided.
     * @param defaulx String type variable, return variable
     * @return a json.
     */
    public static String JsonToSub(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.toString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
//            System.out.println("erro json a string");
            return defaulx;
        }
    }
    /** Method to divide a json.
     * @param jso Variable type json, contains the information.
     * @param param String type variable, contains the name of the json parameter to be divided.
     * @param defaulx String type variable, return variable
     * @return Return a String, with the json divided.
     */
    public static String JsonToString(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
//            System.out.println("erro json a string");
            return defaulx;
        }
    }
   /** From a json element to String.
    * @param jse contains the element json.
    * @param defaulx String type variable, return variable
     * @return Return a String, with the json divided.
    */
    public static String JsonElementToString(JsonElement jse, String defaulx) {
        try {
            if (jse != null) {
                return jse.getAsString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }
    /** from json to Integer.
     * @param jso Variable type json, contains the information
     * @param param String type variable, contains the name of the json parameter to be divided.
     * @param defaulx String type Integer, return variable
     * @return an integer, the variable is defaulx.
     */
    public static int JsonToInteger(JsonObject jso, String param, int defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsInt();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }
    /** from json to boolean
     * @param jso Variable type json, contains the information
     * @param param String type variable, contains the name of the json parameter to be divided.
     * @param defaulx String type Boolean, return variable
     * @return an Boolean, the variable is defaulx.
     */
    public static Boolean JsonToBoolean(JsonObject jso, String param, boolean defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsBoolean();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }
    /**  From table to json.
     * @param table Variable of type DefaultTableModel, table with loaded data
     * @return a String, contains a json with data.
     */
    public String tableToJson(DefaultTableModel table) {
        String resul = "[";
        if (table.getRowCount() > 0) {
            int columCount = table.getColumnCount();
            for (int row = 0; row < table.getRowCount(); row++) {
                String line = "";
                for (int colum = 0; colum < columCount; colum++) {
                    line += "\"" + table.getColumnName(colum) + "\":\"" + table.getValueAt(row, colum).toString() + "\"";
                    if (colum < columCount - 1) {
                        line += ",";
                    }
                }
                if (line.length() > 0) {
                    resul += "{" + line + "}";
                    if (row < table.getRowCount() - 1) {
                        resul += ",";
                    }
                }
            }
            resul += "]";
        } else {
            resul = "[]";
        }
        return resul;
    }
   /** Convert from a table to an html5 table
     * @param table Variable of type DefaultTableModel, table with loaded data
     * @return a String, with an html5 table with data.
     */
    public String tableToHtmlTable(DefaultTableModel table) {
        String resul = "<table>";
        if (table != null) {
            int columCount = table.getColumnCount();
            resul += "<thead><tr>";
            for (int fol = 0; fol < table.getColumnCount(); fol++) {
                resul += String.format("<th>%s</th>", table.getColumnName(fol));
            }
            resul += "</tr></thead>";
            resul += "<tbody>";
            for (int row = 0; row < table.getRowCount(); row++) {
                resul += "<tr>";
                for (int colum = 0; colum < columCount; colum++) {
                    resul += String.format("<td>%s</td>", table.getValueAt(row, colum));
                }
                resul += "</tr>";
            }
            resul += "</tbody>";
        }
        resul += "</table>";
        return resul;
    }
}
