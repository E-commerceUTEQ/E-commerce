package allApis;

import DAO.Pharm_prodDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Models.Pharm_producmodel;
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
@Path("pharm_prod")
public class Pharm_prodresource {

    @Context
    private UriInfo context;

    public Pharm_prodresource() {

    }
    Pharm_prodDAO pharm_prodDAO = new Pharm_prodDAO();
    String pharm_prodd = pharm_prodDAO.selectPharm_prod();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevice() {
        //TODO return proper representation object
        return Response.ok(pharm_prodd)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertpharm_prod")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertpharm_prod(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertpham_pro = false;
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            Pharm_producmodel pm = new Pharm_producmodel();
            pm.setPharmacy_id(Methods.JsonToString(Jso.getAsJsonObject(), "pharmacy_id", ""));
            pm.setProduct_id(Methods.JsonToString(Jso.getAsJsonObject(), "product_id", ""));
            pm.setPrice(Methods.JsonToString(Jso.getAsJsonObject(), "price", ""));

            insertpham_pro = pharm_prodDAO.insertPharm_prod(pm);

            if (insertpham_pro) {
                responseJson = "{ \"status\":" + insertpham_pro + ",\"information\": \" Pharmacy registered product.\"}";
            } else {
                responseJson = "{ \"status\":" + insertpham_pro + ",\"information\": \" Product not registered in the pharmacy.\"}";
            }

        } else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + insertpham_pro + "}";
        }

        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

}
