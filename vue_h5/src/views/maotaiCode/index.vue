<template>
 <div class="maotaiCode">

 </div>
</template>

<script>
import { getSlideCode } from '@/api/maotaiApi'
export default {
  name: 'index',
  data() {
    return {

    }
  },
  mounted() {
    getSlideCode();
  },
  components: {

  }
}
</script>

<style scoped lang="scss">
</style>
