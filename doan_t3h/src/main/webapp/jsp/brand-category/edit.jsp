<%@ page pageEncoding="UTF-8" %>
<%@include file="/jsp/common/nav.jsp"%>
<style>
    .error {
        color: red;
    }
    input:focus,textarea:focus{
        border-color: initial;
    }

    input.error, textarea.error, .has-error{
        border:1px solid red;
    }
</style>
<div class="container">
    <br>
    <span style="color: red">${message}</span>
    <h1>Cập nhập ${title}</h1>
    <br>
    <form  id="brandForm" action="/backend/${path}/save" method="post" enctype="application/x-www-form-urlencoded">
        <input type="text" hidden value="${model.id}" name="id" class="form-control" id="exampleInputPassword1">
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Tên ${title}</label>
                    <input value="${model.name}" type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Mô tả</label>
                    <input value="${model.description}" type="text" name="description" class="form-control" id="exampleInputPassword1">
                </div>
            </div>
        </div>

        <button style="width: 10%" type="submit" class="btn btn-primary btn-block mb-4">Cập nhập</input>

    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
