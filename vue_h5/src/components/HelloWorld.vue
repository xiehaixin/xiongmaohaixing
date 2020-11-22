<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>我是</h2>
    <img class="haixing" src="@/assets/haixing.jpg" alt="">
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '欢迎来到我的网站'
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
  .haixing{
    width: 150px;
  }
}
</style>
