<template>
  <div class="statisticCardContainer">
    <p>Questionnaire Id: {{ response.questionnaireId }}</p>
    <Bar :data="myData" :options="data.options" />
  </div>
</template>

<script lang="ts">
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";
import { Bar } from "vue-chartjs";
import { reactive } from "vue";

ChartJS.defaults.color = "#fff";
ChartJS.defaults.backgroundColor = "rgba(237, 227, 200, 0.5)";
ChartJS.defaults.borderColor = "rgba(13, 43, 41, 0.7)";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export const data = reactive({
  options: {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        callbacks: {
          title: (context) => {
            return context[0].label.replaceAll(",", " ");
          },
        },
      },
    },
    scales: {
      y: {
        min: 0,
        max: 10,
      },
    },
  },
});
</script>

<script setup lang="ts">
const { response } = defineProps(["response"]);

let myData = {
  labels: response.answers.map((answer) => answer.questionName),
  datasets: [{ data: response.answers.map((answer) => answer.questionScore) }],
};
console.log(myData);
</script>

<style>
.statisticCardContainer {
  width: 100%;
  height: 500px;
  background-color: #123e3b;
  padding: 50px 50px 100px 50px;
  border-radius: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  row-gap: 20px;
}
</style>
