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
    <h1>Sửa tài khoản</h1>
    <br>
    <f:form action="/backend/user/changepass" modelAttribute="user" method="post" class=" g-3 needs-validation" >
        <div class="row mb-4">
            <div class="col-md-6">
                <label for="validationCustomUsername" class="form-label">Tên đăng nhập</label>
                <div class="input-group has-validation">
                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                    <f:input type="text" class="form-control" path="userName" id="validationCustomUsername"
                             aria-describedby="inputGroupPrepend" disabled="true" />
                </div>
                <f:errors cssClass="error" path="userName"/>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <label for="validationCustom03" class="form-label">Mật khẩu cũ</label>
                <f:input type="password" class="form-control" path="oldPassword" id="validationCustom03" />
                <f:errors cssClass="error" path="oldPassword"/>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <label for="validationCustomUsername" class="form-label">Mật khẩu mới</label>
                <div class="input-group has-validation">
                    <f:input type="password" class="form-control" path="password" id="validationCustomUsername"
                             aria-describedby="inputGroupPrepend"/>
                </div>
                <f:errors cssClass="error" path="password"/>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-6">
                <label for="validationCustomUsername" class="form-label">nhập lại mật khẩu mới</label>
                <div class="input-group has-validation">
                    <f:input type="password" class="form-control" path="rePassword" id="validationCustomUsername"
                             aria-describedby="inputGroupPrepend" />
                </div>
                <f:errors cssClass="error" path="rePassword"/>
            </div>
        </div>
        <br>
        <div class="col-6">
            <button class="btn btn-primary" type="submit">Đổi mật khẩu</button>
        </div>

    </f:form>
</div>