package music.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.library.entity.Store;

public interface StoreDao extends JpaRepository<Store, Long> {



}
