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
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.enumarate.Status;
import ru.dragosh.tm.service.ProjectService;
import ru.dragosh.tm.service.TaskService;
import ru.dragosh.tm.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/tasks/*")
public class TaskController {
    @Qualifier("getTaskService")
    @Autowired
    private TaskService taskService;

    @Qualifier("getProjectService")
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addTask(ModelMap modelMap) {
        List<Project> projects = projectService.getAll();
        modelMap.addAttribute("projects", projects);
        modelMap.addAttribute("scheduled", Status.SCHEDULED);
        modelMap.addAttribute("processing", Status.PROCESSING);
        modelMap.addAttribute("finished", Status.FINISHED);
        return "addTask";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.GET)
    public String addTaskPost(@RequestParam("projectId") final String projectId,
                              @RequestParam("name") final String name,
                              @RequestParam("description") final String description,
                              @RequestParam("dateStart") final String dateStart,
                              @RequestParam("dateFinish") final String dateFinish,
                              @RequestParam("status") final Status status
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

        Task task = new Task(
                name,
                description,
                dateStart1,
                dateFinish1,
                projectId,
                status);
        taskService.persist(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/delete/{projectId}/{taskId}")
    public String deleteTask(@PathVariable("projectId") final String projectId,
                             @PathVariable("taskId") final String taskId
    ) {
        taskService.deleteByProjectIdAndTaskId(projectId, taskId);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/editPage/{projectId}/{taskId}", method = RequestMethod.GET)
    public String editTaskGet(@PathVariable("projectId") final String projectId,
                              @PathVariable("taskId") final String taskId,
                              ModelMap modelMap) {
        Task task = taskService.getById(projectId, taskId);
        modelMap.addAttribute("task", task);
        modelMap.addAttribute("dateStart", DateUtil.formatDate(task.getDateStart()));
        modelMap.addAttribute("dateFinish", DateUtil.formatDate(task.getDateFinish()));
        modelMap.addAttribute("projectId", projectId);
        modelMap.addAttribute("scheduled", Status.SCHEDULED);
        modelMap.addAttribute("processing", Status.PROCESSING);
        modelMap.addAttribute("finished", Status.FINISHED);
        return "editTask";
    }

    @RequestMapping(value = "/editTask", method = RequestMethod.GET)
    public String editTaskPost(@RequestParam("projectId") final String projectId,
                               @RequestParam("taskId") final String taskId,
                               @RequestParam("name") final String name,
                               @RequestParam("description") final String description,
                               @RequestParam("dateStart") final String dateStart,
                               @RequestParam("dateFinish") final String dateFinish,
                               @RequestParam("status") final Status status
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

        Task task = taskService.getById(projectId, taskId);
        task.setName(name);
        task.setDescription(description);
        task.setDateStart(dateStart1);
        task.setDateFinish(dateFinish1);
        task.setStatus(status);

        taskService.merge(task);
        return "redirect:/tasks";
    }
}
