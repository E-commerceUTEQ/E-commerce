package allApis;

import DAO.ProductDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Models.Productmodel;
import com.google.gson.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Faroukmn_97
 */
@Path("product")
public class Productresource {

    @Context
    private UriInfo context;

    public Productresource() {

    }

    ProductDAO productDAO = new ProductDAO();
    String productd = productDAO.selectProduct();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProduct() {
        //TODO return proper representation object
        return Response.ok(productd)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    String rproduct = productDAO.selectRegisteredProduct();
    @GET
    @Path("/allregisteredproduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRegisteredProduct() {
        //TODO return proper representation object
        return Response.ok(rproduct)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/productbypharm")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response productbypharm(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
             String pharmacy_id = Methods.JsonToString(Jso.getAsJsonObject(), "pharmacy_id", "");
            String prodbypharm = productDAO.selectProductbypharm(pharmacy_id);
            responseJson = "{\"message\":\" Data returned successfully.\",\"flag\":true,\"data\":" + prodbypharm + "}";
        }else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + false + "}";
        }
        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertproduct(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertpro = false;
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            Productmodel pm = new Productmodel();
            pm.setName(Methods.JsonToString(Jso.getAsJsonObject(), "name", ""));
            pm.setLaboratory(Methods.JsonToString(Jso.getAsJsonObject(), "laboratory", ""));
            pm.setCertification(Methods.JsonToString(Jso.getAsJsonObject(), "certification", ""));
            pm.setPhoto(Methods.JsonToString(Jso.getAsJsonObject(), "photo", ""));
            
            insertpro = productDAO.insertProduct(pm);
            if (insertpro) {
                responseJson = "{ \"status\":" + insertpro + ",\"information\": \" The Product was inserted.\"}";
            } else {
                responseJson = "{ \"status\":" + insertpro + ",\"information\": \" The Product was not inserted.\"}";
            }

        } else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + insertpro + "}";
        }

        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

}
