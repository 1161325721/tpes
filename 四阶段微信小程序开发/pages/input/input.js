import { $Message } from '../../dist/base/index';
Page({

  data: {

    visible2: false, 
    actions2: [
      {
        name: '确认购买',
        color: '#ed3f14',
      }
    ],

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    // console.log("接收到的参数是packid=" + options.packid); 
    var packid = options.packid;
    var goprepay = options.goprepay;
    var goappointdate = options.goappointdate;
    var count = options.count;
    var packname = options.packname;

    var numdiscount = options.numdiscount;

    var countlist = [];
    for (var index = 0; index < count; index++) {
      countlist.push(index);
    }
    console.log(countlist);
    this.setData({
      packid: packid,
      goprepay: goprepay,
      goappointdate: goappointdate,
      count: count,
      countlist: countlist,
      packname: packname,
      numdiscount: numdiscount
    });

  },
  formInputChange(e) {
    const { field } = e.currentTarget.dataset
    this.setData({
      [`formData.${field}`]: e.detail.value
    })
  },
  formSubmit(e) {
    console.log('Default Form Submit \n', e.detail);
    var value = e.detail.value;
    var that = this;
    var reg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/;
    var temp = "";
    //循环获取键值
    for (var i in value) {
      var num = value[i];
      var str = i.split('input');
      var mynum = parseInt(str[1]) + 1;
      if (!reg.test(num)) {
        console.log(i + ":" + value[i])
        $Message({
          content: '第' + mynum + '栏身份证号码格式有误',
          type: 'error'
        });
        return false;
      }
    }
    var mypush = [];
    for (var i in value) {

      for (var j = 0; j < mypush.length; j++) {
        if (mypush[j] == value[i]) {
          $Message({
            content: '有重复的身份证号码',
            type: 'error'
          });
          return false;
        } else {
          mypush.push(value[i]);
        }
      }
    }
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
    var goprepay=that.data.goprepay;
    var goappointdate=that.data.goappointdate;
     var formData=that.data.formData;
  var reqTask = wx.request({
        url: 'https://junxin.mynatapp.cc/tpes/wx/order',
        data: {
          packid: parseInt(packid) ,
          goprepay:parseFloat(goprepay) ,
          goappointdate: goappointdate,
          formData:JSON.stringify(formData)
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
            wx.showModal({
              title: '温馨提示',
              content: '订单创建成功，相关体检人员记得到体检工作填好信息',
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
           
              
          }else if(result.data=="payfalse"){
            wx.showModal({
              title: '错误信息',
              content: '你的余额已不足，请充值！',
              showCancel: false,
              confirmText: '确定',
              confirmColor: '#3CC51F',
            });
          } else{
            wx.showModal({
              title: '错误信息',
              content: '订单创建异常！',
              showCancel: false,
              confirmText: '确定',
              confirmColor: '#3CC51F',
            });
          }
        },
        fail: () => { },
        complete: () => {
          action[0].loading = false;
          this.setData({
            visible2: false,
            actions2: action,
            value1: 1
          });
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