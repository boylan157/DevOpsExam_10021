package no.kristiania.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ninjas")
public class Ninja implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "katana", nullable = false)
    private String katana;

    public Ninja(){}

    public Ninja(String name, String katana){
        this.name = name;
        this.katana = katana;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getKatana() {
        return katana;
    }
    public void setKatana(String katana) {
        this.katana = katana;
    }
}
