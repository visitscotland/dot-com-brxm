declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * The date range an event is occurring in, should contain a `startDay` as a
    * YYYY-MM-DD formatted String, and an optional `endDay` if on more than one
    * day.
    */
    period: {
        type: ObjectConstructor;
        default: any;
    };
    /**
    * A line of text that precedes the dates
    */
    label: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {
    datePeriod(): any;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * The date range an event is occurring in, should contain a `startDay` as a
    * YYYY-MM-DD formatted String, and an optional `endDay` if on more than one
    * day.
    */
    period: {
        type: ObjectConstructor;
        default: any;
    };
    /**
    * A line of text that precedes the dates
    */
    label: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    label: string;
    period: Record<string, any>;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
