<template>
  <div class="add-card">
    <form @submit.prevent="onSubmit">
      <input class="form-control" type="text" v-model="menuName" ref="inputText">
      <button class="btn btn-success" type="submit" :disabled="invalidInput">
        메뉴 추가</button>
      <a class="cancel-add-btn" href="" @click.prevent="$emit('close')">&times;</a>
    </form>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
    data() {
        return {
            menuName: "",
            month: this.$route.params.month
        }
    },
    computed: {
        invalidInput() {
          return !this.menuName.trim()
        },
        ...mapState({
          year: 'year'
        })
    },
    props: ['workDayId'],
    methods: {
      ...mapActions([
        'ADD_MENUTOMENUPLAN'
      ]),
      onSubmit() {
        this.ADD_MENUTOMENUPLAN({ 
          workDayId: this.workDayId,
          menuName: this.menuName,
          year: this.year,
          month: this.month
        })
          .catch(err => alert(err.data))
          .finally(_ => this.menuName = "")
      }
    }
}
</script>

<style>
.add-card {
  padding: 10px;
  display: block;
  position: relative;
}
.add-card .cancel-add-btn {
  display: inline-block;
  margin-left: 10px;
  vertical-align: middle;
  text-decoration: none;
  color: #888;
  font-size: 24px;
}
.add-card .cancel-add-btn:hover,
.add-card .cancel-add-btn:focus {
  color: #666;
}
</style>