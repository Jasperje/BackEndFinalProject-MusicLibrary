package music.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.library.entity.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
