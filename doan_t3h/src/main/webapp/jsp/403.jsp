<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<style>
    .error {
        color: red;
    }
</style>
<jsp:include page="/jsp/common/nav.jsp" ></jsp:include>

<div class="container">
    <br>
    <h3 style="text-align: center">Bạn chưa đủ quyền truy cập chức năng này, vui lòng liên hệ đến Email hvdloc.19it3@vku.udn.vn để được giải quyết.
        <br>Trân trọng và quyết thắng!</h3>
    <h3 style="color: red">${message}</h3>
    <br>


</div>