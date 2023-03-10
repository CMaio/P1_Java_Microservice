package cat.tecnocampus.product.persistence;

import cat.tecnocampus.product.application.ProductPersistence;
import cat.tecnocampus.product.domain.Product;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class Persistence implements ProductPersistence {
    private JdbcTemplate jdbcTemplate;

    ResultSetExtractorImpl<Product> productResultSetExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(Product.class);

    RowMapperImpl<Product> productRowMapperExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(Product.class);

    public Persistence(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        final var query = "select * from product";
        return jdbcTemplate.query(query, productResultSetExtractor);
    }

    @Override
    public Product getProduct(long id) {
        final var query = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(query, productRowMapperExtractor,id);
    }

    @Override
    public void createProduct(Product product) {
        final var insertProduct = "INSERT INTO product (name, description,weight) VALUES (?,?,?)";
        jdbcTemplate.update(insertProduct, product.getName(), product.getDescription(),product.getWeight());
    }

    @Override
    public void deleteProduct(long id) {
        final var insertProduct = "DELETE from product WHERE id = ?";
        jdbcTemplate.update(insertProduct,id);
    }
}
