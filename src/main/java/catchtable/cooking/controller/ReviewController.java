package catchtable.cooking.controller;

import catchtable.cooking.dto.CommonResponse;
import catchtable.cooking.dto.ReviewCreateParam;
import catchtable.cooking.dto.ReviewCreateRequest;
import catchtable.cooking.exception.Code;
import catchtable.cooking.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/restaurants/{id}/reviews")
    public CommonResponse<?> postReview(@PathVariable Long id, @RequestBody ReviewCreateRequest reviewCreateRequest) {
        reviewService.createReview(id, reviewCreateRequest);
        return CommonResponse.of(Code.OK);
    }

    @PutMapping("/restaurants/{restaurantId}/reviews/{reviewId}")
    public CommonResponse<?> updateReview(@PathVariable Long restaurantId, @PathVariable Long reviewId, @RequestBody ReviewCreateRequest reviewCreateRequest) {

        ReviewCreateParam reviewCreateParam = ReviewCreateParam.builder()
                .content(reviewCreateRequest.getContent())
                .build();

        reviewService.updateReview(restaurantId, reviewId, reviewCreateParam);

        return CommonResponse.of(Code.OK);
    }


    @DeleteMapping("/restaurants/{restaurantId}/reviews/{reviewId}")
    public CommonResponse<?> deleteReview(@PathVariable Long restaurantId, @PathVariable Long reviewId) {
        reviewService.deleteReview(restaurantId, reviewId);
        return CommonResponse.of(Code.OK);
    }


}
