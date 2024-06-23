package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response;

import lombok.Data;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Review;

@Data
public class ReviewResponse {
    private String username;
    private String comment;
    private int rating;

    public ReviewResponse(Review review) {
        this.username = review.getUsername();
        this.comment = review.getComment();
        this.rating = review.getRating();
    }
}
