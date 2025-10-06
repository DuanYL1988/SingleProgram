import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://static.wikia.nocookie.net/fategrandorder/images/"
var searchCondition = {}
var initCols = [
  {"name":"stage","type":"list"},
  {"name":"icon","type":"list"}
]
const dataList = util.initList(IMG_HOST, jsonData.SERVANT, initCols, "no", "no desc");

Page({
  data: {
    bannerImages: [],
    originDataList: initList(dataList),
    resultList: [],
    // 检索条件图标
    classType: util.getDirInfo({ "page": "fgo", "categoryId": "classType" }),
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.setData({
    })
  },

  /* 检索数据 */
  doSearch(e) {
    let key = e.target.dataset.key
    let resultList = []
    // 初始化
    searchCondition = {}
    if ('clean' !== key ) {
      searchCondition[e.target.dataset.key] = e.target.dataset.value
      resultList = util.fifterList(jsonData.SERVANT, searchCondition)
    } else {
      searchCondition = {}
      resultList = dataList
    }
    this.setData({
      originDataList : initList(resultList)
    })
  },

  showImgBox(e){
    let data = e.currentTarget.dataset.servant
    wx.previewImage({
      current : data.stage[data.selectStage],
      urls: data.stage,
      referrerPolicy : "no-referrer"
    })

  }
})

function initList(list) {
  for(let i = 0; i < list.length ; i++) {
    list[i]["selectStage"] = list[i].icon.length < 10 ? 1 : 3
  }
  return list
}