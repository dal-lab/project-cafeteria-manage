<template>
    <div class="menuplan" :data-monthlyMenuPlan-id="data.workDayId">
        <div class="menuplan-header">
          <div class="menuplan-header-title">{{ data.date }} {{ data.day }}</div>
          <a class="delete-menu-list2-btn" href="" @click.prevent="onDeleteList">&times;</a>
        </div>

        <div class="menu-list2" :data-monthlyMenuPlan-id="data.workDayId">
          <MenuItem v-for="menu in data.menus" :key="`${ menu.pos }`" :data="menu" :work-day-id="data.workDayId" :week-count="weekCount"/>
        </div>

        <div v-if="isAddMenu">
          <AddMenuOnMenuplan :work-day-id="data.workDayId" :week-count="weekCount" @close="isAddMenu=false"/>
        </div>
        <div v-else>
          <a class="add-menu-btn" href="" @click.prevent="isAddMenu=true">
            &plus; 메뉴 추가
          </a>
        </div>
    </div>
</template>

<script>
import MenuItem from './MenuItem.vue'
import AddMenuOnMenuplan from './AddMenuOnMenuplan.vue'

export default {
    data() {
      return {
        isAddMenu: false
      }
    },
    props: ['data', 'weekCount'],
    components: {
      MenuItem,
      AddMenuOnMenuplan,
    }
}
</script>

<style>
.menuplan {
  background-color: #e2e4e6;
  border-radius: 3px;
  margin-right: 10px;
  display: flex;
  flex-direction: column;
  vertical-align: top;
  width: 100%;
  max-height: 100%;
}
.menuplan-header {
  flex: 0 0 auto;
  height: 30px;
  padding: 10px 8px 8px;
  position: relative;
}
.menuplan-header-title {
  font-size: 16px;
  font-weight: 700;
  padding-left: 8px;
  line-height: 30px;
}
.menuplan-header-day {
  font-size: 16px;
  font-weight: 700;
  padding-left: 8px;
  line-height: 30px;
}
.menu-list2 {
  flex: 1 1 auto;
  overflow-y: scroll;
  min-height: 10px;
}
.delete-menu-list2-btn {
  position: absolute;
  right: 10px;
  top: 8px;
  text-decoration: none;
  color: #aaa;
  font-size: 24px;
}
.empty-menu-item   {
  height: 10px;
  width: 100%;
  background-color: rgba(0,0,0, 0);
}
.add-menu-btn {
  flex: 0 0 auto;
  display: block;
  padding: 8px 10px;
  color: #8c8c8c;
  text-decoration: none;
}
.add-menu-btn:focus,
.add-menu-btn:hover {
  background-color: rgba(0,0,0, .1);
}
</style>