package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.repositorys.Repository;
import com.example.demo.repositorys.RepositoryRole;
import com.example.demo.role.PersonRoleForm;
import com.example.demo.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//  объявление репозиториев
@Controller
public class ControlRolePer {
    private Repository repository;
    private RepositoryRole repositoryRole;

    @Autowired
    public ControlRolePer(Repository repository, RepositoryRole repositoryRole) {
        this.repository = repository;
        this.repositoryRole = repositoryRole;
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message1}")
    private String errorMessage1;
//  функция добавление role в person
    @RequestMapping(path = "/addRolePers", method = RequestMethod.GET)
    public String showAddRolePerPage(Model model) {

        model.addAttribute("persons", repository.findAll());
        model.addAttribute("roles", repositoryRole.findAll());

        return "addRolePers";
    }
//  функция добавления реализуется за счет javascript в addRolePers.html
    @RequestMapping(path = "/set", method = RequestMethod.POST)
    public String setRolePerson(Model model, @RequestBody PersonRoleForm body) {
        if (body.getPersonId() == null || body.getRoleId() == null) { return "error"; }
        Optional<Person> person = repository.findById(body.getPersonId());
        Optional<Role> role = repositoryRole.findById(body.getRoleId());
        if (person.isPresent() && role.isPresent()) {
            person.get().addRole(role.get());
            repository.save(person.get());
        } else {
            return "error";
        }

        return "redirect:/personList";
    }
// функция удаление role из person
    @RequestMapping(value = {"/delrolePerson"}, params = {"id", "role"}, method = RequestMethod.GET)
    public String addRoleto(Model model, @RequestParam("id") String id, @RequestParam("role") String roleName) {

        Optional<Person> person = repository.findById(Long.valueOf(id));
        Role role = repositoryRole.findRoleByRol(roleName);
        model.addAttribute("roles",repositoryRole.findAll());
        person.get().removeRole(role);

        return "redirect:/personList";
    }
}