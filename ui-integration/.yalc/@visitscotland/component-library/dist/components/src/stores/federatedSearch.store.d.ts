import { CludoCredentials } from '../types/types';

declare const useFederatedSearchStore: import('pinia').StoreDefinition<"federatedSearch", Pick<{
    filters: import('vue').Ref<any, any>;
    currentPage: import('vue').Ref<number, number>;
    cludoCredentials: import('vue').Ref<{
        apiKey: string;
        customerId: number;
        engineId: number;
    }, CludoCredentials | {
        apiKey: string;
        customerId: number;
        engineId: number;
    }>;
    cludoError: import('vue').Ref<boolean, boolean>;
    eventsApi: import('vue').Ref<any, any>;
    eventsApiError: import('vue').Ref<boolean, boolean>;
    getAutoComplete: () => Promise<any>;
    getSearchResults: () => Promise<void>;
    isLoading: import('vue').Ref<boolean, boolean>;
    results: import('vue').Ref<any, any>;
    searchTerm: import('vue').Ref<string, string>;
    searchUrl: import('vue').Ref<any, any>;
    selectedCategory: import('vue').Ref<any, any>;
    selectedCategoryKey: import('vue').Ref<any, any>;
    selectedSubCategory: import('vue').Ref<any[], any[]>;
    selectedSubCategoryKey: import('vue').Ref<any[], any[]>;
    totalResults: import('vue').Ref<any, any>;
    totalResultsCludo: import('vue').Ref<any, any>;
    totalResultsEvents: import('vue').Ref<any, any>;
    navigateToResultsPage: (resetPageNo?: boolean) => void;
    isHomePage: import('vue').Ref<boolean, boolean>;
    startDate: import('vue').Ref<string, string>;
    endDate: import('vue').Ref<string, string>;
    sortBy: import('vue').Ref<any, any>;
    siteLanguage: import('vue').Ref<any, any>;
}, "filters" | "currentPage" | "isHomePage" | "selectedSubCategory" | "isLoading" | "selectedCategory" | "searchTerm" | "endDate" | "startDate" | "cludoCredentials" | "cludoError" | "eventsApi" | "eventsApiError" | "results" | "searchUrl" | "selectedCategoryKey" | "selectedSubCategoryKey" | "totalResults" | "totalResultsCludo" | "totalResultsEvents" | "sortBy" | "siteLanguage">, Pick<{
    filters: import('vue').Ref<any, any>;
    currentPage: import('vue').Ref<number, number>;
    cludoCredentials: import('vue').Ref<{
        apiKey: string;
        customerId: number;
        engineId: number;
    }, CludoCredentials | {
        apiKey: string;
        customerId: number;
        engineId: number;
    }>;
    cludoError: import('vue').Ref<boolean, boolean>;
    eventsApi: import('vue').Ref<any, any>;
    eventsApiError: import('vue').Ref<boolean, boolean>;
    getAutoComplete: () => Promise<any>;
    getSearchResults: () => Promise<void>;
    isLoading: import('vue').Ref<boolean, boolean>;
    results: import('vue').Ref<any, any>;
    searchTerm: import('vue').Ref<string, string>;
    searchUrl: import('vue').Ref<any, any>;
    selectedCategory: import('vue').Ref<any, any>;
    selectedCategoryKey: import('vue').Ref<any, any>;
    selectedSubCategory: import('vue').Ref<any[], any[]>;
    selectedSubCategoryKey: import('vue').Ref<any[], any[]>;
    totalResults: import('vue').Ref<any, any>;
    totalResultsCludo: import('vue').Ref<any, any>;
    totalResultsEvents: import('vue').Ref<any, any>;
    navigateToResultsPage: (resetPageNo?: boolean) => void;
    isHomePage: import('vue').Ref<boolean, boolean>;
    startDate: import('vue').Ref<string, string>;
    endDate: import('vue').Ref<string, string>;
    sortBy: import('vue').Ref<any, any>;
    siteLanguage: import('vue').Ref<any, any>;
}, never>, Pick<{
    filters: import('vue').Ref<any, any>;
    currentPage: import('vue').Ref<number, number>;
    cludoCredentials: import('vue').Ref<{
        apiKey: string;
        customerId: number;
        engineId: number;
    }, CludoCredentials | {
        apiKey: string;
        customerId: number;
        engineId: number;
    }>;
    cludoError: import('vue').Ref<boolean, boolean>;
    eventsApi: import('vue').Ref<any, any>;
    eventsApiError: import('vue').Ref<boolean, boolean>;
    getAutoComplete: () => Promise<any>;
    getSearchResults: () => Promise<void>;
    isLoading: import('vue').Ref<boolean, boolean>;
    results: import('vue').Ref<any, any>;
    searchTerm: import('vue').Ref<string, string>;
    searchUrl: import('vue').Ref<any, any>;
    selectedCategory: import('vue').Ref<any, any>;
    selectedCategoryKey: import('vue').Ref<any, any>;
    selectedSubCategory: import('vue').Ref<any[], any[]>;
    selectedSubCategoryKey: import('vue').Ref<any[], any[]>;
    totalResults: import('vue').Ref<any, any>;
    totalResultsCludo: import('vue').Ref<any, any>;
    totalResultsEvents: import('vue').Ref<any, any>;
    navigateToResultsPage: (resetPageNo?: boolean) => void;
    isHomePage: import('vue').Ref<boolean, boolean>;
    startDate: import('vue').Ref<string, string>;
    endDate: import('vue').Ref<string, string>;
    sortBy: import('vue').Ref<any, any>;
    siteLanguage: import('vue').Ref<any, any>;
}, "getAutoComplete" | "getSearchResults" | "navigateToResultsPage">>;
export default useFederatedSearchStore;
