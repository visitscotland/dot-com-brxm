export default cookieCheckerComposable;
declare function cookieCheckerComposable(): {
    requiredCookies: import('vue').Ref<any[], any[]>;
    cookiesAllowed: import('vue').ComputedRef<boolean>;
    cookiesLoaded: import('vue').ComputedRef<true | {
        cookieManagerLoaded: import('vue').Ref<boolean, boolean>;
    }>;
};
