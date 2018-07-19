package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.person.PersonForm;
import com.example.demo.repositorys.Repository;
import com.example.demo.repositorys.RepositoryRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class Control {
    private Repository repository;
    private RepositoryRole repositoryRole;

    @Autowired
    public Control(Repository repository, RepositoryRole repositoryRole) {
        this.repository = repository;
        this.repositoryRole = repositoryRole;
    }

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message1}")
    private String errorMessage1;
    @Value("${error.message2}")
    private String errorMessage2;
    //главная страница
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }
    //страница пользователей
    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model) {

        model.addAttribute("persons", repository.findAll());
        model.addAttribute("roles", repositoryRole.findAll());

        return "personList";
    }

    @RequestMapping(value = {"/profilePer"}, params = {"id"}, method = RequestMethod.GET)
    public String getById(Model model, @RequestParam("id") String id){
        model.addAttribute("person", repository.getById((Long.valueOf(id))));
        return "profilePer";
    }

    //создание объекта person
    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        String username = personForm.getUsername();
        String password = "{bcrypt}" + new BCryptPasswordEncoder().encode(personForm.getPassword());

        if (firstName != null && firstName.length() > 0
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName, username, password);
            repository.save(newPerson);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage1);
        return "addPerson";
    }
// удаление объекта person по fistname and lastname
    @RequestMapping(value = {"/delPerson"}, method = RequestMethod.POST)
    public String delPerson(Model model, //
                            @ModelAttribute("personForm") PersonForm personForm) {
        try {
            Person person = repository.findByFirstNameAndLastName(
                    personForm.getFirstName(),
                    personForm.getLastName());
            if (person != null) {
                repository.delete(person);
            }
        } catch (Exception ignored) {
            model.addAttribute("errorMessage", errorMessage2);
            return "delPerson";
        }
        return "redirect:/personList";

    }

    @RequestMapping(value = {"/delPerson"}, method = RequestMethod.GET)
    public String showDelPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "delPerson";
    }

    //удаление одного объекта person по id
    @RequestMapping(value = {"/delete"}, params = {"id"}, method = RequestMethod.GET)
    public String deletePerson(Model model, @RequestParam("id") String id) {

            repository.deleteById(Long.valueOf(id));

        return "redirect:/personList";
    }
    @RequestMapping(value = {"delete"}, method = RequestMethod.GET)
    public String showdeletePersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "personList";
    }

}
