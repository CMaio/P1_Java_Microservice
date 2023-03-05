package cat.tecnocampus.review.application;


import cat.tecnocampus.review.domain.Review;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReviewPersistence {

    public List<Review> getReviews(long id);
    public void createReview(Review review);
    public void deleteReviews(long id);
}
