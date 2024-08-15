<template>
<div class="neirong">
  <section class="c-container">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right" style="top: 100px;left: 100px;color: white;">
      <el-breadcrumb-item :to="{ path: '/' }" >首页</el-breadcrumb-item>
      <el-breadcrumb-item >用户信息[{{this.user.username}}]</el-breadcrumb-item>
    </el-breadcrumb>
    </div>
    <div class="cinfo">
      <div  style="background-color:white;width: 100%;padding: 15px;margin-bottom: 100px;border-radius: 0.8rem;position: relative">
        <div>
          <el-page-header @back="goBack" content="用户信息">
          </el-page-header>
        </div>
        <div style="background-color: white;margin-top: 50px;padding-left: 50px">
          <el-descriptions class="margin-top" title="个人信息" :column="2" size="big">
            <el-descriptions-item ><el-avatar style="transform: scale(1.5);">{{ this.user.username }}</el-avatar></el-descriptions-item>
            <el-descriptions-item label="用户名">{{ this.user.username}}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{ this.user.email}}</el-descriptions-item>
            <el-descriptions-item label="账号">{{ this.user.account}}</el-descriptions-item>
            <el-descriptions-item label="性别">{{ this.user.gender}}</el-descriptions-item>
            <el-descriptions-item label="角色">
              <el-tag size="small" v-if="this.user.role=='0'">普通用户</el-tag>
              <el-tag size="small" v-if="this.user.role=='1'">教师</el-tag>
              <el-tag size="small" v-if="this.user.role=='2'">管理员</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="注册时间">{{ this.user.joinDate }}</el-descriptions-item>
            <el-descriptions-item label="ID">{{ this.user.id }}</el-descriptions-item>
          </el-descriptions>
          <div  style="display:flex;padding:10px;  justify-content: center; /* 垂直居中 */ align-items: center; /* 水平居中 */ margin-top: 100px">
            <el-button type="primary" @click="open">编辑个人信息</el-button>
            <el-button type="primary" @click="update">修改密码</el-button>
            <el-button type="warning" @click="exit">退出登录</el-button>
          </div>
          <div>
            <el-dialog
                title="修改密码"
                v-model="dialogVisible1"
                width="30%">
              <el-form :model="form" label-width="120px">
                <el-form-item label="旧密码">
                  <el-input v-model="form.oldPassword" type="password"></el-input>
                </el-form-item>

                <el-form-item label="新密码">
                  <el-input v-model="form.newPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item label="再输入新密码">
                  <el-input v-model="form.confirmPassword" type="password"></el-input>
                  <span v-if="this.form.confirmPassword!=this.form.newPassword" style="color: red">*请确认两次新密码输入是否相同</span>
                </el-form-item>

              </el-form>
              <template #footer>
          <span class="dialog-footer">
            <el-button @click="clear">清 空</el-button>
            <el-button @click="dialogVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="savePassword">确 定</el-button>
          </span>
              </template>
            </el-dialog>
          </div>
          <div>
            <el-dialog
                title="修改个人信息"
                v-model="dialogVisible"
                width="30%">
              <el-form :model="form" label-width="120px">
                <el-form-item label="用户名">
                  <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="账号">
                  <el-input v-model="form.account"></el-input>
                </el-form-item>
                <el-form-item label="邮箱地址">
                  <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                  <el-radio v-model="form.gender" label="男">男</el-radio>
                  <el-radio v-model="form.gender" label="女">女</el-radio>
                </el-form-item>

              </el-form>
              <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
              </template>
            </el-dialog>
          </div>
        </div>
      </div>


    </div>

  </section>
  <footer class="footer" style="margin-top: 100px">
    <ul class="footer_share">
      <li class="footer_weibo">

      </li>
      <li class="footer_weixin">

      </li>
      <li class="footer_qq">

      </li>
      <li class="footer_fx">
        <div class="fx_dizhi">
          <p>
            分享至
          </p>
          <ul style="display: flex;justify-content: center;">
            <li class="fx_qqkj">

            </li>
            <li class="fx_qq">

            </li>
            <li class="fx_wb">

            </li>
            <li class="fx_tb">

            </li>
          </ul>
        </div>
      </li>
    </ul>
    <div class="footer_nr">
      <div class="footer_nr_content">
        <div class="footer_logo">
          <div class="footer_logo_nr">
            <img src="../components/images/广西师范大学.png">
            <span>

                        </span>
            <img src="../components/images/logo.png">
          </div>
        </div>
        <div class="footer_font">
          <div class="font_info">
            <div style="color: #fff;margin-bottom: 28px;">
                            <span style="margin-left: 0px;">
                                用户协议
                            </span>
              |
              <span>
                                隐私政策
                            </span>
              |

              <span>
                                自律公约
                            </span>
              |
              <span>
                                关于我们
                            </span>
              |
              <span>
                                联系我们
                            </span>
              |
              <span>
                                加入我们
                            </span>
            </div>
          </div>

          <div style="display: flex; ">
                        <span class="bei_hao">
                            桂ICP备2023016466
                        </span>
            <span style="margin-left: 10px; margin-right: 10px;">
                            |
                        </span>
            <span>
                            QQ 1664011417
                        </span>
            <span style="margin-left: 10px; margin-right: 10px;">
                            |
                        </span>
            <span>
                            Wechat z1664011417
                        </span>
          </div>
          <div>
            <p>
              联系方式 17344208380 19580772534
            </p>
          </div>
          <div>
            <p>
              亲爱的朋友，警方反诈劝阻电话“96110”系专门针对避免您财产被骗受损而设，请您一旦收到来电，立即接听
            </p>
          </div>
          <div>
            <p>
              未成年成长关爱热线：021-60371740 （服务时间：10:00-20:00）
            </p>
          </div>

        </div>
      </div>
    </div>
    <div style="height: 35px;background-color: #111;">

    </div>
  </footer>
</div>

</template>

<script>
const axios = require("axios");
import myEditor from '../components/MyEditor'
export default {
  name: "UserInfo",
  components:{
    myEditor
  },
  data() {
    return {
      form:{},
      user: {},
      dialogVisible:false,
      dialogVisible1:false,
    }
  },
  created() {
    this.user.id=sessionStorage.getItem("id");
    if(!this.user.id){
      this.$router.push('/home')
      this.$message({
        type: "error",
        message: "请先进行登录"
      })
    }else{
      this.getuser(this.user.id);
    }
  },
  mounted() {



  },
  methods:{
    getuser(id){
      axios.get("http://localhost:8087/user/"+id).then(res =>{
            this.user=res.data.data;
            if (res.data.code!=='0') {
              this.$message({
                type: "error",
                message: res.data.msg
              })
            }
          }
      );
    },
    goBack() {
      console.log('go back');
      this.$router.push('/home');
    },
    open(){
      this.form=this.user;
      this.dialogVisible=true;
    },
    update(){
      this.form=this.user;
      this.dialogVisible1=true;
    },
    save(){
      axios.put("http://localhost:8087/user",this.form).then(res =>{
        if (res.data.code==='0'){
          this.$message({
            type: "success",
            message: "更新成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.getuser(this.user.id);
        this.dialogVisible = false;//关闭弹窗
      });
    },
    clear(){
      this.form.newPassword='',
      this.form.oldPassword='',
      this.form.confirmPassword='';
    },
    savePassword(){
      //校验旧密码是否正确；
      if(this.form.password!=this.form.oldPassword){
        alert("密码输入错误，请重新输入！！！");
        return;
      }
      if(this.form.newPassword!=this.form.confirmPassword){
        alert("两次新密码密码输入错误，请重新输入！！！");
        return;
      }
      this.form.password=this.form.newPassword;
      axios.put("http://localhost:8087/user",this.form).then(res =>{
        if (res.data.code==='0'){
          this.$message({
            type: "success",
            message: "更新成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.getuser(this.user.id);
        this.dialogVisible1 = false;//关闭弹窗
      });
    },
      exit(){
        sessionStorage.removeItem('id');
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('account');
        sessionStorage.removeItem('role');
        if (sessionStorage.getItem('username')){
          this.$message({
            type: "error",
            message: res.msg
          })
        }else {

          this.$message({
            type: "success",
            message: "登出成功"
          })
        }
        window.location.reload(); // 刷新页面
      }

  }


}
</script>

<style scoped>
@import "../assets/css/foot.css";/*底栏*/

.neirong{
  position: relative;
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  height: 100%;
  overflow: hidden;
  v-index:1;
  background: url("../components/images/新闻资讯背景.jpg") no-repeat center/cover;
  backdrop-filter: blur(10px); /* 添加模糊效果 */
}
.c-container  {
  position: relative;
  top:100px;
  v-index:2;
  padding-left:10%;
  padding-right: 10%;
}
.cinfo {
  position: relative;
  margin-top: 20px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-right: calc(0px - 0.5rem / 2);
  margin-left: calc(0px - 0.5rem / 2);
  box-sizing: border-box;
  height: 600px;
  width: 100%;
}
.footer{

}


</style>