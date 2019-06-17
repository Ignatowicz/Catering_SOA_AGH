package pl.agh.kis.soa.catering.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@XmlTransient
@Table(name = "Dishes")
@Access(AccessType.FIELD)
public class Dish extends AbstractModel {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "day_dish")
    private Boolean dishDay;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

}
