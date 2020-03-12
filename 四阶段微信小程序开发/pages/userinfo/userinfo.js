// pages/userinfo/userinfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    formData: {

    },
    list: null,
    sexy: [ "女","男"],
    sexyIndex: 0,
    rules: [{
      name: 'phone',
      rules: [{ required: true, message: '手机号码必填' }, { mobile: true, message: '手机号码格式不对' }],
    },]
  },


  bindSexyChange: function (e) {
    console.log('picker country 发生选择改变，携带值为', e.detail.value);
    this.setData({
      sexyIndex: e.detail.value
    })
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
    var sexy = that.data.sexyIndex;
    var id = that.data.id;

    this.selectComponent('#form').validate((valid, errors) => {
      if (!valid) {
        const firstError = Object.keys(errors)
        if (firstError.length) {
          this.setData({
            error: errors[firstError[0]].message
          })

        }
      } else {
        wx.showLoading({
          title: '保存中',
          mask: true,
        });
        wx.request({
          url: 'https://junxin.mynatapp.cc/tpes/wx/updatapatientinfo',
          data: {
            phone: phone,
            sexy: sexy,
            id: id
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
                content: '注册失败',
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
    var idcard = options.idcard;
    var myname = options.myname;
    var unit = options.unit;

    this.setData({
      idcard: idcard,
      myname: myname,
      unit: unit
    })
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/patientInfo',
      data: {
        idcard: idcard,
        myname: myname,
        unit: unit
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      dataType: 'json',
      success: (result) => {
        var list = result.data;
        var sexyint = list[0].pgender;
        var id = list[0].pid;
        if(list.length==0){
          wx.showModal({
            title: '温馨提示',
            content: '查无相关信息',
            showCancel: false,
            confirmText: '确定',
            confirmColor: '#3CC51F',
            success (res) {
              if (res.confirm) {
                wx.navigateBack({
                  delta: 1
                });
              } else if (res.cancel) {
                console.log('用户点击取消')
              }
            }
          });
        }
        this.setData({
          [`formData.phone`]:result.data[0].pphone,
          list: list,
          sexyIndex: sexyint,
          id: id
        })
      },
      fail: () => { },
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