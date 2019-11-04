package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.dto.ProjectDTO;
import com.art.xuptbbs.model.Project;
import com.art.xuptbbs.service.ProjectService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Validated
@ResponseBody
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("project")
    public BaseResponse CreateProject(@Validated ProjectDTO projectDTO, String[] label){
        return projectService.CreateProject(projectDTO,label);
    }

    @GetMapping("project/{id}")
    public BaseResponse GetProject(@PathVariable Long id ){
        Project projectVO =  projectService.getProject(id);
        return ResponseData.out(CodeEnum.SUCCESS,projectVO);
    }

    @GetMapping("project")
    public Page<Project> GetAllProject(@Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
                                       @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<Project> projectPage;
        projectPage = projectService.getAllProject(pageRequest);
        return projectPage;
    }

    @GetMapping("project/type")
    public Page<Project> GetTypeProject(
            @NotNull @RequestParam(value = "id") Long id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<Project> projectPage;
        projectPage = projectService.getTypeProject(pageRequest,id);
        return projectPage;
    }

    @GetMapping("project/myproject")
    public Page<Project> GetMyProject(
            @NotNull @RequestParam(value = "id") String id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<Project> projectPage;
        projectPage = projectService.GetMyProject(pageRequest,id);
        return projectPage;
    }
}
