<script setup>
import { reactive } from "vue";
import { notify } from "@kyvg/vue3-notification";
import { useRouter } from "vue-router";
const { questionnaire } = defineProps(["questionnaire"]);

const router = useRouter();

const formData = reactive({});
const submitForm = async () => {
  let data = {
    questionnaireId: questionnaire.id,
    answers: [],
  };
  for (const element in formData) {
    data.answers.push({
      questionName: formData[element].description,
      questionScore: formData[element].score,
    });
  }

  const response = await fetch("http://localhost:8080/responses", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (response.ok) {
    router.push("/sessions");
    notify({
      type: "success",
      title: "The questionnaire has been successfully filled",
    });
  } else {
    console.log("boku yedin izzet");
  }
};

const handleScoreChange = (questionId, score) => {
  formData[questionId] = {
    score,
    description: questionnaire.questions.find((q) => q.id === questionId)
      .description,
  };
};
</script>
<template>
  <form id="questionnaireForm" @submit.prevent="submitForm">
    <div id="questionsContainer">
      <div
        v-for="question in questionnaire.questions"
        :key="question.id"
        class="questionContainer"
      >
        <p>
          {{ question.description }}
        </p>
        <select @change="handleScoreChange(question.id, $event.target.value)">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
        </select>
      </div>
    </div>
    <button type="submit">SUBMIT</button>
  </form>
</template>

<style>
#questionnaireForm {
  width: 100%;
  /* height: 100%; */
  display: flex;
  flex-direction: column;
  row-gap: 50px;
}

#questionsContainer {
  /* height: 250px; */
  max-height: 250px;
  width: 100%;
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  row-gap: 20px;
  padding-right: 10px;
}

.questionContainer {
  background-color: #0d2b29;
  border-radius: 50px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 15px 25px;
}

.questionContainer p {
  font-size: 22px;
}

select {
  background-color: #0d2b29;
  color: #ede3c8;
  border-radius: 50px;
  padding: 10px 15px;
  border: 1px solid #ede3c8;
}
</style>
