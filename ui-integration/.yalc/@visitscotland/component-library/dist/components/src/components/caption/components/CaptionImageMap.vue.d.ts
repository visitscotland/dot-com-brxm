declare const _default: import('vue').DefineComponent<import('vue').ExtractPropTypes<{
    /**
     * The image latitude
     */
    latitude: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The image longitude
     */
    longitude: {
        type: StringConstructor;
        default: string;
    };
    mapOutlineColor: {
        type: StringConstructor;
        default: string;
    };
    mapMarkerColor: {
        type: StringConstructor;
        default: string;
    };
}>, {}, {
    cornerCoordinates: {
        NE: number;
        NW: number;
        SE: number;
        SW: number;
    };
    mapWidth: number;
    mapHeight: number;
}, {
    positionY(): number;
    positionX(): number;
}, {
    calculateAxisPosition(scaleStart: any, scaleEnd: any, coord: any, axisLength: any): number;
}, import('vue').ComponentOptionsMixin, import('vue').ComponentOptionsMixin, {}, string, import('vue').PublicProps, Readonly<import('vue').ExtractPropTypes<{
    /**
     * The image latitude
     */
    latitude: {
        type: StringConstructor;
        default: string;
    };
    /**
     * The image longitude
     */
    longitude: {
        type: StringConstructor;
        default: string;
    };
    mapOutlineColor: {
        type: StringConstructor;
        default: string;
    };
    mapMarkerColor: {
        type: StringConstructor;
        default: string;
    };
}>> & Readonly<{}>, {
    latitude: string;
    longitude: string;
    mapOutlineColor: string;
    mapMarkerColor: string;
}, {}, {}, {}, string, import('vue').ComponentProvideOptions, true, {}, any>;
export default _default;
