package RestAPIServer.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import RestAPIServer.entity.Vm;
import RestAPIServer.mapper.VmMapper;

public class DbDao {
    
    public SqlSessionFactory sqlSessionFactory;
    
    private DbDao(){
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(super.getClass().getClassLoader().getResourceAsStream("mybatis-config.xml"));
    }

    private static class InnerDbDaoClass{
        private static final DbDao uniqueDbDaoInstance = new DbDao();
    }

    public static DbDao getInstance(){
        return InnerDbDaoClass.uniqueDbDaoInstance;
    }

    public Vm getVm(Integer macAddress){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            return vmMapper.getVm(macAddress);
        }finally{
            sqlSession.close();
        }
    }

    public void createVm(Vm vm){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            vmMapper.createVm(vm);
        }finally{
            sqlSession.close();
        }
    }

    public void updateVm(Vm vm){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            vmMapper.updateVm(vm);
        }finally{
            sqlSession.close();
        }
    }

    public void deleteVm(Integer macAddress){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            vmMapper.deleteVm(macAddress);
        }finally{
            sqlSession.close();
        }
    }

    public void runVm(Integer macAddress){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            vmMapper.runVm(macAddress);
        }finally{
            sqlSession.close();
        }
    }

    public void stopVm(Integer macAddress){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            VmMapper vmMapper = sqlSession.getMapper(VmMapper.class);
            vmMapper.stopVm(macAddress);
        }finally{
            sqlSession.close();
        }
    }

}
