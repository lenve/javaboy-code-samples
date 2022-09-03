<template>
    <div>
        <div class="containers" style="display: flex;width:100%;height: 90vh">
            <div class="canvas" style="width: 100%" id="canvas"></div>
            <div id="properties">
            </div>
        </div>
        <div style="display:flex;justify-content: flex-end">
            <el-button @click="downloadXML" type="primary" :icon="Download">下载</el-button>
        </div>
    </div>
</template>

<script setup>
    import {Download} from '@element-plus/icons-vue'
    import {onMounted} from 'vue';

    import BpmnModeler from 'bpmn-js/lib/Modeler';
    import {
        BpmnPropertiesPanelModule,
        BpmnPropertiesProviderModule,
        CamundaPlatformPropertiesProviderModule
    } from 'bpmn-js-properties-panel';
    import CamundaExtensionModule from 'camunda-bpmn-moddle/lib';
    import camundaModdleDescriptors from 'camunda-bpmn-moddle/resources/camunda';

    let bpmnModeler;
    onMounted(() => {
        // 建模
        bpmnModeler = new BpmnModeler({
            container: '#canvas', propertiesPanel: {
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
        bpmnModeler.createDiagram();
    })

    function downloadXML() {
        bpmnModeler.saveXML({format: true}, (err, xml) => {
            if (!err) {
                console.log(xml);
                // 获取文件名
                const name = 'diagram.bpmn';
                // 把输就转换为URI，下载要用到的
                const encodedData = encodeURIComponent(xml);
                const downloadLink = document.createElement('a');
                if (xml) {
                    // 将数据给到链接
                    downloadLink.href =
                        "data:application/bpmn20-xml;charset=UTF-8," + encodedData;
                    // 设置文件名
                    downloadLink.download = name;
                    // 触发点击事件开始下载
                    downloadLink.click();
                }
            }
        })
    }
</script>

<style scoped>

</style>
