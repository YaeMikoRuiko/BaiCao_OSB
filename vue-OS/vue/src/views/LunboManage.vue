<template>
<div class="question">
  <div class="user1">
    <div style="height: 800px;background-color: white;border-radius: 0.8rem;">
      <div style="padding:30px; width: 100%;overflow: auto;max-height: 800px;">
        <el-table
            :data="tableData"
            border
            stripe
            >
          <el-table-column
              prop="imgId"
              label="ID"
              width="50"
              sortable>
          </el-table-column>
          <el-table-column
              prop="imgName"
              label="名称"
              width="150">
          </el-table-column>
          <el-table-column
              prop="img"
              label="图片"
              width="250"
          >
            <template #default="scope">
              <img  :src="('http://localhost:8088'+scope.row.imgUrl)" style="width: 50px;height: 50px"/>
            </template>
          </el-table-column>
          <el-table-column
              prop="imgUrl"
              label="图片地址"
              width="850"
          >
          </el-table-column>
          <el-table-column
              fixed="right"
              label="操作">

            <template #default="scope">
              <div style="display: flex; align-content: space-between">
                <el-button @click="handleEdit(scope.row)" type="primary">编辑</el-button>
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
              width="30%">
            <el-form :model="form" label-width="120px" >
              <el-form-item label="轮播图名称"  >
                {{this.form.imgName}}
              </el-form-item>
              <el-form-item label="当前图片"  >
                <img v-if="this.form.imgUrl" :src="'http://localhost:8088'+this.form.imgUrl" style="height: 100px;width: 100px;"/>
              </el-form-item>
              <el-form-item label="重新上传"  >
                  <el-upload  class="upload" ref="upload" action="http://localhost:8087/files/uploadLunbo" :on-success="filesUploadSuccess"  >
                    <el-button type="primary" class="upload-button">点击上传</el-button>
                  </el-upload>
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
  name: "LunboManage",
  components: {
    myEditor
  },
  data() {
    return {
      form: {},
      currentPage4: 1,
      pageSize: 12,
      total:0,
      dialogVisible:false,
      tableData: [],
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
      axios.get("http://localhost:8087/lunbo/list",{
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
    filesUploadSuccess(res){
      console.log(res);
      this.form.picture = res.data;
      this.save();

    },
    save(){
      if (this.form.imgId){//更新
        this.form.imgUrl=this.form.picture;
        axios.put("http://localhost:8087/lunbo",this.form).then(res =>{
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

          this.dialogVisible = false;//关闭弹窗
           this.load();//刷新表格数据
        });
      }
    },
    handleEdit(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.dialogVisible = true;
      // this.getuser(this.form.authorId);
    },
    handleSizeChange(pageSize){//改变当前页面个数
      this.pageSize = pageSize;

    },
    handleCurrentChange(pageNum){//改变当前页码
      this.currentPage4 = pageNum;

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
  width: 1500px;
  height: 1224px;
  z-index: 12;
}
</style>