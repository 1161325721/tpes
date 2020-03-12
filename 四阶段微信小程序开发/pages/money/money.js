const app = getApp()
import util from '../../utils/md5'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    visible1: false,
    actions2: [
      {
        name: '确认充值',
        color: '#ed3f14',

      }
    ],
    value2: "",
    value1: "",
  },
  handleChange1({ detail }) {
    console.log(detail)
    this.setData({
      value2: detail.detail.value
    })
  },
  handleChange2({ detail }) {
    console.log(detail)
    this.setData({
      value1: detail.detail.value
    })
  },
  handleClick() {
    this.setData({
      visible2: true
    });
  },
  handleCancel2() {
    this.setData({
      visible2: false
    });
  },
  handleClickItem2() {
    const action = [...this.data.actions2];
    action[0].loading = true;
    this.setData({
      actions2: action
    });
    var that = this;
    var inputpwd = that.data.value1;
    var pwd = that.data.formData.pwd;
    var gcbalance = that.data.formData.gcbalance;
    var mymoney = that.data.value2;
    var MD5pwd = util.hexMD5(inputpwd);
    console.log(pwd);
    console.log(MD5pwd);
    if (MD5pwd != pwd) {
      wx.showModal({
        title: '错误信息',
        content: '账户密码输入错误',
        showCancel: false,
        confirmText: '确定',
        confirmColor: '#3CC51F',
      });
      action[0].loading = false;
      that.setData({
        visible2: false,
        actions2: action,
      });

    } else {
      wx.request({
        url: 'https://junxin.mynatapp.cc/tpes/wx/updatainfo',
        data: {
          gcbalance:parseFloat(mymoney) +parseFloat(gcbalance)
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
              title: '修改成功'
            })
            action[0].loading = false;
            that.setData({
              visible2: false,
              actions2: action,
            });

          } else if (result.data == "myfalse") {
            wx.showModal({
              title: '错误信息',
              content: '修改失败了',
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
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '',
      mask: true,
    });
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
        console.log(result.data[0].gcpass)
        this.setData({
          groupClient: result.data,
          [`formData.pwd`]: result.data[0].gcpass,
          [`formData.gcbalance`]: result.data[0].gcbalance,


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