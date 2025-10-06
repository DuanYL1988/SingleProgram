import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://azurlane.netojuu.com/images/"
var searchCondition = {}
var initCols = [
  { "name": "faceImgUrl", "type": "name" }
  , { "name": "skinList", "type": "list" }
  , { "name": "artImg", "type": "list" }
]
const dataList = util.initList(IMG_HOST, jsonData.shipList, initCols, "name", "no")

Page({
  data: {
    shipList: dataList,
    factionIcon : util.getDirInfo({"page":"azurlane","categoryId":"faction"}),
    resultList: [],
    skinImgBox: [],
    artImgBox: [],
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
    let imgType = e.target.dataset.type
    let ship = e.target.dataset.ship
    console.log(imgType)
    if ("skin" === imgType) {
      wx.previewImage({
        current : ship.skinList[0],
        urls: ship.skinList,
      })
    } else {
      wx.previewImage({
        current : ship.artImg[0],
        urls: ship.artImg,
      })
    }
    let goToId = "skin" === imgType ? "skinImgBox" : "artImgBox"
    util.scrollToBottom(goToId)
  },

  /* 检索数据 */
  doSearch(e) {
    let key = e.target.dataset.key
    let resultList = []
    // 初始化
    searchCondition = {}
    if ('clean' !== key ) {
      searchCondition[key] = e.target.dataset.value
      resultList = util.fifterList(jsonData.shipList, searchCondition)
    } else {
      searchCondition = {}
      resultList = dataList
    }
    this.setData({
      shipList : resultList
    })
  },
})