<script setup>
const { questionnaire } = defineProps(["questionnaire"]);
</script>
<template>
  <form id="questionnaireForm">
    <div id="questionsContainer">
      <div
        v-for="question in questionnaire.questions"
        class="questionContainer"
      >
        <p>
          {{ question.description }}
        </p>
        <select>
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
  height: 100%;
  display: flex;
  flex-direction: column;
  row-gap: 20px;
}

#questionsContainer {
  height: 250px;
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
