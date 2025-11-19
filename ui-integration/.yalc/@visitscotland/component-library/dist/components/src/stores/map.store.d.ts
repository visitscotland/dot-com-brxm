import { Ref } from 'vue';

declare const useMapStore: import('pinia').StoreDefinition<"map", Pick<{
    maps: Ref<any[], any[]>;
    activeSubcatFilters: Ref<any[], any[]>;
    selectedSubCategory: Ref<any, any>;
    activeMarkerPos: Ref<any, any>;
    activePlace: Ref<any, any>;
    addMapInstance: (payload: any) => void;
    setHoveredPlace: (payload: any) => void;
    setActivePlace: (payload: any) => void;
    setActiveSubcatFilters: (payload: any) => void;
    setSelectedSubcat: (payload: any) => void;
    setActiveMarkerPos: (payload: any) => void;
    getMapById: (id: string) => any;
    getHoveredStop: (mapId: string) => any;
    getActivePlace: (mapId: string) => any;
}, "maps" | "activeSubcatFilters" | "selectedSubCategory" | "activeMarkerPos" | "activePlace">, Pick<{
    maps: Ref<any[], any[]>;
    activeSubcatFilters: Ref<any[], any[]>;
    selectedSubCategory: Ref<any, any>;
    activeMarkerPos: Ref<any, any>;
    activePlace: Ref<any, any>;
    addMapInstance: (payload: any) => void;
    setHoveredPlace: (payload: any) => void;
    setActivePlace: (payload: any) => void;
    setActiveSubcatFilters: (payload: any) => void;
    setSelectedSubcat: (payload: any) => void;
    setActiveMarkerPos: (payload: any) => void;
    getMapById: (id: string) => any;
    getHoveredStop: (mapId: string) => any;
    getActivePlace: (mapId: string) => any;
}, never>, Pick<{
    maps: Ref<any[], any[]>;
    activeSubcatFilters: Ref<any[], any[]>;
    selectedSubCategory: Ref<any, any>;
    activeMarkerPos: Ref<any, any>;
    activePlace: Ref<any, any>;
    addMapInstance: (payload: any) => void;
    setHoveredPlace: (payload: any) => void;
    setActivePlace: (payload: any) => void;
    setActiveSubcatFilters: (payload: any) => void;
    setSelectedSubcat: (payload: any) => void;
    setActiveMarkerPos: (payload: any) => void;
    getMapById: (id: string) => any;
    getHoveredStop: (mapId: string) => any;
    getActivePlace: (mapId: string) => any;
}, "addMapInstance" | "setHoveredPlace" | "setActivePlace" | "setActiveSubcatFilters" | "setSelectedSubcat" | "setActiveMarkerPos" | "getMapById" | "getHoveredStop" | "getActivePlace">>;
export default useMapStore;
