layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
        var that=$(this)
        that.text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        $.ajax({
            url:context+'/pc/login',
            type:'post',
            data:$("#app-form").serialize(),
            success:function (result) {
                if(result.code==0){
                    layer.msg("登陆成功",{icon:1,shade:0.4,time:3000})
                    location.href = context + 'pc/main'
                }else{
                    that.text("登录").removeAttr("disabled").removeClass("layui-disabled");
                    layer.msg("登陆失败",{icon:5,shade:0.4,time:2000})

                }
            }

        })

    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
