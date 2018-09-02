package site.sixteen.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import site.sixteen.entity.User;

import java.util.List;

/**
 * UserDAO
 *
 * @author panhainan@yeah.net(@link https://sixteen.site)
 * @version 1.0
 * @use User数据访问接口
 * @date 2018/9/1
 **/
public interface UserDAO {

    /**
     * 保存User
     * @param user 用户信息
     * @return 操作结果
     */
    Integer save(User user);

    /**
     * 获取User
     * @param id 用户ID
     * @return user
     */
    @Select("select * from user  where id = #{id}")
    User get(int id );


    /**
     * 根据用户姓名模糊搜索用户
     * @param name 用户姓名
     * @return 符合条件的用户列表
     */
    List<User> getUsersLike(String name);
    /**
     * 删除User
     * @param id 用户ID
     * @return 操作结果
     */
    @Delete("delete from user where id=#{id}")
    Integer remove(int id);
}
