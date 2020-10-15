package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int series;

    @Column
    private String model;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car() { }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "series: " + this.series + ", model: " + this.model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
