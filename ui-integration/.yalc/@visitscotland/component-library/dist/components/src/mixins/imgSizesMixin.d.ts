export default imgSizesMixin;
declare namespace imgSizesMixin {
    namespace inject {
        namespace cols {
            let _default: number;
            export { _default as default };
        }
        namespace sm {
            let _default_1: number;
            export { _default_1 as default };
        }
        namespace md {
            let _default_2: number;
            export { _default_2 as default };
        }
        namespace lg {
            let _default_3: number;
            export { _default_3 as default };
        }
        namespace xl {
            let _default_4: number;
            export { _default_4 as default };
        }
    }
    namespace computed {
        /**
         * Generates a string of media queries for the image, which can be used in the `sizes`
         * attribute. This string is parsed left to right searching for the first match, so we
         * start with the largest viewport size and work down to the smallest, requesting an
         * image at an appropriate percent of the viewport width.
         */
        function computedSizes(): string;
    }
}
