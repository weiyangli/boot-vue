process.env.VUE_APP_VERSION = new Date().getTime();

module.exports = {
    devServer: {
        disableHostCheck: true,
        port: 8888,
        proxy: 'http://localhost'
    },

    // 多页的页面
    pages: {
        sample: 'src/pages/sample/main.js',
        admin: 'src/pages/admin/main.js',
    },

    // yarn build 的输出目录
    outputDir: '../page',
    assetsDir: 'static',

    productionSourceMap: false, // 不生成 map 文件
};
