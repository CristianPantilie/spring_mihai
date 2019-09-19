package ro.teamnet.zthbo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CITY")
public class City {

    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

}
