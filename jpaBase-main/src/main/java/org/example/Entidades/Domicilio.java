package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "domicilio")
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_calle")
    private String nombreCalle;
    @Column(name = "numero")
    private int numero;
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;
}
