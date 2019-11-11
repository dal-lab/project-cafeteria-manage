<template>
    <div>
        {{month}}
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    data() {
        return {
            month: this.$route.params.month
        }
    }
}
</script>

<style>

</style>