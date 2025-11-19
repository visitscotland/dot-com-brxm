declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * Total number of steps the bar represents
     */
    max: {
        type: NumberConstructor;
        required: true;
    };
    /**
     * The current step the bar represents
     */
    currentStep: {
        type: NumberConstructor;
        required: true;
    };
    /**
     * If set to true, instead of a single incremental progress bar, the component will
     * display as a series of discrete blocks
     */
    isStepped: {
        type: BooleanConstructor;
        required: false;
    };
    /**
     * If set to true, the progress bar is marked as complete and highlighted in green
     */
    isFull: {
        type: BooleanConstructor;
        required: false;
    };
    /**
     * Localised string to indicate the current stage of the progress bar. Will be interpolated
     * with the current step (on xxx) and total count (on yyy) to allow for different sentence
     * orders.
     *
     * e.g. "Step xxx of yyy"
     */
    progressLabel: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {
    compId: any;
}, {
    computedClasses(): string;
    /**
     * Interpolates the progress label into a localised string.
     */
    interpolProgressLabel(): string;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * Total number of steps the bar represents
     */
    max: {
        type: NumberConstructor;
        required: true;
    };
    /**
     * The current step the bar represents
     */
    currentStep: {
        type: NumberConstructor;
        required: true;
    };
    /**
     * If set to true, instead of a single incremental progress bar, the component will
     * display as a series of discrete blocks
     */
    isStepped: {
        type: BooleanConstructor;
        required: false;
    };
    /**
     * If set to true, the progress bar is marked as complete and highlighted in green
     */
    isFull: {
        type: BooleanConstructor;
        required: false;
    };
    /**
     * Localised string to indicate the current stage of the progress bar. Will be interpolated
     * with the current step (on xxx) and total count (on yyy) to allow for different sentence
     * orders.
     *
     * e.g. "Step xxx of yyy"
     */
    progressLabel: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    isStepped: boolean;
    isFull: boolean;
    progressLabel: string;
}, {}, {
    BProgress: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            value: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            height: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            max: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: number;
            };
            animated: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            precision: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            showProgress: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            showValue: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            striped: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            height: string;
            value: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            striped: boolean;
            animated: boolean;
            precision: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            showProgress: boolean;
            showValue: boolean;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            value: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: undefined;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: undefined;
            };
            height: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            max: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: number;
            };
            animated: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            precision: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            showProgress: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            showValue: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
            striped: {
                type: import('vue').PropType<boolean>;
                default: undefined;
            };
        }>>, {}, {}, {}, {}, {
            height: string;
            value: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            striped: boolean;
            animated: boolean;
            precision: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            showProgress: boolean;
            showValue: boolean;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        value: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: undefined;
        };
        variant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: undefined;
        };
        bgVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: undefined;
        };
        textVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
            default: undefined;
        };
        height: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        max: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        animated: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        precision: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: undefined;
        };
        showProgress: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        showValue: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
        striped: {
            type: import('vue').PropType<boolean>;
            default: undefined;
        };
    }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        height: string;
        value: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
        striped: boolean;
        animated: boolean;
        precision: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        showProgress: boolean;
        showValue: boolean;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
    BProgressBar: {
        new (...args: any[]): import('vue').CreateComponentPublicInstanceWithMixins<Readonly<import('vue').ExtractPropTypes<{
            value: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: number;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: null;
            };
            label: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            max: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            labelHtml: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            animated: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            precision: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: number;
            };
            showProgress: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            showValue: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            striped: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
        }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, import('vue').PublicProps, {
            label: string;
            value: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            striped: boolean;
            animated: boolean;
            labelHtml: string;
            precision: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            showProgress: boolean;
            showValue: boolean;
        }, true, {}, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, {}, any, import('vue').ComponentProvideOptions, {
            P: {};
            B: {};
            D: {};
            C: {};
            M: {};
            Defaults: {};
        }, Readonly<import('vue').ExtractPropTypes<{
            value: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: number;
            };
            variant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            bgVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
                default: null;
            };
            textVariant: {
                type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
                default: null;
            };
            label: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            max: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: undefined;
            };
            labelHtml: {
                type: import('vue').PropType<string>;
                default: undefined;
            };
            animated: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            precision: {
                type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
                default: number;
            };
            showProgress: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            showValue: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
            striped: {
                type: import('vue').PropType<boolean>;
                default: boolean;
            };
        }>>, {}, {}, {}, {}, {
            label: string;
            value: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
            textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
            striped: boolean;
            animated: boolean;
            labelHtml: string;
            precision: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
            showProgress: boolean;
            showValue: boolean;
        }>;
        __isFragment?: never;
        __isTeleport?: never;
        __isSuspense?: never;
    } & import('vue').ComponentOptionsBase<Readonly<import('vue').ExtractPropTypes<{
        value: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        variant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: null;
        };
        bgVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseColorVariant>;
            default: null;
        };
        textVariant: {
            type: import('vue').PropType<keyof import('bootstrap-vue-next').BaseTextColorVariant>;
            default: null;
        };
        label: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        max: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: undefined;
        };
        labelHtml: {
            type: import('vue').PropType<string>;
            default: undefined;
        };
        animated: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        precision: {
            type: import('vue').PropType<import('node_modules/bootstrap-vue-next/dist/src/types').Numberish>;
            default: number;
        };
        showProgress: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        showValue: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
        striped: {
            type: import('vue').PropType<boolean>;
            default: boolean;
        };
    }>>, {}, unknown, {}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, {
        label: string;
        value: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        max: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        variant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        bgVariant: keyof import('bootstrap-vue-next').BaseColorVariant | null;
        textVariant: keyof import('bootstrap-vue-next').BaseTextColorVariant | null;
        striped: boolean;
        animated: boolean;
        labelHtml: string;
        precision: import('node_modules/bootstrap-vue-next/dist/src/types').Numberish;
        showProgress: boolean;
        showValue: boolean;
    }, {}, string, {}, import('vue').GlobalComponents, import('vue').GlobalDirectives, string, import('vue').ComponentProvideOptions> & import('vue').VNodeProps & import('vue').AllowedComponentProps & import('vue').ComponentCustomProps & (new () => {
        $slots: Readonly<{
            default?: ((props: Record<string, never>) => any) | undefined;
        }> & {
            default?: ((props: Record<string, never>) => any) | undefined;
        };
    });
}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
