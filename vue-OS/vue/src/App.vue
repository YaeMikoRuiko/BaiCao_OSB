<template>
  <!--  <div style="background: linear-gradient(90deg,#3cadeb,#9c88ff);">-->

  <div>
    <!--    根据路由进行访问-->
    <router-view/>
  </div>

</template>

<script>
import MyEditor from "./components/MyEditor"

export default {
  name: "App",
  components: {
     MyEditor,
  },
  mounted() {
    // if (this._isMobile()) {
    //   this.$router.replace('/m.first');
    // } else {
    //   this.$router.replace('/first');
    // }
  },
  methods:{
    // _isMobile() {
    //   let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
    //   return flag;
    // }
  },

}
</script>