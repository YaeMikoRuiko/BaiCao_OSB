<template>
<div class="neirong">
  <section class="c-container">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="top: 100px;left: 100px;">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/tiku' }">题库</el-breadcrumb-item>
      <el-breadcrumb-item >应用题库</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="cinfo">
      <el-tabs @tab-click="handleTabClick" value="计算机操作系统概述" tab-position="left" style="height: 1000px;width:100%;background-color: rgb(255,255,255);margin-bottom: 100px;border-radius: 0.5rem " type="border-card">
        <el-tab-pane   label="计算机操作系统概述" >
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index" style="  margin-top: 3px;">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
          </el-tab-pane>
        <el-tab-pane  label="处理器管理">
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane  label="存储管理">
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane  label="设备管理">
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane  label="文件管理">
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane  label="并发程序设计">
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane  label="网络环境下的操作系统">
          <div style="max-height: 1000px;overflow: auto;">
            <div v-for="(index, item) in choices" :key="index">
              <el-card class="box-card">
                <div><i class="el-icon-menu">第{{item + 1}}题</i> <div v-html="index.question"></div></div>
                <div>
                  <el-button style="float: left; padding: 3px 0" type="text" @click="toggleAnswer(index)">
                    <div v-if="!index.showAnswer" >击查看答案</div>
                    <div v-if="index.showAnswer" v-html="index.answer"></div>
                  </el-button>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="getChoice(index)">
                    查看本题更多信息
                  </el-button>
                </div>
              </el-card>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
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
  name: "Application",
  components:{
    myEditor
  },
  data() {
    return {
      choices:{ showAnswer: false },// 初始化是否显示答案为false
    }
  },
  created() {

  },
  mounted() {
     this.getChoices('计算机操作系统概述')

  },
  methods:{
    getChoice(index){
      sessionStorage.removeItem("question");
      sessionStorage.setItem("question",JSON.stringify(index));//缓存用户信息
      this.$router.push("/application/applicationQuestion="+index.id)
    },
    toggleAnswer(item) {
      item.showAnswer = !item.showAnswer; // 切换显示答案状态
    },
    handleTabClick(tab) {
      console.log(tab.props);
      this.getChoices(tab.props.label);
    },
    getChoices(chapter){
      axios.get("http://localhost:8087/application/comment?chapter="+chapter).then(res =>{
        this.choices=res.data.data;
        console.log(this.choices)
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