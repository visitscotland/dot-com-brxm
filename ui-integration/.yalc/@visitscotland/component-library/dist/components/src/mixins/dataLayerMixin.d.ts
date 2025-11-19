export default dataLayerMixin;
declare namespace dataLayerMixin {
    namespace computed {
        let pageUrl: () => string;
    }
    function data(): {
        dataLayerLoadConfirmed: boolean;
        dataLayerStore: any;
    };
    function mounted(): void;
    namespace methods {
        function templateFiller(template: any, values: any): {};
        function createDataLayerObject(type: any, event: any, href: any): void;
        function returnIsoDate(): string;
        function pushToDataLayer(object: any): void;
        function compileFullTemplate(templateValues: any): any;
        function targetText(event: any): any;
    }
}
