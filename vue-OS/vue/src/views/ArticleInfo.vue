<template>
<div class="neirong">
  <section class="c-container">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="top: 100px;left: 100px;">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/news' }">新闻资讯</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/article' }" >查看全部新闻资讯</el-breadcrumb-item>
      <el-breadcrumb-item  >{{ article.title }}</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="cinfo">
      <div style="max-height: 700px; overflow: auto; padding: 20px; height: 700px; width: 100%; background-color: rgba(255, 255, 255, 0.51); margin-bottom: 100px; border-radius: 0.8rem;">
        <div style="display: flex; flex-direction: column;  align-items: center;">
          <div style="align-items: center"> <h1>{{article.title}}</h1></div>
          <div style="padding-bottom: 10px; width: 100%; border-bottom: 2px solid black; display: flex;">
            <span style="width: 33%; text-align: left;">发布时间:{{article.createTime}}</span>
            <span style="width: 33%; text-align: center;">文章类型:{{article.type}}</span>
            <span style="width: 33%; text-align: right;">发布者:{{user.username}}</span>
          </div>
        </div>
        <div style="margin-top: 20px;" v-html="this.article.content"></div>
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
  name: "Article",
  components:{
    myEditor
  },
  data() {
    return {
      article:'',
      id:'',
      user:'',
    }
  },
  created() {

  },
  mounted() {
    this.id= this.$route.query.id;
     this.searchArticle(this.id);
  },
  methods:{
    searchArticle(id){
      axios.get("http://localhost:8087/article/"+id).then(res =>{
            this.article=res.data.data;
            console.log(this.search)
             this.getuser(this.article.authorId);
            if (res.data.code!=='0') {
              this.$message({
                type: "error",
                message: res.data.msg
              })
            }
          }
      );
    },
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
    }
  }


}
</script>

<style scoped>
@import "../assets/css/foot.css";/*底栏*/

.neirong{
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
  margin-top: 20px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-right: calc(0px - 0.5rem / 2);
  margin-left: calc(0px - 0.5rem / 2);
  box-sizing: border-box;
}


.q-view .q-head .q-title {
  display: inline-block;
  line-height: 1rem;
  font-size: 0.8rem;
}
.q-view .q-body>.q-b1>.q-question {
  padding: 10px 1px;
  color: #333;
}
.q-view .q-body>.q-b1>.q-question>.q-option {
  padding: 10px 0 0 0;
}
.q-view .q-body>.q-b1>.q-question>.q-option>.q-item {
  padding: 5px 5px 5px 25px;
  border: 1px solid #eee;
  margin: 0 0 10px 0;
  border-radius: 3px;
  cursor: pointer;
}
.q-view .q-body>.q-b1>.q-question>.q-option>.q-item>.q-choice {
  float: left;
  margin: 0 0 0 -20px;
}

.q-panel  {
  padding: .75rem;
}
.q-panel .q-head  {
  float: right;
}

.q-panel .q-head {
  display: inline-block;
  line-height: 1rem;
  font-size: 1.1rem;
}

</style>