type FullDuration = {
    minutes: number;
    seconds: number;
    roundedMinutes: string;
};
type Video = {
    videoId: string;
    videoDurationMsg: string;
    videoDuration: number;
    videoFullDuration: FullDuration;
};
type VideoSet = {
    [key: string]: Video;
};
export interface IVideosState {
    videos: VideoSet;
}
declare const useVideoStore: import('pinia').StoreDefinition<"video", IVideosState, {}, {
    addVideo(newVideo: Video): void;
}>;
export default useVideoStore;
