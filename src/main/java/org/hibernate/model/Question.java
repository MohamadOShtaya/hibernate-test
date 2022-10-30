package org.hibernate.model;

import jakarta.persistence.*;

@Entity(name = "Question")
@Table(name = "Question")
public class Question {
    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "questionName")
    private String name;


    @OneToMany(mappedBy = "flashCard")
    private int subjectId;
    public Question(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "questionName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "questions [id=" + id + ", name=" + name + "]";

    }
}
