package com.hhgs.app.service;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     */
    PageInfo<User> findAllUser(int pageSize, int pageNum);

    /**
     * 查询用户下的角色
     * @return
     */
    User queryUserByUsername(String username);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 编辑用户
     * @param user
     */
    void editUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(int userId);

    /**
     * 查找不属于该用户的其他角色
     * @param userId
     * @return
     */
    public List<Role> findOtherRoles(int userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    public void addRolesToUser(int userId, List<Integer> roleIds);


    /**
     * 删除当前用户拥有角色
     * @param userId
     * @param roleIds
     */
    void deleteRole(int userId,List<Integer> roleIds);


    /**
     * 校验原始密码
     * @param user
     * @param originalPassword
     * @return
     */
    int checkPassword(User user, String originalPassword);
}
