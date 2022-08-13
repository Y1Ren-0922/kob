package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.dao.UserDao;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/all/")
    public List<User> getAll() {
        return userDao.selectList(null);
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return userDao.selectOne(queryWrapper);
    }

    @GetMapping("/user/add/{userId}/{username}/{password}")
    public String addUser(@PathVariable int userId,
                          @PathVariable String username,
                          @PathVariable String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userId, username, encodedPassword);
        userDao.insert(user);
        return "Add User Successfully";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userDao.deleteById(userId);
        return "Delete User Successfully";
    }

}
