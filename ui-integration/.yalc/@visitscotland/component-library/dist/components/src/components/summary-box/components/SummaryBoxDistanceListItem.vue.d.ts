declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The number of miles the summary box covers
     */
    miles: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The number of kilometres the summary box covers
     */
    kilometres: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The main label for the box, should generally be a
     * localised version of "Distance"
     */
    distanceLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The word for `miles` in the current language
     */
    milesLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The word for `kilometres` in the current language
     */
    kilometresLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether the summary box should default to miles
     * If false it will default to km
     */
    showMiles: {
        type: BooleanConstructor;
        default: boolean;
    };
}>, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The number of miles the summary box covers
     */
    miles: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The number of kilometres the summary box covers
     */
    kilometres: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The main label for the box, should generally be a
     * localised version of "Distance"
     */
    distanceLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The word for `miles` in the current language
     */
    milesLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The word for `kilometres` in the current language
     */
    kilometresLabel: {
        type: StringConstructor;
        default: string;
    };
    /**
     * Whether the summary box should default to miles
     * If false it will default to km
     */
    showMiles: {
        type: BooleanConstructor;
        default: boolean;
    };
}>> & Readonly<{}>, {
    miles: string;
    kilometres: string;
    distanceLabel: string;
    milesLabel: string;
    kilometresLabel: string;
    showMiles: boolean;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
