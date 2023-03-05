package cat.tecnocampus.productComposite.domain;

import java.util.List;

public class productCompos {
    private long id;
    private String name;
    private String description;
    private long weight;
    private List<compositeRecommendation> compositeRecommendations;
    private List<compositeReview> compositeReviews;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getWeight(){return weight;}

    public void setWeight(long weight){this.weight = weight;}

    public List<compositeRecommendation> getCompositeRecommendations() {
        return compositeRecommendations;
    }

    public void setCompositeRecommendations(List<compositeRecommendation> compositeRecommendations) {
        this.compositeRecommendations = compositeRecommendations;
    }

    public List<compositeReview> getCompositeReviews() {
        return compositeReviews;
    }

    public void setCompositeReviews(List<compositeReview> compositeReviews) {
        this.compositeReviews = compositeReviews;
    }
}
