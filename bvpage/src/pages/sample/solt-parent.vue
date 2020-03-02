<template>
    <div>
        <h3>父组件</h3>
        <!-- solt vue中的模板语言，可以理解为提前占上位置，后期内容用到的时候根据用户的需求自动替换 -->
        <Child>
            <div slot-scope="user">
                <span v-for="(u, index) in user.data" :key="index">{{ u }}</span>
            </div>
            <div slot="solt1">
                <h3>slot1的内容</h3>
            </div>
            <div slot="solt2">
                <h3>slot2的内容</h3>
            </div>
        </Child>
    </div>
</template>

<script>
    import Child from './soltchild';

    export default {
        components: { Child },
        name: ""
    }
</script>

<style scoped>

</style>