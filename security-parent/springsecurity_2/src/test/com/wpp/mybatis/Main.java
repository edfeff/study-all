package com.wpp.mybatis;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @author wangpp
 */
public class Main {
    @Test
    public void t1() {
//        String driver, String url, String username, String password
        DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/security", "root", "root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("dev", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(UserMapper.class);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> allUser = mapper.getAllUser();

        allUser.stream().forEach(System.out::println);

        allUser = mapper.getAllUser();

        allUser.stream().forEach(System.out::println);

        User admin = mapper.getUserByName("admin");
        System.out.println(admin);
    }
}

interface UserMapper {
    @Results( id = "aaa",
            value = {
                    @Result( id = true, property = "id", column = "id" ),
                    @Result( property = "username", column = "username" ),
                    @Result( property = "realName", column = "real_name" ),
                    @Result( property = "password", column = "password" ),
                    @Result( property = "createTime", column = "create_time" ),
                    @Result( property = "lastLoginTime", column = "last_login_time" ),
                    @Result( property = "enabled", column = "enabled" ),
                    @Result( property = "expired", column = "expired" ),
                    @Result( property = "locked", column = "locked" ),
                    @Result( property = "credentialsExpired", column = "credentials_expired" ),
                    @Result( property = "remark", column = "remark" ),
            }
    )
//    @ResultMap( "aaa" )
    @Select( "SELECT * FROM SYS_USER" )
    List<User> getAllUser();

    @ResultMap( "aaa" )
    @Select( "SELECT * FROM SYS_USER WHERE username=#{name}" )
    User getUserByName(String name);
}

class User {
    private Integer id;
    private String username;
    private String realName;
    private String password;
    private Date createTime;
    private Date lastLoginTime;
    private Boolean enabled;
    private Boolean expired;
    private Boolean locked;
    private Boolean credentialsExpired;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", enabled=" + enabled +
                ", expired=" + expired +
                ", locked=" + locked +
                ", credentialsExpired=" + credentialsExpired +
                ", remark='" + remark + '\'' +
                '}';
    }
}