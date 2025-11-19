export default osBranding;
declare namespace osBranding {
    namespace options {
        let div: string;
        let logo: string;
        let statement: string;
        let prefix: string;
        let suffix: string;
    }
    /**
     * Add the API logo and copyright statement.
     */
    function init(obj: any): void;
}
