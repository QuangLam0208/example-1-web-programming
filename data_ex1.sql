
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

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[User]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[User] (
        [id] INT IDENTITY(1,1) PRIMARY KEY,
        [username] NVARCHAR(50) NOT NULL UNIQUE,
        [email] NVARCHAR(50) NOT NULL UNIQUE,
        [password] NVARCHAR(255) NOT NULL
    );
    PRINT 'Da tao bang User (voi ID tu tang)';
END
GO

INSERT INTO [dbo].[GiaoVien] ([name], [address])
VALUES
    (N'Nguyễn Hữu Trung', N'TP. Hồ Chí Minh'),
    (N'Âu Cơ', N'Đắk Lắk'),
	(N'Lạc Long Quân', N'Khánh Hòa')
GO

INSERT INTO [dbo].[User] ([username], [email], [password])
VALUES
    ('lamlq', 'lamlq28@gmail.com', '32632005'),
    ('trungnh', 'trungnh@gmail.com', '123')
GO

-- KIỂM TRA
PRINT N'--- Dữ liệu trong bảng GiaoVien ---';
SELECT * FROM [dbo].[GiaoVien];
GO

PRINT N'--- Dữ liệu trong bảng User ---';
SELECT * FROM [dbo].[User];
GO