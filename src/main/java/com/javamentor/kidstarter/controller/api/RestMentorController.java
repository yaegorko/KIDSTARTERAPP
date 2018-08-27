package com.javamentor.kidstarter.controller.api;

import com.javamentor.kidstarter.model.user.Mentor;
import com.javamentor.kidstarter.model.user.Role;
import com.javamentor.kidstarter.model.user.User;
import com.javamentor.kidstarter.service.interfaces.MentorService;
import com.javamentor.kidstarter.service.interfaces.RoleService;
import com.javamentor.kidstarter.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestMentorController {

    static final Logger logger = LoggerFactory.getLogger(RestMentorController.class);

    @Autowired
    private MentorService mentorService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("/mentor/{id}")
    public ResponseEntity<?> getMentorById(@PathVariable("id") long id, Model model) {
        model.addAttribute("id_model", id);
        Mentor mentor = mentorService.getMentorById(id);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }

    @GetMapping("/mentors")
    public ResponseEntity<List<Mentor>> listAllMentors() {
        List<Mentor> mentor = mentorService.getAllMentors();
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }

    @DeleteMapping("/mentor/{id}")
    public HttpStatus deleteMentorById(@PathVariable("id") long id) {
        mentorService.deleteMentorById(id);
        return HttpStatus.OK;
    }

    @PostMapping("/mentor")
    public ResponseEntity<?> addMentor(@RequestBody Mentor mentor) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role role = roleService.getByName("MENTOR");
        principal.getRoles().add(role);
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>((Collection<? extends SimpleGrantedAuthority>) principal.getAuthorities());
        userService.updateUserNoPasswordEncoder(principal);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        principal,
                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                        updatedAuthorities));
        mentor.setUser(principal);
        mentorService.addMentor(mentor);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }

    @PutMapping("/mentor")
    public ResponseEntity<?>  updateMentor(@ModelAttribute("mentor") Mentor mentor) {
        mentorService.updateMentor(mentor);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }
}
