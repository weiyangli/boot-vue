<template>
    <div class="add-group">
        <div class="add-group-content">
            <Input v-model="group.groupName" placeholder="群聊名称"/>
            <IviewTransfer ref="transfer" @commitKeys="pickUsers" class="trans-box"/>
        </div>
        <div style="display: flex; justify-content: flex-end">
            <Button style="margin-right: 10px" @click="exitCreate">取消</Button>
            <Button type="primary" @click="createGroup">创建</Button>
        </div>
    </div>
</template>

<script>
import  IviewTransfer from '@/components/IviewTransfer';

export default {
    props: {
        userId: { type: String },    // 当前用户 id
    },
    components: { IviewTransfer },
    data() {
        return {
            group: {
                groupName: '',
                userIds: [],
            }
        };
    },
    created() {
    },
    methods: {
        // 选中的用户
        pickUsers(ids) {
            this.group.userIds = ids;
        },
        // 退出创建群组
        exitCreate() {
            this.$refs.transfer.resetData();
            this.$emit("exitCreate");
        },
        // 创建小组
        createGroup() {
            if (!this.group.groupName.trim()) {
                this.$Message.warning('请输入群名称');
                return;
            }
            if (this.group.userIds.length == 0) {
                this.$Message.warning("请选择群成员");
                return;
            }
            let self = this;
            // 将当前用户放入群聊中
            this.group.userIds.push(this.userId);
            this.$MessageDao.createdGroup(this.group).then((data) => {
                self.group.userIds = [];
                self.group.groupName = '';
                self.$Message.info("创建成功!");
                self.$emit("exitCreate");
            }).catch((desc) => {
                self.$Message.error(desc);
            });
        }
    }
};
</script>

<style lang="scss">
.add-group {
    .add-group-content {
        .trans-box {
            margin-top: 12px;
        }
    }
}
</style>
