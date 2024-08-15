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
                  sortable>
              </el-table-column>
              <el-table-column
                  prop="username"
                  label="用户名">
              </el-table-column>
              <el-table-column
                  prop="account"
                  label="账号">
              </el-table-column>
              <el-table-column
                  prop="questionId"
                  label="题号">
              </el-table-column>
              <el-table-column
                  prop="questionType"
                  label="题目类型">
                <template v-slot="{ row }">
                  <span v-if="row.questionType === 'choice'">选择题</span>
                  <span v-if="row.questionType === 'judge'">判断题</span>
                  <span v-if="row.questionType === 'fill'">填空题</span>
                  <span v-if="row.questionType === 'short'">简答题</span>
                  <span v-if="row.questionType === 'application'">应用题</span>
                </template>
              </el-table-column>
              <el-table-column
                  prop="commentText"
                  label="评论内容">
              </el-table-column>
              <el-table-column
                  prop="createTime"
                  label="创建时间">
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
                <el-form-item label="题目类型">
                  <el-select v-model="form.questionType" placeholder="请选择题目类型" >
                    <el-option label="选择题" value="choice"></el-option>
                    <el-option label="判断题" value="judge"></el-option>
                    <el-option label="填空题" value="fill"></el-option>
                    <el-option label="简答题" value="short"></el-option>
                    <el-option label="应用题" value="application"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-if="form.questionType"  label="题目所属章节">
                  <el-select v-model="form.chapter" placeholder="请选择题目所属章节" @change="sendDataToBackend(form)">
                    <el-option label="计算机操作系统概述" value="计算机操作系统概述"></el-option>
                    <el-option label="处理器管理" value="处理器管理"></el-option>
                    <el-option label="存储管理" value="存储管理"></el-option>
                    <el-option label="设备管理" value="设备管理"></el-option>
                    <el-option label="文件管理" value="文件管理"></el-option>
                    <el-option label="并发程序设计" value="并发程序设计"></el-option>
                    <el-option label="网络环境下的操作系统" value="网络环境下的操作系统"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="评论题目" v-if="form.chapter">
                    <el-table
                        :data="form.questionData"
                        style="width: 80%"
                        max-height="250">
                      <el-table-column
                          fixed
                          prop="question"
                          label="题目"
                          width="320">
                      </el-table-column>
                      <el-table-column
                          prop="id"
                          label="题号"
                          width="60">
                      </el-table-column>
                      <el-table-column
                          fixed="right"
                          label="操作"
                          width="50">
                        <template #default="scope">
                          <el-button
                              @click="getId(scope.row)"
                              type="text"
                              size="small">
                            选择
                          </el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                </el-form-item>
                <el-form-item label="已选择题目ID" v-if="form.chapter">
                  {{this.form.questionId}}
                </el-form-item>
                <el-form-item label="评论内容">
                  <el-input
                      type="textarea"
                      autosize
                      placeholder="请输入内容"
                      v-model="form.commentText">
                  </el-input>
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
      tableData: [],
      questionData:[],
      user:[],
    }
  },
  created() {
    this.load();

  },
  methods:{
    load(){
      axios.get("http://localhost:8087/comment/list",{
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
        axios.put("http://localhost:8087/comment",this.form).then(res =>{
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
        this.user.account=sessionStorage.getItem("account")
        this.form.username=this.user.username
        this.form.account=this.user.account
        axios.post("http://localhost:8087/comment",this.form).then(res =>{
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
    sendDataToBackend(form) {
      // 构建需要发送的数据对象
      const requestData = {
        questionType: form.questionType,
        chapter: form.chapter
      };
      // 发送异步请求到后台
      // 例如，使用 Axios 发送 POST 请求
      axios.get("http://localhost:8087/"+requestData.questionType+"/comment?chapter="+requestData.chapter)
          .then(response => {
            console.log('成功发送数据到后台', response.data);
            // 根据后台返回的数据进行进一步处理
            form.questionData=response.data.data;

                form.questionData = form.questionData.map(item => {
                  const div = document.createElement("div");//处理标签
                  div.innerHTML = item.question;
                  const text = div.textContent || div.innerText;
                  item.question=text;
                  return item;})

          })
          .catch(error => {
            console.error('发送数据到后台出错', error);
          });
    },
    getId(row) {
      const id = row.id;
      console.log('当前行的id为：', id);
      // 在这里可以对id进行进一步操作
      this.form.questionId=id;
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