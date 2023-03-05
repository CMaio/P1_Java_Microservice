package cat.tecnocampus.review.api;

import cat.tecnocampus.review.application.ReviewPersistence;
import cat.tecnocampus.review.domain.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private ReviewPersistence persistence;

    public ApiController(ReviewPersistence persistence) {
        this.persistence = persistence;
    }


    @GetMapping("/reviews/{id}")
    public List<Review> getReviews(@PathVariable long id){
        return persistence.getReviews(id);
    }

    @PostMapping("/review")
    public void createRecommendation(@RequestBody Review review){
        persistence.createReview(review);
    }

    @PostMapping("/review/{id}")
    public void deleteRecommendations(@PathVariable long id){
        persistence.deleteReviews(id);
    }


}
