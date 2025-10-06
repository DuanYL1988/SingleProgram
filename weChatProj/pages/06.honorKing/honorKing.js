import jsonData from './data'
import util from '../../common/util'

var searchCondition = {}

Page({
  data: {
    dataList: initialize(),
    skinImgBox : []
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
    let pickHero = e.currentTarget.dataset.data
    console.debug(e)
    this.setData({
        skinImgBox : pickHero.skinImgList
    })
    util.scrollToBottom("artImgBox")
  },

  /* 检索数据 */
  doSearch(e) {

  },
})

function initialize(){
    let resultList = []
    for(let i = 0; i < jsonData.dataList.length ; i++ ){
        let data = jsonData.dataList[i]
        let index = data.no
        let skinIndex = data.defSkinIndex < data.skinList.length ? data.defSkinIndex : 1
        data["faceImgUrl"] = "https://game.gtimg.cn/images/yxzj/img201606/heroimg/#INDEX#/#INDEX#-smallskin-#SKIN#.jpg".replace(/#INDEX#/g,index).replace(/#SKIN#/g,skinIndex)
        data.skill = [
            "https://game.gtimg.cn/images/yxzj/img201606/heroimg/#INDEX#/#INDEX#00.png".replace(/#INDEX#/g,index)
            , "https://game.gtimg.cn/images/yxzj/img201606/heroimg/#INDEX#/#INDEX#10.png".replace(/#INDEX#/g,index)
            , "https://game.gtimg.cn/images/yxzj/img201606/heroimg/#INDEX#/#INDEX#20.png".replace(/#INDEX#/g,index)
            , "https://game.gtimg.cn/images/yxzj/img201606/heroimg/#INDEX#/#INDEX#30.png".replace(/#INDEX#/g,index)
        ]
        data["skinImgList"] = []
        for (let j = 0; j < data.skinList.length ; j++) {
          data["skinImgList"].push("https://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/"+ index +"/"+ index +"-bigskin-"+ (j+1) +".jpg")
        }
        resultList.push(data)
    }
    return resultList
}