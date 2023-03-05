package cat.tecnocampus.recommendation.persistence;

import cat.tecnocampus.recommendation.application.RecommendationPersistence;
import cat.tecnocampus.recommendation.domain.Recommendation;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Persistence implements RecommendationPersistence {

    private JdbcTemplate jdbcTemplate;

    ResultSetExtractorImpl<Recommendation> recommendationResultSetExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("recommendationId")
                    .newResultSetExtractor(Recommendation.class);

    RowMapperImpl<Recommendation> recommendationRowMapperExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("recommendationId")
                    .newRowMapper(Recommendation.class);

    public Persistence(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Recommendation> getRecommendations(long id) {
        final var query = "select * from recommendation where productId = ?";
        return jdbcTemplate.query(query, recommendationResultSetExtractor,id);
    }

    @Override
    public void deleteRecommendations(long id) {
        final var deleteProduct = "DELETE from recommendation WHERE productId = ?";
        jdbcTemplate.update(deleteProduct,id);
    }

    @Override
    public void createRecommendation(Recommendation recommendation) {
        final var insertProduct = "INSERT INTO recommendation (productId, author,rate,content) VALUES (?,?,?,?)";
        jdbcTemplate.update(insertProduct,recommendation.getProductId(),recommendation.getAuthor(),recommendation.getRate(),recommendation.getContent());
    }
}
