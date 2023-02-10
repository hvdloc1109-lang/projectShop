package hvdloc.vn.doan_t3h.service;

import hvdloc.vn.doan_t3h.dto.BrandCategoryDto;
import hvdloc.vn.doan_t3h.dto.ResponseDto;
import hvdloc.vn.doan_t3h.dto.ResponseTableDto;
import hvdloc.vn.doan_t3h.entities.BrandEntity;
import hvdloc.vn.doan_t3h.entities.CategoryEntity;
import hvdloc.vn.doan_t3h.repositories.BrandRepository;
import hvdloc.vn.doan_t3h.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BrandCategoryService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseDto save(BrandCategoryDto dto, String path) {
        ResponseDto responseDto = new ResponseDto("Tạo mới lỗi");
        if ("brand".equalsIgnoreCase(path)) {
            BrandEntity brandEntity = new BrandEntity();
            BeanUtils.copyProperties(dto, brandEntity);
            brandRepository.save(brandEntity);
        } else {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(dto, categoryEntity);
            categoryRepository.save(categoryEntity);
        }
        responseDto = new ResponseDto("Tạo mới thành công");
        return responseDto;
    }

    public BrandCategoryDto getById(Long id, String path) {
        BrandCategoryDto brandCategoryDto = new BrandCategoryDto();
        if ("brand".equalsIgnoreCase(path)) {
            BrandEntity brandEntity = brandRepository.findById(id).get();
            BeanUtils.copyProperties(brandEntity, brandCategoryDto);
        } else {
            CategoryEntity categoryEntity = categoryRepository.findById(id).get();
            BeanUtils.copyProperties(categoryEntity, brandCategoryDto);
        }
        return brandCategoryDto;
    }

    public void list(ResponseTableDto tableDto, String path) {
        if ("brand".equalsIgnoreCase(path)) {
            tableDto.list(brandRepository);
        } else {
            tableDto.list(categoryRepository);
        }
    }

    public String delete(String path, Long id) {
        if ("brand".equalsIgnoreCase(path)) {
            brandRepository.deleteById(id);
        } else {
            categoryRepository.deleteById(id);
        }
        return "Xóa thành công";
    }

    public List<?> findAll(String path){
        if ("brand".equals(path))
            return brandRepository.findAll();
        else
            return  categoryRepository.findAll();
    }

}
