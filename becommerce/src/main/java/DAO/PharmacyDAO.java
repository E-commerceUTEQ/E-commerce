package DAO;

import DataStaticBD.Conection;
import Models.Pharmacymodel;

/**
 *
 * @author Faroukmn_97
 */
public class PharmacyDAO {

    Conection con;
    String sentence;

    public PharmacyDAO() {
        con = new Conection();
    }

    public String selectPharmacy() {
        sentence = "select * from tblpharmacy";
        String json = con.getRecordsInJson(sentence);
        return json;
    }
    
    public String selectPharmacybyuser(String user_id) {
        sentence = "select * from tblpharmacy where user_id="+user_id;
        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public boolean insertPharmacy(Pharmacymodel pharmacymodel) {
        String structure = String.format(
                "<pharmacy>"
                + "<user_id>" + pharmacymodel.getUser_id() + "</user_id>"
                + "<name>" + pharmacymodel.getName() + "</name>"
                + "<address>" + pharmacymodel.getAddress() + "</address>"
                + "<phone>" + pharmacymodel.getPhone() + "</phone>"
                + "<hoursoperation>" + pharmacymodel.getHoursoperation() + "</hoursoperation>"
                + "<dateupdate>" + pharmacymodel.getDateupdate() + "</dateupdate>"
                + "</pharmacy>");

        String sentency = "Select * from insertpharmacy('" + structure + "')";
        System.out.println(structure);
        return con.modifyBD(sentency);
    }

}
