declare const _default: __VLS_WithTemplateSlots<typeof __VLS_component, ReturnType<typeof __VLS_template>>;
export default _default;
type __VLS_WithTemplateSlots<T, S> = T & (new () => {
    $slots: S;
});
declare const __VLS_component: import('vue').DefineComponent<{}, {
    siteLanguage: string;
    cludoCustomerId: number;
    cludoEngineId: number;
    errorMessages: Record<string, any>;
    fromText: string;
    paginationLabels: Record<string, any>;
    searchLabels: Record<string, any>;
    sortLabels: Record<string, any>;
    cardCategoryLabels: Record<string, any>;
    filters?: unknown[];
    eventsApi?: string;
    cludoApiKey?: string;
    subFilters?: unknown[];
    $props: {
        readonly siteLanguage?: string;
        readonly cludoCustomerId?: number;
        readonly cludoEngineId?: number;
        readonly errorMessages?: Record<string, any>;
        readonly fromText?: string;
        readonly paginationLabels?: Record<string, any>;
        readonly searchLabels?: Record<string, any>;
        readonly sortLabels?: Record<string, any>;
        readonly cardCategoryLabels?: Record<string, any>;
        readonly filters?: unknown[];
        readonly eventsApi?: string;
        readonly cludoApiKey?: string;
        readonly subFilters?: unknown[];
    };
}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
declare function __VLS_template(): Partial<Record<`federated-search__spotlight-${any}`, (_: {}) => any>>;
