<template>
    <!--  <img alt="Vue logo" src="./assets/logo.png">-->
    <!--  <HelloWorld msg="Welcome to Your Vue.js App"/>-->
<!--  <Bpmn01></Bpmn01>-->
    <BpmnView></BpmnView>
</template>

<script>
    import HelloWorld from './components/HelloWorld.vue'
    import BpmnView from './components/BpmnView.vue'
    import Bpmn01 from "./components/Bpmn01";

    export default {
        name: 'App',
        components: {
            HelloWorld,
            Bpmn01,
            BpmnView
        }
    }
</script>
