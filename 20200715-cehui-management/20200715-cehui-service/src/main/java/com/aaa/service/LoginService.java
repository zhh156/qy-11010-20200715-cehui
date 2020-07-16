package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.model.User;
import com.aaa.vo.TokenVo;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 15:07
 * @Description
 **/
@Service
public class LoginService extends BaseService<User> {
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 15:18
     * @description:
     *      执行登录操作
     *
     *      pojo：实体类
     *      povo：封装类型（当在实际开发中，会有很多中情况导致多表联查的时候无法装载数据-->我们
     *          就需要根据返回前端的数据去封装一个对象出来-->view object
     * @param user
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo doLogin(User user){
        TokenVo tokenVo = new TokenVo();
        User user1 = new User();
        //1.判断user是否为null
        if(user.getUsername() != null){
            //user不为空
            user1.setUsername(user.getUsername());
            User user2 = super.selectOne(user1);
            //2.判断用户是否存在
            if(user2 == null){
                //用户不存在
                return tokenVo.setType(1).setIsSuccess(false);
            }else {
                //用户存在
                if(user.getPassword() != null ){
                    user1.setPassword(user.getPassword());
                    User user3 = super.selectOne(user1);
                    //3.判断用户对应的密码是否正确
                    if(user3 == null){
                        //密码不正确
                        return tokenVo.setType(2).setIsSuccess(false);
                    }else {
                        //用户对应的密码正确，登录成功
                        //用户登录成功后需要自动生成token来记录用户是否登录成功
                        /**
                         *
                         * !!!mybatis是无法检测连接符的，他会把连接符进行转义（把使用连接符连接的进行切分）
                         * 解决方案：需要把连接符使用空格进行替换掉
                         *
                         */
                        String token = UUID.randomUUID().toString().replaceAll("-", "");
                        user3.setToken(token);
                        Integer update = super.update(user3);
                        if(update > 0){
                            return tokenVo.setIsSuccess(true).setToken(token);
                        }else{
                            return tokenVo.setIsSuccess(false).setType(4);
                        }
                    }
                }
            }
        }else {
            return tokenVo.setIsSuccess(false).setType(4);
        }
        return tokenVo.setIsSuccess(false).setType(4);
    }
}
