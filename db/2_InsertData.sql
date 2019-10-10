set dateformat dmy

insert KhachHang
values ('KH1', N'Nguyễn Quang Huy', N'Việt Nam', '264538568', '6/7/1998', 'Nam', N'KTX Đại Học Công Nghiệp', '0908371510'),
	   ('KH2', N'Đỗ Minh Hậu', N'Việt Nam', '268479221', '1/4/1998', 'Nam', N'KTX Đại Học Công Nghiệp', '0147474747'),
	   ('KH3', N'Phạm Minh Chánh', N'Việt Nam', '211789354', '8/3/1998', 'Nam', N'Trọ Gò Vấp', '0144789621')

select * from KhachHang

insert NhanVien
values ('NV1', N'Lâm Tùng Trang', '255784113', '25/5/1978', N'Nữ', N'Hà Nội', '0123588741', '123456', '111111'),
	   ('NV2', N'Nguyễn Thị Lan Dương', '233877146', '12/7/1985', N'Nữ', N'Cao Bằng', '0155812345', '234567', '222222'),
	   ('NV3', N'Chân Chấn Chu', '277892011', '4/8/2000', N'Nam', N'Bắc Giang', '0199899799', '345678', '333333')

select * from NhanVien

-- Đăng nhập
select * from NhanVien where MaDangNhap = '123456' and MatKhau = '111111'

-- Thông tin đăng kí
INSERT [dbo].[ThongTinDangKy] ([MaDangKy], [MaKH], [SoLuongPhong], [NgayDen], [NgayDi]) VALUES (N'DK1', N'KH1', 1, CAST(N'2019-04-28' AS Date), CAST(N'2019-04-30' AS Date))
INSERT [dbo].[ThongTinDangKy] ([MaDangKy], [MaKH], [SoLuongPhong], [NgayDen], [NgayDi]) VALUES (N'DK2', N'KH3', 2, CAST(N'2019-04-30' AS Date), CAST(N'2019-05-05' AS Date))
INSERT [dbo].[ThongTinDangKy] ([MaDangKy], [MaKH], [SoLuongPhong], [NgayDen], [NgayDi]) VALUES (N'DK3', N'KH2', 1, CAST(N'2019-04-20' AS Date), CAST(N'2019-05-10' AS Date))

select * from ThongTinDangKy

-- Phòng
insert Phong
values  ('P1', N'VIP', 500000, N'Trống'),
		('P2', N'Thường', 500000, N'Trống'),
		('P3', N'VIP', 500000, N'Trống'),
		('P4', N'Thường', 500000, N'Trống'),
		('P5', N'Thường', 500000, N'Trống')

select * from Phong

-- Thông tin nhận phòng
INSERT [dbo].[ThongTinNhan] ([MaNhanPhong], [MaDangKy], [NgayNhan], [GioNhan]) VALUES (N'NP1', N'DK1', CAST(N'2019-04-28' AS Date), CAST(N'03:00:00' AS Time))
INSERT [dbo].[ThongTinNhan] ([MaNhanPhong], [MaDangKy], [NgayNhan], [GioNhan]) VALUES (N'NP2', N'DK2', CAST(N'2019-05-21' AS Date), CAST(N'04:00:00' AS Time))
INSERT [dbo].[ThongTinNhan] ([MaNhanPhong], [MaDangKy], [NgayNhan], [GioNhan]) VALUES (N'NP3', N'DK3', CAST(N'2019-06-20' AS Date), CAST(N'02:00:00' AS Time))
INSERT [dbo].[ThongTinNhan] ([MaNhanPhong], [MaDangKy], [NgayNhan], [GioNhan]) VALUES (N'NP4', N'DK3', CAST(N'2019-06-20' AS Date), CAST(N'02:01:00' AS Time))
INSERT [dbo].[ThongTinNhan] ([MaNhanPhong], [MaDangKy], [NgayNhan], [GioNhan]) VALUES (N'NP9', N'DK3', CAST(N'2018-03-28' AS Date), CAST(N'04:00:00' AS Time))

select * from ThongTinNhan

-- Chi tiết nhận phòng
INSERT [dbo].[ChiTietNhanPhong] ([MaNhanPhong], [MaPhong]) VALUES (N'NP1', N'P5')
INSERT [dbo].[ChiTietNhanPhong] ([MaNhanPhong], [MaPhong]) VALUES (N'NP2', N'P3')
INSERT [dbo].[ChiTietNhanPhong] ([MaNhanPhong], [MaPhong]) VALUES (N'NP3', N'P1')
INSERT [dbo].[ChiTietNhanPhong] ([MaNhanPhong], [MaPhong]) VALUES (N'NP4', N'P2')

select * from ChiTietNhanPhong

-- Thông tin trả phòng
INSERT [dbo].[ThongTinTraPhong] ([MaTraPhong], [MaNhanPhong], [NgayTra], [GioTra]) VALUES (N'TP1', N'NP1', CAST(N'2019-04-29' AS Date), CAST(N'15:30:00' AS Time))
INSERT [dbo].[ThongTinTraPhong] ([MaTraPhong], [MaNhanPhong], [NgayTra], [GioTra]) VALUES (N'TP2', N'NP2', CAST(N'2019-05-30' AS Date), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ThongTinTraPhong] ([MaTraPhong], [MaNhanPhong], [NgayTra], [GioTra]) VALUES (N'TP3', N'NP3', CAST(N'2019-06-12' AS Date), CAST(N'12:00:00' AS Time))

select * from ThongTinTraPhong

-- Dịch vụ
INSERT DichVu 
VALUES  (N'DV1', N'Matxa', N'Giờ', 500000.0000),
		(N'DV2', N'Xông Hơi', N'Giờ', 200000.0000),
		(N'DV3', N'Giữ xe', N'Tiếng', 3000.0000),
		(N'DV4', N'Bữa trưa (thường)', N'Phần', 50000.0000),
		(N'DV5', N'Bữa trưa (VIP)', N'Phần', 500000.0000),
		(N'DV6', N'Giặt ủi', N'Kg', 80000.0000)

-- Hoá đơn
INSERT [dbo].[HoaDon] ([MaHoaDon], [MaNV], [MaTraPhong], [NgayLapHoaDon], [TongHoaDon]) VALUES (N'HD1', N'NV1', N'TP1', CAST(N'2019-04-02' AS Date), 2000000.0000)
INSERT [dbo].[HoaDon] ([MaHoaDon], [MaNV], [MaTraPhong], [NgayLapHoaDon], [TongHoaDon]) VALUES (N'HD2', N'NV2', N'TP3', CAST(N'2019-04-01' AS Date), 5000000.0000)
INSERT [dbo].[HoaDon] ([MaHoaDon], [MaNV], [MaTraPhong], [NgayLapHoaDon], [TongHoaDon]) VALUES (N'HD3', N'NV3', N'TP2', CAST(N'2019-06-02' AS Date), 1000000.0000)

-- Chi tiết hoá đơn
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD1', N'DV1', 2, 1000000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD1', N'DV7', 5, 1500000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD2', N'DV2', 8, 5000000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD2', N'DV5', 8, 5000000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD2', N'DV7', 8, 5000000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD3', N'DV4', 5, 250000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD3', N'DV5', 10, 5000000.0000)
INSERT [dbo].[ChiTietHoaDon] ([MaHoaDon], [MaDichVu], [SoLuong], [TongTien]) VALUES (N'HD3', N'DV7', 1, 300000.0000)