import jsonData from './dataList'
import util from '../../common/util'

const IMG_HOST = "https://static.wikia.nocookie.net/feheroes_gamepedia_en/images/";
var searchCondition = {}
const dataList = initList(jsonData.DATA_LIST);

// 获取应用实例
Page({
  data: {
    imgHost: IMG_HOST,
    bannerImages : [],
    fireEmblemHeros : initFirstList(),
    // 集合数据
    moveType : util.getDirInfo({"page":"fe","categoryId":"move"}),
    weaponType : util.getDirInfo({"page":"fe","categoryId":"weapon"}),
    enteyList : util.getDirInfo({"page":"fe","categoryId":"entry"}),
    // 单个数据
    moveIcon : util.getCodeListByCategory({"page":"fe","categoryId":"move"},"param"),
    weaponIcon : util.getCodeListByCategory({"page":"fe","categoryId":"weapon"},"param"),
  },
  onLoad() {
    this.setData({
    })
  },
  /* 取得关联数据 */
  getJoinData(e) {
    let hero = e.target.dataset.hero
    let joinId = hero.imgName.split('_')[0] + '_'
    let resultList = util.fifterList(dataList,{'imgName' : joinId})
    this.setData({
      joinList : resultList,
      bannerImages : hero.stageImg
    })
    util.scrollToBottom("bannerIllm")
  },
  /* 点击同名其他英雄头像 */
  showFeHero(e){
    let hero = e.target.dataset.hero
    this.setData({
      bannerImages : hero.stageImg
    })
  },
  /* 检索数据 */
  doSearch(e) {
    let key = e.target.dataset.key
    let resultList = []
    if ('clean' !== key ) {
      searchCondition[e.target.dataset.key] = e.target.dataset.value
      resultList = util.fifterList(dataList,searchCondition)
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

function initList(dataList){
  let result = []
  for(let i = 0; i < dataList.length; i++) {
    result.push(editData(dataList[i]))
  }
  return result
}

function editData(data){
  if (data.faceImg.indexOf(data.imgName) < 0) {
    data.faceImg = IMG_HOST + data.faceImg + data.imgName + "_Face_FC.webp"
    data.skillList[2] = IMG_HOST + data.skillList[2]
    data.skillList[4] = IMG_HOST + data.skillList[4]
    data.skillList[6] = IMG_HOST + data.skillList[6]
    data.stageImg[0] = IMG_HOST + data.stageImg[0] + data.imgName + "_Face.webp"
    data.stageImg[1] = IMG_HOST + data.stageImg[1] + data.imgName + "_BtlFace.webp"
    data.stageImg[2] = IMG_HOST + data.stageImg[2] + data.imgName + "_BtlFace_C.webp"
    data.stageImg[3] = IMG_HOST + data.stageImg[3] + data.imgName + "_BtlFace_D.webp"
  }
  return data
}

function initFirstList(){
  let resultList = []
  for(let i = 0; i < 10; i++) {
    resultList.push(dataList[i])
  }
  return resultList
}
