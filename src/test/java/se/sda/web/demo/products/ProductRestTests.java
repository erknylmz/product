package se.sda.web.demo.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestTests {
    @Autowired
    ProductService productService;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testCreate() {
        // Arrange
        Product requestProduct = new Product(null, "Test title", "Test description");

        // Act
        Product responseProduct = testRestTemplate.postForObject("/products", requestProduct, Product.class);

        // Alternative 1
//        HttpEntity<Product> response = testRestTemplate.postForEntity("/products", requestProduct, Product.class);
//        Product responseProduct = response.getBody();

        // Alternative 2
//        HttpEntity<Product> requestProductHttpEntity = new HttpEntity<>(requestProduct);
//        HttpEntity<Product> response = testRestTemplate.exchange("/products", HttpMethod.POST, requestProductHttpEntity, Product.class);
//        Product responseProduct = response.getBody();


        // Assert
        Assertions.assertEquals(requestProduct.getName(), responseProduct.getName());
        Assertions.assertEquals(requestProduct.getDescription(), responseProduct.getDescription());

        // check that product is added
        Product getByIdProductResponse = testRestTemplate.getForObject("/products/" + responseProduct.getId().toString(), Product.class);
        Assertions.assertEquals(requestProduct.getName(), getByIdProductResponse.getName());
        Assertions.assertEquals(requestProduct.getDescription(), getByIdProductResponse.getDescription());

        // Clean up
        testRestTemplate.delete("/products"+responseProduct.getId().toString());
    }

    @Test
    public void testGetAllReturnEmptyArray() {
        // Act
        String responseProducts = testRestTemplate.getForObject("/products", String.class);

        // Assert
        Assertions.assertEquals("[]", responseProducts);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Product originalProduct = productService.create(new Product(null, "Test title", "Test description"));
        Product updatedProduct = new Product(originalProduct.getId(), "Test updated title", "Test updated description");

        // Act
        Product responseProduct = putForProduct(updatedProduct);


        // Assert
        Assertions.assertEquals(updatedProduct.getName(), responseProduct.getName());
        Assertions.assertEquals(updatedProduct.getDescription(), responseProduct.getDescription());

        Product getByIdProductResponse = testRestTemplate.getForObject("/products/" + updatedProduct.getId().toString(), Product.class);
        Assertions.assertEquals(updatedProduct.getName(), getByIdProductResponse.getName());
        Assertions.assertEquals(updatedProduct.getDescription(), getByIdProductResponse.getDescription());
    }


    private Product putForProduct(Product requestBody) {
        HttpEntity<Product> requestEntity = new HttpEntity<>(requestBody);
        HttpEntity<Product> response = testRestTemplate.exchange("/products", HttpMethod.PUT, requestEntity, Product.class);
        return response.getBody();
    }
}
