<script setup>
const { form } = defineProps(["form"]);
</script>
<template>
  <form>
    <h1>{{ form.title.toUpperCase() }}</h1>
    <div v-if="form.inputs[0].type == 'text'" id="inputsContainer">
      <div v-for="input in form.inputs">
        <label :for="input.id">{{ input.label }}</label>
        <input
          :type="input.type"
          :name="input.name"
          :id="input.id"
          :placeholder="input.placeholder"
          required
        />
      </div>
    </div>
    <div v-else class="checkBoxContainer">
      <div v-for="input in form.inputs">
        <input
          :type="input.type"
          :name="input.name"
          :id="input.id"
          :placeholder="input.placeholder"
          required
        />
        <label :for="input.id">{{ input.label }}</label>
      </div>
    </div>
    <div id="submitContainer">
      <router-link to="/adminSelection">
        <button type="submit">SUBMIT</button>
      </router-link>
      <div v-if="form.extra">
        <p>
          {{ form.extraText }}
        </p>
        <router-link :to="form.extraDirection">{{
          form.extraDirectionText.toUpperCase()
        }}</router-link>
      </div>
    </div>
  </form>
</template>

<style>
input[type="text"],
input[type="password"],
input[type="email"] {
  padding: 7px 20px;
  border: none;
  border-radius: 37px;
  font-size: 20px;
  color: #0d2b29;
  width: 100%;
}

input::-webkit-input-placeholder {
  /* Chrome/Opera/Safari/Edge */
  color: #808e7f;
}

input::-ms-input-placeholder {
  /* Microsoft Edge */
  color: #808e7f;
}

input:-ms-input-placeholder {
  /* IE 10+ */
  color: #808e7f;
}

form {
  padding: 55px;
  background-color: #123e3b;
  border: 3px solid #ede3c8;
  width: 450px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  border-radius: 60px;
}

h1,
label {
  color: #ede3c8;
  font-weight: 700;
}

h1 {
  font-size: 35px;
}

label {
  font-size: 22px;
}

p {
  font-size: 18px;
}

p,
a {
  color: #ede3c8;
}

#inputsContainer {
  display: flex;
  flex-direction: column;
  row-gap: 15px;
}

#inputsContainer div {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  row-gap: 5px;
}

#submitContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  row-gap: 10px;
}

#submitContainer div {
  display: flex;
  justify-content: flex-start;
  column-gap: 10px;
  align-items: center;
}

#submitContainer div a {
  font-weight: 700;
  text-decoration: none;
  font-size: 19px;
}

.checkBoxContainer {
  /* background-color: red; */
  width: 100%;
  height: 160px;
  display: flex;
  align-items: flex-start;
  flex-direction: column;
  overflow-y: scroll;
  gap: 10px;
  padding-right: 10px;
}

.checkBoxContainer div {
  width: 100%;
  background-color: #0d2b29;
  border-radius: 50px;
  padding: 5px 20px;
  display: flex;
  align-items: center;
  column-gap: 10px;
}
</style>
