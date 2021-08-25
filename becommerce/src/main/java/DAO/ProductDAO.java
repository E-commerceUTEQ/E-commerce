
package DAO;

import DataStaticBD.Conection;
import Models.Productmodel;

/**
 *
 * @author Faroukmn_97
 */
public class ProductDAO {
    
  Conection con;
    String sentence;  
    
     public ProductDAO(){
       con = new Conection();
    }
     
   public String selectProduct() {
        sentence = "select * from tblproduct";
        String json = con.getRecordsInJson(sentence);
        return json;
    }
   
       public boolean insertProduct(Productmodel productmodel) {
        String structure = String.format(
                "<product>"
                + "<name>" + productmodel.getName() + "</name>"
                + "<laboratory>" + productmodel.getLaboratory() + "</laboratory>"
                + "<certification>" + productmodel.getCertification() + "</certification>"
                + "</product>");

        String sentency = "Select * from insertproduct('" + structure + "')";
        System.out.println(structure);
        return con.modifyBD(sentency);
    }
    
}
