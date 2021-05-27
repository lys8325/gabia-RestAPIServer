package RestAPIServer.mapper;

import RestAPIServer.entity.Vm;

public interface VmMapper {

    public Vm getVmByMacAddress(Integer macAddress);
    
}
