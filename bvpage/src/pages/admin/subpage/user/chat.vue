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
        <Chat class="chat-box" :chatId="chatId"/>
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
            chatId: '2',
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
    }
}
</style>
