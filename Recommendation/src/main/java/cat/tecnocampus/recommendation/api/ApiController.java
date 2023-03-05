package cat.tecnocampus.recommendation.api;

import cat.tecnocampus.recommendation.application.RecommendationPersistence;
import cat.tecnocampus.recommendation.domain.Recommendation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    private RecommendationPersistence persistence;
    public ApiController(RecommendationPersistence recommendation) {
        this.persistence = recommendation;
    }


    @GetMapping("/recommendations/{id}")
    public List<Recommendation> getRecommendations(@PathVariable long id){
        return persistence.getRecommendations(id);
    }

    @PostMapping("/recommendation/{id}")
    public void deleteRecommendations(@PathVariable long id){
        persistence.deleteRecommendations(id);
    }

    @PostMapping("/recommendation")
    public void createRecommendation(@RequestBody Recommendation recommendation){
        persistence.createRecommendation(recommendation);
    }





}
