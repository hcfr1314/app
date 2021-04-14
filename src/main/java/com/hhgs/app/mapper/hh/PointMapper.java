package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.Points;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hhTemplate")
public interface PointMapper {

    List<Points> queryByType(@Param("type") String type);

    List<Points> queryIndicatorGrapIndex(@Param("plantCode") int plantCode);

    List<Points> queryByType(@Param("type") int type);

}
