<template>
    <div class="containers" style="display: flex;width:100%;height: 900px">
        <div class="canvas" style="width: 100%" ref="canvas" id="canvas"></div>
                <div id="properties"></div>
                <button @click="downloadXML">下载</button>
                <a hidden ref="downloadLink"></a>
    </div>
</template>

<script setup>
    import {getCurrentInstance, onMounted} from 'vue';

    const {proxy} = getCurrentInstance();
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
        // 获取到属性ref为“canvas”的dom节点
        const canvas = proxy.$refs.canvas
        // 建模
        bpmnModeler = new BpmnModeler({
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
        bpmnModeler.createDiagram(() => {
            console.log("=======")
            let canvas1 = bpmnModeler.get('canvas');
            // console.log("canvas1",canvas1)
            canvas1.zoom('fit-viewport');
        });
    })
    function downloadXML() {
        bpmnModeler.saveXML({format: true}, (err, xml) => {
            if (!err) {
                console.log(xml);
                // 获取文件名
                const name = 'diagram.bpmn';
                // 将文件名以及数据交给下载方法
                download({name: name, data: xml});
            }
        })
    }
    function download({name = "diagram.bpmn", data}) {
        // 这里就获取到了之前设置的隐藏链接
        const downloadLink = proxy.$refs.downloadLink;
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
</script>

<style scoped>

</style>
