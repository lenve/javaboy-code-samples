<template>
  <div>
    <bpmn-modeler
            ref="refNode"
            :xml="xml"
            :users="users"
            :groups="groups"
            :categorys="categorys"
            :is-view="false"
            @save="save"
    />
  </div>
</template>

<script>
  import bpmnModeler from "muheflow-bpmn-modeler";

  export default {
    components: {
      bpmnModeler,
    },
    data() {
      return {
        xml: "", // Query the xml
        users: [
          { name: "javaboy", id: "1" },
          { name: "itboyhub", id: "2" },
          { name: "江南一点雨", id: "3" },
        ],
        groups: [
          { name: "经理", id: "4" },
          { name: "组长", id: "5" },
          { name: "员工", id: "6" },
        ],
        categorys: [
          { name: "UniversalType", id: "7" },
          { name: "OAType", id: "8" },
        ],
      };
    },
    methods: {
      getModelDetail() {
        // Send request to get xml
        // this.xml = response.xml
      },
      save(data) {
        console.log(data);  // { process: {...}, xml: '...', svg: '...' }
      },
    },
  };
</script>
