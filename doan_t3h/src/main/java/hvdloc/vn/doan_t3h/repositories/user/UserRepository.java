package hvdloc.vn.doan_t3h.repositories.user;

import hvdloc.vn.doan_t3h.entities.UserEntity;
import hvdloc.vn.doan_t3h.repositories.SearchingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends SearchingRepository<UserEntity, Long>, UserRepositoryCustom {
    // đây là jpql (tìm kím)
    @Query("select u from UserEntity u where u.fullName like %?1% or u.userName like %?1% or u.address like %?1% or u.phone like %?1%")
    Page<UserEntity> findAll(String key, Pageable pageable);

    // tìm kím bằng sql thuần
    //@Query( value = "SELECT * FROM user WHERE FULL_NAME like %?1% or USER_NAME like %?1%" , nativeQuery = true)
    //Page<UserEntity> findAll(String key, Pageable pageable);

    // tìm kím danh sách User có trạng thái active
    List<UserEntity> findAllByStatus(Integer status);

    UserEntity findFirstByUserName(String username);

}
