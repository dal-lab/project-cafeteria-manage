<template>
  <Modal>
        <div slot="header">
            <h2>
                메뉴 상세
                <a href="" class="modal-default-button" 
                    @click.prevent="onClose">&times;</a>
            </h2>
        </div>

        <div slot="body">
            <form id="add-menu-form" 
                @submit.prevent="updateMenu">
                <input class="form-control" type="text" v-model="input" name="menuName" ref="input">
            </form>
        </div>

        <div slot="footer">
            <button class="btn" :class="{'btn-success': valid}" type="submit" 
                form="add-menu-form" :disabled="!valid">
            메뉴 수정</button>
            <hr />
            <ul class="menu-options">
                <li @click.prevent="deleteMenu"><a>메뉴 삭제</a></li>
            </ul>
        </div>
    </Modal>
</template>

<script>
import Modal from './Modal.vue'
import { mapMutations, mapActions } from 'vuex'

export default {
    components: {
            Modal
    },
    data() {
        return {
            input: '',
            valid: false
        }
    },
    watch: {
        input(v) {
            this.valid = v.trim().length > 0
        }
    },
    methods: {
        ...mapMutations([
            'SET_IS_MENU',
        ]),
        ...mapActions([
            'DELETE_MENU',
        ]),
        onClose() {
            this.$router.push("/menus")
        },
        deleteMenu() {
            this.DELETE_MENU({ id: this.$route.params.menuId })
            this.onClose()
        },
    }
}
</script>

<style>
.menu-options {
  list-style: none;
  padding-left: 0px;
}
.menu-options li {
  height: 18px;
  line-height: 18px;
  padding: 10px;
  margin: 5px;
  border-radius: 3px;
  font-size: 18px;
  font-weight: 700;
  color: #111;
  margin-bottom: 5px;
  cursor: pointer;
}
.menu-options li:hover,
.menu-options li:focus {
  background-color: rgba(0,0,0, .1);
}
.menu-options li a {
  text-decoration: none;
  color: inherit;
}
</style>