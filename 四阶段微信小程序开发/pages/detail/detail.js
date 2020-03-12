// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    visible1: false,
    actions2: [
      {
        name: '确认购买',
        color: '#ed3f14',

      }
    ],
    value1: 1,
    value2: [],
    displayValue2: '请选择',
    lang: 'zh_CN',
  },
  handleChange1({ detail }) {
    this.setData({
      value1: detail.value
    })
  },

  handleOpen2() {
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
  var that=this;
  var packid=that.data.packid;
  var count=that.data.value1;
  var goprepay=(that.data.numdiscount)*(that.data.value1)
var  goappointdate=that.data.displayValue2;
var packname=that.data.packname;
    var numdiscount = that.data.numdiscount;

if (goappointdate!="请选择") {
  wx.navigateTo({
    url: '../input/input?packid='+packid+'&goprepay='+goprepay+
      '&goappointdate=' + goappointdate + '&count=' + count+
      '&packname=' + packname + '&numdiscount='+numdiscount,
    success: (result) => {
      
    },
    fail: () => {},
    complete: () => {  
      action[0].loading = false;
      that.setData({
        visible2: false,
        actions2: action,
        value1: 1
      });
    }
  });
}else{
  wx.showModal({
    title: '错误信息',
    content: '请选择预约的时间',
    showCancel: false,
    confirmText: '确定',
    confirmColor: '#3CC51F',
  } );
  action[0].loading = false;
  that.setData({
    visible2: false,
    actions2: action,
    value1: 1
  });
}
  },
  setValue(values, key, mode) {
    this.setData({
      [`value${key}`]: values.value,
      [`displayValue${key}`]: values.label,
      // [`displayValue${key}`]: values.displayValue.join(' '),
    })
  },
  onConfirm(e) {
    const {
      index,
      mode
    } = e.currentTarget.dataset
    this.setValue(e.detail, index, mode)
    console.log(`onConfirm${index}`, e.detail)
  },
  onVisibleChange(e) {
    this.setData({
      visible: e.detail.visible
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log("接收到的参数是packid=" + options.packid); 
    var packid = options.packid;
    var url = options.url;
    var packname = options.packname;
    console.log(packname)

    this.setData({
      url: url,
      packid: packid,
      packname: packname
    });
    var day3 = new Date();
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000);
    var s3 = day3.getFullYear() + "-" + (day3.getMonth() + 1) + "-" + day3.getDate();
    // 再通过setData更改Page()里面的data，动态更新页面的数据
    this.setData({
      time: s3
    });
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/detail',
      data: {
        packid: packid
      },
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'cookie': wx.getStorageSync("sessionid")
        //读取sessionid,当作cookie传入后台将PHPSESSID做session_id使用
      },
      dataType: 'json',
      success: (result) => {
          var mylist=result.data;
          var numdiscount;
        for (var index = 0; index < mylist.length; index++) {
          numdiscount=mylist[index].numdiscount;
          
        }
        this.setData({
          mylist:mylist,
          numdiscount:numdiscount
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