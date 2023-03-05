package cat.tecnocampus.recommendation.application;

import cat.tecnocampus.recommendation.domain.Recommendation;

import java.util.List;

public interface RecommendationPersistence {
    public List<Recommendation> getRecommendations(long id);
    public void deleteRecommendations(long id);
    public void createRecommendation(Recommendation recommendation);


}
