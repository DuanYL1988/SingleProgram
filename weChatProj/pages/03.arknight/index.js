import operators from './data'

const IMG_HOST = 'https://patchwiki.biligame.com/images/arknights/'
const PAGE_SIZE = 36
const ALL_OPERATORS = operators.map((operator, index) => {
  const skins = Array.isArray(operator.skinImage) ? operator.skinImage : []
  return Object.assign({}, operator, {
    _key: operator.id || String(index),
    faceUrl: imageUrl(operator.faceImg),
    stageOneUrl: imageUrl(operator.stageOneImg),
    stageTwoUrl: imageUrl(operator.stageTwoImg),
    skinUrls: skins.map(imageUrl),
    skinNames: Array.isArray(operator.skinName) ? operator.skinName : [],
    rarityStars: Math.max(1, Math.min(6, parseInt(operator.rarity, 10) || 1))
  })
})

function imageUrl(path) {
  if (!path || path === "''" || path.indexOf('http') === 0) return ''
  return IMG_HOST + path
}

Page({
  data: {
    operators: ALL_OPERATORS.slice(0, PAGE_SIZE),
    selected: ALL_OPERATORS[0],
    selectedStage: 0,
    selectedSkin: -1,
    query: '',
    selectedVocation: '全部',
    vocations: ['全部'].concat(Array.from(new Set(ALL_OPERATORS.map(item => item.vocation).filter(Boolean)))),
    totalCount: ALL_OPERATORS.length,
    resultCount: ALL_OPERATORS.length,
    shownCount: Math.min(PAGE_SIZE, ALL_OPERATORS.length),
    hasMore: ALL_OPERATORS.length > PAGE_SIZE
  },

  onSearchInput(e) {
    this.setData({ query: e.detail.value })
    this.applyFilters()
  },

  clearSearch() {
    this.setData({ query: '' })
    this.applyFilters()
  },

  selectVocation(e) {
    this.setData({ selectedVocation: e.currentTarget.dataset.value })
    this.applyFilters()
  },

  applyFilters() {
    const query = (this.data.query || '').trim().toLowerCase()
    const vocation = this.data.selectedVocation
    const filtered = ALL_OPERATORS.filter(item => {
      const matchesVocation = vocation === '全部' || item.vocation === vocation
      const searchable = [item.nameCn, item.id, item.vocation, item.subVocation, item.influence, item.tags]
        .join(' ').toLowerCase()
      return matchesVocation && (!query || searchable.indexOf(query) >= 0)
    })
    const count = Math.min(PAGE_SIZE, filtered.length)
    this._filteredOperators = filtered
    this.setData({
      operators: filtered.slice(0, count),
      resultCount: filtered.length,
      shownCount: count,
      hasMore: filtered.length > count
    })
  },

  loadMore() {
    const filtered = this._filteredOperators || ALL_OPERATORS
    const end = Math.min(this.data.shownCount + PAGE_SIZE, filtered.length)
    this.setData({
      operators: filtered.slice(0, end),
      shownCount: end,
      hasMore: end < filtered.length
    })
  },

  selectOperator(e) {
    const index = e.currentTarget.dataset.index
    const operator = (this._filteredOperators || ALL_OPERATORS)[index]
    if (!operator) return
    this.setData({ selected: operator, selectedStage: 0, selectedSkin: -1 })
    wx.pageScrollTo({ scrollTop: 0, duration: 260 })
  },

  selectStage(e) {
    this.setData({ selectedStage: Number(e.currentTarget.dataset.stage), selectedSkin: -1 })
  },

  selectSkin(e) {
    this.setData({ selectedSkin: Number(e.currentTarget.dataset.index) })
  },

  previewArtwork() {
    const selected = this.data.selected
    let urls = [selected.stageOneUrl, selected.stageTwoUrl].filter(Boolean)
    if (this.data.selectedSkin >= 0 && selected.skinUrls[this.data.selectedSkin]) {
      urls = [selected.skinUrls[this.data.selectedSkin]].concat(urls)
    }
    const current = this.data.selectedSkin >= 0 && selected.skinUrls[this.data.selectedSkin]
      ? selected.skinUrls[this.data.selectedSkin]
      : (this.data.selectedStage ? selected.stageTwoUrl : selected.stageOneUrl)
    if (current && urls.length) wx.previewImage({ current, urls, referrerPolicy: 'no-referrer' })
  }
})
