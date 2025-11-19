declare const _default: __VLS_WithTemplateSlots<typeof __VLS_component, ReturnType<typeof __VLS_template>>;
export default _default;
type __VLS_WithTemplateSlots<T, S> = T & (new () => {
    $slots: S;
});
declare const __VLS_component: import('vue').DefineComponent<{}, {
    clearFilter: (inputField: any) => void;
    resetAll: () => void;
    resetOne: (filterId: any) => void;
    $emit: (event: "filter-updated", ...args: any[]) => void;
    filterId: string;
    filterLabel: string;
    $props: {
        readonly filterId?: string;
        readonly filterLabel?: string;
    };
}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
declare function __VLS_template(): {
    default?(_: {}): any;
    "no-js"?(_: {}): any;
};
