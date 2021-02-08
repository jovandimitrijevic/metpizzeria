package rs.ac.metropolitan.it355.pz.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int id;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    private User users;

    @Column(name = "ITEMS")
    private String name;

    @Column(name = "SUM")
    private float sum;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name = "STATUS_ID")
    private OrderStatus orderStatus;


    public Order() {
    }

    public Order(User users, String name, float sum, OrderStatus orderStatus) {
        this.users = users;
        this.name = name;
        this.sum = sum;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", users=" + users +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                ", orderStatus=" + orderStatus +
                '}';
    }
}