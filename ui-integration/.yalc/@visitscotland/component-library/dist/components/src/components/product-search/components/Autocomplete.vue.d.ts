declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<__VLS_TypePropsToRuntimeProps<{
    id: string;
    label: string;
    mode?: string;
    multiselectLabel?: string;
    name: string;
    options: any[];
    placeholder: string;
    trackBy?: string;
    isTourLocation?: boolean;
    defaultVal?: any;
}>>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
    changeValue: (payload: any) => void;
}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<__VLS_TypePropsToRuntimeProps<{
    id: string;
    label: string;
    mode?: string;
    multiselectLabel?: string;
    name: string;
    options: any[];
    placeholder: string;
    trackBy?: string;
    isTourLocation?: boolean;
    defaultVal?: any;
}>>> & Readonly<{
    onChangeValue?: (payload: any) => any;
}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
type __VLS_NonUndefinedable<T> = T extends undefined ? never : T;
type __VLS_TypePropsToRuntimeProps<T> = {
    [K in keyof T]-?: {} extends Pick<T, K> ? {
        type: import('vue').PropType<__VLS_NonUndefinedable<T[K]>>;
    } : {
        type: import('vue').PropType<T[K]>;
        required: true;
    };
};
