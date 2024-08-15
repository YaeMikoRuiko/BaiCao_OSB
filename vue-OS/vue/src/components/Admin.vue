<template>

  <div class="admin1" style="position: absolute; top:75px; left: 0; right: 0;">
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
      <el-radio-button :label="false" >展开</el-radio-button>
      <el-radio-button :label="true" >收起</el-radio-button>
    </el-radio-group>
    <el-menu default-active="/person"  router background-color="black" text-color="white" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
      <el-submenu index="/person">
        <template #title>
          <i class="el-icon-location"></i>
          <span slot="title">用户管理</span>
        </template>
          <el-menu-item index="/person">用户信息</el-menu-item>
          <el-menu-item index="/personData">用户统计</el-menu-item>
<!--        <el-menu-item-group title="分组二">-->
<!--          <el-menu-item index="1-3">选项3</el-menu-item>-->
<!--        </el-menu-item-group>-->
<!--        <el-submenu index="1-4">-->
<!--          <span slot="title">选项4</span>-->
<!--          <el-menu-item index="1-4-1">选项1</el-menu-item>-->
<!--        </el-submenu>-->
      </el-submenu>
      <el-submenu index="/questionChoice">
        <template #title>
          <i class="el-icon-files"></i>
          <span slot="title">题目管理</span>
        </template>
        <el-menu-item index="/questionChoice">选择题</el-menu-item>
        <el-menu-item index="/questionJudge">判断题</el-menu-item>
        <el-menu-item index="/questionfill">填空题</el-menu-item>
        <el-menu-item index="/questionShort">简答题</el-menu-item>
        <el-menu-item index="/questionApplication">应用题</el-menu-item>
        <el-menu-item index="/comment">题目评论</el-menu-item>
      </el-submenu>
      <el-menu-item index="/newsManage">
        <i class="el-icon-menu"></i>
        <span slot="title">新闻资讯</span>
      </el-menu-item>
      <el-menu-item index="/lunboManage" >
        <i class="el-icon-document"></i>
        <span slot="title">轮播图管理</span>
      </el-menu-item>
      <el-menu-item index="/testManage">
        <i class="el-icon-setting"></i>
        <span slot="title">试卷管理</span>
      </el-menu-item>
    </el-menu>
  </div>

</template>

<script>

export default {
  name: "Admin",
  data() {
    return {
      user:{},
      userJson:{},
      activeIndex: '1',
      activeIndex2: '1',
      isCollapse: true,
    };
  },
  mounted() {
    if(sessionStorage.getItem("role")==2){
      this.load();
    }else
    {
      this.$router.push("/home");
      this.$message({
        type: "error",
        message: "权限不足"
      })
    }

  },
  methods: {
    load(){
      let userStr = sessionStorage.getItem("user") ||'{}';
      this.user=userStr;
      this.userJson=JSON.parse(userStr);

    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
.admin1{
  position: absolute;
  left: 0;
  width: 10%;
  height: 80%;
  z-index: 999;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 100%;
}

</style>