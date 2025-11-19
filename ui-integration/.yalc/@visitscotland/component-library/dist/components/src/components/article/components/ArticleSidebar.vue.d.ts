declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * This sets the alignment of the sidebar left or right of the section
     */
    sidebarAlign: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>, {}, {}, {
    sidebarAlignClass(): "vs-article-sidebar--right" | "vs-article-sidebar--left";
}, {}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * This sets the alignment of the sidebar left or right of the section
     */
    sidebarAlign: {
        type: StringConstructor;
        default: string;
        validator: (value: unknown) => any;
    };
}>> & Readonly<{}>, {
    sidebarAlign: string;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
