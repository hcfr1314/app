package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.MenuMapper;
import com.hhgs.app.model.DO.user.Menu;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> menuList(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            return null;
        }
        List<Role> roles = user.getRoles();
        List<Integer> roleIds = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            roles.stream().forEach(ele -> {
                roleIds.add(ele.getId());
            });
            if (roleIds.size() > 0) {
                return menuMapper.menuList(roleIds);
            }
        }
        return null;
    }

}
