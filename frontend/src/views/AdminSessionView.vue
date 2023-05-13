<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import MyNavigation from "../components/MyNavigation.vue";
import SessionInformationCard from "../components/SessionInformationCard.vue";
import questionnaires from "../data/questionnaires.json";
import sessions from "../data/sessions.json";
import sessionsInformation from "../data/sessionsInformation.json";
import Breadcrumbs from "../components/Breadcrumbs.vue";
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";

const newQuestionnaireForm = forms.find(
  (form) => form.title == "create questionnaire"
);

const newQuestionForm = forms.find((form) => form.title == "create question");

const route = useRoute();
const { id } = route.params;

const session = sessions.find((session) => session.id == id);

const questionnaire = questionnaires.find(
  (questionnaire) => questionnaire.sessionId == id
);

let adminQuestionnaireInformation = sessionsInformation.find(
  (sessionInformation) => sessionInformation.information == "adminQuestionnaire"
);

let adminNoQuestionnaireInformation = sessionsInformation.find(
  (sessionInformation) =>
    sessionInformation.information == "adminNoQuestionnaire"
);

const showComponent = ref(false);
console.log(showComponent.value);
function handleButtonClick(button) {
  console.log("Button clicked:", button);
  if (button.type == "create") {
    console.log("create it is");
    showComponent.value = true;
    console.log(showComponent.value);
  }
}
</script>

<template>
  <MyNavigation />
  <main id="adminSessionViewMain">
    <Breadcrumbs direction="/adminSessions" :session="session" />
    <SessionInformationCard
      @button-clicked="handleButtonClick"
      v-if="questionnaire"
      :sessionInformation="adminQuestionnaireInformation"
    />
    <SessionInformationCard
      @button-clicked="handleButtonClick"
      v-else="questionnaire"
      :sessionInformation="adminNoQuestionnaireInformation"
    />
    <div id="adminSessionViewFormContainer" v-if="showComponent">
      <MyForm :form="newQuestionnaireForm" />
      <MyForm :form="newQuestionForm" />
    </div>
  </main>
</template>

<style>
#adminSessionViewMain {
  width: 80%;
  display: grid;
  grid-template-rows: auto auto 1fr;
  margin: 150px 0 100px 0;
  row-gap: 30px;
}

#adminSessionViewFormContainer {
  display: grid;
  grid-template-columns: 1.5fr 1fr;
  column-gap: 30px;
  width: 100%;
  height: 100%;
}

#adminSessionViewFormContainer form {
  height: 100%;
  width: 100%;
}
</style>
