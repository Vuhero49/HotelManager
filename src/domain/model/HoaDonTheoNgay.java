package domain.model;

public class HoaDonTheoNgay extends HoaDon{
    private int day;

    public HoaDonTheoNgay(){}
 
    public HoaDonTheoNgay(int id, String date, String nameCustomer, String roomId, int unitPrice, int day, int hour,
            double price) {
        super(id, date, nameCustomer, roomId, unitPrice, day, hour, price);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public double thanhTien() {
        double s = this.day * this.unitPrice;

        if(this.day > 7)
            s -=  (this.day - 7 ) * this.unitPrice * 0.2 ;

        return s;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Name: " + nameCustomer + ", Id room: " + roomId
            + ", unit price: " + unitPrice + ", Day: " + day + thanhTien();
    }

    public static int size() {
        return 0;
    }
    
}
