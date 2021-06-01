package RestAPIServer.controller;

import org.restlet.resource.ServerResource;
import RestAPIServer.service.VmService;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.restlet.resource.Get;

public class VmRunActionController extends ServerResource{

    private Logger logger = Logger.getLogger(VmRunActionController.class);
    private VmService vmService = VmService.getInstance();

    @Get
    public String runVm() throws MalformedURLException, XmlRpcException {

        int macAddress = Integer.parseInt((String)this.getRequestAttributes().get("macAddress"));
        String response = vmService.runVm(macAddress);
    
        logger.info(response);
        return response;
    }
    
}