import com.gjsm.dao.UserDao;
import com.gjsm.pojo.QueryVo;
import com.gjsm.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Date on 2020/05/30  下午 08:52
 */
public class MyBatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao mapper;

    @Before/*测试方法之前执行*/
    public void init() throws Exception{
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSeesionFactory工厂
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(in);
        //3。使用工厂生产SqlSession对象
        sqlSession = build.openSession();
        //使用SqlSession创建Dao接口的代理对象
        mapper = sqlSession.getMapper(UserDao.class);
    }
    @After/*测试方法之后执行*/
    public void destroy() throws Exception{
        //释放资源
        sqlSession.close();
        in.close();
    }
    @Test
    public void Testsele(){
        User user1 = new User();
        List<User> all = mapper.findAll();
        //使用代理对象的执行方法
        for(User user:all){
            System.out.println(user);
        }
    }

    @Test
    public void TestseleName(){
        User user1 = new User();
        String name="张";
        List<User> allName = mapper.findAllName(name);
        //使用代理对象的执行方法
        for(User user:allName){
            System.out.println(user);
        }
    }

    @Test
    public void TestseleVO(){
        User user1 = new User();
        QueryVo queryVo = new QueryVo();
        user1.setUsername("%张%");
        queryVo.setUser(user1);
        List<User> all = mapper.findAll();
        List<User> allVO = mapper.findAllVO(queryVo);
        for(User user2:allVO){
            System.out.println(user2);
        }

    }

    @Test
    public void Testdele(){
        Integer id=9;
        Boolean aBoolean = mapper.deleAll(id);
        sqlSession.commit();
        if(aBoolean==true){
            System.out.println("删除成功");
            List<User> all = mapper.findAll();
            //使用代理对象的执行方法
            for(User user1:all){
                System.out.println(user1);
            }
        }
    }

    @Test
    public void Testinse(){
        User user = new User();
        user.setUsername("张国栋");
        user.setAddress("安徽合肥");
        user.setSex("男");
        user.setBirthday(new Date());
        Boolean inseAll = mapper.inseAll(user);
        sqlSession.commit();
        List<User> all = mapper.findAll();
        //使用代理对象的执行方法
        for(User user1:all){
            System.out.println(user1);
        }
    }

    @Test
    public void Testupda(){
        User user = new User();
        user.setId(8);
        user.setUsername("张国栋");
        user.setSex("女");
        Boolean inseAll = mapper.updaAll(user);
        sqlSession.commit();
        List<User> all = mapper.findAll();
        //使用代理对象的执行方法
        for(User user1:all){
            System.out.println(user1);
        }
    }

}
