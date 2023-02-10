<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<style>
    .error {
        color: red;
    }
</style>
<jsp:include page="/jsp/common/nav.jsp" ></jsp:include>
<%--<nav class="nav nav-pills nav-fill">--%>
<%--    <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>--%>
<%--    <a class="nav-link active" href="#">Tài khoản</a>--%>
<%--    <a class="nav-link active" href="#">Sản phẩm</a>--%>
<%--    <a class="nav-link active" href="#">Thương hiệu</a>--%>
<%--</nav>--%>
<div class="container">
    <br>
    <h2>Chi tiết tài khoản</h2>
    <br>
    <f:form action="/backend/user/save" modelAttribute="user" method="post" class=" g-3 needs-validation" >
        <div class="row mb-4">
            <div class="col-md-4">
                <label for="validationCustomUsername" class="form-label">Tên đăng nhập</label>
                <div class="input-group has-validation">
                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                    <f:input type="text" class="form-control" path="userName" id="validationCustomUsername"
                             aria-describedby="inputGroupPrepend" readonly="true" />
                </div>
                <f:errors cssClass="error" path="userName"/>
            </div>


        </div>
        <div class="row mb-4">
            <div class="col-md-6">
                <label for="validationCustom03" class="form-label">Họ và tên</label>
                <f:input type="text" class="form-control" path="fullName" id="validationCustom03" readonly="true" />
                <f:errors cssClass="error" path="fullName" />
            </div>
            <div class="col-md-3">
                <label for="validationCustom04" class="form-label">Trạng thái</label>
                <f:select class="form-select" path="status" id="validationCustom04" disabled="true" >
                    <f:option  value="-1">Trạng thái</f:option>
                    <f:option value="1">Kích hoạt</f:option>
                    <f:option value="0">Tạm dừng</f:option>
                </f:select>
                <f:errors cssClass="error" path="status"/>
            </div>
            <div class="col-md-3">
                <label for="validationCustom14" class="form-label">Quyền</label>
                <f:select class="form-select" path="role" id="validationCustom14"  disabled="true">
                    <f:option value="">Danh sách quyền</f:option>
                    <f:option value="ADMIN">Người quản trị</f:option>
                    <f:option value="USER">Nhân viên</f:option>
                    <f:option value="CUSTOMER">Khách hàng</f:option>
                </f:select>
                <f:errors cssClass="error" path="role"/>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-6">
                <label for="validationCustom088" class="form-label">Số điện thoại</label>
                <f:input type="text" class="form-control" path="phone" id="validationCustom088" readonly="true" />
            </div>
            <div class="col-6">
                <label for="validationCustom087" class="form-label">Ngày sinh</label>
                <f:input type="date" class="form-control" path="date" id="validationCustom087" readonly="true" />
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-12">
                <label for="validationCustom086" class="form-label">Địa chỉ</label>
                <f:input type="text" class="form-control" path="address" id="validationCustom086" readonly="true" />
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-12">
                <a href="/backend/user/list" class="btn btn-info" type="submit">Trở về</a>
            </div>
        </div>

    </f:form>
</div>