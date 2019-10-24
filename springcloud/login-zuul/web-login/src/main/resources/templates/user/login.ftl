<@override name="title">登录页</@override>

<@override name="css">
    <link rel="stylesheet" type="text/css" href="${cdn}/css/login.css?v=0.1">
</@override>

<@override name="content">
    <div class="login-main" id="login-page">
        <header class="layui-elip" style="width: 82%">登录页</header>

        <!-- Layui Form表单 -->
        <form class="layui-form" action="/login-page" method="post">
            <!-- 用户名 -->
            <div class="layui-input-inline">
                <input class="layui-input" id="username" name="username" type="text"  placeholder="用户名" autocomplete="false" style="width: 155%">
            </div>

            <!-- 密码 -->
            <div class="layui-input-inline">
                <input class="layui-input" id="password" name="password" type="password"  placeholder="密码" autocomplete="false" style="width: 155%">
            </div>

            <!-- 按钮 -->
            <div class="login-btn" style="padding-left: 105px">
                <button class="layui-btn" type="submit">登录</button>
            </div>

        </form>
    </div>
</@override>

<@override name="js">
    <script type="text/javascript" src="${cdn}/js/login/login.js"></script>
</@override>

<@extends name="../layout/base.ftl"/>