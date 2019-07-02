package com.study.boot.mybatis.controller;

import com.study.boot.mybatis.annotation.SystemLog;
import com.study.boot.mybatis.dao.UserMapper;
import com.study.boot.mybatis.entity.User;
import com.study.boot.mybatis.model.UserModel;
import com.study.boot.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingyu Sun
 * @date 2018/12/21 10:08
 */
@Api(value = "api", tags = "test")
@RestController
@RequestMapping("/api")
public class TestController {

    private final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParam(required = true, name = "id", dataType = "string", paramType = "query")
    @GetMapping("/users")
    @SystemLog(desc = "getUserList")
    public Object get(@RequestParam("id") Integer id) {
        log.info("id is {}",id);
        log.error("test error log success");
        log.debug("{}",request.getQueryString());
        return userService.list();
    }

    @GetMapping("/del")
    public Object del() {
        return userMapper.deleteById(3);
    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "id", dataType = "long", paramType = "form", example = "1"),
            @ApiImplicitParam(required = true, name = "age", dataType = "int", paramType = "form", example = "22"),
            @ApiImplicitParam(required = true, name = "name", dataType = "string", paramType = "form", example = "test")
    })
    public Object getAll(UserModel model) {
        System.out.println(model);
        List<User> all = userMapper.findAll();
        List<UserModel> userModels = new ArrayList<>();
        all.forEach(x ->
        {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(x, userModel);
            userModel.setId(x.getId());
            userModels.add(userModel);
        });
        Map<String,Object> map = new HashMap<>(2);
        map.put("code",1);
        map.put("list",userModels);
        return map;
    }
}
