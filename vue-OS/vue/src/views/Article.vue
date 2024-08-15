<template>
<div class="neirong">
  <section class="c-container">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right" style="top: 100px;left: 100px;color: white;">
      <el-breadcrumb-item :to="{ path: '/' }" >首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/news' }">新闻</el-breadcrumb-item>
      <el-breadcrumb-item >查看全部新闻资讯</el-breadcrumb-item>
    </el-breadcrumb></div>

    <div class="cinfo">
      <div style="height: 1100px;width:105%;background-color: rgb(255,255,255);margin-bottom: 10px;border-radius: 0.5rem;padding:10px " type="border-card">
         <div style="display: flex;align-items: center;margin-bottom: 10px ">    <!--    justify-content: center; -->
          <div style="text-align: left;flex:0.5" v-if="search">
            <span style="text-align: left;">"{{ search }}"共计{{article.length}}条记录</span>
          </div>
           <div style="text-align: left;flex:0.5" v-if="!search">
             <span style="text-align: left;"></span>
           </div>

          <span style="display: inline-block;">关键词</span>
          <el-input v-model="search" style="width: 200px;"></el-input>
          <el-button @click="searchArticle" >搜索</el-button>
        </div>
        <div  style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in article" :key="index" style="  margin-top: 3px;">
              <el-card class="box-card"   @click="getOneArticle(index)">
                <div>
                  <span v-if="index.type=='新闻'"><el-tag  size="medium">新闻</el-tag></span>
                  <span v-if="index.type=='公告'"><el-tag type="success" size="公告">公告</el-tag></span>
                  <span style="text-align: right;font-size: 12px;color: #7f858a"> &nbsp&nbsp&nbsp&nbsp&nbsp{{index.createTime}}</span>
                  <div>

                  </div>

                  <div v-html="index.title" style="margin-top: 10px"></div></div>

              </el-card>
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
  name: "Article",
  components:{
    myEditor
  },
  data() {
    return {
      news:'',
      notices:'',
      article:'',
      search:'',
    }
  },
  created() {

  },
  mounted() {
     this.search = this.$route.query.search;
     this.searchArticle();
  },
  methods:{
    searchArticle(){
      axios.get("http://localhost:8087/article/all?search="+this.search).then(res =>{
            this.article=res.data.data;
            console.log(this.search)
            if (res.data.code!=='0') {
              this.$message({
                type: "error",
                message: res.data.msg
              })
            }
          }
      );
    },
    getOneArticle(index){
      this.$router.push('/news/article/one?id='+index.articleId);
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