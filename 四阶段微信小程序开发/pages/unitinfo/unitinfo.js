// pages/unitinfo/unitinfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    formData: {

    },
    rules: [
      {
        name: 'phone',
        rules: { required: true, message: '电话号码必填' },
      }, {
        name: 'email',
        rules: [{ required: true, message: '电子邮箱必填' }, { email: true, message: '电子邮箱格式错误' }],

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
    var phone = that.data.formData.phone;
    var gcemail = that.data.formData.email   

    var pattern = /[0-9-()（）]{7,18}/;
    this.selectComponent('#form').validate((valid, errors) => {
      if (!valid) {
        const firstError = Object.keys(errors)
        if (firstError.length) {
          this.setData({
            error: errors[firstError[0]].message
          })

        }
      }  else if (!pattern.test(phone)) {
        this.setData({
          error: '电话格式不正确'
        })
      } else {
          wx.showLoading({
            title: '保存中',
            mask: true,
          });
          wx.request({
            url: 'https://junxin.mynatapp.cc/tpes/wx/updatainfo',
            data: {
              gcphone: phone,
              gcemail:gcemail
            },
            method: "POST",
            header: {
              'content-type': 'application/x-www-form-urlencoded',
              'cookie': wx.getStorageSync("sessionid")
              //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
            },
                        dataType: 'json',
            success: (result) => {
              if (result.data == "success") {
                wx.showToast({
                  title: '保存完毕'
                })
                wx.navigateBack({
                  delta: 1
                });

              } else if (result.data == "myfalse") {
                wx.showModal({
                  title: '错误信息',
                  content: '修改失败，',
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
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/myaccount',
      data: {

      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'cookie': wx.getStorageSync("sessionid")
        //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
      },
      dataType: 'json',
      success: (result) => {
        console.log(result.data[0].gcemail)
        this.setData({
          groupClient: result.data,
          [`formData.email`]:result.data[0].gcemail,
          [`formData.phone`]:result.data[0].gcphone

        })


      },
      fail: () => {
        wx.showModal({
          title: '错误信息',
          content: '服务器没有启动',
          showCancel: false,
          confirmText: '确定',
          confirmColor: '#3CC51F',
        });
      },

      complete: () => {
        wx.hideLoading()
      }

    });


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