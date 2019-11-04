package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.dto.ProjectDTO;
import com.art.xuptbbs.interceptor.AuthInterceptor;
import com.art.xuptbbs.mapper.ProjectLabelMapper;
import com.art.xuptbbs.mapper.ProjectMapper;
import com.art.xuptbbs.model.Project;
import com.art.xuptbbs.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectLabelMapper projectLabelMapper;
    @Override
    public BaseResponse CreateProject( ProjectDTO projectDTO, String[]labels) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO,project);
        project.setCreatedAt(LocalDateTime.now());
        project.setLastModified(LocalDateTime.now());
        projectMapper.createProject(project);
        Long id = project.getId();
        if (labels !=null && labels.length >0)
            for (String label : labels){
                projectLabelMapper.createPorjectLabel(id, label);
            }
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public Project getProject(Long id) {
       Project project = projectMapper.getProject(id);
       return project;
    }

    @Override
    public Page<Project> getAllProject(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        var project = projectMapper.getAllProject();
        var pageInfo = new PageInfo<>(project);
        return new PageImpl<>(project, pageable, pageInfo.getTotal());
    }

    @Override
    public Page<Project> getTypeProject(Pageable pageable, Long id) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        var project = projectMapper.getTypeProject(id);
        var pageInfo = new PageInfo<>(project);
        return new PageImpl<>(project, pageable, pageInfo.getTotal());
    }

    @Override
    public Page<Project> GetMyProject(Pageable pageable, String id) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        var project = projectMapper.GetMyProject(id);
        var pageInfo = new PageInfo<>(project);
        return new PageImpl<>(project, pageable, pageInfo.getTotal());
    }
}
