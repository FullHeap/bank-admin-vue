Vue-cli 4

Vue-cli项目搭建

安装nodejs
----------

参考安装配置：

<https://www.runoob.com/nodejs/nodejs-install-setup.html>

安装vue-cli
-----------

参考安装配置

<https://cli.vuejs.org/zh/guide/installation.html>

启动vue ui创建项目
------------------

![](media/c17c9323c5935969ceb7df11c0e048e8.png)

C:\\Users\\廖尹祯\>vue ui

在浏览器中创建项目

![](media/934a120aeeb08877d4928c1b7a478ac6.png)

![](media/21ba9da7198a53c29dfdc51adbc72a26.png)

手动配置项目

![](media/3cba14ee7dfcd533175dddc1fecd9500.png)

选择router，vuex，css ，linter，使用单个配置文件

![](media/be62d9cb716b1b92f042a9ff4188e9e6.png)

添加Sass，ESlint自动检查

![](media/dd0965286586d73841af342c54ad23a2.png)

保存项目预设，下次可以直接创建好

![](media/f28a1a5762dd1a5682418e1c20de5af5.png)

创建项目

![](media/3e835712de5f88699aedd2b2456c6eef.png)

项目创建完毕

![](media/2c48e7b7db85acef1f6e6b129bae0c74.png)

Vscode 打开Vue-study

![](media/a251e59b710a2d179cb4a2efe66c0606.png)

![](media/853e8ae3d023bf3f34067698f4a898ed.png)

项目目录：

Public 公共

├─assets 常量

├─components 组件

├─router 路由

├─store vuex状态

└─views 页面

![](media/7903228931571f326bf969a3a01dc394.png)

Npm install 安装项目依赖

![](media/60e42dfe424c1a09f5cb9c25dfef1c66.png)

修改package.json中Serve为dev

Npm run dev启动项目

![](media/13a93581d5d00fcacc17b12deb0cc76f.png)

添加vue.config.js 配置文件

新增导出对象：

  runtimeCompiler: true,

    publicPath:'',

    devServer: {

        host: '0.0.0.0',

        port: '80',

    }

配置运行时编译

公共路径

服务配置：主机，端口
