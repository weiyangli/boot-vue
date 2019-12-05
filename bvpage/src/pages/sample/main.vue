<template>
    <div class="layout">
        11111111111
        <a @click="navigateTo(1111)"></a>
    </div>
</template>

<script>
export default {
    mounted() {
    },
    methods: {
        navigateTo(name) {
            // this.$router.push('/');
            console.log(name);
        }
    },
    computed: {
    }
};
</script>

<style scoped lang="scss">

</style>
