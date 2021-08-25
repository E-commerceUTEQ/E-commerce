package Models;

/**
 *
 * @author Faroukmn_97
 */
public class Pharm_producmodel {
    
   private String pharmpro_id= "-2"; 
   private String pharmacy_id= "-2"; 
   private String product_id= "-2"; 
   private String price= "-2"; 

    public Pharm_producmodel() {
    }
   

    public String getPharmpro_id() {
        return pharmpro_id;
    }

    public void setPharmpro_id(String pharmpro_id) {
        this.pharmpro_id = pharmpro_id;
    }

    public String getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(String pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
   
   
    
}
