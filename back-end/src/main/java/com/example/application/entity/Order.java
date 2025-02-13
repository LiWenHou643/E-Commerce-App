package com.example.application.entity;

import com.example.application.constants.OrderStatus;
import com.example.application.constants.ShipmentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    double subtotal;

    double total;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    LocalDateTime shippingDate;

    LocalDateTime deliveryDate;

    LocalDateTime cancelDate;

    String shippingAddress;

    @Enumerated(EnumType.STRING)
    ShipmentMethod shippingMethod;

    double shippingCost;

    String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderDetail> orderDetails;

}
