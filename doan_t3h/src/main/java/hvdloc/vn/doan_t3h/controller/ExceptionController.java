package hvdloc.vn.doan_t3h.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice // bat cac exception ma he thong bug ra
public class ExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public String accesDenied(){
        return "403";
    }

}
