export const SET_PACKAGES = '__SET_PACKAGES__'
export const SET_ENTITY_TYPES = '__SET_ENTITY_TYPES__'
export const SET_SELECTED_ENTITY_TYPE = '__SET_SELECTED_ENTITY_TYPE__'
export const SET_ATTRIBUTE_TYPES = '__SET_ATTRIBUTE_TYPES__'
export const SET_EDITOR_ENTITY_TYPE = '__SET_EDITOR_ENTITY_TYPE__'
export const UPDATE_EDITOR_ENTITY_TYPE = '__UPDATE_EDITOR_ENTITY_TYPE__'
export const UPDATE_EDITOR_ENTITY_TYPE_ATTRIBUTE = '__UPDATE_EDITOR_ENTITY_TYPE_ATTRIBUTE__'
export const SET_SELECTED_ATTRIBUTE_ID = '__SET_SELECTED_ATTRIBUTE_ID__'
export const DELETE_SELECTED_ATTRIBUTE = '__DELETE_SELECTED_ATTRIBUTE__'

export const CREATE_ALERT = '__CREATE_ALERT__'

export default {
  [SET_PACKAGES] (state, packages) {
    state.packages = packages
  },
  [SET_ENTITY_TYPES] (state, entityTypes) {
    state.entityTypes = entityTypes
  },
  [SET_SELECTED_ENTITY_TYPE] (state, selectedEntityType) {
    state.selectedEntityType = selectedEntityType
  },
  [SET_ATTRIBUTE_TYPES] (state, attributeTypes) {
    state.attributeTypes = attributeTypes
  },
  [SET_EDITOR_ENTITY_TYPE] (state, editorEntityType) {
    state.editorEntityType = editorEntityType
  },
  [UPDATE_EDITOR_ENTITY_TYPE] (state, update) {
    state.editorEntityType[update.key] = update.value
  },
  /**
   * Update the editorEntityType attribute list in place
   * Performs a key value update for the selected attribute
   * Updates an editorEntityType attribute via index
   */
  [UPDATE_EDITOR_ENTITY_TYPE_ATTRIBUTE] (state, update) {
    // Return the index of the selected attribute in the array of the editorEntityType attributes
    const index = state.editorEntityType.attributes.findIndex(attribute => attribute.id === state.selectedAttributeID)
    const key = update.key

    state.editorEntityType.attributes[index][key] = update.value
  },
  [SET_SELECTED_ATTRIBUTE_ID] (state, selectedAttributeID) {
    state.selectedAttributeID = selectedAttributeID
  },
  /**
   * Deletes the selected attribute using the ID of the selected attribute found in the state
   */
  [DELETE_SELECTED_ATTRIBUTE] (state) {
    state.editorEntityType.attributes = state.editorEntityType.attributes.filter(attribute => attribute.id !== state.selectedAttributeID)
  },
  /**
   * Alert mutations
   * @param alert Object containing 'type' and 'message' Strings
   */
  [CREATE_ALERT] (state, alert) {
    state.alert = alert
  }
}
