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
import ErrorContainer from "../components/ErrorContainer.vue";

const userRole = sessionStorage.getItem("role");

const newQuestionnaireForm = forms.find(
  (form) => form.title == "create questionnaire"
);

const editQuestionnaireForm = forms.find(
  (form) => form.title == "edit questionnaire"
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

const showCreate = ref(false);
const showEdit = ref(false);

function handleButtonClick(button) {
  if (button.type == "create") {
    showCreate.value = true;
  } else if (button.type == "edit") {
    showEdit.value = true;
  } else if (button.type == "delete") {
    if (
      confirm(
        "Are you sure you want to delete the questionnaire? This action cannot be undone."
      )
    ) {
      console.log("deleted");
    } else {
      console.log("canceled");
    }
  }
}
</script>

<template>
  <MyNavigation v-if="userRole === 'Admin'" navigationType="adminNav" />
  <main id="adminSessionViewMain" v-if="userRole === 'Admin'">
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
    <div class="adminSessionViewFormContainer" v-if="showCreate">
      <MyForm :form="newQuestionnaireForm" />
      <MyForm :form="newQuestionForm" />
    </div>

    <div class="adminSessionViewFormContainer" v-if="showEdit">
      <MyForm :form="editQuestionnaireForm" />
      <MyForm :form="newQuestionForm" />
    </div>
  </main>
  <ErrorContainer
    v-else-if="userRole === 'User'"
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
</style>
