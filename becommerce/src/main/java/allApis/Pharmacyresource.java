package allApis;

import DAO.PharmacyDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Models.Pharmacymodel;
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
@Path("pharmacy")
public class Pharmacyresource {

    @Context
    private UriInfo context;

    public Pharmacyresource() {
    }

    PharmacyDAO pharmacyDAO = new PharmacyDAO();
    String pharmacyd = pharmacyDAO.selectPharmacy();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPharmacy() {
        //TODO return proper representation object
        return Response.ok(pharmacyd)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/pharmacybyuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pharmacybyuser(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
             String user_id = Methods.JsonToString(Jso.getAsJsonObject(), "user_id", "");
            String pharcybyuser = pharmacyDAO.selectPharmacybyuser(user_id);
            responseJson = "{\"message\":\" Data returned successfully.\",\"flag\":true,\"data\":" + pharcybyuser + "}";
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
    @Path("/insertpharmacy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertpharmacy(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertpharm = false;
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            Pharmacymodel pm = new Pharmacymodel();
            pm.setUser_id(Methods.JsonToString(Jso.getAsJsonObject(), "user_id", ""));
            pm.setName(Methods.JsonToString(Jso.getAsJsonObject(), "name", ""));
            pm.setAddress(Methods.JsonToString(Jso.getAsJsonObject(), "address", ""));
            pm.setPhone(Methods.JsonToString(Jso.getAsJsonObject(), "phone", ""));
            pm.setHoursoperation(Methods.JsonToString(Jso.getAsJsonObject(), "hoursoperation", ""));
            pm.setDateupdate("now()");

            insertpharm = pharmacyDAO.insertPharmacy(pm);
            if (insertpharm) {
                responseJson = "{ \"status\":" + insertpharm + ",\"information\": \" The Pharmacy was inserted.\"}";
            } else {
                responseJson = "{ \"status\":" + insertpharm + ",\"information\": \" The Pharmacy was not inserted.\"}";
            }

        } else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + insertpharm + "}";
        }

        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

}
