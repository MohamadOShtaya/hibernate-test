package org.hibernate.model;



import javax.persistence.*;


@Entity(name = "question")
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "questionName")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private FlashCard flash;


    public Question(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Question() {

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
