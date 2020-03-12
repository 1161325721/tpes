// pages/report/report.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    formData: {

    },
    rules: [
      {
        name: 'myname',
        rules: { required: true, message: '姓名必填' },
      }, {
        name: 'idcard',
        rules: { required: true, message: '身份证必填' },
      },]
  },
  formInputChange(e) {
    const { field } = e.currentTarget.dataset
    this.setData({
      [`formData.${field}`]: e.detail.value
    })
  },
  submitForm() {

    this.selectComponent('#form').validate((valid, errors) => {
      if (!valid) {
        const firstError = Object.keys(errors)
        if (firstError.length) {
          this.setData({
            error: errors[firstError[0]].message
          })
          return;
        }
      }
      var that = this;
     
      var idcard=that.data.formData.idcard;
      var myname=that.data.formData.myname;
      wx.navigateTo({
        url: '../reportlist/reportlist?idcard='+idcard+'&myname='+myname,
        success: (result) => {
          
        },
        fail: () => {},
        complete: () => {}
      });
        



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