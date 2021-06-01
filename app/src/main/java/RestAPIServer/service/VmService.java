package RestAPIServer.service;

import java.net.MalformedURLException;
import org.apache.xmlrpc.XmlRpcException;
import RestAPIServer.dao.DbDao;
import RestAPIServer.entity.Vm;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VmService {

    private DbDao dbDao = DbDao.getInstance();
    private XmlClientService xmlClientService = XmlClientService.getInstance();

    private static class InnerVmServiceClass{
        private final static VmService uniqueVmServiceInstance = new VmService();
    }

    public static VmService getInstance(){
        return InnerVmServiceClass.uniqueVmServiceInstance;
    }

    public String createVm(Vm vm){

        String response;

        // 유효성 검사.
        if(vm.getCpu() == null || vm.getMemory() == null){
            response = "cpu and memory column should not be null.";
        }else{
            dbDao.createVm(vm);
            response = "new vm has been created!";
        }

        return response;

    }

    public Vm getVm(Integer macAddress){

        Vm vm = dbDao.getVm(macAddress);
        return vm;

    }

    public String updateVm(Vm vm){

        Integer macAddress = vm.getMacAddress();
        Vm originalVm = getVm(macAddress);
        String response = "";

        // 존재 여부 확인.
        if(originalVm == null){
            response = String.format("there is no vm ( mac_address : %08d ).", macAddress);
        }else{
            String status = originalVm.getStatus();

            // status 확인.
            if(status.equals("deleted")){
                response = String.format("vm ( mac_address : %08d ) does not exist. already deleted.", macAddress);
            }else if(status.equals("running")){
                vm.setCpu(null);
                vm.setMemory(null);
                
                dbDao.updateVm(vm);
                response = String.format("vm ( mac_address : %08d ) is running. Only 'description' field can be updated.",macAddress);
            }else if(status.equals("stopped")){
                dbDao.updateVm(vm);
                response = String.format("vm ( mac_address : %08d ) has been updated!", macAddress);
            }
        }

        return response;

    }

    public String deleteVm(Integer macAddress){

        Vm vm = getVm(macAddress);
        String response = "";

        // 존재 여부 확인.
        if(vm == null){
            response = String.format("there is no vm ( mac_address : %08d ).", macAddress);
        }else{
            String status = vm.getStatus();

            // status 확인.
            if(status.equals("deleted")){
                response = String.format("vm ( mac_address : %08d ) does not exist. already deleted.", macAddress);
            }else if(status.equals("running")){
                response = String.format("can't delete vm. vm ( mac_address : %08d ) is still running.", macAddress);
            }else if(status.equals("stopped")){
                dbDao.deleteVm(macAddress);
                response = String.format("vm ( mac_address : %08d ) has been deleted!", macAddress);
            }
        }
        
        return response;

    }

    public String runVm(Integer macAddress) throws MalformedURLException, XmlRpcException{

        Vm vm = getVm(macAddress);
        String response = "";

        // 존재 여부 확인.
        if(vm == null){
            response = String.format("there is no vm ( mac_address : %08d ).", macAddress);
        }else{
            String status = vm.getStatus();

            // status 확인.
            if(status.equals("deleted")){
                response = String.format("vm ( mac_address : %08d ) does not exist. already deleted.", macAddress);
            }else if(status.equals("running")){
                response = String.format("vm ( mac_address : %08d ) is already running.", macAddress);
            }else if(status.equals("stopped")){
                xmlClientService.runVm(macAddress);
                dbDao.runVm(macAddress);
                response = String.format("vm ( mac_address : %08d ) has been started!", macAddress);
            }
        }
        
        return response;

    }

    public String stopVm(Integer macAddress) throws MalformedURLException, XmlRpcException{

        Vm vm = getVm(macAddress);
        String response = "";

        // 존재 여부 확인.
        if(vm == null){
            response = String.format("there is no vm ( mac_address : %08d ).", macAddress);
        }else{
            String status = vm.getStatus();

            // status 확인.
            if(status.equals("deleted")){
                response = String.format("vm ( mac_address : %08d ) does not exist. already deleted.", macAddress);
            }else if(status.equals("running")){
                xmlClientService.stopVm(macAddress);
                dbDao.stopVm(macAddress);
                response = String.format("vm ( mac_address : %08d ) has been stopped!", macAddress);
            }else if(status.equals("stopped")){
                response = String.format("vm ( mac_address : %08d ) is already stopped.", macAddress);
            }
        }
        
        return response;

    }

}
