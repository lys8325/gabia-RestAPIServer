package RestAPIServer.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import RestAPIServer.entity.Vm;
import RestAPIServer.mapper.VmMapper;

public class DbDao {
    
    public SqlSessionFactory sqlSessionFactory;
    
    private DbDao(){
        
        String resource = "app/src/main/resources/mybatis-config.xml";
        
        try {
            InputStream inputStream = new FileInputStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private static class InnerDbDaoClass{
        private static final DbDao uniqueDbDaoInstance = new DbDao();
    }

    public static DbDao getInstance(){
        return InnerDbDaoClass.uniqueDbDaoInstance;
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
