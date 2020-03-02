<template>
    <div class="child">
        <h3>这里是子组件</h3>
        <slot :data="names"></slot>
        <slot name="solt1"></slot>
        <slot name="solt2"></slot>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                names:  [ '李四', '张三', 'liushan', 'jijiji' ]
            }
        }
    }
</script>

<style scoped>

</style>