package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.ProjectDTO;
import com.art.xuptbbs.model.Project;
import com.art.xuptbbs.vo.ProjectVO;
import com.art.xuptbbs.vo.UserDetailVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    BaseResponse CreateProject(ProjectDTO projectDTO, String[]label);

    Project getProject(Long id);

    Page<Project> getTypeProject(Pageable pageable, Long id);

    Page<Project> getAllProject(Pageable pageable);

    Page<Project> GetMyProject(Pageable pageable, String id);
}
