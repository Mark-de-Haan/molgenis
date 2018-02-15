<template>
  <div>

    <!-- Loading spinner -->
    <template v-if="loading">
      <div class="spinner-container d-flex justify-content-center align-items-center">
        <i class="fa fa-spinner fa-spin fa-5x"></i>
      </div>
    </template>

    <!-- Survey -->
    <template v-else>
      <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-xl-10">
          <h1>{{ questionnaire.label }}</h1>
          <p v-html="questionnaire.description"></p>

          <survey :survey="questionnaire"></survey>
        </div>
      </div>
    </template>

  </div>
</template>

<style>
  .panel-footer {
    text-align: right;
  }

  .spinner-container {
    height: 80vh;
  }
</style>

<script>
  import { defaultBootstrapCss, Model, Survey } from 'survey-vue'

  Survey.cssType = 'bootstrap'
  defaultBootstrapCss.navigationButton = 'btn btn-primary'

  import api from '@molgenis/molgenis-api-client'

  export default {
    name: 'QuestionnaireForm',
    props: {

      /**
       * The name of the questionnaire to load
       */
      questionnaireName: {
        type: String,
        required: true
      }
    },
    data () {
      return {
        loading: true,
        questionnaire: null
      }
    },
    methods: {

      /**
       * Submit data
       */
      onSubmit (result) {
        console.log(result)
      },

      /**
       * Auto save
       * @param formData
       */
      onValueChanged (sender, options) {
        console.log(sender)
        console.log(options)
//        const options = {
//          body: JSON.stringify(formData)
//        }
//
//        api.post('/api/v1/' + this.questionnaire.name + '/' + this.questionnaireID + '?_method=PUT', options)
      }
    },
    computed: {

      /**
       * Computes the ID of the questionnaire belonging to the current user
       */
      questionnaireID () {
        return this.entity[this.questionnaire.idAttribute]
      }
    },
    created () {
      api.get('/menu/plugins/questionnaires/meta/' + this.questionnaireName).then(questionnaire => {
        console.log(questionnaire)

        const model = new Model(questionnaire.questionnaireSchema)
        model.onValueChanged.add(this.onValueChanged)
        model.onComplete.add(this.onSubmit)

        this.questionnaire = model
        this.loading = false
      })
    },
    components: {
      Survey
    }
  }
</script>
