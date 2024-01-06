package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import map.project.demo.Strategy.Screening;

@Getter
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    private String id;

    @Transient
    private Screening screening;

    @Column(name = "screeningid")
    private String screeningId;

    @Column(name = "price")
    private float price;

    @Column(name = "seatnumber")
    private int seatNumber;

    public Ticket() {

    }

    public Ticket(String id, Screening screening, float price, int seatNumber) {
        this.id = id;
        this.screening = screening;
        this.screeningId = screening.getId();
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScreeningId(String id) {
        this.screeningId = id;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void addDiscount() {
        this.price = screening.applyDiscount(this.price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
