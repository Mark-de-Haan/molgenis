<template>
  <div id="surveyEditorContainer"></div>
</template>

<script>
  import { SurveyEditor } from 'surveyjs-editor'
  import 'surveyjs-editor/surveyeditor.css'

  export default {
    name: 'QuestionnaireEditor',
    data () {
      return {
        editor: null
      }
    },
    mounted () {
      let editorOptions = {showEmbededSurveyTab: true}
      this.editor = new SurveyEditor('surveyEditorContainer', editorOptions)
      this.editor.saveSurveyFunc = function () {
        console.log(JSON.stringify(this.text))
      }
    },
    components: {
      SurveyEditor
    }
  }
</script>
