package hvdloc.vn.doan_t3h.repositories.product;

import hvdloc.vn.doan_t3h.entities.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, Long> {

    int countDistinctByUserIdAndStatus(Long userId, Integer status);

    List<ProductOrderEntity> findAllByUserId(Long userid);

}
