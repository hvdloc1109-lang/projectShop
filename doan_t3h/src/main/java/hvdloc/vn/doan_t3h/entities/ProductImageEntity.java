package hvdloc.vn.doan_t3h.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_image", schema = "doan_t3h", catalog = "")
@Data
public class ProductImageEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "product_id")
    private Long productId;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;


}
