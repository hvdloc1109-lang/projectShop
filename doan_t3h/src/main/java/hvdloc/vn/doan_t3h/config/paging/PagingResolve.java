package hvdloc.vn.doan_t3h.config.paging;

import hvdloc.vn.doan_t3h.dto.ResponseTableDto;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PagingResolve implements HandlerMethodArgumentResolver {
    // phaan trang
    // chỉ định và hỗ trợ loại param nào
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(PagingParam.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        PagingParam pagingParam = parameter.getParameterAnnotation(PagingParam.class);
        String path = pagingParam.path();
        String pageStr = webRequest.getParameter("page");
        Integer page = StringUtils.isEmpty(pageStr) ? 1 : Integer.valueOf(pageStr);
        String perPageStr = webRequest.getParameter("perpage");
        Integer perpage = StringUtils.isEmpty(perPageStr) ? 5 : Integer.valueOf(perPageStr);
        String key = webRequest.getParameter("key");
        key = StringUtils.isEmpty(key) ? "" : key;
        return new ResponseTableDto(path,page,perpage,key,mavContainer);
    }
}
