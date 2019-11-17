<template>
    <div class="menu-item" :data-menu-id="data.id">
        <router-link :to="`/menus/${data.id}`">
            <div>{{ data.menuName }}</div>
        </router-link>
        <a class="delete-menu-btn" href="" @click.prevent="onDelete">&times;</a>
    </div>
</template>

<script>
export default {
    props: ['data'],
    methods: {
        onDelete() {
            //TODO: 삭제 API 구현 후 연결 필요
        } 
    }
}
</script>

<style>
.menu-item {
  background-color: #fff;
  border-radius: 3px;
  margin: 8px;
  padding: 6px 20px 2px 8px;
  box-shadow: 0 1px 0 #ccc;
  position: relative;
}
.menu-item a {
  text-decoration: none;
  color: #444;
  word-wrap: break-word;
  white-space: normal;
  overflow: hidden;
  display: block;
}
.menu-item:hover,
.menu-item:focus {
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