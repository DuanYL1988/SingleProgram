import jsonData from './data'
import util from '../../common/util'

const IMG_HOST = "https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/";
const QQ_HOSTS = ["http://photogz.photo.store.qq.com/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr"
      ,"http://photogz.photo.store.qq.com/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/"
      ,"http://m.qpic.cn/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr"]
const HOST_ENDS = ["!/b&bo=sASgBUAGgAcDZ5I","!/b&bo=sARYBUAGIAcDZ8o","!/b&bo=sARYBUAGIAcDhyo","!/b&bo=sARYBUAGIAcDd9o"
,"!/b&bo=sARYBUAGIAcDV*o","!/b&bo=sARYBUAGIAcDB6o","!/b&bo=sASgBUAGgAcDB*I","!/b&bo=sARYBUAGIAcDR.o"
,"!/b&bo=sASgBUAGgAcDR7I","!/b&bo=sASgBUAGgAcDV6I","!/b&bo=sASgBUAGgAcDd4I","!/b&bo=ggMABIIDAAQDByI"
,"!/b&bo=ggMABIIDAAQDR2I","!/b&bo=ggMABIIDAAQDZ0I","!/b&bo=ggMABIIDAAQDV3I","!/b&bo=VQMABFUDAAQDByI"
,"!/b&bo=VQMABFUDAAQDNxI","!/b&bo=QAaAB0AGgAcDR2I","!/b&bo=VQMABFUDAAQDR2I","!/b&bo=ggMABIIDAAQDNxI"
,"!/b&bo=sASgBUAGgAcDh3I","!/b&bo=QAYgB0AGIAcDV3I","!/b&bo=HASvBBwErwQDZ0I","!/b&bo=HASvBBwErwQDV3I"]

var searchCondition = {}
var initCols = [
  {"name":"faceImgUrl","type":"single"}
]
const dataList = util.initList(IMG_HOST, jsonData.HERO, initCols, "imgName", "releaseDate desc");

// 获取应用实例
Page({
  data: {
    bannerImages : [],
    fireEmblemHeros : dataList,
    // 集合数据
    moveType : util.getDirInfo({"page":"fe","categoryId":"move"}),
    weaponType : util.getDirInfo({"page":"fe","categoryId":"weapon"}),
    colorList : util.getDirInfo({"page":"fe","categoryId":"color"}),
    enteyList : util.getDirInfo({"page":"fe","categoryId":"entry"}),
    // 单个数据
    moveIcon : util.getCodeListByCategory({"page":"fe","categoryId":"move"},"param"),
    weaponIcon : util.getCodeListByCategory({"page":"fe","categoryId":"weapon"},"param"),
    colorIcon : util.getCodeListByCategory({"page":"fe","categoryId":"color"},"param")
  },
  onLoad() {
    this.setData({
    })
  },

  /* 取得关联数据 */
  getJoinData(e) {
    let hero = e.target.dataset.hero
    let joinId = hero.name
    let resultList = util.fifterList(jsonData.HERO,{'name' : joinId})
    this.setData({
      joinList : resultList,
      bannerImages : editStageImg(hero.stageImg)
    })
    util.scrollToBottom("bannerIllm")
  },
  /* 火纹立绘 */
  showFeHero(e) {
    // 初始化
    let hero = e.target.dataset.hero
    this.setData({
      bannerImages : editStageImg(hero.stageImg)
    })
  },
  /* 检索数据 */
  doSearch(e) {
    let key = e.target.dataset.key
    let resultList = []
    if ('clean' !== key ) {
      searchCondition[e.target.dataset.key] = e.target.dataset.value
      resultList = util.filterLikeList(jsonData.HERO,searchCondition)
    } else {
      searchCondition = {}
      resultList = dataList
    }
    // 设置结果
    this.setData({
      joinList : [],
      bannerImages : [],
      fireEmblemHeros : resultList
    })
  },
  /* 测试跳转页面 */
  goToAlbum(e) {
    let game = e.target.dataset.game
    let imgUrl = e.target.dataset.hero.normalImgUrl
    wx.navigateTo({
      url: '/pages/album/album?game=' + game + '&imgsrc=' + imgUrl
    })
  },
})

function editStageImg(stageImg){
  // stageImg[0] = stageImg[0] + "_Face.webp"
  // stageImg[1] = stageImg[1] + "_BtlFace.webp"
  // stageImg[2] = stageImg[2] + "_BtlFace_C.webp"
  // stageImg[3] = stageImg[3] + "_BtlFace_D.webp"

  // QQ空间图片地址
  for(let i = 0; i < QQ_HOSTS.length; i++) {
    let replaceKey = "{key" + (i + 2) + "}"
    stageImg[0] = stageImg[0].replace(replaceKey,QQ_HOSTS[i])
    stageImg[1] = stageImg[1].replace(replaceKey,QQ_HOSTS[i])
    stageImg[2] = stageImg[2].replace(replaceKey,QQ_HOSTS[i])
    stageImg[3] = stageImg[3].replace(replaceKey,QQ_HOSTS[i])
  }
  for(let i = 0; i< HOST_ENDS.length; i++) {
    let replaceKey = "{end" + i + "}"
    let replaceStr = HOST_ENDS[i] + "!&rf=viewer_4"
    stageImg[0] = stageImg[0].replace(replaceKey,replaceStr)
    stageImg[1] = stageImg[1].replace(replaceKey,replaceStr)
    stageImg[2] = stageImg[2].replace(replaceKey,replaceStr)
    stageImg[3] = stageImg[3].replace(replaceKey,replaceStr)
  }
  return stageImg
}