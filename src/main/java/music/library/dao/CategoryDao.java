package music.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.library.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
