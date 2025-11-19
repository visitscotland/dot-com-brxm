import { CludoCredentials } from '../../types/types';

declare function cludoAutocomplete(searchQuery: string, cludoCredentials: CludoCredentials): Promise<any>;
export default cludoAutocomplete;
