declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Options for select element
     */
    options: {
        type: ArrayConstructor;
        required: true;
    };
    /**
     * Name of the field (for name and id attributes)
     */
    fieldName: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Default value of the field
     */
    value: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Rules for Vuelidate plugin
     */
    validationRules: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Prop to trigger manual validation
     */
    triggerValidate: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Prop to define invalid from parent
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Validation messages
     */
    validationMessages: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * URL for list of countries file - this will be used
     * if the 'country' prop is set to true
     */
    countryListUrl: {
        type: StringConstructor;
        default: string;
    };
    hintText: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether the options should be a countries list
     */
    countries: {
        type: BooleanConstructor;
        default: boolean;
    };
    genericValidation: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Whether the parent form has just been submitted, if so all errors
     * need to be wiped from then re-added to the DOM to inform screen
     * readers that they should be re-declared
     */
    reAlertErrors: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {
    v$: import('vue').Ref<import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>, import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>>;
}, {
    inputVal: string;
    touched: boolean;
    countryList: any[];
}, {
    errorClass(): "" | "vs-select__element--error";
    fieldOptions(): any[];
}, {
    autocompleteValue(fieldName: any): string;
}, {
    emits: string[];
    data(): {
        inputVal: any;
        isInvalid: any;
    };
    computed: {
        isRequired(): boolean;
        rules(): {
            inputVal: {};
        } | {
            inputVal?: undefined;
        };
        errorsList(): any[];
    };
    watch: {
        errorsList(newValue: any): void;
    };
    methods: {
        manualValidate(): void;
        emitStatus(): void;
    };
} | {
    computed: {
        ariaDescription(): string;
    };
}, import('vue').ComponentOptionsMixin, ("updated" | "toggleAction")[], "updated" | "toggleAction", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Options for select element
     */
    options: {
        type: ArrayConstructor;
        required: true;
    };
    /**
     * Name of the field (for name and id attributes)
     */
    fieldName: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Default value of the field
     */
    value: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Rules for Vuelidate plugin
     */
    validationRules: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Prop to trigger manual validation
     */
    triggerValidate: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Prop to define invalid from parent
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Validation messages
     */
    validationMessages: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * URL for list of countries file - this will be used
     * if the 'country' prop is set to true
     */
    countryListUrl: {
        type: StringConstructor;
        default: string;
    };
    hintText: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether the options should be a countries list
     */
    countries: {
        type: BooleanConstructor;
        default: boolean;
    };
    genericValidation: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Whether the parent form has just been submitted, if so all errors
     * need to be wiped from then re-added to the DOM to inform screen
     * readers that they should be re-declared
     */
    reAlertErrors: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{
    onUpdated?: (...args: any[]) => any;
    onToggleAction?: (...args: any[]) => any;
}>, {
    value: string;
    invalid: boolean;
    validationRules: Record<string, any>;
    triggerValidate: boolean;
    validationMessages: Record<string, any>;
    genericValidation: Record<string, any>;
    reAlertErrors: boolean;
    hintText: string;
    countryListUrl: string;
    countries: boolean;
}, {}, {
    BFormSelect: <T>(__VLS_props: {
        name?: string | undefined;
        id?: string | undefined;
        ariaInvalid?: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid | undefined;
        autofocus?: boolean | undefined;
        disabled?: boolean | undefined;
        form?: string | undefined;
        multiple?: boolean | undefined;
        required?: boolean | undefined;
        size?: keyof import('bootstrap-vue-next').BaseSize | undefined;
        plain?: boolean | undefined;
        state?: boolean | null | undefined;
        modelValue?: ((string | number | boolean | readonly unknown[] | Readonly<Record<string, unknown>> | null) & T) | undefined;
        options?: readonly unknown[] | undefined;
        valueField?: string | undefined;
        textField? /**
         * Options for select element
         */: string | undefined;
        htmlField?: string | undefined;
        disabledField?: string | undefined;
        optionsField?: string | undefined;
        labelField? /**
         * Name of the field (for name and id attributes)
         */: string | undefined;
        selectSize?: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | undefined;
    } & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps, __VLS_ctx?: {
        slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
            first?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
            first?: ((props: Record<string, never>) => any) | undefined;
        };
        attrs: any;
        emit: any;
    } | undefined, __VLS_expose?: ((exposed: import('vue').ShallowUnwrapRef<{
        blur: () => void;
        element: import('vue').Ref<HTMLElement | null>;
        focus: () => void;
    }>) => void) | undefined, __VLS_setup?: Promise<{
        props: {
            name?: string | undefined;
            id?: string | undefined;
            ariaInvalid? /**
             * Validation messages
             */: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid | undefined;
            autofocus?: boolean | undefined;
            disabled?: boolean | undefined;
            form?: string | undefined;
            multiple?: boolean | undefined;
            required?: boolean | undefined;
            size?: keyof import('bootstrap-vue-next').BaseSize | undefined;
            plain?: boolean | undefined;
            state?: boolean | null | undefined;
            modelValue?: ((string | number | boolean | readonly unknown[] | Readonly<Record<string, unknown>> | null) & T) | undefined;
            options?: readonly unknown[] | undefined;
            valueField? /**
             * Whether the options should be a countries list
             */: string | undefined;
            textField?: string | undefined;
            htmlField?: string | undefined;
            disabledField?: string | undefined;
            optionsField?: string | undefined;
            labelField?: string | undefined;
            selectSize?: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | undefined;
        } & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps;
        expose(exposed: import('vue').ShallowUnwrapRef<{
            blur: () => void;
            element: import('vue').Ref<HTMLElement | null>;
            focus: () => void;
        }>): void;
        attrs: any;
        slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
            first?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
            first?: ((props: Record<string, never>) => any) | undefined;
        };
        emit: any;
    }>) => import('vue').VNode<import('vue').RendererNode, import('vue').RendererElement, {
        [key: string]: any;
    }> & {
        __ctx?: {
            props: {
                name?: string | undefined;
                id?: string | undefined;
                ariaInvalid?: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid | undefined;
                autofocus?: boolean | undefined;
                disabled?: boolean | undefined;
                form?: string | undefined;
                multiple?: boolean | undefined;
                required?: boolean | undefined;
                size?: keyof import('bootstrap-vue-next').BaseSize | undefined;
                plain?: boolean | undefined;
                state?: boolean | null | undefined;
                modelValue?: ((string | number | boolean | readonly unknown[] | Readonly<Record<string, unknown>> | null) & T) | undefined;
                options?: readonly unknown[] | undefined;
                valueField?: string | undefined;
                textField?: string | undefined;
                htmlField?: string | undefined;
                disabledField?: string | undefined;
                optionsField?: string | undefined;
                labelField?: string | undefined;
                selectSize?: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | undefined;
            } & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps;
            expose(exposed: import('vue').ShallowUnwrapRef<{
                blur: () => void;
                element: import('vue').Ref<HTMLElement | null>;
                focus: () => void;
            }>): void;
            attrs: any;
            slots: Readonly<{
                default?: ((props: Record<string, never>) => any) | undefined;
                first?: ((props: Record<string, never>) => any) | undefined;
            }> & {
                default?: ((props: Record<string, never>) => any) | undefined;
                first?: ((props: Record<string, never>) => any) | undefined;
            };
            emit: any;
        } | undefined;
    };
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
