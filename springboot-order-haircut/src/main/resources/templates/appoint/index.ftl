<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/haircut/seller/appoint/save" >
                        <div class="form-group">
                            <label>预约名称</label>
                            <input name="appointName" maxlength="12"  required="required"   type="text" class="form-control" value="${(appointInfo.appointName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>预约日期</label>
                            <input name="appointWeek" id="date_week"  required="required" type="date" class="form-control" value="${(appointInfo.appointWeek?string('yyyy-MM-dd'))!''}"/>
                        </div>
                        <div class="form-group">
                            <label>预约时间</label>
                            <select name="appointTime"    class="form-control">
                                <option value="09:00" selected>${(appointInfo.appointTime)!'9:00'}</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="appointPrice" required="required" type="number" class="form-control" value="${(appointInfo.appointPrice)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>类别</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (appointInfo.categoryType)?? && appointInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="appointId" value="${(appointInfo.appointId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">


</script>
</body>

</html>