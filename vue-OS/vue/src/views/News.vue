<template>
  <div class="news_page">
  <!-- 新闻资讯 -->
  <section class="news">
    <h3 class="news_text">
      新闻资讯
    </h3>
    <div class="shouye_new_neiron">
      <div class="new_lunbo">
        <input type="radio" name="lunbo" id="lbt_1" checked>
        <label for="lbt_1">

        </label>

        <input type="radio" name="lunbo" id="lbt_2">
        <label for="lbt_2">

        </label>

        <input type="radio" name="lunbo" id="lbt_3">
        <label for="lbt_3">

        </label>

        <input type="radio" name="lunbo" id="lbt_4">
        <label for="lbt_4">

        </label>
        <ul class="clearfix" v-for="item in picture" :key="item">
          <li >
            <img :src="'http://localhost:8088'+item.imgUrl">
          </li>

        </ul>
      </div>
      <div class="new_you_neiron">
        <input type="radio" name="daohan" id="max_new" checked>
        <label for="max_new">
          最新
        </label>
        <input type="radio" name="daohan" id="new">
        <label for="new">
          新闻
        </label>
        <input type="radio" name="daohan" id="gong_gao">
        <label for="gong_gao">
          公告
        </label>
        <ul class="new_you_bottom">
          <li  v-for="index in form" :key="index">
            <div @click="getOneArticle(index)">
              <p>
                {{ index.title }}
              </p>
              <p>
                {{ index.createTime }}
              </p>
            </div>
          </li>
          <p @click="this.$router.push('/article')">
            查看全部资讯
          </p>
        </ul>
        <ul class="new_you_bottom">
          <li  v-for="index in news" :key="index">
            <div  @click="getOneArticle(index)">
              <p>
                {{ index.title }}
              </p>
              <p>
                {{ index.createTime }}
              </p>
            </div>
          </li>
          <p @click="this.$router.push('/article')">
            查看全部资讯
          </p>
        </ul>
        <ul class="new_you_bottom">
          <li  v-for="index in notices" :key="index">
            <div  @click="getOneArticle(index)">
              <p>
                {{ index.title }}
              </p>
              <p>
                {{ index.createTime }}
              </p>
            </div>
          </li>
          <p @click="this.$router.push('/article')">
            查看全部资讯
          </p>
        </ul>
      </div>
    </div>
  </section>
  <!-- 底部 -->
  <footer class="footer">
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
import axios from "axios";
export default {
  name: "News",
  data() {
    return {
      form: {},
      news: '',
      notices: '',
      picture:[],
    }
  },
      mounted() {
        this.getArticle()
      },
      methods:{
        getArticle(){
          //获取轮播图
          axios.get("http://localhost:8087/lunbo/list?pageNum=2&pageSize=4").then(res =>{
            console.log(res.data.data.records)
            this.picture=res.data.data.records
          });//获取文章
          axios.get("http://localhost:8087/article/news").then(res =>{
            this.form=res.data.data.result;
            this.news=res.data.data.news;
            this.notices=res.data.data.notice;
            if (res.data.code==='0'){
              console.log(res.data.data);
            }else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          });
        },
        getOneArticle(index){
           this.$router.push('/news/article/one?id='+index.articleId);
         }
  },
}
</script>

<style scoped>
@import "../assets/css/foot.css";/*底栏*/
/*新闻*/
.news_page{
  display: flex;
  flex-direction: column;
  height: 100%;
}
.news {
  position: relative;
  display: flex;
  flex-direction: column;
  color: #fff;
  width: 100%;
  height: 1024px;
  background: url("../components/images/新闻资讯背景.jpg") no-repeat center/cover;
}

.news_text {
  position: relative;
  margin: 183px auto 60px;
  font-size: 50px;
  font-weight: 400;
  text-align: center;
}

.news_text::before,
.news_text::after {
  content: '';
  position: absolute;
  top: 50%;
  width: 385px;
  height: 14px;
  background: url("../components/images/新闻标题修饰.png") no-repeat right center/cover;
}

.news_text::before {
  left: 0;
  transform: translate(calc(-100% - 32px), -50%);
}

.news_text::after {
  right: 0;
  transform: translate(calc(100% + 32px), -50%) rotateY(180deg);
}

.shouye_new_neiron {
  display: flex;
  justify-content: center;
  margin: 0px auto;
  height: 400px;
}

.shouye_new_neiron .new_lunbo,
.shouye_new_neiron .new_you_neiron {
  overflow: hidden;
  position: relative;
  width: 640px;
  background: rgba(38, 18, 12, 0.60);
}

.shouye_new_neiron .new_lunbo ul {
  width: calc(640px*4);
  transition: all 0.7s;
}

.shouye_new_neiron .new_lunbo ul li {
  float: left;
  width: 640px;
  height: 400px;
}

.shouye_new_neiron .new_lunbo ul li img {
  width: 100%;
  height: 100%;
}

.shouye_new_neiron .new_lunbo input {
  display: none;
}

.shouye_new_neiron .new_lunbo label {
  position: absolute;
  z-index: 999;
  top: 370px;
  width: 18px;
  height: 18px;
  border: solid 2px rgba(255, 255, 255, 0.5);
  border-radius: 50%;
  cursor: pointer;
  transition: all 0.3s;
}

.shouye_new_neiron .new_lunbo label:nth-of-type(1) {
  left: 40%;
}

.shouye_new_neiron .new_lunbo label:nth-of-type(2) {
  left: 45%;
}

.shouye_new_neiron .new_lunbo label:nth-of-type(3) {
  left: 50%;
}

.shouye_new_neiron .new_lunbo label:nth-of-type(4) {
  left: 55%;
}

.shouye_new_neiron .new_lunbo #lbt_1:hover~ul {
  margin-left: 0px;
}

.shouye_new_neiron .new_lunbo #lbt_1:hover~label:nth-of-type(1) {
  background: white;
}


.shouye_new_neiron .new_lunbo #lbt_2:hover~ul {
  margin-left: -640px;
}

.shouye_new_neiron .new_lunbo #lbt_2:hover~label:nth-of-type(2) {
  background: white;
}

.shouye_new_neiron .new_lunbo #lbt_3:hover~ul {
  margin-left: -1280px;
}

.shouye_new_neiron .new_lunbo #lbt_3:hover~label:nth-of-type(3) {
  background: white;
}

.shouye_new_neiron .new_lunbo #lbt_4:hover~ul {
  margin-left: -1920px;
}

.shouye_new_neiron .new_lunbo #lbt_4:hover~label:nth-of-type(4) {
  background: white;
}

.shouye_new_neiron .new_you_neiron input {
  display: none;
}

.shouye_new_neiron .new_you_neiron label {
  position: relative;
  left: 20px;
  padding: 0 19px 12px;
  display: inline-block;
  margin: 35px 0px 0px;
  border-bottom: 3px solid rgba(255, 255, 255, 0.1);
  font-size: 18px;
  cursor: pointer;
}

.shouye_new_neiron .new_you_neiron ul {
  width: 100%;
  margin: 0px auto;
}

.new_you_bottom li div {
  position: relative;
  display: flex;
  align-items: center;
  width: 100%;
  height: 50px;
  border-bottom: 2px solid rgba(255, 255, 255, 0.1);
  font-size: 16px;
  color: #fff;
}

.new_you_bottom li div p:first-of-type {
  width: 480px;
  height: 20px;
  line-height: 20px;
  padding-left: 11px;
}

.new_you_bottom li div p:last-of-type {
  position: absolute;
  top: 0;
  right: 11px;
  color: rgba(255, 255, 255, 0.35);
  font-size: 14px;
  height: 50px;
  line-height: 50px;
}

.new_you_bottom>p {
  position: absolute;
  right: 33px;
  bottom: 30px;
  padding-left: 38px;
  height: 22px;
  background: url("../components/images/新闻符号.png") no-repeat;
  line-height: 22px;
  font-size: 18px;
  color: rgba(255, 255, 255, 0.7);
  cursor: pointer;
}

.new_you_bottom li:hover {
  background: rgba(255, 255, 255, 0.1);
}

.new_you_bottom>p:hover {
  color: #ffd49f;
  background: url("../components/images/新闻符号.png") no-repeat;
}

.new_you_bottom {
  display: none;
}

#max_new:checked~label:nth-of-type(1) {
  color: #ffd49f;
  border-bottom: #ffd49f 3px solid;
}

#max_new:checked~ul:nth-of-type(1) {
  display: block;
}

#new:checked~label:nth-of-type(2) {
  color: #ffd49f;
  border-bottom: #ffd49f 3px solid;
}

#new:checked~ul:nth-of-type(2) {
  display: block;
}

#gong_gao:checked~label:nth-of-type(3) {
  color: #ffd49f;
  border-bottom: #ffd49f 3px solid;
}

#gong_gao:checked~ul:nth-of-type(3) {
  display: block;
}

</style>