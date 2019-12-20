<template>
    <div class="users">
        <Menu theme="light" active-name="user-list" class="users-menu">
            <MenuItem name="user-list" to="user-list">
                <Icon type="md-document" />
                用户管理
            </MenuItem>
            <MenuItem name="role-list" to="role-list">
                <Icon type="md-chatbubbles" />
                角色管理
            </MenuItem>
            <MenuItem name="chat" to="chat">
                <Icon type="md-call" />
                沟通交流
            </MenuItem>
        </Menu>
        <div class="table-box">
            <router-view/>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
        }
    },
    mounted() {
    },
    created() {
        // 默认跳转用户列表
        this.$router.push("user-list");
    },
    methods: {
    },
    computed: {
    }
};
</script>

<style lang="scss">
.users {
    display: flex;
    flex-direction: row;
    height:calc(100vh - 60px);
    .users-menu {
        height: 100%;
    }
    .table-box {
        padding: 16px;
        width: 100%;
    }
}
</style>
