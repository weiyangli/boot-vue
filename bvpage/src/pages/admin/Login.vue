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
             <FormItem prop="code" class="code-item">
                <Input type="text" v-model="formInline.code" placeholder="验证码">
                    <Icon type="ios-key-outline" slot="prepend"/>
                </Input>
            </FormItem>
            <FormItem style="text-align: center;">
                <Button type="primary" @click="handleSubmit('formInline')">登录</Button>
            </FormItem>
        </Form>
        <img :src="authCode.codeBase64" @click="codeGenerator" class="code-image"/>
    </div>
</template>

<script>
export default {
    data () {
        const checkCode  = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入验证码'));
            } else if (value.toLowerCase() != this.authCode.code.toLowerCase()) {
                callback(new Error('验证码输入有误'));
            } else {
                callback();
            }
        };
        return {
            formInline: {
                user: 'admin',
                password: '123456',
                code: '',
            },
            ruleInline: {
                user: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
                code: [
                    { required: true, trigger: 'blur', validator: checkCode }
                ]
            },
            authCode: {},
        }
    },
    mounted() {
    },
    created() {
        // 获取验证码
        this.codeGenerator();
    },
    methods: {
        // 提交登录表单
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
                            path: '/main'
                        })
                    }).catch((desc) => {
                        this.$Message.error(desc);
                    });
                } else {
                    this.$Message.error('Fail!');
                }
            })
        },
        // 生成二维码
        codeGenerator() {
            this.$SystemDao.codeGenerator().then((data) => {
                this.authCode = data;
            }).catch((desc) => {
                this.$Message.error(desc);
            });
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
    .code-image {
        margin-left: 16px;
        margin-top: 30px;
    }
}
</style>
