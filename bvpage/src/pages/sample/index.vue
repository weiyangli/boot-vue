<template>
<div id="app" class="app">
    <router-view/>
</div>
</template>

<script>
export default {
    created() {
    }
};
</script>

<style lang="scss">
body, html {
    width: 100%;
    height: 100%;
}

#app {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
}
</style>
