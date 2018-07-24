package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.repositorys.Repository;
import com.example.demo.repositorys.RepositoryRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    //создание объекта person
    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        Person person = new Person();
        model.addAttribute("person", person);
        model.addAttribute("persons", repository.findAll());

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("person") Person person) {

        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String username = person.getUsername();
        String password = "{bcrypt}" + new BCryptPasswordEncoder().encode(person.getPassword());

        if (firstName != null && firstName.length() > 0
                && lastName != null && lastName.length() > 0) {

                Person newPerson = new Person(firstName, lastName, username, password);
                repository.save(newPerson);

                return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage1);
        return "addPerson";
    }

// страница профиля Person
    @RequestMapping(value = {"/profilePer/{id}"}, method = RequestMethod.GET)
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", repository.getById(id));
        return "profilePer";
    }
// страница по изменению данных Person
    @RequestMapping(value = {"/updatePer/{id}"}, method = RequestMethod.GET)
    public String updatePer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", repository.getById(id));
        model.addAttribute("roles", repositoryRole.findAll());
        model.addAttribute("persons", repository.findAll());
        return "renamePer";
    }
// функция изменения данных Person
    @RequestMapping(value = {"/updatePer/{id}"}, method = RequestMethod.POST)
    public String savePer(@PathVariable("id") Long id,
                          @ModelAttribute("person") Person person) {
        Optional<Person> editPerson = repository.findById(id);
        if (repository.findById(id).isPresent()) {
            Person currentPerson = editPerson.get();
            person.setId(currentPerson.getId());
            person.setPassword(currentPerson.getPassword());
            repository.save(person);
        }
        return "redirect:/profilePer/" + person.getId();
    }
    // удаление объекта person по fistname and lastname
    @RequestMapping(value = {"/delPerson"}, method = RequestMethod.POST)
    public String delPerson(Model model, //
                            @ModelAttribute("person") Person person) {
        try {
            person = repository.findByFirstNameAndLastName(
                    person.getFirstName(),
                    person.getLastName());
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

        Person person = new Person();
        model.addAttribute("person", person);

        return "delPerson";
    }

    //удаление одного объекта person по id
    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") Long id) {

        repository.deleteById(id);

        return "redirect:/personList";
    }

    @RequestMapping(value = {"delete"}, method = RequestMethod.GET)
    public String showdeletePersonPage(Model model) {

        Person person = new Person();
        model.addAttribute("person", person);

        return "personList";
    }

}
