package cat.tecnocampus.review.persistence;

import cat.tecnocampus.review.application.ReviewPersistence;
import cat.tecnocampus.review.domain.Review;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Persistence implements ReviewPersistence {

    private JdbcTemplate jdbcTemplate;

    ResultSetExtractorImpl<Review> reviewResultSetExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("reviewId")
                    .newResultSetExtractor(Review.class);


    public Persistence(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Review> getReviews(long id) {
        final var query = "select * from review where productId = ?";
        return jdbcTemplate.query(query, reviewResultSetExtractor,id);
    }

    @Override
    public void createReview(Review review) {
        final var insertReview = "INSERT INTO review (productId, author,subject,content) VALUES (?,?,?,?)";
        jdbcTemplate.update(insertReview,review.getProductId(),review.getAuthor(),review.getSubject(),review.getContent());
    }

    @Override
    public void deleteReviews(long id) {
        final var deleteProduct = "DELETE from review WHERE productId = ?";
        jdbcTemplate.update(deleteProduct,id);
    }
}
