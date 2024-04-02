$(function () {
    var inputList=$(".weui-input");
    inputList.each(function () {
        var dome=$(this);
        if(dome!=undefined){
            if(dome.attr("checkForm")=="nullcard"){
                dome.blur(function () {
                    var check=/^\d{15}(\d{2}[A-Za-z0-9])?$/;
                    if(dome.val()!=''){
                        if(!check.test(dome.val())){
                            $.alert('身份证格式错误');
                            dome.focus();
                            return false;
                        }
                    }
                });
            }
            if(dome.attr("checkForm")=="card"){
                dome.blur(function () {
                    var check=/^\d{15}(\d{2}[A-Za-z0-9])?$/;
                    if(dome.val()!=''){
                        if(!check.test(dome.val())){
                            $.alert('身份证格式错误');
                            dome.focus();
                            return false;
                        }
                    }else {
                        $.alert('身份证不能为空');
                        dome.focus();
                        return false;
                    }
                });
            }
			/*
            if(dome.attr("checkForm")=="phone"){
                dome.blur(function () {
                    var check=/^1[34578]\d{9}$/;
                    if(dome.val()!=''){
                        if(!check.test(dome.val())){
                            $.alert('手机号码格式错误');
                            dome.focus();
                            return false;
                        }
                    }else {
                        $.alert('手机号码不能为空');
                        dome.focus();
                        return false;
                    }
                });
            }
			*/
        }

    });
});