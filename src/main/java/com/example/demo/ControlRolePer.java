package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.person.PersonForm;
import com.example.demo.repositorys.Repository;
import com.example.demo.repositorys.RepositoryRole;
import com.example.demo.role.Role;
import com.example.demo.role.RoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @RequestMapping(value = {"/addRolePers"}, method = RequestMethod.GET)
    public String showAddRolePerPage(Model model) {

        model.addAttribute("persons", repository.findAll());
        model.addAttribute("roles", repositoryRole.findAll());

        return "addRolePers";
    }

    @RequestMapping(value = {"set"}, params = {"id", "role"}, method = RequestMethod.POST)
    public String setRolePerson(Model model, @RequestParam("id") String id,
                                  @RequestParam("role") String roleName) {
        if (id.isEmpty() || roleName.isEmpty()) { return "error"; }
        Optional<Person> person = repository.findById(Long.valueOf(id));
        Role role = repositoryRole.findRoleByRol(roleName);
        person.get().addRole(role);

        return "redirect:/personList";
    }

    @RequestMapping(value = {"/delrolePerson"}, params = {"id", "role"}, method = RequestMethod.GET)
    public String addRoleto(Model model, @RequestParam("id") String id, @RequestParam("role") String roleName) {

        Optional<Person> person = repository.findById(Long.valueOf(id));
        Role role = repositoryRole.findRoleByRol(roleName);

        person.get().removeRole(role);

        return "redirect:/personList";
    }
}

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MyClass myClass = new MyClass();
        String button = request.getParameter("button");

        if ("button1".equals(button)) {
            myClass.method1();
        }

        request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
    }*/
 /*RoleContainer = RoleForm.getElementByRole('Role');
        currentPerson = personSelObj.value;
        if(Person == "") {
            modelContainer.style.display = "none";
        } else {
            RoleSelObj = RoleForm.getElementByRole('Role');
        }*/
//    @RequestMapping(value = {"/addRolePers"}, method = RequestMethod.POST)
//    public String saveRolePer(Model model, //
//                              @ModelAttribute("personForm") PersonForm personForm,
//                              @ModelAttribute("roleForm")RoleForm roleForm) {
//
//        protected void butaddPer(HttpServletRequest request)
//            throws ServletException, IOException {
//
//            String button = request.getParameter("button");
//
//            if ("set Person".equals(button)) {
//                Person person = repository.findByFirstNameAndLastName(
//                        personForm.getFirstName(),
//                        personForm.getLastName());
//
//            }
//
//            return "redirect:/addRolePers";
//        }
//    }