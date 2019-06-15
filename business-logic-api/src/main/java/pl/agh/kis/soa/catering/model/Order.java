package pl.agh.kis.soa.catering.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.agh.kis.soa.catering.utils.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Orders")
@Access(AccessType.FIELD)
public class Order extends AbstractModel {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private Float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status = OrderStatus.ORDERED;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne
    private User user;

}
