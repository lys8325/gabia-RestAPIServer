package RestAPIServer.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class Vm {
    
    public Integer macAddress;
    public String cpu;
    public String memory;
    public String description;
    public String status;
    public Date createDate;
    public Date deleteDate;

    @Override
    public String toString(){
        String ret = new String();

        ret += "{\n";
        ret += "\tmacAddress : " + String.format("%08d", this.getMacAddress()) + "\n";
        ret += "\tcpu : " + this.getCpu() + "\n";
        ret += "\tmemory : " + this.getMemory() + "\n";
        ret += "\tdescription : " + this.getDescription() + "\n";
        ret += "\tstatus : " + this.getStatus() + "\n";
        ret += "\tcreateDate : " + this.getCreateDate() + "\n";
        ret += "\tdeleteDate : " + this.getDeleteDate() + "\n";
        ret += "}";

        return ret;
    }

}
