import source from './data'

const IMG_HOST = source.IMG_HOST
const MASTER_DATA = source.MASTER_DATA
const PAGE_SIZE = 30

function imageUrl(path) {
  if (!path || path === "''" || path.indexOf('http') === 0) return ''
  return IMG_HOST + path + "/revision/latest"
}

const RESONATOR_LIST = source.DATA_LIST.map((item, index) => {
  const images = []
  const imageFields = [
    { key: 'cardImg', label: '角色卡面' },
    { key: 'spriteImg', label: '角色立绘' },
    { key: 'postImg', label: '海报' }
  ]
  imageFields.forEach(field => {
    const url = imageUrl(item[field.key])
    if (url) images.push({ url, label: field.label })
  })
  ;(item.artworks || []).forEach((path, artworkIndex) => {
    const url = imageUrl(path)
    if (url) images.push({ url, label: '宣传插画 ' + (artworkIndex + 1) })
  })
  const weapon = MASTER_DATA['WEAPON_TYPE_' + item.weaponType]
  const attribute = MASTER_DATA['ATTRS_' + item.attrs]
  return Object.assign({}, item, {
    _key: item.id || String(index),
    faceUrl: imageUrl(item.faceImg),
    images,
    weaponName: weapon ? weapon.name : item.weaponType,
    weaponIcon: weapon ? imageUrl(weapon.imgurl) : '',
    attributeName: attribute ? attribute.name : item.attrs,
    attributeIcon: attribute ? imageUrl(attribute.imgurl) : '',
    rarityStars: Math.max(1, Math.min(5, parseInt(item.rarity, 10) || 4))
  })
})
const DEFAULT_RESONATOR = RESONATOR_LIST.find(item => item.name === 'Hsin') || RESONATOR_LIST[0]
const ALL_RESONATORS = DEFAULT_RESONATOR
  ? [DEFAULT_RESONATOR].concat(RESONATOR_LIST.filter(item => item !== DEFAULT_RESONATOR))
  : RESONATOR_LIST

Page({
  data: {
    resonators: ALL_RESONATORS.slice(0, PAGE_SIZE),
    selected: DEFAULT_RESONATOR,
    artIndex: 0,
    showImagePreview: false,
    previewImageSrc: '',
    query: '',
    selectedAttribute: '全部属性',
    selectedWeapon: '全部武器',
    attributes: ['全部属性'].concat(Array.from(new Set(ALL_RESONATORS.map(item => item.attributeName).filter(Boolean)))),
    weapons: ['全部武器'].concat(Array.from(new Set(ALL_RESONATORS.map(item => item.weaponName).filter(Boolean)))),
    totalCount: ALL_RESONATORS.length,
    resultCount: ALL_RESONATORS.length,
    shownCount: Math.min(PAGE_SIZE, ALL_RESONATORS.length),
    hasMore: ALL_RESONATORS.length > PAGE_SIZE
  },

  onSearchInput(e) {
    this.setData({ query: e.detail.value })
    this.applyFilters()
  },

  clearSearch() {
    this.setData({ query: '' })
    this.applyFilters()
  },

  selectAttribute(e) {
    this.setData({ selectedAttribute: e.currentTarget.dataset.value })
    this.applyFilters()
  },

  selectWeapon(e) {
    this.setData({ selectedWeapon: e.currentTarget.dataset.value })
    this.applyFilters()
  },

  applyFilters() {
    const query = (this.data.query || '').trim().toLowerCase()
    const attribute = this.data.selectedAttribute
    const weapon = this.data.selectedWeapon
    const filtered = ALL_RESONATORS.filter(item => {
      const matchesAttribute = attribute === '全部属性' || item.attributeName === attribute
      const matchesWeapon = weapon === '全部武器' || item.weaponName === weapon
      const text = [item.nameCn, item.name, item.nameJp, item.attrs, item.weaponType, item.weaponName, item.influence, item.tags]
        .join(' ').toLowerCase()
      return matchesAttribute && matchesWeapon && (!query || text.indexOf(query) >= 0)
    })
    const count = Math.min(PAGE_SIZE, filtered.length)
    this._filteredResonators = filtered
    this.setData({
      resonators: filtered.slice(0, count),
      resultCount: filtered.length,
      shownCount: count,
      hasMore: filtered.length > count
    })
  },

  loadMore() {
    const filtered = this._filteredResonators || ALL_RESONATORS
    const end = Math.min(this.data.shownCount + PAGE_SIZE, filtered.length)
    this.setData({ resonators: filtered.slice(0, end), shownCount: end, hasMore: end < filtered.length })
  },

  selectResonator(e) {
    const item = (this._filteredResonators || ALL_RESONATORS)[e.currentTarget.dataset.index]
    if (!item) return
    this.setData({ selected: item, artIndex: 0 })
    wx.pageScrollTo({ scrollTop: 0, duration: 260 })
  },

  selectArtwork(e) {
    this.setData({ artIndex: Number(e.currentTarget.dataset.index) })
  },

  onArtworkSwiperChange(e) {
    this.setData({ artIndex: e.detail.current })
  },

  previewArtwork(e) {
    const item = this.data.selected
    const images = item.images || []
    const index = e && e.currentTarget.dataset.index !== undefined
      ? Number(e.currentTarget.dataset.index)
      : this.data.artIndex
    const current = images[index]
    if (current) this.setData({ showImagePreview: true, previewImageSrc: current.url })
  },

  closeImagePreview() {
    this.setData({ showImagePreview: false, previewImageSrc: '' })
  },

  noop() {
  },

  onBackPress() {
    if (!this.data.showImagePreview) return false
    this.closeImagePreview()
    return true
  }
})
