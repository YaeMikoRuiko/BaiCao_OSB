<template>
<div class="neirong">
  <section class="tiku_chapter-header">
    <div class="flexbox">
      <div class="search">
        <h1>Search this site</h1>
        <h3>点击图标，输入关键字，回车/单击开始搜索</h3>
        <div>
          <input type="text" v-model="search" placeholder="Search . . ." required @keyup.enter="handleEnter()" >
        </div>
      </div>
    </div>
    <div class="buttons">
      <button class="raise" style="top:200px;left:0px" @click="this.$router.push('/choice');">选择题</button>
      <button class="raise" style="top:300px;left:300px" @click="this.$router.push('/judge');">判断题</button>
      <button class="raise" style="top:400px;left:0px" @click="this.$router.push('/fill');">填空题</button>
      <button class="raise" style="top:200px;left:300px" @click="this.$router.push('/short');"> 简答题</button>
      <button class="raise" style="top:300px;left:0px" @click="this.$router.push('/application');">应用题</button>
      <button class="raise" style="top:400px;left:300px" @click="this.dialogVisible=true">随机刷题</button>
    </div>
    <div>
      <el-dialog
          title="随机刷题设置"
          v-model="dialogVisible"
          width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="题目类型">
            <el-select v-model="form.questionType" placeholder="请选择题目类型" >
              <el-option label="选择题" value="choice"></el-option>
              <el-option label="判断题" value="judge"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="题目数量">
            <el-select v-model="form.count" placeholder="请选择题目数量">
              <el-option label="10题" value="10"></el-option>
              <el-option label="15题" value="15"></el-option>
              <el-option label="20题" value="20"></el-option>
            </el-select>
          </el-form-item>

        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="randomQuestion">确 定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </section>
  <section class="tiku_show">
    <div class="tiku_chapter">
      <p class="container-chapter-title">章节练习<span class="detail">考点各个击破，重难点自测</span></p>
      <div class="chapter-title-underline"></div>
      <div class="class-box-container">
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="17">
            <div class="box-content">
              <div class="box-icon icon-wenke"></div>
              <p class="box-classname" course_id="17">计算机操作系统概述</p>
              <p class="box-classnum"><span>{{ counts.os }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="16">
            <div class="box-content">
              <div class="box-icon icon-wenke"></div>
              <p class="box-classname" course_id="16">处理器管理</p>
              <p class="box-classnum"><span>{{ counts.cpu }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="18">
            <div class="box-content">
              <div class="box-icon icon-wuli"></div>
              <p class="box-classname" course_id="18">存储管理</p>
              <p class="box-classnum"><span>{{ counts.space }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="19">
            <div class="box-content">
              <div class="box-icon icon-huaxue"></div>
              <p class="box-classname" course_id="19">设备管理</p>
              <p class="box-classnum"><span>{{ counts.io }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="20">
            <div class="box-content">
              <div class="box-icon icon-shengwu"></div>
              <p class="box-classname" course_id="20">文件管理</p>
              <p class="box-classnum"><span>{{ counts.file }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="26">
            <div class="box-content">
              <div class="box-icon icon-zhengzhi"></div>
              <p class="box-classname" course_id="26">并发程序设计</p>
              <p class="box-classnum"><span>{{ counts.program }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/chapter"  class="class-link" data-courseid="27">
            <div class="box-content">
              <div class="box-icon icon-lishi"></div>
              <p class="box-classname" course_id="27">网络环境下的操作系统</p>
              <p class="box-classnum"><span>{{ counts.net }}</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-28-knowpoint-11"  class="class-link" data-courseid="28">
            <div class="box-content">
              <div class="box-icon icon-dili"></div>
              <p class="box-classname" course_id="28">我的错题集</p>
              <p class="box-classnum"><span>89</span>道错题</p>
            </div>
          </a>
        </div>
      </div>
      <div class="class-box-container1" >
        <div style="background-color: rgba(255,255,255,0.38)">
          <el-carousel height="400px" direction="vertical" :autoplay="true">
            <el-carousel-item v-for="item in this.picture" :key="item">
              <div class="image-container">
                <img :src="'http://localhost:8088'+item.imgUrl" class="full-size-image" />
              </div>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
    <div class="tiku_chapter2">
      <p class="container-chapter-title">题目推荐<span class="detail">考点各个击破，重难点自测</span></p>
      <div class="chapter-title-underline"></div>
      <div class="row">
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/1">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/1">
                  <i class="iconfont icon-list-alt"></i>
                  计算机操作系统概述
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in choiceData.os" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/choice/choiceQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>

            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/5">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/5">
                  <i class="iconfont icon-list-alt"></i>
                  处理器管理
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in choiceData.cpu" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/choice/choiceQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/8">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/8">
                  <i class="iconfont icon-list-alt"></i>
                  存储管理
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in choiceData.space" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/choice/choiceQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/2">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/2">
                  <i class="iconfont icon-list-alt"></i>
                  设备管理
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in choiceData.io" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/choice/choiceQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/3">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/3">
                  <i class="iconfont icon-list-alt"></i>
                  文件管理
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in choiceData.file" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/choice/choiceQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/4">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/4">
                  <i class="iconfont icon-list-alt"></i>
                  并发程序设计
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in judgeData.program" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/judge/judgeQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/6">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/6">
                  <i class="iconfont icon-list-alt"></i>
                  网络环境下的操作系统
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item" v-for="index in judgeData.net" :key="index">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;" ></i>
                </div>
                <div class="chapter-question" @click="getQuestion(index)">
                  <a :href="`/judge/judgeQuestion=${index.id}`" class="chapter-link">
                    {{ index.question}}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="ub-panel">
            <div class="chapter-head">
              <div class="more">
                <a class="ub-text-muted" href="/question/cat/12">
                  更多
                </a>
              </div>
              <div class="chapter-title">
                <a class="chapter-tiku" href="/question/cat/12">
                  <i class="iconfont icon-list-alt"></i>
                  自定义题库
                </a>
              </div>
            </div>
            <div class="chapter-head">
              <div class="chapter-question-item">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;"></i>
                </div>
                <div class="chapter-question">
                  <a href="/question/view/7kpgnze6yuz49fiu" class="chapter-link">
                    敬请期待
                  </a>
                </div>
              </div>
              <div class="chapter-question-item">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;"></i>
                </div>
                <div class="chapter-question">
                  <a href="/question/view/ichshhrr294e4hgr" class="chapter-link">
                    敬请期待
                  </a>
                </div>
              </div>
              <div class="chapter-question-item">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;"></i>
                </div>
                <div class="chapter-question">
                  <a href="/question/view/if5hg0omxhsbqt7k" class="chapter-link">
                    敬请期待
                  </a>
                </div>
              </div>
              <div class="chapter-question-item">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;"></i>
                </div>
                <div class="chapter-question">
                  <a href="/question/view/xmg8j2bsn0qyfvbg" class="chapter-link">
                    敬请期待
                  </a>
                </div>
              </div>
              <div class="chapter-question-item">
                <div class="chapter-icon">
                  <i class="none1" style="font-size:1rem;"></i>
                </div>
                <div class="chapter-question">
                  <a href="/question/view/iav3kijr4stxzqkj" class="chapter-link">
                    敬请期待
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="tiku_chapter3">
      <p class="container-chapter-title">资料分享<span class="detail">考点各个击破，重难点自测</span></p>
      <div class="chapter-title-underline"></div>
      <div class="class-box-container">
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-17-knowpoint-11"  class="class-link" data-courseid="17">
            <div class="box-content">
              <div class="box-icon icon-wenke"></div>
              <p class="box-classname" course_id="17">计算机操作系统概述</p>
              <p class="box-classnum"><span>495</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-16-knowpoint-11"  class="class-link" data-courseid="16">
            <div class="box-content">
              <div class="box-icon icon-wenke"></div>
              <p class="box-classname" course_id="16">处理器管理</p>
              <p class="box-classnum"><span>534</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-18-knowpoint-11"  class="class-link" data-courseid="18">
            <div class="box-content">
              <div class="box-icon icon-wuli"></div>
              <p class="box-classname" course_id="18">存储管理</p>
              <p class="box-classnum"><span>277</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-19-knowpoint-11"  class="class-link" data-courseid="19">
            <div class="box-content">
              <div class="box-icon icon-huaxue"></div>
              <p class="box-classname" course_id="19">设备管理</p>
              <p class="box-classnum"><span>446</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-20-knowpoint-11"  class="class-link" data-courseid="20">
            <div class="box-content">
              <div class="box-icon icon-shengwu"></div>
              <p class="box-classname" course_id="20">文件管理</p>
              <p class="box-classnum"><span>136</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-26-knowpoint-11"  class="class-link" data-courseid="26">
            <div class="box-content">
              <div class="box-icon icon-zhengzhi"></div>
              <p class="box-classname" course_id="26">并发程序设计</p>
              <p class="box-classnum"><span>372</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-27-knowpoint-11"  class="class-link" data-courseid="27">
            <div class="box-content">
              <div class="box-icon icon-lishi"></div>
              <p class="box-classname" course_id="27">网络环境下的操作系统</p>
              <p class="box-classnum"><span>230</span>道题目</p>
            </div>
          </a>
        </div>
        <div class="class-box">
          <a href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-28-knowpoint-11"  class="class-link" data-courseid="28">
            <div class="box-content">
              <div class="box-icon icon-dili"></div>
              <p class="box-classname" course_id="28">我的错题集</p>
              <p class="box-classnum"><span>89</span>道错题</p>
            </div>
          </a>
        </div>
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
const axios = require("axios");
export default {
  name: "Tiku",
  data() {
    return {
      form: {
          questionType:'choice',
          count:'10',
      },
      search: '',// 用于存储输入框中的内容
      choiceData:{},
      judgeData:{},
      question:{},
      counts:{},
      dialogVisible:false,
      picture:[]
     }
    },
   created() {
     if(sessionStorage.getItem("role")){
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
    methods:{
    load(){
      axios.get("http://localhost:8087/lunbo/list?pageNum=3&pageSize=4").then(res =>{
        console.log(res.data.data.records)
        this.picture=res.data.data.records
      });
      axios.get("http://localhost:8087/choice/tiku_choice").then(res =>{
        console.log(res);
        //概论
        this.choiceData.os = res.data.data.os;
        this.choiceData.os = this.choiceData.os.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[选择题] "+text;
          return item;
        });
        //处理器
        this.choiceData.cpu = res.data.data.cpu;
        this.choiceData.cpu = this.choiceData.cpu.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[选择题] "+text;
          return item;
        });
        //内存
        this.choiceData.space = res.data.data.space;
        this.choiceData.space = this.choiceData.space.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[选择题] "+text;
          return item;
        });
        //设备
        this.choiceData.io = res.data.data.io;
        this.choiceData.io = this.choiceData.io.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[选择题] "+text;
          return item;
        });
        //文件
        this.choiceData.file = res.data.data.file;
        this.choiceData.file = this.choiceData.file.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[选择题] "+text;
          return item;
        });
      })
      axios.get("http://localhost:8087/judge/tiku_judge").then(res =>{
        console.log(res);
        //并发程序设计
        this.judgeData.program = res.data.data.program ;
        this.judgeData.program  = this.judgeData.program.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[判断题] "+text;
          return item;
        });
        //网络环境下的os
        this.judgeData.net = res.data.data.net;
        this.judgeData.net = this.judgeData.net.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question="[判断题] "+text;
          return item;
        });

      })
      axios.get("http://localhost:8087/chapter/allcount").then(res =>{
        console.log(res.data);
           this.counts=res.data.data;
      })
    },
      handleEnter()
      {
        // 在用户按下回车键时触发的方法
        console.log('用户开始搜索');
        console.log('搜索的内容是：', this.search);
        // 在这里可以将 this.inputData 的值传递给其他需要的地方
        this.$router.push("/search?search="+this.search)
      },
      getQuestion(index){
        //通过设置session联系 用户点击的对象与详情页
        //清除原有session
        sessionStorage.removeItem("question");
        //重新设置session
        sessionStorage.setItem("question",JSON.stringify(index));//缓存用户信息
        let a=sessionStorage.getItem("question");
        this.question=JSON.parse(a);
      },
      randomQuestion(){
         this.$router.push("/random?questionType="+this.form.questionType+"&count="+this.form.count);
      },
    }
}
</script>

<style scoped>
@import "../assets/css/search.css";/*搜索栏*/
@import "../assets/css/foot.css";/*底栏*/

.tiku_chapter-header  {
  position: relative;
  width: 100%;
  height: 1024px;
  overflow: hidden;
  v-index:1;
  background: url("../components/images/搜索栏.png") no-repeat center/cover;
  backdrop-filter: blur(10px); /* 添加模糊效果 */
}
.tiku_show {
  position: relative;
  width: 100%;
  height: 3546px;
  overflow: hidden;
  v-index:1;
  background: url("../components/images/练习区.jpg") no-repeat center/cover;
  backdrop-filter: blur(10px); /* 添加模糊效果 */
}
.tiku_chapter{
  position: relative;

  height: 600px;
  width: 100%;
  /*opacity: 50%;*/
}
.tiku_chapter2{
  position: absolute;
  height: 2000px;
  width: 100%;
  /*opacity: 50%;*/
}
.tiku_chapter3{
  position: absolute;
  top:1900px;
  height: 600px;
  width: 100%;
  /*opacity: 50%;*/
}
.container-chapter-title {
  background: rgba(255, 255, 255, 0.34);
  height: 60px;
  font-size: 30px;
  text-align: center;
  color: #333;
  font-weight: 700;
  display: flex; /* 使用 Flex 布局 */
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
}
.detail {
  color: #666;
  font-size: 12px;
  font-weight: 400;
  margin-left: 10px;
}
.chapter-title-underline {
  width: 100%;
  height: 4px;
  background: linear-gradient(to right, #000000 50%, #11a68d 50%);;
  margin: 0 auto;
  top:96px;
}
.class-box-container {
  position: absolute;
  width: 796px;
  padding-top: 20px;
  float: left;
  top:120px;
  left: 50px;
}
.class-box-container1 {
  position: absolute;
  width: 796px;
  padding-top: 20px;
  float: left;
  top:120px;
  right: 50px;
}


.class-box {
  float: left;
  position: relative;
  text-align: center;
  width: 198px;
  height: 198px;
  margin-top: -1px;
  margin-left: -1px;
  border: 1px #109d61 solid;
  background-color: rgba(255, 255, 255, 0.49);
}
.class-box:hover {
  background-color: #c5efc2;
}
.class-link{
  display: inline-block;
  width: 100%;
  height: 100%;
  text-align: center;
  text-decoration: none;
}

.box-content {
  margin-top: 45px;
}
.box-icon {
  width: 59px;
  height: 59px;
  margin: 0 auto;
}
.icon-wenke {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-wenke_a67e3bd.png) center center no-repeat;

}
.icon-like {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-like_0f9d9d3.png) center center no-repeat;
}
.icon-wuli {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-wuli_b38a090.png) center center no-repeat;
}
.icon-huaxue {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-huaxue_d56a569.png) center center no-repeat;
}
.icon-shengwu {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-shengwu_c5a0fd8.png) center center no-repeat;
}
.icon-zhengzhi {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-zhengzhi_3e59ee4.png) center center no-repeat;
}
.icon-lishi {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-lishi_f9f6f16.png) center center no-repeat;
}
.class-box .icon-dili {
  background: url(//wkstatic.bdimg.com/static/gaokao/static/miti/tikupc/widget/index/classbox/images/icon-dili_ffc15ed.png) center center no-repeat;
}
.box-classname {
  line-height: 36px;
  font-size: 18px;
  color: #333;
}
.box-classnum {
  font-size: 12px;
  color: #666;
  height: 12px;
  line-height: 12px;
}
:root {
  --size-margin:0.5rem; /* 定义变量 --size-margin 并设置值为 20px */
  --size-radius: 0.4rem;
  --font-size-medium: 0.8rem;
}

.row {
  position: absolute;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-right: calc(0px - var(--size-margin) / 2);
  margin-left: calc(0px - var(--size-margin) / 2);
  box-sizing: border-box;
  width: 80%;
  top:100px;
  left:10%;
}
.col-md-6 {
  -webkit-box-flex: 0;
  -ms-flex: 0 0 50%;
  flex: 0 0 50%;
  max-width: 50%;
  position: relative;
  width: 100%;
  min-height: .05rem;
  padding-right: calc(var(--size-margin) / 2);
  padding-left: calc(var(--size-margin) / 2);
  box-sizing: border-box;
  border: 5px solid transparent; /* 设置边框为透明 */
  border-radius: 10px; /* 添加圆角边界，可以根据需要调整半径大小 */

}
.ub-panel {
  background: rgba(255, 255, 255, 0.7);
  border-radius: var(--size-radius);
  margin-bottom: var(--size-margin);
}
.ub-panel .chapter-head {
  padding: .75rem;
}
.ub-panel .chapter-head .more {
  float: right;
}
.ub-text-muted {
   color: rgb(70, 64, 64);
  text-decoration: none;
 }
.ub-text-muted:hover {
  color: rgb(60, 115, 235);
}
.ub-panel .chapter-head .chapter-title {
  display: inline-block;
  line-height: 1rem;
  font-size: var(--font-size-medium);

}
.chapter-tiku{
  color: #30a1e3;
  text-decoration: none;
}

.chapter-question-item {
  background-color: rgba(185, 189, 194, 0.1);
  height: 40px;
  border: 5px solid transparent; /* 设置边框为透明 */
  border-radius: 5px; /* 添加圆角边界，可以根据需要调整半径大小 */
  margin-top: 3px; /* 上边距为3px */
  margin-bottom: 3px; /* 下边距为3px */
}
.chapter-question-item:hover {
  background-color: rgba(185, 189, 194, 0.3);
}
.chapter-link {
  display: inline-block;
  width: 100%;
  height: 100%;
  text-align: left;
  text-decoration: none;
  overflow: hidden;
  white-space: nowrap;
  color: black;
  margin-top: 5px; /* 上边距为3px */
  margin-bottom: 5px; /* 下边距为3px */
}
.chapter-link:hover {
  color: #3c73eb;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
.image-container {
  width: 100%;
  height: 400px; /* 设置容器高度，可以根据需要调整 */
  overflow: hidden;
}

.full-size-image {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 让图片自适应并填充整个区域 */
}

</style>