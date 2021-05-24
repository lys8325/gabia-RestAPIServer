package RestAPIServer.controller;

import org.restlet.resource.ServerResource;
import RestAPIServer.RPCClient.RPCClient;
import org.restlet.resource.Get;

public class VmRunActionController extends ServerResource{

    private RPCClient rPCClient = new RPCClient();

    @Get
    public void runVm() {
        String targetMacAddressString = (String)this.getRequestAttributes().get("macAddress");
        int targetMacAddress = Integer.parseInt(targetMacAddressString);

        rPCClient.runVm(targetMacAddress);
    }
    
}