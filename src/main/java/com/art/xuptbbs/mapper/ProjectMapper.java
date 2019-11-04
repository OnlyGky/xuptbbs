package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {

    Long createProject(Project project);

    Project getProject(Long id);

    List<Project> getAllProject();

    List<Project> getTypeProject(Long id);

    List<Project> GetMyProject(String id);
}
