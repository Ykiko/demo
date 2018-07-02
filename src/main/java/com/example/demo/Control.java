package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Control {

    private static List<Person> persons = new ArrayList<>();

    // функция поиска по first и Last name
    private Person getPersonByName(String firstName, String lastName) {
        Person person = null;
        for (Person buffer : persons) {
            if (buffer.getFirstName().equals(firstName)
                    && buffer.getLastName().equals(lastName)) {
                person = buffer;
            }
        }
        return person;
    }
    // ​​​
    private Person getPersonById(Long id) {
        Person person = null;
        for (Person buffer : persons) {
            if (buffer.getId().equals(id)) {
                person = buffer;
            }
        }
        return person;
    }
    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message1}")
    private String errorMessage1;
    @Value("${error.message2}")
    private String errorMessage2;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model) {

        model.addAttribute("persons", persons);

        return "personList";
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
        Long id = (long) (persons.size() + 1);

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(id, firstName, lastName);
            persons.add(newPerson);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage1);
        return "addPerson";
    }
// удаление объекта person
    @RequestMapping(value = {"/delPerson"}, method = RequestMethod.POST)
    public String delPerson(Model model, //
                            @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        Person person = getPersonByName(firstName, lastName);
        if (person != null) {
            persons.remove(person);
            return "redirect:/personList";
        }
        model.addAttribute("errorMessage", errorMessage2);
        return "delPerson";
    }

    @RequestMapping(value = {"/delPerson"}, method = RequestMethod.GET)
    public String showDelPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "delPerson";
    }


    @RequestMapping(value = {"/delete"}, params = {"id"}, method = RequestMethod.GET)
    public String deletePerson(Model model, @RequestParam("id") String id) {

        Person person = getPersonById(Long.valueOf(id));
        if (person != null) {
            persons.remove(person);
            return "redirect:/personList";
        }
        return "redirect:/personList";
    }
    @RequestMapping(value = {"delete"}, method = RequestMethod.GET)
    public String showdeletePersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "personList";
    }

}
