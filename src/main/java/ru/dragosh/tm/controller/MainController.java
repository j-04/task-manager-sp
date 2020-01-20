package ru.dragosh.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.service.ProjectService;
import ru.dragosh.tm.service.TaskService;

import java.util.List;

@Controller
public class MainController {
    @Qualifier("getTaskService")
    @Autowired
    private TaskService taskService;

    @Qualifier("getProjectService")
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getGreeting() {
        return "index";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projects(final ModelMap modelMap) {
        modelMap.addAttribute("projects", projectService.getAll());
        return "projects";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String allTasks(ModelMap modelMap) {
        List<Task> list = taskService.getAll();
        modelMap.addAttribute("tasks", list);
        return "tasks";
    }
}
