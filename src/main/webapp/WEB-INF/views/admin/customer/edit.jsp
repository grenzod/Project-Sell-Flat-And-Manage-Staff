<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
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
                                    <label class="col-xs-3">Tên khách hàng</label>
                                    <div class="col-xs-9">
                                        <form:input path="name" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số điện thoại</label>
                                    <div class="col-xs-9">
                                        <form:input path="phone" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Email</label>
                                    <div class="col-xs-9">
                                        <form:input path="email" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên công ty</label>
                                    <div class="col-xs-9">
                                        <form:input path="company" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Nhu cầu</label>
                                    <div class="col-xs-9">
                                        <form:input path="demand" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Sửa khách hàng</button>
                                        </c:if>
                                        <c:if test="${empty customerEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Thêm khách hàng</button>
                                        </c:if>
                                        <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                    </div>
                                </div>
                                <form:hidden path="id" id="customerId"/>
                            </form>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.page-content -->

            <c:forEach var="item" items="${transactionType}">
                <div class="col-xs-12">
                    <div class="col-sm-12">
                        <h3 class="header smaller ligher blue">${item.value}</h3>
                        <button class="btn btn-lg btn-primary" onclick="TransactionType('${item.key}', ${customerEdit.id})">
                            <i class="orange ace-icon fa fa-location-arrow"></i>Add
                        </button>
                    </div>
                    <c:if test="${item.key == 'CSKH'}">
                        <div class="col-xs-12">
                            <table id="tableCSKH" class="table table-striped table-bordered table-hover">
                                <thread>
                                    <tr>
                                        <th>Ngày tạo</th>
                                        <th>Người tạo</th>
                                        <td>Ngày sửa</td>
                                        <td>Người sửa</td>
                                        <th>Chi tiết giao dịch</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thread>

                                <tbody>
                                    <tr>
                                        <td>12/05/2003</td>
                                        <td>Nguyễn Văn A</td>
                                        <td>Gọi điện và tư vấn</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-sx btn-info" data-toggle="tooltip" title="Sửa thông tin giao dịch"
                                                        onclick="UpdateTransaction(1)">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${item.key == 'DDX'}">
                        <div class="col-xs-12">
                            <table id="tableDDX" class="table table-striped table-bordered table-hover">
                                <thread>
                                    <tr>
                                        <th>Ngày tạo</th>
                                        <th>Người tạo</th>
                                        <th>Chi tiết giao dịch</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thread>

                                <tbody>
                                <tr>
                                    <td>12/05/2003</td>
                                    <td>Nguyễn Văn A</td>
                                    <td>Gọi điện và tư vấn</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-sx btn-info" data-toggle="tooltip" title="Sửa thông tin giao dịch"
                                                    onclick="UpdateTransaction(1)">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<%--Modal--%>
<div class="modal fade" id="transactionTypeModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>

            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                    <div class="col-sx-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" class="widget-100">
                        </span>
                    </div>
                </div>
                <input type="hidden" id="customerId" name="customerId" value="">
                <input type="hidden" id="code" name="code" value="">
                <input type="hidden" id="id" name="id" value="">
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btnAddOrUpgradeTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script>
    //Thêm hoặc sửa tòa nhà
    $('#btnAddOrUpdateCustomer').click(function(){
        var data = {};
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i, v){
            data["" + v.name + ""] = v.value;
        })
        if(data['name'] != '' && data['phone'] != ''){
            addOrUpdateCustomer(data)
        }
        else{
            window.location.href = "<c:url value="/admin/customer-edit?don't miss name or phone"/>";
        }
    });

    function addOrUpdateCustomer(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
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

    // chăm sóc khách hàng
    function TransactionType(code, customerId){
        $('#transactionTypeModal').modal();
        $('#code').val(code);
        $('#customerId').val(customerId);
    }

    // Sửa transaction
    function UpdateTransaction(id){
        $('#transactionTypeModal').modal;
        $('#id').val(id);
    }

    $('#btnAddOrUpgradeTransaction').click(function (e){
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['transactionDetail'] = $('#transactionDetail').val();

        AddOrUpgradeTransaction(data);
    });

    function AddOrUpgradeTransaction(data){
        $.ajax({
            type: "POST",
            url: '${customerAPI}/transaction',
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (){
                console.log("success");
                alert("Add transaction sucessfully");
            },
            error: function (){
                console.log("failed");
                window.location.href = <c:url value="admin/customer-edit?message=error"></c:url>
            }
        });
    }
</script>
</body>
</html>
