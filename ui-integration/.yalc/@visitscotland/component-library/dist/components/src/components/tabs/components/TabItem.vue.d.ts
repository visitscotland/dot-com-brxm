declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Title of the tab button
     */
    title: {
        type: StringConstructor;
        required: true;
    };
}>, {}, {}, {}, {
    trackClick(event: any): void;
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
     * Title of the tab button
     */
    title: {
        type: StringConstructor;
        required: true;
    };
}>> & Readonly<{}>, {}, {}, {
    VsHeading: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        level: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        headingStyle: {
            type: StringConstructor;
            required: true;
            validator: (value: unknown) => any;
        };
        id: {
            type: StringConstructor;
            default: any;
        };
        noMargins: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {}, {}, {
        headingClasses(): string[];
        type(): string;
    }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        level: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        headingStyle: {
            type: StringConstructor;
            required: true;
            validator: (value: unknown) => any;
        };
        id: {
            type: StringConstructor;
            default: any;
        };
        noMargins: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{}>, {
        id: string;
        level: string | number;
        noMargins: boolean;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    BTab: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            active: import('vue').PropType<boolean>;
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            title: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            disabled: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            noBody: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            buttonId: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            lazyOnce: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            titleItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            titleLinkAttrs: {
                type: import('vue').PropType<Readonly<import('node_modules/bootstrap-vue-next/dist/src/types').AttrsValue>>;
                default: undefined;
            };
            titleLinkClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            tag: string;
            title: string;
            id: string;
            disabled: boolean;
            lazy: boolean;
            noBody: boolean;
            buttonId: string;
            lazyOnce: boolean;
            titleItemClass: any;
            titleLinkAttrs: Readonly<import('node_modules/bootstrap-vue-next/dist/src/types').AttrsValue>;
            titleLinkClass: any;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            active: import('vue').PropType<boolean>;
            tag: {
                type: import('vue').PropType<string>;
                default: string;
            };
            title: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            disabled: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            lazy: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            noBody: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            buttonId: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            lazyOnce: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            titleItemClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
            titleLinkAttrs: {
                type: import('vue').PropType<Readonly<import('node_modules/bootstrap-vue-next/dist/src/types').AttrsValue>>;
                default: undefined;
            };
            titleLinkClass: {
                type: import('vue').PropType<any>;
                default: undefined;
            };
        }>>, {}, {}, {}, {}, {
            tag: string;
            title: string;
            id: string;
            disabled: boolean;
            lazy: boolean;
            noBody: boolean;
            buttonId: string;
            lazyOnce: boolean;
            titleItemClass: any;
            titleLinkAttrs: Readonly<import('node_modules/bootstrap-vue-next/dist/src/types').AttrsValue>;
            titleLinkClass: any;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        active: import('vue').PropType<boolean>;
        tag: {
            type: import('vue').PropType<string>;
            default: string;
        };
        title: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        id: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        disabled: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        lazy: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        noBody: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        buttonId: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        lazyOnce: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        titleItemClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
        titleLinkAttrs: {
            type: import('vue').PropType<Readonly<import('node_modules/bootstrap-vue-next/dist/src/types').AttrsValue>>;
            default: undefined;
        };
        titleLinkClass: {
            type: import('vue').PropType<any>;
            default: undefined;
        };
    }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        tag: string;
        title: string;
        id: string;
        disabled: boolean;
        lazy: boolean;
        noBody: boolean;
        buttonId: string;
        lazyOnce: boolean;
        titleItemClass: any;
        titleLinkAttrs: Readonly<import('node_modules/bootstrap-vue-next/dist/src/types').AttrsValue>;
        titleLinkClass: any;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
            title?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
            title?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
