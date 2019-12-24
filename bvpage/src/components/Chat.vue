<template>
    <div class="component-chat">
        <div class="chat-messages" id="content">
            <div v-for="(message, index) in messages" :key="index" class="single-message" >
                <div v-if="message.userId == userId" class="self-box" style="justify-content: flex-end;">
                    <div class="self" v-html="message.content"/>
                    <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg"/>
                </div>
                <div v-else class="self-box">
                    <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg"/>
                    <div class="other" v-html="message.content"/>
                </div>
            </div>
        </div>
        <div class="editor-box">
            <Wangeditor ref="editor" @commitContent="commitContent"/>
        </div>
    </div>
</template>

<script>
import io from 'socket.io-client'
import  Wangeditor from '@/components/Wangeditor';

export default {
    components:  { Wangeditor },
    props: {
        userId: { type: String },    // 当前用户 id
        chatId: { type: String },    // 当前聊天窗 id (接受人 id 或者接收群组 id)
    },
    data() {
        return {
            contentUrl: 'http://127.0.0.1:9099',
            messages: [  ],
            socket: null
        }
    },
    watch: {
    },
    mounted() {
        let _this = this;
        let opts = {
            query: `chatId=${this.chatId}`
        };
        // socketIo连接的服务器信息，就是我们后端配置的信息
        let socket = io.connect(this.contentUrl, opts);
        this.socket = socket;
        socket.on('connect', function () {
            console.log('连接成功')
        });
        // 接收后端发送过来的消息
        socket.on(this.chatId, function (data) {
            console.log(data);
            _this.messages.push(data);
        });
        socket.on('disconnect', function () {
            console.log('已经下线')
        });
         //收到有新的人加入房间的信息
        socket.on('system', function(data){
            console.log(data);
        });
       //收到我离开的信息
        socket.on('leavehint', function(data){
                console.log(data);
        });
        // 滚动条默认在最底部
        this.scrollToBottom();
    },
    created() {
        this.join(chatId);
    },
    methods: {
        // 获取富文本内容
        commitContent(content) {
            let self = this;
            if (content.trim()) {
                var messgae = {
                    userId: this.userId,
                    chatId: this.chatId,
                    content: content,
                    type: 1
                }
                // 发送消息
                this.$MessageDao.sendMessage(messgae).then((data) => {
                }).catch((desc) => {
                    this.$Message.error(desc);
                });
            }
            // 清楚已发送的文本
            this.$refs.editor.cleanContent();
        },
        scrollToBottom() {
            this.$nextTick(() => {
                let container = this.$el.querySelector("#content");
                container.scrollTop = container.scrollHeight;
            })
        },
        // 关闭连接
        onclose() {
            this.socket.onclose();
        },
        // 加入房间
        join(name) {
            this.socket.emit('join', name);
        },
        // 离开房间
        leave(name) {
            this.socket.emit('leave', name);
        }
    },
    computed: {
    },
    updated() {
        this.scrollToBottom();
    },
};
</script>

<style lang="scss">
.component-chat {
    .chat-messages {
        overflow: auto;
        height: 60%;
        padding: 16px;
        background-color: #e8eaec;
        .single-message {
            height: auto;
            margin-top: 16px;
            .self-box {
                display: flex;
                div {
                    margin-left: 12px;
                    margin-right: 12px;
                    padding: 10px;
                    border-radius: 5px;
                    max-width: 400px;
                }
                .self {
                    background-color: rgb(157, 234, 106);
                    display: flex;
                }
                .other {
                    background-color: #fff;
                }
            }
        }
    }
    .editor-box {
        height: 40%;
        .w-e-text-container {
            height: 140px !important;
        }
    }   
}
</style>
