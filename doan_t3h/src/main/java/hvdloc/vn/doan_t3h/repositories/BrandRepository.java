package hvdloc.vn.doan_t3h.repositories;

import hvdloc.vn.doan_t3h.entities.BrandEntity;
import hvdloc.vn.doan_t3h.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository extends SearchingRepository<BrandEntity, Long> {
    @Query("select u from BrandEntity u where u.name like %?1% or u.description like %?1%")
    Page<BrandEntity> findAll(String key, Pageable pageable);
}
