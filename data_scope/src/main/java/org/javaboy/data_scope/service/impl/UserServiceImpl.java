package org.javaboy.data_scope.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.javaboy.data_scope.entity.User;
import org.javaboy.data_scope.mapper.UserMapper;
import org.javaboy.data_scope.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().eq(User::getUserName, username);
        User one = getOne(qw);
        if (one != null) {
            one.setRoles(userMapper.getRolesByUid(one.getUserId()));
        }
        return one;
    }
}
