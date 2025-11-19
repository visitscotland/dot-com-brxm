export default cookieMixin;
declare namespace cookieMixin {
    namespace computed {
        function selectedLanguage(): any;
        function localeCookie(): any;
        function translationCookie(): string;
    }
    namespace methods {
        function cookieExists(cookie: any): boolean;
        function setCookie(name: any, value: any, newCookie: any, sessionCookie?: boolean): void;
    }
}
