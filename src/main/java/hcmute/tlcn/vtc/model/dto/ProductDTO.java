package hcmute.tlcn.vtc.model.dto;


import hcmute.tlcn.vtc.model.extra.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDTO {

    private Long productId;

    private String name;

    private String image;

    private String description;

    private String information;

    private Long sold;

    private Status status;

    private Long categoryId;

    private Long brandId;

    private List<ProductVariantDTO> productVariantDTOs;


//    public void validate() {
//        if (this.name == null || this.name.isEmpty()) {
//            throw new IllegalArgumentException("Tên sản phẩm không được để trống!");
//        }
//
//        if (this.description == null || this.description.isEmpty()) {
//            throw new IllegalArgumentException("Mô tả sản phẩm không được để trống!");
//        }
//
//        if (this.information == null || this.information.isEmpty()) {
//            throw new IllegalArgumentException("Thông tin sản phẩm không được để trống!");
//        }
//
//        if (this.categoryId == null) {
//            throw new IllegalArgumentException("Danh mục sản phẩm không được để trống!");
//        }
//
////        if (this.brandId == null) {
////            throw new IllegalArgumentException("Thương hiệu sản phẩm không được để trống!");
////        }
//    }
//
//
//    public void validateUpdate() {
//        if (this.productId == null) {
//            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
//        }
//
//        if (this.name == null || this.name.isEmpty()) {
//            throw new IllegalArgumentException("Tên sản phẩm không được để trống!");
//        }
//
//        if (this.description == null || this.description.isEmpty()) {
//            throw new IllegalArgumentException("Mô tả sản phẩm không được để trống!");
//        }
//
//        if (this.information == null || this.information.isEmpty()) {
//            throw new IllegalArgumentException("Thông tin sản phẩm không được để trống!");
//        }
//
//        if (this.categoryId == null) {
//            throw new IllegalArgumentException("Danh mục sản phẩm không được để trống!");
//        }
//
////        if (this.brandId == null) {
////            throw new IllegalArgumentException("Thương hiệu sản phẩm không được để trống!");
////        }
//    }





}
