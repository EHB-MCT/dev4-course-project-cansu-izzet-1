<script setup>
const { sessionInformation } = defineProps(["sessionInformation"]);

const emits = defineEmits(["button-clicked"]);

function handleClick(button) {
  emits("button-clicked", button);
}
</script>

<template>
  <div class="questionnaireInformationCardContainer">
    <p>{{ sessionInformation.text }}</p>
    <div class="questionnaireInformationCardButtonContainer">
      <button
        @click="handleClick(button)"
        v-for="button in sessionInformation.buttons"
        :style="{
          backgroundColor: button.type === 'delete' ? '#981a1a' : '#0D2B29',
        }"
      >
        {{ button.text }}
      </button>
    </div>
  </div>
</template>

<style>
.questionnaireInformationCardContainer {
  background-color: #123e3b;
  border-radius: 50px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 30px;
}

.questionnaireInformationCardContainer p {
  font-weight: 700;
  font-size: 22px;
}

.questionnaireInformationCardButtonContainer {
  display: flex;
  column-gap: 20px;
}

.deleteButton {
  background-color: #981a1a;
}
</style>
