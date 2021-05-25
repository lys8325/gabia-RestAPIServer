package RestAPIServer.controller;

import org.restlet.resource.ServerResource;
import RestAPIServer.RPCClient.RPCClient;
import org.restlet.resource.Get;

public class VmRunActionController extends ServerResource{

    private RPCClient rPCClient = RPCClient.getInstance();

    @Get
    public void runVm() {
        String targetMacAddressString = (String)this.getRequestAttributes().get("macAddress");
        int targetMacAddress = Integer.parseInt(targetMacAddressString);

        // 조회 - 존재 여부 체크.
        // status 확인.
        // 수정.
        rPCClient.runVm(targetMacAddress);
    }
    
}