package RestAPIServer.controller;

import org.restlet.resource.ServerResource;
import RestAPIServer.dao.DbDao;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.restlet.resource.Get;

public class VmStopActionController extends ServerResource{

    private Logger logger = Logger.getLogger(VmStopActionController.class);
    private DbDao dbDao = DbDao.getInstance();
    private XmlClientController xmlClientController = XmlClientController.getInstance();

    @Get
    public void stopVm() throws MalformedURLException, XmlRpcException {
        String targetMacAddress = (String)this.getRequestAttributes().get("macAddress");
        int macAddress = Integer.parseInt(targetMacAddress);

        dbDao.stopVm(macAddress);

        // 조회 - 존재 여부 체크.
        // status 확인.
        // 수정.
        //rPCClient.stopVm(targetMacAddress);
        xmlClientController.stopVm(macAddress);
    }
    
}
