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

    public ProductDAO() {
        con = new Conection();
    }

    public String selectProduct() {
        sentence = "select * from tblproduct";
        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public String selectRegisteredProduct() {
        sentence = "select producto.product_id, producto.name, producto.laboratory, producto.certification,producto.photo, profarm.price,\n"
                + "farm.pharmacy_id, farm.user_id as userfarm, farm.name as namefarm, farm.address as addressfarm,\n"
                + "farm.phone as phonefarm, farm.hoursoperation as housfarm, farm.dateupdate as dateupdatefarm\n"
                + "from tblproduct as producto inner join pharm_prod as profarm on profarm.product_id = producto.product_id\n"
                + "inner join tblpharmacy as farm on farm.pharmacy_id = profarm.pharmacy_id";
        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public String selectProductbypharm(String pharmacy_id) {
        sentence = "select tblproduct.product_id, tblproduct.name, tblproduct.laboratory, tblproduct.certification, tblproduct.photo\n"
                + "from pharm_prod as profarm\n"
                + "inner join tblproduct on profarm.product_id = tblproduct.product_id\n"
                + "inner join tblpharmacy on tblpharmacy.pharmacy_id = profarm.pharmacy_id\n"
                + "where tblpharmacy.pharmacy_id =" + pharmacy_id;

        String json = con.getRecordsInJson(sentence);
        return json;
    }

    public boolean insertProduct(Productmodel productmodel) {
        String structure = String.format(
                "<product>"
                + "<name>" + productmodel.getName() + "</name>"
                + "<laboratory>" + productmodel.getLaboratory() + "</laboratory>"
                + "<certification>" + productmodel.getCertification() + "</certification>"
                + "<photo>" + productmodel.getPhoto() + "</photo>"
                + "</product>");

        String sentency = "Select * from insertproduct('" + structure + "')";
        System.out.println(structure);
        return con.modifyBD(sentency);
    }

}
