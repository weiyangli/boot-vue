<template>
    <!-- <FileUploader ref="fileUploader" image @on-success="fileUploaded" /> -->
</template>

<script>
// import FileUploader from '@/components/FileUploader';
export default {
    components: { FileUploader },
    created() {
    },
    methods: {
        fileUploaded(file) {
            console.log(file);
        },
    }
};
</script>

<style lang="scss">
</style>
