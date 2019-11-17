<template>
    <div class="board-wrapper">
        <div class="board">

            <div class="board-header">
                <span class="board-title">{{ month }}월</span>
            </div>

            <div class="menuplan-list-section-wrapper">
                <div class="menuplan-list-section">
                    <div class="menuplan-list-wrapper" v-for="monthlyMenuPlan in monthlyMenuPlans"
                        :key="monthlyMenuPlan.workDayId" :data-monthlyMenuPlan-id="monthlyMenuPlan.workDayId">
                        <MenuplanList :data="monthlyMenuPlan" />
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import MenuplanList from './MenuplanList.vue'

export default {
    components: {
        MenuplanList
    },
    data() {
        return {
            loading: false,
            month: this.$route.params.month
        }
    },
    created() {
        this.fetchData()
    },
    computed: {
        ...mapState({
            monthlyMenuPlans: 'monthlyMenuPlans',
            year: 'year'
        })
    },
    methods: {
        ...mapActions([
            'GET_MONTHLYMENUPLANS'
        ]),
        fetchData() {
            this.loading = true
            this.GET_MONTHLYMENUPLANS({ year: this.year, month: this.month }).finally(_ => {
                this.loading = false
            })
        }
    }
}
</script>

<style>
.board-wrapper {
  position: flex;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
}
.board {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.board-header {
  flex: none;
  padding: 8px 4px 8px 8px;
  margin: 0;
  height: 32px;
  line-height: 32px;
}
.board-title {
  font-weight: 700;
  font-size: 18px;
}
.menuplan-list-section-wrapper {
  flex-grow: 1;
  position: relative;
}
.menuplan-list-section {
  position: absolute;
  display: flex;
  flex-wrap: wrap;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  overflow-x: auto;
  overflow-y: auto;
  white-space: nowrap;
  padding: 0 10px;
}
.menuplan-list-wrapper {
  display: flex;
  height: 33%;
  width: 300px;
  vertical-align: top;
  padding: 5px;
  margin-right: 5px;
}
</style>