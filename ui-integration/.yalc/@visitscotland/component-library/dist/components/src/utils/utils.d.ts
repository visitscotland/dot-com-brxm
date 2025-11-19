import { SelectOption } from '../types/types';

declare global {
    interface Window {
        VS: any;
    }
}
export declare function getURLParameters(): {
    [k: string]: string;
};
export declare function getProductTypes(): SelectOption[];
