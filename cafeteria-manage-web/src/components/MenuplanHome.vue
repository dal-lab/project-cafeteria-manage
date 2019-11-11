<template>
    <div>
        <div class="menu-plan-home-title">식단 관리</div>
        <div class="menu-plan-list">
          <div class="menu-plan-item" v-for="month in menuPlanMonth" :key="month"
              ref="menuPlanItem">
              <router-link :to="`/menuPlans/${month}`">
                  <div class="menu-plan-item-title">{{ month }}월</div>
              </router-link>
          </div>
          <div class="menu-plan-item menu-plan-item-new">
              <a class="new-menu-plan-btn" href="" @click.prevent="SET_IS_ADD_MENUPLAN(true)">
                  &plus; 새 식단 추가
              </a>
          </div>
        </div>
        <AddMenuplan v-if="isAddMenuplan" />
    </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'
import AddMenuplan from './AddMenuplan.vue'

export default {
    components: {
        AddMenuplan
    },
    data() {
        return {
          loading: false,
        }
    },
    computed: {
        ...mapState({
            isAddMenuplan: 'isAddMenuplan',
            menuPlanMonth: 'menuPlanMonth',
        })
    },
    created() {
      this.fetchData()
    },
    methods: {
        ...mapMutations([
            'SET_IS_ADD_MENUPLAN',
        ]),
        ...mapActions([
          'GET_MENUPLANMONTH'
        ]),
        fetchData() {
            this.loading = true
            this.GET_MENUPLANMONTH().finally(_ => {
                this.loading = false
            })
        },
        setMonth(month) {
          this.SET_MONTH(month)
          console.log(month)
        }
    }
}
</script>

<style>
.menu-plan-home-title {
  padding: 10px;
  font-size: 18px;
  font-weight: bold;
}
.menu-plan-list {
  padding: 10px;
  display: flex;
  flex-wrap: wrap;
}
.menu-plan-item {
  width: 23%;
  height: 100px;
  margin: 0 2% 20px 0;
  border-radius: 3px;
  background-color: rgb(0, 121, 191);
  position: relative;
}
.menu-plan-item-new {
  background-color: #ddd;
}
.menu-plan-item a {
  text-decoration: none;
  display: block;
  width: 100%;
  height: 100%;
}
.menu-plan-item a:hover,
.menu-plan-item a:focus {
  background-color: rgba(0,0,0, .1);
  color: #666;
}
.menu-plan-item a.new-menu-plan-btn {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  height: 100px;
  width: inherit;
  color: #888;
  font-weight: 700;
}
.menu-plan-item-title {
  color: #fff;
  font-size: 18px;
  font-weight: 700;
  padding: 10px;
}
</style>