package RestAPIServer.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Patch;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import RestAPIServer.entity.Vm;
import RestAPIServer.service.VmService;

public class VmController extends ServerResource{

    private VmService vmService = VmService.getInstance();
    private Logger logger = Logger.getLogger(VmController.class);

    @Post("json")
    public String createVm(Representation entity) throws JSONException, IOException{
        
        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        String response = vmService.createVm(vm);

        logger.info(response);
        return response;

    }

    @Get
    public String getVm(){

        Integer macAddress = Integer.parseInt((String)getRequestAttributes().get("macAddress"));
        Vm vm = vmService.getVm(macAddress);
        String response;

        // 존재 여부 검사.
        if(vm == null){
            response = String.format("there is no vm ( mac_address : %08d ).", macAddress);
        }else{

            if(vm.getStatus().equals("deleted")){
                response = String.format("vm ( mac_address : %08d ) does not exist. already deleted.", macAddress);
            }else{
                response = vm.toString();
            }            
        }

        logger.info(response);
        return response;
            
    }

    @Patch("json")
    public String updateVm(Representation entity) throws JSONException, IOException{

        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        String response = vmService.updateVm(vm);

        logger.info(response);
        return response;

    }

    @Delete
    public String deleteVm(){
    
        Integer macAddress = Integer.parseInt((String)this.getRequestAttributes().get("macAddress"));
        String response = vmService.deleteVm(macAddress);


        logger.info(response);
        return response;

    }

}