package pesistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import domain.model.HoaDonTheoGio;
import domain.model.HoaDonTheoNgay;

public class HoaDonJdbcGateway implements HoaDonGateway {

private Connection connection;

    public HoaDonJdbcGateway() {
        // Initialize the database connection here (replace dbUrl, username, and password with your SQL Server credentials)
        String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=CSDL;encrypt=true;trustServerCertificate=true";
        String username = "vu";
        String password = "1234";
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
    }

    @Override
    public void addHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio) {
        String sql = "INSERT INTO HoaDon(MaHoaDon, NgayHoaDon, TenKhachHang, MaPhong, DonGia, SoNgayThue, SoGioThue) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hoaDonTheoGio.getId());
            statement.setString(2, hoaDonTheoGio.getDate());
            statement.setString(3, hoaDonTheoGio.getNameCustomer());
            statement.setString(4, hoaDonTheoGio.getRoomId());
            statement.setInt(5, hoaDonTheoGio.getUnitPrice());
            statement.setInt(6, hoaDonTheoNgay.getDay());
            statement.setInt(7, hoaDonTheoGio.getHour());
            statement.executeUpdate();
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Vui long nhap thong tin hop le!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void editHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio) {
        String sql = "UPDATE HoaDon SET NgayHoaDon = ?, TenKhachHang = ?, MaPhong = ?, DonGia = ?, SoNgayThue = ?, SoGioThue = ? WHERE MaHoaDon = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hoaDonTheoNgay.getDate());
            statement.setString(2, hoaDonTheoNgay.getNameCustomer());
            statement.setString(3, hoaDonTheoNgay.getRoomId());
            statement.setInt(4, hoaDonTheoNgay.getUnitPrice());
            statement.setInt(5, hoaDonTheoNgay.getDay());
            statement.setInt(6, hoaDonTheoGio.getHour());
            statement.setInt(7, hoaDonTheoNgay.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHoaDon(int id) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HoaDonTheoGio getGioById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int maHoaDon = resultSet.getInt("MaHoaDon");
                String date = resultSet.getString("NgayHoaDon");
                String nameCuStomer = resultSet.getString("TenKhachHang");
                String idroom = resultSet.getString("MaPhong");
                int unitprice = resultSet.getInt("DonGia");
                int day = resultSet.getInt("SoNgayThue");
                int hour = resultSet.getInt("SoGioThue");
                double price = 4.0;

                return new HoaDonTheoGio(maHoaDon, date, nameCuStomer, idroom, unitprice, day, hour, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HoaDonTheoNgay getNgayById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int maHoaDon = resultSet.getInt("MaHoaDon");
                String date = resultSet.getString("NgayHoaDon");
                String nameCuStomer = resultSet.getString("TenKhachHang");
                String idroom = resultSet.getString("MaPhong");
                int unitprice = resultSet.getInt("DonGia");
                int day = resultSet.getInt("SoNgayThue");
                int hour = resultSet.getInt("SoGioThue");
                double price = 4.0;

                return new HoaDonTheoNgay(maHoaDon, date, nameCuStomer, idroom, unitprice, day, hour, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countIdRoom(String idRoom){

        String sql = "SELECT COUNT(*) AS RoomCount FROM HoaDon WHERE LEFT(MaPhong, 1) = ?";

        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idRoom);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("RoomCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public double avgByMonth(int month) {
        String sql = "SELECT AVG(CASE WHEN SoGioThue = 0 THEN donGia * SoNgayThue ELSE donGia * SoGioThue END) AS average FROM HoaDon WHERE MONTH(NgayHoaDon) = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("average");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public void loadHoaDon(HoaDonTheoNgay hoaDonTheoNgay, HoaDonTheoGio hoaDonTheoGio){

    }

    @Override
    public List<HoaDonTheoNgay> getHoaDonTheoNgay() {
        List<HoaDonTheoNgay> hoaDons = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("MaHoaDon");
                String date = resultSet.getString("NgayHoaDon");
                String nameCustomer = resultSet.getString("TenKhachHang");
                String idroom = resultSet.getString("MaPhong");
                int unitprice = resultSet.getInt("DonGia");
                int day = resultSet.getInt("SoNgayThue");
                int hour = resultSet.getInt("SoGioThue");
                // Calculate the average mark using the formula provided
                double price = 4.0;

                hoaDons.add(new HoaDonTheoNgay(id, date, nameCustomer, idroom, unitprice, day, hour, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }

    @Override
    public List<HoaDonTheoGio> getHoaDonTheoGio() {
        List<HoaDonTheoGio> hoaDons = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("MaHoaDon");
                String date = resultSet.getString("NgayHoaDon");
                String nameCustomer = resultSet.getString("TenKhachHang");
                String idroom = resultSet.getString("MaPhong");
                int unitprice = resultSet.getInt("DonGia");
                int day = resultSet.getInt("SoNgayThue");
                int hour = resultSet.getInt("SoGioThue");
                // Calculate the average mark using the formula provided
                double price = 4.0;

                hoaDons.add(new HoaDonTheoGio(id, date, nameCustomer, idroom, unitprice, day, hour, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }
}