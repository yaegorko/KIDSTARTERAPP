package com.javamentor.kidstarter.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javamentor.kidstarter.model.Job;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "mentors")
public class Mentor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id")
    private Long id;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinTable(name = "job_to_mentor",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id") )
    private Set<Job> job;

    @Column (name = "experience", nullable = false)
    private Integer experience;

    @OneToOne
    @JoinColumn(name = "user_fk")
    private User user;

//    @OneToMany (cascade = CascadeType.ALL)
//    @JoinColumn (name = "comment", foreignKey = @ForeignKey(name = "mentor_comment_fk"))
//    private Set<Comment> comment;
}