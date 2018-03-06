package com.terry.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.terry.Bean.BaseResponse;
import com.terry.Bean.Pswd;
import com.terry.Bean.User;
import com.terry.Repository.PwdRepository;
import com.terry.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * *
 * 名称：     LoginService
 * 作者：     Terry
 * 创建时间：  on 2018/1/30.
 * 说明：     
 * *
 ***/

@Service
public class UserService {

    @Autowired
    PwdRepository pwdRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenAPI tokenAPI;

    public BaseResponse signOrLogin(String name, String pwd) {
        BaseResponse back = new BaseResponse();
        Pswd rpwd = pwdRepository.findByUsername(name);
        if (rpwd == null) {
            //新用户自动注册
            try {
                Pswd np = new Pswd();
                np.username = name;
                np.password = pwd;
                np = pwdRepository.save(np);
                User user = new User();
                user.id = np.id;
                user.name = np.username;
                user.age = 25;
                user.sex = "boy";
                userRepository.save(user);
                back.code = 0;
                back.msg = "sign up success";
                back.data = tokenAPI.getToken(np.id);
            } catch (Exception e) {
                back.code = 3;
                back.msg = e.getMessage();
                return back;
            }
        } else if (rpwd.password.equals(pwd)) {
            //老用户直接登录
            back.code = 0;
            back.msg = "login success";
            back.data = tokenAPI.getToken(rpwd.id);
        } else {
            back.code = 4;
            back.msg = "login failed";
        }
        return back;
    }

    public BaseResponse getUserInfo(String token) {
        BaseResponse back = new BaseResponse();
        String id = getUid(token);
        back.data = userRepository.findOne(id);
        if (back.data != null) {
            back.code = 0;
            back.msg = "success";
        }
        return back;
    }

    public BaseResponse getUserList(String token) {
        BaseResponse back = new BaseResponse();
        back.code = 0;
        back.msg = "success";
        back.data = userRepository.findAll();
        return back;
    }

    private String getUid(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

}
