package se.sda.web.demo.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    // findAllByCategoryIdAndNameContains(1, "Lap") -> Laptop
    List<Product> findAllByCategoryIdAndNameContains(Long categoryId, String nameSearch);
    List<Product> findAllByInventories_id(Long inventoryId);
}
