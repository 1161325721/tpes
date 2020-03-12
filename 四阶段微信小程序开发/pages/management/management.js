// pages/management/management.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    current: "management",
    current1: '',
    list: null,
    noData: false
  },
  handleChange1({ detail }) {
    this.setData({
      current1: detail.key
    });
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/orderDetail',
      data: {
        stageid: detail.key
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'cookie': wx.getStorageSync("sessionid")
        //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
      },
      dataType: 'json',
      success: (result) => {
        if (result.data.length > 0) {
          this.setData({
            list: result.data,
            noData: false
          })
        } else {
          this.setData({
            list: null,
            noData: true
          })
        }
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
      complete: () => { wx.hideLoading() }
    });




  },
  handleChange({ detail }) {
    var key = detail.key;
    this.setData({
      current: detail.key
    });
    wx.redirectTo({
      url: '../' + key + '/' + key,
    })

  },

  //取消订单
  cancel(e) {
     let mylist=e.currentTarget.dataset.myitem;
   var that=this;
   var current1=that.data.current1;
   wx.showModal({
    title: '温馨提示',
    content: '确定取消订单吗，进行中的订单不可取消',
    showCancel: false,
    confirmText: '是',
    cancelText:"否",
    showCancel	:true,
    confirmColor: '#3CC51F',
    success (res) {
      if (res.confirm) {
        wx.showLoading({
          title: '',
          mask: true,
        });
        var reqTask = wx.request({
          url: 'https://junxin.mynatapp.cc/tpes/wx/cancelorder',
          data: {
            groupOrder:JSON.stringify(mylist) 
          },
          method: "POST",
          header: {
            'content-type': 'application/x-www-form-urlencoded',
            'cookie': wx.getStorageSync("sessionid")
            //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
          },
          dataType: 'json',
          success: (result) => {
            if(result.data=="success"){
              wx.showToast({
                title: '取消订单成功'
              })
             

              var reqTask = wx.request({
                url: 'https://junxin.mynatapp.cc/tpes/wx/orderDetail',
                data: {
                  stageid: current1
                },
                method: "POST",
                header: {
                  'content-type': 'application/x-www-form-urlencoded',
                  'cookie': wx.getStorageSync("sessionid")
                  //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
                },
                dataType: 'json',
                success: (result) => {
                  if (result.data.length > 0) {
                    that.setData({
                      list: result.data,
                      noData: false
                    })
                  } else {
                    that.setData({
                      list: null,
                      noData: true
                    })
                  }
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
                complete: () => { wx.hideLoading() }
              });
          
            }else if(result.data=="allowfalse"){
              wx.showModal({
                title: '错误信息',
                content: '该订单正在进行体检中，不可取消',
                showCancel: false,
                confirmText: '确定',
                confirmColor: '#3CC51F',
              });
            }else{
              wx.showModal({
                title: '错误信息',
                content: '服务器没有启动',
                showCancel: false,
                confirmText: '确定',
                confirmColor: '#3CC51F',
              });
            }
    
          },
          fail: () => { 
             wx.showModal({
            title: '错误信息',
            content: '服务器没有启动',
            showCancel: false,
            confirmText: '确定',
            confirmColor: '#3CC51F',
          }); },
          complete: () => {wx.hideLoading()  }
        });
      } 
    }
  });


   

  },

  //查看某个订单详情
  orderdetail(e) {
    var packid = e.currentTarget.id
    var url = e.currentTarget.dataset.url
    var goid = e.currentTarget.dataset.goid
    var gostate = e.currentTarget.dataset.gostate
    var packname = e.currentTarget.dataset.packname
    var date = e.currentTarget.dataset.date
    var goprepay = e.currentTarget.dataset.goprepay
    var golasttime = e.currentTarget.dataset.golasttime

    wx.navigateTo({
      url: '../orderdetail/orderdetail?packid=' + packid +
        '&url=' + url + '&packname=' + packname + '&goid=' + goid +
        '&gostate=' + gostate + '&date=' + date + '&goprepay=' + goprepay +
        '&golasttime=' + golasttime,
    });




  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {

    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/orderDetail',
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
        if (result.data.length > 0) {
          this.setData({
            list: result.data,
            noData: false
          })
        } else {
          this.setData({
            list: null,
            noData: true
          })
        }
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
      complete: () => { wx.hideLoading() }
    });


  },


})