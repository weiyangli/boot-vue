/**
 * 执行 ajax 请求获取后台数据
 * url: 请求接口的地址(如果后台使用注解 @PathVariable 接值直接将参数拼接到 url 末尾即可)
 * data: 请求接口的数据
 * async: 设置请求为异步或者同步，默认为异步
 * type: 接口的请求方式 get 或者 post(不支持 rest 请求方式)
 * dataType: 后台返回的数据类型
 * contentType: 默认是 application/json;charset=UTF-8， 如果是 form 表单提交使用 application/x-www-form-urlencoded
 */
$.getData = function(options, callback) {
    $.ajax({
        url        : options.url,
        data       : options.data || null,
        async      : options.async || false,
        type       : options.type,
        dataType   : 'json', // 服务器的响应使用 JSON 格式
        contentType: options.contentType || 'application/json;charset=UTF-8',
        headers: {'X-Requested-With': 'XMLHttpRequest'}
    })
    .done(function(data, textStatus, jqXHR) {
        callback(data);
    })
    .fail(function(jqXHR, textStatus, failThrown) {
        console.log(jqXHR.status);
        // error
        var status = jqXHR.status;
        if (401 == status) {
            alert('401: Token 无效');
        } else if (403 == status) {
            alert('403: 权限不够');
        } else if (404 == status) {
            alert('404: URL 不存在');
        } else if (500 == status) {
            // 发生 500 错误时服务器抛出异常，在控制台打印出异常信息
            console.error(jqXHR.responseJSON.data);
            alert(`500: 发生异常，${jqXHR.responseJSON.message}\n\n详细错误信息请查看控制台输出 (Chrome 按下快捷键 F12)`);
        } else if (502 == status) {
            // 发生 502 错误时，Tomcat Web 服务器不可访问，一般有 2 个原因
            // 1. Nginx 配置出错
            // 2. Tomcat 的 Web 服务没启动或者不接收请求
            alert('502: 服务不可访问');
        } else if (504 == status) {
            alert('504: Gateway Timeout\n\n' + jqXHR.responseText);
        }
    })
}