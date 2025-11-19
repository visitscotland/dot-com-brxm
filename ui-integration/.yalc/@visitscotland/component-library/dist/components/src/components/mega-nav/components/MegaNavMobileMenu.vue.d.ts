declare const _default: import('vue').DefineComponent<{}, {}, {}, {}, {
    /**
     * Submit event to dataLayer for tracking
     */
    dataLayerSubmit(text: any): void;
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
}, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
