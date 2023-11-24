package map.project.demo.Domain;

import map.project.demo.Strategy.Screening;

public class Ticket {
    private String id;
    private Screening screening;
    private float price;
    private int seatNumber;
    private Spectator spectator;

    public Ticket(String id, Screening screening, float price, int seatNumber, Spectator spectator) {
        this.id = id;
        this.screening = screening;
        this.price = price;
        this.seatNumber = seatNumber;
        this.spectator = spectator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Spectator getSpectator() {
        return spectator;
    }

    public void setSpectator(Spectator spectator) {
        this.spectator = spectator;
    }

    public void addDiscount() {
        this.price = screening.applyDiscount(this.price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", screening=" + screening +
                ", price=" + price +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
