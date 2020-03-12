// pages/login/login.js


Page({
  /**
   * 页面的初始数据
   */
  data: {
    text: '\n',
    formData: {

    },
    rules: [
      {
        name: 'account',
        rules: { required: true, message: '账号必填' },
      }, {
        name: 'pwd',
        rules: { required: true, message: '密码必填' },
      }, {
        name: 'vcode',
        rules: { required: true, message: '验证码必填' },
      }]
  },
  formInputChange(e) {
    const { field } = e.currentTarget.dataset
    this.setData({
      [`formData.${field}`]: e.detail.value
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //刚进入页面随机先获取一个
    this.createCode()


  },
  submitForm() {
    this.selectComponent('#form').validate((valid, errors) => {
      if (!valid) {
        const firstError = Object.keys(errors)
        if (firstError.length) {
          this.setData({
            error: errors[firstError[0]].message
          })

        }
      } else {
        var that = this;
        var account = that.data.formData.account;
        var pwd = that.data.formData.pwd;
        var vcode = that.data.formData.vcode;
        var imagecode = that.data.code;
        if (vcode == imagecode) {
          wx.showLoading({
            title: '正在登录中',
            mask: true,
          });
          wx.request({
            url: 'https://junxin.mynatapp.cc/tpes/wx/login',
            data: {
              account: account,
              pwd: pwd
            },
            method: "POST",
            header: { 'content-type': 'application/x-www-form-urlencoded' },
            dataType: 'json',
            success: (result) => {
              if (result.data == "success") {
                console.log(result.header);
                wx.removeStorageSync('sessionid') //必须先清除，否则res.header['Set-Cookie']会报错
                //set-cookie:PHPSESSID=ic4vj84aaavqgb800k82etisu0; path=/; domain=.zhix.net
                // 登录成功，获取第一次的sessionid,存储起来
                // 注意：Set-Cookie（开发者工具中调试全部小写）（远程调试和线上首字母大写）
                wx.setStorageSync("sessionid", result.header["Set-Cookie"]);

                wx.showToast({
                  title: '登录认证通过'
                })
                wx.navigateTo({
                  url: '../combo/combo',
                });
              }
              else if (result.data == "myfalse") {
                wx.showModal({
                  title: '错误信息',
                  content: '账号或密码错误',
                  showCancel: false,
                  confirmText: '确定',
                  confirmColor: '#3CC51F',

                });
              }
              else if (result.data == "fasleError") {
                wx.showModal({
                  title: '错误信息',
                  content: '你的账号有异常，请联系客服查询',
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
        } else {
          wx.showModal({
            title: '错误信息',
            content: '验证码错误请重新输入',
            showCancel: false,
            confirmText: '确定',
            confirmColor: '#3CC51F',

          });


        }





      }
    })
  },
  getData() {
    var that = this;
    console.log(that.data.formData)
  },
  huanyizhang() {

    this.createCode()

  },

  createCode() {

    var code;

    //首先默认code为空字符串

    code = '';

    //设置长度，这里看需求，我这里设置了4

    var codeLength = 4;

    //设置随机字符

    var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    //循环codeLength 我设置的4就是循环4次

    for (var i = 0; i < codeLength; i++) {

      //设置随机数范围,这设置为0 ~ 36

      var index = Math.floor(Math.random() * 36);

      //字符串拼接 将每次随机的字符 进行拼接

      code += random[index];

    }

    //将拼接好的字符串赋值给展示的code

    this.setData({

      code: code

    })

  },
})


