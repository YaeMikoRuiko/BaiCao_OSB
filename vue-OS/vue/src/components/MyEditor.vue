<template>
  <div style="border: 1px solid #ccc;">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :defaultConfig="toolbarConfig"
        :mode="mode"
    />
    <Editor
        style="height: 100px; overflow-y: hidden;"
        v-model="valueHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
    />
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { onBeforeUnmount, ref, shallowRef, onMounted, watch } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

export default {
  name:"MyEditor",
  components: { Editor, Toolbar },
  props: {
    editorId: {
      type: [String, Number],
      default: ''
    },
    initialValue: {
      type: String,
      default: '',
    },
  },
  watch: {
    valueHtml: {
      handler(newValue) {
        // 在 emit 事件时，加入 editorId
        this.$emit('editorDataChanged', { data: newValue, id: this.editorId });
      },
      deep: true
    }
  },
  setup(props) {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 定义加载数据的函数
    const loadData = () => {
      // 假设这里是异步获取数据的逻辑
      valueHtml.value = props.initialValue;
    };

    // 组件挂载时加载数据
    onMounted(loadData);

    // 监听 initialValue 的变化
    watch(() => props.initialValue, (newVal) => {
      loadData();
    });
    // 内容 HTML
    // const valueHtml = ref('<p>请输入内容</p>')
    const valueHtml = ref(props.initialValue)
    // 模拟 ajax 异步获取内容
    onMounted(() => {
      setTimeout(() => {
        valueHtml.value = props.initialValue
      }, 1)
    })

    const toolbarConfig = {
      toolbarKeys: [
        // 菜单 key
        // 'headerSelect',
        // 'bold', // 加粗
        // 'italic', // 斜体
        // 'through', // 删除线
        // 'underline', // 下划线
        // 'bulletedList', // 无序列表
        // 'numberedList', // 有序列表
        'color', // 文字颜色
        // 'insertLink', // 插入链接
        // 'fontSize', // 字体大小
        // 'lineHeight', // 行高
        'uploadImage', // 上传图片
        // 'emotion',//表情
        // 'delIndent', // 缩进
        // 'indent', // 增进
        // 'deleteImage',//删除图片
        // 'divider', // 分割线
        // 'insertTable', // 插入表格
        // 'justifyCenter', // 居中对齐
        // 'justifyJustify', // 两端对齐
        // 'justifyLeft', // 左对齐
        // 'justifyRight', // 右对齐
        'undo', // 撤销
        'redo', // 重做
        // 'clearStyle', // 清除格式
        // 'fullScreen' // 全屏
      ]
      }

    const editorConfig = ref({
      placeholder: '请输入内容...',
      MENU_CONF: {
        uploadImage: {
          server: 'http://localhost:8087/files/upload', // 图片上传的服务器端地址
          fieldName: "file",
          maxFileSize: 10 * 1024 * 1024,
          customInsert(res, insertFn) {
            const formData = new FormData();
            formData.append("file", res); // "file" 是后端期望的字段名
            console.log(res)
            if(res.code != 0 ){
              alert("上传文件失败，"+res.message)
              return
            }
            // insertFn(require("../components/images/god.jpeg"))//上传图片被保存的地址"http://zhangjiajia.love/img/god.945acf4e.jpeg"
              insertFn("http://localhost:8088"+res.data);

          },
          onSuccess(file, res) {
            if(res.code == 0 ){
              console.log(`${file.name} 上传成功`)
              return
            }else {
              alert(`${file.name} 上传出了点异常`)
              return
            }
          },
          // 单个文件上传失败
          onFailed(file, res) {
            console.log(res)
            alert(`${file.name} 上传失败`)
          },
          // 上传错误，或者触发 timeout 超时
          onError(file, err, res) {
            console.log(err, res)
            alert(`${file.name} 上传出错`)
          },

        },
      },


    });

    // 处理图片大小的函数
    function handleImageSize(imgUrl, callback) {
      alert(66)
      const img = new Image();
      img.onload = function() {
        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');
        canvas.width = 100; // 设置图片宽度为200px
        canvas.height = 150 * (img.height / img.width); // 根据比例调整高度

        ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
        const processedImgUrl = canvas.toDataURL('image/jpeg');
        callback(processedImgUrl);
      };
      img.src = imgUrl;
    }


    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }

    return {
      editorRef,
      valueHtml,
      mode: 'simple', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated
    };
  }
}
</script>