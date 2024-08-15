<template>
  <div>
   <div class="person1">
    <div class="user1">
      <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
        <!--    功能趋于-->
        <div style="margin: 10px 0">
          <el-button type="warning" plain @click="add">新增</el-button>
        </div>
        <!--    搜索区域-->
        <div style="margin: 10px 0">
          <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
          <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
        </div>
        <el-table
            :data="tableData"
            border
            stripe
            style="width: 90%">
          <el-table-column
              prop="id"
              label="ID"
              sortable>
          </el-table-column>
          <el-table-column
              label="角色">
            <template #default="scope">
              <span v-if="scope.row.role===0">普通用户</span>
              <span v-if="scope.row.role===1">教师</span>
              <span v-if="scope.row.role===2">管理员</span>
            </template>
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
              prop="password"
              label="密码">
          </el-table-column>
          <el-table-column
              prop="email"
              label="邮箱地址">
          </el-table-column>
          <el-table-column
              prop="gender"
              label="性别">
          </el-table-column>
          <!--        <el-table-column-->
          <!--            label="角色">-->
          <!--          <template #default="scope">-->
          <!--            <span v-if="scope.row.role===1">管理员</span>-->
          <!--            <span v-if="scope.row.role===2">普通用户</span>-->
          <!--          </template>-->
          <!--        </el-table-column>-->
          <el-table-column
              prop="joinDate"
              label="注册日期">
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
              width="30%">
            <el-form :model="form" label-width="120px">
              <el-form-item label="用户名">
                <el-input v-model="form.username"></el-input>
              </el-form-item>
              <el-form-item label="角色">
                <el-radio v-model="form.role" :label="0">普通用户</el-radio>
                <el-radio v-model="form.role" :label="1">老师</el-radio>
                <el-radio v-model="form.role" :label="2">管理员</el-radio>
              </el-form-item>
              <el-form-item label="账号">
                <el-input v-model="form.account"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="form.password"></el-input>
              </el-form-item>
              <el-form-item label="邮箱地址">
                <el-input v-model="form.email"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <el-radio v-model="form.gender" label="男">男</el-radio>
                <el-radio v-model="form.gender" label="女">女</el-radio>
                <el-radio v-model="form.gender" label="未知">未知</el-radio>
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
  </div>
</template>

<script>


import request from "@/utils/request";
import axios from "axios";

export default {
  name: 'Person',
  data() {
    return {
      form: {},
      search:'',
      currentPage4: 1,
      pageSize: 10,
      total:0,
      dialogVisible:false,
      tableData: [],
      user:[],
    }
  },
  created() {
    if(sessionStorage.getItem("role")==2){
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
      axios.get("http://localhost:8087/user",{
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
        axios.put("http://localhost:8087/user",this.form).then(res =>{
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
        axios.post("http://localhost:8087/user",this.form).then(res =>{
          console.log(res);
          if (res.code==='0'){
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
      axios.delete("http://localhost:8087/user/"+id).then(res => {
        if (res.code==='0'){
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
    }
  }
}
</script>
<style>
.person1{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('../components/images/新闻资讯背景.jpg');
  background-size: cover;
}
.user1{
  position: absolute;
  top:75px;
  left: 11%;
  width: 100%;
  height: 100%;
  z-index: 12;
}
</style>