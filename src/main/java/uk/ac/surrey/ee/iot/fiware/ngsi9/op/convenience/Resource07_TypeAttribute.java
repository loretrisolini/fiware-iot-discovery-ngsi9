/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.surrey.ee.iot.fiware.ngsi9.op.convenience;

import uk.ac.surrey.ee.iot.fiware.ngsi9.pojo.DiscoveryContextAvailabilityResponse;
import uk.ac.surrey.ee.iot.fiware.ngsi9.pojo.RegisterContextRequest;
import uk.ac.surrey.ee.iot.fiware.ngsi9.pojo.ContextRegistrationResponseList;
import uk.ac.surrey.ee.iot.fiware.ngsi9.pojo.StatusCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uk.ac.surrey.ee.iot.fiware.ngsi9.marshall.DiscoveryMarshaller;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import uk.ac.surrey.ee.iot.fiware.ngsi9.storage.db4o.RegisterStoreAccess;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import uk.ac.surrey.ee.iot.fiware.ngsi9.op.standard.Resource01_ContextRegistration;
import uk.ac.surrey.ee.iot.fiware.ngsi9.storage.db4o.RegisterResultFilter;

/**
 * Resource which has only one representation.
 */
public class Resource07_TypeAttribute extends ServerResource {

    @Get
    public Representation getTypeAttribute() {

       //ServletContext context = (ServletContext) getContext().getServerDispatcher().getContext().getAttributes().get("org.restlet.ext.servlet.ServletContext");

        String eType = (String) getRequest().getAttributes().get("typeName");
        String attribute = (String) getRequest().getAttributes().get("attributeName");
        
        System.out.println("CO 7: GET CONTEXT ENTITY TYPE: eId Type = '" + eType + "'" + ", attribute = '" + attribute + "'");

        StatusCode sc = new StatusCode(200, "OK", "Result");
        DiscoveryContextAvailabilityResponse discResp = new DiscoveryContextAvailabilityResponse();

        try {
//            RegisterStoreAccess regStore = new RegisterStoreAccess(context);
            RegisterResultFilter regFilter = new RegisterResultFilter();
            //regStore.openDb4o();
            List<RegisterContextRequest> result = RegisterStoreAccess.getRegByEntityType(eType);
            ContextRegistrationResponseList crrl = new ContextRegistrationResponseList();
            crrl = regFilter.getContRegHasEntityTypeAttr(result, eType, attribute);
//            discResp.setContextRegistrationResponseList(crrl);
            discResp.getContextRegistrationResponse().addAll(crrl.getContextRegistrationResponse());
            discResp = regFilter.removeSharedEntityType(discResp, eType);
            //regStore.closeDb4o();
            try {
                int contRegRespSize = discResp.getContextRegistrationResponse().size();
                if (contRegRespSize < 1) {
                    sc = new StatusCode(404, "Context Element Not Found", "Result");
                }
            } catch (NullPointerException npe) {
                sc = new StatusCode(404, "Context Element Not Found", "result");
            }
        } catch (Exception e) {
            sc = new StatusCode(500, "Internal Error", "result");           
        }
        discResp.setErrorCode(sc);
        
        String acceptType = "";
        int size = getClientInfo().getAcceptedMediaTypes().size();
        if (size > 0) {
            acceptType = getClientInfo().getAcceptedMediaTypes().get(0).getMetadata().getSubType();
        }

        StringRepresentation discRespSr = new StringRepresentation("");
        String discRespMsg = "";

        if (acceptType.equalsIgnoreCase(MediaType.APPLICATION_JSON.getSubType())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            discRespMsg = gson.toJson(discResp);
            discRespSr = new StringRepresentation(discRespMsg, MediaType.APPLICATION_JSON);
        } else {
            DiscoveryMarshaller discMar = new DiscoveryMarshaller();
            try {
                discRespMsg = discMar.marshallResponse(discResp);
                discRespSr = new StringRepresentation(discRespMsg, MediaType.APPLICATION_XML);
            } catch (JAXBException ex) {
                Logger.getLogger(Resource01_ContextRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        System.out.println("Response To Send: \n" + discRespMsg);

        return discRespSr;
    }
    
}
