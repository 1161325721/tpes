// pages/reportinfo/reportinfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var crid = options.crid;
    var gcid = options.gcid;
    var proname = options.proname;
    console.log(crid+"crid")
    console.log(proname+"proname")
    console.log(gcid+"gcid")


    this.setData({
      proname:proname
    })
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/reportInfo',
      data: {
        gcid:gcid,
        crid:crid
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      dataType: 'json',
      success: (result) => {
         this.setData({
           list:result.data
         })


      },
      fail: () => { },
      complete: () => { wx.hideLoading() }
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