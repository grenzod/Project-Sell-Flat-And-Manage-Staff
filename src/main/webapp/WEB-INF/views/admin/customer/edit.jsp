<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thông tin khách hàng</title>
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
                    <c:if test="${not empty customerEdit.id}">
                        <li class="active"> Sửa thông tin khách hàng </li>
                    </c:if>
                    <c:if test="${empty customerEdit.id}">
                        <li class="active"> Thêm thông tin khách hàng </li>
                    </c:if>
                </ul>
            </div>

            <div class="page-content">
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="customerEdit" id="listForm" method="GET">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form">

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="name" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="customerPhone" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="email" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="companyName" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="demand" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Sửa tòa nhà</button>
                                        </c:if>
                                        <c:if test="${empty customerEdit.id}">
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
            window.location.href = "<c:url value="/admin/customer-edit?typeCode=require"/>";
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
                window.location.href = "<c:url value="/admin/customer-list"/>";
                console.log(respond);
            }
        });
    }

    //Hủy thao tác
    $('#btnCancel').click(function () {
        window.location.href = "/admin/customer-list";
    });
</script>
</body>
</html>
