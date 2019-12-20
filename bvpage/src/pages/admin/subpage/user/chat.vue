<template>
    <Card dis-hover class="chat">
        <div class="chat-list">
            <div class="serach-input">
                <Input search enter-button placeholder="搜索"/>
                <Button icon="md-add" size="small" title="添加小组"/>
            </div>
            <div class="chat-name">
                <div v-for="(name,index) in names" :key="index" @click="changeGroup(name)" :class="{ actived: name == pickName }" class="chat-box">
                    <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
                    <span class="person">{{ name }}</span>
                </div>
            </div>
        </div>
        <div class="chat-box">
            <div class="chat-head">
                <h3>{{ pickName }}</h3>
            </div>
            <div class="message-box">
                <div v-for="(message, index) in messages" :key="index" v-html="message" class="single-message"/>
            </div>
            <div class="input-box">
                <Input v-model="message"  type="textarea" placeholder="Enter something..." @on-enter="sendMassage" class="message-input" />
                <Chat/>
            </div>
        </div>
    </Card>
</template>

<script>
import Chat from '@/components/Chat';

export default {
    components: { Chat },
    data() {
        return {
            names: ['张三广东省非规', '李四', '王五','张三', '李四','张三', '李四','张三', '李四','张三', '李四','张三', '李四','张三', '李四','张三', '李四','张三', '李四'],
            pickName: '',
            message: '',
            messages: [],
        }
    },
    mounted() {
    },
    created() {
    },
    methods: {
        changeGroup(name) {
            this.pickName = name;
        },
        // 发送消息
        sendMassage() {
            this.messages.push(this.message);
            this.message = '';
        }
    },
    computed: {
    }
};
</script>

<style lang="scss">
.chat {
    height: 100%;
    .ivu-card-body {
        height: 100%;
        padding: 0;
        display: flex;
        flex-direction: row;
    }
    .chat-list {
        width: 300px;
        border: 1px solid #dcdee2;
        .serach-input{
            padding: 10px;
            border-bottom: 1px solid #dcdee2;
            display: flex;
            button {
                margin-left: 10px;
            }
        }
        .chat-name {
            max-height: calc(100vh - 151px);
            overflow: auto;
            .chat-box {
                border-bottom: 1px solid #dcdee2;
                padding: 10px;
                .person {
                    margin-left: 16px;
                    font-weight: bold;
                    // overflow: hidden;
                    // text-overflow: ellipsis;
                    // white-space: nowrap;
                    cursor: pointer;
                }
            }
            .actived {
                background-color: #dcdee2;
            }
        }
    }
    .chat-box {
        width: 100%;
        .chat-head {
            height: 60px;
            border-bottom: 1px solid #dcdee2;
            display: flex;
            align-items: center;
            padding: 16px;
        }
        .message-box {
            height: 380px;
            border-bottom: 1px solid #dcdee2;
            .single-message {
                padding: 5px;
                margin-bottom: 3px;
            }
        }
        .input-box {
            .message-input {
                height: 100%;
            }
        }
    }
}
</style>
