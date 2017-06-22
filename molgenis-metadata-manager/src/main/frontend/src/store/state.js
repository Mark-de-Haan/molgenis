export const INITIAL_STATE = window.__INITIAL_STATE__ || {}

const state = {
  alert: {
    message: null,
    type: null
  },
  packages: [],
  entityTypes: [],
  selectedEntityType: null,
  attributeTypes: [],
  editorEntityType: null,
  selectedAttributeID: null,
  numberOfChanges: 0
}

export default state
