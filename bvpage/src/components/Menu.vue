<template>
    <div class="menu">
        <Menu mode="horizontal"  theme="dark" :active-name="activeName" @on-select="changeMenu">
            <MenuItem name="user">
                <Icon type="ios-paper" />
                系统管理
            </MenuItem>
            <MenuItem name="activity">
                <Icon type="ios-people" />
                活动管理
            </MenuItem>
            <MenuItem name="analysis">
                <Icon type="ios-stats" />
                统计分析
            </MenuItem>
            <Dropdown class="menu-drop">
                <Button type="primary">
                    菜单
                    <Icon type="ios-arrow-down"></Icon>
                </Button>
                <DropdownMenu slot="list">
                    <DropdownItem @click="logout">注销</DropdownItem>
                </DropdownMenu>
            </Dropdown>
        </Menu>
        <router-view/>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                theme1: 'light',
                activeName: '',
            }
        },
        created() {
            // 第一次进入锁定上一次菜单
            this.gotoMenu();
        },
        methods: {
            // 注销用户
            logout() {
                let self = this;
                // 删除 store和 localStorage 中的 token
                this.$store.commit('SET_LOGOUT');
                this.$SystemDao.logout().then((data) => {
                    // 跳转登录页
                    this.$router.push('/');
                }).catch(function (desc) {
                    self.$Message.error(desc);
                });
            },
            // 切换菜单
            changeMenu(menuName) {
                // 将菜单名称存储到 localstorge
                localStorage.setItem('menuName', menuName)
                this.$router.push({ name: menuName });
            },
            // 进入菜单锁定 menuName
            gotoMenu() {
                this.activeName = localStorage.getItem('menuName') ? localStorage.getItem('menuName') : 'user';
            }
        }
    }
</script>
<style lang="scss">
.menu {
    .menu-drop {
        position: absolute;
        right:16px;
        font-style: normal;
        font-weight: 400;
    }
}
</style>