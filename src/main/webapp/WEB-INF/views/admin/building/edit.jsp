<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin/home"/>">
                            Trang chủ
                        </a>
                    </li>
                    <c:if test="${not empty buildingEdit.id}">
                        <li class="active"> Sửa thông tin tòa nhà </li>
                    </c:if>
                    <c:if test="${empty buildingEdit.id}">
                        <li class="active"> Thêm thông tin tòa nhà </li>
                    </c:if>
                </ul>
            </div>

            <div class="page-content">
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="buildingEdit" id="listForm" method="GET">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form">

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="name" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Quận</label>
                                    <div class="col-xs-2">
                                        <label class="name">Quận hiện có</label>
                                        <form:select path="district" class="form-control">
                                            <form:option value="">--Chọn quận---</form:option>
                                            <form:options items="${districts}"/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <form:input path="ward" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <form:input path="street" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Kết cấu</label>
                                    <div class="col-xs-9">
                                        <form:input path="structure" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <form:input path="numberOfBasement" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích sàn</label>
                                    <div class="col-xs-9">
                                            <form:input path="floorArea" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                            <form:input path="direction" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hạng</label>
                                    <div class="col-xs-9">
                                            <form:input path="level" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                            <form:input path="rentArea" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                            <form:input path="rentPrice" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
                                            <form:input path="rentPriceDescription" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                            <form:input path="serviceFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí oto</label>
                                    <div class="col-xs-9">
                                        <form:input path="carFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí moto</label>
                                    <div class="col-xs-9">
                                        <form:input path="motoFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
                                            <form:input path="overtimeFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền nước</label>
                                    <div class="col-xs-9">
                                            <form:input path="waterFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
                                            <form:input path="electricityFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                            <form:input path="deposit" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
                                            <form:input path="payment" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
                                            <form:input path="rentTime" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
                                            <form:input path="decorationTime" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
                                            <form:input path="managerName" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">SĐT quản lý</label>
                                    <div class="col-xs-9">
                                            <form:input path="managerPhone" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                            <form:input path="brokerageFee" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Loai tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
                                            <form:input path="note" cssClass="form-control"/>
                                    </div>
                                </div>

                                <%--Thao tác với ảnh--%>
                                <div class="form-group">
                                    <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                    <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                                    <div class="col-sm-9">
                                        <c:if test="${not empty buildingEdit.image}">
                                            <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                            <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                        </c:if>
                                        <c:if test="${empty buildingEdit.image}">
                                            <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-9 col-xs-push-3">
                                        <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty buildingEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Sửa tòa nhà</button>
                                        </c:if>
                                        <c:if test="${empty buildingEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                        </c:if>
                                        <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                    </div>
                                </div>
                                <form:hidden path="id" id="buildingId"/>
                            </form>
                        </div>
                    </form:form>
                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<script>
    //Thêm hoặc sửa tòa nhà
    $('#btnAddOrUpdateBuilding').click(function(){
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i, v){
            if(v.name != 'typeCode'){
                data["" + v.name + ""] = v.value;
            }
            else{
                typeCode.push(v.value);
            }
        })
        data['typeCode'] = typeCode;
        if(typeCode != '' && data['rentArea'] != '' && data['district'] != ''){
            addOrUpdateBuilding(data)
        }
        else{
            window.location.href = "<c:url value="/admin/building-edit?typeCode=require"/>";
        }
    });

    function addOrUpdateBuilding(data){
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Suscess");
            },
            error: function(respond){
                console.log("Faile");
                window.location.href = "<c:url value="/admin/building-list"/>";
                console.log(respond);
            }
        });
    }

    //Hủy thao tác
    $('#btnCancel').click(function () {
        window.location.href = "/admin/building-list";
    });

    //Thao tác với ảnh
    var imageBase64 = '';
    var imageName = '';

    $("#btnAddOrUpdateBuilding").click(function () {
        var data = {};
        var formData = $("#listForm").serializeArray();
        $.each(formData, function (i, e) {
            if ('' !== e.value && null != e.value) {
                data['' + e.name + ''] = e.value;
            }

            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        var imageId = data['id'];

        $('#loading_image').show();

        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                $('#loading_image').hide();
                showMessageConfirmation("Thành công", "Thao tác thành công!", "success", "/admin/building-edit-" + res.id);
            },
            error: function () {
                $('#loading_image').hide();
                var redirectUrl = (null === imageId) ? "" : "/admin/building-edit-" + imageId;
                showMessageConfirmation("Thất bại", "Đã có lỗi xảy ra! Vui lòng kiểm tra lại.", "warning", redirectUrl);
            }
        });
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name;
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
