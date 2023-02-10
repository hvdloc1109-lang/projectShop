package hvdloc.vn.doan_t3h.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_order", schema = "doan_t3h", catalog = "")
@Data
public class ProductOrderEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Basic
    @Column(name = "NUMBER")
    private int number;
    @Basic
    @Column(name = "COLOR")
    private String color;
    @Basic
    @Column(name = "SIZE")
    private String size;
    @Basic
    @Column(name = "STATUS")
    private int status;
    @Basic
    @Column(name = "USER_ID")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    ProductsEntity productsEntity;
}
