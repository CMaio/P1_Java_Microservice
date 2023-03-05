package cat.tecnocampus.product.api;

import cat.tecnocampus.product.application.ProductPersistence;
import cat.tecnocampus.product.domain.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private ProductPersistence persistence;

    public ApiController(ProductPersistence persistence) {
        this.persistence = persistence;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return persistence.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable long id) {
        return persistence.getProduct(id);
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody Product product) {
        System.out.println("name: " + product.getName() + " description: " + product.getDescription());
        persistence.createProduct(product);
    }

    @PostMapping("/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        //System.out.println("name: " + product.getName() + " description: " + product.getDescription());
        persistence.deleteProduct(id);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "{\"salutation\" : \"Hello world. \"}";
    }
}
