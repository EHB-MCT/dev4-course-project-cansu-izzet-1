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

export default {
  name: "App",
  components: {
    Bar,
  },
  data() {
    return {
      data: {
        labels: [
          ["How much apes", "are there in the", "jungle?"],
          ["How much apes", "are there in the", "jungle?"],
          ["This is a question?", "This is a question?"],
        ],
        datasets: [{ data: [5, 8, 7] }],
      },
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
    };
  },
};
</script>

<template>
  <div class="statisticCardContainer">
    <p>John - 12/03/2020</p>
    <Bar :data="data" :options="options" />
  </div>
</template>

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
