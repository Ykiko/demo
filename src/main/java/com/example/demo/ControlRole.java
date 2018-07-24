package com.example.demo;

import com.example.demo.repositorys.RepositoryRole;
import com.example.demo.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class ControlRole {
    private RepositoryRole repositoryRole;

    @Autowired
    public ControlRole(RepositoryRole repositoryRole) {
        this.repositoryRole = repositoryRole;
    }

    @Value("Role can not be added!")
    private String errorMessage1;

    // создание формы rolPerson
    @RequestMapping(value = {"/rolPerson"}, method = RequestMethod.GET)
    public String roleList(Model model) {

        Role role = new Role();
        model.addAttribute("roles", repositoryRole.findAll());

        return "rolPerson";
    }

    //добавление Роли
    @RequestMapping(value = {"/addRole"}, method = RequestMethod.GET)
    public String showAddRolePage(Model model) {

        Role role = new Role();
        model.addAttribute("role", role);

        return "addRole";
    }

    //сохранение в репозиторий
    @RequestMapping(value = {"/addRole"}, method = RequestMethod.POST)
    public String saveRole(Model model, //
                           @ModelAttribute("role") Role role) {
        String role = role.getRole();

        if (role != null) {
            Role newRole = new Role(role);
            repositoryRole.save(newRole);

            return "redirect:/rolPerson";
        }

        model.addAttribute("errorMessage", errorMessage1);
        return "addRole";
    }

    //    удаление роли по id, если role не занята в person_role
    @RequestMapping(value = {"/del"}, params = {"id"}, method = RequestMethod.GET)
    public String deleteRole(Model model, @RequestParam("id") Long id) {

        repositoryRole.deleteById(id);

        return "redirect:/rolPerson";
    }

    @RequestMapping(value = {"del"}, method = RequestMethod.GET)
    public String showdeleteRolePage(Model model) {

        Role role = new Role();
        model.addAttribute("role", role);

        return "rolPerson";
    }
}
