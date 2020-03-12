// pages/orderdetail/orderdetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var packid = options.packid;
    var url = options.url;
    var packname = options.packname;
    var goid =  options.goid;
    var gostate = options.gostate;
    var date = options.date;
   var goprepay = options.goprepay;
   var golasttime = options.golasttime;

   
    this.setData({
      packid :packid,
      url:url,
      packname:packname,
      goid:goid,
      gostate:gostate,
      date:date,
      goprepay:goprepay,
      golasttime:golasttime
    })
    wx.showLoading({
      title: '',
      mask: true,
    });
    var reqTask = wx.request({
      url: 'https://junxin.mynatapp.cc/tpes/wx/detail',
      data: {
        goid:goid,
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

 
})