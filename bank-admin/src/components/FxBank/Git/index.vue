<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'GitUrl',
  data() {
    return {
      url: 'https://github.com/FullHeap/ruoyi/tree/master/fxbank'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>