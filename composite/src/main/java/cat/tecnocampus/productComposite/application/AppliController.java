package cat.tecnocampus.productComposite.application;

import cat.tecnocampus.productComposite.domain.compositeRecommendation;
import cat.tecnocampus.productComposite.domain.compositeReview;
import cat.tecnocampus.productComposite.domain.productCompos;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppliController {

    private RestTemplate restTemplate;

    private String productServiceUrl;
    private String reviewServiceUrl;
    private String recommendationServiceUrl;
    public AppliController(RestTemplate restTemplate,
                           @Value("${app.product-service.host}") String productServiceHost,
                           @Value("${app.product-service.port}") int productServicePort,
                           @Value("${app.review-service.host}") String reviewServiceHost,
                           @Value("${app.review-service.port}") int reviewServicePort,
                           @Value("${app.recommendation-service.host}") String recommendationServiceHost,
                           @Value("${app.recommendation-service.port}") int recommendationServicePort){

        this.restTemplate = restTemplate;
        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort;
        reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort;
        recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort;
    }

    public List<productCompos> getProducts() {
        var result = restTemplate.exchange(productServiceUrl+ "/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<productCompos>>() {});
        List<productCompos> product;
        product = attachCompositeRecommendations(result.getBody());
        product = attachCompositeReviews(product);
        return product;
    }



    public productCompos getProduct(long id) {
        var result =  restTemplate.exchange(productServiceUrl + "/product/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<productCompos>() {});
        var resutlRecommendation = restTemplate.exchange(recommendationServiceUrl +"/recommendations/" +id,HttpMethod.GET, null, new ParameterizedTypeReference<List<compositeRecommendation>>() {});
        var resutlReview = restTemplate.exchange(reviewServiceUrl +"/reviews/" +id,HttpMethod.GET, null, new ParameterizedTypeReference<List<compositeReview>>() {});
        result.getBody().setCompositeRecommendations(resutlRecommendation.getBody());
        result.getBody().setCompositeReviews(resutlReview.getBody());
        return result.getBody();
    }

    public void createProduct(productCompos productCompos) {
        String jasonString = new JSONObject()
                                .put("id",productCompos.getId())
                                .put("name",productCompos.getName())
                                .put("description",productCompos.getDescription())
                                .put("weight",productCompos.getWeight())
                                .toString();
        restTemplate.postForObject(productServiceUrl+"/products", jasonString, String.class);
        for(compositeRecommendation cr : productCompos.getCompositeRecommendations()){
            String jasonStringRecom = new JSONObject()
                    .put("recommendationId",cr.getRecommendationId())
                    .put("productId",cr.getProductId())
                    .put("author",cr.getAuthor())
                    .put("rate",cr.getRate())
                    .put("content",cr.getContent())
                    .toString();
            restTemplate.postForObject(recommendationServiceUrl+"/recommendation", jasonStringRecom, String.class);
        }
        for(compositeReview cre : productCompos.getCompositeReviews()){
            String jasonStringReview = new JSONObject()
                    .put("reviewId",cre.getReviewId())
                    .put("productId",cre.getProductId())
                    .put("author",cre.getAuthor())
                    .put("subject",cre.getSubject())
                    .put("content",cre.getContent())
                    .toString();
            restTemplate.postForObject(reviewServiceUrl+"/review", jasonStringReview, String.class);
        }
    }

    public void deleteProduct(long id) {
        restTemplate.postForObject(productServiceUrl+"/product/"+id, null, String.class);
        restTemplate.postForObject(recommendationServiceUrl+"/recommendation/"+id, null, String.class);
        restTemplate.postForObject(reviewServiceUrl+"/review/"+id, null, String.class);
    }
    private List<productCompos> attachCompositeRecommendations(List<productCompos> products){

        for(productCompos p : products){
            var resutlRecommendation = restTemplate.exchange(recommendationServiceUrl +"/recommendations/" +p.getId(),HttpMethod.GET, null, new ParameterizedTypeReference<List<compositeRecommendation>>() {});
            p.setCompositeRecommendations(resutlRecommendation.getBody());
        }
        return products;
    }
    private List<productCompos> attachCompositeReviews(List<productCompos> products){
        for(productCompos p : products){
            var resutlReview = restTemplate.exchange(reviewServiceUrl +"/reviews/" +p.getId(),HttpMethod.GET, null, new ParameterizedTypeReference<List<compositeReview>>() {});
            p.setCompositeReviews(resutlReview.getBody());
        }
        return products;
    }

}
