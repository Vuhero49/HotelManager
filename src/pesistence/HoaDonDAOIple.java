package pesistence;

import java.util.List;

import domain.model.HoaDonTheoGio;
import domain.model.HoaDonTheoNgay;

public class HoaDonDAOIple implements HoaDonDAO {
    private HoaDonGateway hoaDonGateway;

    public HoaDonDAOIple(HoaDonGateway hoaDonGateway) {
        this.hoaDonGateway = hoaDonGateway;
    }

    @Override
    public void addHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio) {
        hoaDonGateway.addHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
    }

    @Override
    public void editHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio) {
        hoaDonGateway.editHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
    }

    @Override
    public void deleteHoaDon(int id) {
        hoaDonGateway.deleteHoaDon(id);
    }

    @Override
    public HoaDonTheoGio getGioById(int id) {
        return hoaDonGateway.getGioById(id);
    }

    @Override
    public HoaDonTheoNgay getNgayById(int id) {
        return hoaDonGateway.getNgayById(id);
    }

    @Override
    public int countIdRoom(String idRoom){
        return hoaDonGateway.countIdRoom(idRoom);
    }

    @Override
    public double avgByMonth(int month){
        return hoaDonGateway.avgByMonth(month);
    }

    @Override
    public void loadHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio){
        hoaDonGateway.loadHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
    }

    @Override
    public List<HoaDonTheoNgay> getHoaDonTheoNgay() {
        return hoaDonGateway.getHoaDonTheoNgay();
    }

    @Override
    public List<HoaDonTheoGio> getHoaDonTheoGio() {
        return hoaDonGateway.getHoaDonTheoGio();
    }
}