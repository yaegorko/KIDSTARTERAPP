package com.javamentor.kidstarter.config.initializer;

import com.javamentor.kidstarter.model.Job;
import com.javamentor.kidstarter.model.Tag;
import com.javamentor.kidstarter.model.user.*;
import com.javamentor.kidstarter.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;
import com.javamentor.kidstarter.model.user.Role;
import com.javamentor.kidstarter.model.user.User;
import com.javamentor.kidstarter.service.interfaces.JobService;
import com.javamentor.kidstarter.service.interfaces.RoleService;
import com.javamentor.kidstarter.service.interfaces.TagService;
import com.javamentor.kidstarter.service.interfaces.UserService;
import com.javamentor.kidstarter.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class DataInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private JobService jobService;

    @Autowired
    private RoleService roleService;

    @Autowired
	private AccountService accountService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private KidService kidService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MentorService mentorService;

    public void init(){

        Role role0 = new Role("USER");
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("TEACHER");
        Role role3 = new Role("SPONSOR");
        Role role4 = new Role("OWNER");
        Role role5 = new Role("MENTOR");
        Role role6 = new Role("MODERATOR");
        Role role7 = new Role("KID");

        roleService.addRole(role0);
        roleService.addRole(role1);
        roleService.addRole(role2);
        roleService.addRole(role3);
        roleService.addRole(role4);
        roleService.addRole(role5);
        roleService.addRole(role6);
        roleService.addRole(role7);

        Role roleUser = roleService.getByName("USER");
        Role roleAdmin = roleService.getByName("ADMIN");
        Role roleTeacher = roleService.getByName("TEACHER");
        Role roleSponsor = roleService.getByName("SPONSOR");
        Role roleOwner = roleService.getByName("OWNER");
        Role roleMentor = roleService.getByName("MENTOR");
        Role roleModerator = roleService.getByName("MODERATOR");
        Role roleKid = roleService.getByName("KID");

        List<Role> roles = new ArrayList<>();
        Collections.addAll(roles, roleAdmin, roleTeacher, roleSponsor, roleOwner,
                roleMentor, roleModerator, roleKid, roleUser);

        List<Role> kidRoles = new ArrayList<>(Arrays.asList(roleUser, roleKid, roleSponsor));
        List<Role> teacherRoles = new ArrayList<>(Arrays.asList(roleUser, roleTeacher, roleSponsor));
        List<Role> mentorRoles = new ArrayList<>(Arrays.asList(roleUser, roleMentor, roleSponsor));

        User user1  = new User("Ivan","Ivanov","Ivanovich","qwer","12345",
                roles, 23, "MALE",23-12-34,"admin@mail.ru","RUSSIA","house 8");


	    User user2  = new User("Vovan","Vovanov","Huevich","12434","qwer12",
			    roles,28, "MALE",23-12-34,"user@mail.ru","Ukraine","house 15");

        User kidUser  = new User("Kid","Kid","Kid","12334","qwer",
                kidRoles,28, "MALE",23-12-34,"Kid@mail.ru","RUSSIA","house 15");

        User teacherUser  = new User("Teacher","Teacher","Teacher","11234","qwer",
                teacherRoles,28, "MALE",23-12-34,"Teacher@mail.ru","RUSSIA","house 15");

        User mentorUser  = new User("Mentor","Mentor","Mentor","12364","qwer",
                mentorRoles,28, "MALE",23-12-34,"Mentor@mail.ru","RUSSIA","house 15");


      user1 = userService.addUser(user1);
      user2 = userService.addUser(user2);
      kidUser = userService.addUser(kidUser);
      teacherUser = userService.addUser(teacherUser);
      mentorUser = userService.addUser(mentorUser);

	    Tag tag1 = tagService.addTag(new Tag("Программирование", new HashSet<>()));
	    Tag tag2 = tagService.addTag(new Tag("Фронтенд", new HashSet<>()));
	    Tag tag3 = tagService.addTag(new Tag("Бэкаенд", new HashSet<>()));

	    Account acc1 = new Account();
	    Account acc2 = new Account();
	    Account acc3 = new Account();
	    Account acc4 = new Account();

	    acc1 = accountService.addAccount(acc1);
	    acc2 = accountService.addAccount(acc2);
	    acc3 = accountService.addAccount(acc3);
	    acc4 = accountService.addAccount(acc4);

        Organization org1 = new Organization("Name1","adress1","Мавродия", "mak@l2ff.ru", 8944,LocalDateTime.now(), acc1);
        Organization org2 = new Organization("Name2","adress2","Мавродия", "mak@lf4f.ru", 8944,LocalDateTime.now(), acc2);
        Organization org3 = new Organization("Name3","adress3","Мавродия", "mak@lf5f.ru", 8944,LocalDateTime.now(), acc3);
        Organization org4 = new Organization("Name4","adress4","Мавродия", "mak@lf6f.ru", 8944,LocalDateTime.now(), acc4);

        org1 = organizationService.addOrganization(org1);
        org2 = organizationService.addOrganization(org2);
        org3 = organizationService.addOrganization(org3);
        org4 = organizationService.addOrganization(org4);

	    Owner owner1 = new Owner(null, user1);
	    Owner owner2 = new Owner(null, user2);

	    ownerService.addOwner(owner1);
	    ownerService.addOwner(owner2);


//	    Job job1 = jobService.addJob(new Job("Java", "Топовый язык", new HashSet<>(), new HashSet<>(Collections.singletonList(user1))));
//	    Job job2 = jobService.addJob(new Job("JavaScript", "Какашка", new HashSet<>(), new HashSet<>(Collections.singletonList(user2))));
//
//
//        tag1.setJobs(new HashSet<>(Arrays.asList(job1, job2)));
//	    tag2.setJobs(new HashSet<>(Arrays.asList(job2)));
//	    tag3.setJobs(new HashSet<>(Arrays.asList(job1)));
//
//        tagService.updateTag(tag1);
//        tagService.updateTag(tag2);
//        tagService.updateTag(tag3);
	    Job job1 = jobService.addJob(new Job("Java", "Топовый язык", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()));
	    Job job2 = jobService.addJob(new Job("JavaScript", "Какашка", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()));

        Kid kid1 = kidService.addKid(new Kid(kidUser, new HashSet<>(Collections.singletonList(job1))));

        Teacher teacher1 = teacherService.addTeacher(new Teacher(teacherUser, new HashSet<>(Collections.singletonList(job1))));

        Mentor mentor1 = mentorService.addMentor(new Mentor(mentorUser, new HashSet<>(Collections.singletonList(job2)), 3, "Description"));

        job1.setTags(new HashSet<>(Arrays.asList(tag1, tag3)));
        job2.setTags(new HashSet<>(Arrays.asList(tag1, tag2)));
	    tag1.setJobs(new HashSet<>(Arrays.asList(job1, job2)));
	    tag2.setJobs(new HashSet<>(Arrays.asList(job2)));
	    tag3.setJobs(new HashSet<>(Arrays.asList(job1)));

        jobService.updateJob(job1);
        jobService.updateJob(job2);
    }
}
