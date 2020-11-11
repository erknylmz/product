package se.sda.web.demo.inventories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(@Autowired InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories")
    public List<Inventory> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("inventories/{id}")
    public Inventory getById(@PathVariable Long id) {
        return inventoryService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/inventories")
    public Inventory create(@RequestBody Inventory inventory) {
        return inventoryService.create(inventory);
    }

    @PutMapping("/inventories")
    public Inventory update(@RequestBody Inventory inventory) {
        return inventoryService.update(inventory);
    }

    @DeleteMapping("inventories/{id}")
    public void delete(@PathVariable Long id) {
        inventoryService.delete(id);
    }
}
