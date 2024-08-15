<template>
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
            prop="articleId"
            label="ID"
            width="50"
            sortable>
        </el-table-column>
        <el-table-column
            prop="title"
            label="标题"
            width="750">
        </el-table-column>
        <el-table-column
            prop="type"
            label="类型"
            width="50"
        >
        </el-table-column>
        <el-table-column
            prop="authorId"
            width="100"
            label="发布者ID">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="发布时间"
            width="180">
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
            <el-form-item label="类型">
              <el-select v-model="form.type" placeholder="请选择文章类型">
                <el-option label="新闻" value="新闻"></el-option>
                <el-option label="公告" value="公告"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="发布者"  >
            {{this.form.username}}
            </el-form-item>
            <el-form-item label="标题"  >
              <el-input v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="正文">
              <myEditor editorId="editor1" :initialValue="form.content" @editorDataChanged="handleEditorDataChanged" style="height: 300px"/>
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
  name: "QuestionShort",
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
      html3:'',
      user:{},
    }
  },
  created() {
    this.user.username=sessionStorage.getItem("username");
    this.user.id=sessionStorage.getItem("id");
    this.load();
  },
  methods:{
    load(){
      axios.get("http://localhost:8087/article/list",{
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
      this.form.authorId=this.user.id;
      this.form.username=this.user.username;
    },
    save(){
      if (this.form.articleId){//更新
        axios.put("http://localhost:8087/article",this.form).then(res =>{
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
        this.form.difficulty=3;
        axios.post("http://localhost:8087/article",this.form).then(res =>{
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
      this.getuser(this.form.authorId);
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
      axios.delete("http://localhost:8087/article/"+id).then(res => {
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
        this.form.content = data;
      }

    },
    formatQuestion(question) {
      // 在这里编写对 question 进行处理的逻辑，例如去除空格、转换大小写等操作
      return question; // 示例：将问题转换为大写
    },
    getuser(id){
      axios.get("http://localhost:8087/user/"+id).then(res =>{
             let user=res.data.data;
             console.log(user)
            if (res.data.code!=='0') {
              this.$message({
                type: "error",
                message: res.data.msg
              })
            }
            console.log('用户名为'+user.username)
            this.form.username=user.username;
            return 1;
          }
      );

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