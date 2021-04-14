package com.hhgs.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhgs.app.config.SecurityConfig;
import com.hhgs.app.mapper.hh.UserMapper;
import com.hhgs.app.mapper.sys.SysUserMapper;
import com.hhgs.app.model.DO.user.Group;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserMapper userMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SecurityConfig securityConfig;


    @Override
    public PageInfo<User> findAllUser(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.findAllUser();
        for (User user : userList) {
            user.setRoles(new ArrayList<Role>());
            user.setGroups(new ArrayList<Group>());
        }
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }

    @Override
    public User queryUserByUsername(String username) {
        User user = userMapper.queryUserByUsername(username);
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        String encode = securityConfig.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encode);
        userMapper.saveUser(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        String password = user.getPassword();
        if(password != null) {
            String encodePassword = securityConfig.getPasswordEncoder().encode(password);
            user.setPassword(encodePassword);
        }
        userMapper.editUser(user);
    }

    @Override
    @Transactional
    public int checkPassword(User user, String originalPassword) {
        User dbUser = userMapper.queryUserByUsername(user.getUsername());
        String dbPassword = dbUser.getPassword();

        int repCode = 0;

        //校验密码和原始密码是否匹配
        boolean isMatches = securityConfig.getPasswordEncoder().matches(originalPassword, dbPassword);
        if(isMatches) {
            repCode = 1;
        }
        return repCode;
    }

    @Override
    @Transactional
    public void deleteUser(int userId) {
        userMapper.deleteUserByUserId(userId);
        userMapper.deleteUserRoleByUserId(userId);
    }


    @Override
    public List<Role> findOtherRoles(int userId) {
        List<Role> otherRoles = userMapper.findOtherRoles(userId);
        return otherRoles;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRolesToUser(int userId, List<Integer> roleIds) {
        for (int roleId : roleIds) {
            userMapper.addRolesToUser(userId,roleId);
        }
    }

    @Override
    public void deleteRole(int userId, List<Integer> roleIds) {
        for (Integer roleId : roleIds) {
            userMapper.deleteRoleByRoleId(userId,roleId);
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userMapper.loadUserByUsername(username);

        if(user==null){
            throw new RuntimeException(username+"-------不存在");
        }

        //到另一个数据库mysql查询
        int count=sysUserMapper.countByUserName(username);

        if(count==0){
            throw new RuntimeException(username+"-------不存在mysql库中");
        }

        return user;
    }
}
