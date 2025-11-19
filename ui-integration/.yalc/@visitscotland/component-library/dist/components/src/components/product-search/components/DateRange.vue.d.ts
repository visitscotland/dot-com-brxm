declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    startLabel: {
        type: StringConstructor;
        required: false;
        default: string;
    };
    endLabel: {
        type: StringConstructor;
        required: false;
        default: string;
    };
    defaultDates: {
        type: BooleanConstructor;
        default: boolean;
    };
    selectedProd: {
        type: StringConstructor;
        required: true;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
    dateUpdated: (...args: any[]) => void;
}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    startLabel: {
        type: StringConstructor;
        required: false;
        default: string;
    };
    endLabel: {
        type: StringConstructor;
        required: false;
        default: string;
    };
    defaultDates: {
        type: BooleanConstructor;
        default: boolean;
    };
    selectedProd: {
        type: StringConstructor;
        required: true;
    };
}>> & Readonly<{
    onDateUpdated?: (...args: any[]) => any;
}>, {
    startLabel: string;
    endLabel: string;
    defaultDates: boolean;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
