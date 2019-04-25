layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //登录按钮
    form.on("submit(login)",function(data){
        var that=$(this)
        that.text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        $.ajax({
            url:context+'/pc/login',
            method:'post',
            dataType:'json',
            data:{'username':data.field.username,'password':data.field.password},
            success:function (result) {
                if(result.code==0){
                    layer.msg("登陆成功",{icon:1,shade:0.4,time:3000})
                    location.href = context + 'pc/main'
                }else{
                    that.text("登录").removeAttr("disabled").removeClass("layui-disabled");
                    layer.msg("登陆失败",{icon:5,shade:0.4,time:3000})
                    // location.href = context + 'pc/index'

                }
            }

        })
        return false;

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
