import { Ref } from 'vue';

type Geometry = {
    type: string;
    coordinates: number[];
};
type Properties = {
    altText: string;
    imageSrc: string;
    stopCount: string;
    title: string;
};
type Stop = {
    type: string;
    geometry: Geometry;
    properties: Properties;
};
declare const useItinerariesStore: import('pinia').StoreDefinition<"itineraries", Pick<{
    highlightedStop: Ref<Stop, Stop>;
    setStopHighlighted: (newStop: any) => void;
    getHighlightedStopCoordinates: () => number[];
}, "highlightedStop">, Pick<{
    highlightedStop: Ref<Stop, Stop>;
    setStopHighlighted: (newStop: any) => void;
    getHighlightedStopCoordinates: () => number[];
}, never>, Pick<{
    highlightedStop: Ref<Stop, Stop>;
    setStopHighlighted: (newStop: any) => void;
    getHighlightedStopCoordinates: () => number[];
}, "setStopHighlighted" | "getHighlightedStopCoordinates">>;
export default useItinerariesStore;
