const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
/*   css:{
    loaderOptions:{
      postcss:{
        plugins: [
          require('postcss-px-to-viewport')({
            unitToConvert: 'px',    // 要转化的单位
            viewportWidth: 375,     // UI设计稿的宽度
            unitPrecision: 5,       // 转换后的精度，即小数点位数
            propList: ['*'],        // 指定转换的css属性的单位，*代表全部css属性的单位都进行转换
            viewportUnit: 'vw',     // 指定需要转换成的视窗单位，默认vw
            fontViewportUnit: 'vw', // 指定字体需要转换成的视窗单位，默认vw
            selectorBlackList: [],  // 指定不转换为视窗单位的类名，
            minPixelValue: 1,       // 默认值1，小于或等于1px则不进行转换
            mediaQuery: false,      // 是否在媒体查询的css代码中也进行转换，默认false
            replace: true,          // 是否转换后直接更换属性值
            exclude: undefined,     // 设置忽略文件，用正则做目录名匹配
            include: undefined,     //如果设置了include，那将只有匹配到的文件才会被转换
            landscape: false,       // 是否处理横屏情况
            landscapeUnit: 'vw',    //横屏时使用的单位
            landscapeWidth: 568     //横屏时使用的视口宽度
          })
        ]
      }
    }
  }, */
  chainWebpack: config => {
    config
      .plugin('html')
      .tap(args => {
        args[0].template = './index.html'
        return args
      })
  },
  pluginOptions: {
    'style-resources-loader': {
      preProcessor: 'scss',
      patterns:[]
    }
  }  
})
