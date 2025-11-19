declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The localised main text of the question that prompts the user to answer
     */
    label: {
        type: StringConstructor;
        required: true;
    };
    labelFor: {
        type: StringConstructor;
        required: true;
    };
    /**
     * The localised hint text of the question that clarifies the answer expected
     * from the user
     */
    hint: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Any additional styling  classes that should be applied to the question
     * as a whole
     */
    fieldClass: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Which selector type should be used for the question.
     * `radio|number`.
     */
    fieldType: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
     * The identifier for the field which should be provided when updating the
     * value of the field to the calculator as a whole
     */
    fieldName: {
        type: StringConstructor;
        required: true;
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
     * Minimum number value, only needed for number inputs
     */
    minimum: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * Maximum number value, only needed for number inputs
     */
    maximum: {
        type: NumberConstructor;
        default: number;
    };
}>, {}, {}, {}, {
    /**
     * Fired when the value of the nested input changes, emits the new value up to the
     * questionnaire to be tracked in the running total.
    */
    valueChanged(newData: any): void;
}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, "updateFieldData"[], "updateFieldData", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The localised main text of the question that prompts the user to answer
     */
    label: {
        type: StringConstructor;
        required: true;
    };
    labelFor: {
        type: StringConstructor;
        required: true;
    };
    /**
     * The localised hint text of the question that clarifies the answer expected
     * from the user
     */
    hint: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Any additional styling  classes that should be applied to the question
     * as a whole
     */
    fieldClass: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Which selector type should be used for the question.
     * `radio|number`.
     */
    fieldType: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
     * The identifier for the field which should be provided when updating the
     * value of the field to the calculator as a whole
     */
    fieldName: {
        type: StringConstructor;
        required: true;
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
     * Minimum number value, only needed for number inputs
     */
    minimum: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * Maximum number value, only needed for number inputs
     */
    maximum: {
        type: NumberConstructor;
        default: number;
    };
}>> & Readonly<{
    onUpdateFieldData?: (...args: any[]) => any;
}>, {
    hint: string;
    fieldType: string;
    minimum: number;
    maximum: number;
}, {}, {
    BFormGroup: import('vue').DefineComponent<{
        ariaInvalid: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
            default: undefined;
        };
        contentCols: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsLg: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsMd: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsSm: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsXl: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        description: {
            type: StringConstructor[];
            default: undefined;
        };
        disabled: {
            type: BooleanConstructor;
            default: boolean;
        };
        feedbackAriaLive: {
            type: StringConstructor;
            default: string;
        };
        id: {
            type: StringConstructor;
            default: undefined;
        };
        invalidFeedback: {
            type: StringConstructor;
            default: undefined;
        };
        label: {
            type: StringConstructor;
            default: undefined;
        };
        labelAlign: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignLg: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignMd: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignSm: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignXl: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelClass: {
            type: (ObjectConstructor | StringConstructor | ArrayConstructor)[];
            default: undefined;
        };
        labelCols: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsLg: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsMd: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsSm: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsXl: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelFor: {
            type: StringConstructor;
            default: undefined;
        };
        labelSize: {
            type: StringConstructor;
            default: undefined;
        };
        labelSrOnly: {
            type: BooleanConstructor;
            default: boolean;
        };
        state: {
            type: import('vue').PropType<boolean | null>;
            default: null;
        };
        tooltip: {
            type: BooleanConstructor;
            default: boolean;
        };
        validFeedback: {
            type: StringConstructor;
            default: undefined;
        };
        validated: {
            type: BooleanConstructor;
            default: boolean;
        };
        floating: {
            type: BooleanConstructor;
            default: boolean;
        };
    }, {
        ariaDescribedby: string | null;
        computedAriaInvalid: import('vue').ComputedRef<"grammar" | "spelling" | "true" | "false" | undefined>;
        contentColProps: import('vue').ComputedRef<any>;
        isHorizontal: import('vue').ComputedRef<boolean>;
        labelAlignClasses: import('vue').ComputedRef<string[]>;
        labelColProps: import('vue').ComputedRef<any>;
        onLegendClick: (event: Readonly<MouseEvent>) => void;
        stateClass: import('vue').ComputedRef<"is-valid" | "is-invalid" | null>;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        ariaInvalid: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid>;
            default: undefined;
        };
        contentCols: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsLg: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsMd: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsSm: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        contentColsXl: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        description: {
            type: StringConstructor[];
            default: undefined;
        };
        disabled: {
            type: BooleanConstructor;
            default: boolean;
        };
        feedbackAriaLive: {
            type: StringConstructor;
            default: string;
        };
        id: {
            type: StringConstructor;
            default: undefined;
        };
        invalidFeedback: {
            type: StringConstructor;
            default: undefined;
        };
        label: {
            type: StringConstructor;
            default: undefined;
        };
        labelAlign: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignLg: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignMd: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignSm: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelAlignXl: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelClass: {
            type: (ObjectConstructor | StringConstructor | ArrayConstructor)[];
            default: undefined;
        };
        labelCols: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsLg: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsMd: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsSm: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelColsXl: {
            type: (BooleanConstructor | StringConstructor | NumberConstructor)[];
            default: undefined;
        };
        labelFor: {
            type: StringConstructor;
            default: undefined;
        };
        labelSize: {
            type: StringConstructor;
            default: undefined;
        };
        labelSrOnly: {
            type: BooleanConstructor;
            default: boolean;
        };
        state: {
            type: import('vue').PropType<boolean | null>;
            default: null;
        };
        tooltip: {
            type: BooleanConstructor;
            default: boolean;
        };
        validFeedback: {
            type: StringConstructor;
            default: undefined;
        };
        validated: {
            type: BooleanConstructor;
            default: boolean;
        };
        floating: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>>, {
        id: string;
        ariaInvalid: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid;
        disabled: boolean;
        label: string;
        description: string;
        state: boolean | null;
        tooltip: boolean;
        floating: boolean;
        validated: boolean;
        labelFor: string;
        labelClass: string | Record<string, any> | unknown[];
        contentCols: string | number | boolean;
        contentColsLg: string | number | boolean;
        contentColsMd: string | number | boolean;
        contentColsSm: string | number | boolean;
        contentColsXl: string | number | boolean;
        feedbackAriaLive: string;
        invalidFeedback: string;
        labelAlign: string | number | boolean;
        labelAlignLg: string | number | boolean;
        labelAlignMd: string | number | boolean;
        labelAlignSm: string | number | boolean;
        labelAlignXl: string | number | boolean;
        labelCols: string | number | boolean;
        labelColsLg: string | number | boolean;
        labelColsMd: string | number | boolean;
        labelColsSm: string | number | boolean;
        labelColsXl: string | number | boolean;
        labelSize: string;
        labelSrOnly: boolean;
        validFeedback: string;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsRadioButton: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        value: {
            type: StringConstructor;
            default: string;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
        options: {
            type: ArrayConstructor;
            required: true;
        };
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
        elementClass(): string;
        showClearButton(): boolean;
        errorClass(): "" | "vs-input--error";
    }, {
        clearInput(): void;
        focusOnInput(): void;
        validateErrors(): void;
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
        value: {
            type: StringConstructor;
            default: string;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
        options: {
            type: ArrayConstructor;
            required: true;
        };
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
    VsNumberInput: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        value: {
            type: NumberConstructor;
            default: number;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        minimumNumber: {
            type: NumberConstructor;
            default: number;
        };
        maximumNumber: {
            type: NumberConstructor;
            default: number;
        };
        autoComplete: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
        hintText: {
            type: StringConstructor;
            default: string;
        };
        clearButtonText: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {
        v$: import('vue').Ref<import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>, import('@vuelidate/core').Validation<import('@vuelidate/core').ValidationArgs<unknown>, unknown>>;
    }, {
        inputVal: number;
        clearErrorsOnFocus: boolean;
    }, {
        elementClass(): string;
        showClearButton(): boolean;
        errorClass(): "" | "vs-input--error";
    }, {
        clearInput(): void;
        focusOnInput(): void;
        clearInputAndFocus(): void;
        controlBlurred(): void;
        validateErrors(): void;
        resetErrors(): void;
        decrementValue(event: any): void;
        incrementValue(event: any): void;
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
        value: {
            type: NumberConstructor;
            default: number;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        minimumNumber: {
            type: NumberConstructor;
            default: number;
        };
        maximumNumber: {
            type: NumberConstructor;
            default: number;
        };
        autoComplete: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
        hintText: {
            type: StringConstructor;
            default: string;
        };
        clearButtonText: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{
        onUpdated?: (...args: any[]) => any;
    }>, {
        value: number;
        invalid: boolean;
        validationRules: Record<string, any>;
        triggerValidate: boolean;
        validationMessages: Record<string, any>;
        genericValidation: Record<string, any>;
        reAlertErrors: boolean;
        hintText: string;
        minimumNumber: number;
        maximumNumber: number;
        autoComplete: boolean;
        clearButtonText: string;
    }, {}, {
        BFormInput: import('vue').DefineComponent<{
            number: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            type: {
                type: import('vue').PropType<import('bootstrap-vue-next').InputType>;
                default: string;
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
            min: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            max: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            step: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
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
            type: {
                type: import('vue').PropType<import('bootstrap-vue-next').InputType>;
                default: string;
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
            min: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            max: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            step: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
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
        }>> & {
            "onUpdate:modelValue"?: ((val: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | null) => any) | undefined;
        }, {
            number: boolean;
            type: import('bootstrap-vue-next').InputType;
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
            max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            min: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            placeholder: string;
            required: boolean;
            size: keyof import('bootstrap-vue-next').BaseSize;
            step: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            state: boolean | null;
            formatter: (val: string, evt: Event) => string;
            lazy: boolean;
            lazyFormatter: boolean;
            modelValue: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish | null;
            readonly: boolean;
            debounce: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            debounceMaxWait: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
        VsButton: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            href: {
                type: StringConstructor;
                default: any;
            };
            tabindex: {
                type: StringConstructor;
                default: any;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            size: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            animate: {
                type: BooleanConstructor;
                default: boolean;
            };
            rounded: {
                type: BooleanConstructor;
                default: boolean;
            };
            icon: {
                type: StringConstructor;
                default: string;
            };
            iconOnly: {
                type: BooleanConstructor;
                default: boolean;
            };
            iconPosition: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
        }>, {}, {
            isAnimating: boolean;
        }, {
            buttonClasses(): {
                'vs-button--animated': boolean;
                'vs-button--is-animating': boolean;
                'vs-button--rounded': boolean;
                'vs-button--icon-only': boolean;
                'vs-button--flex-reverse': boolean;
            }[];
            iconClasses(): {
                'vs-icon--right': boolean;
                'vs-icon--left': boolean;
            }[];
        }, {
            animateHandler(event: any): void;
            tabbedIn(event: any): void;
            trackLink(event: any): void;
        }, {
            computed: {
                pageUrl: () => string;
            };
            data(): {
                dataLayerLoadConfirmed: boolean;
                dataLayerStore: any;
            };
            mounted(): void;
            methods: {
                templateFiller(template: any, values: any): {};
                createDataLayerObject(type: any, event: any, href: any): void;
                returnIsoDate(): string;
                pushToDataLayer(object: any): void;
                compileFullTemplate(templateValues: any): any;
                targetText(event: any): any;
            };
        }, import('vue').ComponentOptionsMixin, "btnFocus"[], "btnFocus", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            href: {
                type: StringConstructor;
                default: any;
            };
            tabindex: {
                type: StringConstructor;
                default: any;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            size: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            animate: {
                type: BooleanConstructor;
                default: boolean;
            };
            rounded: {
                type: BooleanConstructor;
                default: boolean;
            };
            icon: {
                type: StringConstructor;
                default: string;
            };
            iconOnly: {
                type: BooleanConstructor;
                default: boolean;
            };
            iconPosition: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
        }>> & Readonly<{
            onBtnFocus?: (...args: any[]) => any;
        }>, {
            icon: string;
            variant: string;
            size: string;
            href: string;
            tabindex: string;
            animate: boolean;
            rounded: boolean;
            iconOnly: boolean;
            iconPosition: string;
        }, {}, {
            BButton: {
                new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
                    pressed: import('vue').PropType<boolean | undefined>;
                    tag: {
                        type: import('vue').PropType<string>;
                        default: string;
                    };
                    type: {
                        type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                        default: string;
                    };
                    replace: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    target: {
                        type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                        default: undefined;
                    };
                    to: {
                        type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                        default: undefined;
                    };
                    append: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    disabled: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    href: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    rel: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    size: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                        default: string;
                    };
                    active: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    activeClass: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    routerComponentName: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    variant: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                        default: string;
                    };
                    opacity: {
                        type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    opacityHover: {
                        type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    underlineVariant: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant | null>;
                        default: undefined;
                    };
                    underlineOffset: {
                        type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                        default: undefined;
                    };
                    underlineOffsetHover: {
                        type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                        default: undefined;
                    };
                    underlineOpacity: {
                        type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    underlineOpacityHover: {
                        type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    pill: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    icon: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    routerTag: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    exactActiveClass: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    loading: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    loadingFill: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    loadingText: {
                        type: import('vue').PropType<string>;
                        default: string;
                    };
                    squared: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                }>> & {
                    onClick?: ((value: MouseEvent) => any) | undefined;
                }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
                    click: (value: MouseEvent) => void;
                }, import('vue').PublicProps, {
                    tag: string;
                    type: import('bootstrap-vue-next').ButtonType;
                    replace: boolean;
                    target: import('bootstrap-vue-next').LinkTarget;
                    to: import('vue-router').RouteLocationRaw;
                    append: boolean;
                    disabled: boolean;
                    href: string;
                    rel: string;
                    size: keyof import('bootstrap-vue-next').BaseSize;
                    active: boolean;
                    activeClass: string;
                    routerComponentName: string;
                    variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                    opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                    underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
                    underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
                    underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    pill: boolean;
                    icon: boolean;
                    routerTag: string;
                    exactActiveClass: string;
                    loading: boolean;
                    loadingFill: boolean;
                    loadingText: string;
                    squared: boolean;
                }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
                    P: {};
                    B: {};
                    D: {};
                    C: {};
                    M: {};
                    Defaults: {};
                }, Readonly<import('vue').ExtractPropTypes<{
                    pressed: import('vue').PropType<boolean | undefined>;
                    tag: {
                        type: import('vue').PropType<string>;
                        default: string;
                    };
                    type: {
                        type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                        default: string;
                    };
                    replace: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    target: {
                        type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                        default: undefined;
                    };
                    to: {
                        type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                        default: undefined;
                    };
                    append: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    disabled: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    href: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    rel: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    size: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                        default: string;
                    };
                    active: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    activeClass: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    routerComponentName: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    variant: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                        default: string;
                    };
                    opacity: {
                        type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    opacityHover: {
                        type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    underlineVariant: {
                        type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant | null>;
                        default: undefined;
                    };
                    underlineOffset: {
                        type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                        default: undefined;
                    };
                    underlineOffsetHover: {
                        type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                        default: undefined;
                    };
                    underlineOpacity: {
                        type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    underlineOpacityHover: {
                        type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                        default: undefined;
                    };
                    pill: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    icon: {
                        type: import('vue').PropType<boolean>;
                        default: undefined;
                    };
                    routerTag: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    exactActiveClass: {
                        type: import('vue').PropType<string>;
                        default: undefined;
                    };
                    loading: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    loadingFill: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                    loadingText: {
                        type: import('vue').PropType<string>;
                        default: string;
                    };
                    squared: {
                        type: import('vue').PropType<boolean>;
                        default: boolean;
                    };
                }>> & {
                    onClick?: ((value: MouseEvent) => any) | undefined;
                }, {}, {}, {}, {}, {
                    tag: string;
                    type: import('bootstrap-vue-next').ButtonType;
                    replace: boolean;
                    target: import('bootstrap-vue-next').LinkTarget;
                    to: import('vue-router').RouteLocationRaw;
                    append: boolean;
                    disabled: boolean;
                    href: string;
                    rel: string;
                    size: keyof import('bootstrap-vue-next').BaseSize;
                    active: boolean;
                    activeClass: string;
                    routerComponentName: string;
                    variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                    opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                    underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
                    underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
                    underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                    pill: boolean;
                    icon: boolean;
                    routerTag: string;
                    exactActiveClass: string;
                    loading: boolean;
                    loadingFill: boolean;
                    loadingText: string;
                    squared: boolean;
                }>;
                __isFragment?: never;
                __isTeleport?: never;
                __isSuspense?: never;
            } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
                pressed: import('vue').PropType<boolean | undefined>;
                tag: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                type: {
                    type: import('vue').PropType<import('bootstrap-vue-next').ButtonType>;
                    default: string;
                };
                replace: {
                    type: import('vue').PropType<boolean>;
                    default: undefined;
                };
                target: {
                    type: import('vue').PropType<import('bootstrap-vue-next').LinkTarget>;
                    default: undefined;
                };
                to: {
                    type: import('vue').PropType<import('vue-router').RouteLocationRaw>;
                    default: undefined;
                };
                append: {
                    type: import('vue').PropType<boolean>;
                    default: undefined;
                };
                disabled: {
                    type: import('vue').PropType<boolean>;
                    default: undefined;
                };
                href: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                rel: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                size: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseSize>;
                    default: string;
                };
                active: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                activeClass: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                routerComponentName: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                variant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseButtonVariant | null>;
                    default: string;
                };
                opacity: {
                    type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                    default: undefined;
                };
                opacityHover: {
                    type: import('vue').PropType<10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                    default: undefined;
                };
                underlineVariant: {
                    type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant | null>;
                    default: undefined;
                };
                underlineOffset: {
                    type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                    default: undefined;
                };
                underlineOffsetHover: {
                    type: import('vue').PropType<1 | 2 | 3 | "1" | "2" | "3">;
                    default: undefined;
                };
                underlineOpacity: {
                    type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                    default: undefined;
                };
                underlineOpacityHover: {
                    type: import('vue').PropType<0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100">;
                    default: undefined;
                };
                pill: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                icon: {
                    type: import('vue').PropType<boolean>;
                    default: undefined;
                };
                routerTag: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                exactActiveClass: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                loading: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                loadingFill: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                loadingText: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                squared: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
            }>> & {
                onClick?: ((value: MouseEvent) => any) | undefined;
            }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {
                click: (value: MouseEvent) => void;
            }, string, {
                tag: string;
                type: import('bootstrap-vue-next').ButtonType;
                replace: boolean;
                target: import('bootstrap-vue-next').LinkTarget;
                to: import('vue-router').RouteLocationRaw;
                append: boolean;
                disabled: boolean;
                href: string;
                rel: string;
                size: keyof import('bootstrap-vue-next').BaseSize;
                active: boolean;
                activeClass: string;
                routerComponentName: string;
                variant: keyof import('bootstrap-vue-next').BaseButtonVariant | null;
                opacity: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                opacityHover: 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                underlineVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
                underlineOffset: 1 | 2 | 3 | "1" | "2" | "3";
                underlineOffsetHover: 1 | 2 | 3 | "1" | "2" | "3";
                underlineOpacity: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                underlineOpacityHover: 0 | "0" | 10 | "10" | 100 | 50 | 25 | 75 | "25" | "50" | "75" | "100";
                pill: boolean;
                icon: boolean;
                routerTag: string;
                exactActiveClass: string;
                loading: boolean;
                loadingFill: boolean;
                loadingText: string;
                squared: boolean;
            }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
                $slots: Readonly<{
                    default?: ((props: Record<string, never>) => any) | undefined;
                    loading?: ((props: Record<string, never>) => any) | undefined;
                    'loading-spinner'?: ((props: Record<string, never>) => any) | undefined;
                }> & {
                    default?: ((props: Record<string, never>) => any) | undefined;
                    loading?: ((props: Record<string, never>) => any) | undefined;
                    'loading-spinner'?: ((props: Record<string, never>) => any) | undefined;
                };
            });
            VsIcon: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
                icon: {
                    type: StringConstructor;
                    required: true;
                };
                variant: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                customColour: {
                    type: StringConstructor;
                    default: any;
                };
                size: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                smallSize: {
                    type: StringConstructor;
                    default: any;
                    validator: (value: unknown) => any;
                };
            }>, {}, {
                tokens: {
                    "vs-color-background-primary": string;
                    "vs-color-background-secondary": string;
                    "vs-color-background-bold": string;
                    "vs-color-background-brand": string;
                    "vs-color-background-inverse": string;
                    "vs-color-background-highlight": string;
                    "vs-color-background-information": string;
                    "vs-color-background-success": string;
                    "vs-color-background-warning": string;
                    "vs-color-background-error": string;
                    "vs-color-background-accent-heather-80": string;
                    "vs-color-background-accent-heather-30": string;
                    "vs-color-background-accent-tolsta-40": string;
                    "vs-color-background-accent-gorse-05": string;
                    "vs-color-border-primary": string;
                    "vs-color-border-secondary": string;
                    "vs-color-border-inverse": string;
                    "vs-color-border-input": string;
                    "vs-color-border-highlight": string;
                    "vs-color-border-bold": string;
                    "vs-color-border-success": string;
                    "vs-color-border-warning": string;
                    "vs-color-border-error": string;
                    "vs-color-border-disabled": string;
                    "vs-color-icon-primary": string;
                    "vs-color-icon-secondary": string;
                    "vs-color-icon-tertiary": string;
                    "vs-color-icon-inverse": string;
                    "vs-color-icon-cta-on-light": string;
                    "vs-color-icon-highlight": string;
                    "vs-color-icon-success": string;
                    "vs-color-icon-warning": string;
                    "vs-color-icon-error": string;
                    "vs-color-icon-disabled": string;
                    "vs-color-icon-accent-saltire-30": string;
                    "vs-color-icon-ski-easy": string;
                    "vs-color-icon-ski-intermediate": string;
                    "vs-color-icon-ski-difficult": string;
                    "vs-color-icon-ski-very-difficult": string;
                    "vs-color-icon-ski-itineraries": string;
                    "vs-color-icon-ski-other": string;
                    "vs-color-icon-accent-loch-ness": string;
                    "vs-color-icon-accent-grey-02": string;
                    "vs-color-icon-accent-saltire": string;
                    "vs-color-icon-accent-bramble": string;
                    "vs-color-icon-accent-heather": string;
                    "vs-color-icon-accent-whisky": string;
                    "vs-color-icon-accent-cranachan": string;
                    "vs-color-icon-accent-buachaille": string;
                    "vs-color-interaction-focus": string;
                    "vs-color-interaction-focus-on-bold": string;
                    "vs-color-interaction-cta-primary": string;
                    "vs-color-interaction-cta-secondary": string;
                    "vs-color-interaction-cta-hover": string;
                    "vs-color-interaction-cta-pressed": string;
                    "vs-color-interaction-cta-disabled": string;
                    "vs-color-interaction-cta-subtle-hover": string;
                    "vs-color-interaction-cta-subtle-pressed": string;
                    "vs-color-interaction-cta-subtle": string;
                    "vs-color-interaction-link-primary": string;
                    "vs-color-interaction-link-secondary": string;
                    "vs-color-interaction-link-active": string;
                    "vs-color-interaction-link-visited": string;
                    "vs-color-interaction-link-disabled": string;
                    "vs-color-interaction-link-visited-on-bold": string;
                    "vs-color-text-primary": string;
                    "vs-color-text-secondary": string;
                    "vs-color-text-tertiary": string;
                    "vs-color-text-inverse": string;
                    "vs-color-text-cta-on-light": string;
                    "vs-color-text-highlight": string;
                    "vs-color-text-brand": string;
                    "vs-color-text-error": string;
                    "vs-color-text-success": string;
                    "vs-color-text-disabled": string;
                    breakpoint_xl: string;
                    grid_container_width_xl: string;
                    breakpoint_md: string;
                    max_container_width_xl: string;
                    grid_container_width_md: string;
                    breakpoint_sm: string;
                    breakpoint_lg: string;
                    breakpoint_xs: string;
                    max_container_width_md: string;
                    grid_container_width_sm: string;
                    grid_container_width_lg: string;
                    max_container_width_sm: string;
                    max_container_width_lg: string;
                    max_container_width_xxl: string;
                    grid_container_width_xxl: string;
                    breakpoint_xxl: string;
                    grid_columns: string;
                    grid_gutter_width: string;
                    "vs-spacer-0": string;
                    "vs-spacer-0125": string;
                    "vs-spacer-025": string;
                    "vs-spacer-050": string;
                    "vs-spacer-075": string;
                    "vs-spacer-100": string;
                    "vs-spacer-125": string;
                    "vs-spacer-150": string;
                    "vs-spacer-175": string;
                    "vs-spacer-200": string;
                    "vs-spacer-250": string;
                    "vs-spacer-300": string;
                    "vs-spacer-400": string;
                    "vs-spacer-500": string;
                    "vs-spacer-600": string;
                    "vs-spacer-700": string;
                    "vs-radius-medium": string;
                    "vs-radius-none": string;
                    "vs-radius-large-increased": string;
                    "vs-radius-extra-large-increased": string;
                    "vs-radius-tiny": string;
                    "vs-radius-large": string;
                    "vs-radius-extra-large": string;
                    "vs-radius-extra-extra-large": string;
                    "vs-radius-huge": string;
                    "vs-radius-full": string;
                    "vs-radius-small": string;
                    "vs-elevation-shadow-raised": string;
                    "vs-elevation-shadow-overlay": string;
                    "vs-elevation-surface": string;
                    "vs-elevation-surface-section": string;
                    "vs-elevation-surface-raised": string;
                    "vs-elevation-surface-overlay": string;
                    "vs-focus-shadow": string;
                    "vs-focus-shadow-on-dark": string;
                    "vs-border-width-sm": string;
                    "vs-border-width-md": string;
                    "vs-font-size-display-m": string;
                    "vs-font-size-display-s": string;
                    "vs-font-size-heading-xl": string;
                    "vs-font-size-heading-l": string;
                    "vs-font-size-heading-m": string;
                    "vs-font-size-heading-s": string;
                    "vs-font-size-heading-xs": string;
                    "vs-font-size-heading-xxs": string;
                    "vs-font-size-heading-xxxs": string;
                    "vs-font-size-detail-l": string;
                    "vs-font-size-detail-m": string;
                    "vs-font-size-detail-s": string;
                    "vs-font-size-body-l": string;
                    "vs-font-size-body-m": string;
                    "vs-font-size-body-s": string;
                    "vs-font-weight-strong": string;
                    "vs-font-weight-medium": string;
                    "vs-font-weight-regular": string;
                    "vs-font-weight-subtle": string;
                    "vs-font-weight-heading": string;
                    "vs-font-weight-body": string;
                    "vs-font-weight-detail": string;
                    "vs-font-family-sans-serif": string;
                    "vs-font-family-display": string;
                    "vs-line-height-heading": number;
                    "vs-line-height-detail": number;
                    "vs-line-height-body": number;
                    "vs-letter-spacing-display": string;
                    "vs-letter-spacing-heading": string;
                    "vs-letter-spacing-detail": string;
                    "vs-letter-spacing-body": string;
                    icon_size_xxs: string;
                    icon_size_xs: string;
                    icon_size_sm: string;
                    icon_size_md: string;
                    icon_size_lg: string;
                    icon_size_xl: string;
                    "vs-icon-facility-audio-loop": string;
                    "vs-icon-control-collapse": string;
                    "vs-icon-facility-wheelchair-access": string;
                    "vs-icon-control-pause": string;
                    "vs-icon-facility-cafe": string;
                    "vs-icon-feedback-error": string;
                    "vs-icon-facility-accessible-parking": string;
                    "vs-icon-control-filters": string;
                    "vs-icon-feedback-information": string;
                    "vs-icon-control-external-link": string;
                    "vs-icon-control-play": string;
                    "vs-icon-facility-accessible-dining": string;
                    "vs-icon-facility-public-transport": string;
                    "vs-icon-season-winter": string;
                    "vs-icon-control-expand": string;
                    "vs-icon-facility-pets-welcome": string;
                    "vs-icon-facility-parking": string;
                    "vs-icon-control-menu": string;
                    "vs-icon-season-spring": string;
                    "vs-icon-season-summer": string;
                    "vs-icon-facility-breakfast": string;
                    "vs-icon-control-back-to-top": string;
                    "vs-icon-control-dismiss": string;
                    "vs-icon-feedback-warning": string;
                    "vs-icon-facility-accessible-shower": string;
                    "vs-icon-facility-wifi": string;
                    "vs-icon-control-search": string;
                    "vs-icon-control-download": string;
                    "vs-icon-season-autumn": string;
                    "vs-icon-facility-accessible-toilet": string;
                    "vs-icon-feedback-success": string;
                    "vs-icon-facility-vegan": string;
                    "font-size-1": string;
                    "font-size-2": string;
                    "font-size-3": string;
                    "font-size-4": string;
                    "font-size-5": string;
                    "font-size-6": string;
                    "font-size-7": string;
                    "font-size-8": string;
                    "font-size-9": string;
                    "font-size-10": string;
                    "font-size-body": string;
                    "font-size-body-md": string;
                    "font-size-lead": string;
                    "font-size-lead-md": string;
                    "font-size-teaser": string;
                    opacity_100: string;
                    opacity_50: string;
                    opacity_0: string;
                    duration_quickly: string;
                    duration_base: string;
                    duration_slowly: string;
                    no_javascript: string;
                    theme_dark: string;
                    theme_grey: string;
                };
            }, {
                iconClasses(): any[];
                fontAwesomeClasses(): any;
                iconStyles(): {
                    color: string;
                };
            }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
                icon: {
                    type: StringConstructor;
                    required: true;
                };
                variant: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                customColour: {
                    type: StringConstructor;
                    default: any;
                };
                size: {
                    type: StringConstructor;
                    default: string;
                    validator: (value: unknown) => any;
                };
                smallSize: {
                    type: StringConstructor;
                    default: any;
                    validator: (value: unknown) => any;
                };
            }>> & Readonly<{}>, {
                variant: string;
                customColour: string;
                size: string;
                smallSize: string;
            }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
        }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
