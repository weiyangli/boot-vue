<template>
    <Card dis-hover class="chat">
        <!-- 创建群聊弹窗 -->
        <Modal v-if="addGroupModal" ref="modal" v-model="addGroupModal"
            title="创建群聊"
            footer-hide
            :mask-closable="false"
            class="message-modal">
            <AddGroup :userId="`${user.id}`" @exitCreate="addGroupModal = false;"/>
        </Modal>
        <div class="chat-list">
            <div class="serach-input">
                <Input search enter-button placeholder="搜索"/>
                <Button icon="md-add" size="small" title="添加小组" @click="openChatWindow = false; addGroupModal = true;"/>
            </div>
            <div class="chat-name">
                <div v-for="(chat, index) in chats" :key="index" @click="changeGroup(chat)" :class="{ actived: currentChat && chat.chatId == currentChat.chatId }" class="chat-person">
                    <Avatar :src="chat.picture || 'https://i.loli.net/2017/08/21/599a521472424.jpg'" />
                    <span class="person">{{ chat.name }}</span>
                </div>
            </div>
        </div>
        <Chat v-if="openChatWindow" class="chat-box" :chatId="`${currentChat.chatId}`" :userId="`${user.id}`" :type="currentChat.type"/>
    </Card>
</template>

<script>
import Chat from '@/components/Chat';
import AddGroup from './AddGroup';

export default {
    components: { Chat, AddGroup },
    data() {
        return {
            users: [],
            groups: [],
            currentChat: null,
            openChatWindow: false,
            user: null,
            addGroupModal: false,
            chats: [],
        }
    },
    mounted() {
    },
    created() {
        // 查询当前登陆用户
        this.findCurrentUser();
        // 查询所有可聊天用户
        this.findUsers();
    },
    methods: {
        // 切换用户同时切换聊天窗口
        changeGroup(chat) {
            this.openChatWindow = false;
            this.currentChat = chat;
            // 查询和当前交流对象的历史记录
            let self = this;
            setTimeout( () => {
                this.openChatWindow = true;
            }, 500)
        },
        // 查询聊天用户集合
        async findUsers() {
            let self = this;
            await this.$UserDao.findUsers().then((data) => {
                self.users.push(...data);
                // 查询所有也加入群聊
                self.findGroup();
            }).catch((desc) => {
                self.$Message.error(desc);
            });
        },
        // 查询加入的所有群聊
         findGroup() {
            let self = this;
            this.$MessageDao.findGroupByUserId().then((data) => {
                self.groups.push(...data);
                // 将群聊和用户数据和并
                self.mergingData();
            }).catch((desc) => {
                self.$Message.error(desc);
            });
        },
        // 合并数据
        mergingData() {
            this.chats = [];
            // 合并用户数据
            for (let user of this.users) {
                let chatId = parseInt(this.user.id) - parseInt(user.id) > 0 ? `${user.id}_${this.user.id}` : `${this.user.id}_${user.id}`;
                this.chats.push({ chatId: chatId, name: user.nickname, picture: user.picture, type: 1 });
            }
            // 合并群组数据
            for (let group of this.groups) {
                this.chats.push({ chatId: group.id, name: group.groupName, picture: group.picture, type: 2 });
            }
        },
        // 查询当前登录用户
        findCurrentUser() {
            let self = this;
            this.$UserDao.findCurrentUser().then((data) => {
                self.user = data;
            }).catch((desc) => {
                self.$Message.error(desc);
            });
        },
    },
    computed: {
    },
    watch: {
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
