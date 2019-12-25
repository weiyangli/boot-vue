<template>
    <div id="articleAdmin">
        <div id="editor"></div>
        <div class="but-box">
            <Button @click="commitContent()" type="info" style="margin-right: 12px">发送</Button>
            <Button @click="cleanContent()" type="info">清空</Button>
        </div>
    </div>
</template>
<script>
import E from "wangeditor";   //引入wangeditor
export default {
    data() {
        return {
            editor: null,
            articleTitle: "",
            content: ""
        };
    },
    methods: {
        commitContent() {
            let contentDom = $(".w-e-text");
            if (!contentDom.html()) {
                this.$Message.warning("请输入内容");
                return;
            }
            const myContent = contentDom.html();
            this.$emit('commitContent', myContent);
        },
        // 清空输入文本
        cleanContent() {
            this.editor.txt.clear();
        }
    },
    mounted() {
        //使用wangeditor
        this.editor = new E("#editor");
        // 自定义菜单配置
        this.editor.customConfig.menus = [
            'head',
            'bold',
            'italic',
            'underline',
            'emoticon',  // 表情
            //'image',   // 插入图片
            'undo',      // 撤销
            'redo'       // 重复
        ]
        this.editor.create();
        let _this = this;
        // 回车键提交文本
        document.onkeydown = function(e){
            var ev = document.all ? window.event : e;
            if(ev.keyCode==13) {
                _this.commitContent();
            }
        }
    }
};
</script>
<style lang="scss">
    .but-box {
        text-align: end;
        padding: 10px;
    }
</style>