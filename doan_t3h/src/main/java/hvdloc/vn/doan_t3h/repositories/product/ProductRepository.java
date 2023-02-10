package hvdloc.vn.doan_t3h.repositories.product;

import hvdloc.vn.doan_t3h.entities.ProductsEntity;
import hvdloc.vn.doan_t3h.entities.UserEntity;
import hvdloc.vn.doan_t3h.repositories.SearchingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends SearchingRepository<ProductsEntity, Long> {
    //jpql
    @Query("select u from ProductsEntity u where u.alias like %?1% or u.name like %?1% or u.fullDescription like %?1% or u.shortDescription like %?1%")
    Page<ProductsEntity> findAll(String key, Pageable pageable);


    @Query(value = "select * from products order by updated_time desc limit 4", nativeQuery = true) // lấy 4sp có thời gian cập nhập gần nhất
    List<ProductsEntity> findTop4Product();

}
