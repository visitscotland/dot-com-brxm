declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * URL to retrieve the information about the featured event
    * from, if no response do not display
    */
    sourceUrl: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {
    featuredEvent: any;
}, {}, {
    /**
     * Invoked when mounted, retrieves a featured event from the dms
     */
    retrieveFeaturedEvent(): void;
}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * URL to retrieve the information about the featured event
    * from, if no response do not display
    */
    sourceUrl: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    sourceUrl: string;
}, {}, {
    VsStretchedLinkCard: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
        link: {
            type: StringConstructor;
            required: true;
            default: string;
        };
        headingLevel: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        headingStyle: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        type: {
            type: StringConstructor;
            required: true;
            validator: (value: unknown) => any;
        };
        imgSrc: {
            type: StringConstructor;
            default: any;
        };
        imgAlt: {
            type: StringConstructor;
            default: string;
        };
        disabled: {
            type: BooleanConstructor;
            default: boolean;
        };
        videoId: {
            type: StringConstructor;
            default: string;
        };
        videoBtnText: {
            type: StringConstructor;
            default: string;
        };
        errorMessage: {
            type: StringConstructor;
            default: string;
        };
        errorType: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        warningSize: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        businessSupport: {
            type: BooleanConstructor;
            default: boolean;
        };
        isHomePage: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>, {
        videoStore: import('pinia').Store<"video", import('../../../stores/video.store').IVideosState, {}, {
            addVideo(newVideo: {
                videoId: string;
                videoDurationMsg: string;
                videoDuration: number;
                videoFullDuration: {
                    minutes: number;
                    seconds: number;
                    roundedMinutes: string;
                };
            }): void;
        }>;
    }, {
        jsDisabled: boolean;
        requiredCookies: 1[];
    }, {
        formattedVideoBtnText(): string;
        formattedVideoDuration(): string;
        stretchedLinkCardClasses(): string;
        videoDetails(): {
            videoId: string;
            videoDurationMsg: string;
            videoDuration: number;
            videoFullDuration: {
                minutes: number;
                seconds: number;
                roundedMinutes: string;
            };
        };
        videoLoaded(): boolean;
        warningClass(): string;
        showCookieWarning(): boolean;
        showError(): boolean;
        showWarning(): string | boolean;
        warningMessage(): string;
        warningAttrs(): {
            class: string;
            size: string;
        };
        isSvg(): boolean;
    }, {
        emitShowModal(): void;
    }, {
        data(): {
            requiredCookies: any[];
            requiredCookiesAllowed: boolean;
            cookieManagerLoaded: boolean;
        };
        methods: {
            managerLoaded(): void;
            cookiesUpdated(): void;
        };
        computed: {
            cookiesAllowed(): any;
            cookiesLoaded(): any;
        };
        mounted(): void;
    }, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
        link: {
            type: StringConstructor;
            required: true;
            default: string;
        };
        headingLevel: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        headingStyle: {
            type: (StringConstructor | NumberConstructor)[];
            default: string;
            validator: (value: unknown) => any;
        };
        type: {
            type: StringConstructor;
            required: true;
            validator: (value: unknown) => any;
        };
        imgSrc: {
            type: StringConstructor;
            default: any;
        };
        imgAlt: {
            type: StringConstructor;
            default: string;
        };
        disabled: {
            type: BooleanConstructor;
            default: boolean;
        };
        videoId: {
            type: StringConstructor;
            default: string;
        };
        videoBtnText: {
            type: StringConstructor;
            default: string;
        };
        errorMessage: {
            type: StringConstructor;
            default: string;
        };
        errorType: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        warningSize: {
            type: StringConstructor;
            default: string;
            validator: (value: unknown) => any;
        };
        businessSupport: {
            type: BooleanConstructor;
            default: boolean;
        };
        isHomePage: {
            type: BooleanConstructor;
            default: boolean;
        };
    }>> & Readonly<{}>, {
        link: string;
        disabled: boolean;
        headingStyle: string | number;
        imgAlt: string;
        imgSrc: string;
        headingLevel: string | number;
        businessSupport: boolean;
        videoId: string;
        videoBtnText: string;
        errorMessage: string;
        errorType: string;
        warningSize: string;
        isHomePage: boolean;
    }, {}, {
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
        VsLink: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            href: {
                type: StringConstructor;
                default: any;
            };
            type: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            iconSize: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            disabled: {
                type: BooleanConstructor;
                default: boolean;
            };
            dataLayerValue: {
                type: StringConstructor;
                default: any;
            };
        }>, {}, {}, {
            variantClass(): string;
            iconName(): string;
            iconVariant(): string;
        }, {
            clickHandler(event: any): void;
            navigateToUrl(): void;
            keyHandler(event: any): void;
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
            href: {
                type: StringConstructor;
                default: any;
            };
            type: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            variant: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            iconSize: {
                type: StringConstructor;
                default: string;
                validator: (value: unknown) => any;
            };
            disabled: {
                type: BooleanConstructor;
                default: boolean;
            };
            dataLayerValue: {
                type: StringConstructor;
                default: any;
            };
        }>> & Readonly<{}>, {
            type: string;
            variant: string;
            disabled: boolean;
            href: string;
            iconSize: string;
            dataLayerValue: string;
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
        }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
        VsImg: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
            src: {
                type: StringConstructor;
                required: true;
            };
            lowResImage: {
                type: StringConstructor;
                default: any;
            };
            alt: {
                type: StringConstructor;
                default: string;
            };
            fluid: {
                type: BooleanConstructor;
                default: boolean;
            };
            fluidGrow: {
                type: BooleanConstructor;
                default: boolean;
            };
            useGenericLqip: {
                type: BooleanConstructor;
                default: boolean;
            };
            useLazyLoading: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>, {}, {}, {
            imgStyle(): {
                backgroundImage: string;
            };
            isSvg(): boolean;
            computedSrcSet(): unknown;
        }, {}, {
            computed: {
                fullSrcSet(): string;
            };
            methods: {
                specificImgSize(size: any): string;
            };
        } | {
            inject: {
                cols: {
                    default: number;
                };
                sm: {
                    default: number;
                };
                md: {
                    default: number;
                };
                lg: {
                    default: number;
                };
                xl: {
                    default: number;
                };
            };
            computed: {
                computedSizes(): string;
            };
        }, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
            src: {
                type: StringConstructor;
                required: true;
            };
            lowResImage: {
                type: StringConstructor;
                default: any;
            };
            alt: {
                type: StringConstructor;
                default: string;
            };
            fluid: {
                type: BooleanConstructor;
                default: boolean;
            };
            fluidGrow: {
                type: BooleanConstructor;
                default: boolean;
            };
            useGenericLqip: {
                type: BooleanConstructor;
                default: boolean;
            };
            useLazyLoading: {
                type: BooleanConstructor;
                default: boolean;
            };
        }>> & Readonly<{}>, {
            fluid: boolean;
            fluidGrow: boolean;
            lowResImage: string;
            alt: string;
            useGenericLqip: boolean;
            useLazyLoading: boolean;
        }, {}, {
            BImg: import('vue').DefineComponent<{
                rounded: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: boolean;
                };
                start: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                end: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                center: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                fluid: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                src: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                blank: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                blankColor: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                block: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                fluidGrow: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                height: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                lazy: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                sizes: {
                    type: import('vue').PropType<string | readonly string[]>;
                    default: undefined;
                };
                srcset: {
                    type: import('vue').PropType<string | readonly string[]>;
                    default: undefined;
                };
                thumbnail: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                width: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                roundedTop: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
                roundedBottom: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
                roundedStart: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
                roundedEnd: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
            }, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
                rounded: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: boolean;
                };
                start: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                end: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                center: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                fluid: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                src: {
                    type: import('vue').PropType<string>;
                    default: undefined;
                };
                blank: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                blankColor: {
                    type: import('vue').PropType<string>;
                    default: string;
                };
                block: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                fluidGrow: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                height: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                lazy: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                sizes: {
                    type: import('vue').PropType<string | readonly string[]>;
                    default: undefined;
                };
                srcset: {
                    type: import('vue').PropType<string | readonly string[]>;
                    default: undefined;
                };
                thumbnail: {
                    type: import('vue').PropType<boolean>;
                    default: boolean;
                };
                width: {
                    type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                    default: undefined;
                };
                roundedTop: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
                roundedBottom: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
                roundedStart: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
                roundedEnd: {
                    type: import('vue').PropType<boolean | import('bootstrap-vue-next').RadiusElement>;
                    default: undefined;
                };
            }>>, {
                fluid: boolean;
                start: boolean;
                end: boolean;
                center: boolean;
                src: string;
                srcset: string | readonly string[];
                width: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                height: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
                lazy: boolean;
                block: boolean;
                rounded: boolean | import('bootstrap-vue-next').RadiusElement;
                roundedTop: boolean | import('bootstrap-vue-next').RadiusElement;
                roundedBottom: boolean | import('bootstrap-vue-next').RadiusElement;
                roundedStart: boolean | import('bootstrap-vue-next').RadiusElement;
                roundedEnd: boolean | import('bootstrap-vue-next').RadiusElement;
                blank: boolean;
                blankColor: string;
                fluidGrow: boolean;
                sizes: string | readonly string[];
                thumbnail: boolean;
            }, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
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
    }, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
