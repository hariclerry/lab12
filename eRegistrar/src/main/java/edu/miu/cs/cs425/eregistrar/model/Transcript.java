package edu.miu.cs.cs425.eregistrar.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Transcript {
    @Id
    @GeneratedValue
    private Long transcriptId;
    private String degreeTitle;

    public Transcript(String degreeTitle) {
        this.degreeTitle  = degreeTitle;
    }

    public Transcript() {

    }

}

