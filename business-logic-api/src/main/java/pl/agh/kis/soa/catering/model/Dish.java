package pl.agh.kis.soa.catering.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
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
