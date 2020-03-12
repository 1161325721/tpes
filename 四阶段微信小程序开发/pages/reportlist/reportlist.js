// pages/reportlist/reportlist.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: null,
    current: 1
  },
  onChange(e) {
    var that = this;
    var idcard = that.data.idcard;
    var myname = that.data.myname
    this.setData({
      current: e.detail.current,
    })
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/report',
      data: {
        idcard: idcard,
        myname: myname,
        page: e.detail.current,
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      dataType: 'json',
      success: (result) => {
        console.log(result.data[0].gcpass)
        this.setData({
          list: result.data,
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var idcard = options.idcard;
    var myname = options.myname;
    this.setData({
      idcard: idcard,
      myname: myname
    })
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/report',
      data: {
        idcard: idcard,
        myname: myname
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      dataType: 'json',
      success: (result) => {
        var list = result.data;
        console.log(list[0].allpage);
        if (list[0].allpage == 0) {
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


        } else {
          this.setData({
            list: list

          })
        }

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