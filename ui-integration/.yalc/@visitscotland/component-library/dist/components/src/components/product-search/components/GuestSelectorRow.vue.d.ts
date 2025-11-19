import { GuestUnit } from '../../../types/types';

declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<__VLS_TypePropsToRuntimeProps<{
    name: string;
    showRemoveBtn: boolean;
    unitLabel: string;
    unitOptions: GuestUnit;
    unitRemoved: boolean;
}>>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
    numberChange: (type: string, groupName: string, unitId: string) => void;
    onRemove: (totalCount: number, childCount: number, unitId: string) => void;
}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<__VLS_TypePropsToRuntimeProps<{
    name: string;
    showRemoveBtn: boolean;
    unitLabel: string;
    unitOptions: GuestUnit;
    unitRemoved: boolean;
}>>> & Readonly<{
    onNumberChange?: (type: string, groupName: string, unitId: string) => any;
    onOnRemove?: (totalCount: number, childCount: number, unitId: string) => any;
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
