package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.user.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    List<Menu> menuList(@Param("list") List<Integer> roleIds);
}
