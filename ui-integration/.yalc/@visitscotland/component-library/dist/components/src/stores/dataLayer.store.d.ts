import { Ref } from 'vue';

type Tag = {
    key: string;
    value: string;
};
declare const useDataLayerStore: import('pinia').StoreDefinition<"datalayer", Pick<{
    pageUrl: Ref<string, string>;
    tagsTestRun: Ref<boolean, boolean>;
    GTMData: Ref<object, object>;
    getValueFromKey: (key: string) => any;
    setTestRun: (payload: boolean) => void;
    setPageUrl: (payload: string) => void;
    processPayload: (payload: Tag) => void;
}, "pageUrl" | "tagsTestRun" | "GTMData">, Pick<{
    pageUrl: Ref<string, string>;
    tagsTestRun: Ref<boolean, boolean>;
    GTMData: Ref<object, object>;
    getValueFromKey: (key: string) => any;
    setTestRun: (payload: boolean) => void;
    setPageUrl: (payload: string) => void;
    processPayload: (payload: Tag) => void;
}, never>, Pick<{
    pageUrl: Ref<string, string>;
    tagsTestRun: Ref<boolean, boolean>;
    GTMData: Ref<object, object>;
    getValueFromKey: (key: string) => any;
    setTestRun: (payload: boolean) => void;
    setPageUrl: (payload: string) => void;
    processPayload: (payload: Tag) => void;
}, "getValueFromKey" | "setTestRun" | "setPageUrl" | "processPayload">>;
export default useDataLayerStore;
