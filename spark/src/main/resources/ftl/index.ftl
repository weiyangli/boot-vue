<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fm 模板生成测试</title>
</head>
<body>
<#-- 接收数据处理格式 -->
<#assign records = data?eval>
    <div id="content">
        <ul style="list-style: none">
            <#list records as record>
　　              <li>姓名：${record.name}</li>
                  <li>编号：${record.id}</li>
                 其他信息<br/>
                <#if record.otherInfo??>
                    <li>enabled: ${record.otherInfo.enabled!}</li>
                    <li>orderNum: ${record.otherInfo.orderNum!}</li>
                    <li>phone: ${record.otherInfo.phone!}</li>
                </#if>
                <#if record.userList?? && record.userList?size gt 0>
                    <#list record.userList as user>
                        <p>
                            <strong>id:${user.id!}</strong>
                            <strong>username:${user.username!}</strong>
                            <strong>nickname:${user.nickname!}</strong>
                        </p>
                    </#list>
                </#if>
                <br/>
            </#list>
        </ul>
    </div>
</body>
</html>