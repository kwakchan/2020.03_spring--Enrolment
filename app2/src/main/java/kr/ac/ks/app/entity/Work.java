package kr.ac.ks.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Work {
    @Id
    @GeneratedValue
    private Long id;
    private String position;
    private String company;
    private String time;
    private String desc;
    
    // 생성자 생성
    public Work(){
    }
    
    public Work(String position, String company, String time, String desc) {
        this.position = position;
        this.company = company;
        this.time = time;
        this.desc = desc;
    }

    // getter, setter 생성
    public Long getId(){ return id;}

    public void setId(Long id) { this.id = id;}


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
