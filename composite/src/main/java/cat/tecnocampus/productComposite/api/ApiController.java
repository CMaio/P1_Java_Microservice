package cat.tecnocampus.productComposite.api;

import cat.tecnocampus.productComposite.application.AppliController;
import cat.tecnocampus.productComposite.domain.productCompos;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private AppliController appliController;

    public ApiController(AppliController appliController) {
        this.appliController = appliController;
    }

    @GetMapping("/products")
    public List<productCompos> getProducts(){
        return appliController.getProducts();
    }

    @GetMapping("/product/{id}")
    public productCompos getProduct(@PathVariable long id){
        return appliController.getProduct(id);
    }

    @PostMapping("/product")
    public void createProductComposite(@RequestBody productCompos productCompos){
        appliController.createProduct(productCompos);
    }

    @PostMapping("/product/{id}")
    public void deleteProductComposite(@PathVariable long id){
        appliController.deleteProduct(id);
    }




}
