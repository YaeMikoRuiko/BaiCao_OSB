<template>
<!--  <div>-->
<!--    <MyEditor style="width: 600px;height: 300px"/>-->
<!--  </div>-->
<div class="question">
  <div class="user1">
    <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">

      <!--    搜索区域-->
      <div style="margin: 10px 0">
        <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
        <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
        <el-button type="warning" plain @click="add">新增</el-button>
      </div>
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            prop="id"
            label="ID"
            width="50"
            sortable>
        </el-table-column>
        <el-table-column
            prop="chapter"
            label="章节">
        </el-table-column>
        <el-table-column
            prop="question"
            label="问题"
            width="500"
        >
        </el-table-column>
        <el-table-column
            prop="optionA"
            label="选项A">
        </el-table-column>
        <el-table-column
            prop="optionB"
            label="选项B">
        </el-table-column>
        <el-table-column
            prop="optionC"
            label="选项C">
        </el-table-column>
        <el-table-column
            prop="optionD"
            label="选项D">
        </el-table-column>
        <el-table-column
            prop="answer"
            width="50"
            label="答案">

        </el-table-column>
<!--        <el-table-column-->
<!--            prop="explanation"-->
<!--            label="解析">-->
<!--        </el-table-column>-->
        <el-table-column
            prop="correctCount"
            label="正确数"
            width="80">
        </el-table-column>
        <el-table-column
            prop="wrongCount"
            label="错误数"
            width="80">
        </el-table-column>
        <el-table-column
            prop="difficulty"
            label="难度"
            width="80">
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
            </div>
          </template>
        </el-table-column>
      </el-table>



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
          <el-form :model="form" label-width="120px">
            <el-form-item label="所属章节">
              <el-select v-model="form.chapter" placeholder="请选择所属章节">
                <el-option label="计算机操作系统概述" value="计算机操作系统概述"></el-option>
                <el-option label="处理器管理" value="处理器管理"></el-option>
                <el-option label="存储管理" value="存储管理"></el-option>
                <el-option label="设备管理" value="设备管理"></el-option>
                <el-option label="文件管理" value="文件管理"></el-option>
                <el-option label="并发程序设计" value="并发程序设计"></el-option>
                <el-option label="网络环境下的操作系统" value="网络环境下的操作系统"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="问题">
<!--              <el-input v-model="form.question"></el-input>-->
              <myEditor editorId="editor1" :initialValue="form.question" @editorDataChanged="handleEditorDataChanged" style="height: 100px"/>
            </el-form-item>
            <el-form-item label="选项A">
              <el-input v-model="form.optionA"></el-input>
            </el-form-item>
            <el-form-item label="选项B">
              <el-input v-model="form.optionB"></el-input>
            </el-form-item>
            <el-form-item label="选项C">
              <el-input v-model="form.optionC"></el-input>
            </el-form-item>
            <el-form-item label="选项D">
              <el-input v-model="form.optionD"></el-input>
            </el-form-item>
              <el-form-item label="正确答案">
                <el-select v-model="form.answer" placeholder="请选择正确选项">
                  <el-option label="选项A" value="A"></el-option>
                  <el-option label="选项B" value="B"></el-option>
                  <el-option label="选项C" value="C"></el-option>
                  <el-option label="选项D" value="D"></el-option>
                </el-select>
              </el-form-item>
            <el-form-item label="解析"  >
<!--              <el-input v-model="form.explanation"></el-input>-->

              <myEditor editorId="editor2" :initialValue="form.explanation" @editorDataChanged="handleEditorDataChanged" />
            </el-form-item>
          </el-form>
          <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
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
import myEditor from "../components/MyEditor"
import '@wangeditor/editor/dist/css/style.css' // 引入 css


export default {
  name: "QuestionChoice",
  components: {
    myEditor
  },
  data() {
    return {
      form: {},
      search:'',
      currentPage4: 1,
      pageSize: 10,
      total:0,
      dialogVisible:false,
      tableData: [],
      html1:'',
      html2:'',
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      axios.get("http://localhost:8087/choice",{
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
        axios.put("http://localhost:8087/choice",this.form).then(res =>{
          console.log(res);
          if (res.data.code==='0'){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          }else {
            this.$message({
              type: "error",
              message: res.data.msg
            })
          }
          this.load();//刷新表格数据
          this.dialogVisible = false;//关闭弹窗
        });
      }else {//新增
        this.form.correctCount=0;
        this.form.wrongCount=0;
        if(!this.form.explanation||this.form.explanation==''||this.form.explanation=='<p><br></p>')
          this.form.explanation="略";
        axios.post("http://localhost:8087/choice",this.form).then(res =>{
          console.log(res);
          if (res.data.code==='0'){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }else {
            this.$message({
              type: "error",
              message: res.data.msg
            })
          }
          this.load();//刷新表格数据
          this.dialogVisible = false;//关闭弹窗
        });
      }

    },
    handleEdit(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.dialogVisible = true;
    },
    handleSizeChange(pageSize){//改变当前页面个数
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){//改变当前页码
      this.currentPage4 = pageNum;
      this.load();
    },
    // currentPage4(){
    //
    // },
    handleDelete(id){
      console.log(id);
      axios.delete("http://localhost:8087/choice/"+id).then(res => {
        if (res.data.code==='0'){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        }else {
          this.$message({
            type: "error",
            message: res.data.msg
          })
        }
        this.load()//重新加载
      });
    },
    handleEditorDataChanged({ data, id }) {
      if (id === 'editor1') {
        this.form.question = data;
      } else if (id === 'editor2') {
        this.form.explanation = data;
      }
    },
    formatQuestion(question) {
      // 在这里编写对 question 进行处理的逻辑，例如去除空格、转换大小写等操作
      return question; // 示例：将问题转换为大写
    },
  }
}
</script>

<style scoped>
@import "../assets/css/foot.css";/*底栏*/
.question{
  display: flex;
  flex-direction: column;
  top: 0;
  left: 0;
  width: 100%;
  height: 1224px;
  background-image: url('../components/images/新闻资讯背景.jpg');
  background-size: cover;
}
.user1{
  position: relative;
  top:75px;
  left: 10%;
  width: 80%;
  height: 100%;
  z-index: 12;
}
</style>