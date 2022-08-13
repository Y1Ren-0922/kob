<template>
  <NavBar />
  <router-view />
</template>

<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"

export default {
  components: {
    NavBar,
  },

  setup() {
    // axios({
    //   url: "http://127.0.0.1:3000/user/account/info/",
    //   method: "get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1ODY4YmMzZjRkZmI0ZjU5YTY1ZTFhYjEyODQ0MzJkYSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2MDM3NTQ5OSwiZXhwIjoxNjYxNTg1MDk5fQ.BN8CQ6vZV5dqrl60JldnBqym1tXP86fISvn_a2cGq1M"
    //   }
    // }).then(function (response) {
    //   console.log(response.data);
    // }).catch(function (error) {
    //   console.log(error.response.data);
    // });

    // axios({
    //   url: "http://127.0.0.1:3000/user/account/register/",
    //   method: "post",
    //   params: {
    //     username: "e",
    //     password: "",
    //     confirmedPassword: "pd"
    //   }
    // }).then(function (response) {
    //   console.log(response.data);
    // }).catch(function (error) {
    //   console.log(error.response.data);
    // });


  }
}

</script>

<style>
body {
  background-image: url("@/assets/imgs/background.png");
  background-size: cover;
}
</style>
