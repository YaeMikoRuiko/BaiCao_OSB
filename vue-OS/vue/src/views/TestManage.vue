<template>
    <div class="comment-page">
      <div class="user2">
        <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px);">

          <!--    搜索区域-->
          <div style="margin: 10px 0">
            <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
            <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
            <el-button type="warning" plain @click="add">新增</el-button>
          </div>
          <div style="width: 90%;max-height: 700px;overflow: auto;">
            <el-table
                :data="tableData"
                border
                stripe
                style="width: 100%;">
              <el-table-column
                  prop="id"
                  label="ID"
                  width="80"
                  sortable>
              </el-table-column>
              <el-table-column
                  prop="testName"
                  label="试卷名称">
              </el-table-column>
              <el-table-column
                  prop="authorName"
                  label="创建者名称">
              </el-table-column>
              <el-table-column
                  prop="authorId"
                  label="创建者ID"
                  width="80">
              </el-table-column>
              <el-table-column
                  prop="questionNum"
                  label="题目数量"
                  width="80">
              </el-table-column>
              <el-table-column
                  prop="createTime"
                  label="创建时间">
              </el-table-column>
              <el-table-column
                  prop="status"
                  label="状态"
                  width="80">
                <template #default="scope">
                  <span v-if="scope.row.status===0"><el-tag type="danger">未通过</el-tag></span>
                  <span v-if="scope.row.status===1"><el-tag type="success">通过</el-tag></span>
                </template>
              </el-table-column>
              <el-table-column
                  fixed="right"
                  label="操作">

                <template #default="scope">
                  <div style="display: flex; align-content: space-between">
                    <el-button @click="handleEdit(scope.row)" type="primary">编辑</el-button>
                    <el-popconfirm
                        title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
                      <template #reference>
                        <el-button type="danger">删除</el-button>
                      </template>
                    </el-popconfirm>
                    <el-button @click="handleEdit1(scope.row)" type="success">组卷</el-button>
                    <el-button @click="handleEdit2(scope.row)" type="warning">查看</el-button>
                  </div>
                </template>
              </el-table-column>

            </el-table>
          </div>
          <div style="margin: 10px 0">
            <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage4"
                :page-sizes="[5, 10]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
            <!--弹窗-->
            <el-dialog
                title="提示"
                v-model="dialogVisible"
                width="50%">
              <el-form :model="form" label-width="300px">
                <el-form-item label="试卷名称">
                  <el-input v-model="form.testName" ></el-input>
                </el-form-item>
                <el-form-item  label="题目数量">
                  <el-input v-model="form.questionNum" ></el-input>
                </el-form-item>
                <el-form-item label="状态"  >
                  <el-select v-model="form.status" placeholder="请选择审核状态">
                    <el-option label="通过" value="1"></el-option>
                    <el-option label="未通过" value="0"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
              <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
              </template>
            </el-dialog>
<!--                  组卷-->
            <el-dialog
                title="自定义组卷"
                v-model="dialogVisible1"
                width="50%">
              <el-form :model="questions" label-width="120px" style="overflow: auto;max-height: 800px;">
                <el-form-item label="试卷名称">
                  <span>{{questions.testName}}</span>
                </el-form-item>
                <el-form-item  label="题目数量">
                  <span>{{questions.count}}</span>
                </el-form-item>
                <el-form-item label="题型"  >
                  <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                  <div style="margin: 15px 0;"></div>
                  <el-checkbox-group v-model="checkedQuestions" @change="handleCheckedQuestionsChange">
                    <el-checkbox v-for="question in questionTypes" :label="question" :key="question">{{question}}</el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item v-if="checkedQuestions.includes('选择题')" label="选择题">
                  <el-input
                      placeholder="请输入数量"
                      v-model="questions.choiceNum"
                      clearable>
                  </el-input>
                </el-form-item>
                <el-form-item v-if="checkedQuestions.includes('判断题')" label="判断题">
                  <el-input
                      placeholder="请输入数量"
                      v-model="questions.judgeNum"
                      clearable>
                  </el-input>
                </el-form-item>
                <el-form-item v-if="checkedQuestions.includes('填空题')" label="填空题">
                  <el-input
                      placeholder="请输入数量"
                      v-model="questions.fillNum"
                      clearable>
                  </el-input>
                </el-form-item>
                <el-form-item v-if="checkedQuestions.includes('简答题')" label="简答题">
                  <el-input
                      placeholder="请输入数量"
                      v-model="questions.shortsNum"
                      clearable>
                  </el-input>
                </el-form-item>
                <el-form-item v-if="checkedQuestions.includes('应用题')" label="应用题">
                  <el-input
                      placeholder="请输入数量"
                      v-model="questions.applicationNum"
                      clearable>
                  </el-input>
                </el-form-item>
                <el-form-item v-if="detectQuestionNum(form.questionNum,questions)">
                  <span style="color: red" >提示:当前设置题目数量与预设不同</span>
                </el-form-item>
                <el-form-item label="选择【选择题】" v-if="!detectQuestionNum(form.questionNum,questions)&&questions.choiceNum">
                  <span >{{questions.choiceNum}}道</span>
                  <el-table
                      :data="choice"
                      style="width: 100%"
                      max-height="250"
                      @selection-change="handleSelectionChange($event,'choice')">
                  >
                    <el-table-column
                        type="selection"
                        :selectable="row => isSelectable(row, index, 'choice')"
                        width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="question"
                        label="题目"
                        width="320">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="chapter"
                        label="所属章节"
                        width="150">
                    </el-table-column>
                  </el-table>
                </el-form-item>
                <el-form-item label="选择【判断题】" v-if="!detectQuestionNum(form.questionNum,questions)&&questions.judgeNum">
                  <span >{{questions.judgeNum}}道</span>
                  <el-table
                      :data="judge"
                      style="width: 100%"
                      max-height="250"
                      @selection-change="handleSelectionChange($event,'judge')">
                    >
                    <el-table-column
                        type="selection"
                        :selectable="row => isSelectable(row, index, 'judge')"
                        width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="question"
                        label="题目"
                        width="320">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="chapter"
                        label="所属章节"
                        width="150">
                    </el-table-column>
                  </el-table>
                </el-form-item>
                <el-form-item label="选择【填空题】" v-if="!detectQuestionNum(form.questionNum,questions)&&questions.fillNum">
                  <span >{{questions.fillNum}}道</span>
                  <el-table
                      :data="fill"
                      style="width: 100%"
                      max-height="250"
                      @selection-change="handleSelectionChange($event,'fill')">
                    >
                    <el-table-column
                        type="selection"
                        :selectable="row => isSelectable(row, index, 'fill')"
                        width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="question"
                        label="题目"
                        width="320">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="chapter"
                        label="所属章节"
                        width="150">
                    </el-table-column>
                  </el-table>
                </el-form-item>
                <el-form-item label="选择【简答题】" v-if="!detectQuestionNum(form.questionNum,questions)&&questions.shortsNum">
                  <span >{{questions.shortsNum}}道</span>
                  <el-table
                      :data="shorts"
                      style="width: 100%"
                      max-height="250"
                      @selection-change="handleSelectionChange($event,'shorts')">
                    >
                    <el-table-column
                        type="selection"
                        :selectable="row => isSelectable(row, index, 'shorts')"
                        width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="question"
                        label="题目"
                        width="320">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="chapter"
                        label="所属章节"
                        width="150">
                    </el-table-column>
                  </el-table>
                </el-form-item>
                <el-form-item label="选择【应用题】" v-if="!detectQuestionNum(form.questionNum,questions)&&questions.applicationNum">
                  <span >{{questions.applicationNum}}道</span>
                  <el-table
                      :data="application"
                      style="width: 100%"
                      max-height="250"
                      @selection-change="handleSelectionChange($event,'application')">
                    >
                    <el-table-column
                        type="selection"
                        :selectable="row => isSelectable(row, index, 'application')"
                        width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="question"
                        label="题目"
                        width="320">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="chapter"
                        label="所属章节"
                        width="150">
                    </el-table-column>
                  </el-table>
                </el-form-item>
              </el-form>
              <template #footer>
          <span class="dialog-footer">
            <el-button @click="cancelQuestion">取 消</el-button>
            <el-button type="primary" @click="saveQuestions(questions.testId)">确 定</el-button>
          </span>
              </template>
            </el-dialog>
            <!--弹窗-->
            <el-dialog
                title="提示"
                v-model="dialogVisible2"
                width="80%">
              <el-form :model="form" label-width="300px">
                <el-form-item label="试卷名称">
                  <span>{{form.testName}}</span>
                </el-form-item>
                <el-form-item  label="题目数量">
                  <span>{{form.questionNum}}</span>
                </el-form-item>
                <el-form-item  label="题目">
                  <div style="overflow: auto;max-height: 500px;">
                    <ul>
                      <div v-for="(question,item) in testQuestionList.choice" :key="question">
                        <i class="el-icon-menu">【选择题】</i>第{{item + 1}}题<div v-html="question.question"></div>
                        <div>A.{{question.optionA}}</div>
                        <div>B.{{question.optionB}}</div>
                        <div>C.{{question.optionC}}</div>
                        <div>D.{{question.optionD}}</div>
                        <hr/>
                      </div>
                    </ul>
                    <ul>
                      <div v-for="(question,item) in testQuestionList.judge" :key="question">
                        <i class="el-icon-menu">【判断题】</i>第{{item + 1}}题<div v-html="question.question"></div>
                        <div>A.正确</div>
                        <div>B.错误</div>
                        <hr/>
                      </div>
                    </ul>
                    <ul>
                      <div v-for="(question,item) in testQuestionList.fill" :key="question">
                        <i class="el-icon-menu">【填空题】</i>第{{item + 1}}题<div v-html="question.question"></div>
                        <hr/>
                      </div>
                    </ul>
                    <ul>
                      <div v-for="(question,item) in testQuestionList.short" :key="question">
                        <i class="el-icon-menu">【简答题】</i>第{{item + 1}}题<div v-html="question.question"></div>
                        <hr/>
                      </div>
                    </ul>
                    <ul>
                      <div v-for="(question,item) in testQuestionList.application" :key="question">
                        <i class="el-icon-menu">【应用题】</i>第{{item + 1}}题<div v-html="question.question"></div>
                        <hr/>
                      </div>
                    </ul>
                  </div>
                </el-form-item>
              </el-form>
              <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible2 = false">取 消</el-button>
          </span>
              </template>
            </el-dialog>
          </div>
        </div>

      </div>
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
const questionOptions = ['选择题', '填空题', '判断题', '简答题', '应用题'];
export default {
  name: 'Comment',
  data() {
    return {
      form: {},
      search:'',
      currentPage4: 1,
      pageSize: 10,
      total:0,
      dialogVisible:false,
      dialogVisible1:false,
      dialogVisible2:false,
      tableData: [],
      questions: {
        choiceNum:0,
        fillNum:0,
        judgeNum:0,
        shortsNum:0,
        applicationNum:0,
      },
      user:[],
      checkAll: false,
      checkedQuestions: [],
      questionTypes: questionOptions,
      isIndeterminate: true,
      questionsAlert: false,
      choice:{},
      fill:{},
      judge:{},
      shorts:{},
      application:{},
      multipleSelection:{
        choice:{},
        fill:{},
        shorts:{},
        judge:{},
        application:{},
      },
      testQuestionList:{},
    }
  },
  created() {
    this.load();
    this.getQuestionList();
  },
  methods:{
    load(){
      axios.get("http://localhost:8087/testPaper/list",{
        params: {
          pageNum: this.currentPage4,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res =>{
        console.log(res);
        this.tableData = res.data.data.records;
        this.total = res.data.data.total;
      })
    },
    add(){
      this.dialogVisible = true;
      this.form = {};
    },
    save(){
      if (this.form.id){//更新
        axios.put("http://localhost:8087/testPaper",this.form).then(res =>{
          console.log(res);
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
          this.load();//刷新表格数据
          this.dialogVisible = false;//关闭弹窗
        });
      }else {//新增
        this.user.username=sessionStorage.getItem("username")
        this.user.id=sessionStorage.getItem("id")
        this.form.authorName=this.user.username
        this.form.authorId=this.user.id
        axios.post("http://localhost:8087/testPaper",this.form).then(res =>{
          console.log(res);
          if (res.data.code==='0'){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load();//刷新表格数据
          this.dialogVisible = false;//关闭弹窗
        });
      }

    },
    saveQuestions(id){
      this.multipleSelection.testId=id;
      const selectionLength1 = this.multipleSelection.choice ? this.multipleSelection.choice.length : null;
      const selectionLength2 = this.multipleSelection.fill ? this.multipleSelection.fill.length : null;
      const selectionLength3 = this.multipleSelection.judge ? this.multipleSelection.judge.length : null;
      const selectionLength4 = this.multipleSelection.shorts ? this.multipleSelection.shorts.length : null;
      const selectionLength5 = this.multipleSelection.application ? this.multipleSelection.application.length : null;
      if(!selectionLength1){
        this.multipleSelection.choice=null;
      }
      if(!selectionLength2){
        this.multipleSelection.fill=null;
      }
      if(!selectionLength3){
        this.multipleSelection.judge=null;
      }
      if(!selectionLength4){
        this.multipleSelection.shorts=null;
      }
      if(!selectionLength5){
        this.multipleSelection.application=null;
      }
      axios.put("http://localhost:8087/testPaper/questions", this.multipleSelection).then(res =>{
        console.log(res);
        if (res.data.code==='0'){
          this.$message({
            type: "success",
            message: "自定义成功"
          }
          )
          location.reload();//直接刷新
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.dialogVisible1 = false;//关闭弹窗
      });
    },
    handleEdit(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.dialogVisible = true;
    },
    handleEdit1(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.questions.testName=this.form.testName;
      this.questions.count=this.form.questionNum;
      this.questions.testId=this.form.id;
      this.dialogVisible1 = true;
    },
    handleEdit2(row){
      this.form = JSON.parse((JSON.stringify(row)));
      axios.get("http://localhost:8087/testPaper/questionList?testId="+this.form.id).then(res =>{
        console.log(res.data.data);
        this.testQuestionList = res.data.data;
      })
      this.dialogVisible2= true;
    },
    handleSizeChange(pageSize){//改变当前页面个数
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){//改变当前页码
      this.currentPage4 = pageNum;
      this.load();
    },
    handleDelete(id){
      console.log(id);
      axios.delete("http://localhost:8087/comment/"+id).then(res => {
        if (res.data.code==='0'){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()//重新加载
      });
    },
    getId(row) {
      const id = row.id;
      console.log('当前行的id为：', id);
      // 在这里可以对id进行进一步操作
      this.form.questionId=id;
    },
    handleCheckAllChange(val) {
      this.checkedQuestions = val ? questionOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedQuestionsChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.questionTypes.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.questionTypes.length;
    },
    detectQuestionNum(count,questions){
      const choiceNum = parseInt(questions.choiceNum);
      const fillNum = parseInt(questions.fillNum);
      const judgeNum = parseInt(questions.judgeNum);
      const shortsNum = parseInt(questions.shortsNum);
      const applicationNum = parseInt(questions.applicationNum);

      // 计算总和
      const all = choiceNum + fillNum + judgeNum + shortsNum + applicationNum;

      // 检查总和是否等于指定的 count
      return count !== all;
    },
    getQuestionList() {
      axios.get("http://localhost:8087/choice/list").then(res =>{
        this.choice = res.data.data;
        // 根据后台返回的数据进行进一步处理
        this.choice = this.choice.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question=text;
          return item;})
      })
      axios.get("http://localhost:8087/judge/list").then(res =>{
        this.judge = res.data.data;
        // 根据后台返回的数据进行进一步处理
        this.judge = this.judge.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question=text;
          return item;})
      })
      axios.get("http://localhost:8087/fill/list").then(res =>{
        this.fill = res.data.data;
        // 根据后台返回的数据进行进一步处理
        this.fill = this.fill.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question=text;
          return item;})
      })
      axios.get("http://localhost:8087/short/list").then(res =>{
        this.shorts = res.data.data;
        // 根据后台返回的数据进行进一步处理
        this.shorts = this.shorts.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question=text;
          return item;})
      })
      axios.get("http://localhost:8087/application/list").then(res =>{
        this.application = res.data.data;
        // 根据后台返回的数据进行进一步处理
        this.application = this.application.map(item => {
          const div = document.createElement("div");//处理标签
          div.innerHTML = item.question;
          const text = div.textContent || div.innerText;
          item.question=text;
          return item;})
      })
    },
    handleSelectionChange(val,type) {
      if(this.multipleSelection[type].length>=this.questions[type+'Num']){
        this.$message({
          type: "error",
          message: "已超出预设数量"
        })
        return
      }
        this.multipleSelection[type] = val;
        console.log(this.multipleSelection.choice);
    },
    isSelectable(row, index,type) {
      // 当选择的数量超过限制时，禁用选择框
      const selectionLength = this.multipleSelection[type] ? this.multipleSelection[type].length : 0;
      if (!selectionLength) {
        return true; // 当选择项为空时，选择框可点击
      }
      return selectionLength < this.questions[type+'Num'];
    },
    cancelQuestion(){
      this.dialogVisible1=false;
      location.reload();//直接刷新
    }
  }

}
</script>
<style>
@import "../assets/css/foot.css";/*底栏*/
.comment-page{
  display: flex;
  flex-direction: column;
  top: 0;
  left: 0;
  width: 100%;
  height: 1224px;
  background-image: url('../components/images/新闻资讯背景.jpg');
  background-size: cover;
}
.user2{
  position: relative;
  top:75px;
  left: 10%;
  width: 80%;
  height: 100%;
  z-index: 12;
}
</style>