import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://patchwiki.biligame.com/images/sonw/"
const IMG_HOST2 = "https://cdnimg-v2.gamekee.com/wiki2.0/images/"
var searchCondition = {}
// 需要格式化图片地址的字段
var initCols = [
  { "name": "cardImg", "type": "name" }
  , { "name": "illustration", "type": "list" }
]
var dataList = concatList()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataList: dataList
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
    let data = e.currentTarget.dataset.acter
    wx.previewImage({
      current : data.illustration[0],
      urls: data.illustration
    })
  },
})

function concatList() {
  let list1 = util.initList(IMG_HOST, jsonData.dataList, initCols, "", "")
  let listMp = util.transListToMap(list1,{},"name")
  //console.debug("debug",listMp)
  for(let name in jsonData.dataList2) {
    let data = jsonData.dataList2[name]
    data["name"] = name
    data.cardImg = IMG_HOST2 + data.cardImg

    if(name in listMp) {
      listMp[name]['illustration'].concat(data.illustration)
      data.illustration.forEach((img,index) => {
        listMp[name]['illustration'].push(IMG_HOST2 + img) 
      })
    } else {
      data.illustration.forEach((img,index) => {
        data['illustration'][index] = IMG_HOST2 + img
      })
      listMp[name] = data
    }
  }
  return listMp
}