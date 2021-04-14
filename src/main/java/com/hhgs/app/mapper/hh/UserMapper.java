package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface UserMapper {
    User loadUserByUsername(@Param("username") String username);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 查询用户下的角色
     * @return
     */
    User queryUserByUsername(@Param("username") String username);


    /**
     * 保存用户
     * @param user
     */
    void saveUser(@Param("user") User user);


    /**
     * 编辑用户
     * @param user
     */
    void editUser(@Param("user")User user);

    /**
     * 查找不属于该用户的其他角色
     * @param userId
     * @return
     */
    List<Role> findOtherRoles (@Param("userId") int userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    void addRolesToUser(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 删除该用户拥有的角色
     * @param userId
     * @param roleId
     */
    void deleteRoleByRoleId(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    /**
     * 根据用户id删除用户
     * @param userId
     */
    void deleteUserByUserId(@Param("userId") int userId);

    /**
     * 根据用户id删除用户角色的中间表数据
     * @param userId
     */
    void deleteUserRoleByUserId(@Param("userId") int userId);
}
