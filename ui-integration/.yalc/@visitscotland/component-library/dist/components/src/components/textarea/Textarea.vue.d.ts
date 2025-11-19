declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Default value of the field
     */
    value: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Name of the field (for name and id attributes)
     */
    fieldName: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Name of the field (if different from ID)
     */
    name: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether the browser can autocomplete the field or not.
     */
    autoComplete: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Rules for Vuelidate plugin
     */
    validationRules: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Prop to define invalid from parent
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
    * Option to disable the input
    */
    disabled: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Prop to trigger manual validation. Used by a parent
     * component to trigger validation eg. when the submit
     * button is clicked.
     */
    triggerValidate: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Specific validation messages for different
     * types of validation
     */
    validationMessages: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Fallback translated validation - this is a set of
     * validation messages to be used when no specific
     * validation message is needed, eg. "This field is required"
     */
    genericValidation: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Content for hint text
     */
    hintText: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Element placeholder text
     */
    placeholder: {
        type: StringConstructor;
        default: string;
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
    /** The number of visible lines of text */
    rows: {
        type: StringConstructor | NumberConstructor;
        default: number;
    };
}>, {
    v$: import('vue').Ref<import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>, import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>>;
}, {
    inputVal: string;
    clearErrorsOnFocus: boolean;
}, {
    /**
     * element type class plus error classes
     */
    elementClass(): string;
}, {
    /**
     * Clear any text entered in the input
     */
    clearInput(): void;
    /**
     * Focus on the input
     */
    focusOnInput(): void;
    /**
     * Clears the input on button click
     * and adds focus back to the input
     */
    clearInputAndFocus(): void;
    /**
     *  return autocomplete value in appropriate places
     */
    autocompleteValue(): "on" | "off";
    /**
     * Validate errors on blur, and re-render them to the screen for screen
     * reader notice
     */
    validateErrors(): void;
    /**
     * Reset field errors on focus to allow screen reader to notice them if still
     * present on blur
     */
    resetErrors(): void;
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
}, import('vue').ComponentOptionsMixin, "updated"[], "updated", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Default value of the field
     */
    value: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Name of the field (for name and id attributes)
     */
    fieldName: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Name of the field (if different from ID)
     */
    name: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether the browser can autocomplete the field or not.
     */
    autoComplete: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Rules for Vuelidate plugin
     */
    validationRules: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Prop to define invalid from parent
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
    * Option to disable the input
    */
    disabled: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Prop to trigger manual validation. Used by a parent
     * component to trigger validation eg. when the submit
     * button is clicked.
     */
    triggerValidate: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Specific validation messages for different
     * types of validation
     */
    validationMessages: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Fallback translated validation - this is a set of
     * validation messages to be used when no specific
     * validation message is needed, eg. "This field is required"
     */
    genericValidation: {
        type: ObjectConstructor;
        default(): {};
    };
    /**
     * Content for hint text
     */
    hintText: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Element placeholder text
     */
    placeholder: {
        type: StringConstructor;
        default: string;
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
    /** The number of visible lines of text */
    rows: {
        type: StringConstructor | NumberConstructor;
        default: number;
    };
}>> & Readonly<{
    onUpdated?: (...args: any[]) => any;
}>, {
    name: string;
    value: string;
    disabled: boolean;
    invalid: boolean;
    validationRules: Record<string, any>;
    triggerValidate: boolean;
    validationMessages: Record<string, any>;
    genericValidation: Record<string, any>;
    reAlertErrors: boolean;
    hintText: string;
    placeholder: string;
    autoComplete: boolean;
    rows: string | number;
}, {}, {
    BFormTextarea: import('vue').DefineComponent<{
        number: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        required: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        name: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        trim: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        size: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
            default: undefined;
        };
        id: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        state: {
            type: import('vue').PropType<boolean>;
            default: null;
        };
        disabled: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        ariaInvalid: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
            default: undefined;
        };
        modelValue: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: string;
        };
        autofocus: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        form: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        lazy: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        autocomplete: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        formatter: {
            type: import('vue').PropType<(val: string, evt: Event) => string>;
            default: undefined;
        };
        lazyFormatter: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        list: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        placeholder: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        plaintext: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        readonly: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        debounce: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        debounceMaxWait: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        noResize: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        rows: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        wrap: {
            type: import('vue').PropType<string>;
            default: string;
        };
    }, {
        blur: () => void;
        element: import('vue').Ref<HTMLInputElement | null>;
        focus: () => void;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
        "update:modelValue": (val: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | null) => void;
    }, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        number: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        required: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        name: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        trim: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        size: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
            default: undefined;
        };
        id: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        state: {
            type: import('vue').PropType<boolean>;
            default: null;
        };
        disabled: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        ariaInvalid: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
            default: undefined;
        };
        modelValue: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: string;
        };
        autofocus: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        form: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        lazy: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        autocomplete: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        formatter: {
            type: import('vue').PropType<(val: string, evt: Event) => string>;
            default: undefined;
        };
        lazyFormatter: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        list: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        placeholder: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        plaintext: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        readonly: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        debounce: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        debounceMaxWait: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        noResize: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        rows: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        wrap: {
            type: import('vue').PropType<string>;
            default: string;
        };
    }>> & {
        "onUpdate:modelValue"?: ((val: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | null) => any) | undefined;
    }, {
        number: boolean;
        trim: boolean;
        name: string;
        id: string;
        ariaInvalid: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid;
        autofocus: boolean;
        disabled: boolean;
        form: string;
        plaintext: boolean;
        autocomplete: string;
        list: string;
        placeholder: string;
        required: boolean;
        size: keyof import('bootstrap-vue-next').BaseSize;
        state: boolean | null;
        formatter: (val: string, evt: Event) => string;
        lazy: boolean;
        lazyFormatter: boolean;
        modelValue: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | null;
        readonly: boolean;
        debounce: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        debounceMaxWait: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        wrap: string;
        noResize: boolean;
        rows: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
