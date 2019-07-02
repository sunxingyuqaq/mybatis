package com.study.boot.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.boot.mybatis.dao.UserMapper;
import com.study.boot.mybatis.entity.User;
import com.study.boot.mybatis.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sxy
 * @date 2018-12-20
 */
@MapperScan("com.study.boot.mybatis.dao")
@EnableSwagger2
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class MybatisApplication {

    /**
     * SQL执行效率插件
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Bean
    @Order(11)
    public CommandLineRunner runner() {
        return (args) -> {
            User u = new User();
            u.setAge(11);
            u.setName("sun");
            u.setEmail("change.com");
            u.setDeleted(true);
            u.setEnable(false);
            userMapper.insert(u);
            User us = new User();
            us.setAge(12);
            us.setName("test");
            us.setEmail("abc.com");
            us.setDeleted(false);
            us.setEnable(true);
            userMapper.insert(us);
            List<User> users = userMapper.selectList(null);
            for (User user : users) {
                System.out.println(user);
            }
            Page<User> page = new Page<>();
            page.setSize(2);
            IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().likeLeft("name", "Jack"));
            userIPage.getRecords().forEach(System.out::println);
            userMapper.deleteById(1);
            User user = userMapper.selectById(2);
            if (user != null) {
                user.setAge(123);
                user.setName("jake");
                user.setDeleted(null);
                userMapper.updateById(user);
            }
            List<User> all = userMapper.findAll();
            all.stream().filter(x -> x.getAge() > 11).forEach(System.out::println);
        };
    }

    @Bean
    @Order(12)
    public CommandLineRunner lineRunner() {
        return (args -> {
            User first = userMapper.selectById(3);
            User second = userMapper.selectById(4);
            userService.changeAge(first, second);
            User byId = userMapper.findById(4L);
            Assert.notNull(byId, "user not null");
            List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("age", 24).
                    and(x -> x.eq("age", 21).or().eq("deleted", false)));
            System.out.println(users.size());
            userMapper.selectList(new QueryWrapper<User>().in("age", 24, 28));
            userMapper.selectList(new QueryWrapper<User>().inSql("age", "select age from user where age = 26"));
            userMapper.selectList(new QueryWrapper<User>().lt("age", 26));
        });
    }
}

