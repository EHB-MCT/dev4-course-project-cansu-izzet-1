<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import MyNavigation from "../components/MyNavigation.vue";
import SessionInformationCard from "../components/SessionInformationCard.vue";
import sessionsInformation from "../data/sessionsInformation.json";
import Breadcrumbs from "../components/Breadcrumbs.vue";
import QuestionnaireCard from "../components/QuestionnaireCard.vue";
import ErrorContainer from "../components/ErrorContainer.vue";

const userRole = sessionStorage.getItem("role");
const userAccessToken = sessionStorage.getItem("accessToken");

const route = useRoute();
const { id } = route.params;

let questionnaireInformation = sessionsInformation.find(
  (sessionInformation) => sessionInformation.information == "questionnaire"
);

let noQuestionnaireInformation = sessionsInformation.find(
  (sessionInformation) => sessionInformation.information == "noQuestionnaire"
);

let hasQuestionnaire = ref(null);
let questionnaire = ref([]);

const showFill = ref(false);

function handleButtonClick(button) {
  if (button.type == "fill") {
    showFill.value = true;
  }
}

let session = ref([]);
fetch(`http://localhost:8080/sessions/${id}`, {
  method: "GET",
  headers: {
    Authorization: userAccessToken,
  },
})
  .then((response) => response.json())
  .then((result) => {
    session.value = result;
  })
  .then(() => {
    fetch(`http://localhost:8080/questionnaires/${session.value.id}`, {
      method: "GET",
      headers: {
        Authorization: userAccessToken,
      },
    })
      .then((response) => response.json())
      .then((result) => {
        questionnaire.value = result;
        hasQuestionnaire.value = true;
      })
      .catch(() => {
        hasQuestionnaire = false;
      });
  });
</script>

<template>
  <MyNavigation v-if="userRole === 'User'" navigationType="userNav" />
  <main id="adminSessionViewMain" v-if="userRole === 'User'">
    <Breadcrumbs direction="/sessions" :session="session" />
    <SessionInformationCard
      v-if="hasQuestionnaire"
      @button-clicked="handleButtonClick"
      :sessionInformation="questionnaireInformation"
    />
    <SessionInformationCard
      v-else="hasQuestionnaire"
      @button-clicked="handleButtonClick"
      :sessionInformation="noQuestionnaireInformation"
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
