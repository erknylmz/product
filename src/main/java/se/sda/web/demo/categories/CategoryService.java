package se.sda.web.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepo repo;

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Optional<Category> getById(Long id) {
        return repo.findById(id);
    }

    public Category create(Category category) {
        return repo.save(category);
    }

    public Category update(Category updatedCategory) {
        return repo.save(updatedCategory);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
