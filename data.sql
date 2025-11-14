-- Tạo DB nếu chưa tồn tại
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'ltwebct4st6')
BEGIN
    CREATE DATABASE [ltwebct4st6];
END
GO


USE [ltwebct4st6];
GO


IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GiaoVien]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[GiaoVien] (
        [id] INT IDENTITY(1,1) PRIMARY KEY,
        [name] NVARCHAR(50) NOT NULL,
        [address] NVARCHAR(100)
    );
    PRINT 'Da tao bang GiaoVien (voi ID tu tang)';
END
GO



IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[User]') AND type in (N'U'))
BEGIN
    DROP TABLE [dbo].[User];
    PRINT 'Da xoa bang User cu';
END
GO


CREATE TABLE [dbo].[User] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [email] NVARCHAR(50) NOT NULL UNIQUE,
    [username] NVARCHAR(50) NOT NULL UNIQUE,
    [fullname] NVARCHAR(100) NULL, 
    [password] NVARCHAR(255) NOT NULL,
    [avatar] NVARCHAR(255) NULL,
    [roleid] NVARCHAR(20) NULL, 
    [phone] NVARCHAR(20) NULL,
    [createddate] DATETIME NULL DEFAULT GETDATE() -- Tự động lấy ngày giờ
);
PRINT 'Da tao bang User (voi cau truc moi bao gom fullname)';
GO


INSERT INTO [dbo].[GiaoVien] ([name], [address])
VALUES
    (N'Nguyễn Hữu Trung', N'TP. Hồ Chí Minh'),
    (N'Âu Cơ', N'Đắk Lắk'),
	(N'Lạc Long Quân', N'Khánh Hòa')
GO


INSERT INTO [dbo].[User] 
    ([email], [username], [fullname], [password], [roleid], [phone])
VALUES
    -- (Bỏ qua 'id' và 'createddate' vì chúng tự động)
    ('lamlq28@gmail.com', 'lamlq', N'Lương Quang Lâm', '32632005', '1', '0909123456'),
    ('trungnh@gmail.com', 'trungnh', N'Nguyễn Hữu Trung', '123', '2', '0908111222')
GO

-- KIỂM TRA
PRINT N'--- Dữ liệu trong bảng GiaoVien ---';
SELECT * FROM [dbo].[GiaoVien];
GO

PRINT N'--- Dữ liệu trong bảng User ---';
SELECT * FROM [dbo].[User];
GO