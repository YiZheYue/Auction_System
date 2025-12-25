<template>
  <el-dialog
    :title="!dataForm.scoreId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="学号" prop="studentId">
      <el-input v-model="dataForm.studentId" placeholder="学号"></el-input>
    </el-form-item>
    <el-form-item label="考试编号" prop="examId">
      <el-input v-model="dataForm.examId" placeholder="考试编号"></el-input>
    </el-form-item>
    <el-form-item label="科目" prop="subject">
      <el-input v-model="dataForm.subject" placeholder="科目"></el-input>
    </el-form-item>
    <el-form-item label="分数" prop="score">
      <el-input v-model="dataForm.score" placeholder="分数"></el-input>
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
          scoreId: 0,
          studentId: '',
          examId: '',
          subject: '',
          score: ''
        },
        dataRule: {
          studentId: [
            { required: true, message: '学号不能为空', trigger: 'blur' }
          ],
          examId: [
            { required: true, message: '考试编号不能为空', trigger: 'blur' }
          ],
          subject: [
            { required: true, message: '科目不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '分数不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.scoreId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.scoreId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/scores/info/${this.dataForm.scoreId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.studentId = data.scores.studentId
                this.dataForm.examId = data.scores.examId
                this.dataForm.subject = data.scores.subject
                this.dataForm.score = data.scores.score
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
              url: this.$http.adornUrl(`/generator/scores/${!this.dataForm.scoreId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'scoreId': this.dataForm.scoreId || undefined,
                'studentId': this.dataForm.studentId,
                'examId': this.dataForm.examId,
                'subject': this.dataForm.subject,
                'score': this.dataForm.score
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
