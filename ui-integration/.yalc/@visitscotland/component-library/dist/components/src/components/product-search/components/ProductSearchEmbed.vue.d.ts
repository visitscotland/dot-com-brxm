declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    defaultProd: {
        type: StringConstructor;
        default: string;
        validator(value: string): boolean;
    };
    defaultLocale: {
        type: StringConstructor;
        default: string;
        validator(value: string): boolean;
    };
    defaultLocation: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {}, {
    trackSubmit(type: any, location: any): void;
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
    defaultProd: {
        type: StringConstructor;
        default: string;
        validator(value: string): boolean;
    };
    defaultLocale: {
        type: StringConstructor;
        default: string;
        validator(value: string): boolean;
    };
    defaultLocation: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    defaultProd: string;
    defaultLocale: string;
    defaultLocation: string;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
