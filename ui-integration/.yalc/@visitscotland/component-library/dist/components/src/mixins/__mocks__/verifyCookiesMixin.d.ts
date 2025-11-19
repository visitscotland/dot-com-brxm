export default cookieCheckerMixin;
declare namespace cookieCheckerMixin {
    function data(): {
        requiredCookiesAllowed: boolean;
        cookieManagerLoaded: boolean;
    };
    namespace computed {
        function cookiesAllowed(): any;
        function cookiesLoaded(): any;
    }
}
