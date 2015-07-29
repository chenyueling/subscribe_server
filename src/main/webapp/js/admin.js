/**
 * 菜单初始化
 */
function initMemu() {
    $toggleTitle = $('.toggle').prev();
    // 初始化隐藏所有二级菜单
    $('.toggle').hide();

    //添加点击显示与隐藏
    $toggleTitle.click(function () {
        var $thisToggle = $(this).next('.toggle');
        $thisToggle.slideToggle('slow');
        $('.toggle').not($thisToggle).hide('slow');
        return false;
    });

    // 添加菜单项点击事件
    var $subLink = $(".toggle").find("a");
    $subLink.click(function () {
        var url = this.href;
        loadJspToMain(url);
        return false;
    });
    //添加一级列表点击事件
    var $superLink = $(".superTitle").find("a");
    $superLink.click(function () {
        var url = this.href;
        loadJspToMain(url);
        return false;
    })
}

function initFrameMemu() {
    $toggleTitle = $('.toggle').prev();
    // 初始化隐藏所有二级菜单
    $('.toggle').hide();

    //添加点击显示与隐藏
    $toggleTitle.click(function () {
        var $thisToggle = $(this).next('.toggle');
        $thisToggle.slideToggle('slow');
        $('.toggle').not($thisToggle).hide('slow');
        return false;
    });

    // 添加菜单项点击事件
    var $subLink = $(".toggle").find("a");
    $subLink.click(function () {
        var url = this.href;
        $('#main').attr("src", url);
        return false;
    });

    //添加一级列表点击事件
    var $superLink = $(".superTitle").find("a");
    $superLink.click(function () {
        var url = this.href;
        $('#main').attr("src", url);
        alert(1);
        return false;
    });


}
/**
 * 初始化easy UI框架 菜单
 * @return
 */
function initMenu() {
    var $subLink = $(".wg_menu").find("a");
    $subLink.click(function () {
        var url = this.href;
        $('#wg_main').attr("src", url);
        return false;
    });
}

/**
 * 后台初始化,主要让mian框可以自适应浏览器宽度
 * @return
 */
function initIndex() {
    var minWidth = 764;
    var minHeight = 300;
    var bodyWidth = $(window).width() - 270;
    var bodyHeight = $(window).height() - 93;
    if (bodyWidth < minWidth) {
        bodyWidth = minWidth;
    }
    if (bodyHeight < minHeight) {
        bodyHeight = minHeight;
    }
    var isIE = !!window.ActiveXObject;
    if (isIE) {
        bodyWidth = bodyWidth + 20;
    }
    $(".mian").css('width', bodyWidth + 'px');
    $(".mian").css('height', bodyHeight + 'px');
    $(window).resize(function () {
        var minWidth = 764;
        var minHeight = 300;
        var bodyWidth = $(window).width() - 270;
        var bodyHeight = $(window).height() - 93;
        if (bodyWidth < minWidth) {
            bodyWidth = minWidth;
        }
        if (bodyHeight < minHeight) {
            bodyHeight = minHeight;
        }
        var isIE = !!window.ActiveXObject;
        if (isIE) {
            bodyWidth = bodyWidth + 20;
        }
        $(".mian").css('width', bodyWidth + 'px');
        $(".mian").css('height', bodyHeight + 'px');
    });
}

/**
 * 读取JSP页面到ID为main的DIV中
 */
function loadJspToMain(url) {
    $.get(url, function (data, textStatus) {
        $("#main").html(data);
    });
}
/**
 * 读取JSP页面到ID为main的DIV中
 * @param url
 * @param params 参数
 * @return
 */
function loadJspToMain(url, params) {
    $.get(url, params, function (data, textStatus) {
        $("#main").html(data);
    });
}

/**
 * 添加css
 * @param src
 * @return
 */
function addCss(src) {
    $("head").append("<link>");
    var $a = jQuery("head").children(":last");
    $a.attr({
        rel: "stylesheet",
        type: "text/css",
        href: src
    });
}

/**
 * 自动填充表单
 * @param form
 * @param row
 * @return
 */
function completeForm(form, row) {
    for (var key in row) {
        var s = "*[wg_name='" + key + "']";
        $(form).find(s).val(row[key]);
        $(form).find(s).text(row[key]);
    }
}

/**
 * 打开读取框
 * @return
 */
function showLoadind() {
    var isLoading = true;
    var value = 0;
    var interval = setInterval(function () {
        value %= 100;
        value += 1;
        $('#wg_progressbar').progressbar('setValue', value);
    }, 50);
    $('#wg_loading').dialog({
        onClose: function () {
            clearInterval(interval);
        }
    });
    $('#wg_loading').dialog('open');
}

/**
 * 关闭读取框
 * @return
 */
function hideLoadind() {
    $('#wg_loading').dialog('close');
}

/**
 * 刷新验证码
 * @return
 */
function refreshCode() {
    var timenow = new Date().getTime();
    //每次请求需要一个不同的参数，否则可能会返回同样的验证码
    //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
    var html = "<img style='width:90px; height: 30px; float: right;margin-left: 10px;' src='randomCode?d=" + timenow + "'/>";
    $('#validataCode').html(html);
    $("input[name='validateCode']").val('');
}

/**
 * 初始化后台登录框
 * @param form
 * @param callback
 * @return
 */
function initLoginForm(form, url, callback) {
//    var data = $form.formSerialize();

    $(form).ajaxForm({
        type: "post",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        beforeSubmit: function () {

            $.messager.progress({
                title: '用户登录',
                msg: '登录中...'
            });
        },
        success: function (json) {
            $.messager.progress('close');
            if (!json) {
                $.messager.alert('用户登录', '未知异常,请联系管理员');
                return;
            }
            if (json.result != "success") {
                $("input[name='password']").val('')
                $.messager.alert('用户登录', json.data);
                refreshCode();
                return;
            } else {
                if (callback) {
                    callback();
                }
            }
        },
        error: function () {
            $.messager.progress('close');
            $("input[name='password']").val('');
            $("input[name='validateCode']").val('');
            $.messager.alert('用户登录', '未知异常,请联系管理员');
        }
    });
    $('#login_submit').bind('click', function () {
        var password = $("input[name='password']").val();
        var username = $("input[name='username']").val();
        var validateCode = $("input[name='validateCode']").val();
        if (username == '') {
            $.messager.alert('用户登录', '请输入用户名');
            return false;
        }
        if (password == '') {
            $.messager.alert('用户登录', '请输入密码');
            return false;
        }
        if (validateCode == '') {
            $.messager.alert('用户登录', '请输入验证码');
            return false;
        }
        console.log(username);
//        var key = getRSAKey();
//        var result = encryptedString(key, encodeURIComponent(password));
//        $("input[name='password']").val(result);
        $(form).submit();

//        var data = {
//            username: $("input[name='username']").val(),
//            password: $("input[name='password']").val()
//        }
//        console.log(data);
//        var options = {
//            type: 'POST',
//            url: '/jersey-jetty/rest/admins/login',
//            contentType: 'application/json',
//            data: JSON.stringify(data),
//            success: function (data) {
//                console.log(data);
//            },
//            error: function () {
//                $.messager.progress('close');
//                $("input[name='password']").val('');
//                $("input[name='validateCode']").val('');
//                $.messager.alert('用户登录', '未知异常,请联系管理员');
//            },
//            dataType: 'json'
//        };
//
//        $.ajax(options)

    });

}

//初始化表单
function initForm(form, url, callback) {
    var data = from.serializeArray();
    alert(data);
    $(form).ajaxForm({
        type: "post",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        data: data,
        beforeSubmit: function () {
            $.messager.progress({
                title: '系统消息',
                msg: '正在提交数据...'
            });
        },
        success: function (json) {
            $.messager.progress('close');
            if (!json) {
                $.messager.alert('系统消息', '未知异常,请联系管理员');
                return;
            }
            if (json.result != "success") {
                $.messager.alert('系统消息', json.data);
                return;
            } else {
                if (callback) {
                    callback();
                }
            }
        },
        error: function () {
            $.messager.progress('close');
            $.messager.alert('系统消息', '未知异常,请联系管理员');
        }
    });
}
