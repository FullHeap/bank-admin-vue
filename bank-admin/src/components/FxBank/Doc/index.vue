<template>
  <div>
    <svg-icon icon-class="question" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'DocUrl',
  data() {
    return {
      url: 'https://github.com/FullHeap/ruoyi/tree/master/fxbank/doc'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>