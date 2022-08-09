<template>
  <div>bot: {{ bot_name }}</div>
  <div>bot rating: {{ bot_rating }}</div>
  <router-view />
</template>

<script>
import { ref } from 'vue'
import $ from 'jquery'

export default {
  name: "App",
  setup: () => {
    let bot_rating = ref("");
    let bot_name = ref("");

    $.ajax({
      url: "http://127.0.0.1:3000/pk/getbotinfo/",
      type: "get",
      success: resp => {
        bot_name.value = resp.name;
        bot_rating.value = resp.rating;
      }
    });

    return {
      bot_name,
      bot_rating
    }
  }
}

</script>

<style>
body {
  background-image: url("@/assets/background.png");
  background-size: cover;
}
</style>
