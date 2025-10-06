import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://patchwiki.biligame.com/images/crazyones/"
var searchCondition = {}
// 需要格式化图片地址的字段
var initCols = [
  { "name": "cardImg", "type": "name" }
]

Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataList: util.initList(IMG_HOST, jsonData.dataList, initCols, "name", "favorite"),
    heroineList : util.getDirInfo({"page":"crazyones","categoryId":"heroine"}),
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.setData({
      // dataList: util.initList(IMG_HOST, jsonData.dataList, initCols, "name", "releaseDate")
    })
  },

  doSearch(e) {
    let searchVal = e.currentTarget.dataset.value
    if (util.isEmpty(searchCondition["heroine"])) {
      searchCondition["heroine"] = searchVal
    } else {
      if (searchVal !== searchCondition["heroine"]) {
        searchCondition["heroine"] = searchVal
      } else {
        delete searchCondition["heroine"]
      }
    }
    let resultList = util.fifterList(jsonData.dataList, searchCondition)
    this.setData({
      dataList : resultList
    })
  },

  /* 取得关联数据 */
  getImgBox(e) {
    let heroine = e.currentTarget.dataset.data
    console.debug(e)
    let condition = {heroine : heroine.heroine}
    let result = util.fifterList(jsonData.dataList, condition)
    let imgBox = []
    for(let i = 0 ; i < result.length; i++) {
      imgBox.push(result[i].cardImg)
    }

    wx.previewImage({
      current : heroine.cardImg,
      urls: imgBox
    })
  },
})