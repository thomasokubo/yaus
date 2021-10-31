package com.tto.yaus.usecases.geturl;

import com.tto.yaus.gateways.UrlGateway;

public class GetUrlInteractor implements GetUrlInputPort {
    private final UrlGateway urlGateway;
    private final GetUrlOutputPort presenter;

    public GetUrlInteractor(UrlGateway urlGateway, GetUrlOutputPort presenter) {
        this.urlGateway = urlGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(String hash) {
        presenter.accepts(urlGateway.findByHash(hash));
    }
}
