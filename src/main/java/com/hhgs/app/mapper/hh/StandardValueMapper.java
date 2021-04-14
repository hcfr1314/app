package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.StandardValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface StandardValueMapper {

    int updateStandardValue(@Param("plantCode") int plantCode, @Param("val") double val, @Param("val2") double val2, @Param("name") String name);

    List<StandardValue> queryByPlantCodeList(@Param("codeList") List<Integer> codeList);
}
