<template>
    <div class="component-chat">
        <Button @click="queueSend">发送</Button>
    </div>
</template>

<script>
var stompClient = null;

export default {
    data() {
        return {
            path:"ws://localhost/webSocket/1/2",
            socket:""
        }
    },
    mounted() {
        // 初始化
        this.connect ();
    },
    created() {
    },
    methods: {
        connect (){
            let that = this
            let url = this.path;
            var socket = new WebSocket(url);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                let MessageDto = {message: this.buildId }
                stompClient.send(url, {}, JSON.stringify(MessageDto));  // 前端向服务器发送请求
                stompClient.subscribe(url, function (data) {   // 后端主动向前端推送数据
                    let res = JSON.parse(data.body)
                    that.pumpList = res.data.list
                });
            });
      },
      // 向服务器发送消息（服务器将发送给发送者）
      queueSend() {    // 前端向服务器发送请求
        let MessageDto = {
          message: "咯哦lol"
        }
        // 参数依次为 发送地址，header，消息
        stompClient.send(url, {}, JSON.stringify(MessageDto));
      },
    },
    computed: {
    }
};
</script>

<style lang="scss">

</style>
