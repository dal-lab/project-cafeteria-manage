<template>
    <Modal>
        <div slot="header">
            <h2>
                새 식단 추가
                <a href="" class="modal-default-button" 
                    @click.prevent="SET_IS_ADD_MENUPLAN(false)">&times;</a>
            </h2>
        </div>
        <div slot="body">
            <form id="update-menu-form" 
                @submit="addMenuplan">
                <input class="form-control" type="text" v-model="input" name="workMonth" ref="input">
            </form>
        </div>
        <div slot="footer">
            <button class="btn" :class="{'btn-success': valid}" type="submit" 
                form="add-menu-form" :disabled="!valid" @click.prevent="addMenuplan">
            식단 추가</button>
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
            'SET_IS_ADD_MENUPLAN'
        ]),
        ...mapActions([
            'ADD_MENUPLAN',
        ]),
        addMenuplan() {
            this.ADD_MENUPLAN({ menuPlanMonth: this.input })
                .catch(err => console.log(err))
                .finally(() => this.SET_IS_ADD_MENUPLAN(false))
        }
    }
}
</script>

<style>

</style>