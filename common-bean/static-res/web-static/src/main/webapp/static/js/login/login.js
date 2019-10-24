;(function (window) {
    // 此处定义后台访问路径
    var url = {
        login: '/user/success'
    };

    // Vue对象
    var login = new Vue({
        el: '#login-page',    // Vue与页面绑定
        data: {               // 定义变量

        },
        created: function(){  // 页面初始化时执行的方法

        },
        watch: {

        },
        // 定义方法
        methods: {
            login: function () {
                var param = {
                    userName: $("#userName").val(),
                    password: $("#password").val()
                };

                $.ajax({
                    url: url.login,
                    type: 'post',
                    dataType: 'json',
                    data: param,
                    success: function (resp) {
                        if (resp.status == 1){
                            window.location.href = "user/success";
                        }
                    },
                    error: function (resp) {

                    }
                });
            }
        }
    });

})(window || {});