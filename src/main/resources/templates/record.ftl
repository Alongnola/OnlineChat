<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>在线聊天室</title>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/build.css"/>
    <link rel="stylesheet" type="text/css" href="/css/qq.css"/>
</head>
<body>

<div class="qqBox">
    <div class="context">
        <div class="conRight">
            <div class="Righthead">
                <div class="headName"><b>聊天记录</b></div>
            </div>

            <div class="RightCont">
                <ul class="newsList" id="message">
                    <#list records as record>
                        <li>
                          <div class="nesHead">
                            <img src= ${record.headUrl}>
                          </div>
                          <div class="news" style="line-height: 20px">
                            ${record.created_date}:<br/>
                              ${record.content}
                          </div>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>