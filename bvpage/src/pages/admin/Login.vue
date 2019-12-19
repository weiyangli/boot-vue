<template>
    <div class="content">
        <Form ref="formInline" :model="formInline" :rules="ruleInline">
            <FormItem prop="user">
                <Input type="text" v-model="formInline.user" placeholder="Username">
                    <Icon type="ios-person-outline" slot="prepend"/>
                </Input>
            </FormItem>
            <FormItem prop="password">
                <Input type="password" v-model="formInline.password" placeholder="Password">
                    <Icon type="ios-lock-outline" slot="prepend"/>
                </Input>
            </FormItem>
            <FormItem style="text-align: center;">
                <Button type="primary" @click="handleSubmit('formInline')">登录</Button>
            </FormItem>
    </Form>
    </div>
</template>

<script>
export default {
    data () {
        return {
            formInline: {
                user: 'admin',
                password: '123456'
            },
            ruleInline: {
                user: [
                    { required: true, message: 'Please fill in the user name', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Please fill in the password.', trigger: 'blur' },
                    { type: 'string', min: 3, message: 'The password length cannot be less than 3 bits', trigger: 'blur' }
                ]
            }
        }
    },
    mounted() {
    },
    created() {
    },
    methods: {
        handleSubmit(name) {
            const self = this;
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        // 提交表单登录
                        this.$SystemDao.login(this.formInline.user, this.formInline.password).then((data) => {
                            // 将 token 写到localStorage
                            this.$store.commit('SET_LOGIN', data);
                            // 跳转至其他页面
                            this.$router.push({
                                path: "/main"
                            })
                        }).catch((desc) => {
                            this.$Message.error(desc);
                        });
                    } else {
                        this.$Message.error('Fail!');
                    }
                })
            }
    },
    computed: {
    }
};
</script>

<style scoped lang="scss">
.content {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background:url('/static/images/grass.jpg') no-repeat 50% 0;
    background-size: cover;
}
</style>
