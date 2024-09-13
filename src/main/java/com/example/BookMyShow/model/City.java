package com.example.BookMyShow.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "cities")
public class City extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Theater> theaters;
}
