declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The path of the SVG to display, relative to /src/assets
     */
    path: {
        type: StringConstructor;
        required: true;
    };
    /**
 * The fill color of the SVG
 */
    fill: {
        type: StringConstructor;
        default: string;
    };
    /**
 * The height attribute of the SVG
 */
    height: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    /**
 * The width attribute of the SVG
 */
    width: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
}>, {}, {
    svg: any;
}, {
    nativeAttrs(): any;
    attributes(): any;
    nativeStyleAttrMap(): any;
    children(): any;
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The path of the SVG to display, relative to /src/assets
     */
    path: {
        type: StringConstructor;
        required: true;
    };
    /**
 * The fill color of the SVG
 */
    fill: {
        type: StringConstructor;
        default: string;
    };
    /**
 * The height attribute of the SVG
 */
    height: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
    /**
 * The width attribute of the SVG
 */
    width: {
        type: (StringConstructor | NumberConstructor)[];
        default: string;
    };
}>> & Readonly<{}>, {
    fill: string;
    height: string | number;
    width: string | number;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
