package pl.agh.kis.soa.catering.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Subscriptions")
@Access(AccessType.FIELD)
public class Subscription extends AbstractModel {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "frequency")
    private String frequency;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne
    private User user;

}
