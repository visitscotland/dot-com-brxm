import { VueRecaptcha } from 'vue-recaptcha';
declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Recaptcha site key string
     */
    siteKey: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether or not the form is invalid - this should
     * also only be true when an attempt at submission has
     * been made
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Language to show text in
     */
    language: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Validation message if the recaptcha isn't completed
     */
    errorMsg: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Text for invisible recaptcha textarea - tells
     * screenreader users it's not needed
     */
    textareaLabel: {
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
}>, {}, {
    response: any;
    recaptchaSiteKey: string;
}, {}, {
    verified(response: any): void;
    render(): void;
}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, "verified"[], "verified", import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Recaptcha site key string
     */
    siteKey: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether or not the form is invalid - this should
     * also only be true when an attempt at submission has
     * been made
     */
    invalid: {
        type: BooleanConstructor;
        default: boolean;
    };
    /**
     * Language to show text in
     */
    language: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Validation message if the recaptcha isn't completed
     */
    errorMsg: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Text for invisible recaptcha textarea - tells
     * screenreader users it's not needed
     */
    textareaLabel: {
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
    VueRecaptcha: typeof VueRecaptcha;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
