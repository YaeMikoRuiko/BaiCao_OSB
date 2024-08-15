<template>
  <!--    主题-->

  <div style="display: flex">
    <!--      侧边栏-->
    <address/>
    <Admin/>
    <!--      内容区域-->
    <router-view style="flex: 1"/>
  </div>
</template>

<script>
import Admin from "@/components/Admin";

export default {
  name: "Layout2",
  components: {
     Admin
  }
}
</script>

<style scoped>

</style>