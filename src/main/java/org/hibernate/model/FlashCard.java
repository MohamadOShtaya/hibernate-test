package org.hibernate.model;




import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "flashcard")
@Entity(name = "flashcard")
public class FlashCard {
    @Id
    @Column(name = "flashCardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "flashCardName")
    private String name;
    @OneToMany(mappedBy = "flash",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Question> qList = new ArrayList<>();
    @Column(name = "Url")
    String url;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;


    public FlashCard() {

    }
    public FlashCard(int id, String name, List<Question> qList, String url) {

        this.id = id;
        this.name = name;
        this.qList=qList;
        this.url=url;
    }

    public FlashCard(int id, String name, List<Question> qList) {

        this.id = id;
        this.name = name;
        this.qList=qList;
    }
    public FlashCard(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public String getName() {
        return name;
    }
    public List<Question> getqList() {
        return qList;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void  setqList(List<Question> qList ){
        this.qList=qList;
    }


    @Override
    public String toString() {
        return "flahcard [id=" + id + ", name=" + name + "]"+"question List"+qList+"Url"+url+"]";

    }
}

