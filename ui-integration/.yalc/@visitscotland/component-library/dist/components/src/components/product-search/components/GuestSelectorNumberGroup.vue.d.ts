import { GuestNumberGroup } from '../../../types/types';

declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<__VLS_TypePropsToRuntimeProps<{
    adultCount: number;
    childrenCount: number;
    count: number;
    disabled: boolean;
    id: string;
    infantCount: number;
    inputType: GuestNumberGroup;
    maxValue: number;
    minValue: number;
    name: string;
    rowName: string;
    rowLabel: string;
    unitRemoved: boolean;
}>>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
    changeHandler: (inputName: string, inputValue: number, type: string, unitId: string) => void;
}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<__VLS_TypePropsToRuntimeProps<{
    adultCount: number;
    childrenCount: number;
    count: number;
    disabled: boolean;
    id: string;
    infantCount: number;
    inputType: GuestNumberGroup;
    maxValue: number;
    minValue: number;
    name: string;
    rowName: string;
    rowLabel: string;
    unitRemoved: boolean;
}>>> & Readonly<{
    onChangeHandler?: (inputName: string, inputValue: number, type: string, unitId: string) => any;
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
