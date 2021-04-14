package com.hhgs.app.mapper.sys;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("sysTemplate")
public interface SysUserMapper {
    int countByUserName(@Param("username") String username);
}
