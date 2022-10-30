package org.hibernate.model;




import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "subjects")
@Table(name = "subjects")
public class subjects {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "subject_name")
    private String name;
    @Column(name = "subject_url")
    private String url;
    @Column(name = "subject_flashCardList")
    private List<flashCard> flashCards= new ArrayList<>();
    public subjects() {

    }
    public subjects(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public subjects(int id, String name, ArrayList<flashCard> flashCards, String url) {
        super();
        this.id = id;
        this.name = name;
        this.flashCards=flashCards;
        this.url=url;
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<flashCard> getFlashCards(){
        return flashCards;
    }
    public void setFlashCards(List<flashCard> flashCards){
        this.flashCards=flashCards;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url=url;
    }

    @Override
    public String toString() {
        return "subjects [id=" + id + ", name=" + name +",flashCard"+flashCards+ ",url"+url + "]";

    }

}
