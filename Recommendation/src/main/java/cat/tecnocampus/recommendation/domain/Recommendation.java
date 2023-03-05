package cat.tecnocampus.recommendation.domain;

public class Recommendation {

    private long recommendationId;
    private long productId;
    private String author;
    private long rate;
    private String content;

    public long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
