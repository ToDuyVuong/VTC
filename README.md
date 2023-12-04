# Sàn Bách Hóa VTC

## 1. Giới thiệu

Đồ án môn học: Trang Bán Sàn Thương Mại Điện Tử Bách Hóa VTC

## 2. Thành viên

- Tô Duy Vượng - 20110053
- Nguyễn Quốc Trung - 20110588
- Vũ Trần Thành Công - 20110448

## 3. Mô tả đồ án

- Đồ án sử dụng công nghệ Spring Boot, Security, Spring Data JPA, Hibernate, MySQL, Restful API, ReactJS, Redux, Axios,
  Bootstrap, HTML, CSS, JavaScript...
- Đồ án sử dụng mô hình Client - Server.
_~~- Đồ án sử dụng mô hình 3 lớp: Controller, Service, Repository.~~_


## 4. Các chức năng

- Đăng nhập, đăng ký, đăng xuất.


- Quản lý thông tài khoản:
    - Xem thông tin tài khoản.
    - Cập nhật thông tin tài khoản.
    - Đổi mật khẩu. 
    - Lấy lại mật khẩu.
    

- Quản lý địa chỉ:
    - Thêm địa chỉ.
    - Xem chi tiết địa chỉ.
    - Cập nhật thông tin chi tiết địa chỉ.
    - Chọn trạng thái địa chỉ.
    - Xóa địa chỉ.
    - Xem danh sách địa chỉ.


- Quản lý cửa hàng:
    - Thêm cửa hàng.
    - Xem chi tiết cửa hàng.
    - Cập nhật thông tin chi tiết cửa hàng.
    - Chọn trạng thái cửa hàng.


- Quản lý danh mục từ quyền admin:
    - Thêm danh mục bằng quyền admin.
    - Xem danh sách danh mục.
    - Xem chi tiết danh mục.
    - Cập nhật thông tin danh mục.
    - Chọn trạng thái danh mục.


- Quản lý thương hiệu sản phẩm từ quyền admin:
    - Thêm thương hiệu bằng quyền admin.
    - Xem danh sách thương hiệu.
    - Xem chi tiết thương hiệu.
    - Cập nhật thông tin thương hiệu.
    - Cập nhật trạng thái thương hiệu.
  

- Quản lý danh mục từ quyền vendor:
    - Thêm danh mục bằng quyền vendor.
    - Xem danh sách danh mục của vendor.
    - Xem danh sách danh mục parent của admin.
    - Xem chi tiết danh mục.
    - Cập nhật thông tin danh mục.
    - Chọn trạng thái danh mục.


- Quản lý thuộc tính từ quyền vendor:
    - Thêm thuộc tính bằng quyền vendor.
    - Xem danh sách thuộc tính của vendor.
    - Xem chi tiết thuộc tính.
    - Cập nhật thông tin thuộc tính.
    - Khóa, mở khóa thuộc tính.
    - Xóa thuộc tính.



- Quản lý sản phẩm từ quyền vendor:
    - Thêm sản phẩm bằng quyền vendor.
    - Xem danh sách sản phẩm của vendor.
    - Xem chi tiết sản phẩm.
    - Cập nhật thông tin sản phẩm.
    - Chọn trạng thái sản phẩm.
    - Xem danh sách sản phẩm đã xóa.
    - Khôi phục sản phẩm đã xóa.
    - Xem danh sách sản phẩm theo danh mục.
    - Xem danh sách sản phẩm bán chạy.
    - Xem danh sách sản phẩm mới nhất.
    - Tim kiếm sản phẩm theo tên.
    - Xem danh sách sản phẩm theo giá.


- Xem sản phẩm:
    - Xem chi tiết sản phẩm.
    - Xem danh sách sản phẩm theo danh mục.
    - Xem danh sách sản phẩm bán chạy.
    - Xem danh sách sản phẩm mới nhất.
    - Tim kiếm sản phẩm theo tên.
    - Xem danh sách sản phẩm theo giá.
    - Tính số yêu thích của sản phẩm.


- Yêu thích sản phẩm:
    - Thêm sản phẩm vào danh sách yêu thích.
    - Chi tiết sản phẩm yêu thích.
    - Xem danh sách sản phẩm yêu thích.
    - Xóa sản phẩm khỏi danh sách yêu thích.


- Theo dõi cửa hàng:
    - Thêm cửa hàng vào danh sách theo dõi.
    - Xem danh sách cửa hàng theo dõi.
    - Xóa cửa hàng khỏi danh sách theo dõi.
    - Tính số theo dõi của cửa hàng.


- Xem cửa hàng:
    - Xem chi tiết cửa hàng.
    - Xem danh sách sản phẩm của cửa hàng.
    - Xem danh sách sản phẩm bán chạy của cửa hàng.
    - Xem danh sách sản phẩm mới nhất của cửa hàng.
    - Tim kiếm sản phẩm theo tên của cửa hàng.
    - Xem danh sách sản phẩm theo giá của cửa hàng.


- Quản lý giỏ hàng:
    - Thêm sản phẩm vào giỏ hàng.
    - Xem danh sách sản phẩm trong giỏ hàng theo cửa hàng.
    - Xem danh sách sản phẩm trong giỏ hàng theo mã giỏ hàng.
    - Cập nhật số lượng sản phẩm trong giỏ hàng.
    - Xóa sản phẩm khỏi giỏ hàng.
    - Xóa sản phẩm khỏi giỏ hàng theo cửa hàng.


- Quản lý mã giảm giá từ quyền admin:
    - Thêm mã giảm giá bằng quyền admin.
    - Xem danh sách mã giảm giá theo trạng thái và loại mã giảm giá.
    - Xem chi tiết mã giảm giá.
    - Cập nhật thông tin mã giảm giá.
    - Chọn trạng thái mã giảm giá.


- Quản lý mã giảm giá từ quyền vendor:
    - Thêm mã giảm giá bằng quyền vendor.
    - Xem danh sách mã giảm giá theo trạng thái, loại mã giảm giá và cửa hàng.
    - Xem chi tiết mã giảm giá.
    - Cập nhật thông tin mã giảm giá.
    - Chọn trạng thái mã giảm giá.


- Xem mã giảm giá từ quyền khách:
    - Xem danh sách mã giảm giá theo trạng thái và loại mã giảm giá.
    - Xem chi tiết mã giảm giá.
    - Xem danh sách mã giảm giá theo cửa hàng.
    - Xem danh sách tất cả mã giảm giá.


- Quản lý mã giảm giá từ quyền customer:
    - Lưu mã giảm giá.
    - Xem danh sách mã giảm giá đã lưu.


- Quản lý đơn hàng của customer:
  - Tạo đơn hàng tạm thời với mã giỏ hàng.
  - Cập nhật đơn hàng tạm thời với:
    - Thông tin địa chỉ nhận hàng.
    - Thông tin mã giảm giá.
    - Thông tin phương thức thanh toán.
    - Thông tin phương thức vận chuyển.
    - Thông tin ghi chú.
  - Đặt hàng với đơn hàng tạm thời.
  - Xem danh sách đơn hàng đã đặt.
  - Xem danh sách đơn theo trạng thái.
  - Xem chi tiết đơn hàng đã đặt.
  - Hủy đơn hàng đã đặt.
 

- Quản lý đơn hàng của vender:
  - Xem danh sách đơn hàng theo trạng thái.
  - Xem chi tiết đơn hàng đã đặt.
  - Cập nhật trạng thái đơn hàng.
  - Xem danh sách đơn hàng theo thời gian.
  - Xem danh sách đơn hàng theo thời gian và trạng thái.


- Quản lý đánh giá của customer:
  - Thêm đánh giá.
  - Xóa đánh giá.


- Quản lý đánh giá:
  - Xem danh sách đánh giá theo sản phẩm.


- Quản lý bình luận:
  - Thêm bình luận.
  - Xóa bình luận.
  - Tính số bình luận của sản phẩm.
  - Tính điểm đánh giá của sản phẩm.


- Thống kê doanh thu từ quyền vender:
  - Xem doanh thu theo thời gian.



- Phân trang sản phẩm:
  - Xem danh sách sản phẩm theo danh mục.
  - Xem danh sách sản phẩm theo cửa hàng.
  - Xem danh sách sản phẩm bán chạy theo cửa hàng.
  - Xem danh sách sản phẩm mới nhất theo cửa hàng.
  - Tim kiếm sản phẩm theo tên và lọc sản phẩm theo giá.
  - Tim kiếm sản phẩm theo tên và lọc sản phẩm. 
  - Xem danh sách sản phẩm theo giá theo cửa hàng.
  - Xem danh sách sản phẩm theo giá.
  - Lọc sản phẩm theo giá. 
  - //Xem danh sách sản phẩm theo thương hiệu.
