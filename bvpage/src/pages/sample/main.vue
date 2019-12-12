<template>
    <div class="layout">
        11111111111
        <a @click="navigateTo()">测试</a>
    </div>
</template>

<script>
export default {
    mounted() {
    },
    created() {
        this.deleteDemo();
    },
    methods: {
        navigateTo() {
            this.$axios.get('/demo')
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });
        },
        deleteDemo() {
            this.$DemoDao.deleteDemo('张三').then((result) => {
                console.log('成功：' + result);
            }).catch(function (reason) {
                console.log('Failed: ' + reason);
            });
        }
    },
    computed: {
    }
};
</script>

<style scoped lang="scss">

</style>
