<template>
  <div>
    <header class="header">
      <div class="audio">
        <input type="checkbox" id="audio">
        <label for="audio" class="audios">

        </label>
        <img src="./images/音乐开启.png" class="audioYes">
        <img src="./images/音乐关闭.png" class="audioNo">
        <embed :src="require('./video/背景音乐.mp3')" loop="true" autostart="true">

      </div>
      <div class="logo" id="logo1">

      </div>
      <nav class="header_nav">
        <div class="header_textNav">
          <a href="/home" class="header_textPublic header_textChecked">
            首&nbsp;页
          </a>
          <a href="/news" class="header_textPublic">
            新&nbsp;闻
          </a>
          <a v-if="!user.username" class="header_textPublic" id="tikudenglu" @click="toggleLoginDisplay">
            题&nbsp;库
          </a>
          <a v-else="user.username" href="/tiku" class="header_textPublic" id="tikudenglu">
            题&nbsp;库
          </a>

          <a href="#" class="header_textPublic">
            使&nbsp;用
          </a>
          <a href="#" class="header_textPublic">
            社&nbsp;区
          </a>
          <a href="#" class="header_textPublic">
            我&nbsp;们
          </a>
          <div class="header_navs">

          </div>
        </div>
      </nav>
      <div class="header_right">
        <a href="/person" class="header_cz" v-if="user.role==2">
          <p style="font-size: 17px;">
            天天向上系统
          </p>
          <img src="./images/成长关爱.png" class="header_czimg">
        </a>
        <div v-if="!user.username" class="header_login">
          <input type="checkbox" style="display: none;" id="login" >
          <label for="login" class="login_btn">
                    <span  class="dr">
                        登录
                    </span>
            <img src="./images/登入.png" class="header_login_img">
          </label>
          <div class="mhy_login" >
            <div style="width: 450px;">
              <div class="mhy_account">
                <img src="./images/广西师范大学.png" class="gxnu_logo">
                <label for="login" class="mhy_account_out">
                  <img :src="require('./images/关闭.png')">
                </label>
                <input type="radio" name="account" style="display: none;" id="account_one" checked>
                <input type="radio" name="account" style="display: none;" id="account_two">
                <div class="account">
                  <label for="account_one">
                    账号登入
                  </label>
                  <label for="account_two">
                    用户注册
                  </label>
                </div>
                <div class="login_one">
                  <el-form :model="form" size="normal"  ref="form" :rules="rules">
                    <el-form-item style="margin-top: 50px" prop="account">
                      <el-input v-model="form.account"  prefix-icon="el-icon-user-solid" placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                      <el-input v-model="form.password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button style="color: white; border-radius: 30px;width: 100%;height: 50px;font-size:16px;background: linear-gradient(90deg,#372c65,#000000);" @click="login">登录</el-button>
                    </el-form-item>
                  </el-form>
                </div>
                <div class="login_two">
                  <el-form :model="form1" size="normal"  ref="form1" :rules="rules1">
                    <el-form-item prop="username">
                      <el-input v-model="form1.username"  prefix-icon="el-icon-user-solid" placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="account">
                      <el-input v-model="form1.account"  prefix-icon="el-icon-bank-card" placeholder="请输入账号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                      <el-input v-model="form1.password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="confirm">
                      <el-input v-model="form1.confirm" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item >
                      <el-button style="color: white; border-radius: 30px;width: 100%;height: 50px;font-size:16px;background: linear-gradient(90deg,#372c65,#000000);" @click="register">注册</el-button>
                    </el-form-item>

                  </el-form>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="user.username" class="header_login" @click="this.$router.push('/userInfo')">
          <label for="login" class="login_btn">
                    <span  class="dr">
                       {{this.user.username }}
                    </span>
            <img src="./images/登入.png" class="header_login_img">
          </label>
        </div>
      </div>
    </header>
  </div>

</template>
<style>


.header {
  display: flex;
  position: fixed;
  z-index: 999;
  top: 0;
  height: 66px;
  width: 100%;
  background-color: rgba(17, 17, 17, 0.75);
  box-shadow: 0px 3px 7px 0px rgba(0, 0, 0, 0.19);
}

/* 音乐按钮 */

.audio {
  position: fixed;
  z-index: 9;
  top: 18px;
  left: 70px;

}

#audio {
  display: none;
}

.audio .audios {
  position: absolute;
  z-index: 999;
  display: block;
  width: 30px;
  height: 30px;
}

.audioYes,
.audioNo {
  position: absolute;
  display: none;
  width: 30px;
  height: 30px;
}

.audioNo {
  display: block;
}

#audio:checked~.audioNo {
  display: none;
}

.audio embed {
  display: none;
}

#audio:checked~embed {
  display: block;
  opacity: 0;
}

#audio:checked~.audioYes {
  display: block;
}



.logo {
  float: left;
  height: 100%;
  margin-left: 140px;
  width: 200px;
  background: url(./images/logo.png) no-repeat;
  background-size: contain;
}

/* 导航 */
.header_nav {
  position: relative;
  margin-left: -84px;
}

.header_textNav {
  padding-right: 10px;
  margin-left: 40px;
}

.header_textPublic {
  padding: 0 30px;
  float: left;
  height: 66px;
  line-height: 66px;
  text-align: center;
  font-size: 17px;
  color: #ccc;
  text-decoration: none;
}

.header_textPublic:hover,
.header_textChecked {
  text-shadow: 0 0 10px #69e0ff, 0 0 20px #69e0ff, 0 0 40px #69e0ff;
  color: #fff;
  text-decoration: none;
}

/* 蓝色导航条 */

.header_navs {
  position: absolute;
  left: 70px;
  top: 0px;
  height: 5px;
  width: 40px;
  background-color: rgb(105, 224, 255);
  transition: all 0.3s;
}

.header_textPublic:nth-of-type(1):hover~.header_navs {
  left: 70px;
}

.header_textPublic:nth-of-type(2):hover~.header_navs {
  left: 168px;
}

.header_textPublic:nth-of-type(3):hover~.header_navs {
  left: 268px;
}

.header_textPublic:nth-of-type(4):hover~.header_navs {
  left: 368px;
}

.header_textPublic:nth-of-type(5):hover~.header_navs {
  left: 466px;
}

.header_textPublic:nth-of-type(6):hover~.header_navs {
  left: 565px;
}

/* 头部右边登入/成长关爱 */

.header_right {
  display: flex;
  align-items: center;
  position: absolute;
  right: 30px;
  top: 0;
  height: 100%;

}

.header_cz {
  display: flex;
  align-items: center;
  margin-right: 20px;
  color: #ccc;
  text-decoration: none;
}

.header_czimg,
.header_login_img {
  margin-left: 18px;
  width: 27px;
  height: 27px;
  opacity: 0.7;
}

.login {
  display: flex;
  align-items: center;
  height: 100%;
}


.login_btn {
  position: relative;
  z-index: 999;
  display: flex;
  align-items: center;
  padding: 0 10px;
  border: 0;
  font-size: 17px;
  color: #ccc;
  cursor: pointer;
}

.header_cz:hover,
.header_cz:hover .header_czimg,
.header_login:hover .dr,
.header_login:hover .header_login_img {
  color: white;
  opacity: 1;
}

/* 登入页面 */

.mhy_login {
  display: none;
}

#login:checked~.mhy_login {
  display: flex;
}




.mhy_login {
  align-items: center;
  justify-content: center;
  position: fixed;
  z-index: 99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .6);

}

.mhy_account {
  position: relative;
  margin-top: 58px;
  padding: 30px 40px 50px;
  background-color: #fff;
  border-radius: 4px;
}

.gxnu_logo {
  width: 250px;
  height: 50px;
  position: absolute;
  top: -58px;
  left: calc(50% - 125.5px);
}

.mhy_account_out {
  position: absolute;
  z-index: 999;
  top: 15px;
  right: 15px;
}

.mhy_account_out img {
  display: block;
  width: 16px;
  height: 16px;
  cursor: pointer;
}

.account {
  border-bottom: 1px solid #f6f6f6;
  font-size: 18px;
  color: #7f858a;
}

.account label {
  display: inline-block;
  padding: 10px;
  width: 165px;
  text-align: center;
  cursor: pointer;
}


.mhy_input_account input {
  outline: none;
  padding: 14px 12px;
  border: none;
  color: #3c4044;
  font-size: 16px;
}



/* 注册/登入切换 */
.login_one,
.login_two {
  display: none;
}

#account_one:checked~.account label:nth-child(1) {
  font-weight: 700;
  color: #3c4044;
}

#account_one:checked~.login_one {
  display: block;
}

#account_two:checked~.account label:nth-child(2) {
  font-weight: 700;
  color: #3c4044;
}

#account_two:checked~.login_two {
  display: block;
}



</style>

<script>
import axios from "axios";

export default {
  data() {
    return {
      form: {},
      form1: {},
      user: {},
      userJson: {},
      activeIndex: '1',
      activeIndex2: '1',
      username:"",
      rules: {
        account: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      },
      rules1: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        account: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        confirm: [
          {required: true, message: '请输入验证密码', trigger: 'blur'},
        ],
      },
    }
  },

  mounted() {
    this.load();

  },
  methods: {
    load(){
      this.user.username=sessionStorage.getItem("username")
      this.user.account=sessionStorage.getItem("account")
      this.user.role=sessionStorage.getItem("role")
      this.user.id=sessionStorage.getItem("id")
      console.log(this.user)
    },
    // handleSelect(key, keyPath) {
    //   console.log(key, keyPath);
    // },
    confirmEvent(){
      sessionStorage.removeItem("user");
      this.load();
      this.$message.error('您已退出登录');
    },
    /*题库登录*/
    toggleLoginDisplay() {
      const checkbox = document.getElementById("login");
      if (checkbox) {
        checkbox.checked = !checkbox.checked;
      }
    },
    /*注册*/
    register() {
      if (this.form1.password !== this.form1.confirm) {
        this.$message({
          type: "success",
          message: "两次密码输入不一致"
        })
        return;
      }

      this.$refs['form1'].validate((valid) => {
        if (valid) {
          axios.post("http://localhost:8087/user/register", this.form1).then(res => {
            console.log(res);
            if (res.data.code === '0') {
              this.$message({
                type: "success",
                message: "注册成功"
              })
              // this.$router.push("/login");//注册成功后的页面跳转
              window.location.reload(); // 刷新页面
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    /*登录*/
    login(){
      console.log(this.form);
      this.$refs['form'].validate((valid) => {
        if (valid) {
          axios.post("http://localhost:8087/user/login",this.form).then(res => {
            console.log(res);
            if (res.data.code==='0'){
              this.$message({
                type: "success",
                message: "登录成功"
              })
              console.log("res.date:"+res.data)
              localStorage.setItem('token', res.data.data.token);//将Jwt存如浏览器本地存储
                let username=res.data.data.username
                let account=res.data.data.account
                let role=res.data.data.role
                let id=res.data.data.id
              sessionStorage.setItem("username",username);//缓存用户信息
              sessionStorage.setItem("account",account);//缓存用户信息
              sessionStorage.setItem("role",role);//缓存用户信息
              sessionStorage.setItem("id",id);//缓存用户信息
              // alert(id)
              // window.alert("dddd");
              window.location.reload(); // 刷新页面
            }else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

  }
}
</script>