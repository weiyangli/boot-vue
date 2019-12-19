<template>
    <div class="menu">
        <Menu mode="horizontal"  theme="dark" :active-name="activeName" @on-select="changeMenu">
            <MenuItem name="user" to="user">
                <Icon type="ios-paper" />
                系统管理
            </MenuItem>
            <MenuItem name="activity" to="activity">
                <Icon type="ios-people" />
                活动管理
            </MenuItem>
            <MenuItem name="analysis" to="analysis">
                <Icon type="ios-stats" />
                统计分析
            </MenuItem>
            <Dropdown class="menu-drop" @on-click="changeDrop" style="color: #fff; cursor: pointer;">
                菜单
                <Icon type="ios-arrow-down"></Icon>
                <DropdownMenu slot="list" >
                    <DropdownItem name="logout">注销</DropdownItem>
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
                    this.$router.push({ path: '/' });
                }).catch(function (desc) {
                    self.$Message.error(desc);
                });
            },
            // 切换菜单
            changeMenu(menuName) {
                // 将菜单名称存储到 localstorge
                localStorage.setItem('menuName', menuName)
            },
            // 进入菜单锁定 menuName
            gotoMenu() {
                this.activeName = localStorage.getItem('menuName') ? localStorage.getItem('menuName') : 'user';
                /**
                 * 记：
                 * router 是在 main.js 中声明的全局属性 this.$router.push({ name: this.activeName })
                 * route是一个跳转的路由对象，每一个路由都会有一个route对象，是一个局部的对象 this.$route.name 
                 */
                if (this.$route.name != this.activeName) {
                    this.$router.push({ name: this.activeName });
                }
            },
            // 右侧菜单下拉切换
            changeDrop(name) {
                if (name == 'logout') {
                    this.logout();
                }
            }
        }
    }
</script>
<style lang="scss">
.menu {
    .menu-drop {
        position: absolute;
        right: 60px;
        font-style: normal;
        font-weight: 400;
    }
}
</style>