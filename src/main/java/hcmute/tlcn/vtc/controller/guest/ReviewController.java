package hcmute.tlcn.vtc.controller.guest;

import hcmute.tlcn.vtc.model.data.user.response.ListReviewResponse;
import hcmute.tlcn.vtc.service.guest.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ListReviewResponse> getReviewsByProductId(@PathVariable Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Mã sản phẩm không được để trống!");
        }
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }
}
