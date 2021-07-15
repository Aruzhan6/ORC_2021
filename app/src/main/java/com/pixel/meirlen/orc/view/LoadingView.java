package com.pixel.meirlen.orc.view;

/**
 * Interface representing a BaseView that will use to load data.
 */
public interface LoadingView extends BaseView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */

    void hideLoading();

    /**
     * receive message on failed.
     */
    void loadingFailed(String message);

    /**
     * Process and show error.
     */
    void onError(Throwable throwable);
}
