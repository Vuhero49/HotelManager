package domain;

import java.util.List;

import domain.model.HoaDonTheoGio;
import domain.model.HoaDonTheoNgay;
import pesistence.*;

public class HoaDonServiceIple implements HoaDonService {
    private HoaDonDAO hoaDonDAO;

    public HoaDonServiceIple(   ) {
        // Initialize the StudentDAO (Data Access Layer)
        hoaDonDAO = new HoaDonDAOIple(new HoaDonJdbcGateway());
    }

    @Override
    public void addHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio) {
        hoaDonDAO.addHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
    }

    @Override
    public void editHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio) {
        hoaDonDAO.editHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
    }

    @Override
    public void deleteHoaDon(int id) {
        hoaDonDAO.deleteHoaDon(id);
    }

    @Override
    public HoaDonTheoGio getGioById(int id) {
        return hoaDonDAO.getGioById(id);
    }

    @Override
    public HoaDonTheoNgay getNgayById(int id) {
        return hoaDonDAO.getNgayById(id);
    }

    @Override
    public int countIdRoom(String idRoom){
        return hoaDonDAO.countIdRoom(idRoom);
    }

    @Override
    public double avgByMonth(int month){
        return hoaDonDAO.avgByMonth(month);
    }

    @Override
    public void loadHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio){
        hoaDonDAO.loadHoaDon(hoaDonTheoNgay, hoaDonTheoGio);
    }

    @Override
    public List<HoaDonTheoNgay> getHoaDonTheoNgay() {
        return hoaDonDAO.getHoaDonTheoNgay();
    }

    @Override
    public List<HoaDonTheoGio> getHoaDonTheoGio() {
        return hoaDonDAO.getHoaDonTheoGio();
    }
}
