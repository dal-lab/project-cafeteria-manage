<template>
    <nav class="header">
        <div class="header-logo">
            <router-link to="/">베니스 F&S</router-link>
        </div>
        <ul class="header-menus">
            <li class="header-menu"><router-link to="/menus">메뉴 관리</router-link></li>
            <li class="header-menu"><router-link to="/menuplans">식단 관리</router-link></li>
            <!-- <li class="header-menu"><router-link to="/ingredients">식자재 관리</router-link></li> -->
        </ul>
    </nav>
</template>

<script>
export default {

}
</script>

<style>
.header {
  flex: none;
  background-color: rgb(2, 106, 167);
  height: 32px;
  padding: 4px;
}
.header a {
  display: block;
  height: 30px;
  line-height: 30px;
  text-decoration: none;
  color: rgba(255,255,255,.5);
}
.header-logo {
  position: absolute;
  left: 50%;
  top: 7px;
  margin-left: -30px;
  text-align: center;
  font-weight: bolder;
  font-size: 24px;
}
.header-logo a:hover,
.header-logo a:focus {
  color: rgba(255,255,255,.9);
}
.header-menus {
  list-style-type: none;
  margin: 0;
  padding: 0;
  background-color: rgba(255,255,255, .3);
}
.header-menu {
  float: left;
  padding-left: 10px;
}

.header-menu a {
  display: block;
  border-radius: 2px;
  padding: 0 10px;
  background-color: rgba(255,255,255, .5);
  color: white;
  transition: all .3s;
}

.header-menu a:hover,
.header-menu a:focus {
    background-color: rgba(255,255,255, .3);
}
</style>
