package RestAPIServer.controller;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class XmlClientController {

    private static class InnerXmlClientClass{
        private static final XmlClientController uniqueXmlClientInstance = new XmlClientController();
    }

    public static XmlClientController getInstance(){
        return InnerXmlClientClass.uniqueXmlClientInstance;
    }
    
    public void runVm(Integer macAddress) throws XmlRpcException, MalformedURLException{
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
        Vector<Serializable> params = new Vector<Serializable>();

        xmlRpcClientConfigImpl.setServerURL(new URL("http://localhost:8889"));
        xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
        params.add(macAddress);

        String response = (String)xmlRpcClient.execute("vmAction.runVm", params);
        System.out.println(response);
    }

    public void stopVm(Integer macAddress) throws XmlRpcException, MalformedURLException{
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
        Vector<Serializable> params = new Vector<Serializable>();

        xmlRpcClientConfigImpl.setServerURL(new URL("http://localhost:8889"));
        xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
        params.add(macAddress);

        String response = (String)xmlRpcClient.execute("vmAction.stopVm", params);
        System.out.println(response);
    }
}
