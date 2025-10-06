import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://pbs.twimg.com/profile_"

var initCols = [
  {"name":"icon","type":"single","imageHost": IMG_HOST + "images/"}
  , {"name":"banner","type":"single","imageHost":IMG_HOST + "banners/"}
]

// 获取应用实例
Page({
  data: {
    menuList : util.initList(IMG_HOST, jsonData.menuList, initCols, "", "")
  },
  onLoad() {
    this.setData({
    })
  },
})
