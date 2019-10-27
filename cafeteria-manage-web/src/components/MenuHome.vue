<template>
    <div>
        <div class="menu-home-title">메뉴 관리</div>
        <div class="menu-list">
            <div class="menu-item" v-for="menu in menus" :key="menu.id"
                ref="boardItem">
                <router-link :to="`/menus/${menu.id}`">
                    <div class="menu-item-title">{{ menu.menuName }}</div>
                </router-link>
            </div>
            <div class="menu-item menu-item-new">
                <a class="new-menu-btn" href="" @click.prevent="SET_IS_ADD_MENU(true)">
                    &plus; 새 메뉴 추가
                </a>
            </div>
        </div>
        <AddMenu v-if="isAddMenu" />
        <Menu v-if="isMenu" />
    </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'
import AddMenu from './AddMenu.vue'
import Menu from './Menu.vue'

export default {
    components: {
        AddMenu,
        Menu
    },
    data() {
        return {
            loading: false,
        }
    },
    computed: {
        ...mapState({
            isAddMenu: 'isAddMenu',
            isMenu: 'isMenu',
            menus: 'menus'
        })
    },
    created() {
        this.fetchData()
    },
    methods: {
        ...mapMutations([
            'SET_IS_ADD_MENU',
            'SET_IS_MENU'
        ]),
        ...mapActions([
            'FETCH_MENUS'
        ]),
        fetchData() {
            this.loading = true
            this.FETCH_MENUS().finally(_ => {
                this.loading = false
            })
        }
    }
}
</script>

<style>
.menu-home-title {
  padding: 10px;
  font-size: 18px;
  font-weight: bold;
}
.menu-list {
  padding: 10px;
  display: flex;
  flex-wrap: wrap;
}
.menu-item {
  width: 23%;
  height: 100px;
  margin: 0 2% 20px 0;
  border-radius: 3px;
  background-color: rgb(0, 121, 191);
  position: relative;
}
.menu-item-new {
  background-color: #ddd;
}
.menu-item a {
  text-decoration: none;
  display: block;
  width: 100%;
  height: 100%;
}
.menu-item a:hover,
.menu-item a:focus {
  background-color: rgba(0,0,0, .1);
  color: #666;
}
.menu-item-title {
  color: #fff;
  font-size: 18px;
  font-weight: 700;
  padding: 10px;
}
.menu-item a.new-menu-btn {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  height: 100px;
  width: inherit;
  color: #888;
  font-weight: 700;
}
</style>