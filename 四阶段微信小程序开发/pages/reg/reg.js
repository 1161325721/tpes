// pages/reg/reg.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    formData: {

    },
    rules: [
      {
        name: 'myget',
        rules: [{ required: true, message: '单位名字必填' }, { minlength: 2, message: '单位名字长度太短，请输入全名' }]
      },
      {
        name: 'account',
        rules: { required: true, message: '账号必填' },
      }, {
        name: 'pwd',
        rules: [{ required: true, message: '密码必填' }, { minlength: 6, message: '密码长度不少于6位数' }],
      }, {
        name: 'repwd',
        rules: [{ required: true, message: '确认密码必填' }, { minlength: 6, message: '确认密码长度不少于6位数' }],
      }, {
        name: 'phone',
        rules: { required: true, message: '电话号码必填' },
      },{
        
        name: 'email',
        rules: [{ required: true, message: '电子邮箱必填' }, { email:true, message: '电子邮箱格式错误' }],
 
      }]
  },
  formInputChange(e) {
    const { field } = e.currentTarget.dataset
    this.setData({
      [`formData.${field}`]: e.detail.value
    })
  },

  submitForm() {
    var that = this;
    var account = that.data.formData.account;
    var pwd = that.data.formData.pwd;
    var repwd = that.data.formData.repwd;
    var gcname = that.data.formData.myget;
    var phone = that.data.formData.phone;
    var gcemail = that.data.formData.email   
    var pattern = /[0-9-()（）]{7,18}/;
    var pattern1 = /^[\u4e00-\u9fa5_a-zA-Z]+$/;
    var numpattern = /^[1-9]\d*$/;
    this.selectComponent('#form').validate((valid, errors) => {
      if (!valid) {
        const firstError = Object.keys(errors)
        if (firstError.length) {
          this.setData({
            error: errors[firstError[0]].message
          })

        }
      } else if (!pattern1.test(gcname)) {
        this.setData({
          error: '单位名字格式不正确，请输入中英文'
        })
      }
      else if (!numpattern.test(account)) {
        this.setData({
          error: '账号只允许输入数字，不能有其他类型'
        })
      } else if (!pattern.test(phone)) {
        this.setData({
          error: '电话格式不正确'
        })
      } else {
        if (pwd == repwd) {
          wx.showLoading({
            title: '正在注册中',
            mask: true,
          });

          wx.request({
            url: 'https://junxin.mynatapp.cc/tpes/wx/reg',
            data: {
              gcname: gcname,
              gcaccount: account,
              gcpass: pwd,
              gcphone: phone,
              gcemail:gcemail
            },
            method: "POST",
            header: { 'content-type': 'application/x-www-form-urlencoded' },
            dataType: 'json',
            success: (result) => {
              if (result.data == "success") {
                wx.showToast({
                  title: '注册认证通过'
                })
                wx.navigateBack({
                  delta: 1
                });

              } else if (result.data == "myfalse") {
                wx.showModal({
                  title: '错误信息',
                  content: '注册失败，账号已经被注册了',
                  showCancel: false,
                  confirmText: '确定',
                  confirmColor: '#3CC51F',
                });
              }
              wx.hideLoading()
            },
            fail: () => {
              wx.showModal({
                title: '错误信息',
                content: '服务器没有启动',
                showCancel: false,
                confirmText: '确定',
                confirmColor: '#3CC51F',
              });
              wx.hideLoading()
            },
          });
        } else {
          wx.showModal({
            title: '错误信息',
            content: '密码输入不一致，请确认重输',
            showCancel: false,
            confirmText: '确定',
            confirmColor: '#3CC51F',

          });
        }









      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})