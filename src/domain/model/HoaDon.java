package domain.model;

public abstract class HoaDon {
    protected int id;
    protected String date;
    protected String nameCustomer;
    protected String roomId;
    protected int unitPrice;

    public HoaDon(){}

    public HoaDon(int id, String date, String nameCustomer, String roomId, int unitPrice, int day, 
    int hour, double price) {
        this.id = id;
        this.date = date;
        this.nameCustomer = nameCustomer;
        this.roomId = roomId;
        this.unitPrice = unitPrice;
    }

    public abstract double thanhTien();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Name: " + nameCustomer + ", Id room: " + roomId
            + ", unit price: " + unitPrice + thanhTien();
    }
}
