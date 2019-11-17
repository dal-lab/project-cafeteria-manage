<template>
    <div class="menuplan" :data-monthlyMenuPlan-id="data.workDayId">
        <div class="menuplan-header">
          <div class="menuplan-header-title">{{ data.date }}</div>
          <a class="delete-menu-list-btn" href="" @click.prevent="onDeleteList">&times;</a>
        </div>

        <div class="menu-list" :data-monthlyMenuPlan-id="data.id">
          <MenuItem v-for="menu in data.menus" :key="`${menu.id}`" :data="menu"/>
        </div>
    </div>
</template>

<script>
import MenuItem from './MenuItem.vue'

export default {
    props: ['data'],
    components: {
      MenuItem
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
.menu-list {
  flex: 1 1 auto;
  overflow-y: scroll;
  min-height: 10px;
}
.delete-menu-list-btn {
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