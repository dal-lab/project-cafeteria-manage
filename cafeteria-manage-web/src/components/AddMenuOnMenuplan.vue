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
            month: this.$route.params.month,
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
    props: ['workDayId', 'weekCount'],
    methods: {
      ...mapActions([
        'ADD_MENUTOMENUPLAN'
      ]),
      onSubmit() {
        const menuPos = this.newMenuPos()
        this.ADD_MENUTOMENUPLAN({ 
          workDayId: this.workDayId,
          menuName: this.menuName,
          pos: menuPos,
          year: this.year,
          month: this.month,
          weekCount: this.weekCount,
        })
          .catch(err => alert(err.data))
          .finally(_ => this.menuName = "")
      },
      newMenuPos() {
        const workDay = this.$store.state.monthlyMenuPlans.filter(menuPlan => menuPlan.workDayId === this.workDayId)[0]
        console.log(workDay.menus);
        if (!workDay) return 65535
        const {menus} = workDay
        if (!menus.length) return 65535
        
        return menus[menus.length - 1].pos * 2
      },
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