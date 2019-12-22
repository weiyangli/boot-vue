<template>
    <div class="component-chat">
        <div class="chat-messages" id="content">
            <div v-for="(message, index) in messages" :key="index" class="single-message" >
                <div v-if="message.chatId == userId" class="self-box" style="justify-content: flex-end;">
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
        chatId: { type: String },    // 当前聊天窗 id (接受人 id 或者接收群组 id)
    },
    data() {
        return {
            contentUrl: 'http://127.0.0.1:9099',
            messages: [  ],
            userId: 1,
        }
    },
    mounted() {
        let _this = this;
        let opts = {
            query: `chatId=${this.chatId}`
        };
        // socketIo连接的服务器信息，就是我们后端配置的信息
        let socket = io.connect(this.contentUrl, opts)
        socket.on('connect', function () {
            console.log('连接成功')
        });
        // 接收后端发送过来的消息
        socket.on('push_event', function (data) {
            console.log(data);
            _this.messages.push(data);
        });
        socket.on('disconnect', function () {
            console.log('已经下线')
        });
        // 滚动条默认在最底部
        this.scrollToBottom();
    },
    created() {
    },
    methods: {
        // 获取富文本内容
        commitContent(content) {
            if (content.trim()) {
                let message = {
                    content: content,
                    chatId: 1,
                    title: ''
                }
                this.messages.push(message);
            }
            // 清楚已发送的文本
            this.$refs.editor.cleanContent();
        },
        scrollToBottom() {
            this.$nextTick(() => {
                let container = this.$el.querySelector("#content");
                container.scrollTop = container.scrollHeight;
            })
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
            height: 250px !important;
        }
    }
}
</style>
