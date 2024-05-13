package domain.model;

public class HoaDonTheoGio extends HoaDon{
    private int hour;

    public HoaDonTheoGio(){}

    public HoaDonTheoGio(int id, String date, String nameCustomer, String roomId, int unitPrice, int day, int hour,
            double price) {
        super(id, date, nameCustomer, roomId, unitPrice, day, hour, price);
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    private int tinhHour(){
        int time = this.hour;

        if(this.hour > 24 && this.hour < 30)
            time = 24;  
        return time;
    }

    @Override
    public double thanhTien() {
        return tinhHour() * this.unitPrice;
    }   

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Name: " + nameCustomer + ", Id room: " + roomId
            + ", unit price: " + unitPrice + ", Hour: " + hour + thanhTien();
    }
}
