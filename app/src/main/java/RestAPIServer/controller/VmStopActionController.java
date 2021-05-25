package RestAPIServer.controller;

import org.restlet.resource.ServerResource;
import RestAPIServer.RPCClient.RPCClient;

import java.net.MalformedURLException;

import org.apache.xmlrpc.XmlRpcException;
import org.restlet.resource.Get;

public class VmStopActionController extends ServerResource{

    //private RPCClient rPCClient = RPCClient.getInstance();
    private XMLClient xmlClient = XMLClient.getInstance();

    @Get
    public void stopVm() throws MalformedURLException, XmlRpcException {
        String targetMacAddressString = (String)this.getRequestAttributes().get("macAddress");
        int targetMacAddress = Integer.parseInt(targetMacAddressString);

        // 조회 - 존재 여부 체크.
        // status 확인.
        // 수정.
        //rPCClient.stopVm(targetMacAddress);
        xmlClient.stopVm(targetMacAddress);
    }
    
}
