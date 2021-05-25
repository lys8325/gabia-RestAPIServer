package RestAPIServer.RPCClient;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.data.Method;
import org.restlet.data.Protocol;

public class RPCClient {

    private RPCClient() {}

    public static RPCClient getInstance(){
        return InnerInstanceClass.uniqueInstance;
    }

    private static class InnerInstanceClass{
        private static final RPCClient uniqueInstance = new RPCClient();
    }

    public void runVm(Integer macAddress){
        Client client = new Client(new Context(), Protocol.HTTP);
        Request request = new Request();
        request.setResourceRef("http://localhost:8889/vmRunAction/" + macAddress);
        request.setMethod(Method.GET);
        client.handle(request);
    }

    public void stopVm(Integer macAddress){
        Client client = new Client(new Context(), Protocol.HTTP);
        Request request = new Request();
        request.setResourceRef("http://localhost:8889/vmStopAction/" + macAddress);
        request.setMethod(Method.GET);
        client.handle(request);
    }
}
