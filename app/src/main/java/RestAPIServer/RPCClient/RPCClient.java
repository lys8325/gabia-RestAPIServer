package RestAPIServer.RPCClient;

import org.restlet.resource.ClientResource;

public class RPCClient extends ClientResource{

    public void runVm(Integer macAddress){
        ClientResource clientResource = new ClientResource("http://localhost:8889/vmRunAction/" + macAddress);
        clientResource.get();
    }

    public void stopVm(Integer macAddress){
        ClientResource clientResource = new ClientResource("http://localhost:8889/vmStopAction/" + macAddress);
        clientResource.get();
    }
}
