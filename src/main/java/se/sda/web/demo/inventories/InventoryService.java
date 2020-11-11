package se.sda.web.demo.inventories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo repo;

    public List<Inventory> getAll() {
        return repo.findAll();
    }

    public Optional<Inventory> getById(Long id) {
        return repo.findById(id);
    }

    public Inventory create(Inventory inventory) {
        return repo.save(inventory);
    }

    public Inventory update(Inventory updatedInventory) {
        return repo.save(updatedInventory);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
