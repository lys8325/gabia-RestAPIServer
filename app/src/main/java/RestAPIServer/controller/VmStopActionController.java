package RestAPIServer.controller;

import org.restlet.resource.ServerResource;
import RestAPIServer.RPCClient.RPCClient;
import org.restlet.resource.Get;

public class VmStopActionController extends ServerResource{

    private RPCClient rPCClient = RPCClient.getInstance();

    @Get
    public void stopVm() {
        String targetMacAddressString = (String)this.getRequestAttributes().get("macAddress");
        int targetMacAddress = Integer.parseInt(targetMacAddressString);

        rPCClient.stopVm(targetMacAddress);
    }
    
}
