package com.art.xuptbbs.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectLabelMapper {

    void createPorjectLabel(@Param("projectid") Long id,@Param("label") String label);
}
