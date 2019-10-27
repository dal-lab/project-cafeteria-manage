<template>
    <Modal>
        <div slot="header">
            <h2>
                새 메뉴 추가
                <a href="" class="modal-default-button" 
                    @click.prevent="SET_IS_ADD_MENU(false)">&times;</a>
            </h2>
        </div>
        <div slot="body">
            <form id="update-menu-form" 
                @submit="addMenu">
                <input class="form-control" type="text" v-model="input" name="menuName" ref="input">
            </form>
        </div>
        <div slot="footer">
            <button class="btn" :class="{'btn-success': valid}" type="submit" 
                form="add-menu-form" :disabled="!valid" @click.prevent="addMenu">
            메뉴 추가</button>
        </div>
    </Modal>
</template>

<script>
import { mapMutations, mapActions } from 'vuex'
import Modal from './Modal.vue'

export default {
    components: {
        Modal
    },
    data() {
        return {
            input: '',
            valid: false,
        }
    },
    watch: {
        input(v) {
            this.valid = v.trim().length > 0
        }
    },
    mounted() {
        this.$refs.input.focus()
    },
    methods: {
        ...mapMutations([
            'SET_IS_ADD_MENU',
        ]),
        ...mapActions([
            'ADD_MENU',
        ]),
        addMenu() {
            this.ADD_MENU({ menuName: this.input })
                .catch(err => console.log(err))
                .finally(() => this.SET_IS_ADD_MENU(false))
        }
    }
}
</script>

<style>

</style>