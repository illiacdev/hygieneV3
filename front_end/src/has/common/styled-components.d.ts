import { CSSProp } from "styled-components";

// https://github.com/DefinitelyTyped/DefinitelyTyped/issues/31245#issuecomment-780019806

declare module 'react' {
    interface DOMAttributes<T> {
        css?: CSSProp
        mycss?: CSSProp
    }
}

declare module 'styled-components' {
    export interface ThemeProps<T> {
        css?: CSSProp;
    }
}

// and extend them!
declare module 'styled-components' {
    export interface DefaultTheme {
        borderRadius: string;

        colors: {
            main: string;
            secondary: string;
        };
    }
}



declare global {
    namespace JSX {
        interface IntrinsicAttributes {
            css?: CSSProp;
            selected?: boolean;
        }
    }
}
