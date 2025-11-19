declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
    * Content for the top right, category badge
    */
    badgeOne: {
        type: StringConstructor;
        default: string;
    };
    /**
    * Content for multiple top right, category badges
    * Only appears if badgeOne not set
    *
    * Expects an array of strings
    */
    multiBadgeOne: {
        type: ArrayConstructor;
        default: any;
    };
    /**
    * Content for the middle right, pink badge
    */
    badgeTwo: {
        type: StringConstructor;
        default: string;
    };
    /**
    * Content for the bottom right, light pink badge
    */
    badgeThree: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {}, {
    /**
    * Limits multiBadgeOne to 3 entries
    */
    limitedMulti(): unknown[];
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
    * Content for the top right, category badge
    */
    badgeOne: {
        type: StringConstructor;
        default: string;
    };
    /**
    * Content for multiple top right, category badges
    * Only appears if badgeOne not set
    *
    * Expects an array of strings
    */
    multiBadgeOne: {
        type: ArrayConstructor;
        default: any;
    };
    /**
    * Content for the middle right, pink badge
    */
    badgeTwo: {
        type: StringConstructor;
        default: string;
    };
    /**
    * Content for the bottom right, light pink badge
    */
    badgeThree: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    badgeOne: string;
    multiBadgeOne: unknown[];
    badgeTwo: string;
    badgeThree: string;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
