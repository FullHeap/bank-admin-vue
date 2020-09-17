const port = process.env.port || process.env.npm_config_port || 80 

//添加图标路径解析
const path = require('path')
function resolve(dir) {
  return path.join(__dirname, dir)
}

//覆盖vue默认配置
module.exports = {
    runtimeCompiler: true,
    publicPath:'',
    devServer: {
        host: '0.0.0.0',
        port: port,
        proxy: {
          '/': {
            target: 'http://localhost:8080',
            changeOrigin: true
          }
        }
    },
    //设置 svg图标  需要添加dev依赖svg-sprite-loader
    chainWebpack(config) {
      // set svg-sprite-loader
      config.module
        .rule('svg')
        .exclude.add(resolve('src/assets/icons'))
        .end()
      config.module
        .rule('icons')
        .test(/\.svg$/)
        .include.add(resolve('src/assets/icons'))
        .end()
        .use('svg-sprite-loader')
        .loader('svg-sprite-loader')
        .options({
          symbolId: 'icon-[name]'
        })
        .end()      
    }
}