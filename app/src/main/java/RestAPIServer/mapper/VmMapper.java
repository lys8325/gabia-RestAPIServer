package RestAPIServer.mapper;

import RestAPIServer.entity.Vm;

public interface VmMapper {

    public Vm getVm(Integer macAddress);
    
    public void createVm(Vm vm);

    public void updateVm(Vm vm);

    public void deleteVm(Integer macAddress);
    
}
