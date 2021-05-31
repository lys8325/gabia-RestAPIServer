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

import RestAPIServer.dao.DbDao;
import RestAPIServer.entity.Vm;

public class VmController extends ServerResource{
    
    private DbDao dbDao = DbDao.getInstance();

    @Post("json")
    public String createVm(Representation entity) throws JSONException, IOException{
        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        
        // 유효성 검사.
        if(vm.getCpu().equals(null) || vm.getMemory().equals(null)){
            return "cpu and memory column should not be null\n";
        }else{
            dbDao.createVm(vm);
            return "new vm has been created!\n";
        }
    }

    @Get
    public void getVm(){

        Integer macAddress = Integer.parseInt((String)getRequestAttributes().get("macAddress"));

        Vm vm = dbDao.getVm(macAddress);

        // 존재 여부 체크.
        if(vm.equals(null)){
            return;
        }

        System.out.println(vm.toString());
    }

    @Patch("json")
    public void updateVm(Representation entity) throws JSONException, IOException{
        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        

        // 조회 - 존재 여부 체크.
        // status 확인.
        // 수정.
        // 조회 - 출력.
        dbDao.updateVm(vm);
    }

    @Delete
    public String deleteVm(){
        // 조회 - 존재 여부 체크.
        // status 확인.
        // 삭제.

        
        Integer macAddress = Integer.parseInt((String)this.getRequestAttributes().get("macAddress"));

        dbDao.deleteVm(macAddress);
        return String.format("mac_address : %08d vm has been deleted\n", macAddress);
    }
}