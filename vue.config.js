module.exports = {

  devServer: {
    host: "192.168.0.105", //要设置当前访问的ip 否则失效
    port: 8765,//当前web服务端口
    // open: true, //浏览器自动打开页面
    proxy: {
      '/api': {
        target: 'http://192.168.0.105:8181',//目标地址
        //ws: true,//是否代理websocket
        changeOrigin: true,//是否跨域
        pathRewrite: {
          '^/api': ''//url重写
        }
      }
    }
  }
};
