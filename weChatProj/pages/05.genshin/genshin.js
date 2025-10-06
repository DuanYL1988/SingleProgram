import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://static.wikia.nocookie.net/gensin-impact/images/"
var searchCondition = {}
// 需要格式化图片地址的字段
var initCols = [
  { "name": "faceImgUrl", "type": "name" }
  , { "name": "birthday", "type": "list" }
  , { "name": "artwork", "type": "list" }
  , { "name": "imgBox", "type": "list" }
]

Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataList: util.initList(IMG_HOST, jsonData.dataList, initCols, "name", "releaseDate")
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
    let attrNm = e.target.dataset.type
    let data = e.target.dataset.data
    console.log(attrNm)
    wx.previewImage({
      current : data[attrNm][0],
      urls: data[attrNm],
    })
  },
})