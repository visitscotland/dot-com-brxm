declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The total equivalent weight of emissions for the survey, used as the headline figure
     * as well as for working out daily emissions and in the comparison.
     */
    totalKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The weight of emissions specifically related to food
     */
    foodKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The weight of emissions specifically related to transport
     */
    transportKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The weight of emissions specifically related to accommodation
     */
    accommodationKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The number of nights the user is staying, used to calculate per day emissions
     */
    stayDuration: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * An array that defines which placeholders in the comparison need to be replaced and what
     * they should be replaced with. Each entry should be an object with a "repl" key and a
     * "divisor" key
     *
     * {
     *      "repl": "xxx",
     *      "divisor": 0.2
     * }
     *
     * In the above case, the substring "xxx" in the comparison will be replaced with the total
     * emissions number / 0.2
     */
    comparisonReplacements: {
        type: ArrayConstructor;
        default: () => any[];
    };
    /**
     * Language locale string. Used to determine correct formatting for numbers (decimal
     * delimeter)
     *
     * Example: "en-gb"
     */
    language: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {
    chartDirection: import('vue').Ref<string, string>;
    chartAxis: import('vue').Ref<{
        primary: {
            type: string;
        };
    }, {
        primary: {
            type: string;
        };
    } | {
        primary: {
            type: string;
        };
    }>;
    cssProps: string;
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
    /**
     * Data object for the vue3-chart
     */
    chartData(): {
        name: any;
        emissions: number;
    }[];
    /**
     * Returns the localised comparison from the labelsmap with actual data interpolated into
     * it. The comparison is a string with one or more placeholders in it which need to be
     * replaced with a number specific to the user's actual data (in the general form of
     * "that's equivalent to 15 full washloads").
     *
     * The exact string that needs to be replaced and the number that should be entered is
     * derived from the comparison replacements object described in props.
     */
    interpolComparison(): any;
    /**
     * Interpolates the per day emissions into a localised string.
     */
    interpolKGsPerDay(): any;
    /**
     * Interpolates the per day success into a localised string.
     */
    interpolPerDaySuccess(): any;
    transportPercent(): number;
    accommodationPercent(): number;
    foodPercent(): number;
    totalPerDay(): number;
}, {
    responsiveMargin(width: any): {
        left: number;
        top: number;
        right: number;
        bottom: number;
    };
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
     * The total equivalent weight of emissions for the survey, used as the headline figure
     * as well as for working out daily emissions and in the comparison.
     */
    totalKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The weight of emissions specifically related to food
     */
    foodKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The weight of emissions specifically related to transport
     */
    transportKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The weight of emissions specifically related to accommodation
     */
    accommodationKilos: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * The number of nights the user is staying, used to calculate per day emissions
     */
    stayDuration: {
        type: NumberConstructor;
        default: number;
    };
    /**
     * An array that defines which placeholders in the comparison need to be replaced and what
     * they should be replaced with. Each entry should be an object with a "repl" key and a
     * "divisor" key
     *
     * {
     *      "repl": "xxx",
     *      "divisor": 0.2
     * }
     *
     * In the above case, the substring "xxx" in the comparison will be replaced with the total
     * emissions number / 0.2
     */
    comparisonReplacements: {
        type: ArrayConstructor;
        default: () => any[];
    };
    /**
     * Language locale string. Used to determine correct formatting for numbers (decimal
     * delimeter)
     *
     * Example: "en-gb"
     */
    language: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    language: string;
    totalKilos: number;
    foodKilos: number;
    transportKilos: number;
    accommodationKilos: number;
    stayDuration: number;
    comparisonReplacements: unknown[];
}, {}, {
    VsCol: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {
        BCol: import('vue').DefineComponent<{
            alignSelf: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentVertical | "auto">;
                default: null;
            };
            tag: {
                type: StringConstructor;
                default: string;
            };
            order: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            offset: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            col: {
                type: BooleanConstructor;
                default: boolean;
            };
            cols: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
        }, {
            computedClasses: import('vue').ComputedRef<(string[] | {
                [x: string]: boolean;
                col: boolean;
            })[]>;
        }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            alignSelf: {
                type: import('vue').PropType<import('bootstrap-vue-next').AlignmentVertical | "auto">;
                default: null;
            };
            tag: {
                type: StringConstructor;
                default: string;
            };
            order: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            offset: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
            col: {
                type: BooleanConstructor;
                default: boolean;
            };
            cols: {
                type: (StringConstructor | NumberConstructor)[];
                default: null;
            };
        }>>, {
            tag: string;
            cols: string | number;
            col: boolean;
            offset: string | number;
            order: string | number;
            alignSelf: import('bootstrap-vue-next').AlignmentVertical | "auto";
        }, import('vue').SlotsType<{
            default?: Record<string, never> | undefined;
        }>, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    }, {}, string, () => {
        cols: unknown;
        sm: unknown;
        md: unknown;
        lg: unknown;
        xl: unknown;
    }, true, {}, any>;
    VsRow: import('vue').DefineComponent<{}, {}, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<{}> & Readonly<{}>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
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
    Chart: import('vue').DefineComponent<{
        data: {
            type: () => import('vue3-charts/dist/types').Data[];
            default: () => never[];
        };
        margin: {
            type: () => import('vue3-charts/dist/types').Margin;
            default: () => {
                top: number;
                right: number;
                bottom: number;
                left: number;
            };
            required: false;
        };
        size: {
            type: () => import('vue3-charts/dist/types').Size;
            default: () => {
                width: number;
                height: number;
            };
            required: false;
        };
        direction: {
            type: () => import('vue3-charts/dist/types').Direction;
            default: string;
            required: false;
        };
        axis: {
            type: () => import('vue3-charts/dist/types').ChartAxis;
            required: false;
        };
        config: {
            type: () => Partial<import('vue3-charts/dist/types').ChartConfig>;
            default: () => {};
        };
    }, {
        axBottomEl: import('vue').Ref<{
            $el: SVGGElement;
        } | undefined>;
        axLeftEl: import('vue').Ref<{
            $el: SVGGElement;
        } | undefined>;
        chartEl: import('vue').Ref<null>;
        hideX: import('vue').ComputedRef<boolean | undefined>;
        hideY: import('vue').ComputedRef<boolean | undefined>;
        rotateX: import('vue').ComputedRef<boolean | undefined>;
        onMouseMove: (e: MouseEvent) => void;
        onMouseOut: () => void;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps, Readonly<import('vue').ExtractPropTypes<{
        data: {
            type: () => import('vue3-charts/dist/types').Data[];
            default: () => never[];
        };
        margin: {
            type: () => import('vue3-charts/dist/types').Margin;
            default: () => {
                top: number;
                right: number;
                bottom: number;
                left: number;
            };
            required: false;
        };
        size: {
            type: () => import('vue3-charts/dist/types').Size;
            default: () => {
                width: number;
                height: number;
            };
            required: false;
        };
        direction: {
            type: () => import('vue3-charts/dist/types').Direction;
            default: string;
            required: false;
        };
        axis: {
            type: () => import('vue3-charts/dist/types').ChartAxis;
            required: false;
        };
        config: {
            type: () => Partial<import('vue3-charts/dist/types').ChartConfig>;
            default: () => {};
        };
    }>>, {
        data: import('vue3-charts/dist/types').Data[];
        margin: import('vue3-charts/dist/types').Margin;
        size: import('vue3-charts/dist/types').Size;
        direction: import('vue3-charts/dist/types').Direction;
        config: Partial<import('vue3-charts/dist/types').ChartConfig>;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    Grid: import('vue').DefineComponent<{
        strokeDasharray: {
            type: StringConstructor;
            default: () => string;
        };
        hideX: {
            type: BooleanConstructor;
            default: boolean;
        };
        hideY: {
            type: BooleanConstructor;
            default: boolean;
        };
        center: {
            type: BooleanConstructor;
            default: boolean;
        };
    }, {
        data: import('vue').Ref<import('vue3-charts/dist/types').Data[]>;
        canvas: import('vue').Ref<{
            x: number;
            y: number;
            width: number;
            height: number;
        } | null>;
        xLines: import('vue').Ref<number[]>;
        yLines: import('vue').Ref<number[]>;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps, Readonly<import('vue').ExtractPropTypes<{
        strokeDasharray: {
            type: StringConstructor;
            default: () => string;
        };
        hideX: {
            type: BooleanConstructor;
            default: boolean;
        };
        hideY: {
            type: BooleanConstructor;
            default: boolean;
        };
        center: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>>, {
        strokeDasharray: string;
        hideX: boolean;
        hideY: boolean;
        center: boolean;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    Bar: import('vue').DefineComponent<{
        selectedBar: {
            type: ObjectConstructor;
            default: () => null;
        };
        barStyle: {
            type: (ObjectConstructor | FunctionConstructor)[];
            default: () => {
                fill: string;
            };
        };
        barStyleSelected: {
            type: (ObjectConstructor | FunctionConstructor)[];
            default: () => {
                fill: string;
            };
        };
        maxWidth: {
            type: NumberConstructor;
            default: number;
        };
        dataKeys: {
            type: () => [string, string];
            required: true;
        };
        gap: {
            type: NumberConstructor;
            default: number;
        };
    }, {
        getStyle: import('vue').ComputedRef<Function>;
        getStyleSelected: import('vue').ComputedRef<Function>;
        toKebabCase: (data: any) => {
            [x: string]: any;
        };
        bars: import('vue').Ref<import('vue3-charts/dist/types').Rectangle[]>;
        mouse: import('vue3-charts/dist/hooks/useMouse').MouseReturn;
        onBarClick: (bar: any, idx: number) => void;
    }, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps, Readonly<import('vue').ExtractPropTypes<{
        selectedBar: {
            type: ObjectConstructor;
            default: () => null;
        };
        barStyle: {
            type: (ObjectConstructor | FunctionConstructor)[];
            default: () => {
                fill: string;
            };
        };
        barStyleSelected: {
            type: (ObjectConstructor | FunctionConstructor)[];
            default: () => {
                fill: string;
            };
        };
        maxWidth: {
            type: NumberConstructor;
            default: number;
        };
        dataKeys: {
            type: () => [string, string];
            required: true;
        };
        gap: {
            type: NumberConstructor;
            default: number;
        };
    }>>, {
        selectedBar: Record<string, any>;
        barStyle: Function | Record<string, any>;
        barStyleSelected: Function | Record<string, any>;
        maxWidth: number;
        gap: number;
    }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
    Responsive: import('vue').DefineComponent<{}, {
        el: import('vue').Ref<null>;
        width: import('vue').Ref<number>;
        height: import('vue').Ref<number>;
    }, {}, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps, Readonly<import('vue').ExtractPropTypes<{}>>, {}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
