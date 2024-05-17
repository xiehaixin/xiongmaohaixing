const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 打包路径详解参考：https://cli.vuejs.org/zh/config
  publicPath: process.env.NODE_ENV === 'production' ? '/': '/',
  assetsDir: 'static',
  /* chainWebpack: config => {
    config
      .plugin('html')
      .tap(args => {
        console.log('aaaaaaa',args)
        return args
      })
  }, */
  pluginOptions: {
    'style-resources-loader': {
      preProcessor: 'scss',
      patterns:[]
    }
  },
  configureWebpack:{
    devServer: {
      port: 8081,
      proxy: {
        '/_api': {
          target: 'https://xiehaixin.cn', //, 
          changeOrigin: true,
          pathRewrite: {
            '^/_api': '/'
          }
        }
      }
    }
  },
  pages: {
    index: {
       // page 的入口
       entry: 'src/main.js',
       // 模板来源
       template: 'public/index.html',
       // 在 dist/index.html 的输出
       filename: 'index.html',
       // 当使用 title 选项时，
       // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
       title: '看看没惊喜',
       // 在这个页面中包含的块，默认情况下会包含
       // 提取出来的通用 chunk 和 vendor chunk。
       chunks: ['chunk-vendors', 'chunk-common', 'index']
    },
    privacyPage: {
      // page 的入口
      entry: 'src/privacyPage/main.js',
      // 模板来源
      template: 'public/index.html',
      // 在 dist/index.html 的输出
      filename: 'privacyPage/index.html',
      // 当使用 title 选项时，
      // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
      title: '别浪费时间',
      // 在这个页面中包含的块，默认情况下会包含
      // 提取出来的通用 chunk 和 vendor chunk。
      chunks: ['chunk-vendors', 'chunk-common', 'privacyPage']
    },
    checkTokenPage: {
      // page 的入口
      entry: 'src/checkTokenPage/main.js',
      // 模板来源
      template: 'public/index.html',
      // 在 dist/index.html 的输出
      filename: 'checkTokenPage/index.html',
      // 当使用 title 选项时，
      // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
      title: '别浪费时间',
      // 在这个页面中包含的块，默认情况下会包含
      // 提取出来的通用 chunk 和 vendor chunk。
      chunks: ['chunk-vendors', 'chunk-common', 'checkTokenPage']
    }
  }
})
