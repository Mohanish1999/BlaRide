package com.blarides.controller;


import com.blarides.domain.entity.DriverAccount;
import com.blarides.domain.entity.RiderAccount;
import com.blarides.domain.entity.User;
import com.blarides.domain.security.UserRole;
import com.blarides.repository.RoleRepository;
import com.blarides.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private UserService userService;

    private RoleRepository roleRepository;

    @Autowired
    public HomeController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/home";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleRepository.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }

    @RequestMapping("/home")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        DriverAccount driverAccount = user.getDriverAccount();
        RiderAccount riderAccount = user.getRiderAccount();

        model.addAttribute("driverAccount", driverAccount);
        model.addAttribute("riderAccount", riderAccount);
        model.addAttribute("user", user);

        return "userFront";
    }

    @RequestMapping("/timeline")
    public String timeline(Principal principal, Model model) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        return "timeline";
    }
}
