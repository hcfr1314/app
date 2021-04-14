package com.hhgs.app.service;

import com.hhgs.app.model.DO.user.Menu;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MenuService {

    List<Menu> menuList(Authentication user);
}
