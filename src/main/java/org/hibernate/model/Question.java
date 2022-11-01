package org.hibernate.model;



import javax.persistence.*;


@Entity(name = "question")
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId ;
    @Column(name = "questionName")
    private String questionName;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private FlashCard flash;


    public Question(int id, String name) {
        this.questionId = id;
        this.questionName = name;
    }

    public Question() {

    }

    public Question(String questionName, FlashCard flash) {
        this.questionName = questionName;
        this.flash = flash;
    }

    public int getId() {
        return questionId;
    }

    public void setId(int id) {
        this.questionId = id;
    }

    @Column(name = "questionName")
    public String getName() {
        return questionName;
    }

    public void setName(String name) {
        this.questionName = name;
    }

    @Override
    public String toString() {
        return "questions [id=" + questionId + ", name=" + questionName + "]";

    }
}
