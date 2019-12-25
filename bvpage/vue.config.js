process.env.VUE_APP_VERSION = new Date().getTime();

module.exports = {
    devServer: {
        disableHostCheck: false,                // 是否进行配置反向代理
        port: 8888,
        // proxy: 'http://localhost',
        proxy: {
            '/': {
                target: 'http://localhost',   // 后台接口域名
                changeOrigin: true,           // 是否跨域
                secure: false,                // 如果是https接口，需要配置这个参数
                pathRewrite: { '^/': ''}      // pathRewrite 表示的意思是 把/ 替换为 空
            },
            '/ws/*': {
                target: 'ws://127.0.0.1',
                ws: true,                      // 如果要代理 websockets，配置这个参数
                changeOrigin: true,            // 是否跨域
            }
        }
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
