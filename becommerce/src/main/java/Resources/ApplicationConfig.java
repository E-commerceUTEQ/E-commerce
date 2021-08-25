package Resources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Faroukmn_97
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
        }
    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(allApis.Pharm_prodresource.class);
        resources.add(allApis.Pharmacyresource.class);
        resources.add(allApis.Productresource.class);
        resources.add(allApis.Userresource.class);
    } 
}
