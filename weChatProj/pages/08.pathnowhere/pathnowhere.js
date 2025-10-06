import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "	https://patchwiki.biligame.com/images/wqmt/"
var searchCondition = {}
// 需要格式化图片地址的字段
var initCols = [
  { "name": "cardImg", "type": "name" }
  , { "name": "illustration", "type": "list" }
]

Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataList: util.initList(IMG_HOST, jsonData.dataList, initCols, "name", "name")
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.setData({
    })
  },
  /* 取得关联数据 */
  getImgBox(e) {
    console.debug(e)
    let data = e.currentTarget.dataset.acter
    wx.previewImage({
      current : data.illustration[0],
      urls: data.illustration
    })
  },
})