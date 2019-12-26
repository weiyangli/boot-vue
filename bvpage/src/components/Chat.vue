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
import  Wangeditor from '@/components/Wangeditor';

export default {
    components:  { Wangeditor },
    props: {
        userId: { type: String },    // 当前用户 id
        chatId: { type: String },    // 当前聊天窗 id (接受人 id 或者接收群组 id)
        type: { type: Number },      // 消息类型 1： 普通消息 2：群组消息
    },
    data() {
        return {
            contentUrl: '/ws/chat/point',
            messages: [ ],
            stompClient: null
        }
    },
    watch: {
    },
    mounted() {
        // 连接 ws 
        this.connection();
        // 滚动条默认在最底部
        this.scrollToBottom();
    },
    created() {
        this.pullMessages();
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
                    receiveId: 2,
                    type: 1
                }
                // 发送消息
                this.sendMessage(messgae);
            }
            // 清楚已发送的文本
            this.$refs.editor.cleanContent();
        },
        // 滚动条在最底部
        scrollToBottom() {
            this.$nextTick(() => {
                let container = this.$el.querySelector("#content");
                container.scrollTop = container.scrollHeight;
            })
        },
        // 连接 ws
        connection() {
            // 建立连接对象
            let socket = new SockJS(this.contentUrl);
            // 获取STOMP子协议的客户端对象
            this.stompClient = Stomp.over(socket);
            // 定义客户端的认证信息,按需求配置
            let headers = {
                Authorization:''
            }
            // 向服务器发起websocket连接
            this.stompClient.connect(headers,() => {
                // 订阅服务端提供的某个 topic
                this.stompClient.subscribe(`/user/${this.chatId}/topic/chat`, (data) => { 
                    this.messages.push(JSON.parse(data.body));
                });
            }, (err) => {
                // 连接发生错误时的处理函数
                console.log(err);
            });
        }, 
        // 发送消息到服务端
        sendMessage(messgae) {
            let headers = {
                Authorization:''
            }
            this.stompClient.send("/ws/chat", headers, JSON.stringify(messgae));
        },
        // 断开连接 ws
        disconnect() {
            if (this.stompClient) {
                this.stompClient.disconnect();
            }
        },
        // 拉取当前聊天窗口历史信息
        pullMessages() {
            let self = this;
            this.$MessageDao.pullMessages(this.chatId, this.type).then((data) => {
                self.messages.push(...data);
            }).catch((desc) => {
                this.$Message.error(desc);
            });
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
                    /*max-width: 400px;*/
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
            height: 150px !important;
        }
    }   
}
</style>
