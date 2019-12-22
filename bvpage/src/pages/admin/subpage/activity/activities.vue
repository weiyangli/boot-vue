<template>
    <div class="activities">
        活动信息<br/>
        {{ messages }}
    </div>
</template>

<script>
import io from 'socket.io-client'

export default {
    data() {
        return {
            messages: [ ]
        }
    },
    mounted() {
        let _this = this
        let opts = {
            query: 'loginUser=lwy'
        }
        // socketIo连接的服务器信息，就是我们后端配置的信息
        let socket = io.connect('http://127.0.0.1:9099', opts)
        socket.on('connect', function () {
            console.log('连接成功')
        })
        // 接收后端发送过来的消息
        socket.on('push_event', function (data) {
            _this.messages.push(data);
        })
        socket.on('disconnect', function () {
            console.log('已经下线')
        })
    },
    created() {
    },
    methods: {
    },
    computed: {
    }
};
</script>

<style lang="scss">

</style>
