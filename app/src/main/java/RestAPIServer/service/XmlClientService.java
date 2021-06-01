package RestAPIServer.service;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class XmlClientService {

    private static class InnerXmlClientServiceClass{
        private static final XmlClientService uniqueXmlClientServiceInstance = new XmlClientService();
    }

    public static XmlClientService getInstance(){
        return InnerXmlClientServiceClass.uniqueXmlClientServiceInstance;
    }
    
    public void runVm(Integer macAddress) throws XmlRpcException, MalformedURLException{
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
        Vector<Serializable> params = new Vector<Serializable>();

        xmlRpcClientConfigImpl.setServerURL(new URL("http://139.150.74.19:8889"));
        xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
        params.add(macAddress);

        String response = (String)xmlRpcClient.execute("vmAction.runVm", params);
        System.out.println(response);
    }

    public void stopVm(Integer macAddress) throws XmlRpcException, MalformedURLException{
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
        Vector<Serializable> params = new Vector<Serializable>();

        xmlRpcClientConfigImpl.setServerURL(new URL("http://139.150.74.19:8889"));
        xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
        params.add(macAddress);

        String response = (String)xmlRpcClient.execute("vmAction.stopVm", params);
        System.out.println(response);
    }
}
