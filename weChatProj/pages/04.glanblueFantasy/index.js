import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://huiji-public.huijistatic.com/gbf/uploads/"
var searchCondition = {}
// 需要格式化图片地址的字段
var initCols = [
  { "name": "faceImgSrc", "type": "name" },
  { "name": "illImgs", "type": "list" }
]
const originList = util.initList(IMG_HOST, jsonData.dataList, initCols, "no", "no")

Page({
  /**
   * 页面的初始数据
   */
  data: {
    resultList: originList,
    bannerImages : []
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
    if ('clean' !== key ) {
      searchCondition[e.target.dataset.key] = e.target.dataset.value
      resultList = util.filterLikeList(originList, searchCondition)
    } else {
      searchCondition = {}
      resultList = originList
    }
    // 设置结果
    this.setData({
      resultList : resultList
    })
  },

  /* 取得关联数据 */
  getImgBox(e) {
    let data = e.currentTarget.dataset.acter
    let splitIndex = data.name.indexOf("(")
    let nameCond = splitIndex > 0 ? data.name.substring(0, splitIndex) : data.name
    let condition = {"name" : nameCond}
    let result = util.fifterList(originList, condition)
    // 设置结果
    this.setData({
      resultList : result,
      bannerImages : data.illImgs
    })

    /*
    wx.previewImage({
      current : data.illImgs[0],
      urls: data.illImgs,
      referrerPolicy : "no-referrer"
    })
    */
  },
})