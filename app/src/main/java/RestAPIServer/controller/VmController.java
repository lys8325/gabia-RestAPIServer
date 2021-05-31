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
    public void createVm(Representation entity) throws JSONException, IOException{
        JSONObject json = new JSONObject(entity.getText());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        Vm vm = mapper.convertValue(json, Vm.class);
        
        // 유효성 검사 - 인자 확인.
        // 동적 컴포넌트?
        // 생성.
        System.out.println(vm.toString());
        System.out.println("has been created!");
    }

    @Get
    public void readVm(){
        // 조회 - 존재 여부 체크.
        // vm.toString 출력.
        // 리스트?
        Integer macAddress = Integer.parseInt((String)getRequestAttributes().get("macAddress"));
        
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        Vm vm = dbDao.getVmByMacAddress(macAddress);

        System.out.println(vm.toString());
        //sqlSession.close();
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
        System.out.println(vm.toString());
    }

    @Delete
    public void deleteVm(){
        // 조회 - 존재 여부 체크.
        // status 확인.
        // 삭제.
        System.out.println((String)this.getRequestAttributes().get("macAddress"));
    }
}