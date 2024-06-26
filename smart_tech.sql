-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 08, 2023 lúc 02:45 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `smart_tech`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_cart`
--

CREATE TABLE `_cart` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `product_detail_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_category`
--

CREATE TABLE `_category` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_category`
--

INSERT INTO `_category` (`id`, `active`, `code`, `created_date`, `name`, `parent_id`) VALUES
(10, b'1', 'PHONE', '2023-10-12 01:12:56', 'Điện thoại nổi bật', NULL),
(11, b'1', 'P_APPLE', '2023-10-12 01:12:56', 'Apple', 10),
(12, b'1', 'P_SS', '2023-10-12 01:12:56', 'Samsung', 10),
(13, b'1', 'P_XIAO', '2023-10-12 01:12:56', 'Xiaomi', 10),
(14, b'1', 'P_OPPO', '2023-10-12 01:12:56', 'OPPO', 10),
(15, b'1', 'P_VIVO', '2023-10-12 01:12:56', 'Vivo', 10),
(16, b'1', 'P_REALME', '2023-10-12 01:12:56', 'Realme', 10),
(17, b'1', 'LAPTOP', '2023-10-12 01:12:56', 'Laptop', NULL),
(18, b'1', 'L_MAC', '2023-10-12 01:12:56', 'Macbook', 17),
(19, b'1', 'L_THINKPAD', '2023-10-12 01:12:56', 'Thinkpad', 17),
(20, b'1', 'L_ASUS', '2023-10-12 01:12:56', 'Asus', 17),
(21, b'1', 'L_HP', '2023-10-12 01:12:56', 'Hp', 17),
(22, b'1', 'L_DELL', '2023-10-12 01:12:56', 'Dell', 17),
(23, b'1', 'L_SURF', '2023-10-12 01:12:56', 'Surface', 17),
(24, b'1', 'SCREEN', '2023-10-12 01:12:56', 'Màn hình', NULL),
(29, b'1', 'SC_ASUS', '2023-10-12 01:12:56', 'Asus', 24),
(30, b'1', 'SC_DELL', '2023-10-12 01:12:56', 'Dell', 24),
(31, b'1', 'SC_SS', '2023-10-12 01:12:56', 'Samsung', 24),
(32, b'1', 'SS_LG', '2023-10-12 01:12:56', 'LG', 24),
(33, b'1', 'TABLET', '2023-10-12 01:12:56', 'Máy tính bảng', NULL),
(34, b'1', 'TAB_IPADAIR', '2023-10-12 01:12:56', 'iPad Air', 33),
(35, b'1', 'TAB_IPADPRO', '2023-10-12 01:12:56', 'iPad Pro', 33),
(36, b'1', 'TAB_LENOVO', '2023-10-12 01:12:56', 'Lenovo', 33),
(37, b'1', 'TAB_SS', '2023-10-12 01:12:56', 'Samsung', 33),
(38, b'1', 'TAB_XIAO', '2023-10-12 01:12:56', 'Xiaomi', 33),
(39, b'1', 'SOUND', '2023-10-12 01:12:56', 'Âm thanh', NULL),
(40, b'1', 'SOU_BLUETOOTH', '2023-10-12 01:12:56', 'Tai nghe Bluetooth', 39),
(41, b'1', 'SOU_WIRELESS', '2023-10-12 01:12:56', 'Tai nghe không dây', 39),
(42, b'1', 'SOU_GAMING', '2023-10-12 01:12:56', 'Tai nghe Gaming', 39),
(43, b'1', 'SOU_FULL', '2023-10-12 01:12:56', 'Tai nghe chụp tai', 39),
(44, b'1', 'SOU_SPEAKER_BLUETOOTH', '2023-10-12 01:12:56', 'Loa Bluetooth', 39),
(45, b'1', 'SOU_SPEAKER_KAR', '2023-10-12 01:12:56', 'Loa Karaoke', 39),
(46, b'1', 'SMART_WATCH', '2023-10-12 01:12:56', 'Đồng hồ thông mình', NULL),
(47, b'1', 'SW_APPLE', '2023-10-12 01:12:56', 'Apple Watch', 46),
(48, b'1', 'SW_SS', '2023-10-12 01:12:56', 'Samsung', 46),
(49, b'1', 'SW_HUA', '2023-10-12 01:12:56', 'Huawei', 46),
(50, b'1', 'SW_XIAO', '2023-10-12 01:12:56', 'Xiaomi', 46),
(51, b'1', 'TELEVISION', '2023-10-12 01:12:56', 'Tivi', NULL),
(52, b'1', 'TELE_SS', '2023-10-12 01:12:56', 'Samsung', 51),
(53, b'1', 'TELE_XIAO', '2023-10-12 01:12:56', 'Xiaomi', 51),
(54, b'1', 'TELE_TOSHI', '2023-10-12 01:12:56', 'Toshiba', 51),
(55, b'1', 'TELE_LG', '2023-10-12 01:12:56', 'LG', 51),
(56, b'1', 'TELE_TCL', '2023-10-12 01:12:56', 'TCL', 51);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_color_product`
--

CREATE TABLE `_color_product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_color_product`
--

INSERT INTO `_color_product` (`id`, `name`, `product_id`) VALUES
(2, 'Đen', 9),
(3, 'Trắng', 9),
(4, 'Xanh', 9),
(5, 'Tím', 9),
(6, 'Titan Đen', 10),
(7, 'Titan Trắng', 10),
(8, 'Titan Tự Nhiên', 10),
(9, 'Titan Xanh', 10),
(10, 'Xám', 11),
(11, 'Vàng', 11),
(12, 'Xanh lá', 11),
(13, 'Xanh dương', 11),
(14, 'Đen', 12),
(15, 'Vàng', 12),
(16, 'Xám', 13),
(17, 'Bạc', 13),
(18, 'Vàng', 13),
(19, 'Đen', 14),
(20, 'Bạc', 15),
(21, 'Vàng', 15),
(22, 'Xám', 16),
(23, 'Xanh dương', 16),
(24, 'Bạc', 16),
(25, 'Bạc', 17),
(26, 'Đen', 18),
(27, 'Đen', 19),
(28, 'Đen', 20),
(29, 'Đen', 21),
(30, 'Trắng', 22);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_image`
--

CREATE TABLE `_image` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `object_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_image`
--

INSERT INTO `_image` (`id`, `created_date`, `object_id`, `type`, `url`) VALUES
(10, NULL, '9', 'PRODUCT', 'https://cdn2.cellphones.com.vn/358x/media/catalog/product/s/2/s23-ultra-kem_3.png'),
(11, NULL, '2', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/g/a/galaxys23ultra_front_green_221122_1.jpg'),
(12, NULL, '3', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/s/2/s23-ultra-kem-1_1.png'),
(13, NULL, '4', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/s/2/s23-ultra-xanh-1_1.png'),
(14, NULL, '5', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/s/2/s23-ultra-tim_2.png'),
(15, NULL, '10', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/v/n/vn_iphone_15_pro_black_titanium_pdp_image_position-1a_black_titanium_color.jpg'),
(16, NULL, '6', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/v/n/vn_iphone_15_pro_black_titanium_pdp_image_position-1b_black_titanium_color.jpg'),
(17, NULL, '7', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-15-pro-max_4__1.jpg'),
(18, NULL, '8', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-14-pro_2__2.png'),
(19, NULL, '9', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-14-pro_1__2.png'),
(20, NULL, '11', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/_/7/_76666_7__3.jpg'),
(21, NULL, '10', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/2/_/2_330_2.jpg'),
(22, NULL, '11', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/x/i/xiaomi-redmi-note-12-8gb-128gb_3__1.png'),
(23, NULL, '12', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/3/_/3_317_2.jpg'),
(24, NULL, '13', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/3/_/3_317_2.jpg'),
(25, NULL, '12', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/y/1/y16-_en-web.png'),
(26, NULL, '14', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/v/i/vivo-y16-den.jpg'),
(27, NULL, '15', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/v/i/vivo-y16-combo.jpg'),
(28, NULL, '13', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/macbook-air-gold-select-201810_4.jpg'),
(29, NULL, '16', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/macbookm1.jpg'),
(30, NULL, '17', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0002_dsc03721_2.jpg'),
(31, NULL, '18', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0000_macbook-air-gallery1-20201110_geo_us.jpg'),
(32, NULL, '14', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/5/h/5h53.png'),
(33, NULL, '19', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0003_21163_laptop_asus_tuf_gaming_f15_4_.jpg'),
(34, NULL, '15', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/2/_/2_448.png'),
(35, NULL, '20', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/1/_/1_472.png'),
(36, NULL, '21', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/3/_/3_405.png'),
(37, NULL, '16', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_31__5.png'),
(38, NULL, '22', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_23__7.png'),
(39, NULL, '23', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_25__5.png'),
(40, NULL, '24', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_24__7.png'),
(41, NULL, '17', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-lg-ultrawide-29wq600-29-inch-1.png'),
(42, NULL, '25', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-lg-ultrawide-29wq600-29-inch-2.png'),
(43, NULL, '18', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-asus-vz279heg1r-27-inch-1.png'),
(44, NULL, '26', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-asus-vz279heg1r-27-inch-2.png'),
(45, NULL, '19', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_182_3_.png'),
(46, NULL, '27', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-dell-gaming-monitor-s2422hg-24-inch-2_1.jpg'),
(47, NULL, '20', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-samsung-ls34c500gaexxv-34-inch-1.png'),
(48, NULL, '28', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-samsung-ls34c500gaexxv-34-inch-2.png'),
(49, NULL, '21', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/t/i/tivi_xiaomi_a_pro_3_1.png'),
(50, NULL, '29', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/t/i/tivi_xiaomi_a_pro_2_1.png'),
(51, NULL, '22', 'PRODUCT', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_169_2.png'),
(52, NULL, '30', 'PRODUCT_COLOR', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/a/i/airpods2-s-1.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_order`
--

CREATE TABLE `_order` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `admin_note` varchar(4000) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `method_payment` varchar(255) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  `payment_online_date` varchar(255) DEFAULT NULL,
  `payment_online_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `user_note` varchar(4000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_order`
--

INSERT INTO `_order` (`id`, `address`, `admin_note`, `code`, `created_by`, `created_date`, `full_name`, `method_payment`, `note`, `payment_online_date`, `payment_online_id`, `phone`, `status`, `total`, `user_note`) VALUES
(1, '28', NULL, NULL, 'qtoan443@gmail.com', '2023-10-30 00:06:51', 'Toàn Nguyễn', 'COD', '121', NULL, NULL, '0949357532', 'PENDING', 62982000.00, NULL),
(2, '28', NULL, NULL, 'qtoan443@gmail.com', '2023-10-30 00:14:59', 'Toàn Nguyễn', 'COD', '152 thạnh lộc ', NULL, NULL, '0949357532', 'PENDING', 56686000.00, NULL),
(3, '28', NULL, NULL, 'toannqps23443@fpt.edu.vn', '2023-10-30 00:16:45', 'Toàn Nguyễn', 'COD', '', NULL, NULL, '0949357532', 'PENDING', 36441000.00, NULL),
(4, '28', NULL, 'HD202310301000', 'toannqps23443@fpt.edu.vn', '2023-10-30 00:18:58', 'Toàn Nguyễn', 'COD', '', NULL, NULL, '0949357532', 'CANCEL', 17990000.00, ''),
(5, '28', NULL, 'HD202310301001', 'toannqps23443@fpt.edu.vn', '2023-10-30 00:22:30', 'Toàn Nguyễn', 'COD', 'ssww', NULL, NULL, '0949357532', 'DONE', 109323000.00, NULL),
(6, '28', NULL, 'HD202311081002', 'qtoan443@gmail.com', '2023-11-08 20:39:21', 'Toàn Nguyễn', 'COD', '', NULL, NULL, '0949357532', 'PENDING', 15290000.00, NULL);

--
-- Bẫy `_order`
--
DELIMITER $$
CREATE TRIGGER `tgr_gen_order_code` BEFORE INSERT ON `_order` FOR EACH ROW BEGIN
insert into _order_seq value();
set NEW.CODE = CONCAT('HD', DATE_FORMAT(CURRENT_DATE, '%Y%m%d'), LAST_INSERT_ID());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_order_detail`
--

CREATE TABLE `_order_detail` (
  `id` bigint(20) NOT NULL,
  `cost` decimal(19,2) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `percent_discount` int(11) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product_detail_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_order_detail`
--

INSERT INTO `_order_detail` (`id`, `cost`, `order_id`, `percent_discount`, `price`, `product_detail_id`, `quantity`) VALUES
(1, 1000000.00, 1, 40, 34990000.00, 25, 3),
(2, 1000000.00, 2, 30, 40490000.00, 24, 2),
(3, 1000000.00, 3, 10, 40490000.00, 22, 1),
(4, 1000000.00, 4, 0, 17990000.00, 64, 1),
(5, 1000000.00, 5, 10, 40490000.00, 22, 3),
(6, 1000000.00, 6, 0, 15290000.00, 67, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_order_seq`
--

CREATE TABLE `_order_seq` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_order_seq`
--

INSERT INTO `_order_seq` (`id`) VALUES
(1000),
(1001),
(1002);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_product`
--

CREATE TABLE `_product` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `is_show_home` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_description` varchar(200) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_product`
--

INSERT INTO `_product` (`id`, `active`, `category_id`, `code`, `created_by`, `created_date`, `description`, `is_show_home`, `name`, `short_description`, `slug`, `updated_date`) VALUES
(9, b'1', 12, 'SS_GALAXY_S23', NULL, NULL, '<p>Cập nhật thêm những thông tin mới và chính xác nhất về <a href=\"https://cellphones.com.vn/samsung-galaxy-s23-ultra.html\"><strong>Samsung Galaxy S23 Ultra</strong></a> tại CellphoneS ngay!</p><p><strong>Samsung Galaxy S23 Ultra 12GB 512GB</strong> tạo nên đột phá mạnh mẽ về mặt hiệu năng khi được trang bị vi xử lý <strong>Snapdragon 8 Gen 2 </strong>vượt trội cùng <strong>12GB bộ nhớ RAM</strong>. Chất lượng hiển thị siêu sắc nét trên S23 Ultra tới từ tầm nền <strong>Dynamic AMOLED 2X 120Hz</strong> thế hệ mới. Bên cạnh đó, smartphone này còn sở hữu cụm camera cao cấp với độ rõ nét đạt tới<strong> 200MP</strong>. Viên pin <strong>5000mAh</strong> cùng <strong>sạc nhanh 45W</strong> giúp nâng cao thời lượng sử dụng lên một tầm cao mới.&nbsp;</p><h2><strong>Samsung Galaxy S23 Ultra 12GB 512GB - Hiển thị sắc nét, cấu hình đỉnh cao</strong></h2><p>Smartphone cao cấp nhất của tập đoàn Samsung trong năm 2023 được nhiều người bình chọn là phân khúc<strong> Samsung Galaxy S23 Ultra</strong>. Bứt phá mọi giới hạn hiệu năng với vi xử lý Snapdragon 8 Gen 2 đỉnh cao cùng khả năng hiển thị hình ảnh sắc nét trong từng chi tiết điểm ảnh, Galaxy S23 Ultra mang tới trải nghiệm sử dụng siêu mượt mà, dễ dàng làm hài lòng những người dùng khó tính nhất</p><h3><strong>Cụm camera 200MP cực khủng giúp nâng tầm trải nghiệm quay chụp</strong></h3><p>Trong phiên bản nâng cấp lần này, khả năng quay chụp của Samsung Galaxy S23 Ultra được nâng lên một tầm cao mới và gần như không thua kém bất kỳ đối thủ nào trên thị trường. Với hệ thống ống kính gồm 3 camera phía sau, bao gồm camera chính 200MP, camera góc siêu rộng 12MP và 2 camera tele 10MP cho zoom quang học và xóa phông.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-5.jpg\" alt=\"Samsung Galaxy S23 Ultra 12GB 512GB - Hiển thị sắc nét, cấu hình đỉnh cao\" width=\"810\" height=\"456\"></p><p>Galaxy S23 Ultra vượt trội trong việc lấy nét chính xác và nhanh chóng trong chế độ chụp zoom, khắc phục hoàn toàn các vấn đề về lấy nét chậm, rung lắc và nhòe hình ở những thế hệ trước đó. Ngoài ra, tính năng quay video với độ phân giải 8K ở tốc độ 30FPS cũng đạt chất lượng cao cấp, chuẩn điện ảnh cho người dùng.</p><h3><strong>Cấu hình đột phá khi sở hữu chipset Snapdragon 8 Gen 2 mạnh mẽ</strong></h3><p>Về mặt hiệu năng, Galaxy S23 Ultra cũng có những điểm nâng cấp hết sức ấn tượng so với các thế hệ Samsung Galaxy S tiền nhiệm. Được trang bị con chip Snapdragon 8 Gen 2 tối ưu riêng cho phân khúc máy Galaxy S Series, S23 Ultra vượt trội hoàn toàn về mặt cấu hình.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-1.jpg\" alt=\"Samsung Galaxy S23 Ultra 12GB 512GB - Hiển thị sắc nét, cấu hình đỉnh cao\" width=\"810\" height=\"456\"></p><p>Do đó, các tác vụ cơ bản như xem phim, lướt web, quay chụp hình đều có thể được trải nghiệm hết sức mượt mà trên phân khúc S23 Ultra này. Kiểm tra điểm hiệu năng cho thấy máy đạt tới con số khá ấn tượng, 1767 điểm đơn nhân, 4314 điểm đa nhân trên thang đo Geekbench 6.</p><h3><strong>Trải nghiệm suốt ngày dài với viên pin 5000mAh</strong></h3><p>Samsung Galaxy S23 Ultra được trang bị viên pin lớn với dung lượng lên tới 5000mAh, đem tới thời lượng sử dụng lên tới 9 giờ 28 phút cho các tác vụ như chơi game, xem Youtube, lướt Facebook. Còn với những tác vụ cơ bản như gọi điện, nhắn tin thì bạn thậm chí có thể trải nghiệm Galaxy S23 Ultra trong cả ngày dài.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-4.jpg\" alt=\"Samsung Galaxy S23 Ultra 12GB 512GB - Hiển thị sắc nét, cấu hình đỉnh cao\" width=\"810\" height=\"456\"></p><p>Bên cạnh đó, thế hệ smartphone Samsung mới này còn hỗ trợ sạc nhanh lên tới 45W, giúp sạc đầy pin chỉ trong vỏn vẹn 1 giờ 27 phút.&nbsp;</p><h3><strong>Tạo hình sang trọng, thu hút mọi ánh nhìn</strong></h3><p>Galaxy S23 Ultra vẫn được thừa hưởng nhiều nét sang trọng giống với thế hệ S22 trước đó như khung kim loại, mặt lưng kính cong bo nhẹ. Tuy nhiên, kiểu dáng cong bo này đã được tinh chỉnh lại phẳng hơn so với S22 Ultra, mang lại cảm giác cầm nắm chắc tay và thoải mái hơn khi sử dụng trong thời gian dài.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-2.jpg\" alt=\"Samsung Galaxy S23 Ultra 12GB 512GB - Hiển thị sắc nét, cấu hình đỉnh cao\" width=\"810\" height=\"456\"></p><p>Mặt lưng máy được bao phủ bởi lớp kính cường lực chống va đập, có vân tay mờ nhẹ để trải nghiệm tốt hơn. Bộ khung nhôm bao bọc quanh máy có tính bền bỉ cao và cực kỳ nhẹ, mang lại điểm nhấn thiết kế với vẻ bóng loáng nổi bật.</p><h3><strong>Hiển thị chân thực hơn thông qua tấm nền Dynamic AMOLED 2X cao cấp</strong></h3><p>Bên cạnh các điểm nhấn vượt trội trên thì các thông số hiển thị trên Samsung Galaxy S23 Ultra cũng không hề tầm thường. Máy sở hữu công nghệ màn hình Dynamic AMOLED 2X, độ phân giải 2K và độ sáng lên tới 1750nits.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-10.jpg\" alt=\"samsung-galaxy-s23-ultra-12gb-512gb\" width=\"810\" height=\"456\"></p><p>Nhờ vào những thông số này, màn hình hiển thị trên S23 Ultra mang đến màu sắc rực rỡ và nội dung sắc nét, tạo trải nghiệm tuyệt vời khi chơi game và xem phim.</p><h2><strong>Điện thoại Samsung Galaxy S23 Ultra được ra mắt vào thời điểm nào?</strong></h2><p>Sau một loạt các đồn đoán trên nhiều trang tin công nghệ nổi tiếng thì đầu tháng 2 vừa rồi, ấn phẩm Samsung S23 Ultra cũng đã chính thức có mặt trên thị trường. Cụ thể, Samsung Galaxy S23 Ultra đã được ra mắt vào ngày 1/02/2023 tại sự kiện Unpacked 2023, tổ chức ở San Francisco.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-8.jpg\" alt=\"Điện thoại Samsung Galaxy S23 Ultra được ra mắt vào thời điểm nào?\" width=\"810\" height=\"456\"></p><p>Các phiên bản màu sắc mà Samsung đã lựa chọn để khoác lên S23 Ultra trong lần ra mắt vừa rồi là: Tím, kem, xanh và đen. Tất cả các tùy chọn màu này đều toát lên sự sang trọng, lịch lãm nhưng cũng cực kỳ trẻ trung, tươi mới, phù hợp với nhiều đối tượng người dùng.</p><h2><strong>Samsung Galaxy S23 Ultra sẽ có mức giá bao nhiêu?</strong></h2><p>Vào thời điểm vừa mới được ra mắt, S23 Ultra được đánh giá là có giá thành cao hơn một chút so với người anh em S22 Ultra trước đó. Tuy nhiên, hiện tại thì mức giá thành của S23 Ultra đã có sự biến động. Cụ thể:</p><p>- Samsung Galaxy S23 Ultra 8GB/ 256GB: khoảng 26.990.000 VNĐ.<br>- Samsung Galaxy S23 Ultra 12GB/ 512GB: khoảng 31.990.000 VNĐ.<br>- Samsung Galaxy S23 Ultra 12GB/ 1TB: khoảng 35.990.000 VNĐ.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Samsung/samsung_s/S23/samsung-galaxy-s23-ultra-12gb-512gb-3.jpg\" alt=\"Samsung Galaxy S23 Ultra sẽ có mức giá bao nhiêu?\" width=\"810\" height=\"456\"></p><p>So với các đối thủ cùng tầm giá khác như iPhone 14 Series thì mức giá này của Galaxy S23 Ultra không chênh lệch quá nhiều so với mặt bằng chung của thị trường.&nbsp;</p><h2><strong>Mua Samsung Galaxy S23 Ultra 12GB 512GB giá hấp dẫn tại CellphoneS</strong></h2><p>Siêu phẩm công nghệ đầu năm 2023 của Samsung - Samsung Galaxy S23 Ultra 12GB 512GB hiện đang được bày bán với mức giá siêu hấp dẫn tại cửa hàng công nghệ CellphoneS. Nhanh tay liên hệ tới số 1800 2097 của CellphoneS để nhận được báo giá tốt cùng nhiều phần quà khuyến mãi khác khi mua Samsung S23 Ultra ngay bạn nhé!</p>\r\n', b'1', 'Samsung Galaxy S23 Ultra', 'Thoả sức chụp ảnh, quay video chuyên nghiệp - Camera đến 200MP, chế độ chụp đêm cải tiến, bộ xử lí ảnh thông minh\r\nChiến game bùng nổ - chip Snapdragon 8 Gen 2 8 nhân tăng tốc độ xử lí, màn hình 120Hz', 'samsung-galaxy-s23-ultra', NULL),
(10, b'1', 11, 'IP_15_PM', NULL, NULL, '<h2><strong>Đánh giá điện thoại iPhone 15 Pro Max có gì mới</strong></h2><p><strong>iPhone 15 Pro Max</strong> đem lại một diện mạo hoàn toàn mới và sở hữu nhiều tính năng ưu việt cùng công nghệ tiên tiến. Hãy khám phá các đánh giá chi tiết về sản phẩm về khía cạnh thiết kế, màn hình, hiệu năng, thời lượng pin và bộ camera độc đáo qua các thông tin dưới đây!</p><h3><strong>Hiệu năng mạnh mẽ cùng chipset A17 Pro tiên tiến&nbsp;</strong></h3><p>Sản phẩm trang bị <strong>chip A17 Pro</strong> được sản xuất trên <strong>tiến trình 3nm</strong>, mang lại bước cải tiến đột phá. Nhờ những cải tiến về thiết kế và kiến trúc vi mô, CPU mới <strong>nhanh hơn đến 10%</strong>, hỗ trợ tính năng như Giọng nói cá nhân hay tự động sửa lỗi chuyên nghiệp. Không chỉ vậy, <strong>GPU nhanh hơn đến 20%</strong> với thiết kế 6 lõi mới nhằm nâng cao hiệu suất và tiết kiệm năng lượng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-20.jpeg\" alt=\"Đánh giá điện thoại iPhone 15 Pro Max\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>iPhone 15 Pro đã có một bước tiến đáng kể với công nghệ dò tia tăng tốc phần cứng, với tốc độ nhanh hơn gấp 4 lần so với các phiên bản trước đây dùng công nghệ dò tia dựa trên phần mềm. Kết quả của sự cải tiến này là trải nghiệm đồ họa trên thiết bị trở nên mượt mà hơn, đặc biệt là trải nghiệm khi chơi game và sử dụng ứng dụng thực tế ảo (AR) sẽ trở nên sống động hơn.&nbsp;Người dùng sẽ có trải nghiệm chiến game mượt mà hơn với: Resident Evil 4, Seadth Stranding, Resident Evil Village hay Assassin\'s Creed Mirage,...</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-22.png\" alt=\"Đánh giá điện thoại iPhone 15 Pro Max\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Chip A17 Pro được trang bị một bộ giải mã AV1 đặc biệt, giúp nâng cao chất lượng video và tiết kiệm pin khi bạn sử dụng các dịch vụ truyền phát. Đồng thời, bộ điều khiển USB mới đã được cải tiến để hỗ trợ tốc độ USB 3 lần đầu xuất hiện trên iPhone, cung cấp khả năng truyền dữ liệu nhanh hơn đáng kể và hỗ trợ đầu ra video với độ phân giải HDR 4K ở tốc độ 60 fps.</p><h3><strong>Công nghệ màn hình vượt trội</strong></h3><p>Màn hình <strong>OLED</strong> trên <strong>iPhone 15 Pro Max</strong> là một điểm đặc biệt không thể bỏ qua. Với kích thước <strong>6,7 inch</strong> cùng độ phân giải đỉnh cao <strong>2796x1179 pixel</strong>, tần số quét 120Hz và mật độ điểm ảnh lên đến 460 ppi, màn hình mang đến trải nghiệm ấn tượng cho người dùng. Nó còn được trang bị công nghệ HDR, giúp hiển thị màu sắc và độ sáng chân thực hơn bao giờ hết.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-21.jpeg\" alt=\"Công nghệ màn hình vượt trội\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Thêm vào đó, công nghệ <strong>TrueTone</strong> hiện đại đã được tích hợp, tự động điều chỉnh màu sắc của màn hình dựa trên môi trường xung quanh. Với sự kết hợp này, bạn có thể thấy rõ ràng rằng màn hình của iPhone 15 Pro Max tạo ra trải nghiệm xem phim, chơi game và lướt web vượt trội với hình ảnh sống động, rực rỡ, và màu sắc chân thực.</p><h3><strong>Tính năng Dynamic Island độc đáo</strong></h3><p>Dù không phải lần đầu xuất hiện trên iPhone, nhưng việc Dynamic Island tiếp tục được trang bị trên Apple iPhone 15 Pro Max vẫn đáp ứng được nhu cầu của người dùng. Dynamic Island mang đến trải nghiệm thú vị, đa dạng tính năng như âm nhạc, xem maps mà không cần thoát khỏi ứng dụng đang mở. Bên cạnh đó, với tính năng cập nhật thông báo theo thời gian thực mang đến tính tiện lợi khi sử dụng điện thoại IP 15 Pro Max.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/apple-iphone-pro-max-18.png\" alt=\"Màn hình iPhone 15 Pro Max\" width=\"810\" height=\"456\"></p><p>(Ảnh: Apple.com)</p><h3><strong>Dung lượng pin sử dụng thoải mái, cổng kết nối USB-C</strong></h3><p><strong>iPhone 15 Pro Max</strong> sở hữu viên pin có <strong>dung lượng lớn hơn</strong> đáng kể so với thế hệ trước đó. Với viên pin này, bạn có thể thưởng thức <strong>video lên đến 29 giờ</strong>, xem video trực tuyến lên đến 25 giờ, nghe nhạc lên đến 95 giờ (tuỳ vào tác vụ khác nhau mà thời gian có thể thay đổi). Nhờ vậy, bạn có thể thoải mái sử dụng cả ngày mà không lo gián đoạn giữa chừng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-5.png\" alt=\"Thời lượng pin\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Thiết bị hỗ trợ sạc Magsafe lên đến 15W, sạc không dây Qi lên đến <strong>7.5W.</strong> Khi sạc với bộ tiếp hợp nguồn 20W hoặc công suất lớn hơn, iPhone 15 Pro Max có thể sạc đầy lên đến 50% pin trong vòng 30 phút.</p><p>Điểm khác biệt của iPhone 15 Pro Max còn nằm ở <strong>cổng sạc USB-C mới</strong>, cho phép sử dụng 1 cáp sạc để nạp năng lượng cho các thiết bị khác như Mac, iPad, AirPods Pro gen 2 được nâng cấp và iPhone 15 Pro Max. Ngoài ra, bạn có thể dùng iPhone 15 Pro Max để sạc AirPods hoặc Apple Watch thông qua cổng USB Type C này.</p><h3><strong>Kết nối mạng 5G, cho trải nghiệm internet vượt trội</strong></h3><p><strong>iPhone 15 Pro Max</strong> đặc biệt ở khả năng hỗ trợ kết nối 5G, mang đến trải nghiệm internet nhanh và ổn định hơn bao giờ hết. Do đó, bạn có thể thỏa sức xem video trực tuyến, chơi game online, tải nội dung và thậm chí làm việc từ xa mà không lo gặp sự cản trở từ tình trạng giật lag. Ngoài ra, 5G còn giúp bạn truy cập internet ở những khu vực có tín hiệu yếu một cách mượt mà.</p><h3><strong>Tích hợp công nghệ eSIM tiện lợi</strong></h3><p>Một điều thú vị nữa ở iPhone 15 Pro Max là có thể lưu trữ <strong>tối đa đến 8 eSIM</strong>, có thể bật lên sử dụng bất cứ lúc nào. Tuy nhiên tuỳ vào khu vực sử dụng mà thiết bị chỉ có thể sử dụng được 1 đến 2 eSIM cùng lúc. Người dùng có thể kích hoạt eSIM tại trang web trực tuyến của nhà mạng, hoặc nhờ nhân viên hỗ trợ kích hoạt khi nhận máy.</p><h3><strong>Nút tác vụ với cơ chế mới tiện lợi hơn</strong></h3><p>Sản phẩm đt&nbsp;<strong>iPhone Pro Max 2023</strong> còn có một điểm mới là phím gạt rung được thay đổi thành <strong>nút tác vụ (Action Button)</strong> với cơ chế hoàn toàn mới. Nhiều reviewer đánh giá nút tác vụ trên IP 15 Pro Max rất mạnh mẽ, có thể khởi chạy hầu hết ứng dụng trên thiết bị như: nút rung chuông, chế độ tập trung, các ứng dụng camera, đèn pin, ghi âm, kính lúp,... Ngoài ra còn có các trợ năng khác để người dùng tuỳ chỉnh theo nhu cầu sử dụng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/apple-iphone-pro-max-16.png\" alt=\"Thiết kế iPhone Pro Max 2023\" width=\"810\" height=\"456\"></p><p>(Ảnh: Apple.com</p><h3><strong>Thiết kế bền bỉ cùng chất liệu titan cao cấp</strong></h3><p><strong>iPhone 15 Pro Max</strong> được chế tạo bởi <strong>chất liệu&nbsp;titan cao cấp</strong> chuẩn hàng không vũ trụ&nbsp;với độ bền ấn tượng. Sản phẩm không chỉ thu hút mọi ánh nhìn bởi diện mạo tinh tế và hiện đại mà còn mang đến phiên bản Pro nhẹ nhất từng có của Apple. Không chỉ vậy, đây là một sản phẩm thân thiện với môi trường bởi hãng đã sử dụng nguyên liệu tái chế để giảm lượng rác thải.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/apple-iphone-15-pro-6.JPG\" alt=\"Thiết kế bền bỉ cùng chất liệu titan cao cấp\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><h3><strong>Màu sắc thanh lịch, thu hút mọi ánh nhìn</strong></h3><p>iPhone 15 Pro Max là sự hòa quyện hoàn hảo giữa công nghệ và tính thẩm mỹ, với bảng màu đa dạng gồm 4<strong>&nbsp;sự lựa chọn phong cách: titan đen, titan xanh, titan tự nhiên và titan trắng</strong>. Mỗi gam màu tạo nên một câu chuyện riêng, thể hiện cá tính và phong cách cá nhân của người sử dụng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-9.png\" alt=\"Đánh giá sản phẩm iPhone 15 Pro Max\" width=\"810\" height=\"455\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Đây không chỉ đơn thuần là một thiết bị di động thông minh, mà còn là biểu tượng của thời trang và cá tính. Nó cho phép bạn tự do thể hiện bản thân và phong cách thông qua việc lựa chọn màu sắc độc đáo này.</p><h3><strong>Công nghệ camera ấn tượng, cho hình ảnh chi tiết chân thực nhất</strong></h3><p>Hệ thống <strong>camera kép tiên tiến trên iPhone 15 Pro Max</strong> không chỉ mang lại những trải nghiệm quay chụp ấn tượng mà còn biến mỗi khoảnh khắc thành một tác phẩm nghệ thuật. Camera chính <strong>48MP</strong>, camera UltraWide <strong>12MP</strong> vượt trội với khả năng tối ưu hóa chụp ảnh, mang đến bức ảnh độ phân giải siêu sắc nét với màu sắc chân thực.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-23.jpeg\" alt=\"Cụm camera\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Người dùng có thể điều chỉnh với 3 tuỳ chọn tiêu cự: 35mm, 28mm, 24mm và có thể lựa chọn 1 tiêu cự bất kỳ để cài đặt mặc định. Camera 48MP hỗ trợ hình ảnh HEIF độ phân giải gấp 4 lần.&nbsp;Điều này cùng với khả năng xử lý hình ảnh tiên tiến để tạo ra những bức ảnh đẹp và ấn tượng hơn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-6.png\" alt=\"Chuyển đổi dữ liệu đơn giản\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Camera telephoto 12MP cũng mang đến sự cải thiện đáng kể. Nó có khả năng thu phóng quan học phạm vi lên đến <strong>5x</strong>, mang lại sự độc đáo và chuyên nghiệp cho mỗi bức ảnh. Tính năng thu phóng quang học hỗ trợ tốt trong các trường hợp cần ghi lại những khoảnh khắc, hình ảnh từ xa, chụp cận cảnh. Module dịch chuyển 3D tự động lấy nét kết hợp với công nghệ cảm biến chống rung quang học là điểm cải tiến được đánh giá cao.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-24.jpeg\" alt=\"camera\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><p>Cùng với đó, công nghệ <strong>Photonic Engine</strong> tạo nên sắc màu tươi sáng, chân thực và nổi bật các chi tiết.&nbsp;Ngoài ra, camera trước TrueDepth 12MP có khả năng tự động lấy nét kết hợp với khả năng chụp ảnh cận cảnh và chất lượng nhóm, giúp bạn bắt trọn nhiều khoảnh khắc quý giá.</p><h3><strong>Công nghệ Face ID bảo mật hàng đầu</strong></h3><p>Với công nghệ tiên tiến của <strong>Face ID tích hợp trong iPhone 15 Pro Max</strong>, thao tác mở khóa thiết bị trở nên dễ dàng và an toàn hơn bao giờ hết. Sản phẩm này sử dụng khả năng nhận diện khuôn mặt vô cùng chính xác, ngay cả trong các điều kiện ánh sáng biến đổi và các góc độ khác nhau. Hệ thống <strong>camera TrueDepth</strong> đảm bảo cho bạn trải nghiệm mở khóa nhanh chóng và độ bảo mật cao.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-4.png\" alt=\"Công nghệ Face ID bảo mật hàng đầu\" width=\"810\" height=\"456\"></p><p><i>(Ảnh: Apple.com)</i></p><h3><strong>Hệ điều hành iOS 17 mới nhất 2023</strong></h3><p><strong>IOS 17 trên đt iPhone 15 Pro Max</strong> mang đến những tối ưu đáng kể về hiệu năng, tính bảo mật và trải nghiệm trên thiết bị. Người dùng có thể sử dụng những tính năng thú vị và giao diện mượt mà. Phiên bản cập nhật hệ điều hành này cũng được cho là đáng để cập nhật trên thiết bị, kể cả các dòng iPhone 11, 12. Một số tính năng mới trên IOS 17 của iP 15 Pro Max mà bạn nên thử:</p><p>- <strong>Namedrop</strong> cải tiến hơn Airdrop: chỉ cần đặt 2 chiếc iPhone cùng hệ điều hành IOS 17 để truyền tải các file dữ liệu. Thao tác này giúp tiết kiệm thời gian đáng kể so với cách gửi file truyền thống bằng Airdrop.</p><p>- <strong>Nhạc chuông mới</strong>: một điều mới mẻ được mang đến là hàng chục bài nhạc chuông mới tương thích cho cả cuộc gọi và báo thức. Người dùng có thể tuỳ chỉnh nhạc chuông cho từng eSIM để phân biệt cuộc gọi đến từ sim nào một cách hữu ích.</p><p>- <strong>Tự động xoá tin nhắn OTP</strong>: để nâng cao tính bảo mật, IOS 17 còn có thể tự xoá các tin nhắn với mã OTP được gửi đến sau khi người dùng chọn tự động điền (autofill). Tuy nhiên để sử dụng bạn cần thực hiện cài đặt trước.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/apple-iphone-pro-max-17.png\" alt=\"Hệ điều hành IOS 17\" width=\"810\" height=\"456\"></p><p>(Ảnh: Apple.com)</p><p>- <strong>Ảnh đại diện liên hệ</strong>: người dùng hoàn toàn có thể tuỳ chỉnh ảnh đại diện cho các liên hệ của mình, khiến danh sách liên hệ trở nên sinh động và dễ sử dụng hơn.</p><p>- <strong>Tạo nhãn dán động</strong>: nhãn dán động được tạo từ ảnh, video hoặc live photo trên điện thoại. Các nhãn dán này có thể được sử dụng cho tin nhắ, ghi chú và đánh dấu. Với nhãn dán động trên IOS 17, các cuộc trò chuyện sẽ trở nên sinh động và thú vị hơn bao giờ hết.</p><h3><strong>Kết nối với hệ sinh thái Apple (Apple Watch,&nbsp;Airpods, Mac)</strong></h3><p>Một điểm nổi bật của iPhone so với các thiết bị smartphone khác đó chính là hệ sinh thái Apple. Theo đó, điện thoại&nbsp;iPhone 15 Pro Max có thể kết nối với các thiết bị khác trong cùng hệ sinh thái như&nbsp;Apple Watch, Airpods hay Mac một cách nhanh chóng. Theo đó:</p><p>-&nbsp; iPhone và&nbsp;Apple Watch: Hỗ trợ kiểm tra tin nhắn, trả lời cuộc gọi hay điều khiến các ứng dụng trên iPhone một cách tiện lợi.</p><p>-&nbsp; iPhone và Mac: Chuyển đổi liền mạch giữa các thiết bị qua&nbsp;tính năng Handoff nhờ đó người dùng có thể thực hiện các công việc còn dang dở trên thiết bị khác. Cùng với đó, người dùng có thể truy cập vào các dữ liệu, hình ảnh, video nhờ hệ thống iCloud đồng bộ.</p><p>-&nbsp; iPhone và Airpods: Kết nối nhanh chóng, hỗ trợ trả lời cuộc gọi cũng như gọi Siri trên di động thông minh</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/apple-iphone-15-pro-21.jpg\" alt=\"Kết nối với hệ sinh thái Apple (Apple Watch, Airpods, Mac)\" width=\"810\" height=\"456\"></p><p>(Ảnh: Apple.com)</p><h2><strong>iPhone 15 Pro Max dung lượng 256GB có đủ dùng không?</strong></h2><p>Thế hệ điện thoại Apple mới 2023 này, Apple đã loại bỏ phiên bản dung lượng bộ nhớ trong 128GB. Theo đó,&nbsp;iPhone 15 Pro Max 256GB bộ nhớ trong sẽ là phiên bản bộ nhớ chuẩn. Vậy 256GB bộ nhớ lưu trữ có đủ dùng không, phù hợp sử dụng với những ai?</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/apple-iphone-15-pro-22.jpg\" alt=\"iPhone 15 Pro Max dung lượng 256GB có đủ dùng\" width=\"810\" height=\"456\"></p><p>256GB là dung lượng lưu trữ lớn, phù hợp với nhu cầu sử dụng của đại đa số người dùng cơ bản. Theo đó, nếu sử dụng điện thoại trong các nhu cầu như chơi game, gọi điện, nhắn tin, lướt web thì 256GB hoàn toàn có thể đáp ứng được nhu cầu.&nbsp;Tuy nhiên, với người dùng thực hiện các công việc về sáng tạo nội dung, cần lưu trữ những hình ảnh hay thước phim độ phân giải cao với dung lượng lớn thì có thể cân nhắc đến những phiên bản dung lượng cao hơn.</p><h2><strong>So sánh iPhone 15 Pro Max và iPhone 14 Pro Max</strong></h2><p>iPhone 15 Pro Max tiếp nối nhiều điểm mạnh từ phiên bản trước, iPhone 14, và được bổ sung, cải tiến với nhiều công nghệ tiên tiến mới. Hãy cùng xem qua bảng <strong>so sánh iPhone 15 Pro Max với iPhone 14 Pro Max</strong> dưới đây để nắm rõ sự tương đồng và khác biệt giữa hai dòng sản phẩm này!</p><figure class=\"table\"><table><tbody><tr><td>&nbsp;</td><td><strong>iPhone 15 Pro Max</strong></td><td><strong>iPhone 14 Pro Max</strong></td></tr><tr><td>Kích thước</td><td>159.9 x 76.7 x 8.25 mm</td><td>160.7 x 77.6 x 7.85 mm</td></tr><tr><td>Kết cấu</td><td><p><strong>Vỏ hợp kim titan</strong></p><p>Mặt sau kính mờ</p><p><strong>Nút Action</strong></p></td><td><p>Vỏ làm bằng thép không gỉ</p><p>Mặt sau kính mờ</p><p>Nút gạt tắt chuông/im lặng</p></td></tr><tr><td>Trọng lượng</td><td><strong>221 gam</strong></td><td>240 gam</td></tr><tr><td>Màu sắc</td><td>Titan đen, Titan trắng, Titan xanh, Titan tự nhiên</td><td>Đen, Tím, Bạc, Vàng</td></tr><tr><td>Màn hình</td><td><p>OLED Super Retina 6.7 inch</p><p>Thiết kế viên thuốc</p><p>Dynamic Island</p><p>Màn hình luôn bật&nbsp;</p></td><td><p>OLED Super Retina 6.7 inch</p><p>Thiết kế viên thuốc</p><p>Dynamic Island</p><p>Màn hình luôn bật&nbsp;</p></td></tr><tr><td>Độ phân giải</td><td>2796x1290 pixel</td><td>2796 x 1290 pixel</td></tr><tr><td>Mật độ điểm ảnh</td><td>460 PPI</td><td>460 PPI</td></tr><tr><td>Độ sáng tối đa</td><td>2000 nits</td><td>2000 nits</td></tr><tr><td>Tần số quét</td><td>&nbsp;đến 120Hz</td><td>&nbsp;đến 120Hz</td></tr><tr><td>Bộ vi xử lý</td><td><strong>A17 Pro</strong></td><td>A16 Bionic</td></tr><tr><td>Bộ nhớ trong</td><td><strong>256GB, 512GB, 1TB</strong></td><td>128GB, 256GB, 512GB, 1TB</td></tr><tr><td>Thời lượng pin</td><td>Lên đến 29 giờ xem video</td><td>Lên đến 29 giờ xem video</td></tr><tr><td>Cổng sạc&nbsp;</td><td><strong>USB-C</strong></td><td>Lighting</td></tr><tr><td>Camera</td><td><p>48MP + 12MP + 12MP</p><p><strong>Camera tele zoom 5x</strong></p></td><td><p>48MP + 12MP + 12MP</p><p>Camera tele zoom 3x</p></td></tr><tr><td>Hỗ trợ 5G</td><td>Có</td><td>Có</td></tr></tbody></table></figure><p>Điểm khác biệt của iPhone 15 Pro Max với 14 Pro Max nằm ở <strong>lớp vỏ được làm bằng chất liệu Titanium</strong> nhẹ, bền bỉ và cao cấp, điều này giúp trọng lượng máy giảm đi đáng kể. Thêm vào đó, một số điểm nổi bật khác như chip A17 Pro sản xuất trên tiến trình 3nm cho hiệu năng mạnh mẽ, <strong>cổng sạc USB-C 3.0</strong> với tốc độ nhanh hơn và<strong> camera zoom quang 5x</strong>.&nbsp;Một điểm đáng chú ý còn nằm nút Action thay thế cho nút gạt tắt chế độ im lặng, vị trí nằm phía trên 2 nút tăng giảm âm lượng. Kể cả trên phiên bản <a href=\"https://cellphones.com.vn/iphone-15.html\"><strong>iPhone 15 128GB</strong></a> tiêu chuẩn, Apple cũng áp dụng những thay đổi này để mang đến trải nghiệm hoàn hảo.</p><h2><strong>iPhone 15 Pro Max giá bao nhiêu tiền, bảng giá chi tiết?</strong></h2><p><strong>iPhone 15 Pro Max</strong>&nbsp;ra mắt cùng series&nbsp;iPhone 15&nbsp;đã thu hút sự chú ý của cộng đồng yêu công nghệ trên toàn cầu không chỉ bởi sự đổi mới về công nghệ mà còn bởi mức giá hợp lý. Vậy điện thoại&nbsp;<strong>iPhone 15 Pro Max giá bao nhiêu</strong>&nbsp;khi bắt đầu mở bán tại Việt Nam? Với mức giá khởi điểm&nbsp;<strong>từ 1.199 USD</strong>&nbsp;tại thị trường Mỹ, tùy thuộc vào quốc gia và các yếu tố cụ thể, giá sản phẩm có thể thay đổi.</p><p>Vậy iPhone 15 Pro Max giá bao nhiêu tại Việt Nam - tham khảo ngay bảng giá chi tiết sau:</p><figure class=\"table\"><table><tbody><tr><td><strong>Sản phẩm</strong></td><td><p><strong>Giá tham khảo</strong></p><p><strong>(theo Apple.com)</strong></p></td><td><p><strong>Giá cuối tại CellphoneS</strong></p><p><strong>(đã bao gồm các ưu đãi)</strong></p></td></tr><tr><td>iPhone 15 Pro Max 256GB</td><td>từ 34.999.000 VND</td><td>từ 31.490.000 VND</td></tr><tr><td>iPhone 15 Pro Max 512GB</td><td>từ 40.999.000 VND</td><td>từ 37.490.000 VND</td></tr><tr><td>iPhone 15 Pro Max 1TB</td><td>&nbsp;từ 46.999.000 VND</td><td>từ 41.490.000 VND</td></tr></tbody></table></figure><h2><strong>Vì sao nên chọn mua điện thoại iPhone 15?</strong></h2><p><strong>iPhone 15 Pro Max</strong> được các tín đồ công nghệ chú ý, đặc biệt là các iFan bởi tính đột phá về công nghệ cùng hiệu năng vượt trội. Đây xứng đáng là 1 trong những siêu phẩm mà bạn nên sở hữu trong năm 2023.</p><h3><strong>Khả năng sử dụng bền bỉ</strong></h3><p>Không chỉ iPhone 15 Pro Max mà các sản phẩm khác của Apple đều được sản xuất với quy trình nghiêm ngặt và đạt các chứng nhận tiêu chuẩn quốc tế, đảm bảo độ bền và khả năng sử dụng trong thời gian dài. Không chỉ vậy, với chất liệu titan cao cấp chuẩn hàng không vũ trụ, bạn lại càng yên tâm trong quá trình sử dụng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-7.png\" alt=\"Vì sao nên chọn mua điện thoại iPhone 15?\" width=\"810\" height=\"455\"></p><p><i>(Ảnh:Apple.com)</i></p><h3><strong>Khả năng bảo mật, bảo vệ dữ liệu người dùng cao</strong></h3><p>Sản phẩm tích hợp Camera TrueDepth, mang lại khả năng nhận diện khuôn mặt chính xác ngay cả trong các góc độ và điều kiện ánh sáng khác nhau. Điều này không chỉ giúp bạn mở khóa thiết bị một cách nhanh chóng mà còn bảo vệ thông tin cá nhân một cách tối ưu.</p><p>Không dừng lại ở đó, Camera TrueDepth còn là nền tảng cho nhiều tính năng bảo mật và tiện ích khác như <strong>Animoji, Memoji</strong> và chế độ chụp ảnh chân dung chất lượng cao với hiệu ứng ấn tượng. Tất cả những điều này đều đóng góp vào việc tạo ra một môi trường an toàn và bảo mật cho dữ liệu cá nhân và thông tin quan trọng của bạn trên thiết bị này.</p><h3><strong>Hiệu năng mạnh mẽ</strong></h3><p>Sản phẩm được trang bị công nghệ tiên tiến và <strong>chipset A17 Pro</strong>, mang lại hiệu suất đỉnh cao cho mọi nhu cầu sử dụng của bạn.Khả năng xử lý nhanh chóng và mượt mà giúp bạn dễ dàng thực hiện các tác vụ đa nhiệm, chơi game yêu thích, và làm việc với ứng dụng nặng. Không chỉ vậy, dung lượng bộ nhớ lớn có sẵn trên <strong>iPhone 15 Pro Max</strong> cung cấp không gian lưu trữ đáng kể, đảm bảo bạn có đủ chỗ để lưu trữ dữ liệu, hình ảnh và video quan trọng của mình.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iphone-15-pro-11.JPG\" alt=\"Chuyển đổi dữ liệu đơn giản\" width=\"810\" height=\"456\"></p><p><i>(Ảnh:Apple.com)</i></p><h3><strong>Nâng cao trải nghiệm với chính sách bảo hành Apple</strong></h3><p>Sản phẩm iPhone thường được <strong>bảo hành chính hãng 1 năm</strong>. Điều này đồng nghĩa với việc bạn có thể tận hưởng dịch vụ kiểm tra và bảo hành từ các trung tâm ủy quyền của Apple trên khắp cả nước.</p><p>Chính sách bảo hành của Apple không chỉ đảm bảo sự hỗ trợ kỹ thuật chuyên nghiệp mà còn mang đến sự yên tâm về việc sử dụng sản phẩm. Nếu thiết bị của bạn gặp lỗi do nhà sản xuất hoặc trong điều kiện bảo hành, bạn có thể dễ dàng đưa máy đến để được kiểm tra và sửa chữa miễn phí hoặc với mức giá ưu đãi. Điều này giúp nâng cao giá trị của chiếc iPhone 15 Pro Max và tạo sự tin tưởng trong việc sử dụng sản phẩm.</p><h3><strong>Chuyển đổi dữ liệu đơn giản</strong></h3><p>Chỉ cần một vài thao tác đơn giản, bạn có thể chuyển đổi dữ liệu quan trọng như tin nhắn và hình ảnh từ các thiết bị khác một cách dễ dàng. Không cần phải lo lắng về việc mất dữ liệu quan trọng hay rắc rối trong quá trình chuyển đổi. Điều này giúp bạn tiết kiệm thời gian và công sức, tận hưởng ngay trải nghiệm sử dụng mới mẻ trên iPhone 15 ProMax.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-8.png\" alt=\"Hiệu năng mạnh mẽ\" width=\"810\" height=\"456\"></p><p><i>(Ảnh:Apple.com)</i></p><h3><strong>Phụ kiện đa dạng về tính năng và màu sắc</strong></h3><p>Sự đa dạng về phụ kiện là một trong những điểm đáng chú ý của <strong>iPhone 15 Pro Max</strong>. Bạn có thể tận hưởng sự linh hoạt trong việc tùy chỉnh thiết bị của mình theo cách riêng biệt. Có nhiều loại ốp lưng và bao da với các màu sắc và kiểu dáng độc đáo để bảo vệ thiết bị và thể hiện phong cách cá nhân. Ngoài ra, các phụ kiện như tai nghe không dây, <strong>sạc nhanh MagSafe</strong>, và nhiều sản phẩm khác cũng giúp nâng cao trải nghiệm sử dụng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-10.JPG\" alt=\"Kết nối với bạn bè cùng hệ sinh thái\" width=\"810\" height=\"456\"></p><p><i>(Ảnh:Apple.com)</i></p><h3><strong>Sản phẩm thân thiện môi trường</strong></h3><p>Apple sử dụng các <strong>vật liệu tái chế và nguyên liệu thân thiện với môi trường</strong> trong quá trình sản xuất iPhone 15 Pro Max. Điều này giúp giảm lượng rác thải và tác động đến tài nguyên tự nhiên. Không chỉ vậy, sản phẩm còn tiết kiệm năng lượng trong quá trình sử dụng hàng ngày, giúp giảm lượng khí nhà kính.</p><h3><strong>Kết nối với bạn bè cùng hệ sinh thái</strong></h3><p>iPhone 15 Pro Max không chỉ là một chiếc điện thoại thông minh, mà còn là cầu nối để bạn kết nối với bạn bè và gia đình thông qua hệ sinh thái của Apple. Sự tích hợp mượt mà với các sản phẩm và dịch vụ khác nhau giúp bạn thực hiện các hoạt động chia sẻ và giao tiếp một cách dễ dàng hơn bao giờ hết.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iPhone-15-pro-max-5.png\" alt=\"Kết nối với bạn bè cùng hệ sinh thái\" width=\"810\" height=\"456\"></p><p><i>(Ảnh:Apple.com)</i></p><p>Bạn có thể chia sẻ ảnh, video và tài liệu một cách nhanh chóng qua <strong>iMessage và AirDrop</strong>. Sử dụng <strong>FaceTime</strong> để thực hiện cuộc gọi video chất lượng cao với người thân và bạn bè, dù họ ở bất kỳ đâu trên thế giới. Ngoài ra, tích hợp với iCloud giúp bạn lưu trữ và truy cập dữ liệu cá nhân từ bất kỳ thiết bị nào trong hệ sinh thái Apple.</p><h2><strong>Tại sao nên mua iPhone 15 Pro Max chính hãng tại CellphoneS?</strong></h2><p>Để sở hữu được sản phẩm&nbsp;<strong>iPhone 15 ProMax</strong>&nbsp;chính hãng, giá tốt, ưu đãi hấp dẫn thì việc tìm kiếm đơn vị uy tín và đáng tin cậy là điều vô cùng quan trọng. Một trong những hệ thống cung cấp sản công nghệ chính hãng, giá tốt hiện nay là CellphoneS:</p><p><strong>Sản phẩm chính hãng, có bảo hành đầy đủ:&nbsp;</strong>CellphoneS tự hào là 1 trong những đơn vị cung cấp sản phẩm chính hãng Apple tại thị trường Việt Nam. Do đó, khách hàng hoàn toàn yên tâm về chất lượng, chính sách bảo hành và nguồn gốc sản phẩm khi mua iPhone 15 Pro Max tại đây.</p><p><strong>Thu cũ lên đời với trợ giá hấp dẫn:&nbsp;</strong>Chương trình mang đến trải nghiệm độc đáo với chương trình thu cũ lên đời vô cùng hấp dẫn. Bạn sẽ được hưởng trợ giá cao cho chiếc iPhone cũ của mình, giúp giảm đáng kể chi phí khi sở hữu iPhone 15 ProMax mới.</p><p><strong>Giảm giá sâu cho phụ kiện mua kèm chính hãng:&nbsp;</strong>Khi mua kèm phụ kiện tại cửa hàng cùng với chiếc iPhone 15 Pro Max, bạn sẽ được hưởng ưu đãi hấp dẫn với mức giảm giá sâu. Như vậy, khách hàng có thể trang bị cho chiếc điện thoại mới của mình bằng các phụ kiện chất lượng, như ốp lưng, tai nghe, hoặc bộ sạc không dây với mức tiết kiệm đáng kể.</p><p><strong>Trả góp 0%, trả trước chỉ với 1 khoản nhỏ:&nbsp;</strong>CellphoneS triển khai chương trình trả góp 0% với mức trả trước thấp giúp khách hàng giảm bớt áp lực về gánh nặng tài chính. Chỉ cần chi trả 1 khoản nhất định theo từng tháng với gói trả góp đã chọn, bạn đã có thể tận hưởng ngay tiện ích mà sản phẩm mang lại.</p><p><strong>Hệ thống cửa hàng toàn quốc, giao nhanh:&nbsp;</strong>Hệ thống không chỉ giúp bạn trải nghiệm trực tiếp sản phẩm trước khi quyết định mua, mà còn đảm bảo giao hàng nhanh chóng và tiện lợi. Bất kể bạn ở đâu, chúng tôi sẽ luôn sẵn sàng phục vụ bạn với dịch vụ giao hàng chất lượng và đáng tin cậy.</p><p>Trên đây là tổng hợp thông tin chi tiết về điện thoại <strong>Apple iPhone 15 ProMax</strong> như: thông số, giá bao nhiêu, có gì mới. Để có trong tay chiếc <strong>iPhone 15 Pro Max chính hãng</strong>, cùng với hàng loạt ưu đãi hấp dẫn, xem ngay các phiên bản tại CellphoneS. Không chỉ có chính sách trả góp 0%, CellphoneS còn hỗ trợ chương trình thu cũ lên đời với những ưu đãi và nhiều chính sách giảm giá hấp dẫn. Điều này sẽ giúp bạn không chỉ tiết kiệm chi phí mà còn sở hữu sớm nhất siêu phẩm đến từ nhà Táo.</p><h2><strong>Một số câu hỏi thường gặp về iPhone 15 Pro Max</strong></h2><p>iPhone 15 Pro Max sở hữu cấu hình vượt trội nhưng người dùng còn nhiều thắc mắc đến sản phẩm này trước khi có quyết định nâng cấp. Một số thắc mắc phổ biến mà người dùng quan tâm đến sản phẩm này phải kể đến như:</p><h3><strong>iPhone 15&nbsp;Pro Max có mấy phiên bản bộ nhớ trong</strong></h3><p>Không giống như ba phiên bản khác, điện thoại&nbsp;iPhone 15 Pro Max không được trang bị phiên bản 128GB dung lượng bộ nhớ lưu trữ mà nâng cấp dung lượng chuẩn lên 256GB. Theo đó,&nbsp;<strong>iPhone 15 Pro Max</strong> sẽ còn <strong>ba phiên bản bộ nhớ trong: 256GB, 512GB và 1TB.</strong></p><h3><strong>iPhone 15&nbsp;Pro Max nặng bao nhiêu</strong></h3><p>Với khung viền titan cải tiến nhờ đó thế hệ điện thoại mới này nhẹ hơn đáng kể so với phiên bản tiền nhiệm. Cụ thể&nbsp;<strong>iPhone 15 Pro Max có trọng lượng</strong> rơi vào khoảng&nbsp;<strong>221 gram</strong>, nhẹ hơn tới 20g so với trên 14 Pro Max (240 gam). Với trọng lượng giảm, thiết bị giúp người dùng có những trải nghiệm cầm nắm thoải mái hơn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iphone-14-pro-max-16.jpg\" alt=\"iPhone 15 Pro Max nặng bao nhiêu\" width=\"810\" height=\"456\"></p><h3><strong>iPhone 15&nbsp;Pro Max có màu hồng, đỏ không</strong></h3><p>Đặc điểm màu sắc trên dòng iPhone Pro của Apple đó là sử dụng những tone màu trung tính. Trong khi đó phiên bản thường,&nbsp; Plus hay mini sẽ được trang bị những gam màu rực rỡ như vàng, đỏ, tím hồng,..&nbsp;iPhone 15 Pro Max cũng không nằm ngoài quy luật trên nên máy sẽ không được trang bị màu đỏ hay hồng mà sở hữu bốn màu: Titan tự nhiên, đen, trắng và xanh</p><h3><strong>Đang dùng iPhone 14 Pro/Pro Max có nên mua 15 Pro Max không</strong></h3><p>Sở hữu nhiều nâng cấp nổi trội về thiết kế và tính năng, vậy người dùng sử dụng phiên bản 14 Pro và Pro Max có nên nâng cấp lên thế hệ mới này không? Câu trả lời là có. 15 Pro Max với khung titan sang trọng, cổng sạc với cùng nút tác vụ tiện lợi. Bên cạnh đó, điện thoại còn được cải tiến cấu hình với con chip A17 Pro cùng camera nâng cấp khả năng chụp zoom lên 5x. Với những tính năng trên thì những người dùng muốn trải nghiệm những công nghệ mới nhất hoàn toàn nên trải nghiệm. Trường hợp người dùng muốn trải nghiệm nhưng còn vướng mắc về giá thì có thể tham khảo đến chương trình thu cũ lên đời để sở hữu điện thoại công nghệ mới nhất với khoản chi phí cần bù thêm hợp lý.</p><h3><strong>Kích thước của iPhone 15 Pro Max bao nhiêu</strong></h3><p>Cùng sở hữu màn hình kích thước 6.7 inch như thế hệ thệ 14 Pro Max tuy nhiên kích thước điện thoại mới này có sự thay đổi đôi chút. Cụ thể, máy dày hơn đôi chút so với trên iPhone 14 Pro Max và giảm kích thước về chiều dài và rộng. Chi tiết kích thước trên iPhone 15 Pro Max như sau: Dày&nbsp;8,25mm, rộng&nbsp;76,7 mm và dài&nbsp;159,9 mm.</p><h3><strong>iPhone 15 Pro Max có chống nước, chống sốc không</strong></h3><p>Mẫu điện thoại cao cấp mới này vẫn được Apple trang bị các tiêu chuẩn kháng nước và bụi bẩn như thế hệ trước đó. Cụ thể,&nbsp;iPhone 15 Pro Max được trang bị chuẩn <strong>kháng nước và bụi bẩn&nbsp;IP68 theo tiêu chuẩn IEC 60529</strong> giúp bảo vệ thiết bị dưới nước ở độ sâu 6 mét trong thời gian tối đa 30 phút. Tuy nhiên, tính năng kháng nước sẽ không được hãng bảo hành, do đó khuyến kích người dùng không test thử tính năng này.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Apple/iphone_15/iphone-14-pro-max-15.jpg\" alt=\"iPhone 15 Pro Max có chống nước\" width=\"810\" height=\"456\"></p><h3><strong>Màu titan trên iPhone 15 Pro Max gần với màu gì</strong></h3><p>iPhone 15 Pro Max được trang bị bốn màu titan hoàn toàn mới bao gồm: Titan tự nhiên, titan đen, titan trắng và titan xanh. Cả bốn màu trên phiên bản đều là những màu mới trong đó titan tự nhiêu có màu sắc của màu&nbsp;Titanium trong tự nhiên và nhận được nhiều quan tâm của người dùng. Ban phiên bản màu còn lại tuy mới nhưng vẫn có nhiều điểm tương đồng với những màu sắc trước đó, như sau:</p><figure class=\"table\"><table><tbody><tr><td><strong>Màu iPhone 15 Pro Max</strong></td><td><strong>So sánh với màu trước đó</strong></td></tr><tr><td>Titan tự nhiên</td><td>Màu mới lần đầu xuất hiện</td></tr><tr><td>Titan đen</td><td>Khá giống&nbsp;Pacific Blue trên&nbsp;iPhone 12 Pro nhưng sắc xanh đậm hơn</td></tr><tr><td>Titan trắng</td><td>Có nhiều điểm tương đồng với&nbsp;Black Space trước đó</td></tr><tr><td>Titan xanh</td><td>Màu sắc gần giống với màu Bạc&nbsp;Silver</td></tr></tbody></table></figure><p>Nhìn chung, tuy cả bốn màu đều là màu mới nhưng chúng đều có điểm tương đồng với những màu sắc từng được xuất hiện trước đó của Apple.</p>\r\n', b'1', 'iPhone 15 Pro Max', 'Thoả sức chụp ảnh, quay video chuyên nghiệp - Camera đến 200MP, chế độ chụp đêm cải tiến, bộ xử lí ảnh thông minh\r\nChiến game bùng nổ - chip Snapdragon 8 Gen 2 8 nhân tăng tốc độ xử lí, màn hình 120Hz', 'iphone-15-pro-max', NULL);
INSERT INTO `_product` (`id`, `active`, `category_id`, `code`, `created_by`, `created_date`, `description`, `is_show_home`, `name`, `short_description`, `slug`, `updated_date`) VALUES
(11, b'1', 13, 'XIAO_NOTE_12', NULL, NULL, '<p><strong>Xiaomi Redmi Note 12 8GB 128GB </strong>tỏa sáng với diện mạo viền vuông cực thời thượng cùng hiệu suất mạnh mẽ nhờ sở hữu con chip <strong>Snapdragon 685 </strong>ấn tượng. Chất lượng hiển thị hình ảnh của Redmi Note 12 Vàng cũng khá sắc nét thông qua tấm nền <strong>AMOLED 120Hz </strong>hiện đại. Chưa hết, máy còn sở hữu cụm 3 camera với độ rõ nét lên tới <strong>50MP </strong>cùng <strong>viên pin 5000mAh </strong>và s <strong>ạc nhanh 33W </strong>giúp đáp ứng được mọi nhu cầu sử dụng của người dùng.</p><h2><strong>Phiên bản sắc vàng - Độc quyền chỉ có tại CellphoneS</strong></h2><p>Xiaomi Redmi Note 12 được Xiaomi cho ra mắt với nhiều phiên bản màu sắc khác nhau cho người dùng lựa chọn, tuy nhiên sắc vàng là màu sắc ấn tượng nhất trong series <strong>Redmi Note 12</strong> này và hiện được bán độc quyền tại CellphoneS.</p><p>Redmi Note 12 Vàng Bình Minh với hiệu ứng chuyển màu gradient bắt mắt phối hợp với thiết kế khung viền phẳng mang lại một thiết kế tinh tế.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-3.jpg\" alt=\"Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><h2><strong>Điện thoại Xiaomi Redmi Note 12 8GB Vàng - Cấu hình vượt trội, quay chụp đỉnh cao</strong></h2><p>Xiaomi Redmi Note 12 8GB 128GB có lẽ là cái tên được nhắc tới nhiều nhất khi nói về các dòng smartphone tốt trong phân khúc giá tầm trung. Thế hệ 12 của Series Xiaomi Redmi Note này được đánh giá cao về thiết kế thời thượng bên ngoài cùng hiệu năng mạnh mẽ thông qua chipset Qualcomm hiện đại. Dưới này là các đánh giá chi tiết về thông số kỹ thuật của Xiaomi Redmi Note 12 mà bạn có thể tham khảo thêm nhé!</p><h3><strong>Trải nghiệm mượt mà mọi tác vụ với chipset Snapdragon 685 hiện đại</strong></h3><p>Sức mạnh xử lý, tính toán của Xiaomi Redmi Note 12 vàng tới từ con chip hiện đại của nhà Qualcomm - Snapdragon 685. Đây là con chip được sản xuất trên tiến trình 6nm, mang lại hiệu suất ấn tượng với tốc độ xung nhịp tối đa lên tới 2.8GHz.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-4.jpg\" alt=\"HIệu năng Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><p>Điều này đảm bảo cho máy luôn hoạt động mượt mà và hiệu quả trong các tác vụ hàng ngày như đàm thoại, lướt web và xem phim. Không chỉ vậy, Redmi Note 12 còn hỗ trợ chơi các tựa game phổ biến như Liên Quân Mobile và PUBG Mobile với cấu hình phù hợp, đem tới trải nghiệm tốt nhất cho người dùng. Cụ thể, qua các bài test hiệu năng máy mang lại 360216 điểm trên phần mềm Benchmark và trên Geekbench 6 thì thiết bị cho 436 điểm đơn nhân và 1440 điểm đa nhân.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-8.jpg\" alt=\"HIệu năng Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><h3><strong>Cải thiện khả năng nhiếp ảnh với cảm biến camera lên tới 50MP</strong></h3><p>Với bộ cảm biến 3 camera, bao gồm camera chính 50MP, camera góc siêu rộng 8MP và camera macro 2MP, Xiaomi Redmi Note 12 8GB cho phép người dùng thỏa sức ghi lại các phong cảnh khác nhau với chất lượng cực kỳ sắc nét.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-10.jpg\" alt=\"Camera Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><p>Đồng thời, máy cũng hỗ trợ tính năng chụp ảnh chân dung với hiệu ứng bokeh tự nhiên, mang lại những bức ảnh đẹp và đầy ấn tượng. Thông qua cụm cảm biến hiện đại này của Redmi Note 12, bạn sẽ có được những trải nghiệm chụp ảnh và quay video cực kỳ tuyệt vời.</p><h3><strong>Thỏa sức làm việc giải trí suốt ngày dài với viên pin 5000mAh</strong></h3><p>So với thế hệ tiền nhiệm trước đó, thông số năng lượng trên Xiaomi Redmi Note 12 vàng dường như vẫn được giữ nguyên là 5000mAh. Tuy nhiên, do được nâng cấp về vi xử lý nên mức độ tiêu thụ điện năng của phân khúc smartphone này đã được tối ưu hơn rất nhiều. Cụ thể, máy có thể duy trì hoạt động trong suốt 1,35 ngày trước khi cần sạc lại.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-6.jpg\" alt=\"Pin Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><p>Ngoài ra, Redmi Note 12 vàng còn hỗ trợ công nghệ sạc siêu nhanh với công suất tối đa 33W. Nhờ khả năng sạc nhanh này, bạn có thể tiết kiệm được nhiều thời gian hơn và không phải chờ đợi quá lâu trong quá trình nạp lại pin nữa rồi nhé!</p><h3><strong>Nâng tầm trải nghiệm hình ảnh thông qua màn hình cỡ lớn</strong></h3><p>Trong phiên bản Redmi Series mới nhất này, Xiaomi đã tích hợp cho máy màn hình AMOLED có độ phân giải Full HD+ (1080 x 2400 pixels) siêu sắc nét. Với công nghệ này, chất lượng hiển thị trên màn hình luôn đạt độ chi tiết cực cao, màu sắc sinh động, mang tới trải nghiệm hình ảnh chân thực cho mọi khung hình.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-7.jpg\" alt=\"Màn hình Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><p>Ngoài ra, Redmi Note 12 còn sở hữu tần số quét màn hình đạt tới 120Hz. Nhờ vậy mà mọi trải nghiệm vuốt chạm của người dùng trên màn hình luôn cực kỳ nhanh nhạy, mượt mà. Đồng thời, máy cũng sẽ sở hữu độ sáng tối đa 1200nits, giúp cải thiện khả năng hiển thị hình ảnh mỗi khi được sử dụng ngoài trời.</p><h3><strong>Diện mạo vuông vắn đầy thời thượng</strong></h3><p>Xiaomi Redmi Note 12 8GB có diện mạo đẹp mắt và được đánh giá là khá ấn tượng. Theo đó, máy sở hữu thiết kế viền vuông, các góc bo tròn kết hợp với khung vát phẳng tạo nên một vẻ ngoài hết sức hiện đại nhưng cũng không kém phần trẻ trung.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-5.jpg\" alt=\"Thiết kế Xiaomi Redmi Note 12 8GB Vàng\" width=\"810\" height=\"456\"></p><p>Mặt trước của điện thoại được trang bị màn hình phẳng và được thiết kế dạng nốt ruồi. Viền màn hình được tối ưu hóa, tạo ra một không gian hiển thị rộng rãi, thoải mái.&nbsp;</p><h2><strong>Điện thoại Xiaomi Redmi Note 12 8GB khi nào ra mắt?</strong></h2><p>Không để các MiFan phải chờ đợi lâu, ngay từ đầu năm 2023 vừa rồi, Xiaomi đã chính thức “trình làng” ấn phẩm công nghệ nổi bật - Redmi Note 12 trên thị trường smartphone. Cụ thể, Xiaomi Redmi Note 12 đã được ra mắt vào ngày 11/01/2023 tại thị trường nội địa.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-9.jpg\" alt=\"Điện thoại Xiaomi Redmi Note 12 8GB khi nào ra mắt\" width=\"810\" height=\"456\"></p><p>Về các phiên bản màu sắc, Redmi Note 12 sở hữu 3 phiên bản màu chính lần lượt là: Xám, Xanh Dương và Xanh Lá. Các tone màu này đều cực kỳ sang trọng, tinh tế, phù hợp với mọi phong cách cá nhân của người sử dụng.&nbsp;</p><h2><strong>Điện thoại Xiaomi Redmi Note 12 8GB 128GB giá bao nhiêu?</strong></h2><p>Xiaomi Redmi Note 12 8GB hiện tại đang có mức giá dao động trong khoảng 5.8 triệu VNĐ. Với một phân khúc smartphone tầm trung như Redmi Note 12 thì mức giá này khá hợp lý.&nbsp;</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Redmi/Redmi-Note/Xiaomi-Redmi-Note12-Vang-1.jpg\" alt=\"Điện thoại Xiaomi Redmi Note 12 8GB 128GB giá bao nhiêu\" width=\"810\" height=\"456\"></p><p>Dù so với người anh em tiền nhiệm trước đó thì mức giá này có hơi cao, tuy nhiên những nâng cấp trong lần ra mắt này của Redmi Note 12 8GB 128GB được xem là hoàn toàn xứng đáng với con số trên. Nếu bạn đang tìm kiếm một phân khúc smartphone tầm trung ổn định thì Xiaomi Redmi Note 12 sẽ là gợi ý hoàn hảo mà bạn không nên bỏ qua nhé!</p><h2><strong>Mua Xiaomi Redmi Note 12 vàng giá tốt tại CellphoneS</strong></h2><p>Cửa hàng CellphoneS hiện đang là điểm đến mua sắm smartphone được rất nhiều người tiêu dùng Việt lựa chọn hiện nay. Do đó, nếu vẫn chưa tìm được địa chỉ mua Xiaomi Redmi Note 12 8GB nào đủ uy tín thì CellphoneS sẽ là cửa hàng mà bạn không nên bỏ lỡ nhé!</p><p>Khi mua sắm tại CellphoneS, bạn có thể hoàn toàn yên tâm khi chất lượng sản phẩm tại đây luôn được đảm bảo chính hãng và có chế độ bảo hành đầy đủ. Kết nối ngay tới cửa hàng qua hotline 1800 2097 để được hỗ trợ và hướng dẫn đặt mua hàng bạn nhé!</p>\r\n', b'0', 'Xiaomi Redmi Note 12', 'Thoả sức chụp ảnh, quay video chuyên nghiệp - Camera đến 200MP, chế độ chụp đêm cải tiến, bộ xử lí ảnh thông minh\r\nChiến game bùng nổ - chip Snapdragon 8 Gen 2 8 nhân tăng tốc độ xử lí, màn hình 120Hz', 'Xiaomi-redmi-note-12', NULL),
(12, b'1', 15, 'VIVO_Y16', NULL, NULL, '<p>Thương hiệu Vivo đang trở nên “bứt phá” trong năm 2022 với hàng loạt sản phẩm mang đến cho các fan công nghệ, và một trong số đó là điện thoại Vivo Y16. Được trang bị pin lớn, camera kép và hiệu năng gaming, Y16 sẽ là sản phẩm <strong>điện thoại Vivo</strong> tuyệt vời chỉ với mức giá phải chăng.</p><h2><strong>Điện thoại Vivo Y16 - Trẻ trung, gọn nhẹ cùng cấu hình nổi trội</strong></h2><p>Dòng smartphone phổ thông Y series của hãng Vivo vừa kết nạp thêm một “thành viên” mới mang tên điện thoại Vivo Y16 - với ngoại hình trẻ trung kết hợp cùng các thông số nổi trội như camera kép, pin 5000mAh và tối ưu gaming giúp model nhà Vivo trở thành lựa chọn đáng sở hữu trong phân khúc giá rẻ.</p><h3><strong>Thiết kế khung viền phẳng sang trọng, màu sắc họa tiết ánh sao</strong></h3><p><strong>Vivo Y16</strong> sẽ là chiếc điện thoại hấp dẫn đến từ Vivo cho người dùng công nghệ. Chiếc máy sở hữu ngôn ngữ thiết kế hòa phối giữa trẻ trung và hiện đại đã được Vivo áp dụng lên nhiều sản phẩm gần đây. Mặt lưng điện thoại được hoàn thiện với họa tiết xước phay nhẹ giúp tăng vẻ cuốn hút. Thân hình mỏng gọn giúp Y16 luôn có độ linh hoạt khi cầm trên tay.</p><p>Nếu bạn đang tìm kiếm chiếc điện thoại sử dụng chip&nbsp;Snapdragon 680 có thể tham khảo thêm ngay <a href=\"https://cellphones.com.vn/vivo-y36.html\"><strong>Vivo Y36</strong></a> thế hệ tiền nhiệm mới của Y Series cũng được rất nhiều người tìm kiếm và lụa chọn</p><p>Mặt lưng điện thoại Y16 được hoàn thiện từ vật liệu&nbsp;PMMA và PC có kết cấu như thủy tinh nhờ đó mà sản phẩm không chỉ sở hữu một màu sắc tươi sáng ấn tượng&nbsp; mà còn chông bám vân tay hay chống mài mòn một cách hiệu quả.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Vivo/vivo_y/vivo-Y16-18-compressed.jpg\" alt=\"Điện thoại Vivo Y16 \"></p><p>Vivo Y16 có hai phiên bản màu sắc sang trọng độc đáo cho người dùng lựa chọn đó là đen ánh sao và vàng mưa sao. Cả hai phiên bản đều mang hiệu ứng lấp lánh như những ánh sao trên bầu trời.</p><h3><strong>Màn hình Halo full view 6.51 inch, chế độ bảo vệ mắt thông minh</strong></h3><p>Điện thoại Vivo Y16 này phù hợp cho các tác vụ giải trí nhờ màn hình 6.51 inch thiết kế notch “giọt nước” quen thuộc. Độ phân giải màn hình sẽ là 1600 x 720 pixels - tức chuẩn HD Plus. Phần viền hai bên màn hình Vivo Y16 đã được thu hẹp tối ưu, giúp mở rộng tỷ lệ hiển thị và cho hình ảnh thêm sống động.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Vivo/vivo_y/vivo-Y16-25-compressed.jpg\" alt=\"Điện thoại Vivo Y16\"></p><p>Đặc biệt màn hình còn tự động điều chỉnh độ sáng theo ánh sáng của môi trường xung quanh cũng như khả năng lọc bỏ ánh sáng xanh gây hại cho mắt người dùng. Tuy nhiên tính năng bảo vệ mắt của điện thoại Vivo Y16 sẽ không tự động kích hoạt, người dùng cần bật tính năng để sử dụng.</p><h3><strong>Bảo mật nâng cao với mở khóa gương mặt và vân tay cạnh bên</strong></h3><p>Điện thoại Y16&nbsp;được hãng Vivo trang bị nhiều công nghệ bảo mật an toàn và nhanh chóng. Cụ thể,&nbsp;Y16 được trang bị cảm biến vân tay cạnh bên trên nút nguồn mang lại kahr năng mở khóa nhanh chóng với độ an toàn cao.&nbsp;Bên cạnh đó là mở khóa gương mặt trong nháy mắt mỗi khi bạn cần.</p><h3><strong>Chụp ảnh thú vị với bộ đôi camera 13 MP</strong></h3><p>Điện thoại Vivo Y16 mang đến khả năng nhiếp ảnh đa dạng cho người dùng công nghệ. Chiếc máy sở hữu cụm camera sau hai ống kính, trong đó camera chính có độ phân giải 13 MP khẩu độ f/2.2 cung cấp tính năng chụp góc rộng, và camera phụ có độ phân giải 2 MP chụp ảnh chân dung. Các tính năng chụp đa dạng như timelapse, làm đẹp khuôn mặt tự động, chụp chuyên nghiệp đều góp mặt trên Vivo Y16.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Vivo/vivo_y/vivo-Y16-15-compressed.jpg\" alt=\"Điện thoại Vivo Y16\" width=\"810\" height=\"456\"></p><p>Ngoài ra điện thoại Vivo Y16 còn trang bị thêm camera selfie độ phân giải 5 MP. Camera này giúp Y16 không chỉ bắt trọn toàn bộ khung cảnh selfie ở góc rộng, mà còn có khả năng quay video định dạng Full HD 1080p tuyệt vời để sáng tạo nội dung.</p><h3><strong>Hiệu suất ấn tượng với chip&nbsp;MediaTek Helio P35, RAM mở rộng</strong></h3><p>Được tối ưu trải nghiệm chơi game, điện thoại Vivo Y16 được trang bị bộ chip xử lý MediaTek Helio G35 - vốn thuộc dòng chip Helio G series chuyên gaming cho hiệu năng mạnh. Hơn nữa, điểm mạnh của Vivo Y16 còn có bộ nhớ RAM 4 GB và tùy chọn bộ nhớ lên đến 128 GB đáp ứng nhu cầu lưu trữ của cả điện thoại và của chính người dùng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Vivo/vivo_y/vivo-Y16-22-compressed.jpg\" alt=\"Điện thoại Vivo Y16\" width=\"810\" height=\"456\"></p><p>Ngoài ra, điểm nổi trội nữa mà điện thoại Y16 có được đó là khả năng tối ưu hiệu suất chơi game. Hai tính năng Multi Turbo 2.2 và Ultra Game Mode sẽ cho phép người dùng điều chỉnh thiết lập game tùy ý. Chế độ Extended RAM 2.0 hỗ trợ Vivo Y16 chạy đa nhiệm thêm mượt và không lag. Điện thoại Vivo Y16 sẽ vận hành trên nền tảng Android 12, với giao diện riêng mang tên Funtouch OS 12 hỗ trợ cập nhật dài hạn.</p><h3><strong>Thoải mái dùng máy nhờ 5000mAh</strong></h3><p>Vivo Y16 sở hữu dung lượng pin tận 5000mAh cung cấp thời gian sử dụng trọn vẹn ngày dài, đáp ứng tốt các nhu cầu liên lạc kiêm giải trí. Y16 cũng tích hợp công nghệ sạc 10W với chuẩn kết nối USB Type-C đa dụng phổ biến, tương thích nhiều loại dây kết nối.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Vivo/vivo_y/vivo-Y16-4-compressed.jpg\" alt=\"Điện thoại Vivo Y16\"></p><p>Về phương thức bảo mật, điện thoại Vivo Y16 có trang bị cảm biến vân tay đặt tại nút cạnh thân máy. Đồng thời, các công nghệ kết nối hữu dụng như Bluetooth 5.0, GPS, WiFi thế hệ mới và jack cắm tai nghe 3.5mm đều góp mặt trên điện thoại nhằm phục vụ trọn các thao tác sử dụng hằng ngày.</p><h2><strong>Giá bán và ngày ra mắt của điện thoại Vivo Y16</strong></h2><p>Điện thoại Vivo Y16 dự kiến sẽ được ra mắt vào giữa tháng Tám năm 2022. Chiếc máy sẽ ra mắt tại nhiều thị trường các nước châu Á.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Phone/Vivo/vivo_y/vivo-Y16-2-ava-compressed.jpg\" alt=\"Điện thoại Vivo Y16\"></p><p>Điện thoại Vivo Y16 dự kiến có giá bán khởi điểm gần 5 triệu VNĐ - nằm trong phân khúc trung cấp. Đồng thời mức giá này cho thấy Vivo Y16 có thể cạnh tranh cùng những sản phẩm Realme C35, Samsung Galaxy A23, hoặc OPPO A55.</p><h2><strong>Đặt trước điện thoại Vivo Y16 và nhận phần quà hấp dẫn tại hệ thống CellphoneS</strong></h2><p>Hệ thống CellphoneS là điểm đến tuyệt vời cho những giây phút trải nghiệm công nghệ hấp dẫn. Và điện thoại Y16 - một trong những sản phẩm mới của hãng smartphone Vivo - sẽ dự kiến cập bến thị trường Việt Nam sắp tới. Hãy nhanh tay đặt trước tại CellphoneS để nhận máy cùng nhiều phần quà hấp dẫn tại hệ thống nhé!&nbsp;</p><p>Tóm lại, nếu đang đang tìm kiếm những dòng điện thoại thuộc phân khúc tầm trung có thể đáp ứng các nhu cầu cơ bản thì <strong>Vivo Y16</strong> chính là một trong những lựa chọn tốt nên chọn hiện nay.&nbsp;</p>\r\n', NULL, 'vivo Y16', 'Thoả sức chụp ảnh, quay video chuyên nghiệp - Camera đến 200MP, chế độ chụp đêm cải tiến, bộ xử lí ảnh thông minh\r\nChiến game bùng nổ - chip Snapdragon 8 Gen 2 8 nhân tăng tốc độ xử lí, màn hình 120Hz', 'vivo-y16', NULL),
(13, b'1', 18, 'MAC_AIR_M1', NULL, NULL, '<h2><strong>Macbook Air M1 2020 - Sang trọng, tinh tế, hiệu năng khủng</strong></h2><p><strong>Macbook Air M1</strong> là dòng sản phẩm có thiết kế mỏng nhẹ, sang trọng và tinh tế cùng với đó là giá thành phải chăng nên <a href=\"https://cellphones.com.vn/laptop/mac/macbook-air.html\"><strong>MacBook Air</strong></a> đã thu hút được đông đảo người dùng yêu thích và lựa chọn. Đây cũng là một trong những phiên bản Macbook Air mới nhất mà khách hàng không thể bỏ qua khi đến với CellphoneS. Dưới đây là chi tiết về thiết kế, cấu hình của máy.</p><h3><strong>Thiết kế tinh tế, chất liệu nhôm bền bỉ</strong></h3><p>Macbook Air 2020 M1 mới vẫn luôn tuân thủ triết lý thiết kế với những đường nét đơn nhưng vô cùng sang trọng. Máy có độ mỏng nhẹ chỉ 1,29kg và các cạnh tràn viền khiến thiết bị trở nên đẹp hơn và cao cấp hơn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/macbook/Air/M1-2020/macbook-air-2020-m1-4.jpg\" alt=\"Thiết kế tinh tế, chất liệu nhôm bền bỉ\" width=\"810\" height=\"456\"></p><p>Vỏ nhôm bên ngoài mang đến sự bền bỉ vượt trội theo thời gian. Các cổng vẫn được thiết kế tương tự như phiên bản trước đó được ra mắt hồi tháng 3 năm 2020.</p><p><i>&gt;&gt;&gt; <strong>Tìm hiểu thêm</strong>:&nbsp;</i><a href=\"https://cellphones.com.vn/macbook-air-2022.html\"><i>Macbook Air 2022</i></a><i> thiết kế mỏng nhẹ vô cùng ấn tượng</i></p><h3><strong>Màn hình Retina 13.3 inch tráng gương</strong></h3><p>MacBook Air M1 256GB 2020&nbsp;được trang bị màn hình Retina IPS 13.3 inch mang đến nhiều màu sắc hơn lên đến 48% so với những thế hệ trước đó. Bên cạnh đó, màn hình tráng gương tràn viền khiến viền giúp mỏng hơn 50% so với trước đây.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/macbook/Air/M1-2020/macbook-air-2020-m1-6.jpg\" alt=\"Màn hình Retina 13.3 inch tráng gương\" width=\"810\" height=\"456\"></p><p>Với độ phân giải 2560 x 1600 và tỉ lệ khung hình 16:10 với 227 ppi giúp hình ảnh trở nên rõ nét và sống động hơn bao giờ hết. Ngoài ra, công nghệ True Tone trên máy tự động điều chỉnh cân bằng trắng giúp phù hợp với nhiệt độ màu của ánh sáng xung quanh và mang đến không gian màu rộng hơn 25% so với sRGB.</p><h3><strong>Chip M1, hiệu năng cực đỉnh</strong></h3><p>Điểm nhấn của&nbsp;MacBook Air M1 256GB 2020&nbsp;chính là con chip M1. CPU của chip M1 sẽ có 8 nhân, bao gồm 4 nhân hiệu suất cao và 4 nhân hiệu suất thấp mang đến sức mạnh vượt trội cho thiết bị rất. Sức mạnh trên M1 256GB hơn 98% so với các laptop Windows và hiệu năng vượt trội hơn so với các phiên bản Air sử dụng chip Intel.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/macbook/Air/M1-2020/macbook-air-2020-m1-3.jpg\" alt=\"Chip M1, hiệu năng cực đỉnh\" width=\"810\" height=\"456\"></p><p>RAM 8GB và card đồ họa VGA 7-core GPU giúp máy có thể xử lý nhanh chóng các tác vụ hằng ngày. Ngoài ra với việc trang bị ổ cứng SSD dung lượng 256GB sẽ cho người dùng tốc độ đọc, sao chép, ghi cực nhanh so với ổ HDD thông thường.</p><h3><strong>Bàn phím Magic Keyboard, Touch ID tiện lợi</strong></h3><p><strong>Macbook Air M1 2020</strong>&nbsp;được trang bị bàn phím Magic Keyboard trên cơ chế cắt chéo với bước phím chỉ 1mm. Máy có phím Esc vật lý đồng thời cụm phím mũi tên được bố trí theo kiểu chữ \"T\" đảo ngược. Với thiết kế này mang lại cho người dùng trải nghiệm đánh máy chính xác, êm ái và vô cùng thoải mái.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/macbook/Air/M1-2020/macbook-air-2020-m1-2.jpg\" alt=\"Bàn phím Magic Keyboard, Touch ID tiện lợi\" width=\"810\" height=\"456\"></p><p>Touch ID được tích hợp vào MacBook Air mang đến độ bảo mật cao, an toàn tuyệt đối. Chỉ cần đặt ngón tay vào cảm biến Touch ID sẽ giúp máy tính MacBook Air mở khóa dễ dàng. Sử dụng vân tay để truy cập vào các tài liệu, ghi chú đồng thời thiết lập hệ thống đã khóa.</p><p>Bên cạnh đó,bạn cũng có thể sử dụng Apple Pay để thanh toán an toàn và tiện dụng khi mua sắm trực tuyến. Các thao tác nhập thông tin giao hàng hay hóa đơn, hay các chi tiết thẻ của bạn sẽ được bảo mật tuyệt đối.</p><h3><strong>Thunderbolt 3 giúp kết nối dễ dàng, thời lượng pin ấn tượng</strong></h3><p>MacBook Air M1 256GB 2020&nbsp;kết nối dễ dàng với các thiết bị bằng Thunderbolt. Đây là giao diện phần cứng được tận dụng cổng USB Type-C thuận nghịch mang đến đôi tốc độ gấp đôi so với người tiền nhiệm. Cổng còn hỗ trợ USB4, cho phép kết nối với nhiều thiết bị ngoại vi hơn, kể cả màn hình Apple Pro Display XDR có độ phân giải cao nhất là 6K.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/macbook/Air/M1-2020/macbook-air-2020-m1-1.jpg\" alt=\"Thunderbolt 3 giúp kết nối dễ dàng, thời lượng pin ấn tượng\" width=\"810\" height=\"456\"></p><p>Do sử dụng chip M1 rất ít tỏa nhiệt, nên máy rất tiết kiệm điện năng mang lại khả năng tối ưu pin và thời gian sử dụng. Bạn có thể thoải mái lướt web trong vòng 15 tiếng và 18 tiếng xem video mà không lo hết pin.</p><h2><strong>So sánh Macbook Air M1 2020 với Macbook Air M2</strong></h2><figure class=\"table\"><table><tbody><tr><td>&nbsp;</td><td><strong>MacBook Air M1 2020</strong></td><td><strong>Macbook Air M2 2022</strong></td></tr><tr><td>Giá niêm yết</td><td>28.990.000đ</td><td>32.990.000đ</td></tr><tr><td>Màn hình</td><td><p>13.3 inches</p><p>Retina Display</p><p>2560 x 1600 pixels (2K)</p><p>Tấm nền IPS</p></td><td><p>13 inches</p><p>Liquid Retina Display</p><p>2560 x 1664 pixels</p><p>Tấm nền IPS</p></td></tr><tr><td>CPU - GPU</td><td><p>Apple M1</p><p>GPU 7 nhân, 16 nhân Neural Engine</p></td><td><p>Apple M2</p><p>8 nhân GPU, 16 nhân Neural Engine</p></td></tr><tr><td>Thời lượng pin</td><td>49.9-watt-hour</td><td>52,6 Wh</td></tr><tr><td>Cổng giao tiếp</td><td>2 cổng Thunderbolt / USB 4</td><td>Cổng HDMI và đầu đọc thẻ SD, USB Type-C</td></tr></tbody></table></figure><p>Nhìn chung, với chênh lệch 200 USD Macbook Air M2 và M1 không có quá nhiều khác biệt, trừ con chip mạnh hơn. Tuy nhiên, trong các trải nghiệm cơ bản thì khó để nhận thấy sự khác biệt giữa 2 thế hệ, do đó với giá bán rẻ hơn thì&nbsp;Macbook Air M1 2020 vẫn là một lựa chọn sáng giá tại thời điểm này.</p><h2><strong>Macbook Air M1 2020 giá bao nhiêu?</strong></h2><p>Macbook Air 2020 được trang bị con chip M1 mới với giá bán khởi điểm tại thị trường Mỹ là 999 USD, trong đó phiên bản max cấu hình có giá lên tới 1449 USD, tương đương từ 25,000,000 đến 40,000,000 đồng tại Việt Nam.</p><p>Giá bán các phiên bản của Macbook Air M1 cụ thể như sau:</p><figure class=\"table\"><table><tbody><tr><td>Phiên bản</td><td>Giá bán</td></tr><tr><td>&nbsp;Macbook Air M1 8GB - 256GB</td><td>Chỉ từ 23.990.000 đồng</td></tr><tr><td>Macbook Air M1 16GB - 256GB</td><td>Chỉ từ 28.990.000 đồng</td></tr><tr><td>Macbook Air M1 8GB - 512GB</td><td>Chỉ từ 28.990.000 đồng</td></tr><tr><td>&nbsp;Macbook Air M1 16GB - 512GB</td><td>Chỉ từ 36.500.000 đồng&nbsp;</td></tr></tbody></table></figure><p>Lưu ý: Giá bán trong bài viết có thể thay đổi tùy theo trương trình khuyến mãi, để biết được giá bán chính xác thời điểm hiện tại, truy cập website CellphoneS để biết thêm thông tin chi tiết.</p><h2><strong>Mua Macbook Air M1 2020 chính hãng tại CellphoneS</strong></h2><p>Nếu bạn muốn mua&nbsp;Macbook Air 2020 M1&nbsp;chính hãng thì hãy nhanh tay liên hệ đến Hotline: 1800.2097 hay đến trực tiếp cửa hàng thuộc hệ thống CellphoneS để tiến hành đặt hàng với mức giá cạnh tranh nhất. Với chế độ bảo hành chính hãng tại trung tâm ủy quyền Apple Việt Nam lên đến 12 tháng, 1 đổi 1 trong 30 ngày nếu có lỗi từ nhà sản xuất, bạn có thể hoàn toàn an tâm khi mua sản phẩm <strong>Macbook Air M1</strong> tại CellphoneS.</p>\r\n', b'1', 'Apple MacBook Air M1', 'Phù hợp cho lập trình viên, thiết kế đồ họa 2D, dân văn phòng\r\nHiệu năng vượt trội - Cân mọi tác vụ từ word, exel đến chỉnh sửa ảnh trên các phần mềm như AI, PTS\r\nĐa nhiệm mượt mà - Ram 8GB cho phép v', 'apple-macbook-air-m1', NULL),
(14, b'0', 20, 'ASUS_TUF', NULL, NULL, '<h2><strong>Laptop Asus TUF Gaming F15 FX506HC-HN144W - Độc đáo từ thiết kế, mạnh mẽ ở hiệu năng</strong></h2><p><strong>Laptop Asus TUF Gaming F15 FX506HC-HN144W</strong>&nbsp;sở hữu thiết kế táo bạo và độc đáo bậc nhất trên thị trường với gam màu Graphite black nổi bật mà cực huyền bí. Với hiệu năng đỉnh cao và linh kiện cấu thành có chất lượng đứng đầu thị trường, chiếc <a href=\"https://cellphones.com.vn/laptop/asus/gaming.html\"><strong>laptop Asus Gaming</strong></a> này được kỳ vọng dễ dàng chinh phục mọi thử thách và đồng hành với các game thủ trên mọi đấu trường.</p><h3><strong>Vẻ ngoài mạnh mẽ mà cực huyền bí đến từ gam màu Graphite black</strong></h3><p>Những chiếc laptop dòng Gaming từ lâu đã mang vẻ ngoài đầy cuốn hút với những thiết kế mạnh mẽ và độc đáo. Không nằm ngoài xu hướng đó, những chiếc Laptop Asus TUF Gaming F15 FX506HC-HN144W cũng sở hữu vẻ ngoài cực ấn tượng đặc biệt là khi nó được khoác lên mình chiếc áo đượm màu huyền bí - Graphite black.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Laptop-Asus-TUF-Gaming-F15-FX506HC-HN144W-1.png\" alt=\"Laptop Asus TUF Gaming F15 FX506HC-HN144W\" width=\"810\" height=\"450\"></p><p>Những đường cắt đầy nghệ thuật trên lớp vỏ của chiếc <a href=\"https://cellphones.com.vn/laptop/do-hoa.html\"><strong>laptop thiết kế đồ họa</strong></a> này không chỉ đóng vai trò như sự trang trí cách điệu mà còn giúp chiếc máy ghi dấu ấn mạnh mẽ từ vẻ ngoài. Phần mặt dưới được thiết kế những hình tổ ong để chiếc máy được cố định trên mặt phẳng tốt hơn đồng thời có những khe tản nhiệt mảnh giúp tăng khả năng tản nhiệt cho máy.</p><p>Với vẻ ngoài độc đáo đầy tính nghệ thuật đương đại và tràn đầy khí thế, Asus TUF Gaming F15 FX506HC-HN144W được xem là đứa con cưng của Asus - kiệt tác mới trong làng công nghệ.</p><h3><strong>Tối ưu hiệu suất làm việc với cấu hình siêu khủng</strong></h3><p>Ông lớn Asus đã có màn đầu tư cực ngoạn mục cho đứa con cưng của mình khi trang bị cho nó bộ vi xử lý cao cấp đến từ Intel là Intel core i5. Với bộ vi xử lý này chiến binh đến từ nhà Asus có thể cân mọi tác vụ một cách nhẹ nhàng.</p><p>Bộ nhớ đệm khủng lên đến 8GB của Laptop Asus TUF Gaming F15 FX506HC-HN144W giúp tối ưu hóa trải nghiệm cho người dùng để bạn có những phút trải nghiệm với tốc độ vận hành vượt trội với chiếc Laptop này. Bên cạnh đó để đáp ứng việc chiến các tựa game đồ họa đỉnh cao như PUBG, LOL, War tank, GTA 5,... dòng laptop gaming đến từ nhà Asus đã được trang bị thêm VGA Geforce RTX. Nhờ card đồ họa cao cấp này mà bạn không bao giờ bị trễ hình ảnh game cũng như không gặp bất cứ vấn đề nào trong đa nhiệm các thao tác. Tốc độ quét nhanh cùng hiệu ứng hình ảnh chân thực là vũ khí sắc bén đồng hành cùng anh em game thủ trên mọi đấu trường.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Laptop-Asus-TUF-Gaming-F15-FX506HC-HN144W-2.png\" alt=\"Laptop Asus TUF Gaming F15 FX506HC-HN144W\" width=\"810\" height=\"450\"></p><p>Bộ nhớ cũng được xem là thế mạnh của dòng <a href=\"https://cellphones.com.vn/laptop/gaming.html\"><strong>laptop gaming</strong></a> này với dung lượng ROM lên đến 512GB đóng vai trò một thư viện tài liệu khổng lồ nơi bạn có thể lưu trữ bất cứ dữ liệu cần thiết nào cho bản thân.</p><p>Laptop Asus TUF Gaming F15 FX506HC-HN144W duy trì hiệu suất làm việc tối ưu bằng cách trang bị hệ thống tản nhiệt cao cấp. Nhờ hệ thống này mà chiếc laptop này không bao giờ gặp tình trạng treo, giật, lag do nóng máy. Từ đó ổn định năng suất xử lý cũng như nâng cao tuổi thọ của thiết bị.</p><h3><strong>Trải nghiệm từng khung hình với màn hình 15.6 inch Full HD</strong></h3><p>Laptop Asus TUF Gaming F15 FX506HC-HN144W đem đến trải nghiệm cực chân thực cho game thủ thông qua màn hình 15.6 inch có độ phân giải Full HD. Độ quét màn hình lên đến 144Hz mang đến khả năng hiển thị hình ảnh sắc nét và kịp thời. Dải màu rộng cùng khung hình lớn là thứ kích thích thị giác của người sử dụng kết hợp công nghệ chống chói giúp chiếc laptop nhà Asus có thể hoạt động tốt ngay cả khi ở môi trường chói sáng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Laptop-Asus-TUF-Gaming-F15-FX506HC-HN144W-3.png\" alt=\"Laptop Asus TUF Gaming F15 FX506HC-HN144W\" width=\"810\" height=\"450\"></p><p>Âm thanh cũng là một phần tạo nên những trải nghiệm khó quên với Laptop Asus TUF Gaming F15 FX506HC-HN144W. Được trang bị 2 loa thiết kế cắt táo bạo tạo nên những âm rung đầy mạnh mẽ. Âm thanh được phát ra kịp thời để nâng cao trải nghiệm sử dụng đồng thời đa dạng các chế độ tùy chỉnh để mang đến hệ thống âm chính xác tuyệt đối.</p><h3><strong>Kết nối đa chiều với hệ thống cổng kết nối đa dạng của Asus TUF Gaming F15 FX506HC</strong></h3><p>Trong thời đại công nghệ thông tin, việc chuyển đổi dữ liệu đa chiều là điều kiện bắt buộc để tối ưu hóa hiệu suất làm việc. Để làm được điều này, người ta thường tìm đến những chiếc laptop có nhiều cổng kết nối như Asus TUF Gaming F15 FX506HC</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Laptop-Asus-TUF-Gaming-F15-FX506HC-HN144W-4.png\" alt=\"Laptop Asus TUF Gaming F15 FX506HC-HN144W\" width=\"810\" height=\"450\"></p><p>Chiếc laptop này đang sở hữu trên mình các cổng kết nối như:</p><p>- 1 cổng RJ45 LAN</p><p>- 1 cổng Thunderbolt 4 support DisplayPort</p><p>- 3 cổng USB 3.2 Gen 1 Type-A</p><p>- 1 cổng 3.5mm Combo Audio Jack</p><p>Với đầy đủ khả năng kết nối mạng LAN qua dây cắm và kết nối wifi, Asus TUF Gaming F15 FX506HC là sự lựa chọn để có thể tối ưu mọi công việc.</p><h2><strong>Mua laptop Asus TUF Gaming F15 FX506HC-HN144W chính hãng tại CellphoneS</strong></h2><p>Ưu việt từ thiết kế đến hiệu suất giúp chiếc Laptop Asus TUF Gaming F15 FX506HC-HN144W trở thành kiệt tác mà không game thủ nào có thể từ chối. Sở hữu ngay những chiếc laptop Asus TUF Gaming F15 FX506HC chính hãng ngay tại CellphoneS để có mức giá tốt nhất trên thị trường cùng chế độ bảo hành hấp dẫn nhé.</p>\r\n', NULL, 'Laptop ASUS TUF Gaming F15 FX506HC-HN144W', 'Phù hợp cho lập trình viên, thiết kế đồ họa 2D, dân văn phòng\r\nHiệu năng vượt trội - Cân mọi tác vụ từ word, exel đến chỉnh sửa ảnh trên các phần mềm như AI, PTS\r\nĐa nhiệm mượt mà - Ram 8GB cho phép v', 'laptop-asus-tuf-gaming-f15-fx506hc-hn144w', '2023-10-30 00:24:34'),
(15, b'1', 21, 'HP_15_EG2065TX', NULL, NULL, '<h2><strong>Laptop HP Pavilion 15-EG2065TX 7C0Q3PA - mạnh mẽ và sang trọng</strong></h2><p>Laptop HP Pavilion 15-EG2065RX 7C0Q3PA là một trong những chiếc laptop đáng để người dùng sở hữu nhất bởi cấu hình mạnh mẽ trong phân khúc giá tầm trung. Vậy chiếc <a href=\"https://cellphones.com.vn/laptop/hp/pavilion.html\"><strong>laptop HP Pavilion</strong></a> này có gì đặc biệt, có đáng sở hữu không thì hãy cùng tìm hiểu ngay sau đây.&nbsp;</p><h3><strong>Sở hữu thiết kế sang trọng cùng màn hình mỏng</strong></h3><p>Laptop HP Pavilion 15-EG2065RX 7C0Q3PA được làm từ chất liệu cao cấp với lớp vỏ từ ALUp phủ màu bạc cho một vẻ ngoài sang trọng và hiện đại. Trọng lượng của sản phẩm cũng nhẹ chỉ 1.75 kg và rất mỏng giúp các bạn học sinh, sinh viên và những người làm văn phòng có thể dễ dàng đem theo.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/HP/Pavillion/laptop-hp-pavilion-15-eg2065tx-7c0q3pa-2.jpg\" alt=\"Đánh giá laptop HP Pavilion 15-EG2065TX 7C0Q3PA\" width=\"810\" height=\"456\"></p><p>Màn hình HP Pavilion 15-EG2065RX 7C0Q3PA có kích thước 15.6 inch với tấm nền IPS cùng độ phân giải FHD đem đến một trải nghiệm hình ảnh mượt mà. Thêm vào đó, sản phẩm được trang bị bàn phím fullsize và chuột cảm ứng đa điểm phục vụ tốt nhu cầu công việc của người dùng.</p><h3><strong>Cấu hình mạnh mẽ giúp phục vụ nhu cầu người dùng</strong></h3><p>Laptop HP Pavilion 15-EG2065RX 7C0Q3PA được tích hợp con chip Intel i5 10 nhân 12 luồng với mức xung nhịp 4.4GHz giúp sản phẩm có tốc độ xử lý nhanh chóng. Ngoài ra, với card đồ họa NVIDIA GeForce MX550 2GB GDDR6 giúp người dùng có thể thực hiện các tác vụ cơ bản mượt mà và trơn tru.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/HP/Pavillion/laptop-hp-pavilion-15-eg2065tx-7c0q3pa-1.jpg\" alt=\"Đánh giá laptop HP Pavilion 15-EG2065TX 7C0Q3PA\" width=\"810\" height=\"456\"></p><p>Với RAM là 8GB cùng ổ cứng SSD 256GB, sản phẩm sẽ đem lại một không gian lưu trữ khá thoải mái cho người sử dụng. Thiết bị cũng sở hữu đầy đủ các cổng kết nối cũng như kết nối tai nghe headphone và microphone.</p><h2><strong>Mua laptop HP Pavilion 15-EG2065RX 7C0Q3PA chính hãng, uy tín tại CellphoneS</strong></h2><p>Hiện laptop HP Pavilion 15-EG2065RX 7C0Q3PA đã có mặt tại các cửa hàng trong hệ thống CellphoneS. Để sở hữu sản phẩm chính hãng với mức giá ưu đãi nhất, quý khách hãy tới ngay địa chỉ chi nhánh gần nhất.</p>\r\n', NULL, 'Laptop HP Pavilion 15-EG2065TX 7C0Q3PA', 'Trải nghiệm màn hình sắc nét, chân thực - 15.6 inches Full HD, IPS, 250nits, 45% NTSC\r\nCard đồ họa NVIDIA® GeForce® MX550 - Mạnh hơn, nhanh hơn, hiệu suất hơn, game hạng nặng và đồ họa cao cấp không l', 'laptop-hp-pavilion-15-eG2065TX-7C0Q3PA', NULL),
(16, b'1', 23, 'SURFACE_PRO_9', NULL, NULL, '<h2><strong>Laptop Surface Pro 9 - Thao tác linh hoạt, cải tiến vi xử lý</strong></h2><p>Laptop Surface Pro 9 là dòng <a href=\"https://cellphones.com.vn/laptop/laptop-13-inch.html\"><strong>laptop 13 inch</strong></a> sở hữu thiết kế đẹp mắt, tính di động cao để thao tác sử dụng thêm linh hoạt. Surface Pro 9 còn mang đến hiệu suất mạnh mẽ, giúp xử lý nhanh mọi tác vụ và có thể giải trí được với game 3D.</p><h3><strong>Thiết kế linh hoạt, màn hình cảm ứng dùng tiện lợi</strong></h3><p>Laptop Surface Pro 9 được làm với kiểu dáng 2 trong 1, mỏng đẹp đầy tiện dụng để đảm bảo nhu cầu di chuyển thường xuyên của người dùng. Phần viền của máy được hoàn thiện tinh xảo, cho độ bền cứng cáp và tăng được sự hiện đại cho thiết bị.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Microsoft/laptop-surface-pro-9-2.jpg\" alt=\"Đánh giá Laptop Surface Pro 9\" width=\"810\" height=\"456\"></p><p>Đặc biệt, Surface Pro 9 sẽ phục vụ tốt cả nhu cầu dùng laptop và máy tính bảng cho bạn, nhờ có chân đế linh hoạt 180 độ tiên tiến. Do đó, bạn có thể đặt để máy sao cho phù hợp với góc quan sát để làm việc hiệu quả hơn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Microsoft/laptop-surface-pro-9-5.jpg\" alt=\"Đánh giá Laptop Surface Pro 9\" width=\"810\" height=\"456\"></p><p>Ngoài ra, màn hình của Surface Pro 9 là màn hình cảm ứng, nên bạn có thể viết và phát thảo với độ chính xác cao và phản hồi tự nhiên. Màn hình cũng có độ phân giải cao nên mọi chi tiết được hiển thị rõ nét và mượt mà trong chuyển động.</p><h3><strong>Hiệu năng mạnh mẽ với NPU và sử dụng thoải mái cả ngày dài</strong></h3><p>Laptop Surface Pro 9 mang trong mình một hiệu năng mạnh mẽ với bộ vi xử lý thần kinh NPU với kết nối tốc độ cao 5G, cho mọi vận hành đa tác vụ đều có được sự mượt mà. Bên trong laptop còn tích hợp hệ thống tản nhiệt cao cấp, nên máy luôn có được hiệu suất tốt mà tuổi thọ vẫn được được duy trì.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/laptop/Microsoft/laptop-surface-pro-9-1.jpg\" alt=\"Đánh giá Laptop Surface Pro 9\" width=\"810\" height=\"456\"></p><p>Ngoài ra với Surface Pro 9 tiến độ công việc của bạn sẽ không lo bị gián đoạn với thời lượng pin dùng được cho cả ngày dài, thoải mái. Máy còn có thêm công nghệ sạc Fast Charging, nên thời gian sạc nhanh hơn để nguồn năng lượng luôn dồi dào.</p><h2><strong>Mua ngay laptop Surface Pro 9 giá rẻ, chất lượng uy tín tại CellphoneS</strong></h2><p>Laptop Surface Pro 9 sẽ giúp bạn thao tác sử dụng linh hoạt, làm việc đạt hiệu quả cao. Do đó, đừng bỏ lỡ sản phẩm mới chất lượng này với giá rẻ tại cửa hàng CellphoneS nhé!</p>\r\n', NULL, 'Laptop Surface Pro 9', 'Trọng lượng máy nhẹ chỉ 879 g rất dễ cho vào túi xách, balo.\r\nMàn hình PixelSense Flow hỗ trợ cảm ứng với kích thước 13 inch, độ phân giải 2.8K cùng công nghệ hiển thị HDR Dolby Vision IQ, giúp tối ưu', 'laptop-surface-pro-9', NULL),
(17, b'1', 32, 'LG_UltraWide_29WQ600_29', NULL, NULL, '<h3><strong>Màn hình LG UltraWide 29WQ600 29 inch – Màn hình độ, dải màu sống động</strong></h3><p>Màn hình LG UltraWide 29WQ600 29 inch là sản phẩm màn hình chất lượng đến từ LG. Mẫu <a href=\"https://cellphones.com.vn/man-hinh/lg.html\"><strong>màn hình LG</strong></a> này được trang bị một kích thước siêu lớn cùng với đó là nhiều công nghệ hiển thị hỗ trợ trải nghiệm thị giác của người dùng.</p><h3><strong>Tấm nền IPS, dải màu 99% sRGB</strong></h3><p>LG UltraWide 29WQ600 29 inch được trang bị tấm nền IPS mang lại màu sắc với độ chính xác cao. Bên cạnh đó, thiết bị còn sở hữu độ bao phủ màu lên tới 99% sRGB giúp mang lại trải nghiệm thị giác với hình ảnh rõ nét, màu sắc chân thực.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/man-hinh/LG/29-inch/man-hinh-lg-ultrawide-29wq600-29-inch-1.jpg\" alt=\"Đánh giá màn hình LG UltraWide 29WQ600 29 inch\" width=\"810\" height=\"456\"></p><p>Màn hình sở hữu tốc độ phản hồi 1ms giúp tối ưu các trải nghiệm, đặc biệt là khi chơi game. Bên cạnh đó là công nghệ AMD FreeSync giúp các chuyển động có thể diễn ra mượt mà hơn, giảm thiểu tình trạng giật hình và xé hình.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/man-hinh/LG/29-inch/man-hinh-lg-ultrawide-29wq600-29-inch-4.jpg\" alt=\"Đánh giá màn hình LG UltraWide 29WQ600 29 inch\" width=\"810\" height=\"456\"></p><h3><strong>Kích thước 29 inch mang lại không giản hiển thị vượt trội</strong></h3><p>LG UltraWide 29WQ600 là mẫu màn hình UltraWide với kích thước lên tới 29 inch cùng tỉ lệ hiển thị 21:9. Với không gian hiển thị rộng, người dùng có thể làm cùng lúc được nhiều việc hơn mà không cần chuyển qua lại giữa các cửa sổ. Cùng với đó, người dùng có thể tùy chỉnh không gian hiển thị và làm việc đơn giản chỉ qua vài lần nhấp chuột.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/man-hinh/LG/29-inch/man-hinh-lg-ultrawide-29wq600-29-inch-2.jpg\" alt=\"Đánh giá màn hình LG UltraWide 29WQ600 29 inch\" width=\"810\" height=\"456\"></p><h3><strong>Âm thanh MaxxAudio vượt trội</strong></h3><p>Với LG UltraWide 29WQ600, không chỉ là một màn hình thông thường mà thiết bị còn được tích hợp loa chuẩn MaxxAudio. Với 2 loa âm thanh nổi 2 bên công suất 7W nhờ đó người dùng sẽ có những trải nghiệm âm thanh đắm chìm.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/man-hinh/LG/29-inch/man-hinh-lg-ultrawide-29wq600-29-inch-3.jpg\" alt=\"Đánh giá màn hình LG UltraWide 29WQ600 29 inch\" width=\"810\" height=\"456\"></p><h2><strong>Mua ngay màn hình LG UltraWide 29WQ600 tại CellphoneS</strong></h2><p>Màn hình LG UltraWide 29WQ600 29 inch với không gian hiển thị rộng rãi cùng nhiều công nghệ hình ảnh, bảo vệ mắt. Đây sẽ là một màn hình chất lượng có thể đáp ứng tốt nhiều nhu cầu sử dụng của người dùng.</p>\r\n', NULL, 'Màn hình LG UltraWide 29WQ600', 'Màn hình UltraWide với kích thước 29 inch, trang bị độ phân giải 2560 x 1080\r\nCông nghệ HDR10 cho giúp bạn thưởng thức nội dung với màu sắc ấn tượng\r\nTấm nên IPS và sRGB 99% hiển thị chính xác màu sắc', 'man-hinh-lg-ultrawide-29WQ600', NULL),
(18, b'1', 29, 'ASUS_VZ279HEG1R', NULL, NULL, '<h2><strong>Màn hình Asus VZ279HEG1R 27 inch - Thiết kế màn hình siêu mỏng cùng khả năng hiển thị cực sắc nét</strong></h2><p><strong>Màn hình Asus VZ279HEG1R 27 inch</strong> là thế hệ màn hình của thương hiệu Asus mới được cho ra mắt trong thời gian gần đây. Nổi bật với thiết kế màn hình siêu mỏng cùng chất lượng hiển thị sống động và trung thực trong từng khung hình, <a href=\"https://cellphones.com.vn/man-hinh/asus.html\"><strong>màn hình Asus</strong></a> với kích thước 27 inch sẽ là lựa chọn hoàn hảo, đáp ứng được toàn bộ nhu cầu về hình ảnh của người dùng.</p><h3><strong>Thiết kế viền màn hình siêu mỏng, tăng khả năng hiển thị</strong></h3><p>Dù là một thiết bị công nghệ cỡ lớn nhưng màn hình Asus VZ279HEG1R 27 inch lại sở hữu độ mỏng về thiết kế không hề thua kém bất kỳ các thiết bị smartphone nào. Với độ dày màn hình chỉ vỏn vẹn 7.5mm, thiết bị sẽ mang tới cho người dùng khả năng hiển thị hình ảnh lớn cùng chất lượng sắc nét hơn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Asus/man-hinh-asus-vz279heg1r-27-inch-1.jpg\" alt=\"Đánh giá màn hình Asus VZ279HEG1R 27 inch\" width=\"810\" height=\"456\"></p><p>Phần giá đỡ của màn hình được thiết kế dạng tròn, có diện tích tiếp xúc với mặt phẳng lớn, giúp VZ279HEG1R có thể đứng vững được trên nhiều bề mặt phẳng khác nhau.</p><h3><strong>Chất lượng hiển thị sắc nét, đạt tiêu chuẩn quốc tế</strong></h3><p>Màn hình Asus VZ279HEG1R 27 inch được trang bị tấm nền hiển thị IPS 27 inch với độ phân giải Full HD đem lại khả năng hiển thị sắc nét và cực kỳ lôi cuốn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Asus/man-hinh-asus-vz279heg1r-27-inch-2.jpg\" alt=\"Đánh giá màn hình Asus VZ279HEG1R 27 inch\" width=\"810\" height=\"456\"></p><p>Đối với những gamer thì đây là một thiết bị cực kỳ thích hợp cho những trò chơi hiện nay. Nhờ sở hữu độ trễ chỉ 1ms cùng tần số quét lên tới 75Hz, các game thủ sẽ được đắm chìm vào trong thế giới ảo, tăng trải nghiệm mượt mà trong quá trình chơi game.</p><h2><strong>Mua màn hình Asus VZ279HEG1R 27 inch chính hãng tại CellphoneS</strong></h2><p>Bên cạnh những ưu điểm vượt trội, màn hình VZ279HEG1R 27 inch còn sở hữu mức giá cực kỳ ưu đãi tại cửa hàng CellphoneS. Để nhận thêm thông tin hoặc đặt mua sản phẩm, bạn có thể liên hệ với CellphoneS chúng tôi qua số điện thoại 1800 2097 để được hỗ trợ và tư vấn.</p>\r\n', NULL, 'Màn hình ASUS VZ279HEG1R', 'Trải nghiệm không gian hiển thị vô cùng rộng lớn với kích thước màn hình đến 27 inches\r\nĐộ phân giải Full HD cho chất lượng hình ảnh rõ nét, chân thực cùng màu sắc sống động\r\nĐộ trễ chỉ 1 ms kết hợp c', 'man-hinh-asus-VZ279HEG1R', NULL),
(19, b'1', 30, 'Monitor_S2422HG_24', NULL, NULL, '<h2><strong>Màn hình Asus VZ279HEG1R 27 inch - Thiết kế màn hình siêu mỏng cùng khả năng hiển thị cực sắc nét</strong></h2><p><strong>Màn hình Asus VZ279HEG1R 27 inch</strong> là thế hệ màn hình của thương hiệu Asus mới được cho ra mắt trong thời gian gần đây. Nổi bật với thiết kế màn hình siêu mỏng cùng chất lượng hiển thị sống động và trung thực trong từng khung hình, <a href=\"https://cellphones.com.vn/man-hinh/asus.html\"><strong>màn hình Asus</strong></a> với kích thước 27 inch sẽ là lựa chọn hoàn hảo, đáp ứng được toàn bộ nhu cầu về hình ảnh của người dùng.</p><h3><strong>Thiết kế viền màn hình siêu mỏng, tăng khả năng hiển thị</strong></h3><p>Dù là một thiết bị công nghệ cỡ lớn nhưng màn hình Asus VZ279HEG1R 27 inch lại sở hữu độ mỏng về thiết kế không hề thua kém bất kỳ các thiết bị smartphone nào. Với độ dày màn hình chỉ vỏn vẹn 7.5mm, thiết bị sẽ mang tới cho người dùng khả năng hiển thị hình ảnh lớn cùng chất lượng sắc nét hơn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Asus/man-hinh-asus-vz279heg1r-27-inch-1.jpg\" alt=\"Đánh giá màn hình Asus VZ279HEG1R 27 inch\" width=\"810\" height=\"456\"></p><p>Phần giá đỡ của màn hình được thiết kế dạng tròn, có diện tích tiếp xúc với mặt phẳng lớn, giúp VZ279HEG1R có thể đứng vững được trên nhiều bề mặt phẳng khác nhau.</p><h3><strong>Chất lượng hiển thị sắc nét, đạt tiêu chuẩn quốc tế</strong></h3><p>Màn hình Asus VZ279HEG1R 27 inch được trang bị tấm nền hiển thị IPS 27 inch với độ phân giải Full HD đem lại khả năng hiển thị sắc nét và cực kỳ lôi cuốn.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Asus/man-hinh-asus-vz279heg1r-27-inch-2.jpg\" alt=\"Đánh giá màn hình Asus VZ279HEG1R 27 inch\" width=\"810\" height=\"456\"></p><p>Đối với những gamer thì đây là một thiết bị cực kỳ thích hợp cho những trò chơi hiện nay. Nhờ sở hữu độ trễ chỉ 1ms cùng tần số quét lên tới 75Hz, các game thủ sẽ được đắm chìm vào trong thế giới ảo, tăng trải nghiệm mượt mà trong quá trình chơi game.</p><h2><strong>Mua màn hình Asus VZ279HEG1R 27 inch chính hãng tại CellphoneS</strong></h2><p>Bên cạnh những ưu điểm vượt trội, màn hình VZ279HEG1R 27 inch còn sở hữu mức giá cực kỳ ưu đãi tại cửa hàng CellphoneS. Để nhận thêm thông tin hoặc đặt mua sản phẩm, bạn có thể liên hệ với CellphoneS chúng tôi qua số điện thoại 1800 2097 để được hỗ trợ và tư vấn.</p>\r\n', NULL, 'Màn hình cong Dell Gaming Monitor S2422HG ', 'Thiết kế màn hình cong kích thước 23.6 inches mang đến không gian trải nghiệm bất tận\r\nCông nghệ AMD FreeSync™ Premium giúp giảm thiểu tối đa tình trạng xé hình, giật ảnh\r\nChiến game cực đã với tần số', 'man-hinh-cong-dell-gaming-monitor-S2422HG ', NULL);
INSERT INTO `_product` (`id`, `active`, `category_id`, `code`, `created_by`, `created_date`, `description`, `is_show_home`, `name`, `short_description`, `slug`, `updated_date`) VALUES
(20, b'1', 31, 'LS34C500GAEXXV', NULL, NULL, '<h2><strong>Màn hình Samsung LS34C500GAEXXV 34 inch - Hình ảnh mượt mà đẹp mắt</strong></h2><p>Màn hình Samsung LS34C500GAEXXV 34 inch là siêu phẩm dành cho dân văn phòng, với tỷ lệ khung hình 21:9 tạo không gian hình ảnh rộng rãi ấn tượng. Màn hình đủ lớn giúp bạn quản lý dữ liệu dễ dàng, hiển thị đầy đủ thông tin với các thao tác dễ dàng. Hãy xem đoạn mô tả sau đây để hiểu rõ về <a href=\"https://cellphones.com.vn/man-hinh/samsung.html\"><strong>màn hình Samsung</strong></a> kích thước lớn này.</p><h3><strong>Hiển thị trọn vẹn hình ảnh, mở rộng góc nhìn</strong></h3><p>Màn hình Samsung LS34C500GAEXXV 34 inch được tích hợp độ phân giải 3440 x 1440 pixels với mật độ điểm ảnh cao. Nhờ vậy hình ảnh được hiển thị rất rõ ràng với màu sắc sống động tự nhiên trong từng khoảnh khắc.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Samsung/34-inch/man-hinh-samsung-ls34c500gaexxv-34-inch-2.jpg\" alt=\"Đánh giá màn hình Samsung LS34C500GAEXXV 34 inch\" width=\"810\" height=\"456\"></p><p>Màn hình được trang bị dải màu rộng lên tới&nbsp;16.7 triệu màu cùng công nghệ&nbsp;HDR10.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Samsung/34-inch/man-hinh-samsung-ls34c500gaexxv-34-inch-1.jpg\" alt=\"Đánh giá màn hình Samsung LS34C500GAEXXV 34 inch\" width=\"810\" height=\"456\"></p><h3><strong>Trải nghiệm mượt mà, bảo vệ mắt tối đa</strong></h3><p>Công nghệ AMD Freesync được tích hợp vào màn hình Samsung LS34C500GAEXXV có khả năng tự động đồng bộ hóa tần số làm tươi mới màn hình. Nhờ vậy thiết bị có thể giảm thiểu tình trạng rách hình, hạn chế tối đa độ trễ đầu vào khi chơi game.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Samsung/34-inch/man-hinh-samsung-ls34c500gaexxv-34-inch-3.jpg\" alt=\"Đánh giá màn hình Samsung LS34C500GAEXXV 34 inch\" width=\"810\" height=\"456\"></p><p>Màn hình được tích hợp thêm công nghệ Flicker Free giúp loại bỏ tình trạng nhấp nháy khó chịu. Đôi mắt của bạn sẽ luôn cảm thấy dễ chịu, không bị mỏi mắt dù làm việc lâu dài trước màn hình.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Man-hinh/Samsung/34-inch/man-hinh-samsung-ls34c500gaexxv-34-inch-4.jpg\" alt=\"Đánh giá màn hình Samsung LS34C500GAEXXV 34 inch\" width=\"810\" height=\"456\"></p><h2><strong>Mua ngay màn hình Samsung LS34C500GAEXXV 34 inch chính hãng giá tốt tại cửa hàng CellphoneS</strong></h2><p>Samsung LS34C500GAEXXV 34 inch đã có mặt tại hệ thống bán lẻ CellphoneS, giá cực tốt và bảo hành đầy đủ. Nếu bạn muốn nâng cấp chiếc màn hình cũ thì hãy thử trải nghiệm màn ảnh cực chất của Samsung này nhé!&nbsp;</p>\r\n', NULL, 'Màn hình Samsung LS34C500GAEXXV', 'Khai phóng tầm nhìn với tỉ lệ màn hình 21:9, thưởng thức cực đỉnh mọi nội dung\r\nKích thước 34 inch, độ phân giải Ultra WQHD hiển thị sắc nét, bắt trọn mọi chi tiết\r\nCông nghệ HDR10 vượt trội, tái hiện', 'man-hinh-samsung-LS34C500GAEXXV', NULL),
(21, b'1', 53, 'TV_Xiaomi_Pro_65', NULL, NULL, '<h2><strong>Google Tivi Xiaomi A Pro 65 inch 4K UHD có điểm gì nổi trội đáng mua</strong></h2><p>Google Tivi Xiaomi A Pro 65 inch 4K UHD với giá bán hấp dẫn trong phân khúc. Vậy mẫu tivi thông minh này còn những điểm gì nổi trội so với sản phẩm cùng phân khúc đáng để người dùng trải nghiệm?</p><ul><li><strong>Hình ảnh 4K sắc nét, dải màu rộng</strong>: Hiển thị hình ảnh sống động, mãn nhãn trong các trải nghiệm thị giác</li><li><strong>Thiết kế cao cấp không viền cùng khung kim loại</strong>: Sang trọng, phù hợp với nhiều không gian nhà</li><li><strong>Âm thanh&nbsp;Dolby Audio, DTS-X và DTS Virtual:X</strong> Sống động đậm chất điện ảnh như ngoài rạp chiếu</li><li><strong>Nền tảng Google TV</strong>: Kho ứng dụng và giải trí đa dạng</li><li><strong>Điều khiển thông minh với trợ lý Google</strong></li></ul><p>Bên cạnh những điểm nổi bật trên thì khách hàng mua&nbsp;Xiaomi A Pro 65 inch 4K UHD trong thời gian<strong> từ 11.08.23 - 31.08.23</strong>sẽ được nhận thêm <strong>ưu đãi giảm giá</strong>vô cùng hấp dẫn trị giá<strong> tới 1.5 triệu đồng.</strong></p><h2><strong>Google Tivi Xiaomi A Pro 65 inch 4K UHD - Sống động và đắm chìm trong từng khung hình</strong></h2><p><strong>Google Tivi Xiaomi A Pro 65 inch 4K UHD </strong>đang là sản phẩm smart tivi được quan tâm bậc nhất hiện tại. Nhờ có thiết kế thông minh cùng nhiều cải tiến đáng chú ý mà chiếc <a href=\"https://cellphones.com.vn/tivi/xiaomi.html\"><strong>tivi Xiaomi</strong></a> này mang đến cảm giác đắm chìm để người sử dụng được tận hưởng trọn vẹn từng phút giây.</p><h3><strong>Đắm chìm trong hình ảnh chi tiết chuẩn 4K</strong></h3><p>Google Tivi Xiaomi A Pro 65 inch 4K UHD là những chiếc tivi công nghệ mới đến từ Xiaomi và được hãng này áp dụng chuẩn hiển thị chất lượng cao. Theo đó, sản phẩm này có độ phân giải 4K chuẩn UHD mang đến những hình ảnh sống động và cực kỳ chi tiết. Công nghệ Dolby Vision theo chuẩn điện ảnh cũng đóng góp công sức để giúp hình ảnh ngập tràn ánh sáng, độ tương phản và màu sắc rực rỡ.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tivi/Xiaomi/tivi-xiaomi-a-pro-65-inch-4k-2.jpg\" alt=\"Google Tivi Xiaomi A Pro 65 inch 4K UHD - Sống động và đắm chìm trong từng khung hình\" width=\"810\" height=\"456\"></p><p>Với dải màu chuẩn DCI-P3 những chiếc tivi Google Tivi Xiaomi A Pro 65 inch 4K UHD có khả năng tái hiện chân thực nhất các gam màu theo đúng ý tưởng của những nhà sáng tạo. Dải màu siêu rộng mang đến những chuẩn màu chính xác cùng độ đậm nhạt và sắc thái rực rỡ của khung hình.&nbsp;</p><p>Áp dụng khả năng ước tính chuyển động (MEMC) chiếc smart tivi đến từ Xiaomi mang đến những đoạn video mượt mà cùng tốc độ chuyển động cao. Đây cũng chính là chìa khóa mở ra khả năng cân đẹp các tựa game đồ họa cao, chuyển động nhanh cho Google Tivi Xiaomi A Pro 65 inch 4K UHD.</p><h3><strong>Âm thanh đa chiều, mạnh mẽ, sống động như ngoài rạp</strong></h3><p>Google Tivi Xiaomi A Pro 65 inch 4K UHD sở hữu bộ đôi loa 12W cùng công nghệ giải mã kép Dolby Audio™ + DTS-X mang đến những âm thanh đa chiều. Nhờ sự kết hợp hoàn hảo ở cả hình ảnh và âm thanh mà những trải nghiệm trên chiếc tivi này cực kỳ đắm chìm.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tivi/Xiaomi/tivi-xiaomi-a-pro-65-inch-4k-4.jpg\" alt=\"Google Tivi Xiaomi A Pro 65 inch 4K UHD - Sống động và đắm chìm trong từng khung hình\" width=\"810\" height=\"456\"></p><p>Công nghệ DTS Virtual:X với sự cải tiến mạnh mẽ trong âm thanh cũng giúp những chiếc tivi đến từ Xiaomi được chân thực hơn. Hệ thống âm vang từ mọi hướng tác động cùng lúc vào nhiều giác quan để mọi cảm xúc được dâng trào. Hệ thống âm mạnh mẽ không chỉ mang đến sự thực tế trong trải nghiệm mà còn giúp hoạt động giải trí thêm phần chuyên nghiệp như đang ở ngoài rạp chiếu phim.</p><h3><strong>Thiết kế thông minh, định hướng tương lai</strong></h3><p>Thiết kế của Google Tivi Xiaomi A Pro 65 inch 4K UHD mang màu sắc hoàn toàn mới với các đường nét tinh xảo định hướng trải nghiệm của người dùng. Viên siêu mỏng của chiếc tivi này tạo nên cảm giác vô cực cho màn hình, ngay khi được khởi động, google tivi Xiaomi xóa tan ranh giới giữa hình ảnh trên tivi với thực tế bởi rất khó để người dùng có thể nhận ra viền tivi ở đâu. Điều này mang đến sự chân thực khi xem phim và giải trí bằng chiếc tivi này từ đó người dùng tận hưởng tối đa và sống trọn khoảnh khắc cùng Google Tivi Xiaomi A Pro 65 inch 4K UHD.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tivi/Xiaomi/tivi-xiaomi-a-pro-65-inch-4k-1.jpg\" alt=\"Google Tivi Xiaomi A Pro 65 inch 4K UHD - Sống động và đắm chìm trong từng khung hình\" width=\"810\" height=\"456\"></p><p>Thiết kế kim loại với phần khung nguyên khối tạo nên chuẩn mực mới cho cái đẹp, mang đến sự hiện đại cho tổng thể tivi. Nhờ có những bứt phá, và sản tạo trong thiết kế, Google Tivi Xiaomi A Pro 65 inch 4K UHD dễ dàng hòa hợp với bất cứ không gian nội thất nào và điều quan trọng nhất là nó luôn là điểm nhấn không thể bỏ qua trong căn nhà.</p><h3><strong>Trợ lý ảo Google - nhiều hơn là giải trí thông thường</strong></h3><p>Smart tivi đến từ Xiaomi còn sở hữu một đặc điểm mà chắc hẳn đang là xu thế của thị trường. Google Tivi Xiaomi A Pro 65 inch 4K UHD được trang bị trợ lý ảo google để tối ưu hơn các hoạt động giải trí cũng như hỗ trợ các thao tác xử lý trên tivi.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tivi/Xiaomi/tivi-xiaomi-a-pro-65-inch-4k-3.jpg\" alt=\"Tivi Xiaomi A Pro 65 inch 4K\" width=\"810\" height=\"456\"></p><p>Google TV có thể tập hợp và để cử các bộ phim, chương trình tivi, âm nhạc,... phù hợp với người dùng thông qua thông tin được cung cấp bởi các ứng dụng đã được đăng ký của người dùng. Sự sắp xếp và đề xuất được dành riêng cho mỗi cá nhân mang đến sự tối ưu cho nhu cầu cá nhân và người xem có thể dễ dàng tìm kiếm các nội dung phù hợp trong mục đề xuất dựa trên các thông tin đã được đăng ký, xem trước và có sẵn.</p><p>Bên cạnh khả năng đề xuất và sắp xếp, Google Tivi Xiaomi A Pro 65 inch 4K UHD còn hỗ trợ người dùng thông qua việc điều khiển tivi bằng giọng nói. Theo đó chỉ cần sử dụng “Ok google” và người dùng có thể tìm kiếm mọi thông tin bằng giọng nói của mình. Điều này sẽ giúp tối ưu việc điều khiển chiếc smart tivi này để đem đến những trải nghiệm thuận lợi hơn cho người dùng.</p><h2><strong>Mua Google Tivi Xiaomi A Pro 65 inch 4K UHD chính hãng tại CellphoneS</strong></h2><p>Sự cải tiến trong thiết kế cùng những công nghệ mới được ứng dụng trong chiếc tivi Google Tivi Xiaomi A Pro 65 inch 4K UHD đã giúp nó đáp ứng được tối đa nhu cầu giải trí của người dùng. Nhờ có độ phân giải cao cùng nhiều tính năng mới mẻ, những chiếc tivi đến từ Xiaomi thực sự đã tạo ra bước tiến mới thỏa mãn tối đa nhu cầu giải trí của người dùng. Để sở hữu những chiếc Google Tivi Xiaomi A Pro 65 inch 4K UHD chính hãng với hàng ngàn ưu đãi hấp dẫn hãy đến ngay CellphoneS nhé.</p>\r\n', NULL, 'Tivi Xiaomi A Pro', 'Độ phân giải 4K HDR mang lại hình ảnh rõ ràng và chính xác.\r\nThiết kế kim loại nguyên khối tinh tế vô cùng sang trọng, đẹp mắt.\r\nÂm thanh nổi công suất cao, hỗ trợ giải mã âm thanh DTS.\r\nĐiều khiển th', 'tivi-xiaomi-a-pro', NULL),
(22, b'1', 41, 'AirPods', NULL, NULL, '<p><i>Vừa qua, Apple đã chính thức cho ra mắt chiếc tai nghe <strong>Apple Airpods 2</strong>&nbsp;sở hữu <strong>chip H1</strong> được dành riêng giúp chuyển nhanh các cuộc gọi từ iPhone sang Airpods&nbsp;cũng như giảm mức tiêu thụ điện năng cực kỳ thấp. <strong>Thời gian</strong> sử dụng đến <strong>5 giờ nghe nhạc</strong> hoặc <strong>3 giờ đàm thoại</strong> và khi kết hợp với hộp sạc cho thời gian đến 24 giờ. Kết nối không dây chất lượng cao, tự động bật và luôn kết nối giúp sẵn sàng theo bạn đến bất kỳ đâu. Cùng tham khảo thêm về chiếc Airpod 2 này nhé.</i></p><h2><strong>Đánh giá tai nghe Apple AirPods 2 – Thiết kế tối giản, âm thanh tuyệt vời</strong></h2><h3><strong>AirPods 2 chính hãng VN/A là gì? Vì sao nên lựa chọn?</strong></h3><p>Đầu tiên, AirPods 2 chính hãng VN/A là hàng chính hãng do Apple sản xuất theo tiêu chuẩn của thị trường Việt Nam. Thiết bị được phân phối chính hãng thông qua các đại lý ủy quyền của Apple. Vậy tai nghe bluetooth Apple AirPods 2 chính hãng VN/A có gì khác những mẫu tai nghe cũ, tai nghe xách tay?</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-1.jpg\" alt=\"Tai nghe AirPods 2 VN/A là gì? Vì sao khách hàng nên chọn tai nghe bluetooth Apple AirPods 2 VN/A\" width=\"810\" height=\"456\"></p><p><a href=\"https://dienthoaivui.com.vn/tai-nghe-bluetooth-apple-airpods-2/\"><strong>Tai nghe Apple AirPods 2</strong></a> chính hãng VN/A là hàng mới nên chất lượng hoàn toàn được đảm bảo. Ngoài ra, khi mua tai nghe bluetooth Apple AirPods 2 VN/A khách hàng sẽ được hưởng gói bảo hành ưu đãi, cụ thể được đổi mới trong vòng 15 ngày tại CellphoneS, bảo hành 1 năm tại các trung tâm bảo hành ủy quyền của Apple tại Việt Nam. Khi mua máy khách hàng cũng được cung cấp đầy đủ hóa đơn chứng minh nguồn gốc cũng như phụ kiện kèm theo.</p><h3><strong>Dung lượng pin lớn, sử dụng bền bỉ</strong></h3><p>AirPods 2 chính hãng VN/A có dung lượng pin khá ấn tượng. Tai nghe cho 5 giờ chơi nhạc và 3 giờ đàm thoại. Ngoài ra, hộp sạc cũng sở hữu tính năng tính pin, cho thêm 24 giờ sử dụng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-4.jpg\" alt=\"Tai nghe AirPods 2 chính hãng VN/A phiên bản sạc thường sở hữu dung lượng pin lớn\" width=\"810\" height=\"456\"></p><p>AirPods 2 có 2 phiên bản: sạc không dây và sạc có dây (sạc thường). Phiên bản chúng ta đang nói đến là phiên bản sạc thường. Tuy nhiên, <strong>tai nghe AirPods 2</strong> sạc có dây ngoài cách thức sạc pin, tai nghe không có quá nhiều khác biệt so với phiên bản sạc không dây.</p><h3><strong>Thiết kế nhỏ gọn, bắt mắt</strong></h3><p>Về cơ bản <a href=\"https://cellphones.com.vn/thiet-bi-am-thanh/tai-nghe/tai-nghe-bluetooth.html\">tai nghe bluetooth</a>&nbsp;Apple AirPods 2 vẫn sở hữu thiết kế thời trang và nhỏ gọn, trọng lượng 4g cũng rất nhẹ không khác mấy so với tai nghe AirPods thế hệ đầu tiên. Nó cũng vẫn giữ nguyên thiết kế với màu trắng đặc trưng cho các dòng tai nghe. Đây là tai nghe wireless 100% và có khả năng tích hợp với mọi thiết bị Apple khác nhau nên bạn có thể linh hoạt sử dụng.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-2.jpg\" alt=\"Tai nghe AirPods 2 vẫn giữ thiết kế nhỏ gọn, bắt mắt của từ Airpods 1\" width=\"810\" height=\"456\"></p><p>Thêm vào đó, chúng còn được phủ lên thêm một lớp chất liệu mới ở đầu mỗi tai nghe để giúp tai nghe được bám hơn trên tai người đeo, tương tự như lớp phủ mặt kính mờ trên mặt lưng của chiếc Pixel 3 mà Google đã trang bị cho chiếc điện thoại của mình.</p><h3><strong>Chip H1cho khả năng kết nối nhanh hơn, mở Siri bằng giọng nói</strong></h3><p>Tai nghe Apple AirPods 2 trang bị chip H1 được hy vọng sẽ giúp kết nối ổn định hơn, mượt mà hơn thế hệ tiền nhiệm, cho người dùng nhiều trải nghiệm tốt hơn. Do đó, thay vì sử dụng chip W1 như các thế hệ sản phẩm cũ thì thế hệ mới 2019 lại được thêm vào chip H1 mạnh mẽ giúp giảm thiểu thời gian chuyển đổi giữa hai thiết bị và gia tăng thời gian đàm thoại lên đến 5 giờ liên tục.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-7.jpg\" alt=\"Chip H1 cho khả năng kết nối nhanh hơn\" width=\"810\" height=\"456\"></p><p>Trước đây, người dùng phải chạm hai lần vào tai nghe để kích hoạt trợ lý ảo Siri thì bây giờ tai nghe <strong>Airpods 2</strong> đã có sự cải tiến khi bạn chỉ cần thu âm giọng nói là có thể mở Siri chờ lệnh ngay. Tính năng này giúp bạn có thể dễ dàng điều chỉnh tai nghe mà không cần thao tác quá nhiều. Điều này cho phép người dùng có thể tương tác với Siri khi iPhone để trong túi quần.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-5.jpg\" alt=\"mở Siri bằng giọng nói\" width=\"810\" height=\"456\"></p><p>&gt;&gt;&gt;Xem thêm: <a href=\"https://cellphones.com.vn/apple-airpods-3.html\">Tai nghe Apple Airpods 3</a> mới nhất sắp được ra mắt trong thời gian sắp tới.</p><h3><strong>Tính năng chống ồn vượt trội</strong></h3><p>Một trong những tính năng được Apple nâng cấp trên Airpods 2 chính là khả năng chống ồn. Theo báo cáo từ công ty phân tích Barclays, tai nghe AirPods có khả năng chống ồn, xử lý âm thanh bên ngoài tốt hơn so với tai nghe AirPods đời đầu với khả năng chống ồn còn hạn chế. Bên cạnh đó, tai nghe AirPods 2 có thể sẽ được Apple sử dụng công nghệ chống ồn vật lý, khác với chống ồn điện từ như những tai nghe headphone. Với khả năng chống ồn tốt hơn này, tai nghe AirPods hứa hẹn sẽ cho ra chất âm tuyệt vời và cải thiện khoảng cách kết nối giữa các thiết bị.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-6.jpg\" alt=\"chống ồn tốt\" width=\"810\" height=\"456\"></p><h2><strong>Giá Airpod 2 là bao nhiêu?</strong></h2><p>Bên cạnh thiết kế, hiệu năng, chất âm và tính năng thì giá tai nghe Airpod 2 được không ít người dùng quan tâm. Ra mắt vào năm 2019, sản phẩm tạo ấn tượng tốt bởi nâng cấp vượt trội so với thế hệ tiền nhiệm. Cho đến nay, thiết bị Airpod 2 giá rẻ hơn, giảm nhiều hơn, nhờ vậy mà người dùng có thể dễ dàng sở hữu. Cụ thể</p><figure class=\"table\"><table><tbody><tr><td><strong>Phiên bản</strong></td><td><strong>Giá bán</strong></td></tr><tr><td>AirPods 2 đi kèm vỏ sạc không dây Qi thời điểm ra mắt</td><td>199 USD (khoảng 4.7 triệu đồng)</td></tr><tr><td>AirPods 2 đi kèm hộp sạc thường thời điểm ra mắt</td><td>159USD (khoảng 3.7 triệu đồng)</td></tr><tr><td>AirPods 2 VN/A chính hãng mới nhất</td><td>chỉ từ 2.950.000 đồng</td></tr></tbody></table></figure><p>Như có thể thấy, hiện tại Airpod 2 chỉ còn giá khoảng 2.95 triệu đồng. Với nhiều đợt ưu đãi khác nhau của CellphoneS, giá còn có thể thấp hơn nữa. Bạn hoàn toàn có thể rinh ngay với chi phí thấp nhất.</p><p><i>Lưu ý giá thiết bị mới nhất có thể thay đổi tuỳ theo thời điểm khuyến mãi và chương trình khác nhau.</i></p><p>Sản phẩm Airpods thế hệ 2 này nhỉnh hơn so với giá Airpod 1. Tuy nhiên xét về toàn diện và cải tiến công nghệ, đây vẫn là sản phẩm đáng mua trong phân khúc.</p><p><img src=\"https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:80/plain/https://cellphones.com.vn/media/wysiwyg/Tai-nghe/Apple/Airpods-2/apple-airpods-2-3.jpg\" alt=\" Tai nghe Bluetooth Apple AirPods 2 VN/A\" width=\"810\" height=\"456\"></p><h2><strong>Một số câu hỏi liên quan về Apple Airpods 2</strong></h2><h3><strong>Apple Airpods 2 có chống nước hay không?</strong></h3><p>Airpods thế hệ thứ 2 lần này sẽ không được trang bị khả năng chống nước và chống thấm nước. Vì thế, cần hạn chế dính nước và mô hôi của các hoạt động thể thao làm ảnh hưởng đến chất lượng sản phẩm.</p><h3><strong>Airpods 2 có chống ồn không?</strong></h3><p>Hiện tại, sản phẩm tai nghe Apple Airpod 2 vẫn chưa được trang bị khả năng chống ồn.</p><h2><strong>Mua tai nghe AirPods 2 chính hãng Apple, giá tốt tại CellphoneS</strong></h2><p>Bạn muốn sở hữu chiếc tai nghe Apple thế hệ mới, hãy cùng ghé qua CellphoneS để đặt mua<strong>&nbsp;AirPods 2 chính hãng</strong>&nbsp;với mức giá và nhiều ưu đãi hấp dẫn như bảo hành 12 tháng tại tất cả các trung tâm bảo hành của Apple tại Việt Nam, đổi mới trong 15 ngày tại CellphoneS nếu có lỗi nhà sản xuất. Khi đến với CellphoneS, khách hàng sẽ được đảm bảo sản phẩm chính hãng. Vậy còn chần chừ gì mà không đến ngay hệ thống cửa hàng CellphoneS, để chọn cho mình một chiếc tai nghe ưng ý.</p>\r\n', NULL, 'Tai nghe Bluetooth Apple AirPods | Chính hãng Apple Việt Nam', 'Phản hồi nhanh hơn và tiết kiệm năng lượng nhờ vào con chip Apple H1\r\nThiết kế sang trọng, gọn nhẹ tạo cảm giác thoải mái khi đeo hàng giờ liền\r\nTích hợp 2 micro khử tiếng ồn cho chất lượng âm thanh t', 'tai-nghe-bluetooth-apple-airpods-chinh-hang-apple-viet-nam', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_product_detail`
--

CREATE TABLE `_product_detail` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `color_product_id` bigint(20) DEFAULT NULL,
  `cost` decimal(19,2) DEFAULT NULL,
  `percent_discount` int(11) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_product_detail`
--

INSERT INTO `_product_detail` (`id`, `active`, `color_product_id`, `cost`, `percent_discount`, `price`, `product_id`, `quantity`, `size`) VALUES
(10, b'1', 2, 1000000.00, 0, 35990000.00, 9, 10, '12GB - 1TB'),
(11, b'1', 2, 1000000.00, 0, 25590000.00, 9, 10, '12GB - 512GB'),
(12, b'1', 2, 1000000.00, 0, 21790000.00, 9, 10, '8GB - 256GB'),
(13, b'1', 3, 1000000.00, 0, 21790000.00, 9, 10, '12GB - 1TB'),
(14, b'1', 3, 1000000.00, 0, 25590000.00, 9, 10, '12GB - 512GB'),
(15, b'1', 3, 1000000.00, 0, 21790000.00, 9, 10, '8GB - 256GB'),
(16, b'1', 4, 1000000.00, 0, 21790000.00, 9, 10, '12GB - 1TB'),
(17, b'1', 4, 1000000.00, 0, 25590000.00, 9, 10, '12GB - 512GB'),
(18, b'1', 4, 1000000.00, 0, 21790000.00, 9, 10, '8GB - 256GB'),
(19, b'1', 5, 1000000.00, 0, 21790000.00, 9, 10, '12GB - 1TB'),
(20, b'1', 5, 1000000.00, 0, 25590000.00, 9, 10, '12GB - 512GB'),
(21, b'1', 5, 1000000.00, 0, 21790000.00, 9, 2, '8GB - 256GB'),
(22, b'1', 6, 1000000.00, 10, 40490000.00, 10, 94, '512GB'),
(23, b'1', 6, 1000000.00, 20, 34990000.00, 10, 102, '256GB'),
(24, b'1', 7, 1000000.00, 30, 40490000.00, 10, 101, '512GB'),
(25, b'1', 7, 1000000.00, 40, 34990000.00, 10, 101, '256GB'),
(26, b'1', 8, 1000000.00, 10, 40490000.00, 10, 105, '512GB'),
(27, b'1', 8, 1000000.00, 15, 34990000.00, 10, 106, '256GB'),
(28, b'1', 9, 1000000.00, 25, 40490000.00, 10, 107, '512GB'),
(29, b'1', 9, 1000000.00, 30, 34990000.00, 10, 108, '256GB'),
(30, b'1', 10, 1000000.00, 0, 8490000.00, 11, 100, 'Note 12 Pro 5G'),
(31, b'1', 10, 1000000.00, 0, 7190000.00, 11, 100, 'Note 12 Pro 4G'),
(32, b'1', 10, 1000000.00, 0, 5890000.00, 11, 100, 'Note 12S'),
(33, b'1', 10, 1000000.00, 0, 4990000.00, 11, 100, 'Note 12 8GB 128GB'),
(34, b'1', 10, 1000000.00, 0, 4290000.00, 11, 100, 'Note 12 4GB 128GB'),
(35, b'1', 11, 1000000.00, 0, 8490000.00, 11, 100, 'Note 12 Pro 5G'),
(36, b'1', 11, 1000000.00, 0, 7190000.00, 11, 100, 'Note 12 Pro 4G'),
(37, b'1', 11, 1000000.00, 0, 5890000.00, 11, 100, 'Note 12S'),
(38, b'1', 11, 1000000.00, 0, 4990000.00, 11, 100, 'Note 12 8GB 128GB'),
(39, b'1', 11, 1000000.00, 0, 4290000.00, 11, 100, 'Note 12 4GB 128GB'),
(40, b'1', 12, 1000000.00, 0, 8490000.00, 11, 100, 'Note 12 Pro 5G'),
(41, b'1', 12, 1000000.00, 0, 7190000.00, 11, 100, 'Note 12 Pro 4G'),
(42, b'1', 12, 1000000.00, 0, 5890000.00, 11, 100, 'Note 12S'),
(43, b'1', 12, 1000000.00, 0, 4990000.00, 11, 100, 'Note 12 8GB 128GB'),
(44, b'1', 12, 1000000.00, 0, 4290000.00, 11, 100, 'Note 12 4GB 128GB'),
(45, b'1', 13, 1000000.00, 0, 8490000.00, 11, 100, 'Note 12 Pro 5G'),
(46, b'1', 13, 1000000.00, 0, 7190000.00, 11, 100, 'Note 12 Pro 4G'),
(47, b'1', 13, 1000000.00, 0, 5890000.00, 11, 100, 'Note 12S'),
(48, b'1', 13, 1000000.00, 0, 4990000.00, 11, 100, 'Note 12 8GB 128GB'),
(49, b'1', 13, 1000000.00, 0, 4290000.00, 11, 100, 'Note 12 4GB 128GB'),
(50, b'1', 14, 1000000.00, 0, 3790000.00, 12, 100, '4GB 128GB'),
(51, b'1', 15, 1000000.00, 0, 3790000.00, 12, 100, '4GB 128GB'),
(52, b'1', 16, 1000000.00, 0, 36500000.00, 13, 100, '16GB-512GB'),
(53, b'1', 16, 1000000.00, 0, 28990000.00, 13, 100, '16GB-256GB'),
(54, b'1', 16, 1000000.00, 0, 24990000.00, 13, 100, '8GB-512GB'),
(55, b'1', 16, 1000000.00, 0, 19190000.00, 13, 100, '8GB-256GB'),
(56, b'1', 17, 1000000.00, 0, 36500000.00, 13, 100, '16GB-512GB'),
(57, b'1', 17, 1000000.00, 0, 28990000.00, 13, 100, '16GB-256GB'),
(58, b'1', 17, 1000000.00, 0, 24990000.00, 13, 100, '8GB-512GB'),
(59, b'1', 17, 1000000.00, 0, 19190000.00, 13, 100, '8GB-256GB'),
(60, b'1', 18, 1000000.00, 0, 36500000.00, 13, 100, '16GB-512GB'),
(61, b'1', 18, 1000000.00, 0, 28990000.00, 13, 100, '16GB-256GB'),
(62, b'1', 18, 1000000.00, 0, 24990000.00, 13, 100, '8GB-512GB'),
(63, b'1', 18, 1000000.00, 0, 19190000.00, 13, 100, '8GB-256GB'),
(64, b'0', 19, 1000000.00, 0, 17990000.00, 14, 100, 'I5-11400H 8GB - 512GB RTX-3050'),
(65, b'0', 19, 1000000.00, 0, 17490000.00, 14, 100, 'I5-11400H 8GB - 512GB RTX-2050'),
(66, b'0', 19, 1000000.00, 0, 14990000.00, 14, 100, 'I5-11400H 8GB - 512GB RTX-1650'),
(67, b'1', 20, 1000000.00, 0, 15290000.00, 15, 99, '15inch'),
(68, b'1', 20, 1000000.00, 0, 14990000.00, 15, 100, '14inch'),
(69, b'1', 22, 1000000.00, 0, 26490000.00, 16, 100, '8GB/256GB'),
(70, b'1', 23, 1000000.00, 0, 26490000.00, 16, 100, '8GB/256GB'),
(71, b'1', 24, 1000000.00, 0, 26490000.00, 16, 100, '8GB-256GB'),
(72, b'1', 25, 1000000.00, 0, 5390000.00, 17, 100, '29 inch'),
(73, b'1', 26, 1000000.00, 0, 3090000.00, 18, 100, '27 inch'),
(74, b'1', 27, 1000000.00, 0, 4190000.00, 19, 100, '24 inch'),
(75, b'1', 28, 1000000.00, 0, 7990000.00, 20, 100, '34 inch'),
(76, b'1', 29, 1000000.00, 0, 13990000.00, 21, 100, '65 inch'),
(77, b'1', 29, 1000000.00, 0, 8790000.00, 21, 100, '55 inch'),
(78, b'1', 29, 1000000.00, 0, 8790000.00, 21, 100, '75 inch'),
(79, b'1', 29, 1000000.00, 0, 8790000.00, 21, 99, '43 inch'),
(80, b'1', 30, 1000000.00, 0, 4590000.00, 22, 100, 'AirPods 3 Mag Safe'),
(81, b'1', 30, 1000000.00, 0, 4190000.00, 22, 100, 'AirPods 3 (2022)'),
(82, b'1', 30, 1000000.00, 0, 2690000.00, 22, 100, 'AirPods 2'),
(84, b'1', 32, 1000000.00, 0, 21000000.00, 26, 100, '512GB'),
(85, b'1', 32, 1000000.00, 0, 21000000.00, 26, 100, '256GB'),
(86, b'1', 33, 1000000.00, 0, 21000000.00, 26, 100, '512GB'),
(87, b'1', 33, 1000000.00, 0, 21000000.00, 26, 100, '256GB'),
(88, b'1', 34, 1000000.00, 0, 21000000.00, 26, 100, '512GB'),
(89, b'1', 34, 1000000.00, 0, 21000000.00, 26, 100, '256GB'),
(90, b'1', 35, 0.00, 0, 0.00, 27, 0, 'sdf');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_specifications`
--

CREATE TABLE `_specifications` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `_value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_specifications`
--

INSERT INTO `_specifications` (`id`, `name`, `product_id`, `_value`) VALUES
(1, 'Kích thước màn hình', 9, '6.7 inches'),
(2, 'Công nghệ màn hình', 9, 'Super Retina XDR OLED'),
(3, 'Camera sau', 9, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(4, 'Camera trước', 9, '12MP, ƒ/1.9'),
(5, 'Chipset', 9, 'Apple A16 Bionic'),
(6, 'Bộ nhớ trong', 9, '128 GB'),
(7, 'Thẻ SIM', 9, '2 SIM (nano‑SIM và eSIM)'),
(8, 'Hệ điều hành', 9, 'iOS 17'),
(9, 'Độ phân giải màn hình', 9, '2796 x 1290-pixel'),
(10, 'Tính năng màn hình', 9, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(11, 'Kích thước màn hình', 9, '6.7 inches'),
(12, 'Công nghệ màn hình', 9, 'Super Retina XDR OLED'),
(13, 'Camera sau', 9, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(14, 'Camera trước', 9, '12MP, ƒ/1.9'),
(15, 'Chipset', 9, 'Apple A16 Bionic'),
(16, 'Bộ nhớ trong', 9, '128 GB'),
(17, 'Thẻ SIM', 9, '2 SIM (nano‑SIM và eSIM)'),
(18, 'Hệ điều hành', 9, 'iOS 17'),
(19, 'Độ phân giải màn hình', 9, '2796 x 1290-pixel'),
(20, 'Tính năng màn hình', 9, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(21, 'Kích thước màn hình', 10, '6.7 inches'),
(22, 'Công nghệ màn hình', 10, 'Super Retina XDR OLED'),
(23, 'Camera sau', 10, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(24, 'Camera trước', 10, '12MP, ƒ/1.9'),
(25, 'Chipset', 10, 'Apple A16 Bionic'),
(26, 'Bộ nhớ trong', 10, '128 GB'),
(27, 'Thẻ SIM', 10, '2 SIM (nano‑SIM và eSIM)'),
(28, 'Hệ điều hành', 10, 'iOS 17'),
(29, 'Độ phân giải màn hình', 10, '2796 x 1290-pixel'),
(30, 'Tính năng màn hình', 10, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(31, 'Kích thước màn hình', 11, '6.7 inches'),
(32, 'Công nghệ màn hình', 11, 'Super Retina XDR OLED'),
(33, 'Camera sau', 11, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(34, 'Camera trước', 11, '12MP, ƒ/1.9'),
(35, 'Chipset', 11, 'Apple A16 Bionic'),
(36, 'Bộ nhớ trong', 11, '128 GB'),
(37, 'Thẻ SIM', 11, '2 SIM (nano‑SIM và eSIM)'),
(38, 'Hệ điều hành', 11, 'iOS 17'),
(39, 'Độ phân giải màn hình', 11, '2796 x 1290-pixel'),
(40, 'Tính năng màn hình', 11, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(41, 'Kích thước màn hình', 12, '6.7 inches'),
(42, 'Công nghệ màn hình', 12, 'Super Retina XDR OLED'),
(43, 'Camera sau', 12, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(44, 'Camera trước', 12, '12MP, ƒ/1.9'),
(45, 'Chipset', 12, 'Apple A16 Bionic'),
(46, 'Bộ nhớ trong', 12, '128 GB'),
(47, 'Thẻ SIM', 12, '2 SIM (nano‑SIM và eSIM)'),
(48, 'Hệ điều hành', 12, 'iOS 17'),
(49, 'Độ phân giải màn hình', 12, '2796 x 1290-pixel'),
(50, 'Tính năng màn hình', 12, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(51, 'Kích thước màn hình', 13, '6.7 inches'),
(52, 'Công nghệ màn hình', 13, 'Super Retina XDR OLED'),
(53, 'Camera sau', 13, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(54, 'Camera trước', 13, '12MP, ƒ/1.9'),
(55, 'Chipset', 13, 'Apple A16 Bionic'),
(56, 'Bộ nhớ trong', 13, '128 GB'),
(57, 'Thẻ SIM', 13, '2 SIM (nano‑SIM và eSIM)'),
(58, 'Hệ điều hành', 13, 'iOS 17'),
(59, 'Độ phân giải màn hình', 13, '2796 x 1290-pixel'),
(60, 'Tính năng màn hình', 13, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(61, 'Kích thước màn hình', 14, '6.7 inches'),
(62, 'Công nghệ màn hình', 14, 'Super Retina XDR OLED'),
(63, 'Camera sau', 14, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(64, 'Camera trước', 14, '12MP, ƒ/1.9'),
(65, 'Chipset', 14, 'Apple A16 Bionic'),
(66, 'Bộ nhớ trong', 14, '128 GB'),
(67, 'Thẻ SIM', 14, '2 SIM (nano‑SIM và eSIM)'),
(68, 'Hệ điều hành', 14, 'iOS 17'),
(69, 'Độ phân giải màn hình', 14, '2796 x 1290-pixel'),
(70, 'Tính năng màn hình', 14, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(71, 'Kích thước màn hình', 15, '6.7 inches'),
(72, 'Công nghệ màn hình', 15, 'Super Retina XDR OLED'),
(73, 'Camera sau', 15, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(74, 'Camera trước', 15, '12MP, ƒ/1.9'),
(75, 'Chipset', 15, 'Apple A16 Bionic'),
(76, 'Bộ nhớ trong', 15, '128 GB'),
(77, 'Thẻ SIM', 15, '2 SIM (nano‑SIM và eSIM)'),
(78, 'Hệ điều hành', 15, 'iOS 17'),
(79, 'Độ phân giải màn hình', 15, '2796 x 1290-pixel'),
(80, 'Tính năng màn hình', 15, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(81, 'Kích thước màn hình', 16, '6.7 inches'),
(82, 'Công nghệ màn hình', 16, 'Super Retina XDR OLED'),
(83, 'Camera sau', 16, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(84, 'Camera trước', 16, '12MP, ƒ/1.9'),
(85, 'Chipset', 16, 'Apple A16 Bionic'),
(86, 'Bộ nhớ trong', 16, '128 GB'),
(87, 'Thẻ SIM', 16, '2 SIM (nano‑SIM và eSIM)'),
(88, 'Hệ điều hành', 16, 'iOS 17'),
(89, 'Độ phân giải màn hình', 16, '2796 x 1290-pixel'),
(90, 'Tính năng màn hình', 16, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(91, 'Kích thước màn hình', 17, '6.7 inches'),
(92, 'Công nghệ màn hình', 17, 'Super Retina XDR OLED'),
(93, 'Camera sau', 17, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(94, 'Camera trước', 17, '12MP, ƒ/1.9'),
(95, 'Chipset', 17, 'Apple A16 Bionic'),
(96, 'Bộ nhớ trong', 17, '128 GB'),
(97, 'Thẻ SIM', 17, '2 SIM (nano‑SIM và eSIM)'),
(98, 'Hệ điều hành', 17, 'iOS 17'),
(99, 'Độ phân giải màn hình', 17, '2796 x 1290-pixel'),
(100, 'Tính năng màn hình', 17, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(101, 'Kích thước màn hình', 18, '6.7 inches'),
(102, 'Công nghệ màn hình', 18, 'Super Retina XDR OLED'),
(103, 'Camera sau', 18, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(104, 'Camera trước', 18, '12MP, ƒ/1.9'),
(105, 'Chipset', 18, 'Apple A16 Bionic'),
(106, 'Bộ nhớ trong', 18, '128 GB'),
(107, 'Thẻ SIM', 18, '2 SIM (nano‑SIM và eSIM)'),
(108, 'Hệ điều hành', 18, 'iOS 17'),
(109, 'Độ phân giải màn hình', 18, '2796 x 1290-pixel'),
(110, 'Tính năng màn hình', 18, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(111, 'Kích thước màn hình', 19, '6.7 inches'),
(112, 'Công nghệ màn hình', 19, 'Super Retina XDR OLED'),
(113, 'Camera sau', 19, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(114, 'Camera trước', 19, '12MP, ƒ/1.9'),
(115, 'Chipset', 19, 'Apple A16 Bionic'),
(116, 'Bộ nhớ trong', 19, '128 GB'),
(117, 'Thẻ SIM', 19, '2 SIM (nano‑SIM và eSIM)'),
(118, 'Hệ điều hành', 19, 'iOS 17'),
(119, 'Độ phân giải màn hình', 19, '2796 x 1290-pixel'),
(120, 'Tính năng màn hình', 19, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(121, 'Kích thước màn hình', 20, '6.7 inches'),
(122, 'Công nghệ màn hình', 20, 'Super Retina XDR OLED'),
(123, 'Camera sau', 20, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(124, 'Camera trước', 20, '12MP, ƒ/1.9'),
(125, 'Chipset', 20, 'Apple A16 Bionic'),
(126, 'Bộ nhớ trong', 20, '128 GB'),
(127, 'Thẻ SIM', 20, '2 SIM (nano‑SIM và eSIM)'),
(128, 'Hệ điều hành', 20, 'iOS 17'),
(129, 'Độ phân giải màn hình', 20, '2796 x 1290-pixel'),
(130, 'Tính năng màn hình', 20, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(131, 'Kích thước màn hình', 21, '6.7 inches'),
(132, 'Công nghệ màn hình', 21, 'Super Retina XDR OLED'),
(133, 'Camera sau', 21, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(134, 'Camera trước', 21, '12MP, ƒ/1.9'),
(135, 'Chipset', 21, 'Apple A16 Bionic'),
(136, 'Bộ nhớ trong', 21, '128 GB'),
(137, 'Thẻ SIM', 21, '2 SIM (nano‑SIM và eSIM)'),
(138, 'Hệ điều hành', 21, 'iOS 17'),
(139, 'Độ phân giải màn hình', 21, '2796 x 1290-pixel'),
(140, 'Tính năng màn hình', 21, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi'),
(141, 'Kích thước màn hình', 22, '6.7 inches'),
(142, 'Công nghệ màn hình', 22, 'Super Retina XDR OLED'),
(143, 'Camera sau', 22, 'Camera chính: 48MP, 26 mm, ƒ/1.6,\r\nCamerra góc rộng: 12MP, 13 mm, ƒ/2.4\r\nCamera Tele 2x: 12MP, 52 mm, ƒ/1.6'),
(144, 'Camera trước', 22, '12MP, ƒ/1.9'),
(145, 'Chipset', 22, 'Apple A16 Bionic'),
(146, 'Bộ nhớ trong', 22, '128 GB'),
(147, 'Thẻ SIM', 22, '2 SIM (nano‑SIM và eSIM)'),
(148, 'Hệ điều hành', 22, 'iOS 17'),
(149, 'Độ phân giải màn hình', 22, '2796 x 1290-pixel'),
(150, 'Tính năng màn hình', 22, 'Dynamic Island\r\n\r\nHDR display\r\n\r\nTrue Tone\r\n\r\nWide color (P3)\r\n\r\nHaptic Touch\r\n\r\nLớp phủ oleophobia chống dấu vân tay\r\n\r\nĐộ sáng tối đa: 2000 nits\r\n\r\nMật độ điểm ảnh 460 ppi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `_user`
--

CREATE TABLE `_user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` longtext DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `_user`
--

INSERT INTO `_user` (`id`, `active`, `address`, `created_date`, `email`, `first_name`, `last_name`, `password`, `phone`, `role`) VALUES
(5, b'1', NULL, '2023-10-30 00:20:13', 'toannqps23443@fpt.edu.vn', 'Nguyễn', 'Toàn', '{bcrypt}$2a$10$9C97PNdc4kWyRCv2tBx9h.jDOB1ZLTYxTe4s.pRcwO22UjZWCxlme', NULL, 'USER'),
(6, b'1', NULL, '2023-10-30 00:23:22', 'quoctoan2801@gmail.com', 'Nguyễn', 'Toàn', '{noop}123456', NULL, 'ADMIN'),
(7, b'1', NULL, '2023-11-08 20:38:22', 'qtoan443@gmail.com', 'hang', 'khach', '{bcrypt}$2a$10$uVZDPipA1Lu6SIR4mSdWzu0TVL7FffCLm4uQJV2MzJi7zRmOhFQZe', NULL, 'USER');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `_cart`
--
ALTER TABLE `_cart`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_category`
--
ALTER TABLE `_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_j5gjj5fugc1j1f7155d87rf8a` (`code`);

--
-- Chỉ mục cho bảng `_color_product`
--
ALTER TABLE `_color_product`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_image`
--
ALTER TABLE `_image`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_order`
--
ALTER TABLE `_order`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_order_detail`
--
ALTER TABLE `_order_detail`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_order_seq`
--
ALTER TABLE `_order_seq`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_product`
--
ALTER TABLE `_product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9swig05bbl1wa2tpek2dpb8cg` (`code`);

--
-- Chỉ mục cho bảng `_product_detail`
--
ALTER TABLE `_product_detail`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_specifications`
--
ALTER TABLE `_specifications`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `_user`
--
ALTER TABLE `_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `_cart`
--
ALTER TABLE `_cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `_category`
--
ALTER TABLE `_category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT cho bảng `_color_product`
--
ALTER TABLE `_color_product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `_image`
--
ALTER TABLE `_image`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `_order`
--
ALTER TABLE `_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `_order_detail`
--
ALTER TABLE `_order_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `_order_seq`
--
ALTER TABLE `_order_seq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1003;

--
-- AUTO_INCREMENT cho bảng `_product`
--
ALTER TABLE `_product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `_product_detail`
--
ALTER TABLE `_product_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT cho bảng `_specifications`
--
ALTER TABLE `_specifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;

--
-- AUTO_INCREMENT cho bảng `_user`
--
ALTER TABLE `_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
