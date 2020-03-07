<#include "../common/header.ftl">

<h1 class="title-center">门店预约平台管理系统</h1>
<form class="form-horizontal templatemo-signin-form" role="form" action="/haircut/admin/tologin/" method="post" onSubmit="return beforeSubmit(this);">
    <div class="form-group">
        <div class="col-md-12">
            <h3 class="col-md-5 col-md-offset-4">店主登录</h3>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <label for="l-username" class="col-sm-4 control-label"  >用户名</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" required="required"   name="username" placeholder="Username">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <label for="l-password" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" required="required" name="password" placeholder="Password">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <label for="l-password" class="col-sm-12 control-label" style="color: red">${(msg)!''}</label>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <div class="col-sm-offset-4 col-sm-8">
                <button class="btn btn-primary login-button">登录</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    function beforeSubmit(form){
        if(form.username.value==''){
            alert('用户名不能为空！');
            form.username.focus();
            return false;
        }
        if(form.password.value==''){
            alert('密码不能为空！');
            form.password.focus();
            return false;
        }