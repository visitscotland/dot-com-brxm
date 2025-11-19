declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Name of the input
     */
    fieldName: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Value when checked
     */
    value: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Label for checkbox
     */
    label: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Rules for Vuelidate plugin
     */
    validationRules: {
        type: ObjectConstructor;
        default(): {};
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
     * Prop to define invalid from parent
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
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
     * Content for info text
     */
    infoText: {
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
    /**
     * Size of the checkbox
     * `sm|md|lg`.
     */
    size: {
        type: StringConstructor;
        default: any;
        validator: (value: unknown) => any;
    };
}>, {
    v$: import('vue').Ref<import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>, import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>>;
}, {
    inputVal: string;
    errors: any[];
}, {
    checkBoxClasses(): {
        'vs-checkbox': boolean;
        'vs-checkbox--error': any;
        'vs-checkbox--small': boolean;
    };
}, {}, {
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
}, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Name of the input
     */
    fieldName: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Value when checked
     */
    value: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Label for checkbox
     */
    label: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Rules for Vuelidate plugin
     */
    validationRules: {
        type: ObjectConstructor;
        default(): {};
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
     * Prop to define invalid from parent
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
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
     * Content for info text
     */
    infoText: {
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
    /**
     * Size of the checkbox
     * `sm|md|lg`.
     */
    size: {
        type: StringConstructor;
        default: any;
        validator: (value: unknown) => any;
    };
}>> & Readonly<{}>, {
    value: string;
    size: string;
    invalid: boolean;
    validationRules: Record<string, any>;
    triggerValidate: boolean;
    validationMessages: Record<string, any>;
    genericValidation: Record<string, any>;
    reAlertErrors: boolean;
    hintText: string;
    infoText: string;
}, {}, {
    BFormCheckbox: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue | import('bootstrap-vue-next').CheckboxValue[]>;
            indeterminate: import('vue').PropType<boolean>;
            reverse: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            name: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            ariaLabel: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            autofocus: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            button: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            disabled: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            form: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            switch: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            value: {
                type: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue>;
                default: boolean;
            };
            required: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            size: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                default: undefined;
            };
            plain: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            state: {
                type: import('vue').PropType<boolean | null>;
                default: null;
            };
            inline: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            ariaLabelledby: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            buttonGroup: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            buttonVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                default: null;
            };
            uncheckedValue: {
                type: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue>;
                default: boolean;
            };
        }>>, {
            blur: () => void;
            element: import('vue').Ref<HTMLElement | null>;
            focus: () => void;
        }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            reverse: boolean;
            name: string;
            id: string;
            ariaLabel: string;
            autofocus: boolean;
            button: boolean;
            disabled: boolean;
            form: string;
            switch: boolean;
            value: import('bootstrap-vue-next').CheckboxValue;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            plain: boolean;
            state: boolean | null;
            inline: boolean;
            ariaLabelledby: string;
            buttonGroup: boolean;
            buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
            uncheckedValue: import('bootstrap-vue-next').CheckboxValue;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue | import('bootstrap-vue-next').CheckboxValue[]>;
            indeterminate: import('vue').PropType<boolean>;
            reverse: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            name: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            id: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            ariaLabel: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            autofocus: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            button: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            disabled: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            form: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            switch: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            value: {
                type: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue>;
                default: boolean;
            };
            required: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            size: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                default: undefined;
            };
            plain: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            state: {
                type: import('vue').PropType<boolean | null>;
                default: null;
            };
            inline: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            ariaLabelledby: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            buttonGroup: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            buttonVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                default: null;
            };
            uncheckedValue: {
                type: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue>;
                default: boolean;
            };
        }>>, {
            blur: () => void;
            element: import('vue').Ref<HTMLElement | null>;
            focus: () => void;
        }, {}, {}, {}, {
            reverse: boolean;
            name: string;
            id: string;
            ariaLabel: string;
            autofocus: boolean;
            button: boolean;
            disabled: boolean;
            form: string;
            switch: boolean;
            value: import('bootstrap-vue-next').CheckboxValue;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            plain: boolean;
            state: boolean | null;
            inline: boolean;
            ariaLabelledby: string;
            buttonGroup: boolean;
            buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
            uncheckedValue: import('bootstrap-vue-next').CheckboxValue;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        modelValue: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue | import('bootstrap-vue-next').CheckboxValue[]>;
        indeterminate: import('vue').PropType<boolean>;
        reverse: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        name: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        id: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        ariaLabel: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        autofocus: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        button: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        disabled: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        form: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        switch: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        value: {
            type: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue>;
            default: boolean;
        };
        required: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        size: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
            default: undefined;
        };
        plain: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        state: {
            type: import('vue').PropType<boolean | null>;
            default: null;
        };
        inline: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        ariaLabelledby: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        buttonGroup: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        buttonVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
            default: null;
        };
        uncheckedValue: {
            type: import('vue').PropType<import('bootstrap-vue-next').CheckboxValue>;
            default: boolean;
        };
    }>>, {
        blur: () => void;
        element: import('vue').Ref<HTMLElement | null>;
        focus: () => void;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        reverse: boolean;
        name: string;
        id: string;
        ariaLabel: string;
        autofocus: boolean;
        button: boolean;
        disabled: boolean;
        form: string;
        switch: boolean;
        value: import('bootstrap-vue-next').CheckboxValue;
        required: boolean;
        size: keyof import('bootstrap-vue-next').BaseSize;
        plain: boolean;
        state: boolean | null;
        inline: boolean;
        ariaLabelledby: string;
        buttonGroup: boolean;
        buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
        uncheckedValue: import('bootstrap-vue-next').CheckboxValue;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
