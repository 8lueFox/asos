package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Types type;

    public Role(){
        type = Types.Student;
    }

    public Role(Types type){
        this.type = type;
    }

    public enum Types{
        Student,
        Pracownik,
        Pracownik_Dziekanatu
    }
}
