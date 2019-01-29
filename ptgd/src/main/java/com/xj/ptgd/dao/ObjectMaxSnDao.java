package com.xj.ptgd.dao;

import com.xj.ptgd.entity.body.ObjectMaxSn;
import org.apache.ibatis.annotations.Param;

/**
 * 流水号生成 object_maxSn
 * @author cp
 * @since 2018/9/10
 */
public interface ObjectMaxSnDao {

    ObjectMaxSn findObjectMax(@Param("tableName") String tableName,@Param("columnName") String columnName,@Param("dateFmt") String dateFmt);

    void updateObjectMaxSnForMaxSerialNo(@Param("maxSerialNo") String maxSerialNo,@Param("tableName") String tableName,@Param("columnName") String columnName,@Param("dateFmt") String dateFmt);
}
