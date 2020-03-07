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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>预约编号</th>
                            <th>预约名称</th>
                            <th>预约日期</th>
                            <th>预约时间</th>
                            <#--<th>图片</th>-->
                            <th>价格</th>
                            <#--<th>库存</th>-->
                            <#--<th>描述</th>-->
                            <th>类目编号</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list appointInfoPage.list as appointInfo>
                        <tr>
                            <td>${appointInfo.appointId}</td>
                            <td>${(appointInfo.appointName)!''}</td>
                            <#--<td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>-->
                            <td>${(appointInfo.appointWeek?string('yyyy-MM-dd'))!''}</td>
                            <td>${(appointInfo.appointTime)!''}</td>
                            <td>${(appointInfo.appointPrice)!''}</td>
                            <#--<td>${productInfo.productStock}</td>-->
                            <#--<td>${productInfo.productDescription}</td>-->
                            <td>${(appointInfo.categoryType)!''}</td>
                            <td>${(appointInfo.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
                            <td>${(appointInfo.updateTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
                            <td><a href="/haircut/seller/appoint/index?appointId=${appointInfo.appointId}">修改</a></td>
                            <td>
                                <#if appointInfo.getAppointStatusEnum().message == "上架">
                                    <a href="/haircut/seller/appoint/off_sale?appointId=${appointInfo.appointId}">下架</a>
                                <#else>
                                    <a href="/haircut/seller/appoint/on_sale?appointId=${appointInfo.appointId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if appointInfoPage.getPageNum() lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/haircut/seller/appoint/list?page=${appointInfoPage.getPageNum() - 1}&size=${appointInfoPage.getPageSize()}">上一页</a></li>
                    </#if>

                    <#list 1..appointInfoPage.getPages() as index>
                        <#if appointInfoPage.getPageNum() == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/haircut/seller/appoint/list?page=${index}&size=${appointInfoPage.getPageSize()}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if appointInfoPage.getPageNum() gte appointInfoPage.getPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/haircut/seller/appoint/list?page=${appointInfoPage.getPageNum() + 1}&size=${appointInfoPage.getPageSize()}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
x`
</div>
</body>
</html>