
package Controller;

import DataStaticBD.Conection;
import DataStaticBD.Methods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.lang.reflect.Method;
import javax.swing.table.DefaultTableModel;
import DAO.UserDAO;
import Models.Usermodel;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Faroukmn_97
 * In this class, you validate the login and the creation of a new user.
 */
public class UserController {

    /**
     *  
     */
    private Conection conex;
    UserDAO udao;

    public UserController() {
        conex = new Conection();
        udao = new UserDAO();
    }
 /** this function validates the entry and receives two variables for user access to the application
  * @return this function returns an object type vector, and receives two variables for user access
  * @param email It is a String variable, this variable will contain the user's mail
  * @param pwd It is a String variable, this variable will contain the encrypted password of the user
  */
    public Object[] LogIn(String email, String pwd) {
        DefaultTableModel table = conex.returnRecord("select tbluser.user_id, tbluser.name,tbluser.last_name, tbluser.email, tbluser.password, tbluser.address,\n" +
"tbltypeuser.role, tbluser.imguser, tbluser.registrationdate, tbluser.dateupdate, tbluser.birthdaydate\n" +
"from tbluser inner join tbltypeuser on tbltypeuser. typeuser_id = tbluser.typeuser_id where tbluser.email='" + email + "'");
        String message = "Usuario no encontrado";
        boolean status = false;
        Usermodel usr = new Usermodel();
        if (table.getRowCount() > 0) {
            message = "Contraseña incorrecta";
            for (int index = 0; index < table.getRowCount(); index++) {
                 System.out.println("-"+encriptPassword(pwd)+"-");
                 System.out.println("-"+table.getValueAt(index, 4).toString()+"-");
                 System.out.println(encriptPassword(pwd).equals(table.getValueAt(index, 4).toString()));
                if (encriptPassword(pwd).equals(table.getValueAt(index, 4).toString())) {
                    usr = udao.setUser(table, index);
                    message = "Acceso consedido.";
                    status = true;
                }
            }
        }
        return new Object[]{status, message, usr};
    }
     public String encriptPassword(String pwd) {
        return DigestUtils.sha256Hex(pwd);
    }
}
