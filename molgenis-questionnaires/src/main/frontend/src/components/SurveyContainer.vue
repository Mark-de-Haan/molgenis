<template>
  <div>
    <survey :survey="survey"></survey>
  </div>
</template>

<script>
  import { Model, Survey } from 'survey-vue'
  import exampleSurveyJson from '../../exampleSurveyJson'

  Survey.cssType = 'bootstrap'

  export default {
    name: 'SurveyContainer',
    props: ['surveyJson'],
    data () {
      return {
        survey: new Model(exampleSurveyJson)
      }
    },
    components: {
      Survey
    }
  }
</script>
