<template>
    <Card dis-hover class="chat">
        <div class="chat-list">
            <div class="serach-input">
                <Input search enter-button placeholder="搜索"/>
                <Button icon="md-add" size="small" title="添加小组"/>
            </div>
            <div class="chat-name">
                <div v-for="(user, index) in users" :key="index" @click="changeGroup(user.id)" :class="{ actived: user.id == currentUserId }" class="chat-person">
                    <Avatar :src="user.photo || 'https://i.loli.net/2017/08/21/599a521472424.jpg'" />
                    <span class="person">{{ user.nickname }}</span>
                </div>
            </div>
        </div>
        <Chat v-if="openChatWindow" class="chat-box" :chatId="chatId" :userId="`${user.id}`"/>
    </Card>
</template>

<script>
import Chat from '@/components/Chat';

export default {
    components: { Chat },
    data() {
        return {
            users: [],
            currentUserId: '',
            chatId: '',
            openChatWindow: false,
            user: null
        }
    },
    mounted() {
    },
    created() {
        // 查询当前登陆用户
        this.findCurrentUser();
        // 查询所有用户信息
        this.findUsers();
    },
    methods: {
        // 切换用户同时切换聊天窗口
        changeGroup(userId) {
            this.openChatWindow = false;
            this.currentUserId = userId;
            // 查询和当前交流对象的历史记录
            let self = this;
            setTimeout( () => {
                this.openChatWindow = true;
            }, 500)
        },
        findUsers() {
            let self = this;
            this.$UserDao.findUsers().then((data) => {
                self.users.push(...data);
            }).catch((desc) => {
                this.$Message.error(desc);
            });
        },
        findCurrentUser() {
            let self = this;
            this.$UserDao.findCurrentUser().then((data) => {
                self.user = data;
            }).catch((desc) => {
                this.$Message.error(desc);
            });
        },
    },
    computed: {
    },
    watch: {
     // 切换聊天对象 
      currentUserId(newValue) {
          let userId = this.user.id; 
          this.chatId = parseInt(userId) - parseInt(newValue) < 0 ? `${userId}_${newValue}` : `${newValue}_${userId}`;
      }
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
        justify-content: space-between;
    }
    .chat-list {
        width: 20%;
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
            .chat-person {
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
        width: 80%;
    }
}
</style>
