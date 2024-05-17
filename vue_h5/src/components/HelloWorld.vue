<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>我是</h2>
    <img class="haixing" src="@/assets/haixing.jpg" alt="海星">
  </div>
</template>

<script>
import { homeClick } from '@/api/home'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '欢迎来到我的网站',
      // 初始窗口宽度和高度
      windowWidth: window.innerWidth,
      windowHeight: window.innerHeight
    }
  },
  mounted() {
    // 添加点击事件监听器
    window.addEventListener('click', this.handleClick);
    // 添加resize事件监听器来更新窗口尺寸
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    // 移除监听器以防内存泄露
    window.removeEventListener('click', this.handleClick);
    // 移除监听器
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    handleClick(event) {
      // event.clientX 和 event.clientY 会给你点击位置的坐标
      const x = event.clientX;
      const y = event.clientY;
      const w = this.windowWidth;
      const h = this.windowHeight;
      // console.log(`Clicked at x: ${x}, y: ${y}, w: ${w}, h: ${h}`);
      homeClick(x, y, w, h)
      .then(res => {
        if(res && res.data && res.data.url){
          window.location = res.data.url;
        }
      });
    },
    handleResize() {
      // 更新窗口尺寸
      this.windowWidth = window.innerWidth;
      this.windowHeight = window.innerHeight;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.hello{
  font-size: 12px;
  .haixing{
    width: 150px;
  }
}
</style>
