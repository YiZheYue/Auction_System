<template>
  <el-dialog
    :title="!dataForm.examId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="考试科目" prop="examName">
      <el-input v-model="dataForm.examName" placeholder="考试科目"></el-input>
    </el-form-item>
    <el-form-item label="日期" prop="examDate">
      <el-input v-model="dataForm.examDate" placeholder="日期"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          examId: 0,
          examName: '',
          examDate: ''
        },
        dataRule: {
          examName: [
            { required: true, message: '考试科目不能为空', trigger: 'blur' }
          ],
          examDate: [
            { required: true, message: '日期不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.examId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.examId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/exams/info/${this.dataForm.examId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.examName = data.exams.examName
                this.dataForm.examDate = data.exams.examDate
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/exams/${!this.dataForm.examId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'examId': this.dataForm.examId || undefined,
                'examName': this.dataForm.examName,
                'examDate': this.dataForm.examDate
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
