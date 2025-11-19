declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * the layout of the card group
    * `grid`
    */
    variant: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
    * specifies the number of cards per row
    * `3` or `4`
    */
    cardsPerRow: {
        type: NumberConstructor;
        default: number;
        validator: (value: unknown) => boolean;
    };
    /**
    * sets the scroll snap behavior
    * * `always` - on all breakpoints
    * `true` - only below xl breakpoint
    * `false` - disabled
    * note: these props will be updated in future to remove the boolean
    * and keep only string options which would be a breaking change
    */
    scrollSnap: {
        type: (StringConstructor | BooleanConstructor)[];
        default: boolean;
        validator: (value: unknown) => boolean;
    };
}>, {}, {}, {
    cardGroupClasses(): (string | {
        'is-scroll-snap-responsive': boolean;
        'is-scroll-snap-always': boolean;
        'is-grid': boolean;
    })[];
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * the layout of the card group
    * `grid`
    */
    variant: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
    /**
    * specifies the number of cards per row
    * `3` or `4`
    */
    cardsPerRow: {
        type: NumberConstructor;
        default: number;
        validator: (value: unknown) => boolean;
    };
    /**
    * sets the scroll snap behavior
    * * `always` - on all breakpoints
    * `true` - only below xl breakpoint
    * `false` - disabled
    * note: these props will be updated in future to remove the boolean
    * and keep only string options which would be a breaking change
    */
    scrollSnap: {
        type: (StringConstructor | BooleanConstructor)[];
        default: boolean;
        validator: (value: unknown) => boolean;
    };
}>> & Readonly<{}>, {
    variant: string;
    cardsPerRow: number;
    scrollSnap: string | boolean;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
