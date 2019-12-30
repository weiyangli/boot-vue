<template>
    <div class="pdf">
        <div>
            <Spin v-if="!pdfSrc" size="large"></Spin>
            <pdf v-else src="./static/pdf/手机端报工使用手册.pdf" class="pdf-content"></pdf>
        </div>
    </div>
</template>

<script>
import pdf from "vue-pdf";

export default {
    components: { pdf },
    data() {
        return {
            pdfSrc: '',
            pageCount: 11,
            currentPage: 2
        };
    },
    created() {
        this.loadPdfHandler();
    },
    methods: {
        loadPdfHandler() {
            this.pdfSrc = '/static/pdf/手机端报工使用手册.pdf';
        }
    },
};
</script>

<style lang="scss">
.pdf {
    height: 100%;
    width: 100%;

    .pdf-content {
            height: 100%;
    width: 100%;
    }
}
</style>
