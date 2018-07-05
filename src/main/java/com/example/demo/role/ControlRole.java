package com.example.demo.role;

import com.example.demo.PersonForm;
import com.example.demo.repositorys.RepositoryRole;
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

        RoleForm roleForm = new RoleForm();
        model.addAttribute("roles", repositoryRole.findAll());

        return "rolPerson";
    }
//добавление Роли
    @RequestMapping(value = {"/addRole"}, method = RequestMethod.GET)
    public String showAddRolePage(Model model) {

        RoleForm roleForm = new RoleForm();
        model.addAttribute("roleForm", roleForm);

        return "addRole";
    }

    @RequestMapping(value = {"/addRole"}, method = RequestMethod.POST)
    public String saveRole(Model model, //
                           @ModelAttribute("roleForm") RoleForm roleForm) {
        String role = roleForm.getRole();

        if (role != null) {
            Role newRole = new Role(role);
            repositoryRole.save(newRole);

            return "redirect:/rolPerson";
        }

        model.addAttribute("errorMessage", errorMessage1);
        return "addRole";
    }

    @RequestMapping(value = {"/delete"}, params = {"id"}, method = RequestMethod.GET)
    public String deleteRole(Model model, @RequestParam("id") String id) {

        repositoryRole.deleteById(Long.valueOf(id));

        return "redirect:/rolPerson";
    }
    @RequestMapping(value = {"delete"}, method = RequestMethod.GET)
    public String showdeleteRolePage(Model model) {

        RoleForm roleForm = new RoleForm();
        model.addAttribute("roleForm", roleForm);

        return "rolPerson";
    }
}
