declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The heading level used for the heading.
     * `1|2|3|4|5|6`
     */
    level: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
        validator: (value: unknown) => any;
    };
    /**
     * The heading style used for the heading.
     * `display-m|display-s|heading-xl|
     * heading-l|heading-m|heading-s|heading-xs|heading-xxs`
     */
    headingStyle: {
        type: StringConstructor;
        required: true;
        validator: (value: unknown) => any;
    };
    /**
     * id string for h tag
     * typically — though not necessarily — used as page anchor
     */
    id: {
        type: StringConstructor;
        default: any;
    };
    /**
     * Option to remove margins from headings.
     * Useful when heading is used inside a component
     */
    noMargins: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {}, {
    headingClasses(): string[];
    type(): string;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The heading level used for the heading.
     * `1|2|3|4|5|6`
     */
    level: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
        validator: (value: unknown) => any;
    };
    /**
     * The heading style used for the heading.
     * `display-m|display-s|heading-xl|
     * heading-l|heading-m|heading-s|heading-xs|heading-xxs`
     */
    headingStyle: {
        type: StringConstructor;
        required: true;
        validator: (value: unknown) => any;
    };
    /**
     * id string for h tag
     * typically — though not necessarily — used as page anchor
     */
    id: {
        type: StringConstructor;
        default: any;
    };
    /**
     * Option to remove margins from headings.
     * Useful when heading is used inside a component
     */
    noMargins: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    id: string;
    level: string | number;
    noMargins: boolean;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
