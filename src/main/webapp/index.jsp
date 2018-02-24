<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type=" text/javascript">
    var websocket = null;

    function onLine() {

//判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://" + document.location.host + "/testws/ws/");
        } else {
            alert('当前浏览器 Not support websocket')
        }

//连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };

//连接成功建立的回调方法
        websocket.onopen = function () {
            setMessageInnerHTML("WebSocket连接成功");
        }

//接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        }

//连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("WebSocket连接关闭");
        }

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }

//关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }
    }

    function setMessageInnerHTML(msg) {
        $("#showMsg").append("<br>" + msg);
    }

    function send() {
        if (websocket.readyState != 1) {
            alert("WebSoket未连接");
            return;
        }

        var val = $("#val").val();
        if (val == undefined || '' == val) {
            alert("消息为空");
            return;
        }

        websocket.send(val);
    }

</script>
<h2>WebSoket-DEMO</h2>
<button onclick="onLine()" id="btn_Be">开始连接</button>
<div id="showMsg"></div>

<hr>

<input type="text" id="val" placeholder="输入消息">
<button onclick="send()">发送</button>