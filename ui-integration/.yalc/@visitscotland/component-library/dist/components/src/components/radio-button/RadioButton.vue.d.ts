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
     * Whether the parent form has just been submitted, if so all errors
     * need to be wiped from then re-added to the DOM to inform screen
     * readers that they should be re-declared
     */
    reAlertErrors: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * An array of options that the user can select. Each should be an object with a `value`
     * string (that is submitted as part of the answer to the carbon calculation), a `text`
     * string which denotes what the answer represents, and an `icon` string which dictates
     * which icon is rendered on the radio button.
     */
    options: {
        type: ArrayConstructor;
        required: true;
    };
    /**
     * An optional string which provides the user further information about how to answer
     * the question. Appears below the radio button input.
     */
    hintText: {
        type: StringConstructor;
        default: string;
    };
}>, {
    v$: import('vue').Ref<import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>, import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>>;
}, {
    inputVal: string;
    clearErrorsOnFocus: boolean;
    checkedValue: string;
}, {
    /**
     * element type class plus error classes
     */
    elementClass(): string;
    showClearButton(): boolean;
    errorClass(): "" | "vs-input--error";
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
     * Validate errors on blur, and re-render them to the screen for screen
     * reader notice
     */
    validateErrors(): void;
    /**
     * Reset field errors on focus to allow screen reader to notice them if still
     * present on blur
     */
    resetErrors(): void;
    valueChanged(newData: any): void;
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
}, import('vue').ComponentOptionsMixin, ("updated" | "updateFieldData")[], "updated" | "updateFieldData", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
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
     * Whether the parent form has just been submitted, if so all errors
     * need to be wiped from then re-added to the DOM to inform screen
     * readers that they should be re-declared
     */
    reAlertErrors: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * An array of options that the user can select. Each should be an object with a `value`
     * string (that is submitted as part of the answer to the carbon calculation), a `text`
     * string which denotes what the answer represents, and an `icon` string which dictates
     * which icon is rendered on the radio button.
     */
    options: {
        type: ArrayConstructor;
        required: true;
    };
    /**
     * An optional string which provides the user further information about how to answer
     * the question. Appears below the radio button input.
     */
    hintText: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{
    onUpdated?: (...args: any[]) => any;
    onUpdateFieldData?: (...args: any[]) => any;
}>, {
    value: string;
    invalid: boolean;
    validationRules: Record<string, any>;
    triggerValidate: boolean;
    validationMessages: Record<string, any>;
    genericValidation: Record<string, any>;
    reAlertErrors: boolean;
    hintText: string;
}, {}, {
    BFormRadioGroup: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<import('bootstrap-vue-next').RadioValue>;
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
            ariaInvalid: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
                default: undefined;
            };
            autofocus: {
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
            required: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            size: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                default: string;
            };
            plain: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            state: {
                type: import('vue').PropType<boolean | null>;
                default: null;
            };
            options: {
                type: import('vue').PropType<readonly import('bootstrap-vue-next').RadioOptionRaw[]>;
                default: () => never[];
            };
            valueField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            textField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            htmlField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            disabledField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            buttons: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            validated: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            buttonVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                default: string;
            };
            stacked: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
        }>>, {
            blur: () => void;
            focus: () => void;
        }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            reverse: boolean;
            name: string;
            id: string;
            ariaInvalid: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid;
            autofocus: boolean;
            disabled: boolean;
            form: string;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            plain: boolean;
            state: boolean | null;
            options: readonly import('bootstrap-vue-next').RadioOptionRaw[];
            valueField: string;
            textField: string;
            htmlField: string;
            disabledField: string;
            buttons: boolean;
            validated: boolean;
            buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
            stacked: boolean;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<import('bootstrap-vue-next').RadioValue>;
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
            ariaInvalid: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
                default: undefined;
            };
            autofocus: {
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
            required: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            size: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                default: string;
            };
            plain: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            state: {
                type: import('vue').PropType<boolean | null>;
                default: null;
            };
            options: {
                type: import('vue').PropType<readonly import('bootstrap-vue-next').RadioOptionRaw[]>;
                default: () => never[];
            };
            valueField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            textField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            htmlField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            disabledField: {
                type: import('vue').PropType<string>;
                default: string;
            };
            buttons: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            validated: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            buttonVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                default: string;
            };
            stacked: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
        }>>, {
            blur: () => void;
            focus: () => void;
        }, {}, {}, {}, {
            reverse: boolean;
            name: string;
            id: string;
            ariaInvalid: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid;
            autofocus: boolean;
            disabled: boolean;
            form: string;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            plain: boolean;
            state: boolean | null;
            options: readonly import('bootstrap-vue-next').RadioOptionRaw[];
            valueField: string;
            textField: string;
            htmlField: string;
            disabledField: string;
            buttons: boolean;
            validated: boolean;
            buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
            stacked: boolean;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        modelValue: import('vue').PropType<import('bootstrap-vue-next').RadioValue>;
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
        ariaInvalid: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
            default: undefined;
        };
        autofocus: {
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
        required: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        size: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
            default: string;
        };
        plain: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        state: {
            type: import('vue').PropType<boolean | null>;
            default: null;
        };
        options: {
            type: import('vue').PropType<readonly import('bootstrap-vue-next').RadioOptionRaw[]>;
            default: () => never[];
        };
        valueField: {
            type: import('vue').PropType<string>;
            default: string;
        };
        textField: {
            type: import('vue').PropType<string>;
            default: string;
        };
        htmlField: {
            type: import('vue').PropType<string>;
            default: string;
        };
        disabledField: {
            type: import('vue').PropType<string>;
            default: string;
        };
        buttons: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        validated: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        buttonVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
            default: string;
        };
        stacked: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
    }>>, {
        blur: () => void;
        focus: () => void;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        reverse: boolean;
        name: string;
        id: string;
        ariaInvalid: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid;
        autofocus: boolean;
        disabled: boolean;
        form: string;
        required: boolean;
        size: keyof import('bootstrap-vue-next').BaseSize;
        plain: boolean;
        state: boolean | null;
        options: readonly import('bootstrap-vue-next').RadioOptionRaw[];
        valueField: string;
        textField: string;
        htmlField: string;
        disabledField: string;
        buttons: boolean;
        validated: boolean;
        buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
        stacked: boolean;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
            first?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
            first?: ((props: Record<string, never>) => any) | undefined;
        };
    });
    BFormRadio: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<import('bootstrap-vue-next').RadioValue | undefined>;
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
            value: {
                type: import('vue').PropType<import('bootstrap-vue-next').RadioValue>;
                default: boolean;
            };
            required: {
                type: import('vue').PropType<boolean>;
                default: boolean;
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
            value: import('bootstrap-vue-next').RadioValue;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            plain: boolean;
            state: boolean | null;
            inline: boolean;
            ariaLabelledby: string;
            buttonGroup: boolean;
            buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            modelValue: import('vue').PropType<import('bootstrap-vue-next').RadioValue | undefined>;
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
            value: {
                type: import('vue').PropType<import('bootstrap-vue-next').RadioValue>;
                default: boolean;
            };
            required: {
                type: import('vue').PropType<boolean>;
                default: boolean;
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
            value: import('bootstrap-vue-next').RadioValue;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            plain: boolean;
            state: boolean | null;
            inline: boolean;
            ariaLabelledby: string;
            buttonGroup: boolean;
            buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        modelValue: import('vue').PropType<import('bootstrap-vue-next').RadioValue | undefined>;
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
        value: {
            type: import('vue').PropType<import('bootstrap-vue-next').RadioValue>;
            default: boolean;
        };
        required: {
            type: import('vue').PropType<boolean>;
            default: boolean;
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
        value: import('bootstrap-vue-next').RadioValue;
        required: boolean;
        size: keyof import('bootstrap-vue-next').BaseSize;
        plain: boolean;
        state: boolean | null;
        inline: boolean;
        ariaLabelledby: string;
        buttonGroup: boolean;
        buttonVariant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
