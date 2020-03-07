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
                            <th>openid</th>
                            <th>姓名</th>
                            <th>头像</th>
                            <th>性别</th>
                            <th>国籍</th>
                            <th>省份</th>
                            <th>城市</th>
                        </tr>
                        </thead>
                        <tbody >

                        <#list weChatUserInfoPage.list as user>
                        <tr>
                            <td style="width: 100px" width="20px;">${user.openId}</td>
                            <td>${user.nickName}</td>
                            <td ><img style="width: 50px;height: 50px;"  src="${user.headImgUrl}"/></td>
                            <td>${user.sexDesc}</td>
                            <td>${user.country}</td>
                            <td>${user.province}</td>
                            <td>${user.city}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if weChatUserInfoPage.getPageNum() lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/haircut/seller/customer/list?page=${weChatUserInfoPage.getPageNum() - 1}&size=${weChatUserInfoPage.getPageSize()}">上一页</a></li>
                    </#if>

                    <#list 1..weChatUserInfoPage.getPages() as index>
                        <#if weChatUserInfoPage.getPageNum() == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/haircut/seller/customer/list?page=${index}&size=${weChatUserInfoPage.getPageSize()}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if weChatUserInfoPage.getPageNum() gte weChatUserInfoPage.getPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/haircut/seller/customer/list?page=${weChatUserInfoPage.getPageNum() + 1}&size=${weChatUserInfoPage.getPageSize()}">下一页</a></li>
                    </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>