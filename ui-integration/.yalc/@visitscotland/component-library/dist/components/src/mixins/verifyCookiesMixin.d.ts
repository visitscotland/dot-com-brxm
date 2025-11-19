export default cookieCheckerMixin;
declare namespace cookieCheckerMixin {
    function data(): {
        requiredCookies: any[];
        requiredCookiesAllowed: boolean;
        cookieManagerLoaded: boolean;
    };
    namespace methods {
        function managerLoaded(): void;
        function cookiesUpdated(): void;
    }
    namespace computed {
        function cookiesAllowed(): any;
        function cookiesLoaded(): any;
    }
    function mounted(): void;
}
