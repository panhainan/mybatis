package site.sixteen;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import site.sixteen.dao.UserDAO;
import site.sixteen.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * MyBatisXmlTest
 *
 * @author panhainan@yeah.net(@link https://sixteen.site)
 * @version 1.0
 * @use MyBatis使用Resources方式加载mybatis-config配置进行测试
 * @date 2018/9/1
 **/
@Slf4j
public class MyBatisXmlTest {


    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testSave() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
//        User user = new User("十六爷", new Date(), "panhainan@yeah.net", "长沙理工大学");
//        User user = new User("十月", new Date(), "shiyue@gmail.com", "清华大学");
//        User user = new User("九月", new Date(), "jiuyue@gmail.com", "北京大学");
        User user = new User("莉莉丝111", new Date(), "lilisi@gmail.com", "北京大学");
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        Integer result = userDAO.save(user);
        log.debug("result:{}",result);
        log.debug("user:{}", user);
        sqlSession.close();
    }

    @Test
    public void testGet() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        int id = 11;
        // User user = sqlSession.selectOne("site.sixteen.dao.UserDAO.get", id);
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        User user = userDAO.get(id);
        User user1 = userDAO.get(id);
        User user2 = userDAO.get(id);
        log.debug("user {}", user);
        log.debug("user1{}", user1);
        log.debug("user2{}", user2);
        User user3 = userDAO.get(id);
        log.debug("user3{}", user3);
        sqlSession.close();
    }

    @Test
    public void testGet1() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        int id = 11;
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        User user = userDAO.get(id);
        log.debug("user {}", user);
        Integer result = userDAO.save(new User("四月", new Date(), "siyue@gmail.com", "清华大学"));
        log.debug("result:{}",result);
        user = userDAO.get(id);
        log.debug("user {}", user);
        sqlSession.close();
    }
    @Test
    public void testGet2() throws IOException {
        SqlSession sqlSession1 = getSqlSessionFactory().openSession(true);
        SqlSession sqlSession2 = getSqlSessionFactory().openSession(true);
        int id = 11;
        UserDAO userDAO = sqlSession1.getMapper(UserDAO.class);
        UserDAO userDAO2 = sqlSession2.getMapper(UserDAO.class);

        User user = userDAO.get(id);
        log.debug("user {}", user);
        User user2 = userDAO2.get(id);
        log.debug("user2{}", user2);

        Integer result = userDAO.updateName("十六111",user.getId());
        log.debug("result:{}",result);

        User user3 = userDAO.get(id);
        log.debug("user3{}", user3);
        //脏读
        User user4 = userDAO2.get(id);
        log.debug("user4{}", user4);

        sqlSession1.close();
        sqlSession2.close();
    }
    @Test
    public void testGetUsersLike() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        String name ="月";
        List<User> userList = userDAO.getUsersLike(name);
        log.debug("{}",userList);
        sqlSession.close();
    }

    @Test
    public void testRemove() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        int id = 2;
        Integer result = userDAO.remove(id);
        log.debug("result:{}",result);
        sqlSession.close();
    }
}
