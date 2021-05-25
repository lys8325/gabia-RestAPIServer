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
public class XMLClient {

    private static class InnerXMLClientClass{
        private static final XMLClient uniqueXMLClientInstance = new XMLClient();
    }

    public static XMLClient getInstance(){
        return InnerXMLClientClass.uniqueXMLClientInstance;
    }
    
    public void runVm(Integer macAddress) throws XmlRpcException, MalformedURLException{
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
        Vector<Serializable> params = new Vector<Serializable>();

        xmlRpcClientConfigImpl.setServerURL(new URL("http://localhost:8889"));
        xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
        params.add(macAddress);

        xmlRpcClient.execute("vmAction.runVm", params);
    }

    public void stopVm(Integer macAddress) throws XmlRpcException, MalformedURLException{
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
        Vector<Serializable> params = new Vector<Serializable>();

        xmlRpcClientConfigImpl.setServerURL(new URL("http://localhost:8889"));
        xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
        params.add(macAddress);

        xmlRpcClient.execute("vmAction.stopVm", params);
    }
}
