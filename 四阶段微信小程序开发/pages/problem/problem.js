// pages/problem/problem.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    search: {
      searchValue: '',
      showClearBtn: false
    },
    searchResult: [],
    list:null,
    noData: false,
    current:1,
  },
  onChange(e) {
    var that=this;
    var value=that.data.search.searchValue;
    console.log(value)
    that.setData({
        current: e.detail.current,
    })
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/problem',
      data: {
        keywords :value,
           page:e.detail.current,
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

    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/problem',
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
      url: 'https://junxin.mynatapp.cc/tpes/wx/problem',
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
            noData: false,
            current:1,
            name:null,
          })
        } else {
          this.setData({
            list: null,
            noData: true,
            current:1,
            name:null,
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
        url: 'https://junxin.mynatapp.cc/tpes/wx/problem',
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
              current:1,
              name:null,


            })
          } else {
            that.setData({
              list: null,
              noData: true,
              'search.showClearBtn': false,
              current:1,
              name:null,

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
      url: 'https://junxin.mynatapp.cc/tpes/wx/problem',
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
            current:1,
            name:null,


          })
        } else {
          that.setData({
            list: null,
            noData: true,
            'search.showClearBtn': false,
            current:1,
            name:null,


          })
        }
      },


    })
   }
  },
})