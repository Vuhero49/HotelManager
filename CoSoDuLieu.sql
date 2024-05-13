use CoSoDuLieu;

create table HoaDon(
	MaHoaDon INT PRIMARY KEY,
    NgayHoaDon DATE,
    TenKhachHang NVARCHAR(50),
    MaPhong NVARCHAR(50),
    DonGia DECIMAL,
    SoNgayThue INT,
	SoGioThue INT,
);

drop table HoaDon;

