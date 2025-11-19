export default validateFormElementMixin;
declare namespace validateFormElementMixin {
    let emits: string[];
    function data(): {
        inputVal: any;
        isInvalid: any;
    };
    namespace computed {
        /**
         * calculate whether element is required
         */
        function isRequired(): boolean;
        /**
         * set rules object for validation
         * needed because `required`, `email` and other values
         * can't be key value pairs
         */
        function rules(): {
            inputVal: {};
        } | {
            inputVal?: undefined;
        };
        function errorsList(): any[];
    }
    namespace watch {
        function errorsList(newValue: any): void;
    }
    namespace methods {
        /**
         * manually run validation and emit to parent
         */
        function manualValidate(): void;
        /**
         * Emit status of input - for automatic updating
         */
        function emitStatus(): void;
    }
}
