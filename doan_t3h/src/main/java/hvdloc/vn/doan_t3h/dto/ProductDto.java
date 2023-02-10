package hvdloc.vn.doan_t3h.dto;

import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class ProductDto {
    private Long id;
    private String alias; // tên bí danh
    private Timestamp createdTime; // thời gian tạo
    private Double discountPercent; // giảm % giá
    private boolean enabled; // trạng thái sản phẩm
    private String fullDescription; // mô tả cụ thể
    private double height; // cao
    private Boolean inStock; // trạnh thái
    private double length; // dài
    private String mainImage; // ảnh chính
    private String name; // tên
    private Integer price; // giá
    private String shortDescription; // mô tả ngắn
    private Timestamp updatedTime; // thời gian cập nhập
    private double weight; // nặng
    private double width; // rộng
    private Integer brandId;
    private Integer categoryId;
    MultipartFile fileImage; // ảnh

    public void setFileImage(MultipartFile fileImage) {
        if (fileImage != null && StringUtils.isEmpty(fileImage.getOriginalFilename()))
            this.fileImage = null;
        else
            this.fileImage = fileImage;
    }
}
