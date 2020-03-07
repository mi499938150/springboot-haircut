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
                            <th>类目编号</th>
                            <th>名字</th>
                            <th>类型编号</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoryInfoPage.list as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${(category.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
                            <td>${(category.updateTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
                            <td><a href="/haircut/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if categoryInfoPage.getPageNum() lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/haircut/seller/category/list?page=${categoryInfoPage.getPageNum() - 1}&size=${categoryInfoPage.getPageSize()}">上一页</a></li>
                    </#if>

                    <#list 1..categoryInfoPage.getPages() as index>
                        <#if categoryInfoPage.getPageNum() == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/haircut/seller/category/list?page=${index}&size=${categoryInfoPage.getPageSize()}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if categoryInfoPage.getPageNum() gte categoryInfoPage.getPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/haircut/seller/category/list?page=${categoryInfoPage.getPageNum() + 1}&size=${categoryInfoPage.getPageSize()}">下一页</a></li>
                    </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>