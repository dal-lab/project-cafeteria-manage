<template>
    <div class="menu-item2" :data-menu-id="data.id">
        <a href="" @click.prevent="onModify">
            <div>{{ data.menuName }}</div>
        </a>
        <a class="delete-menu-btn" href="" @click.prevent="onDelete">&times;</a>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  data() {
    return {
      month: this.$route.params.month
    }
  },
  computed: {
    ...mapState({
      year: 'year'
    })
  },
  props: ['data', 'workDayId'],
  methods: {
    ...mapActions([
      'DELETE_MENUPLAN'
    ]),
    onModify() {
      // TODO: 현재 선택된 일자의 메뉴 변경 기능 구현 필요
      console.log("onModify")
    },
    onDelete() {
      this.DELETE_MENUPLAN({
        workDayId: this.workDayId,
        menuId: this.data.id,
        year: this.year,
        month: this.month
      })
    } 
  }
}
</script>

<style>
.menu-item2 {
  background-color: #fff;
  border-radius: 3px;
  margin: 8px;
  padding: 6px 20px 2px 8px;
  box-shadow: 0 1px 0 #ccc;
  position: relative;
}
.menu-item2 a {
  text-decoration: none;
  color: #444;
  word-wrap: break-word;
  white-space: normal;
  overflow: hidden;
  display: block;
}
.menu-item2:hover,
.menu-item2:focus {
  background-color: rgba(0,0,0, .1);
  cursor: pointer;
}
.menu-item-meta {
  font-size: 26px;
  padding: 5px 0 0 3px;
  color: #8c8c8c;  
}
.delete-menu-btn {
  position: absolute;
  right: 10px;
  top: 4px;
  text-decoration: none;
  font-size: 18px;
  color: #aaa;
}
</style>