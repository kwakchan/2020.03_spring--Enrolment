package kr.ac.ks.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Interests {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    // 생성자 생성
    public Interests(){
    }

    public Interests(String name) {
        this.name = name;
    }

    // getter,setter 생성
    public Long getId(){ return id;}

    public void setId(Long id) { this.id = id;}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
