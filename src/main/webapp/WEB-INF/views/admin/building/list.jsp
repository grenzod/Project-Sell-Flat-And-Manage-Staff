<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>

<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                        <li class="active">
                            Danh sách tòa nhà
                        </li>
                    </ul>
                </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box ui-sortable-handle">
                            <div class="widget-header">
                                <h5 class="widget-title">Tìm kiếm</h5>

                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="widget-body" style="font-family: 'Times New Roman', Times, serif; font-size: medium;">
                                <div class="widget-main">
                                    <form:form modelAttribute="modelSearch" id="listForm" action="${buildingListURL}" method="GET">
                                        <div class="row">
                                            <div class="from-group">
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <label class="name">Tên tòa nhà</label>
                                                        <form:input path="name" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="name">Diện tích sàn</label>
                                                        <form:input path="floorArea" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-2">
                                                        <label class="name">Quận hiện có</label>
                                                        <form:select path="district" class="form-control">
                                                            <form:option value="">--Chọn quận---</form:option>
                                                            <form:options items="${districts}"/>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-xs-5">
                                                        <label class="name">Phường</label>
                                                        <form:input path="ward" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-5">
                                                        <label class="name">Đường</label>
                                                        <form:input path="street" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-4">
                                                        <label class="name">Số tầng hầm</label>
                                                        <form:input path="numberOfBasement" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label class="name">Hướng</label>
                                                        <form:input path="direction" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label class="name">Hạng</label>
                                                        <form:input path="level" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-4">
                                                        <label class="name">Diện tích thuê từ</label>
                                                        <form:input path="areaFrom" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label class="name">Diện tích thuê đến</label>
                                                        <form:input path="areaTo" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-2">
                                                        <label class="name">Giá thuê từ</label>
                                                        <form:input path="rentPriceFrom" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-2">
                                                        <label class="name">Giá thuê đến</label>
                                                        <form:input path="rentPriceTo" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-4">
                                                        <label class="name">Tên quản lý</label>
                                                        <form:input path="managerName" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label class="name">Số điện thoại quản lý</label>
                                                        <form:input path="managerPhone" cssClass="form-control"/>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <security:authorize access="hasRole('MANAGER')">
                                                            <div>
                                                                <label class="name">Chọn nhân viên phụ trách</label>
                                                                <form:select class="form-control" path="staffId">
                                                                    <form:option value="">---Chọn nhân viên---</form:option>
                                                                    <form:options items="${listStaffs}"/>
                                                                </form:select>
                                                            </div>
                                                        </security:authorize>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <button type="button" class="btn btn-danger" id="btnSearchBuilding">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"></path>
                                                            </svg>
                                                            Tìm kiếm
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>

                            <div class="pull-right">
                                <a href="/admin/building-edit">
                                    <button class="btn btn-info" title="Thêm tòa nhà">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </button>
                                </a>

                                <button class="btn btn-danger" title="Xóa tòa nhà" id="btnDeleteBuilding">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Bảng danh sách -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <display:table name="modelSearch.listResult" cellspacing="0" cellpadding="0"
                                           requestURI="${buildingListURL}" partialList="true" sort="external"
                                           size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                           id="tableList" pagesize="${modelSearch.maxPageItems}"
                                           export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">

                                <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                headerClass="center select-cell">
                                    <fieldset>
                                        <input type="checkbox" name="checkList" value="${tableList.id}"
                                               id="checkbox_${tableList.id}" class="check-box-element"/>
                                    </fieldset>
                                </display:column>

                                <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                                <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                                <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                                <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại quản lý"/>
                                <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn"/>
                                <display:column headerClass="text-left" property="emptyArea" title="Diện tích trống"/>
                                <display:column headerClass="text-left" property="rentArea" title="Diện tích thuê"/>
                                <display:column headerClass="text-left" property="brokerageFee" title="Phí môi giới"/>
                                <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                                <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>

                                <display:column headerClass="col-actions" title="Thao tác">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${tableList.id})">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-gift" viewBox="0 0 16 16">
                                                <path d="M3 2.5a2.5 2.5 0 0 1 5 0 2.5 2.5 0 0 1 5 0v.006c0 .07 0 .27-.038.494H15a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a1.5 1.5 0 0 1-1.5 1.5h-11A1.5 1.5 0 0 1 1 14.5V7a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1h2.038A3 3 0 0 1 3 2.506zm1.068.5H7v-.5a1.5 1.5 0 1 0-3 0c0 .085.002.274.045.43zM9 3h2.932l.023-.07c.043-.156.045-.345.045-.43a1.5 1.5 0 0 0-3 0zM1 4v2h6V4zm8 0v2h6V4zm5 3H9v8h4.5a.5.5 0 0 0 .5-.5zm-7 8V7H2v7.5a.5.5 0 0 0 .5.5z"/>
                                            </svg>
                                        </button>
                                    </security:authorize>

                                    <a href="/admin/building-edit-${tableList.id}" class="btn btn-xs btn-info" title="Sửa tòa nhà">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>

                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${tableList.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>

                                </display:column>

                            </display:table>
                        </div>
                    </div>
                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

    <div class="modal fade" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body" class="center">
                    <table class="table table-striped table-bordered table-hover" id="staffList">
                        <thead>
                        <tr>
                            <th class="center">Chọn</th>
                            <th class="center">Tên nhân viên</th>
                        </tr>
                        </thead>

                        <tbody>

                        </tbody>
                    </table>
                    <input type="hidden" id="buildingId" name="Building" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnassignmentBuilding">Giao tòa nhà</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

    <%--Các chức năng với tòa nhà--%>
    <script>
        //Giao tòa nhà
        function assignmentBuilding(buildingId){
            $('#assignmentBuildingModal').modal();
            loadStaff(buildingId);
            $('#buildingId').val(buildingId);
        }

        function loadStaff(buildingId){
            $.ajax({
                type: "GET",
                url: "${buildingAPI}/" + buildingId + '/staffs',
                dataType: "JSON",
                success: function (response) {
                    var row = '';
                    $.each(response.data, function (index, item) {
                        row += '<tr>';
                        row += '<td class="text-center">' +
                            '<input type = "checkbox" value=' + item.staffId + ' id = "checkbox_' + item.staffId + '"classs = "check-box-element"' +
                            item.checked + '/></td>';
                        row += '<td class="text-center">' + item.fullName + '</td>';
                        row += '</tr>';
                    });
                    $('#staffList tbody').html(row);
                    console.info("Success");
                },
                error: function(response){
                    console.log("Faile");
                    window.location.href = "<c:url value="/admin/building-list?message=error"/>";
                    console.log(response);
                }
            });
        }

        $('#btnassignmentBuilding').click(function(e){
            e.preventDefault();
            var data = {};
            data['buildingId'] = $('#buildingId').val();
            var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            data['staffs'] = staffs;
            if(data['staffs'] != ''){
                assignment(data);
            }
            console.log("OK");
        })

        function assignment(data){
            $.ajax({
                type: "POST",
                url: "${buildingAPI}/" + 'assigment',
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function (response) {
                    console.log("Success");
                },
                error: function(response){
                    console.log("Faile");
                    <%--window.location.href = "<c:url value="/admin/building-list"/>";--%>
                    console.log(response);
                }
            });
        }


        // Tìm kiếm tòa nhà
        $('#btnSearchBuilding').click(function(e){
            e.preventDefault();
            $('#listForm').submit();
        });


        // Xóa tòa nhà
        function deleteBuilding(id){
            var buildingId = [id];
            deleteBuildings(buildingId);
        }

        $('#btnDeleteBuilding').click(function(e){
            e.preventDefault();
            var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            deleteBuildings(buildingIds);
        })

        function deleteBuildings(data) {
            $.ajax({
                type: "DELETE",
                url: "${buildingAPI}/" + data,
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
    </script>
</body>
</html>
