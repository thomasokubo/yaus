package com.tto.yaus.usecases.addurl;

import com.tto.yaus.entities.Hash;
import com.tto.yaus.gateways.UrlGateway;

public class AddUrlInteractor implements AddUrlInputPort {

    private final AddUrlOutputPort presenter;
    private final UrlGateway urlGateway;
    private static final Hash HASH = new Hash(8);

    public AddUrlInteractor(UrlGateway urlGateway, AddUrlOutputPort presenter) {
        this.urlGateway = urlGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(String url) {
        final String hash = HASH.generate();

        urlGateway.save(hash, url);

        presenter.accept(hash);
    }
}
