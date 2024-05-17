<template>
  <div>
    <checkToken/>
  </div>
</template>

<script>
import checkToken from '@/components/checkToken/index.vue'
export default {
  name: 'App',
  components:{
    checkToken
  }
}
</script>

<style  lang="scss" scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
