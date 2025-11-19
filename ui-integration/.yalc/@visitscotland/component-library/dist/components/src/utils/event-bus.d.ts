export default class EventBus {
    bus: HTMLElement;
    /**
     * Add an event listener.
     */
    addEventListener(event: any, callback: any): void;
    /**
     * Remove an event listener.
     */
    removeEventListener(event: any, callback: any): void;
    /**
     * Dispatch an event.
     */
    dispatchEvent(event: any, detail?: {}): void;
}
