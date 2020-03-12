// pages/combo/combo.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    list: null,
    noData: false,
    search: {
      searchValue: '',
      showClearBtn: false
    },
    searchResult: [],
    current: 'combo'
  },

  handleChange({ detail }) {
    var key=detail.key;
    this.setData({
      current: detail.key
    });
    wx.redirectTo({
      url: '../'+key+'/'+key,   
      })
  },
  //查看详情方法
  detail(e){
   var packid=e.currentTarget.id
   var url=e.currentTarget.dataset.url
    var packname = e.currentTarget.dataset.packname
console.log(packname)
    
   wx.navigateTo({
    url: '../detail/detail?packid='+packid+'&url='+url+'&packname=' + packname,   
   });
     

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
      url: 'https://junxin.mynatapp.cc//tpes/wx/combo',
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
  },

  //输入内容时
  searchActiveChangeinput: function (e) {
    const val = e.detail.value;
    this.setData({
      'search.showClearBtn': val != '' ? true : false,
      'search.searchValue': val
    })
  },
  //点击清除搜索内容
  searchActiveChangeclear: function (e) {
    this.setData({
      'search.showClearBtn': false,
      'search.searchValue': ''
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/combo',
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


  },
  //点击聚集时
  focusSearch: function () {
    if (this.data.search.searchValue) {
      this.setData({
        'search.showClearBtn': true
      })
    }
  },
  //搜索提交
  searchSubmit: function () {
    const val = this.data.search.searchValue;
    if (val) {
      const that = this,
        app = getApp();
      // 搜索中提示
      wx.showToast({
        title: '搜索中',
        icon: 'loading'
      });
      // 搜索请求路径
      // 请根据自己实际情况填写路径，具体请看注意事项
      wx.request({
        url: 'https://junxin.mynatapp.cc/tpes/wx/combo',
        data: {
          keywords: val,
        },
        method: "POST",
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'cookie': wx.getStorageSync("sessionid")
          //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
        },
        dataType: 'json',
        success: function (result) {
          if (result.data.length > 0) {
            that.setData({
              list: result.data,
              'search.showClearBtn': false,
              noData: false,
            })
          } else {
            that.setData({
              list: null,
              noData: true,
              'search.showClearBtn': false,
            })
          }
        },


      })
    }
  },
 //失去焦点，并且长度为0时
  blurSearch:function(){
    var val = this.data.search.searchValue;
    const that = this;

   if(val.length==0){
    wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/combo',
      data: {
        keywords: val,
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'cookie': wx.getStorageSync("sessionid")
        //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
      },
      dataType: 'json',
      success: function (result) {
        if (result.data.length > 0) {
          that.setData({
            list: result.data,
            'search.showClearBtn': false,
            noData: false,
          })
        } else {
          that.setData({
            list: null,
            noData: true,
            'search.showClearBtn': false,
          })
        }
      },


    })
   }
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