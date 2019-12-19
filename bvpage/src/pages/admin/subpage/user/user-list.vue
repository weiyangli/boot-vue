<template>
    <div class="user-list">
        <Button type="primary" :disabled="pickData.length > 0 ? false : true" @click="deleteUser" style="margin-bottom: 16px;">删除</Button>
        <Table border :columns="columns1" :data="data1" @on-selection-change="selectData"></Table>
    </div>
</template>

<script>

export default {
        data() {
        return {
            columns1: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: 'Name',
                    key: 'name'
                },
                {
                    title: 'Age',
                    key: 'age'
                },
                {
                    title: 'Address',
                    key: 'address'
                }
            ],
            data1: [
                {
                    name: 'zzc',
                    age: 18,
                    address: '上海',
                    date: '2016-10-03'
                },
                {
                    name: 'jp',
                    age: 24,
                    address: '广东',
                    date: '2016-10-01'
                },
                {
                    name: '张三',
                    age: 30,
                    address: '悉尼',
                    date: '2016-10-02'
                },
                {
                    name: '兰芝',
                    age: 26,
                    address: '深圳',
                    date: '2016-10-04'
                }
            ],
            pickData: [],
        }
    },
    mounted() {
    },
    created() {
    },
    methods: {
        // 批量删除数据
        deleteUser() {
            for (let data of this.pickData) {
                let index = this.data1.findIndex(x => x.id == data.id)
                if (index > -1) {
                    this.data1.splice(index, 1);
                }
            }
        },
        // 选中数据
        selectData(data) {
            this.pickData = data;
        }
    },
    computed: {
    }
};
</script>

<style lang="scss">

</style>
