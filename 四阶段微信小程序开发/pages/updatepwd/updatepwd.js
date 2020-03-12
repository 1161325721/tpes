// pages/updatepwd/updatepwd.js
const app = getApp()
import util from '../../utils/md5'    

Page({

  /**
   * 页面的初始数据
   */
  data: {
    formData: {

    },
    rules: [
      {
        name: 'oldpwd',
        rules: { required: true, message: '原始密码必填' },
      }, {
        name: 'newpwd',
        rules: [{ required: true, message: '确认密码必填' }, { minlength: 6, message: '确认密码长度不少于6位数' }],
      },{
        name: 'renewpwd',
        rules: [{ required: true, message: '再次确认密码必填' }, { minlength: 6, message: '再次确认密码长度不少于6位数' }],
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
      var old=that.data.formData.old;
      var oldpwd=that.data.formData.oldpwd;
      var md5oldpwd =  util.hexMD5(oldpwd)
      var newpwd = that.data.formData.newpwd;
      var renewpwd=that.data.formData.renewpwd;
      console.log(old!==md5oldpwd)
      if(old!=md5oldpwd){
        this.setData({
          error: '原始密码输入错误了'
        })
        return;

      } else if(newpwd!=renewpwd){
        this.setData({
          error: '新密码两次输入不一致 '
        })
        return;

      } else {
          wx.showLoading({
            title: '修改中',
            mask: true,
          });
          wx.request({
            url: 'https://junxin.mynatapp.cc/tpes/wx/updatainfo',
            data: {
              gcpass: util.hexMD5(newpwd)
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
        console.log(result.data[0].gcpass)
        this.setData({
          [`formData.old`]:result.data[0].gcpass,
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