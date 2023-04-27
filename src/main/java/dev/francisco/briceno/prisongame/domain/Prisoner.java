package dev.francisco.briceno.prisongame.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "prisioner")
@Entity
public class Prisoner implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "mazze")
    private String mazze;

    @Column(name = "can_scape")
    private Boolean canScape;
}
