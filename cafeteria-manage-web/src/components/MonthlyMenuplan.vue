<template>
    <div>
        <div v-for="monthlyMenuPlan in monthlyMenuPlans" :key="monthlyMenuPlan.id">
            {{ monthlyMenuPlan.date }}
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
    data() {
        return {
            loading: false,
            month: this.$route.params.month
        }
    },
    created() {
        this.fetchData()
    },
    computed: {
        ...mapState({
            monthlyMenuPlans: 'monthlyMenuPlans'
        })
    },
    methods: {
        ...mapActions([
            'GET_MONTHLYMENUPLANS'
        ]),
        fetchData() {
            this.loading = true
            this.GET_MONTHLYMENUPLANS({ year: 2019, month: this.month }).finally(_ => {
                this.loading = false
            })
        }
    }
}
</script>

<style>

</style>