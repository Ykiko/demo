package com.example.demo.person;

public class PersonForm {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Long id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

  /*  @GetMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            Iterable<Role> roles = rolesRepository.findAll();
            model.addAttribute("roles", roles);
        } else {
            return "404";
        }
        return "editUser";
    }*/