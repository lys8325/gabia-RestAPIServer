package RestAPIServer.mapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import RestAPIServer.entity.Vm;

public class DBManager {
    
    public SqlSessionFactory sqlSessionFactory;
    
    private DBManager(){
        
        String resource = "app/src/main/resources/mybatis-config.xml";
        
        try {
            InputStream inputStream = new FileInputStream(resource);
            System.out.println("until!================================================");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("here!================================================");
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private static class InnerDBManagerClass{
        private static final DBManager uniqueDBManagerInstance = new DBManager();
    }

    public static DBManager getInstance(){
        return InnerDBManagerClass.uniqueDBManagerInstance;
    }

    public Vm getVmByMacAddress(Integer macAddress){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            return vmMapper.getVmByMacAddress(macAddress);
        }finally{
            sqlSession.close();
        }
       
    }
}
