package ru.dragosh.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.enumarate.Status;
import ru.dragosh.tm.service.ProjectService;
import ru.dragosh.tm.service.TaskService;
import ru.dragosh.tm.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/projects/*")
public class ProjectController {
    @Qualifier("getProjectService")
    @Autowired
    private final ProjectService projectService;

    @Qualifier("getTaskService")
    @Autowired
    private TaskService taskService;

    public ProjectController(@Qualifier("getProjectService") ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addProjectGet(ModelMap modelMap) {
        modelMap.addAttribute("scheduled", Status.SCHEDULED);
        modelMap.addAttribute("processing", Status.PROCESSING);
        modelMap.addAttribute("finished", Status.FINISHED);
        return "addProject";
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.GET)
    public String addProjectPost(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("dateStart") String dateStart,
                                 @RequestParam("dateFinish") String dateFinish,
                                 @RequestParam("status") Status status
    ) throws ParseException {
        System.out.println(dateStart);
        System.out.println(dateFinish);
        Date dateStart1 = null;
        Date dateFinish1 = null;
        try {
            dateStart1 = new SimpleDateFormat("yyyy-mm-dd").parse(dateStart);
            dateFinish1 = new SimpleDateFormat("yyyy-mm-dd").parse(dateFinish);
        } catch (ParseException e) {
            dateStart1 = new SimpleDateFormat("yyyy-mm-dd").parse(DateUtil.DEFAULT_DATE);
            dateFinish1 = new SimpleDateFormat("yyyy-mm-dd").parse(DateUtil.DEFAULT_DATE);
        }
        Project project = new Project(name, description, dateStart1, dateFinish1, status);
        projectService.persist(project);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProjectById(@PathVariable("id") final String id) {
        projectService.delete(id);
        taskService.deleteByProjectId(id);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/editPage/{projectId}", method = RequestMethod.GET)
    public String editProjectGet(@PathVariable("projectId") String projectId, ModelMap modelMap) {
        modelMap.addAttribute("projectId", projectId);
        modelMap.addAttribute("scheduled", Status.SCHEDULED);
        modelMap.addAttribute("processing", Status.PROCESSING);
        modelMap.addAttribute("finished", Status.FINISHED);
        Project project = projectService.getById(projectId);
        modelMap.addAttribute("project", project);
        modelMap.addAttribute("dateStart", DateUtil.formatDate(project.getDateStart()));
        modelMap.addAttribute("dateFinish", DateUtil.formatDate(project.getDateFinish()));
        return "editProject";
    }

    @RequestMapping(value = "/editProject", method = RequestMethod.GET)
    public String editProjectPost(
            @RequestParam("projectId") String projectId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("dateStart") String dateStart,
            @RequestParam("dateFinish") String dateFinish,
            @RequestParam("status") Status status
    ) throws ParseException {
        Date dateStart1 = null;
        Date dateFinish1 = null;
        try {
            dateStart1 = new SimpleDateFormat("yyyy-mm-dd").parse(dateStart);
            dateFinish1 = new SimpleDateFormat("yyyy-mm-dd").parse(dateFinish);
        } catch (ParseException e) {
            dateStart1 = new SimpleDateFormat("yyyy-mm-dd").parse(DateUtil.DEFAULT_DATE);
            dateFinish1 = new SimpleDateFormat("yyyy-mm-dd").parse(DateUtil.DEFAULT_DATE);
        }
        Project project = new Project();
        project.setId(projectId);
        project.setName(name);
        project.setDescription(description);
        project.setDateStart(dateStart1);
        project.setDateFinish(dateFinish1);
        project.setStatus(status);
        projectService.merge(project);
        return "redirect:/projects";
    }
}