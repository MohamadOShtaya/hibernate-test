package org.hibernate.model;



import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Table(name = "flashcard")
@Entity(name = "flashcard")
public class flashCard {
    @Id
    @Column(name = "flashCard_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "flashCard_name")
    private String name;
    @Column(name = "flashCard_questionList")
    private List<Question> qList = new ArrayList<>();
    @Column(name = "Url")
    String url;
    //@OneToMany(mappedBy = "subjects")
    private int subjectId;



    public flashCard() {

    }
    public flashCard(int id, String name,List<Question> qList,String url) {

        this.id = id;
        this.name = name;
        this.qList=qList;
        this.url=url;
    }

    public flashCard(int id, String name,List<Question> qList) {

        this.id = id;
        this.name = name;
        this.qList=qList;
    }
    public flashCard(int id, String name) {

        this.id = id;
        this.name = name;
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

