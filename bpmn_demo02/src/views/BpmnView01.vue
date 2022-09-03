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
  import bpmnModeler from "workflow-bpmn-modeler";

  export default {
    components: {
      bpmnModeler,
    },
    data() {
      return {
        xml: "", // 后端查询到的xml
        users: [
          { name: "javaboy", id: 1 },
          { name: "itboyhub", id: 2 },
          { name: "江南一点雨", id: 3 },
        ],
        groups: [
          { name: "经理", id: 4 },
          { name: "组长", id: 5 },
          { name: "员工", id: 6 },
        ],
        categorys: [
          { name: "OA", id: "oa" },
          { name: "财务", id: "finance" },
        ],
      };
    },
    methods: {
      getModelDetail() {
        // 发送请求，获取xml
        // this.xml = response.xml
      },
      save(data) {
        console.log(data);  // { process: {...}, xml: '...', svg: '...' }
      },
    },
  };
</script>
