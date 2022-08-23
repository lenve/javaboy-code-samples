<template>
  <div class="containers" style="display: flex;">
    <div class="canvas" ref="canvas"></div>
    <div id="properties"></div>
    <button @click="downloadXML">下载</button>
    <a hidden ref="downloadLink"></a>
  </div>
</template>

<script>
// 引入相关的依赖
import BpmnModeler from 'bpmn-js/lib/Modeler'
import {
  xmlStr
} from '../bpmn/01.js' // 这里是直接引用了xml字符串
import {
  BpmnPropertiesPanelModule,
  BpmnPropertiesProviderModule,
  CamundaPlatformPropertiesProviderModule
} from 'bpmn-js-properties-panel';
import CamundaExtensionModule from 'camunda-bpmn-moddle/lib';
import camundaModdleDescriptors from 'camunda-bpmn-moddle/resources/camunda';

export default {
  name: "Bpmn01",
  data() {
    return {
      // bpmn建模器
      bpmnModeler: null,
      container: null,
      canvas: null
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      // 获取到属性ref为“canvas”的dom节点
      const canvas = this.$refs.canvas
      // 建模
      this.bpmnModeler = new BpmnModeler({
        container: canvas, propertiesPanel: {
          parent: '#properties'
        },
        additionalModules: [
          BpmnPropertiesPanelModule,
          BpmnPropertiesProviderModule,
          CamundaPlatformPropertiesProviderModule,
          CamundaExtensionModule
        ],
        moddleExtensions: {
          camunda: camundaModdleDescriptors
        }
      })
      this.bpmnModeler.createDiagram(() => {
        this.bpmnModeler.get('canvas').zoom('fit-viewport');
      });
      // this.createNewDiagram()
    },
    createNewDiagram() {
      // 将字符串转换成图显示出来
      this.bpmnModeler.importXML(xmlStr, (err) => {
        if (err) {
          // console.error(err)
        } else {
          // 这里是成功之后的回调, 可以在这里做一系列事情
          // this.success()
        }
      })
    },
    downloadXML() {
      this.bpmnModeler.saveXML({format: true}, (err, xml) => {
        if (!err) {
          console.log(xml);
          // 获取文件名
          const name = 'diagram.bpmn';
          // 将文件名以及数据交给下载方法
          this.download({name: name, data: xml});
        }
      })
    },
    download({name = "diagram.bpmn", data}) {
      // 这里就获取到了之前设置的隐藏链接
      const downloadLink = this.$refs.downloadLink;
      // 把输就转换为URI，下载要用到的
      const encodedData = encodeURIComponent(data);

      if (data) {
        // 将数据给到链接
        downloadLink.href =
            "data:application/bpmn20-xml;charset=UTF-8," + encodedData;
        // 设置文件名
        downloadLink.download = name;
        // 触发点击事件开始下载
        downloadLink.click();
      }
    }
  }
}
</script>

<style scoped>
.containers {
  position: absolute;
  background-color: #ffffff;
  width: 100%;
  height: 100%;
}

.canvas {
  width: 100%;
  height: 100%;
}

.panel {
  position: absolute;
  right: 0;
  top: 0;
  width: 300px;
}
</style>
