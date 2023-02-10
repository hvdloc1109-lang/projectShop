package hvdloc.vn.doan_t3h.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "brand", schema = "doan_t3h", catalog = "")
@Data
public class BrandEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Basic
    @Column(name = "MAIN_IMAGE")
    private String mainImage;

}
