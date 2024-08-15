<template>
<div class="neirong">
  <section class="c-container">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="top: 100px;left: 100px;">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/tiku' }">题库</el-breadcrumb-item>
      <el-breadcrumb-item >随机测试</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="cinfo">
      <div style="height: 1100px;width:105%;background-color: rgb(255,255,255);margin-bottom: 10px;border-radius: 0.5rem;padding:10px " type="border-card">
        <div style="display: flex;margin-bottom: 10px">
          <p v-if="questionType==='choice'"  style="text-align: left;flex:1">选择题</p>
          <p v-if="questionType==='judge'" style="text-align: right;">判断题</p>
          <span>共{{count}}题</span>
        </div>
        <div v-if="questionType==='choice'" style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in questions" :key="index" style="  margin-top: 3px;">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <el-radio-group  v-model="index.userAnswer">
                  <el-radio label="A">A.{{index.optionA}}</el-radio>
                  <el-radio label="B">B.{{index.optionB}}</el-radio>
                  <el-radio label="C">C.{{index.optionC}}</el-radio>
                  <el-radio label="D">D.{{index.optionD}}</el-radio>
                </el-radio-group>
                <div>
                  <div v-if="index.userAnswer==index.answer&&showAnswer">正确</div>
                  <div v-if="index.userAnswer!=index.answer&&showAnswer">错误,正确答案为{{index.answer}}</div>
                  <el-button  v-if="showAnswer" style="float: right; padding: 3px 0" type="text" @click="getQuestion(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        <div v-if="questionType==='judge'" style="max-height: 1000px;overflow: auto;">
          <div v-for="(index, item) in questions" :key="index" style="  margin-top: 3px;">
            <el-card class="box-card">
              <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
              <el-radio-group  v-model="index.userAnswer">
                <el-radio label="对">正确</el-radio>
                <el-radio label="错">错误</el-radio>
              </el-radio-group>
              <div>
                <div v-if="index.userAnswer==index.answer&&showAnswer">正确</div>
                <div v-if="index.userAnswer!=index.answer&&showAnswer">错误,正确答案为{{index.answer}}</div>
                <el-button  v-if="showAnswer" style="float: right; padding: 3px 0" type="text" @click="getQuestion(index)">
                  查看本题更多信息
                </el-button>
              </div>
            </el-card>
          </div>
        </div>
        <div style="display: flex; justify-content: center;">
          <el-button @click="getResult" style="margin-top: 10px;">查询作答结果</el-button>
          <el-button @click="getQuestions" style="margin-top: 10px;">再测一次</el-button>
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
  name: "Random",
  components:{
    myEditor
  },
  data() {
    return {
      questionType :'',
      count:'',
      questions:{},
      showAnswer: false,
    }
  },
  created() {

  },
  mounted() {
     this.questionType = this.$route.query.questionType;
     this.count = this.$route.query.count;
     this.getQuestions();

  },
  methods:{
    getQuestions(){
      this.showAnswer=false,
      axios.get("http://localhost:8087/test/random?questionType="+this.questionType+"&count="+this.count).then(res =>{
            this.questions=res.data.data.records;
            console.log(this.questions)
            if (res.data.code!=='0') {
              this.$message({
                type: "error",
                message: res.data.msg
              })
            }
          }
      );
    },
    getResult() {
      let correctCount = 0; // 记录正确答案的数量
      let wrongCount = 0; // 记录错误答案的数量
      console.log(this.questions)
      for (let key in this.questions) {
        if (!this.questions[key].userAnswer) {
          alert('存在空值，请填写答案');
          return;
        }

        if (this.questions[key].userAnswer === this.questions[key].answer) {
          correctCount++;
        } else {
          wrongCount++;
        }
      }
      console.log(`正确答案数量：${correctCount}`);
      console.log(`错误答案数量：${wrongCount}`);
      let correctRate=correctCount/(correctCount+wrongCount)
      alert(`正确答案数量：${correctCount} `+`错误答案数量：${wrongCount} `+`正确率：${correctRate}`)
      this.showAnswer=true;
    },
    getQuestion(index){
      sessionStorage.removeItem("question");
      sessionStorage.setItem("question",JSON.stringify(index));//缓存用户信息
      this.$router.push("/"+this.questionType+"/"+this.questionType+"Question="+index.id)
    },

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
  background: url("../components/images/题目详情.jpg") no-repeat center/cover;
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

.q-view {
  padding: .75rem;
  border-bottom: 1px dashed #eee;
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

.q-panel .q-head {
  padding: .75rem;
}
.q-panel .q-head .q-more {
  float: right;
}

.q-panel .q-head .q-title {
  display: inline-block;
  line-height: 1rem;
  font-size: 1.1rem;
}
.ub-text-muted {
  color: rgb(70, 64, 64);
  text-decoration: none;
}

.text-ellipsis {
  white-space: nowrap; /* 不换行 */
  overflow: hidden; /* 隐藏超出部分 */
  text-overflow: ellipsis; /* 显示省略号 */
}
</style>