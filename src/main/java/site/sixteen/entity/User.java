package site.sixteen.entity;

import lombok.*;

import java.util.Date;

/**
 * User
 *
 * @author panhainan@yeah.net(@link https://sixteen.site)
 * @version 1.0
 * @use 用户表对应实体
 * @date 2018/9/1
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {


    /**
     * 编号，数据库主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 毕业学校
     */
    private String graduateSchool;

    public User(String name, Date birthday, String email, String graduateSchool) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.graduateSchool = graduateSchool;
    }

}
