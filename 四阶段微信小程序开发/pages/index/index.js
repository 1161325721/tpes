var util = require('../../utils/util.js');
Page({
  data: {
    value2: [],
    displayValue2: '请选择',
    lang: 'zh_CN',
  },
  onLoad: function() {
    var day3 = new Date();
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000);
    var s3 = day3.getFullYear() + "-" + (day3.getMonth() + 1) + "-" + day3.getDate();
    console.log(s3 + "time")
    // 再通过setData更改Page()里面的data，动态更新页面的数据
    this.setData({
      time: s3
    });
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

})