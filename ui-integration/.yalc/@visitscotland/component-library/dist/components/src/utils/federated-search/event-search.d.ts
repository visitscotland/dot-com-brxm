import { FederatedSearchResult } from '../../types/types';

declare function eventSearch(api: string, searchTerm: string, page: number, selectedCategoryKey: string, selectedSubCategoryKey: string[], startDate: string, endDate: string, sortBy: string, siteLanguage: string): Promise<{
    results: FederatedSearchResult[];
    totalResults: any;
}>;
export default eventSearch;
