import jsonData from '../tableData'

export default {
  /* 取得字典 */
  getDirInfo(condition) {
    let result = this.fifterList(jsonData.m_dirt, condition)
    if (result.length <= 1) {
      result = result[0]
    }
    // 匹配字典
    for(let i = 0 ; i < result.length ; i ++) {
      let key = ""
      let data = result[i]
      if("fe" == condition["page"]) {
        key = "{A01_IMG}"
      } else if ("azurlane" == condition["page"]) {
        key = "{A04_IMG}"
      } else if ("fgo" == condition["page"]) {
        key = "{A02_IMG}"
      }
      data.imgSrc = data.imgSrc.replace(key, jsonData.URL_MATCH[key])
    }
    return result
  },

  /** 筛选数据 */
  fifterList(list, condition) {
    return list.filter(item => {
      let fifterable = true
      // LOOP
      for (let key in condition) {
        // 条件值
        let value = condition[key]
        // 模糊匹配
        if (item[key].indexOf(value) < 0) {
          fifterable = false
        }
      }
      return fifterable
    })
  },

  /** 排序 */
  sortList(list, sortCol) {
    list.sort((curr, next) => {
      // 升序降序
      let col = sortCol
      let descFlag = "asc"
      curr[col] = this.isEmpty(curr[col]) ? "9999" : curr[col]
      next[col] = this.isEmpty(next[col]) ? "9999" : next[col]
      if (sortCol.split(' ').length > 1) {
        col = sortCol.split(' ')[0]
        descFlag = sortCol.split(' ')[1]
      }
      // 排序
      if ("asc" == descFlag) {
        return parseInt(curr[col]) - parseInt(next[col])
      } else {
        return parseInt(next[col]) - parseInt(curr[col])
      }
    })
    return list
  },

  getCodeListByCategory(condition, key) {
    return this.transListToMap(jsonData.m_dirt,condition, key)
  },

  /** 将LIST转为MAP方便通过KEY取得数据 */
  transListToMap(dataList, condition, key) {
    let list = this.fifterList(dataList, condition)
    let result = {}
    list.forEach(item => {
      result[item[key]] = item
    })
    return result
  },

  /**
   * 
   * @param {图片服务器} imgHost 
   * @param {数据列表} list 
   * @param {替换的图片字段} column 
   * @param {替换文字列} rpcCol 
   * @param {排序字段} sortCol 
   */
  initList(imgHost, list, column, rpcCol, sortCol) {
    let newList = []
    for (let i = 0; i < list.length; i++) {
      let data = list[i]
      for (let c = 0; c < column.length; c++) {
        let columnInfo = column[c]
        // 追加设置:设置了column.imgHost时使用column.imgHost作为imgHost
        imgHost = this.isEmpty(columnInfo["imageHost"]) ? imgHost : columnInfo["imageHost"]
        // END
        if ("list" === columnInfo["type"]) {
          for (let j = 0; j < data[columnInfo.name].length; j++) {
            let replaceTxt = data[rpcCol]
            data[columnInfo.name][j] = this.editImgSrc(imgHost, data[columnInfo.name][j], replaceTxt)
          }
        } else {
          // 碧蓝航线
          let replaceTxt = data[rpcCol]
          if(imgHost.indexOf("azurlane")>0) {
            replaceTxt = replaceTxt.replace(/ /g,'_')
            replaceTxt += "ShipyardIcon.png"
          }
          // 火纹
          if(imgHost.indexOf("feheroes")>0) {
            replaceTxt += "_Face_FC.webp"
          }
          data[columnInfo.name] = this.editImgSrc(imgHost, data[columnInfo.name], replaceTxt)
        }
      }
      newList.push(data)
    }
    newList = this.sortList(newList, sortCol)
    return newList
  },
  
  /**
   * 将数据中{host}替换成具体地址
   * @param {图片网络地址} imgHost 
   * @param {原始数据} originSrc 
   * @param {图片地址} imgName 
   */
  editImgSrc(imgHost, originSrc, imgName) {
    // 没有替代参数直接返回
    if (originSrc.indexOf(imgHost) >= 0 || originSrc.indexOf("http") == 0) {
      return originSrc
    }
    return imgHost + originSrc.replace("{key}", imgName)
  },

  /** 屏幕滚动到底部 */
  scrollToBottom(id) {
    wx.createSelectorQuery().select('#' + id).boundingClientRect((rect) => {
      wx.pageScrollTo({
        scrollTop: rect.bottom
      })
    }).exec()
  },

  isEmpty(data) {
    if (typeof data == "undefined") {
      return true
    } else if (typeof data == "string" && "" == data) {
      return true
    } else if (typeof data == "object" && Object.keys(data).length == 0) {
      return true
    }
    return false
  },
}