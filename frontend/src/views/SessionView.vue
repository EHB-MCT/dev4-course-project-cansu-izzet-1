<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import MyNavigation from "../components/MyNavigation.vue";
import SessionInformationCard from "../components/SessionInformationCard.vue";
import questionnaires from "../data/questionnaires.json";
import sessions from "../data/sessions.json";
import sessionsInformation from "../data/sessionsInformation.json";
import Breadcrumbs from "../components/Breadcrumbs.vue";
import QuestionnaireCard from "../components/QuestionnaireCard.vue";
import ErrorContainer from "../components/ErrorContainer.vue";

const userRole = sessionStorage.getItem("role");
const route = useRoute();
const { id } = route.params;

let questionnaireInformation = sessionsInformation.find(
  (sessionInformation) => sessionInformation.information == "questionnaire"
);

const questionnaire = questionnaires.find(
  (questionnaire) => questionnaire.sessionId == id
);
console.log(questionnaire);

const session = sessions.find((session) => session.id == id);

const showFill = ref(false);

function handleButtonClick(button) {
  if (button.type == "fill") {
    showFill.value = true;
  }
}
</script>

<template>
  <MyNavigation v-if="userRole === 'User'" navigationType="userNav" />
  <main id="adminSessionViewMain" v-if="userRole === 'User'">
    <Breadcrumbs direction="/sessions" :session="session" />
    <SessionInformationCard
      v-if="questionnaire"
      @button-clicked="handleButtonClick"
      :sessionInformation="questionnaireInformation"
    />
    <div v-if="showFill" id="questionnaireFormContainer">
      <QuestionnaireCard :questionnaire="questionnaire" />
    </div>
  </main>
  <ErrorContainer
    v-else-if="userRole === 'Admin'"
    message="Regrettably, your current access privileges do not permit you to view the contents of this page."
    :has-button="false"
  />

  <ErrorContainer
    v-else
    message="Please note that you are currently not authenticated or logged into the system."
    :has-button="true"
  />
</template>

<style>
#adminSessionViewMain {
  width: 80%;
  display: grid;
  grid-template-rows: auto auto 1fr;
  margin: 150px 0 100px 0;
  row-gap: 30px;
}

.adminSessionViewFormContainer {
  display: grid;
  grid-template-columns: 1.5fr 1fr;
  column-gap: 30px;
  width: 100%;
  height: 100%;
}

.adminSessionViewFormContainer form {
  height: 100%;
  width: 100%;
}

#questionnaireFormContainer {
  width: 100%;
  height: 100%;
}
</style>
