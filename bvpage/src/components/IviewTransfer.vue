<template>
    <div class="transfer-box">
        <Transfer
            :data="sourceData"
            :target-keys="targetKeys"
            :list-style="listStyle"
            :render-format="render"
            filterable
            @on-change="handleChange">
        </Transfer>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                sourceData: [],
                targetKeys: [],
                listStyle: {
                    width: '250px',
                    height: '300px'
                }
            }
        },
        methods: {
            handleChange(newTargetKeys) {
                this.targetKeys = newTargetKeys;
                this.$emit("commitKeys", this.targetKeys);
            },
            render(item) {
                let picture = item.picture ? item.picture : 'https://i.loli.net/2017/08/21/599a521472424.jpg';
                return `<div class="picture-box"> <img src=${picture} style="width:30px; height:30px; border-radius: 30px; margin-right:10px;"/> <span class="nickname-span">${ item.label }</span></div>`;
            },
            // 查询所有可聊天用户
            async findUsers() {
                let self = this;
                await this.$UserDao.findUsers().then((data) => {
                    // 将用户 id 转存未 key(iview 组件默认绑定 key属性)
                    let users = data.map(x => {
                        return { key: x.id, label: x.nickname, disabled: false, description: '', picture: x.picture };
                    })
                    self.sourceData.push(...users);
                }).catch((desc) => {
                    this.$Message.error(desc);
                });
            },
            //重置
            resetData() {
                this.targetKeys = [];
            },
        },
        created() {
            // 查询所有可聊天用户
            this.findUsers();
        }
    }
</script>
<style lang="scss">
.transfer-box {
    margin-bottom: 10px;
    .ivu-transfer {
        display: flex;
        .ivu-transfer-list-body {
            .ivu-transfer-list-content {
                .ivu-transfer-list-content-item {
                    display: flex;
                    align-items: center;
                }
            }
        }
    }
}
.picture-box {
    display: flex;
    .nickname-span {
        font-weight: bold;
        display: flex;
        align-items: center;
    }
}
</style>