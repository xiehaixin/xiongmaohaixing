<template>
  <div>
    <privacy></privacy>
  </div>
</template>

<script>
import privacy from '@/components/privacy/index.vue'
export default {
  name: 'App',
  components:{
    privacy
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
