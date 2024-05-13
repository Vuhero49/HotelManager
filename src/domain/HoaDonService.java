package domain;

import java.util.List;

import domain.model.HoaDonTheoGio;
import domain.model.HoaDonTheoNgay;

public interface HoaDonService {
    void addHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio);
    void editHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio);
    void deleteHoaDon(int id);
    HoaDonTheoGio getGioById(int id);
    HoaDonTheoNgay getNgayById(int id);
    int countIdRoom(String idRoom);
    double avgByMonth(int month);
    void loadHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio);
    List<HoaDonTheoNgay> getHoaDonTheoNgay();
    List<HoaDonTheoGio> getHoaDonTheoGio();
}
