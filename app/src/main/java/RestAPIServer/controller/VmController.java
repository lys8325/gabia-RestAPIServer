package RestAPIServer.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Patch;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import RestAPIServer.entity.Vm;

public class VmController extends ServerResource{
    
    @Post("json")
    public void createVm(Representation entity) throws JSONException, IOException{
        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        
        System.out.println(vm.toString());
    }

    @Get
    public void readVm(){
        System.out.println((String)this.getRequestAttributes().get("macAddress"));
    }

    @Patch("json")
    public void updateVm(Representation entity) throws JSONException, IOException{
        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        
        System.out.println(vm.toString());
    }

    @Delete
    public void deleteVm(){
        System.out.println((String)this.getRequestAttributes().get("macAddress"));
    }
}