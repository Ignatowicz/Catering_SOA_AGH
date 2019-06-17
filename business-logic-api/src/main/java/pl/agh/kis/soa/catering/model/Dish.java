package pl.agh.kis.soa.catering.model;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public Boolean getDishDay() {
        return dishDay;
    }

    @XmlTransient
    public Category getCategory() {
        return category;
    }

}
