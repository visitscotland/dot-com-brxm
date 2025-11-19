import { CludoCredentials, FederatedSearchResult } from '../../types/types';

declare function cludoSearch(searchTerm: string, cludoCredentials: CludoCredentials, page: number, selectedCategory: string, selectedCategoryKey: string): Promise<{
    results: FederatedSearchResult[];
    totalResults: any;
}>;
export default cludoSearch;
