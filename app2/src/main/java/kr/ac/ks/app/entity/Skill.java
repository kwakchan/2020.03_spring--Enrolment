package kr.ac.ks.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer grade;
    
    // 생성자 생성
    public Skill() {
    }
    
    public Skill(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
    }
    
    // getter, setter 생성
    public Long getId(){ return id; }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}

