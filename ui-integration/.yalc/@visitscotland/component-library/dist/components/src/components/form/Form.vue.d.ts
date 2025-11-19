declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * If set to true, the form submits through the marketo library
     * using a hidden form. If false it submits using native browser
     * form logic.
     */
    isMarketo: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * For non-marketo forms this dictates which data processing layer the form uses - 'breg',
     * 'fepl', or 'dp'. This adjusts certain behaviour, for example the `exponea.identify()`
     * tracking is only desirable for breg forms.
     *
     * Defaults to breg as it is by far the most common form type, and from within the
     * freemarker sites we don't currently have access to the information to distinguish them.
     */
    submissionType: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The target url for the form, if not marketo
     */
    submitUrl: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The URL for the form data file
     */
    dataUrl: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Recaptcha site key string
     */
    recaptchaKey: {
        type: StringConstructor;
        default: any;
    };
    /**
     * Text for invisible recaptcha textarea - tells
     * screenreader users it's not needed
     */
    recaptchaTextareaLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * If the form should use sandbox or live Marketo details
     */
    isProd: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Munchkin ID for the Marketo environment
     */
    munchkinId: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Marketo instance URL for the form
     */
    marketoInstance: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Language indicator for content
     */
    language: {
        type: StringConstructor;
        default: string;
    };
    /**
     * URL for generic messaging config
     */
    messagingUrl: {
        type: StringConstructor;
        required: true;
    };
    /**
     * URL for generic messaging config
     */
    countryListUrl: {
        type: StringConstructor;
        default: string;
    };
    /**
     * We can't interact with recaptcha in the test environment and should
     * remove it from the form flow.
     */
    isTest: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {
    submitted: boolean;
    submitting: boolean;
    submitError: boolean;
    formData: {};
    messagingData: {};
    form: {};
    formIsInvalid: boolean;
    showErrorMessage: boolean;
    errorFields: any[];
    triggerValidate: boolean;
    recaptchaVerified: boolean;
    conditionalFields: {};
    inputVal: string;
    reAlertErrors: boolean;
    emailFieldName: string;
}, {
    formId(): any;
    showFormHeading(): boolean;
}, {
    /**
     * Called on component created. Loads the json file located at this.dataUrl which
     * contains all of the question data, localisations and validation.
     */
    getFormData(): void;
    /**
     * Called on component created. Loads the generic messaging files which provide
     * global validation and submission messages and localisations.
     */
    getGlobalMessaging(): void;
    /**
     * Retrieves the subset of info from a formdata json files which relates to the
     * current language. This is used in all translation functions to check if a
     * translation for a given string is provided.
     */
    getLanguageObj(): any;
    /**
     * Attempts to retrieve the label for a given field from the current language obj.
     * If no localisation is available, or the langauge is en, falls back to the default
     * label for the fieldname.
     */
    getTranslatedLabel(fieldName: any, index: any): string;
    /**
     * Attempts to retrieve the legend for a given field from the current language obj.
     * If no localisation is available, or the langauge is en, falls back to the default
     * legend for the fieldname.
     */
    getTranslatedLegend(fieldName: any, index: any): string;
    getTranslatedInfo(fieldName: any, index: any): string;
    /**
     * Attempts to retrieve the validation for a given field from the current language obj.
     * If no localisation is available, or the langauge is en, falls back to the default
     * validation for the fieldname.
     */
    getTranslatedValidation(fieldName: any, index: any): any;
    /**
     * Attempts to retrieve the options for a given select field from the current language
     * obj. If no localisation is available, or the langauge is en, falls back to the default
     * options for the fieldname.
     */
    getTranslatedOptions(fieldName: any, index: any): any;
    /**
     * Attempts to retrieve the hint for a given field from the current language obj.
     * If no localisation is available, or the langauge is en, falls back to the default
     * hint for the fieldname.
     */
    getTranslatedHint(fieldName: any, index: any): string;
    /**
     * Attempts to retrieve a localisation of a given piece of static content from
     * the current language obj. If no localisation is available, or the langauge is en,
     * falls back to the default english version of that content. If that is also not
     * provided, falls back to the generic content from the global messaging obj.
     */
    getTranslatedContent(type: any): any;
    /**
     * Retrieves the default value for a message from the global messages json file in the
     * current language.
     */
    getMessagingData(type: any, lang: any): any;
    /**
     * Collects all hidden input fields within the current element, parses their values,
     * and returns an object where each hidden input's name serves as a key and its parsed value
     * as the corresponding value. The function converts 'true' and 'false' strings to boolean
     * values, and retains other values as strings.
     *
     * @returns {Object<string, boolean|string>} - An object where:
     *   - `key`: The name of the hidden input field.
     *   - `value`: The parsed value of the input field, where 'true' becomes `true`,
     *     'false' becomes `false`, and other values remain as strings.
     */
    getHiddenFields(): {
        [x: string]: string | boolean;
    };
    /**
     * Parses the value of an input field, converting 'true' and 'false' string values to their
     * boolean primitive equivalents.
     *
     * @param {HTMLInputElement} inputField - The input field element to process. It must have a
     * `value` and `name` property.
     * @returns {{name: string, value: boolean|string}} - An object containing:
     *   - `name`: The name of the input field.
     *   - `value`: The parsed value of the input field. Returns `true` if the value is 'true',
     *     `false` if the value is 'false', and the original value otherwise.
     */
    parseBooleanStringsFromInputField(inputField: HTMLInputElement): {
        name: string;
        value: boolean | string;
    };
    /**
     * Returns true if a given value is undefined
     */
    isUndefined(value: any): boolean;
    /**
     * Called when the value of any input changes, updating values in the main this.form source
     * of truth. The new value is stored, validated, error statuses and conditional field
     * appearance is updated
     *
     * The inputs can't directly v-model variables from the this.form object because reactivity
     * on sub-variables on arrays passed through multiple components is inconsistent and
     * unreliable. Instead each component tracks its own value on an internal variable called
     * inputVal then reports any changes to that value back up to the form.
     */
    updateFieldData(data: any): void;
    /**
     * Updates the errorFields list with the current validated state of each field.
     */
    manageErrorStatus(field: any, errors: any): void;
    /**
     * Determines whether a given field should display the localised word "optional".
     */
    showOptionalText(field: any): boolean;
    /**
     * Determines whether or not an element should have a label with the bootstrap vue
     * component or if that should be handled separately.
     */
    needsLabel(field: any): boolean;
    /**
     * Interrupts form submission to ensure that manually triggered validation and recaptcha
     * verification has occurred. If successful, proceeds with submission.
     */
    preSubmit(e: any): void;
    /**
     * Invokes Marketo functionality to submit the form, along with recaptcha info, in the
     * correct format.
     */
    marketoSubmit(): void;
    /**
     * Submits the form using Axios, submitting a json object to the submitUrl
     */
    axiosSubmit(): void;
    /**
     * If exponea is present in the window (via gtm with accepted cookies), and the form uses
     * BREG, attach the current user to the record associated with their email address
     */
    attachEmail(): void;
    /**
     * Checks recaptcha response from the server
     */
    onRecaptchaVerify(): void;
    /**
     * Checks whether each conditional field in the formdata currently meets its condition,
     * and updates the status of those fields in this.conditionalFields.
     */
    checkConditionalFields(): void;
    /**
     * Sets the 'd-none' class on conditional fields which are currently not displaying.
     */
    conditionalElementClass(fieldName: any): "" | "d-none";
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
}, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * If set to true, the form submits through the marketo library
     * using a hidden form. If false it submits using native browser
     * form logic.
     */
    isMarketo: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * For non-marketo forms this dictates which data processing layer the form uses - 'breg',
     * 'fepl', or 'dp'. This adjusts certain behaviour, for example the `exponea.identify()`
     * tracking is only desirable for breg forms.
     *
     * Defaults to breg as it is by far the most common form type, and from within the
     * freemarker sites we don't currently have access to the information to distinguish them.
     */
    submissionType: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The target url for the form, if not marketo
     */
    submitUrl: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The URL for the form data file
     */
    dataUrl: {
        type: StringConstructor;
        required: true;
    };
    /**
     * Recaptcha site key string
     */
    recaptchaKey: {
        type: StringConstructor;
        default: any;
    };
    /**
     * Text for invisible recaptcha textarea - tells
     * screenreader users it's not needed
     */
    recaptchaTextareaLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * If the form should use sandbox or live Marketo details
     */
    isProd: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Munchkin ID for the Marketo environment
     */
    munchkinId: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Marketo instance URL for the form
     */
    marketoInstance: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Language indicator for content
     */
    language: {
        type: StringConstructor;
        default: string;
    };
    /**
     * URL for generic messaging config
     */
    messagingUrl: {
        type: StringConstructor;
        required: true;
    };
    /**
     * URL for generic messaging config
     */
    countryListUrl: {
        type: StringConstructor;
        default: string;
    };
    /**
     * We can't interact with recaptcha in the test environment and should
     * remove it from the form flow.
     */
    isTest: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    language: string;
    countryListUrl: string;
    isMarketo: boolean;
    submissionType: string;
    submitUrl: string;
    recaptchaKey: string;
    recaptchaTextareaLabel: string;
    isProd: boolean;
    munchkinId: string;
    marketoInstance: string;
    isTest: boolean;
}, {}, {
    VsInput: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        value: {
            type: StringConstructor;
            default: string;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        name: {
            type: StringConstructor;
            default: string;
        };
        type: {
            type: StringConstructor;
            default: string;
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
        disabled: {
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
        placeholder: {
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
        inputVal: string;
        clearErrorsOnFocus: boolean;
    }, {
        elementClass(): string;
        showClearButton(): boolean;
    }, {
        clearInput(): void;
        focusOnInput(): void;
        clearInputAndFocus(): void;
        autocompleteValue(fieldName: any): string;
        validateErrors(): void;
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
        value: {
            type: StringConstructor;
            default: string;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        name: {
            type: StringConstructor;
            default: string;
        };
        type: {
            type: StringConstructor;
            default: string;
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
        disabled: {
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
        placeholder: {
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
        type: string;
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
    VsSelect: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        options: {
            type: ArrayConstructor;
            required: true;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        value: {
            type: StringConstructor;
            default: string;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        countryListUrl: {
            type: StringConstructor;
            default: string;
        };
        hintText: {
            type: StringConstructor;
            default: string;
        };
        countries: {
            type: BooleanConstructor;
            default: boolean;
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
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
        options: {
            type: ArrayConstructor;
            required: true;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        value: {
            type: StringConstructor;
            default: string;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        countryListUrl: {
            type: StringConstructor;
            default: string;
        };
        hintText: {
            type: StringConstructor;
            default: string;
        };
        countries: {
            type: BooleanConstructor;
            default: boolean;
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
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
            textField?: string | undefined;
            htmlField?: string | undefined;
            disabledField?: string | undefined;
            optionsField?: string | undefined;
            labelField?: string | undefined;
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
                ariaInvalid?: import('node_modules/bootstrap-vue-next/dist/src/types').AriaInvalid | undefined;
                autofocus?: boolean | undefined;
                disabled?: boolean | undefined;
                form?: string | undefined;
                multiple?: boolean | undefined;
                required?: boolean | undefined;
                size?: keyof import('bootstrap-vue-next').BaseSize | undefined;
                plain? /**
                 * The target url for the form, if not marketo
                 */: boolean | undefined;
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
    VsCheckbox: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        value: {
            type: StringConstructor;
            default: string;
        };
        label: {
            type: StringConstructor;
            required: true;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
        hintText: {
            type: StringConstructor;
            default: string;
        };
        infoText: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
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
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        value: {
            type: StringConstructor;
            default: string;
        };
        label: {
            type: StringConstructor;
            required: true;
        };
        validationRules: {
            type: ObjectConstructor;
            default(): {};
        };
        triggerValidate: {
            type: BooleanConstructor;
            default: boolean;
        };
        validationMessages: {
            type: ObjectConstructor;
            default(): {};
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        genericValidation: {
            type: ObjectConstructor;
            default(): {};
        };
        hintText: {
            type: StringConstructor;
            default: string;
        };
        infoText: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
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
    VsRecaptcha: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        siteKey: {
            type: StringConstructor;
            default: string;
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        language: {
            type: StringConstructor;
            default: string;
        };
        errorMsg: {
            type: StringConstructor;
            default: string;
        };
        textareaLabel: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {}, {
        response: any;
        recaptchaSiteKey: string;
    }, {}, {
        verified(response: any): void;
        render(): void;
    }, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, "verified"[], "verified", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        siteKey: {
            type: StringConstructor;
            default: string;
        };
        invalid: {
            type: BooleanConstructor;
            default: boolean;
        };
        language: {
            type: StringConstructor;
            default: string;
        };
        errorMsg: {
            type: StringConstructor;
            default: string;
        };
        textareaLabel: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{
        onVerified?: (...args: any[]) => any;
    }>, {
        language: string;
        invalid: boolean;
        reAlertErrors: boolean;
        siteKey: string;
        errorMsg: string;
        textareaLabel: string;
    }, {}, {
        VueRecaptcha: typeof import('vue-recaptcha').VueRecaptcha;
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
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
    VsHeading: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        level: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        headingStyle: {
            type: StringConstructor;
            required: true;
            validator: (value: unknown) => any;
        };
        id: {
            type: StringConstructor;
            default: any;
        };
        noMargins: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {}, {}, {
        headingClasses(): string[];
        type(): string;
    }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        level: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        headingStyle: {
            type: StringConstructor;
            required: true;
            validator: (value: unknown) => any;
        };
        id: {
            type: StringConstructor;
            default: any;
        };
        noMargins: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{}>, {
        id: string;
        level: string | number;
        noMargins: boolean;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsWarning: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        icon: {
            type: StringConstructor;
            default: string;
        };
        type: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        size: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
    }>, {}, {}, {
        warningClasses(): string[];
        btnAttrs(): {
            class: string;
            size: string;
        };
    }, {
        manageCookies(): void;
    }, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        icon: {
            type: StringConstructor;
            default: string;
        };
        type: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        size: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
    }>> & Readonly<{}>, {
        type: string;
        icon: string;
        size: string;
    }, {}, {
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
        VsBody: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            noMargins: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>, {}, {}, {
            bodyClasses(): string[];
        }, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            noMargins: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>> & Readonly<{}>, {
            variant: string;
            noMargins: boolean;
        }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    VsTextarea: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        value: {
            type: StringConstructor;
            default: string;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        name: {
            type: StringConstructor;
            default: string;
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
        disabled: {
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
        placeholder: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
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
        elementClass(): string;
    }, {
        clearInput(): void;
        focusOnInput(): void;
        clearInputAndFocus(): void;
        autocompleteValue(): "on" | "off";
        validateErrors(): void;
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
        value: {
            type: StringConstructor;
            default: string;
        };
        fieldName: {
            type: StringConstructor;
            required: true;
        };
        name: {
            type: StringConstructor;
            default: string;
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
        disabled: {
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
        placeholder: {
            type: StringConstructor;
            default: string;
        };
        reAlertErrors: {
            type: BooleanConstructor;
            default: boolean;
        };
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
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
