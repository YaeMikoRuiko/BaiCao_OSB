<template>
  <Header/>
  <!--    主题-->

  <div style="display: flex">
    <!--      侧边栏-->
    <address/>
    <!--      内容区域-->
    <router-view style="flex: 1"/>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Admin from "@/components/Admin";
export default {
  name: "Layout",
  components: {
    Header,
    Admin
  }
}
</script>

<style scoped>

</style>