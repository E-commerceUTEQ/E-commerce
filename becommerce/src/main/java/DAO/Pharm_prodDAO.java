package DAO;

import DataStaticBD.Conection;
import Models.Pharm_producmodel;

/**
 *
 * @author Faroukmn_97
 */
public class Pharm_prodDAO {

    Conection con;
    String sentence;

    public Pharm_prodDAO() {
        con = new Conection();
    }
    
    public String selectPharm_prod() {
        sentence = "select * from pharm_prod";
        String json = con.getRecordsInJson(sentence);
        return json;
    }
    
    public boolean insertPharm_prod(Pharm_producmodel pharm_producmodel) {
        String structure = String.format(
                "<pharm_prod>"
                + "<pharmacy_id>" + pharm_producmodel.getPharmacy_id() + "</pharmacy_id>"
                + "<product_id>" + pharm_producmodel.getProduct_id() + "</product_id>"
                + "<price>" + pharm_producmodel.getPrice() + "</price>"
                + "</pharm_prod>");

        String sentency = "Select * from insertpharm_prod('" + structure + "')";
        System.out.println(structure);
        return con.modifyBD(sentency);
    }

}
