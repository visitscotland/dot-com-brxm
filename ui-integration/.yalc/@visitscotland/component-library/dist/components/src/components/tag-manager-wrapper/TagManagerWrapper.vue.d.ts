declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Receive an external payload to be pushed through
     * the data layer by this component
     */
    payload: {
        type: ObjectConstructor;
        default: () => void;
    };
}>, {}, {}, {}, {
    /**
     * This function receives a payload as props
     * then replaces all "-" for "_" to match the keys on the templates from iProspect
     * and after that pushes all the key:values pairs to the store
     * so any component can retrieve it using the general getter function:
     * dataLayerStore.getValueFromKey("key_name")
     */
    processPayload(payload: any): void;
}, {
    computed: {
        pageUrl: () => string;
    };
    data(): {
        dataLayerLoadConfirmed: boolean;
        dataLayerStore: any;
    };
    mounted(): void;
    methods: {
        templateFiller(template: any, values: any): {};
        createDataLayerObject(type: any, event: any, href: any): void;
        returnIsoDate(): string;
        pushToDataLayer(object: any): void;
        compileFullTemplate(templateValues: any): any;
        targetText(event: any): any;
    };
}, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Receive an external payload to be pushed through
     * the data layer by this component
     */
    payload: {
        type: ObjectConstructor;
        default: () => void;
    };
}>> & Readonly<{}>, {
    payload: Record<string, any>;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
