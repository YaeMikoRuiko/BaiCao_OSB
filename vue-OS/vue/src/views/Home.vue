<template>

<div class="neirong">
  <!-- 主体内容 -->
  <section class="home">
    <div class="head-sculpture">
      <img src="../components/images/god.jpeg" class="touxiang">
    <div class='particle-border'>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
    <div class='particle'></div>
  </div>
    </div>
    <div class = "opener_container" style="font-size: 50px; margin-top: 350px;">
      <h1 class="neon" >张&nbsp家嘉</h1>
    </div>
    <div class = "opener_container" style="font-size: 20px;margin-top: 450px; ">
      <h1 class="neon" >本科毕业设计《操作系统》题库</h1>
    </div>
    <div class="tiku_text" >
      <h1 class="tiku_h1" data-text="Operating System Question Bank">
        Operating System Question Bank
      </h1>
    </div>
      <div class="video_body_nr">
        <div class="video_button">
          <input type="checkbox" style="display: none;" id="conductVideo">
          <label for="conductVideo" class="conductButton">
            <div class="buttonStart">
            </div>
          </label>
          <div class="videos">
            <label for="conductVideo" class="offbutton">

            </label>
            <video src="../components/video/宣传视频.mp4" muted="muted" controls="controls" autoplay style="width: 100%;">

            </video>
          </div>
          <div class="shade">

          </div>
        </div>

        <div class="glide_cartoon">
          <div>
            <img src="../components/images/下滑动画.png">
          </div>
          <div>
            <img src="../components/images/下滑动画.png">
          </div>
          <div>
            <img src="../components/images/下滑动画.png">
          </div>
        </div>

      </div>

  </section>
  <!-- 新闻资讯 -->
  <section class="news">
    <h3 class="news_text">
      项目介绍
    </h3>
    <div class = "opener_container" style="font-size: 20px; margin-top: 10px;position: relative;">
      <span class="neon" ><p>《操作系统》是计算机类专业的必修课程，随着大学课程教学改革的不断深入,科学、客观、准确地检查与评价这门课程的教学质量,对于指导、管理这门课程的教学工作具有重要意义。</p>
        为了提高老师工作效率, 更有助于推进考试管理水平的科学化自动化, 为此本人开始开发《操作系统》题库系统。</span>
    </div>
    <div class="jieshao" >
      <el-carousel :interval="2000" type="card" height="400px" >
        <el-carousel-item v-for="item in picture" :key="item">
          <img :src="'http://localhost:8088'+item.imgUrl" style="width: 900px;height: 400px"/>
        </el-carousel-item>
      </el-carousel>
    </div>

  </section>

  <!-- 国家 -->
  <section class="city">
    <ul class="city_ul">
      <li class="city_li">
        <div class="city_bg1" >

        </div>
        <p class="city_text">
          随机组卷
        </p>
        <div class="figure1" >

        </div>
      </li>
      <li class="city_li">
        <div class="city_bg2" >

        </div>
        <p class="city_text">
          超多题型
        </p>
        <div class="figure2" >

        </div>
      </li>
      <li class="city_li">
        <div class="city_bg3" >

        </div>
        <p class="city_text">
          在线做题
        </p>
        <div class="figure3" >

        </div>
      </li>
      <li class="city_li">
        <div class="city_bg4" >

        </div>
        <p class="city_text">
          多种模式
        </p>
        <div class="figure4">

        </div>
      </li>
      <li class="city_li_two">
        <div class="city_bg5" >

        </div>
        <p class="city_text_two">
          敬请期待
        </p>
      </li>
    </ul>

  </section>
  <!-- 右边关注 -->
  <div class="right_gz">
    <input type="checkbox" class="right_jt" id="gz" checked>
    <label for="gz" class="right_jt" id="an1">

    </label>
    <div class="gz_2">
      <ul>
        <li>
          <div class="right_top">
            <p>
              关注我们
            </p>
            <img src="../components/images/关注我们.png">
            <p>
              扫码关注微信
            </p>
          </div>
        </li>
        <li>
          <div class="right_weibo right_bj">
            <img src="../components/images/微博关注.png">
            <span>官方微博</span>
          </div>
        </li>
        <li>
          <div class="right_tap right_bj">
            <img src="../components/images/微博关注.png">
            <span>taptap</span>
          </div>
        </li>
        <li>
          <div class="right_mys right_bj">
            <img src="../components/images/微博关注.png">
            <span>官方社区</span>
          </div>
        </li>
      </ul>
    </div>
  </div>
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

<script >
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      form: {},
      article: {},
      picture:{},
    };
  },
  mounted() {
     this.load();
  },
  methods: {
    load(){
      axios.get("http://localhost:8087/lunbo/list?pageNum=1&pageSize=4").then(res =>{
             console.log(res.data.data.records)
        this.picture=res.data.data.records
      });
    },
  }
}
</script>

<style scoped>
@import '../assets/css/circle.css';/*头像动画*/
@import '../assets/css/glide_cartoon.css';/*下滑动画*/
@import '../assets/css/name_cartoon.css';/*开发者介绍动画*/
@import '../assets/css/tiku_text.css';/*题库介绍动画*/
@import "../assets/css/news.css";/*新闻栏目*/
@import "../assets/css/guojia.css";
@import "../assets/css/right.css";/*关注我们*/
@import "../assets/css/foot.css";/*底栏*/

.neirong{
  display: flex;
  flex-direction: column;
  height: 100%;
}

.home  {
  position: relative;
  width: 100%;
  height: 1024px;
  overflow: hidden;
  v-index:1;
  background: url("../components/images/首页背景.jpg") no-repeat center/cover;
  backdrop-filter: blur(10px); /* 添加模糊效果 */
}

.home::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4); /* 设置半透明的黑色背景 */
  v-index:1;

}

.video_body_nr {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-end;
  position: absolute;
  z-index: 5;
  top: 0px;
  bottom: 0px;
  left: 0px;
  right: 0px;
}

.video_button {
  margin-bottom: 20px;
  width: 356px;
  height: 76px;
  background: url("../components/images/video背景.png") no-repeat center;
}

.conductButton {
  display: block;
  position: relative;
  margin: 14px auto 0;
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border: none;
  background-color: #fff;                         
  cursor: pointer;
  transition: all 0.2s linear;
}

.conductButton:hover {
  background-color: transparent;
}

.buttonStart {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 28px;
  height: 28px;
  background: url("../components/images/videoButton.png") no-repeat center top;
  transform: translate(-50%, -50%);
}

.conductButton:hover .buttonStart {
  background-position: center bottom;
}

.videos {
  display: none;
  width: 896px;
  height: 504px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  z-index: 111;
}

.offbutton {
  position: absolute;
  right: -62px;
  top: 10px;
  width: 32px;
  height: 32px;
  background: url("../components/images/宣传视频关闭.png");
  opacity: 0.65;
  cursor: pointer;
  z-index: 111;
}

.offbutton:hover {
  opacity: 1;
}

#conductVideo:checked~.videos {
  display: block;
}

#conductVideo:checked~.shade {
  display: block;
}

.shade {
  display: none;
  position: absolute;
  z-index: 11;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.66);
}


/*头像+动画*/

.head-sculpture{
  position: relative;
  width: 100%;
  height: 100%;
  z-index: 4;
  margin-bottom: 0px;
  height: 0px;
  top:300px;
}
/*产品介绍*/
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
.jieshao{
  position: relative;
  width: 80%;
  height: 50%;
  top:10%;
  left: 10%;
}
.el-carousel__item h3 {
  position: absolute;
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 500px;
  margin: 0;

}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

/*右栏关注我们*/
#an1::after {
  content: '';
  display: block;
  background: url("../components/images/关注箭头.png");
  width: 12px;
  height: 18px;
  transition: all 0.5s;
}
</style>