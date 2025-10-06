import jsonData from './data'

const IMG_HOST = "https://browndust2-tw-wiki.netlify.app/browndust2/characters/"

Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataList: initalize()
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.setData({})
  },
  /* 取得关联数据 */
  getImgBox(e) {
    console.debug(e)
    let data = e.currentTarget.dataset.acter
    wx.previewImage({
      current : data.illustration,
      urls: [data.illustration]
    })
  },
})

function initalize(){
    let result = []
    jsonData.dataList.forEach(item => {
        item["cardImg"] = IMG_HOST + item.imgName + ".webp"
        item["illustration"] = IMG_HOST + "large_images/" + item.imgName + ".webp"
        result.push(item)
    })
    return result
}