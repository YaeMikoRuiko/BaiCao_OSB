<template>
<div class="neirong">
  <section class="q-container">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="top: 100px;left: 100px;">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/tiku' }">题库</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/application' }">应用题库</el-breadcrumb-item>
      <el-breadcrumb-item><p ><strong>{{ applicationData.chapter }}[{{applicationData.id}}]</strong></p></el-breadcrumb-item>
    </el-breadcrumb>
    <div class="qinfo">
         <div class="qinfo-left">
           <div  class="q-view" style="padding: 20px;padding-bottom:5px; ">
             <div class="q-head">
               <div class="q-title" style="font-size: 1.3rem">
                 <i class="el-icon-menu"></i>
                 应用题
               </div>
             </div>
             <div class="q-body">
               <div class="q-b1" >
                 <div class="q-question" >
                   <div v-html="applicationData.question"></div>
                   <div class="q-option">
                     <div class="q-item" @click="toggleAnswer(applicationData)">
                         <div v-if="!applicationData.showAnswer" ><i class="el-icon-zoom-in"> 点击查看答案</i></div>
                          <div v-if="applicationData.showAnswer" ><i class="el-icon-zoom-out"> 点击隐藏答案</i></div>
                     </div>
                   </div>
                   <div v-if="applicationData.showAnswer">
                    <i class="el-icon-s-claim">答案：</i> <div v-html="applicationData.answer"></div>
                     <i class="el-icon-s-opportunity">解析：</i> <div v-html="applicationData.explanation"></div>
                   </div>
                 </div>
               </div>
             </div>

         </div>
           <div class="q-info1" v-if="prevAndNext ">
             <div class="q-before" v-if="prevAndNext.prevApplication" style="padding: 20px" @click="getQuestion(prevAndNext.prevApplication)">
               <div class="qbf">
                 <div class="q-select" >
                   上一题
                 </div>
                 <div >
                   <a class="ub-text-muted" :href="`/application/applicationQuestion=${this.prevAndNext.prevApplication.id}`">
                     <div class="ub-text-muted text-ellipsis" >
                       {{this.prevAndNext.prevApplication.question}}
                   </div>
                   </a>
                 </div>
               </div>
             </div>
             <div class="q-after" v-if="prevAndNext.nextApplication"  style="padding: 20px" @click="getQuestion(this.prevAndNext.nextApplication)">
               <div class="qaf">
                 <div class="q-select">
                   下一题
                 </div>
                 <div >
                   <a class="ub-text-muted" :href="`/application/applicationQuestion=${this.prevAndNext.nextApplication.id}`">
                   <div class="ub-text-muted text-ellipsis" >
                     {{this.prevAndNext.nextApplication.question}}
                   </div>
                   </a>
                 </div>
               </div>
             </div>
           </div>
           <div class="q-view" style="padding: 20px">
           <div  style="  padding-bottom:15px;border-bottom: 2px solid rgba(151, 248, 139, 0.2);">
             我要评论
           </div>
           <myEditor editorId="editor1"  @editorDataChanged="handleEditorDataChanged" />
           <div class="q-button" style="display:flex;padding:10px;  justify-content: center; /* 垂直居中 */ align-items: center; /* 水平居中 */ ">
             <el-button @click="commit">提交</el-button>
           </div>

         </div>
           <div class="q-view" style="padding: 20px">
             <div  style="  padding-bottom:15px;border-bottom: 2px solid rgba(151, 248, 139, 0.2);">
               用户评论区
             </div>
             <div class="pinglun" style="max-height: 300px;overflow: auto;">
               <div class="q-comment" v-for="index in comments" :key="index" style="padding-bottom: 10px;padding-top: 20px;border-bottom: 2px solid rgba(151, 248, 139, 0.2);">
                 <div style="display: flex; align-items: center;">
                   <el-avatar>{{ index.username }}</el-avatar>
                   <div style="margin-left: 10px; display: flex; flex-direction: column;">
                     <span >{{index.account}}</span>
                     <span style="font-size: 11px;margin-top: 2px">{{index.createTime}}</span>
                   </div>
                 </div>
                 <div style="margin-top: 10px" v-html="index.commentText"></div>
                 <el-popconfirm
                     v-if="this.userComment.account===index.account"
                     width="220"
                     confirm-button-text="删除"
                     cancel-button-text="取消"
                     :icon="InfoApplicationed"
                     icon-color="#626AEF"
                     title="是否删除该条评论?"
                     @confirm="delectComment(index)"
                 >
                   <template #reference>
                     <el-button type="text">删除</el-button>
                   </template>
                 </el-popconfirm>
               </div>
             </div>
           </div>
          </div>

         <div class="qinfo-right">
           <div class="q-panel">
            <div class="q-head">
            <div class="q-more">
              <a href="javascript:;" class="ub-text-muted" data-dialog-request="/question/correct/12166">
                <i class="el-icon-question"></i>
                纠错
              </a>
            </div>
            <div class="q-title">
              <i class="el-icon-menu"></i>
              题目信息
            </div>
          </div>
            <div class="q-body">
            <div>
              <el-tag type="success" size="medium">{{applicationData.chapter}}</el-tag>
            </div>
            <div class="q-foot">
              <div class="tw-flex-grow">
                <div>
                  {{applicationData.correctCount}}
                </div>
                <div>
                  正确
                </div>
              </div>
              <div class="tw-flex-grow">
                <div>
                  {{ this.comments.length }}
                </div>
                <div>
                  评论
                </div>
              </div>
              <div class="tw-flex-grow" >
                <div>
                  {{ applicationData.wrongCount }}
                </div>
                <div>
                  错误
                </div>
              </div>
            </div>
          </div>
         </div>
           <div class="q-panel-2">
          <div class="q-body">
            <div class="q-foot">
              <div class="tw-flex-grow" >
                <a href="/shoucang" class="ub-text-muted" data-action="toggle">
                  <i class="el-icon-folder"></i>
                  收藏
                </a>
              </div>
              <div class="tw-flex-grow" >
                <a href="/cuoti" class="ub-text-muted" data-action="toggle">
                  <i class="el-icon-folder-delete"></i>
                  错题集
                </a>
              </div>
            </div>

          </div>
        </div>
           <div class="q-panel-3">
          <div class="q-head">
            <div class="q-more">
              <div class="q-foot">
                <div class="tw-flex-grow">
                  <el-rate
                      v-model="difficulty"
                      disabled
                      show-score
                      text-color="#ff9900"
                      >
                  </el-rate>
                  <div>
                    当前评分
                  </div>
                </div>
              </div>

            </div>
          </div>
        </div>
           <div class="margin-top">
          <div class="tw-rounded-lg tw-bg-white margin-bottom ">
            <a href="https://chuangke.aliyun.com/invite?userCode=f4udg2pm" target="_blank">
              <img style="width:100%;" class="tw-rounded-lg" src="https://mz-demo-assets.tecmz.com/data/image/2023/03/24/50491_tp3c_3665.png">
            </a>
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
  name: "ApplicationQuestion",
  components:{
    myEditor
  },
  data() {
    return {
      applicationData:{showAnswer: false,},
      selectedOption: null,
      hasAnswered: false, // 新增标志，用于控制是否显示颜色
      mydifficulty:'',
      difficulty:1,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'] ,
      html:'',
      comments:[],
      userComment:[],
      hasMethodExecuted: true,
      prevAndNext:[],

    }
  },
  created() {

  },
  mounted() {
    this.userComment.account=sessionStorage.getItem("account");
    this.userComment.username=sessionStorage.getItem("username");
    this.setQuestion();
    this.setPrevAndNext();
    this.setComment();

  },
  methods:{
    setQuestion(){
      //获取session
      let a=sessionStorage.getItem("question");
      this.applicationData=JSON.parse(a);
      this.difficulty=this.applicationData.difficulty;
    },
    setPrevAndNext(){
      axios.get("http://localhost:8087/application/twoApplication?id="+this.applicationData.id).then(res =>{
        this.prevAndNext=res.data.data;
        const div = document.createElement("div");
        if(this.prevAndNext.prevApplication){
          div.innerHTML = this.prevAndNext.prevApplication.question;
          const text = div.textContent || div.innerText;
          this.prevAndNext.prevApplication.question = "[应用题] " + text;
        }
        if(this.prevAndNext.nextApplication){
          div.innerHTML = this.prevAndNext.nextApplication.question;
          const text1 = div.textContent || div.innerText;
          this.prevAndNext.nextApplication.question = "[应用题] " + text1;
        }
           console.log(this.prevAndNext)
        if (res.data.code!=='0') {
          this.$message({
            type: "error",
            message: res.data.msg
          })
        }
      });
    },
    toggleAnswer(item) {
      item.showAnswer = !item.showAnswer; // 切换显示答案状态
    },
    delectComment(index){
      axios.delete("http://localhost:8087/comment/"+index.id).then(res => {
        if (res.data.code==='0'){
          this.$message({
            type: "success",
            message: "删除成功"
          })
          this.setComment();
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      });
    },
    commit(){
      this.userComment.commentText=this.html;
      this.userComment.questionId=this.applicationData.id;
      this.userComment.questionType="application";
      this.userComment.account=sessionStorage.getItem("account");
      this.userComment.username=sessionStorage.getItem("username");
      let userCommentCopy = {
        commentText: this.userComment.commentText,
        questionId: this.userComment.questionId,
        questionType: this.userComment.questionType,
        account: this.userComment.account,
        username: this.userComment.username
      };
// 将属性值转换为字符串，确保它们是有效的 JSON 数据
      userCommentCopy.commentText = String(this.userComment.commentText);
      userCommentCopy.questionType = String(this.userComment.questionType);
      userCommentCopy.account = String(this.userComment.account);
      userCommentCopy.username = String(this.userComment.username);
// 将处理后的对象转换为 JSON 数据
      let jsonUserComment = JSON.stringify(userCommentCopy);
      let a=JSON.parse(jsonUserComment)
// 打印转换后的 JSON 数据
      console.log(a);
      axios.post("http://localhost:8087/comment",a)
          .then(res=> {
            if(res.data.code=='0'){
              console.log('成功发送数据到后台', res.data);
              // 根据后台返回的数据进行进一步处理
              this.setComment();
            }else{
              console.log("发送失败",res.data);
            }
          })
    },
    handleEditorDataChanged({ data, id }) {
        this.html = data;
  },
    setComment(){
      axios.get("http://localhost:8087/comment/application?questionId="+this.applicationData.id)
          .then(res=> {
            console.log('成功发送数据到后台', res.data);
            // 根据后台返回的数据进行进一步处理
            this.comments=res.data.data;

          })
    },
    getQuestion(question){
      let questionJson=JSON.stringify(question)
      sessionStorage.removeItem("question")
      sessionStorage.setItem("question",questionJson)
    }

}
}
</script>

<style scoped>
@import "../assets/css/foot.css";/*底栏*/
.correct-answer {
  background-color: rgba(97, 252, 78,0.4);

}

.wrong-answer {
  background-color: rgba(224, 12, 12, 0.40);
}
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
.q-container  {
  position: relative;
  top:100px;
  v-index:2;
  padding-left:10%;
  padding-right: 10%;
}
.qinfo {
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
.qinfo-left {
  position: relative;
  width: 100%;
  min-height: .05rem;
  padding-right: calc(0.5rem / 2);
  padding-left: calc( 0.5rem/ 2);
  box-sizing: border-box;
  -webkit-box-flex: 0;
  -ms-flex: 0 0 75%;
  flex: 0 0 75%;
  max-width: 75%;
}
.q-view{
  background-color: rgba(255, 255, 255, 0.65);
  border-radius: .5rem;
  margin-bottom: .5rem;
}
.q-view .q-head {
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
.q-item:hover {
  background-color: rgba(97, 252, 78, 0.2);
}
.q-info1 {
  display: flex; /* 启用Flexbox布局 */
}
.q-before, .q-after {
  flex: 1; /* 使两个容器各自占据等量的空间 */
  background-color: rgba(255, 255, 255, 0.65);
  border-radius: .5rem;
  margin-bottom: .5rem;
  min-width: 0; /* 设置最小宽度为0，允许内容溢出 */
}
.q-before {
  margin-right: 5px; /* 在第一个元素和第二个元素之间添加5px的间隔 */
}
.q-after:hover{
  background-color: rgba(151, 248, 139,0.25);
}
.q-before:hover{
  background-color: rgba(151, 248, 139,0.25);
}

.qaf{
  /*margin-left: 10px;*/
  /*margin-top: 10px;*/
  /*margin-bottom: 10px;*/
}
.qinfo-right {
  position: relative;
  width: 100%;
  min-height: .05rem;
  padding-right: calc(0.5rem / 2);
  padding-left: calc( 0.5rem/ 2);
  box-sizing: border-box;
  -webkit-box-flex: 0;
  -ms-flex: 0 0 25%;
  flex: 0 0 25%;
  max-width: 25%;
}
.q-panel{
  background: rgba(255, 255, 255, 0.65);
  border-radius: 0.4rem;
  margin-bottom: 0.5rem;
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
.q-panel .q-body {
  margin-top: -.75rem;
  padding: .75rem;
}
.q-panel-2{
  background: rgba(255, 255, 255, 0.65);
  border-radius: 0.4rem;
  margin-bottom: 0.5rem;
  padding-bottom: 10px;
}

.q-foot {
  display: flex;
  border-top: 2px solid rgba(151, 248, 139,0.2); /* 添加上边界线 */
  padding-top: 10px; /* 在边界线和容器内部元素之间添加10像素的上内边距 */
}
.tw-flex-grow {
  display: flex; /* 启用Flexbox布局 */
  flex-direction: column; /* 子项沿垂直方向排列 */
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  flex-grow: 1;
}
.q-panel-3{
  background: rgba(255, 255, 255, 0.65);
  border-radius: 0.4rem;
  margin-bottom: 0.5rem;
  padding-bottom: 10px;
}
.text-ellipsis {
  white-space: nowrap; /* 不换行 */
  overflow: hidden; /* 隐藏超出部分 */
  text-overflow: ellipsis; /* 显示省略号 */
}
</style>