<template>
    <Modal>
        <div slot="header">
            <h2>
                새 메뉴 추가
            </h2>
        </div>
        <div slot="body">
            <form id="add-menu-form" 
                @submit.prevent="addMenu">
                <input class="form-control" type="text" v-model="input" ref="input">
            </form>
        </div>
        <div slot="footer">
            <button class="btn" :class="{'btn-success': valid}" type="submit" 
                form="add-menu-form" :disabled="!valid">
            메뉴 추가</button>
        </div>
    </Modal>
</template>

<script>
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
    }
}
</script>

<style>

</style>