declare const _default: __VLS_WithTemplateSlots<typeof __VLS_component, ReturnType<typeof __VLS_template>>;
export default _default;
type __VLS_WithTemplateSlots<T, S> = T & (new () => {
    $slots: S;
});
declare const __VLS_component: import('vue').DefineComponent<{}, {
    resetScroll: typeof resetScroll;
    $emit: (event: "filter-updated", ...args: any[]) => void;
    variant: string;
    wrap: boolean;
    filterCategories: unknown[];
    activeFilter: string | unknown[];
    scrollButtons: boolean;
    scrollLeftText: string;
    scrollRightText: string;
    $props: {
        readonly variant?: string;
        readonly wrap?: boolean;
        readonly filterCategories?: unknown[];
        readonly activeFilter?: string | unknown[];
        readonly scrollButtons?: boolean;
        readonly scrollLeftText?: string;
        readonly scrollRightText?: string;
    };
}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
declare function __VLS_template(): {
    "fed-filter-header"?(_: {}): any;
};
declare function resetScroll(): void;
