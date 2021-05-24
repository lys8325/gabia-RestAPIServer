/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package RestAPIServer;

import org.restlet.data.Protocol;
import RestAPIServer.controller.VmController;
import RestAPIServer.controller.VmRunActionController;
import RestAPIServer.controller.VmStopActionController;

import org.restlet.Application;
import org.restlet.Component;

// import org.restlet.ServerResource;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        Component component =  new Component();
        component.getServers().add(Protocol.HTTP, 8888);

        component.getDefaultHost().attach("/vm/{macAddress}", VmController.class);
        component.getDefaultHost().attach("/vm", VmController.class);
        component.getDefaultHost().attach("/vmRunAction/{macAddress}", VmRunActionController.class);
        component.getDefaultHost().attach("/vmStopAction/{macAddress}", VmStopActionController.class);

        component.start();
    }

}
